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
  }
];
