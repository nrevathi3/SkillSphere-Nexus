import { Component, OnInit, ChangeDetectorRef } from '@angular/core';
import { CommonModule } from '@angular/common';
import { ActivatedRoute } from '@angular/router';
import { FormsModule } from '@angular/forms';

import { LearningService } from '../learning/learning.service';

@Component({
  selector: 'app-course-details',
  standalone: true,
  imports: [CommonModule, FormsModule],
  templateUrl: './course-details.html',
  styleUrl: './course-details.scss'
})
export class CourseDetails implements OnInit {

  // =====================================================
  // COURSE DATA
  // =====================================================

  course: any = null;

  enrollment: any = null;

  certificate: any = null;

  contents: any[] = [];


  // =====================================================
  // LOADING STATES
  // =====================================================

  enrolling = false;

  enrollmentLoading = false;

  updatingProgress = false;

  submittingAssessment = false;


  // =====================================================
  // COURSE CONTENT
  // =====================================================

  completedContents: string[] = [];


  // =====================================================
  // ASSESSMENT
  // =====================================================

  assessmentScore: number | null = null;

  assessmentRetrying = false;

  readonly PASSING_SCORE = 50;


  // =====================================================
  // EMPLOYEE ID
  // =====================================================

  empId = '4fea4c16-b5f5-45c2-88c7-2324e45b82ee';


  // =====================================================
  // CONSTRUCTOR
  // =====================================================

  constructor(
    private route: ActivatedRoute,
    private learningService: LearningService,
    private cdr: ChangeDetectorRef
  ) {}


  // =====================================================
  // INITIALIZATION
  // =====================================================

  ngOnInit(): void {

    const courseId =
      this.route.snapshot.paramMap.get('courseId');


    if (courseId) {

      this.loadCourse(courseId);

    }

  }


  // =====================================================
  // LOAD COURSE
  // =====================================================

  loadCourse(courseId: string): void {

    this.learningService
      .getCourse(courseId)
      .subscribe({

        next: (data: any) => {

          this.course = data;

          console.log(
            'Course Details:',
            data
          );


          this.loadCourseContent(courseId);

          this.loadEnrollment(courseId);


          this.cdr.detectChanges();

        },


        error: (error: any) => {

          console.error(
            'Failed to load course:',
            error
          );

        }

      });

  }


  // =====================================================
  // LOAD COURSE CONTENT
  // =====================================================

  loadCourseContent(courseId: string): void {

    this.learningService
      .getCourseContent(courseId)
      .subscribe({

        next: (data: any) => {

          /*
           * Make sure the response is always treated
           * as an array.
           */

          this.contents =
            Array.isArray(data)
              ? data
              : [];


          console.log(
            'Course Content:',
            this.contents
          );


          /*
           * If the course content is already completed,
           * mark the available lessons as completed
           * in the UI.
           */

          if (
            this.enrollment &&
            this.enrollment.progress >= 100
          ) {

            this.completedContents =
              this.contents.map(
                (content: any) =>
                  content.contentId
              );

          }


          this.cdr.detectChanges();

        },


        error: (error: any) => {

          console.error(
            'Failed to load course content:',
            error
          );

        }

      });

  }


  // =====================================================
  // LOAD ENROLLMENT
  // =====================================================

  loadEnrollment(courseId: string): void {

    this.enrollmentLoading = true;


    this.learningService
      .getEnrollments(this.empId)
      .subscribe({

        /*
         * IMPORTANT:
         * Use any here because the service currently
         * returns Object and caused TS2769.
         */

        next: (data: any) => {

          const enrollments: any[] =
            Array.isArray(data)
              ? data
              : [];


          /*
           * Find the enrollment belonging to
           * the current course.
           */

          this.enrollment =
            enrollments.find(
              (item: any) =>
                item.courseId === courseId
            ) || null;


          console.log(
            'Current Enrollment:',
            this.enrollment
          );


          /*
           * If an enrollment exists and its content
           * progress is already 100%, show the lesson
           * as completed.
           */

          if (
            this.enrollment &&
            this.enrollment.progress >= 100 &&
            this.contents.length > 0
          ) {

            this.completedContents =
              this.contents.map(
                (content: any) =>
                  content.contentId
              );

          }


          /*
           * Make sure retry mode is not active when
           * loading the page.
           */

          this.assessmentRetrying = false;

          this.assessmentScore = null;


          this.enrollmentLoading = false;

          this.cdr.detectChanges();

        },


        error: (error: any) => {

          console.error(
            'Failed to load enrollment:',
            error
          );


          this.enrollment = null;

          this.enrollmentLoading = false;

          this.cdr.detectChanges();

        }

      });

  }


