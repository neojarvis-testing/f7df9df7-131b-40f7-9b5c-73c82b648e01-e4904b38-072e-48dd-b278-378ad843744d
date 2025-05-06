import { Component, OnInit, AfterViewInit, ViewChild, ElementRef } from '@angular/core';
import { InvestmentService } from '../../services/investment.service';
import { Investment } from '../../models/investment.model';
import { Router } from '@angular/router';
import { Chart } from 'chart.js';
import { InvestmentInquiry } from 'src/app/models/investment-inquiry.model';
import { InvestmentInquiryService } from 'src/app/services/investment-inquiry.service';

@Component({
  selector: 'app-admin-view-investment',
  templateUrl: './admin-view-investment.component.html',
  styleUrls: ['./admin-view-investment.component.css'],
})
export class AdminViewInvestmentComponent implements OnInit, AfterViewInit {
  // Array to hold all investment data
  investments: Investment[] = [];
  filteredInvestments: Investment[] = [];

  // Available investment types
  types: string[] = ['Bond', 'Stock', 'Real Estate'];
  categories: string[] = [];

  // Search and filter parameters
  searchTerm: string = '';
  selectedCategory: string = '';

  // Delete confirmation popup
  showDeletePopup = false;
  investmentToDelete: number | null = null;

  // Chart instances to track graphs
  chartInstanceBar: Chart | null = null;
  chartInstancePie: Chart | null = null;

  // Sample inquiry data used for visualization in Bar Chart
  inquiriesData: { investmentId: number, inquiries: number }[] = [
    { investmentId: 1, inquiries: 10 },
    { investmentId: 2, inquiries: 5 },
    { investmentId: 3, inquiries: 20 },
  ];

  // References to HTML elements for charts
  @ViewChild('inquiriesBarChart', { static: false }) inquiriesBarChart!: ElementRef;
  @ViewChild('investmentPieChart', { static: false }) investmentPieChart!: ElementRef;

  constructor(private investmentService: InvestmentService, private router: Router, private investmentInquiryService: InvestmentInquiryService) {}

  // Lifecycle hook - called when the component initializes
  ngOnInit(): void {
    this.loadInvestments();
  }

  // Lifecycle hook - called after view initialization
  ngAfterViewInit(): void {
    // Delay chart initialization to ensure elements are available
    setTimeout(() => {
      if (this.inquiriesBarChart?.nativeElement) {
        this.createBarChart();
      }
      if (this.investmentPieChart?.nativeElement) {
        this.createPieChart();
      }
    }, 500);
  }

  // Fetch investment data from the service
  loadInvestments(): void {
    this.investmentService.getAllInvestments().subscribe((data: any) => {
      console.log("Raw API Response:", data); // Debugging line to verify API response
      this.investments = Array.isArray(data) ? data : data.investments || [];
      this.filteredInvestments = [...this.investments];
      this.categories = [...new Set(this.filteredInvestments.map(data => data.name))];
      this.createPieChart();
    });
  }

  // Search functionality to filter investments based on name
  onSearch(): void {
    this.filteredInvestments = this.investments.filter((investment) =>
      investment.name.toLowerCase().includes(this.searchTerm.toLowerCase())
    );
    this.applyFilter();
  }

  // Filter investments based on category
  onFilter(): void {
    this.applyFilter();
  }

  // Apply both search and filter conditions
  applyFilter(): void {
    this.filteredInvestments = this.investments.filter((investment) => {
      const matchesCategory = this.selectedCategory === '' || investment.type === this.selectedCategory;
      const matchesSearch = investment.name.toLowerCase().includes(this.searchTerm.toLowerCase());
      return matchesCategory && matchesSearch;
    });
  }

  // Navigate to the edit investment page
  onEdit(investmentId: number): void {
    this.router.navigate(['/admin/edit-investment', investmentId]);
  }

  // Show delete confirmation popup
  onDeleteConfirm(investmentId: number): void {
    this.showDeletePopup = true;
    this.investmentToDelete = investmentId;
  }

  // Delete an investment after confirmation
  onDelete(): void {
    if (this.investmentToDelete) {
      this.investmentService.deleteInvestment(this.investmentToDelete).subscribe({
        next: () => {
          this.loadInvestments(); // Reload data after deletion
          this.closeDeletePopup();
        },
        error: (err) => {
          console.error('Error deleting investment:', err);
          alert('An error occurred while deleting the investment. Please try again.');
        },
      });
    }
  }

  // Close delete confirmation popup
  closeDeletePopup(): void {
    this.showDeletePopup = false;
    this.investmentToDelete = null;
  }

  // Hex color generator
  hexCharacters = [0,1,2,3,4,5,6,7,8,9,"A","B","C","D","E","F"];

  getCharacter(index) {
    return this.hexCharacters[index];
  }

  generateJustOneColor(){
    let hexColorRep = "#";
    for (let position = 0; position < 6; position++){
        hexColorRep += this.getCharacter(position);
    }
    return hexColorRep;
  }

  // Generate bar chart for investment inquiries
  createBarChart(): void {
    if (this.chartInstanceBar) {
      this.chartInstanceBar.destroy(); // Destroy previous instance before re-creating
    }

    const chartLabels = this.inquiriesData.map(data => `Investment ${data.investmentId}`);
    const chartData = this.inquiriesData.map(data => data.inquiries);

    this.chartInstanceBar = new Chart(this.inquiriesBarChart.nativeElement, {
      type: 'bar',
      data: {
        labels: chartLabels,
        datasets: [{
          label: 'Number of Inquiries',
          data: chartData,
          backgroundColor: [this.generateJustOneColor(), this.generateJustOneColor(), this.generateJustOneColor()]
        }]
      },
      options: {
        responsive: true,
        plugins: {
          legend: { position: 'top' }
        },
        scales: {
          y: { beginAtZero: true }
        }
      }
    });
  }

  // Generate pie chart for investment distribution
  createPieChart(): void {
    const dynamicColors = this.generateRandomColor(this.categories.length);
    console.log(dynamicColors); // Debugging line

    if (this.chartInstancePie) {
      this.chartInstancePie.destroy(); // Destroy previous instance before re-creating
    }

    const categoryData = this.categories.map(category =>
      this.investments.filter(investment => investment.name === category).length
    );

    this.chartInstancePie = new Chart(this.investmentPieChart.nativeElement, {
      type: 'pie',
      data: {
        labels: this.categories,
        datasets: [{
          data: categoryData,
          backgroundColor: dynamicColors
        }]
      },
      options: {
        responsive: true,
        plugins: {
          legend: { position: 'bottom' }
        }
      }
    });
  }

  // Generate random colors dynamically for chart display
  generateRandomColor(count: number): string[] {
    return Array.from({ length: count }, () =>
      `#${Math.floor(Math.random() * 16777215).toString(16)}` // Generates a random HEX color
    );
  }
}