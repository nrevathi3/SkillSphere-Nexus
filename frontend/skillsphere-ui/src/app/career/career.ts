import { CommonModule } from '@angular/common';
import { ChangeDetectorRef, Component, OnInit } from '@angular/core';
import { CareerService } from '../services/career';

@Component({
  selector: 'app-career',
  standalone: true,
  imports: [CommonModule],
  templateUrl: './career.html',
  styleUrl: './career.scss'
})
export class Career implements OnInit {

  plans: any[] = [];

  constructor(
    private careerService: CareerService,
    private cdr: ChangeDetectorRef
  ) {}

  ngOnInit(): void {
    this.loadPlans();
  }

  loadPlans(): void {
    this.careerService.getCareerPlans().subscribe({
      next: (data) => {
        console.log('CAREER PLANS RECEIVED:', data);
        console.log('IS ARRAY:', Array.isArray(data));

        this.plans = Array.isArray(data) ? data : [];

        console.log('PLANS AFTER ASSIGNMENT:', this.plans);

        this.cdr.detectChanges();
      },
      error: (err) => {
        console.error('Failed to load career plans', err);
      }
    });
  }
}