  // =====================================================
  // ASSESSMENT PASSED
  // =====================================================

  get assessmentPassed(): boolean {

    if (!this.enrollment) {

      return false;

    }


    if (
      this.enrollment.score === null ||
      this.enrollment.score === undefined
    ) {

      return false;

    }


    return Number(
      this.enrollment.score
    ) >= this.PASSING_SCORE;

  }


  // =====================================================
  // ASSESSMENT FAILED
  // =====================================================

  get assessmentFailed(): boolean {

    if (!this.enrollment) {

      return false;

    }


    if (
      this.enrollment.score === null ||
      this.enrollment.score === undefined
    ) {

      return false;

    }


    return (
      Number(this.enrollment.score) <
      this.PASSING_SCORE
    ) && !this.assessmentRetrying;

  }


  // =====================================================
  // UPDATE COURSE PROGRESS
  // =====================================================

  updateCourseProgress(progress: number): void {

    if (!this.enrollment) {

      console.error(
        'No enrollment found'
      );

      return;

    }


    this.learningService
      .updateProgress(
        this.enrollment.enrollmentId,
        progress
      )
      .subscribe({

        next: (data: any) => {

          this.enrollment = data;


          console.log(
            'Progress Updated:',
            data
          );


          this.cdr.detectChanges();

        },


        error: (error: any) => {

          console.error(
            'Failed to update progress:',
            error
          );

        }

      });

  }


  // =====================================================
  // MARK CONTENT COMPLETE
  // =====================================================

  markContentComplete(
    contentId: string
  ): void {

    if (!this.enrollment) {

      console.error(
        'No enrollment found'
      );

      return;

    }


    /*
     * Don't complete the same lesson twice.
     */

    if (
      this.completedContents.includes(
        contentId
      )
    ) {

      return;

    }


    /*
     * Add lesson temporarily.
     */

    this.completedContents.push(
      contentId
    );


    const totalContents =
      this.contents.length;


    const completedCount =
      this.completedContents.length;


    /*
     * Calculate actual content progress.
     */

    const progress =
      totalContents > 0
        ? Math.round(
          (completedCount /
            totalContents) *
          100
        )
        : 0;


    this.updatingProgress = true;


    this.learningService
      .updateProgress(
        this.enrollment.enrollmentId,
        progress
      )
      .subscribe({

        next: (data: any) => {

          this.enrollment = data;


          console.log(
            'Content completed. Progress:',
            progress
          );


          this.updatingProgress = false;


          this.cdr.detectChanges();

        },


        error: (error: any) => {

          console.error(
            'Failed to update progress:',
            error
          );


          /*
           * Remove lesson if backend update failed.
           */

          this.completedContents =
            this.completedContents.filter(
              id => id !== contentId
            );


          this.updatingProgress = false;


          this.cdr.detectChanges();

        }

      });

  }


  // =====================================================
  // TRY ASSESSMENT AGAIN
  // =====================================================

  tryAssessmentAgain(): void {

    console.log(
      'Retrying assessment...'
    );


    /*
     * Show the assessment input.
     */

    this.assessmentRetrying = true;


    /*
     * Clear the previous input.
     */

    this.assessmentScore = null;


    this.cdr.detectChanges();

  }


  // =====================================================
  // SUBMIT ASSESSMENT
  // =====================================================

