import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable, of, throwError } from 'rxjs';
import { catchError, map } from 'rxjs/operators';
import { Competency, CompetencyFramework, EmployeeCompetency, GapResult } from '../../shared/models/competency';
import { environment } from '../../../environments/environment';

@Injectable({
  providedIn: 'root'
})
export class CompetencyService {
  private readonly baseUrl = `${environment.apiUrl}/competencies`;

  // Realistic mock databases
  private mockCompetencies: Competency[] = [
    { competencyId: 'c1-uuid', name: 'Software Architecture', category: 'TECHNICAL', description: 'System modeling, design patterns, microservices architectures', maxLevel: 5 },
    { competencyId: 'c2-uuid', name: 'Problem Solving', category: 'SOFT', description: 'Analytical reasoning, algorithms complexity, logical thinking', maxLevel: 5 },
    { competencyId: 'c3-uuid', name: 'Cloud Engineering', category: 'TECHNICAL', description: 'AWS, Azure, Docker, Kubernetes, infrastructure as code', maxLevel: 5 },
    { competencyId: 'c4-uuid', name: 'Investment Banking', category: 'DOMAIN', description: 'Mergers and acquisitions, asset management, capital markets knowledge', maxLevel: 5 },
    { competencyId: 'c5-uuid', name: 'Technical Leadership', category: 'SOFT', description: 'Mentoring, architecture governance, conflict resolution', maxLevel: 5 }
  ];

  private mockFrameworks: CompetencyFramework[] = [
    { frameworkId: 'f1-uuid', role: 'Senior Java Developer', competency: this.mockCompetencies[0], requiredLevel: 4 },
    { frameworkId: 'f2-uuid', role: 'Senior Java Developer', competency: this.mockCompetencies[1], requiredLevel: 4 },
    { frameworkId: 'f3-uuid', role: 'Senior Java Developer', competency: this.mockCompetencies[2], requiredLevel: 3 },
    { frameworkId: 'f4-uuid', role: 'Principal Architect', competency: this.mockCompetencies[0], requiredLevel: 5 },
    { frameworkId: 'f5-uuid', role: 'Principal Architect', competency: this.mockCompetencies[4], requiredLevel: 5 }
  ];

  private mockEmployeeCompetencies: EmployeeCompetency[] = [
    { employeeCompetencyId: 'ec1-uuid', employeeId: '101', competency: this.mockCompetencies[0], currentLevel: 3 },
    { employeeCompetencyId: 'ec2-uuid', employeeId: '101', competency: this.mockCompetencies[1], currentLevel: 4 },
    { employeeCompetencyId: 'ec3-uuid', employeeId: '101', competency: this.mockCompetencies[2], currentLevel: 2 },
    { employeeCompetencyId: 'ec4-uuid', employeeId: '102', competency: this.mockCompetencies[0], currentLevel: 5 }
  ];

  constructor(private http: HttpClient) {}

  // --- Competency Catalog ---
  getAll(): Observable<Competency[]> {
    if (environment.useMock) {
      return of([...this.mockCompetencies]);
    }
    return this.http.get<Competency[]>(this.baseUrl).pipe(
      catchError(this.handleError)
    );
  }

  getById(id: string): Observable<Competency> {
    if (environment.useMock) {
      const comp = this.mockCompetencies.find(c => c.competencyId === id);
      return comp ? of({ ...comp }) : throwError(() => new Error('Competency not found'));
    }
    return this.http.get<Competency>(`${this.baseUrl}/${id}`).pipe(
      catchError(this.handleError)
    );
  }

  create(competency: Competency): Observable<Competency> {
    if (environment.useMock) {
      const newId = competency.competencyId || 'c-' + Math.random().toString(36).substr(2, 9);
      const newComp = { ...competency, competencyId: newId };
      this.mockCompetencies.push(newComp);
      return of(newComp);
    }
    return this.http.post<Competency>(this.baseUrl, competency).pipe(
      catchError(this.handleError)
    );
  }

