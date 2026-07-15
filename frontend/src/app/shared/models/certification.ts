export interface Certificate {
  certid: string; // UUID
  empid: string; // UUID
  name: string;
  issuingOrganization: string;
  issueDate: string; // ISO Date String
  expiry: string; // ISO Date String
  status: string; // Valid / Expired
}
