package com.example.hospital;

import com.example.hospital.entities.*;
import com.example.hospital.repositories.ConsultationRepository;
import com.example.hospital.repositories.MedecinRepository;
import com.example.hospital.repositories.PatientRepository;
import com.example.hospital.repositories.RendezVousRepository;
import com.example.hospital.service.UserService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.Date;
import java.util.List;
import java.util.stream.Stream;

@SpringBootApplication
public class HospitalApplication {

    public static void main(String[] args) {
        SpringApplication.run(HospitalApplication.class, args);
    }

    @Bean
    CommandLineRunner start(
            PatientRepository patientRepository,
            MedecinRepository medecinRepository,
            RendezVousRepository rendezVousRepository,
            ConsultationRepository consultationRepository,
            UserService userService) {
        return args -> {
            // Test opérations sur Patient
            System.out.println("==== Ajout de patients ====");
            Stream.of("Mohamed", "Hassan", "Najat")
                  .forEach(name -> {
                      Patient patient = new Patient();
                      patient.setNom(name);
                      patient.setDateNaissance(new Date());
                      patient.setMalade(Math.random() > 0.5);
                      patient.setScore((int)(Math.random()*100));
                      patientRepository.save(patient);
                  });
            
            System.out.println("==== Consultation de tous les patients ====");
            patientRepository.findAll().forEach(p -> {
                System.out.println(p.getId() + " : " + p.getNom());
            });
            
            System.out.println("==== Consultation d'un patient ====");
            Patient patient = patientRepository.findById(1L).orElse(null);
            if (patient != null) {
                System.out.println(patient.getNom() + " - Malade: " + patient.isMalade());
            }
            
            System.out.println("==== Recherche de patients ====");
            List<Patient> patientsMalades = patientRepository.findByMalade(true);
            patientsMalades.forEach(p -> {
                System.out.println("Patient malade: " + p.getNom());
            });
            
            System.out.println("==== Mise à jour d'un patient ====");
            if (patient != null) {
                patient.setScore(95);
                patientRepository.save(patient);
                System.out.println("Patient " + patient.getNom() + " mis à jour avec score: " + patient.getScore());
            }
            
            System.out.println("==== Suppression d'un patient ====");
            if (patient != null) {
                patientRepository.delete(patient);
                System.out.println("Patient " + patient.getNom() + " supprimé");
            }
            
            // Création de médecins
            Stream.of("Dr. Ahmed", "Dr. Yasmine", "Dr. Omar")
                  .forEach(name -> {
                      Medecin medecin = new Medecin();
                      medecin.setNom(name);
                      medecin.setEmail(name.toLowerCase().replace(" ", ".") + "@hospital.ma");
                      medecin.setSpecialite(Math.random() > 0.5 ? "Cardiologue" : "Dentiste");
                      medecinRepository.save(medecin);
                  });
            
            // Ajouter quelques patients pour les rendez-vous
            Patient patient1 = new Patient();
            patient1.setNom("Karim");
            patient1.setDateNaissance(new Date());
            patient1.setMalade(true);
            patient1.setScore(80);
            patientRepository.save(patient1);
            
            // Créer un rendez-vous
            Medecin medecin = medecinRepository.findByNom("Dr. Ahmed");
            RendezVous rendezVous = new RendezVous();
            rendezVous.setDate(new Date());
            rendezVous.setStatut(StatutRDV.EN_COURS);
            rendezVous.setPatient(patient1);
            rendezVous.setMedecin(medecin);
            RendezVous savedRDV = rendezVousRepository.save(rendezVous);
            
            // Créer une consultation
            Consultation consultation = new Consultation();
            consultation.setDateConsultation(new Date());
            consultation.setRapport("Rapport de consultation pour " + patient1.getNom());
            consultation.setRendezVous(savedRDV);
            consultationRepository.save(consultation);
            
            // Test de la gestion des utilisateurs et rôles
            User user1 = new User();
            user1.setUsername("user1");
            user1.setPassword("password1");
            userService.addNewUser(user1);
            
            User user2 = new User();
            user2.setUsername("user2");
            user2.setPassword("password2");
            userService.addNewUser(user2);
            
            Role roleAdmin = new Role();
            roleAdmin.setName("ADMIN");
            userService.addNewRole(roleAdmin);
            
            Role roleUser = new Role();
            roleUser.setName("USER");
            userService.addNewRole(roleUser);
            
            userService.addRoleToUser("user1", "ADMIN");
            userService.addRoleToUser("user1", "USER");
            userService.addRoleToUser("user2", "USER");
            
            System.out.println("Application démarrée avec succès!");
        };
    }
}
