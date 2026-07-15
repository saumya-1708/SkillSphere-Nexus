import { Component, inject, signal, OnInit } from '@angular/core';
import { forkJoin } from 'rxjs';
import { EmployeeService } from '../../core/services/employee.service';
import { SkillService } from '../../core/services/skill.service';
import { EmployeeSkillService } from '../../core/services/employee-skill.service';
import { AssessmentService } from '../../core/services/assessment.service';
import { CertificationService } from '../../core/services/certification.service';
import { CompetencyService } from '../../core/services/competency.service';
import { KeyValuePipe } from '@angular/common';

@Component({
  selector: 'app-dashboard',
  imports: [KeyValuePipe],
  templateUrl: './dashboard.html',
  styleUrl: './dashboard.css'
})
export class Dashboard implements OnInit {
  private readonly empService = inject(EmployeeService);
  private readonly skillService = inject(SkillService);
  private readonly empSkillService = inject(EmployeeSkillService);
  private readonly assessmentService = inject(AssessmentService);
  private readonly certService = inject(CertificationService);
  private readonly competencyService = inject(CompetencyService);

  // Dashboard Stats
  readonly employeeCount = signal(0);
  readonly skillCount = signal(0);
  readonly assessmentCount = signal(0);
  readonly activeCertCount = signal(0);
  readonly avgAssessmentScore = signal(0);
  readonly competencyCount = signal(0);

  // Chart data signals
  readonly skillProficiencies = signal<{ [level: string]: number }>({});
  readonly skillCategories = signal<{ [cat: string]: number }>({});
  readonly statusLoading = signal(true);

  ngOnInit(): void {
    this.fetchDashboardData();
  }

  fetchDashboardData(): void {
    this.statusLoading.set(true);
    
    forkJoin({
      employees: this.empService.getAll(),
      skills: this.skillService.getAll(),
      empSkills: this.empSkillService.getAll(),
      assessments: this.assessmentService.getAll(),
      certs: this.certService.getAll(),
      competencies: this.competencyService.getAll()
    }).subscribe({
      next: (data) => {
        this.employeeCount.set(data.employees.length);
        this.skillCount.set(data.skills.length);
        this.assessmentCount.set(data.assessments.length);
        this.competencyCount.set(data.competencies.length);

        // Calculate average assessment score
        if (data.assessments.length > 0) {
          const total = data.assessments.reduce((sum, current) => sum + current.score, 0);
          this.avgAssessmentScore.set(Math.round(total / data.assessments.length));
        }

        // Calculate active certifications
        const activeCerts = data.certs.filter(c => c.status === 'Valid').length;
        this.activeCertCount.set(activeCerts);

        // Group skill proficiencies (1-5)
        const levels: { [key: string]: number } = { '1': 0, '2': 0, '3': 0, '4': 0, '5': 0 };
        data.empSkills.forEach(es => {
          const lvl = es.proficiencyLevel.toString();
          if (levels[lvl] !== undefined) {
            levels[lvl]++;
          }
        });
        this.skillProficiencies.set(levels);

        // Group skill categories
        const cats: { [key: string]: number } = {};
        data.skills.forEach(s => {
          const cat = s.category || 'TECHNICAL';
          cats[cat] = (cats[cat] || 0) + 1;
        });
        this.skillCategories.set(cats);

        this.statusLoading.set(false);
      },
      error: (err) => {
        console.error('Error fetching dashboard stats', err);
        this.statusLoading.set(false);
      }
    });
  }
}
