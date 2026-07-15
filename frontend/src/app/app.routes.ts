import { Routes } from '@angular/router';

export const routes: Routes = [
  { path: '', redirectTo: 'dashboard', pathMatch: 'full' },
  { 
    path: 'dashboard', 
    loadComponent: () => import('./features/dashboard/dashboard').then(m => m.Dashboard) 
  },
  { 
    path: 'employee', 
    loadComponent: () => import('./features/employee/employee').then(m => m.EmployeeFeature) 
  },
  { 
    path: 'skill', 
    loadComponent: () => import('./features/skill/skill').then(m => m.SkillFeature) 
  },
  { 
    path: 'assessment', 
    loadComponent: () => import('./features/assessment/assessment').then(m => m.AssessmentFeature) 
  },
  { 
    path: 'competency', 
    loadComponent: () => import('./features/competency/competency').then(m => m.CompetencyFeature) 
  },
  { 
    path: 'certification', 
    loadComponent: () => import('./features/certification/certification').then(m => m.CertificationFeature) 
  },
  { path: '**', redirectTo: 'dashboard' }
];