  submitAssessment(): void {

    if (!this.enrollment) {

      console.error(
        'No enrollment found'
      );

      return;

    }


    /*
     * Validate score.
     */

    if (
      this.assessmentScore === null ||
      this.assessmentScore < 0 ||
      this.assessmentScore > 100
    ) {

      alert(
        'Please enter a valid score between 0 and 100.'
      );

      return;

    }


    this.submittingAssessment = true;


    this.learningService
      .submitAssessment(
        this.enrollment.enrollmentId,
        this.assessmentScore
      )
      .subscribe({

        next: (data: any) => {

          console.log(
            'Assessment submitted:',
            data
          );


          this.enrollment = data;


          /*
           * Exit retry mode.
           */

          this.assessmentRetrying = false;


          /*
           * Clear input.
           */

          this.assessmentScore = null;


          this.submittingAssessment = false;


          /*
           * IMPORTANT:
           *
           * If the backend previously marked the
           * enrollment completed when progress became
           * 100%, don't allow the UI to treat it as
           * finally completed until the user explicitly
           * clicks "Complete Course".
           */

          if (
            this.enrollment &&
            Number(this.enrollment.score) >=
            this.PASSING_SCORE
          ) {

            this.enrollment.completed = false;

          }


          this.cdr.detectChanges();

        },


        error: (error: any) => {

          console.error(
            'Failed to submit assessment:',
            error
          );


          this.submittingAssessment = false;


          this.cdr.detectChanges();

        }

      });

  }


  // =====================================================
  // COMPLETE COURSE
  // =====================================================

  completeCourse(): void {

    if (!this.enrollment) {

      console.error(
        'No enrollment found'
      );

      return;

    }


    /*
     * Don't allow course completion when
     * assessment has not been passed.
     */

    if (!this.assessmentPassed) {

      console.warn(
        'Assessment must be passed before completing the course.'
      );

      return;

    }


    /*
     * Don't allow completion before content
     * is finished.
     */

    if (
      this.enrollment.progress < 100
    ) {

      console.warn(
        'Course content must be completed first.'
      );

      return;

    }


    this.learningService
      .completeCourse(
        this.enrollment.enrollmentId
      )
      .subscribe({

        next: (data: any) => {

          this.enrollment = data;


          console.log(
            'Course Completed:',
            data
          );


          this.cdr.detectChanges();

        },


        error: (error: any) => {

          console.error(
            'Failed to complete course:',
            error
          );

        }

      });

  }


  // =====================================================
  // GENERATE CERTIFICATE
  // =====================================================

  generateCertificate(): void {

    if (!this.enrollment) {

      console.error(
        'No enrollment found'
      );

      return;

    }


    /*
     * Certificate can only be generated
     * after successful course completion.
     */

    if (
      !this.enrollment.completed ||
      !this.assessmentPassed
    ) {

      console.warn(
        'Course must be completed and assessment passed first.'
      );

      return;

    }


    this.learningService
      .generateCertificate(
        this.enrollment.enrollmentId
      )
      .subscribe({

        next: (data: any) => {

          this.certificate = data;


          console.log(
            'Certificate Generated:',
            data
          );


          this.cdr.detectChanges();

        },


        error: (error: any) => {

          console.error(
            'Failed to generate certificate:',
            error
          );

        }

      });

  }


  // =====================================================
  // ENROLL COURSE
  // =====================================================

  enrollCourse(): void {

    if (!this.course) {

      return;

    }


    this.enrolling = true;


    this.learningService
      .enroll(
        this.empId,
        this.course.courseId
      )
      .subscribe({

        next: (data: any) => {

          this.enrollment = data;


          this.enrolling = false;


          this.assessmentRetrying = false;

          this.assessmentScore = null;


          console.log(
            'Enrollment successful:',
            data
          );


          this.cdr.detectChanges();

        },


        error: (error: any) => {

          this.enrolling = false;


          console.error(
            'Enrollment failed:',
            error
          );


          this.cdr.detectChanges();

        }

      });

  }

}
