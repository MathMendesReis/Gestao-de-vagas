```mermaid
gclassDiagram
  class CandidateEntity {
    + id: UUID
    + name: String
    + username: String
    + email: String
    + password: String
    + description: String
    + curriculum: String
    + createdAt: LocalDateTime
  }

  class JobEntity {
    + id: UUID
    + description: String
    + benefits: String
    + level: String
    + companyEntity: CompanyEntity
    + companyId: UUID
    + createdAt: LocalDateTime
  }

  class CompanyEntity {
    + id: UUID
    + username: String
    + email: String
    + password: String
    + website: String
    + name: String
    + description: String
    + createdAt: LocalDateTime
  }

  CandidateEntity --|> JobEntity : Applies
  JobEntity --o CompanyEntity : Belongs to

```