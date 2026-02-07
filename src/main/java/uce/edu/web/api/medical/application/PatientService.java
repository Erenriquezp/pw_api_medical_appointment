package uce.edu.web.api.medical.application;

import java.util.ArrayList;
import java.util.List;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import uce.edu.web.api.medical.application.representation.PatientRepresentation;
import uce.edu.web.api.medical.domain.Patient;
import uce.edu.web.api.medical.infrastructure.PatientRepository;

@ApplicationScoped
public class PatientService {
   
   @Inject
   private PatientRepository patientRepository;

   public List<PatientRepresentation> getAll() {
      List<PatientRepresentation> patients = new ArrayList<>();
      for (Patient patient : this.patientRepository.listAll()) {
         patients.add(this.mapper(patient));  
      }
      return patients;
   }

   public PatientRepresentation getById(Long id) {
      return this.mapper(this.patientRepository.findById(id));
   }

   @Transactional
   public void create(PatientRepresentation patientR) {
      this.patientRepository.persist(this.mapperToPatient(patientR));
   }

   @Transactional 
   public void update(Long id, PatientRepresentation patientR) {
      Patient patient = this.patientRepository.findById(id);
      if (patient != null) {
         patient.firstName = patientR.firstName;
         patient.lastName = patientR.lastName;
         patient.birthDate = patientR.birthDate;
         patient.address = patientR.address;
         patient.email = patientR.email;
         patient.phoneNumber = patientR.phoneNumber;       
      }
   }

   @Transactional
   public void partialUpdate(Long id, PatientRepresentation patientR) {
      Patient patient = this.patientRepository.findById(id);
      if (patient != null) {
         if (patientR.firstName != null) {
            patient.firstName = patientR.firstName;            
         }         
         if (patientR.lastName != null) {
            patient.lastName = patientR.lastName;                        
         }
         if (patientR.birthDate != null) {
            patient.birthDate = patientR.birthDate;            
         }
         if (patientR.address != null) {
            patient.address = patientR.address;            
         }
         if (patientR.email != null) {
            patient.email = patientR.email;            
         }
         if (patientR.phoneNumber != null) {
            patient.phoneNumber = patientR.phoneNumber;            
         }
      }
   }

   @Transactional
   public void delete(Long id) {
      this.patientRepository.deleteById(id);
   }

   private PatientRepresentation mapper(Patient patient) {
      PatientRepresentation patientR = new PatientRepresentation();
      patientR.id = patient.id;
      patientR.firstName = patient.firstName;
      patientR.lastName = patient.lastName;
      patientR.birthDate = patient.birthDate;
      patientR.address = patient.address;
      patientR.email = patient.email;
      patientR.phoneNumber = patient.phoneNumber;

      return patientR;
   }

   private Patient mapperToPatient(PatientRepresentation patientR) {
      Patient patient = new Patient();
      patient.id = patientR.id;
      patient.firstName = patientR.firstName;
      patient.lastName = patientR.lastName;
      patient.birthDate = patientR.birthDate;
      patient.address = patientR.address;
      patient.email = patientR.email;
      patient.phoneNumber = patientR.phoneNumber;

      return patient;
   }
}
