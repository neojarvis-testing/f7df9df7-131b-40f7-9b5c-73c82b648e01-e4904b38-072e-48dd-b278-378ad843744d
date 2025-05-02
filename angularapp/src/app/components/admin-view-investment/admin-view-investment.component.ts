import { Component, OnInit, AfterViewInit, ViewChild, ElementRef } from '@angular/core';
import { InvestmentService } from '../../services/investment.service';
import { Investment } from '../../models/investment.model';
import { Router } from '@angular/router';
//import { Chart } from 'chart.js';
 
@Component({
  selector: 'app-admin-view-investment',
  templateUrl: './admin-view-investment.component.html',
  styleUrls: ['./admin-view-investment.component.css'],
})
export class AdminViewInvestmentComponent implements OnInit, AfterViewInit {
  investments: Investment[] = [];
  filteredInvestments: Investment[] = [];
  categories: string[] = [];
  searchTerm: string = '';
  selectedCategory: string = '';
  showDeletePopup = false;
  investmentToDelete: number | null = null;
 
  // Chart Instances
  // chartInstanceBar: Chart | null = null;
  // chartInstancePie: Chart | null = null;
 
  // Inquiry Data for Bar Chart
  inquiriesData: { investmentId: number, inquiries: number }[] = [
    { investmentId: 1, inquiries: 10 },
    { investmentId: 2, inquiries: 5 },
    { investmentId: 3, inquiries: 20 },
  ];
 
  @ViewChild('inquiriesBarChart', { static: false }) inquiriesBarChart!: ElementRef;
  @ViewChild('investmentPieChart', { static: false }) investmentPieChart!: ElementRef;
 
  constructor(private investmentService: InvestmentService, private router: Router) {}
 
  ngOnInit(): void {
    this.loadInvestments();
  }
 
  ngAfterViewInit(): void {
    setTimeout(() => {
      if (this.inquiriesBarChart?.nativeElement) {
        this.createBarChart();
      }
      if (this.investmentPieChart?.nativeElement) {
        this.createPieChart();
      }
    }, 500);
  }
 
  loadInvestments(): void {
    this.investmentService.getAllInvestments().subscribe((data: Investment[]) => {
      this.investments = data;
      this.filteredInvestments = data;
      // this.categories = [...new Set(this.investments.map(data => data.name))];
     // this.createBarChart();
      this.createPieChart();
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
 
 
getCharacter(index) {
    return this.hexCharacters[index]
}
 
generateJustOneColor(){
 
    let hexColorRep = "#"
 
    for (let position = 0; position < 6; position++){
        hexColorRep += this.getCharacter( position )
    }
 
    return hexColorRep
 
}
 
 
  // Bar Chart for Investment Inquiries
  createBarChart(): void {
    // if (this.chartInstanceBar) {
    //   this.chartInstanceBar.destroy();
    // }
 
    // const chartLabels = this.inquiriesData.map(data => `Investment ${data.investmentId}`);
    // const chartData = this.inquiriesData.map(data => data.inquiries);
 
   
   
    // this.chartInstanceBar = new Chart(this.inquiriesBarChart.nativeElement, {
    //   type: 'bar',
    //   data: {
    //     labels: chartLabels,
    //     datasets: [{
    //       label: 'Number of Inquiries',
    //       data: chartData,
    //       //backgroundColor: ['#28a745', '#dc3545', '#007bff'],
    //       backgroundColor: [ this.generateJustOneColor()+'',this.generateJustOneColor()+'',this.generateJustOneColor()+'' ]
    //     }]
    //   },
    //   options: {
    //     responsive: true,
    //     plugins: {
    //       legend: { position: 'top' }
    //     },
    //     scales: {
    //       y: { beginAtZero: true }
    //     }
    //   }
    // });
  }
 
  // Pie Chart for Investment Distribution
  createPieChart(): void {
    // const dynamicColors = this.generateRandomColor(this.categories.length);
    // console.log(dynamicColors)
 
    // if (this.chartInstancePie) {
    //   this.chartInstancePie.destroy();
    // }
 
    // const categoryData = this.categories.map(category =>
    //   this.investments.filter(investment => investment.name === category).length
    // );
 
    // this.chartInstancePie = new Chart(this.investmentPieChart.nativeElement, {
    //   type: 'pie',
    //   data: {
    //     labels: this.categories,
    //     datasets: [{
    //       data: categoryData,
    //       backgroundColor: dynamicColors
    //       //backgroundColor: ['#28a745', '#dc3545', '#007bff'],
    //     }]
    //   },
    //   options: {
    //     responsive: true,
    //     plugins: {
    //       legend: { position: 'bottom' }
    //     }
    //   }
    // });
  }
 
  generateRandomColor(count: number): string[] {
    return Array.from({ length: count }, () =>
      `#${Math.floor(Math.random() * 16777215).toString(16)}` // Generates a random HEX color
    );
  }
 
}