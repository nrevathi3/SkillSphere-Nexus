import { Routes } from '@angular/router';
import { EmployeeProfile } from './skill-profile/employee-profile/employee-profile';

export const routes: Routes = [

  {
    path: 'employee-profile',
    component: EmployeeProfile
  },

  {
    path: '',
    redirectTo: 'employee-profile',
    pathMatch: 'full'
  },

  {
    path: 'learning',
    loadComponent: () =>
      import('./learning/learning')
        .then(m => m.Learning)
  },

  {
    path: 'learning-path',
    loadComponent: () =>
      import('./learning-path/learning-path')
        .then(m => m.LearningPath)
  },

  {
    path: 'learning/course/:courseId',
    loadComponent: () =>
      import('./course-details/course-details')
        .then(m => m.CourseDetails)
  },
  {
    path: 'certification',
    loadComponent: () =>
      import('./certification/certification')
        .then(m => m.Certification)
  },
  {
    path: 'certification/expiring',
    loadComponent: () =>
      import('./certification/expiring-certifications/expiring-certifications')
        .then(m => m.ExpiringCertifications)
  }
];
