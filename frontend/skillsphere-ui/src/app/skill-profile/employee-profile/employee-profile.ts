import { Component, OnInit } from '@angular/core';
import { SkillProfile } from '../skill-profile';

@Component({
  selector: 'app-employee-profile',
  imports: [],
  templateUrl: './employee-profile.html',
  styleUrl: './employee-profile.scss',
})
export class EmployeeProfile implements OnInit {

  employeeProfile: any;

  // We'll replace this with the real employee ID from the database later.
  empId = '4fea4c16-b5f5-45c2-88c7-2324e45b82ee';

  constructor(private skillProfile: SkillProfile) {}

  ngOnInit(): void {
    this.skillProfile.getProfile(this.empId).subscribe({
      next: (data) => {
        this.employeeProfile = data;
        console.log('Employee Profile:', data);
      },
      error: (error) => {
        console.error('Failed to load employee profile:', error);
      }
    });
  }
}