  update(id: string, competency: Competency): Observable<Competency> {
    if (environment.useMock) {
      const index = this.mockCompetencies.findIndex(c => c.competencyId === id);
      if (index === -1) {
        return throwError(() => new Error('Competency not found'));
      }
      this.mockCompetencies[index] = { ...competency, competencyId: id };
      return of(this.mockCompetencies[index]);
    }
    return this.http.put<Competency>(`${this.baseUrl}/${id}`, competency).pipe(
      catchError(this.handleError)
    );
  }

  delete(id: string): Observable<void> {
    if (environment.useMock) {
      const index = this.mockCompetencies.findIndex(c => c.competencyId === id);
      if (index === -1) {
        return throwError(() => new Error('Competency not found'));
      }
      this.mockCompetencies.splice(index, 1);
      return of(void 0);
    }
    return this.http.delete<void>(`${this.baseUrl}/${id}`).pipe(
      catchError(this.handleError)
    );
  }

  // --- Competency Framework ---
  defineFrameworkRequirement(framework: CompetencyFramework): Observable<CompetencyFramework> {
    if (environment.useMock) {
      const newId = 'f-' + Math.random().toString(36).substr(2, 9);
      const newFw = { ...framework, frameworkId: newId };
      this.mockFrameworks.push(newFw);
      return of(newFw);
    }
    return this.http.post<CompetencyFramework>(`${this.baseUrl}/frameworks`, framework).pipe(
      catchError(this.handleError)
    );
  }

  getFrameworkForRole(role: string): Observable<CompetencyFramework[]> {
    if (environment.useMock) {
      return of(this.mockFrameworks.filter(f => f.role.toLowerCase() === role.toLowerCase()));
    }
    return this.http.get<CompetencyFramework[]>(`${this.baseUrl}/frameworks/role/${role}`).pipe(
      catchError(this.handleError)
    );
  }

  // --- Employee Competency ---
  recordEmployeeLevel(employeeCompetency: EmployeeCompetency): Observable<EmployeeCompetency> {
    if (environment.useMock) {
      const newId = 'ec-' + Math.random().toString(36).substr(2, 9);
      const newEc = { ...employeeCompetency, employeeCompetencyId: newId };
      
      // Update existing or push
      const index = this.mockEmployeeCompetencies.findIndex(
        ec => ec.employeeId === employeeCompetency.employeeId && 
              ec.competency.competencyId === employeeCompetency.competency.competencyId
      );
      if (index !== -1) {
        this.mockEmployeeCompetencies[index].currentLevel = employeeCompetency.currentLevel;
        return of(this.mockEmployeeCompetencies[index]);
      }
      this.mockEmployeeCompetencies.push(newEc);
      return of(newEc);
    }
    return this.http.post<EmployeeCompetency>(`${this.baseUrl}/employee-levels`, employeeCompetency).pipe(
      catchError(this.handleError)
    );
  }

  // --- Gap Analysis ---
  analyzeGap(employeeId: string, role: string): Observable<GapResult[]> {
    if (environment.useMock) {
      // Find all framework requirements for this role
      const requirements = this.mockFrameworks.filter(f => f.role.toLowerCase() === role.toLowerCase());
      
      // Map to Gap Results
      const results: GapResult[] = requirements.map(req => {
        // Find if employee has a record for this competency
        const empLevel = this.mockEmployeeCompetencies.find(
          ec => ec.employeeId.toString() === employeeId.toString() && 
                ec.competency.competencyId === req.competency.competencyId
        );
        const current = empLevel ? empLevel.currentLevel : 0;
        const gap = Math.max(0, req.requiredLevel - current);
        return {
          competencyName: req.competency.name,
          requiredLevel: req.requiredLevel,
          currentLevel: current,
          gap: gap
        };
      });
      return of(results);
    }
    return this.http.get<GapResult[]>(`${this.baseUrl}/gap-analysis`, {
      params: { employeeId, role }
    }).pipe(
      catchError(this.handleError)
    );
  }

  private handleError(error: any) {
    console.error('API Error:', error);
    return throwError(() => new Error(error.message || 'Server error occurred'));
  }
}
