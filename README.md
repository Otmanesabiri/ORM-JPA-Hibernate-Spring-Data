# Application de Gestion Hospitalière avec Spring Data JPA

Application de démonstration pour la gestion des patients, médecins, rendez-vous et consultations dans un environnement hospitalier, ainsi que la gestion des utilisateurs et des rôles.

## Fonctionnalités

- **Gestion des patients** : Ajouter, consulter, mettre à jour et supprimer des patients
- **Gestion des médecins** : Enregistrer et consulter les médecins par spécialité
- **Gestion des rendez-vous** : Programmer des rendez-vous entre patients et médecins avec différents statuts
- **Gestion des consultations** : Enregistrer des rapports de consultation liés aux rendez-vous
- **Gestion des utilisateurs et rôles** : Système d'authentification avec des rôles utilisateurs

## Structure des entités

### Relations entre les entités

- **Patient - RendezVous** : Relation OneToMany (Un patient peut avoir plusieurs rendez-vous)
- **Medecin - RendezVous** : Relation OneToMany (Un médecin peut avoir plusieurs rendez-vous)
- **RendezVous - Consultation** : Relation OneToOne (Un rendez-vous peut avoir une consultation)
- **User - Role** : Relation ManyToMany (Un utilisateur peut avoir plusieurs rôles, un rôle peut être attribué à plusieurs utilisateurs)

## Technologies utilisées

- **Framework** : Spring Boot 3.1.5
- **Persistence** : Spring Data JPA, Hibernate
- **Base de données** : H2 (en mémoire), MySQL (option de migration)
- **API** : Spring Data REST
- **Autres** : Lombok (réduction de code)

## Prérequis

- JDK 17 ou supérieur
- Maven 3.6 ou supérieur
- IDE (IntelliJ IDEA, Eclipse, VS Code...)
- MySQL (optionnel pour la migration)

## Configuration et exécution

### Cloner le projet

```bash
git clone https://github.com/yourusername/Activit-Pratique-N-2---ORM-JPA-Hibernate-Spring-Data.git
cd Activit-Pratique-N-2---ORM-JPA-Hibernate-Spring-Data
```

### Exécuter l'application avec Maven

```bash
mvn spring-boot:run
```

L'application démarre par défaut sur le port 8082 (configurable dans `application.properties`).

### Accéder à l'application

- Application : http://localhost:8082
- Console H2 : http://localhost:8082/h2-console
  - JDBC URL: `jdbc:h2:mem:hospital-db`
  - Username: `sa`
  - Password: (laisser vide)

## Migration de H2 vers MySQL

Pour utiliser MySQL au lieu de H2 :

1. Assurez-vous que MySQL est installé et en cours d'exécution
2. Dans `application.properties`, commentez la configuration H2 et décommentez la configuration MySQL :

```properties
# Configuration H2 Database
#spring.datasource.url=jdbc:h2:mem:hospital-db
#spring.datasource.driverClassName=org.h2.Driver
#spring.datasource.username=sa
#spring.datasource.password=
#spring.jpa.database-platform=org.hibernate.dialect.H2Dialect
#spring.h2.console.enabled=true
#spring.jpa.hibernate.ddl-auto=create
#spring.jpa.show-sql=true

# Configuration MySQL Database
spring.datasource.url=jdbc:mysql://localhost:3306/hospital-db?createDatabaseIfNotExist=true
spring.datasource.username=root
spring.datasource.password=
spring.jpa.hibernate.ddl-auto=update
spring.jpa.properties.hibernate.dialect=org.hibernate.dialect.MySQL8Dialect
spring.jpa.show-sql=true
```

## API REST

L'application expose automatiquement des endpoints REST pour toutes les entités grâce à Spring Data REST :

- Patients : `http://localhost:8082/patients`
- Médecins : `http://localhost:8082/medecins`
- Rendez-vous : `http://localhost:8082/rendezVous`
- Consultations : `http://localhost:8082/consultations`
- Utilisateurs : `http://localhost:8082/users`
- Rôles : `http://localhost:8082/roles`

## Modèle de données

### Patient
- id (Long)
- nom (String)
- dateNaissance (Date)
- malade (boolean)
- score (int)
- rendezVous (Collection)

### Medecin
- id (Long)
- nom (String)
- email (String)
- specialite (String)
- rendezVous (Collection)

### RendezVous
- id (Long)
- date (Date)
- statut (Enum: EN_COURS, ANNULE, HONORE)
- patient (Patient)
- medecin (Medecin)
- consultation (Consultation)

### Consultation
- id (Long)
- dateConsultation (Date)
- rapport (String)
- rendezVous (RendezVous)

### User
- userId (String)
- username (String)
- password (String)
- roles (Collection)

### Role
- id (Long)
- name (String)
- users (Collection)

## Auteur
- OTMANE SABIRI
