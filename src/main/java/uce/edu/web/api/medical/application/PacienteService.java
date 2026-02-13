package uce.edu.web.api.medical.application;

import java.util.ArrayList;
import java.util.List;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import uce.edu.web.api.medical.application.representation.PacienteRepresentation;
import uce.edu.web.api.medical.domain.Paciente;
import uce.edu.web.api.medical.infrastructure.PacienteRepository;

@ApplicationScoped
public class PacienteService {

   @Inject
   private PacienteRepository patientRepository;

   public List<PacienteRepresentation> getAll() {
      List<PacienteRepresentation> patients = new ArrayList<>();
      for (Paciente patient : this.patientRepository.listAll()) {
         patients.add(this.mapper(patient));
      }
      return patients;
   }

   public PacienteRepresentation getById(Long id) {
      return this.mapper(this.patientRepository.findById(id));
   }

   @Transactional
   public PacienteRepresentation create(PacienteRepresentation patientR) {
      Paciente patient = this.mapperToPatient(patientR);
      this.patientRepository.persist(patient);
      return this.mapper(patient);
   }

   @Transactional
   public PacienteRepresentation update(Long id, PacienteRepresentation patientR) {
      Paciente patient = this.patientRepository.findById(id);
      if (patient != null) {
         patient.nombre = patientR.nombre;
         patient.apellido = patientR.apellido;
         patient.fechaNacimiento = patientR.fechaNacimiento;
         patient.direccion = patientR.direccion;
         patient.email = patientR.email;
         patient.telefono = patientR.telefono;
         return this.mapper(patient);
      }
      return null;
   }

   @Transactional
   public PacienteRepresentation partialUpdate(Long id, PacienteRepresentation patientR) {
      Paciente patient = this.patientRepository.findById(id);
      if (patient != null) {
         if (patientR.nombre != null) {
            patient.nombre = patientR.nombre;
         }
         if (patientR.apellido != null) {
            patient.apellido = patientR.apellido;
         }
         if (patientR.fechaNacimiento != null) {
            patient.fechaNacimiento = patientR.fechaNacimiento;
         }
         if (patientR.direccion != null) {
            patient.direccion = patientR.direccion;
         }
         if (patientR.email != null) {
            patient.email = patientR.email;
         }
         if (patientR.telefono != null) {
            patient.telefono = patientR.telefono;
         }
         return this.mapper(patient);
      }
      return null;
   }

   @Transactional
   public void delete(Long id) {
      this.patientRepository.deleteById(id);
   }

   private PacienteRepresentation mapper(Paciente patient) {
      PacienteRepresentation patientR = new PacienteRepresentation();
      patientR.id = patient.id;
      patientR.nombre = patient.nombre;
      patientR.apellido = patient.apellido;
      patientR.fechaNacimiento = patient.fechaNacimiento;
      patientR.direccion = patient.direccion;
      patientR.email = patient.email;
      patientR.telefono = patient.telefono;

      return patientR;
   }

   private Paciente mapperToPatient(PacienteRepresentation patientR) {
      Paciente patient = new Paciente();
      patient.id = patientR.id;
      patient.nombre = patientR.nombre;
      patient.apellido = patientR.apellido;
      patient.fechaNacimiento = patientR.fechaNacimiento;
      patient.direccion = patientR.direccion;
      patient.email = patientR.email;
      patient.telefono = patientR.telefono;

      return patient;
   }
}
