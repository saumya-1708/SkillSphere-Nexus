export type CompetencyCategory = 'TECHNICAL' | 'DOMAIN' | 'SOFT';

export interface Competency {
  competencyId: string; // UUID
  name: string;
  category: CompetencyCategory;
  description: string;
  maxLevel: number;
}

export interface CompetencyFramework {
  frameworkId?: string; // UUID
  role: string;
  competency: Competency;
  requiredLevel: number;
}

export interface EmployeeCompetency {
  employeeCompetencyId?: string; // UUID
  employeeId: string; // UUID
  competency: Competency;
  currentLevel: number;
}

export interface GapResult {
  competencyName: string;
  requiredLevel: number;
  currentLevel: number;
  gap: number;
}
