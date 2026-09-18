import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';

@Injectable({
  providedIn: 'root'
})
export class CertificationService {

  private baseUrl =
    'http://localhost:8090/api/certifications';

  constructor(private http: HttpClient) {}

  getEmployeeCertifications(employeeId: string) {
    return this.http.get(
      `${this.baseUrl}/employee/${employeeId}`
    );
  }

  getById(certificationId: string) {
    return this.http.get(
      `${this.baseUrl}/${certificationId}`
    );
  }

  requestRenewal(
    certificationId: string,
    requestedBy: string
  ) {
    return this.http.post(
      `${this.baseUrl}/renewals/${certificationId}`,
      null,
      {
        params: { requestedBy }
      }
    );
  }
  getRenewal(renewalId: string) {
    return this.http.get(
      `${this.baseUrl}/renewals/${renewalId}`
    );
  }

  approveRenewal(
    renewalId: string,
    newExpiry: string,
    approvedBy: string
  ) {
    return this.http.put(
      `${this.baseUrl}/renewals/${renewalId}/approve`,
      null,
      {
        params: {
          newExpiry,
          approvedBy
        }
      }
    );
  }

  getCompliance(employeeId: string) {
    return this.http.get(
      `http://localhost:8090/api/compliance/employee/${employeeId}`
    );
  }

  getReport() {
    return this.http.get(
      `${this.baseUrl}/report`
    );
  }

  getAudit(certificationId: string) {
    return this.http.get(
      `${this.baseUrl}/audit/${certificationId}`
    );
  }
  getExpiring() {
    return this.http.get(
      `${this.baseUrl}/expiring`
    );
  }
}
