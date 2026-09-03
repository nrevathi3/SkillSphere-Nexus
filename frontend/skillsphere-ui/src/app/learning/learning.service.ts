import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';

@Injectable({
  providedIn: 'root',
})
export class LearningService {

  private baseUrl = 'http://localhost:8080/api';

  constructor(private http: HttpClient) {
  }


  // =====================================================
  // COURSES
  // =====================================================

  getCourses() {
    return this.http.get(`${this.baseUrl}/courses`);
  }


  getCourse(courseId: string) {
    return this.http.get(
      `${this.baseUrl}/courses/${courseId}`
    );
  }


  // =====================================================
  // ENROLLMENT
  // =====================================================

  enroll(
    empId: string,
    courseId: string
  ) {

    return this.http.post(
      `${this.baseUrl}/enrollments`,
      null,
      {
        params: {
          empId,
          courseId
        }
      }
    );

  }


  getEnrollments(empId: string) {

    return this.http.get(
      `${this.baseUrl}/enrollments/employee/${empId}`
    );

  }


  // =====================================================
  // PROGRESS
  // =====================================================

  updateProgress(
    enrollmentId: string,
    progress: number
  ) {

    return this.http.put(
      `${this.baseUrl}/progress/${enrollmentId}`,
      null,
      {
        params: {
          progress
        }
      }
    );

  }


  // =====================================================
  // ASSESSMENT
  // =====================================================

  submitAssessment(
    enrollmentId: string,
    score: number
  ) {

    return this.http.post(
      `${this.baseUrl}/progress/${enrollmentId}/assessment`,
      null,
      {
        params: {
          score
        }
      }
    );

  }


  // =====================================================
  // COMPLETE COURSE
  // =====================================================

  completeCourse(
    enrollmentId: string
  ) {

    return this.http.post(
      `${this.baseUrl}/progress/${enrollmentId}/complete`,
      null
    );

  }


  // =====================================================
  // LEARNING PATHS
  // =====================================================

  getLearningPaths() {

    return this.http.get(
      `${this.baseUrl}/learning-paths`
    );

  }


  getLearningPathCourses(
    pathId: string
  ) {

    return this.http.get(
      `${this.baseUrl}/learning-paths/${pathId}/courses`
    );

  }


  // =====================================================
  // COURSE CONTENT
  // =====================================================

  getCourseContent(
    courseId: string
  ) {

    return this.http.get(
      `${this.baseUrl}/course-content/course/${courseId}`
    );

  }


  // =====================================================
  // CERTIFICATE
  // =====================================================

  generateCertificate(
    enrollmentId: string
  ) {

    return this.http.post(
      `${this.baseUrl}/certificates/${enrollmentId}`,
      null
    );

  }


  getLearningPathProgress(pathId: string, empId: string) {
    return this.http.get(
      `${this.baseUrl}/learning-paths/${pathId}/progress/${empId}`
    );
  }
}
