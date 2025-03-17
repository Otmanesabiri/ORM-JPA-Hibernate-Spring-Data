package com.example.hospital.repositories;

import com.example.hospital.entities.Consultation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource
public interface ConsultationRepository extends JpaRepository<Consultation, Long> {
}
