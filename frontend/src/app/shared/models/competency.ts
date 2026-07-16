export type CompetencyCategory = 'TECHNICAL' | 'DOMAIN' | 'SOFT';

export interface Competency {
  competencyId: string; // UUID
  name: string;
  category: CompetencyCategory;
  description: string;
  maxLevel: number;
}

export interface CompetencyRequestDTO {
  name: string;
  category: CompetencyCategory;
  description: string;
  maxLevel: number;
}

export interface CompetencyResponseDTO extends Competency {}

export interface CompetencyFramework {
  frameworkId?: string; // UUID
  role: string;
  competency: Competency;
  requiredLevel: number;
}

export interface CompetencyFrameworkRequestDTO {
  role: string;
  competencyId: string;
  requiredLevel: number;
}

export interface CompetencyFrameworkResponseDTO {
  frameworkId?: string; // UUID
  role: string;
  competencyId: string;
  requiredLevel: number;
}

export interface EmployeeCompetency {
  employeeCompetencyId?: string; // UUID
  employeeId: string; // UUID
  competency: Competency;
  currentLevel: number;
}

export interface EmployeeCompetencyRequestDTO {
  employeeId: string;
  competencyId: string;
  currentLevel: number;
}

export interface EmployeeCompetencyResponseDTO {
  employeeCompetencyId?: string; // UUID
  employeeId: string; // UUID
  competencyId: string;
  currentLevel: number;
}

export interface GapResult {
  competencyName: string;
  requiredLevel: number;
  currentLevel: number;
  gap: number;
}
