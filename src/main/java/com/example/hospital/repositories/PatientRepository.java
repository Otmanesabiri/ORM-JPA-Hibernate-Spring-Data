package com.example.hospital.repositories;

import com.example.hospital.entities.Patient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.Date;
import java.util.List;

@RepositoryRestResource
public interface PatientRepository extends JpaRepository<Patient, Long> {
    // Rechercher des patients par nom
    List<Patient> findByNomContains(String nom);
    
    // Rechercher des patients malades
    List<Patient> findByMalade(boolean malade);
    
    // Rechercher des patients par score
    List<Patient> findByScoreGreaterThan(int score);
    
    // Rechercher des patients par date de naissance
    List<Patient> findByDateNaissanceBetween(Date dateDebut, Date dateFin);
    
    // Recherche combinée
    List<Patient> findByMaladeAndScoreGreaterThan(boolean malade, int score);
}
