import { Component, OnInit } from '@angular/core';
import { InvestmentService } from '../../services/investment.service';
import { Investment } from '../../models/investment.model';

@Component({
  selector: 'app-admin-view-investment',
  templateUrl: './admin-view-investment.component.html',
  styleUrls: ['./admin-view-investment.component.css'],
})
export class AdminViewInvestmentComponent implements OnInit {
  investments: Investment[] = [];
  filteredInvestments: Investment[] = [];
  categories: string[] = ['Stock', 'Bond', 'Real Estate'];
  searchTerm: string = '';
  selectedCategory: string = '';
  showDeletePopup = false;
  investmentToDelete: number | null = null;

  constructor(private investmentService: InvestmentService) {}

  ngOnInit(): void {
    this.loadInvestments();
  }

  loadInvestments(): void {
    this.investmentService.getAllInvestments().subscribe((data: Investment[]) => {
      this.investments = data;
      this.filteredInvestments = data;
    });
  }

  onSearch(): void {
    this.filteredInvestments = this.investments.filter((investment) =>
      investment.name.toLowerCase().includes(this.searchTerm.toLowerCase())
    );
    this.applyFilter();
  }

  onFilter(): void {
    this.applyFilter();
  }

  applyFilter(): void {
    this.filteredInvestments = this.investments.filter((investment) => {
      const matchesCategory =
        this.selectedCategory === '' || investment.type === this.selectedCategory;
      const matchesSearch = investment.name
        .toLowerCase()
        .includes(this.searchTerm.toLowerCase());
      return matchesCategory && matchesSearch;
    });
  }

  onEdit(investmentId: number): void {
    // Navigate to the edit-investment component
    console.log('Navigate to edit investment:', investmentId);
  }

  onDeleteConfirm(investmentId: number): void {
    this.showDeletePopup = true;
    this.investmentToDelete = investmentId;
  }

  onDelete(): void {
    if (this.investmentToDelete !== null) {
      this.investmentService.deleteInvestment(this.investmentToDelete).subscribe({
        next: () => {
          console.log('Investment deleted:', this.investmentToDelete);
          this.loadInvestments();
          this.closeDeletePopup();
        },
        error: (err) => {
          console.error('Failed to delete investment:', err);
          alert('An error occurred while deleting the investment. Please try again.');
        },
      });
    }
  }

  closeDeletePopup(): void {
    this.showDeletePopup = false;
    this.investmentToDelete = null;
  }
}
