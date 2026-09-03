import { Component, OnInit, ChangeDetectorRef } from '@angular/core';
import { CommonModule } from '@angular/common';
import { RouterLink } from '@angular/router';
import { LearningService } from './learning.service';

@Component({
  selector: 'app-learning',
  standalone: true,
  imports: [CommonModule, RouterLink],
  templateUrl: './learning.html',
  styleUrl: './learning.scss'
})
export class Learning implements OnInit {

  courses: any[] = [];

  constructor(
    private learningService: LearningService,
    private cdr: ChangeDetectorRef
  ) {}

  ngOnInit(): void {
    this.loadCourses();
  }

  loadCourses(): void {
    this.learningService.getCourses()
      .subscribe({
        next: (data: any) => {
          this.courses = data;
          this.cdr.detectChanges();

          console.log('Courses:', data);
        },
        error: (error) => {
          console.error('Failed to load courses:', error);
        }
      });
  }
}
