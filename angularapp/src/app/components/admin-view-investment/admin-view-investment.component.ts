import { Component, OnInit, ViewChild, ElementRef } from '@angular/core';
import { InvestmentService } from '../../services/investment.service';
import { Investment } from '../../models/investment.model';
import { Router } from '@angular/router';
 
@Component({
  selector: 'app-admin-view-investment',
  templateUrl: './admin-view-investment.component.html',
})
export class AdminViewInvestmentComponent implements OnInit{
  investments: Investment[] = [];
  filteredInvestments: Investment[] = [];
  categories: string[] = [];
  searchTerm: string = '';
  selectedCategory: string = '';
  showDeletePopup = false;
  investmentToDelete: number | null = null;
 
  inquiriesData: { investmentId: number, inquiries: number }[] = [
    { investmentId: 1, inquiries: 10 },
    { investmentId: 2, inquiries: 5 },
    { investmentId: 3, inquiries: 20 },
  ];
 
  @ViewChild('inquiriesBarChart', { static: false }) inquiriesBarChart!: ElementRef;
  @ViewChild('investmentPieChart', { static: false }) investmentPieChart!: ElementRef;
 
  constructor(private readonly investmentService: InvestmentService, private readonly router: Router) {}
 
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
    this.router.navigate(['/admin/edit-investment', investmentId]);
  }
 
  onDeleteConfirm(investmentId: number): void {
    this.showDeletePopup = true;
    this.investmentToDelete = investmentId;
  }
 
  onDelete(): void {
    if (this.investmentToDelete) {
      this.investmentService.deleteInvestment(this.investmentToDelete).subscribe({
        next: () => {
          this.loadInvestments();
          this.closeDeletePopup();
        },
        error: (err) => {
          console.error('Error deleting investment:', err);
          alert('An error occurred while deleting the investment. Please try again.');
        },
      });
    }
  }
 
  closeDeletePopup(): void {
    this.showDeletePopup = false;
    this.investmentToDelete = null;
  }
 
 hexCharacters = [0,1,2,3,4,5,6,7,8,9,"A","B","C","D","E","F"]
 
generateJustOneColor(){
    let hexColorRep = "#"
    return hexColorRep
}
}