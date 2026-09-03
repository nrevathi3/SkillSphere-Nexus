import { Component, OnInit, ChangeDetectorRef } from '@angular/core';
import { CommonModule } from '@angular/common';
import { RouterLink } from '@angular/router';
import { LearningService } from '../learning/learning.service';

@Component({
  selector: 'app-learning-path',
  standalone: true,
  imports: [CommonModule, RouterLink],
  templateUrl: './learning-path.html',
  styleUrl: './learning-path.scss'
})
export class LearningPath implements OnInit {

  learningPaths: any[] = [];

  pathCourses: { [key: string]: any[] } = {};

  loading = true;

  // Temporary employee ID for testing
  // This is the employee ID from your current enrollment.
  empId = '4fea4c16-b5f5-45c2-88c7-2324e45b82ee';

  constructor(
    private learningService: LearningService,
    private cdr: ChangeDetectorRef
  ) {}

  ngOnInit(): void {
    this.loadLearningPaths();
  }


  // =====================================================
  // LOAD LEARNING PATHS
  // =====================================================

  loadLearningPaths(): void {

    this.loading = true;

    this.learningService.getLearningPaths()
      .subscribe({

        next: (data: any) => {

          this.learningPaths = data;

          console.log(
            'Learning Paths:',
            this.learningPaths
          );

          this.loadCoursesForPaths();

        },

        error: (error) => {

          console.error(
            'Failed to load learning paths:',
            error
          );

          this.loading = false;

          this.cdr.detectChanges();
        }

      });

  }


  // =====================================================
  // LOAD COURSES + PROGRESS FOR EACH PATH
  // =====================================================

  loadCoursesForPaths(): void {

    if (this.learningPaths.length === 0) {

      this.loading = false;

      this.cdr.detectChanges();

      return;
    }


    let completedRequests = 0;


    this.learningPaths.forEach((path: any) => {

      // -------------------------------------------------
      // LOAD COURSES
      // -------------------------------------------------

      this.learningService
        .getLearningPathCourses(path.pathId)
        .subscribe({

          next: (data: any) => {

            this.pathCourses[path.pathId] = data;

            console.log(
              'Courses for path:',
              path.name,
              data
            );

            this.loadProgressForPath(path);

            completedRequests++;

            if (
              completedRequests ===
              this.learningPaths.length
            ) {

              this.loading = false;

              this.cdr.detectChanges();

            }

          },

          error: (error) => {

            console.error(
              'Failed to load courses for path:',
              path.pathId,
              error
            );

            this.pathCourses[path.pathId] = [];

            // Even if courses fail, try loading progress
            this.loadProgressForPath(path);

            completedRequests++;

            if (
              completedRequests ===
              this.learningPaths.length
            ) {

              this.loading = false;

              this.cdr.detectChanges();

            }

          }

        });

    });

  }


  // =====================================================
  // LOAD ACTUAL LEARNING PATH PROGRESS
  // =====================================================

  loadProgressForPath(path: any): void {

    this.learningService
      .getLearningPathProgress(
        path.pathId,
        this.empId
      )
      .subscribe({

        next: (progress: any) => {

          console.log(
            'Progress for path:',
            path.name,
            progress
          );

          /*
           * Backend may return:
           *
           * 100
           *
           * or
           *
           * { progress: 100 }
           */

          if (typeof progress === 'number') {

            path.progress = progress;

          } else if (
            progress &&
            progress.progress !== undefined
          ) {

            path.progress = progress.progress;

          } else {

            path.progress = 0;

          }

          this.cdr.detectChanges();

        },

        error: (error) => {

          console.error(
            'Failed to load progress for path:',
            path.pathId,
            error
          );

          path.progress = 0;

          this.cdr.detectChanges();

        }

      });

  }


  // =====================================================
  // GET COURSES FOR A PATH
  // =====================================================

  getCoursesForPath(pathId: string): any[] {

    return this.pathCourses[pathId] || [];

  }


  // =====================================================
  // GET COURSE COUNT
  // =====================================================

  getCourseCount(pathId: string): number {

    return this.getCoursesForPath(pathId).length;

  }

}
