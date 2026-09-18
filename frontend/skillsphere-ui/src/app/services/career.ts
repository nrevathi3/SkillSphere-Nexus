import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';

@Injectable({
  providedIn: 'root'
})
export class CareerService {

  private baseUrl = 'http://localhost:8090/api/career';

  constructor(private http: HttpClient) {}

  getCareerPlans() {
    return this.http.get<any[]>(`${this.baseUrl}/plans`);
  }

  createCareerPlan(data: any) {
    return this.http.post(`${this.baseUrl}/plans`, data);
  }

  updateCareerPlan(id: string, data: any) {
    return this.http.put(`${this.baseUrl}/plans/${id}`, data);
  }

  getJobs() {
    return this.http.get<any[]>(`${this.baseUrl}/jobs/active`);
  }

  createJob(data: any) {
    return this.http.post(`${this.baseUrl}/jobs`, data);
  }
  submitApplication(data: any) {
    return this.http.post(
      `${this.baseUrl}/applications`,
      data
    );
  }

  getAnalytics() {
    return this.http.get<any>(`${this.baseUrl}/analytics`);
  }
}
