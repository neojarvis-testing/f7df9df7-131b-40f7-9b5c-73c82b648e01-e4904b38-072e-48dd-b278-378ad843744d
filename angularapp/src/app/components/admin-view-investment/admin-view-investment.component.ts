import { Component, OnInit, AfterViewInit, ViewChild, ElementRef } from '@angular/core';
import { InvestmentService } from '../../services/investment.service';
import { Investment } from '../../models/investment.model';
import { Router } from '@angular/router';
import { Chart } from 'chart.js';  // Importing Chart.js for bar chart

@Component({
  selector: 'app-admin-view-investment',
  templateUrl: './admin-view-investment.component.html',
  styleUrls: ['./admin-view-investment.component.css'],
})
export class AdminViewInvestmentComponent implements OnInit, AfterViewInit {
  investments: Investment[] = [];
  filteredInvestments: Investment[] = [];
  categories: string[] = ['Stock', 'Bond', 'Real Estate'];
  searchTerm: string = '';
  selectedCategory: string = '';
  showDeletePopup = false;
  investmentToDelete: number | null = null;

  // Data for the bar chart
  inquiriesData: { investmentId: number, inquiries: number }[] = [
    { investmentId: 1, inquiries: 10 },
    { investmentId: 2, inquiries: 5 },
    { investmentId: 3, inquiries: 20 },
    // Add your actual data dynamically
  ];

  @ViewChild('inquiriesBarChart', { static: false }) inquiriesBarChart!: ElementRef;

  constructor(private investmentService: InvestmentService, private router: Router) {}

  ngOnInit(): void {
    this.loadInvestments();
  }

  ngAfterViewInit(): void {
    // Ensure the chart is created after the view is initialized
    if (this.inquiriesBarChart) {
      this.createBarChart();
    }
  }

  loadInvestments(): void {
    this.investmentService.getAllInvestments().subscribe((data: Investment[]) => {
      this.investments = data;
      this.filteredInvestments = data;
      // Ensure the chart is created after data is loaded
      if (this.inquiriesBarChart) {
        this.createBarChart();
      }
    });
  }

  // Search and Filter Functions
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

  // Edit and Delete Investment Functions
  onEdit(investmentId: number): void {
    this.router.navigate(['/admin/edit-investment', investmentId]);
  }

  onDeleteConfirm(investmentId: number): void {
    this.showDeletePopup = true;
    this.investmentToDelete = investmentId;
  }
  
  onDelete(): void {
    if (this.investmentToDelete !== null) {
      this.investmentService.deleteInvestment(this.investmentToDelete).subscribe({
        next: () => {
          this.loadInvestments();
          this.closeDeletePopup();
        },
        error: (err) => {
          alert('An error occurred while deleting the investment. Please try again.');
        },
      });
    }
  }

  closeDeletePopup(): void {
    this.showDeletePopup = false;
    this.investmentToDelete = null;
  }

  // Create Bar Chart based on inquiries data
  createBarChart(): void {
    if (this.inquiriesBarChart && this.inquiriesBarChart.nativeElement) {
      const chartData = this.inquiriesData.map(data => data.inquiries);
      const chartLabels = this.inquiriesData.map(data => `Investment ${data.investmentId}`);

      new Chart(this.inquiriesBarChart.nativeElement, {
        type: 'bar',
        data: {
          labels: chartLabels,
          datasets: [{
            label: 'Number of Inquiries',
            data: chartData,
            backgroundColor: ['#28a745', '#dc3545', '#007bff'],  // Green, Red, Blue
          }]
        },
        options: {
          responsive: true,
          plugins: {
            legend: {
              position: 'top',
            },
          },
          scales: {
            y: {
              beginAtZero: true
            }
          }
        },
      });
    }
  }
}
