package uce.edu.web.api.medical.application;

import java.util.ArrayList;
import java.util.List;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import uce.edu.web.api.medical.application.representation.CitaRepresentation;
import uce.edu.web.api.medical.domain.Cita;
import uce.edu.web.api.medical.domain.Doctor;
import uce.edu.web.api.medical.domain.Paciente;
import uce.edu.web.api.medical.infrastructure.CitaRepository;
import uce.edu.web.api.medical.infrastructure.DoctorRepository;
import uce.edu.web.api.medical.infrastructure.PacienteRepository;

@ApplicationScoped
public class CitaService {
   @Inject
   CitaRepository appointmentRepository;

   @Inject
   DoctorRepository doctorRepository;

   @Inject
   PacienteRepository patientRepository;

   public List<CitaRepresentation> getAll() {
      List<CitaRepresentation> appointments = new ArrayList<>();
      for (Cita appointment : this.appointmentRepository.listAll()) {
         appointments.add(this.mapToDto(appointment));
      }
      return appointments;
   }

   public CitaRepresentation update(Long id, CitaRepresentation dto) {
      Cita appointment = appointmentRepository.findById(id);
      if (appointment != null) {
         appointment.fechaCita = dto.fechaCita;
         appointment.status = dto.status;

         Doctor doc = doctorRepository.findById(dto.doctorId);
         Paciente pat = patientRepository.findById(dto.pacienteId);

         if (doc == null || pat == null) {
            throw new IllegalArgumentException("Doctor or Patient not found");
         }
         appointment.doctor = doc;
         appointment.paciente = pat;

         appointmentRepository.persist(appointment);
         return mapToDto(appointment);
      }
      throw new IllegalArgumentException("Appointment not found");
   }

   @Transactional
   public CitaRepresentation create(CitaRepresentation dto) {
      Cita appointment = new Cita();
      appointment.fechaCita = dto.fechaCita;
      appointment.status = "ACTIVA";

      Doctor doc = doctorRepository.findById(dto.doctorId);
      Paciente pat = patientRepository.findById(dto.pacienteId);

      if (doc == null || pat == null) {
         throw new IllegalArgumentException("Doctor or Patient not found");
      }
      appointment.doctor = doc;
      appointment.paciente = pat;

      appointmentRepository.persist(appointment);

      return mapToDto(appointment);
   }

   @Transactional
   public CitaRepresentation cancelAppointment(Long id) {
      Cita app = appointmentRepository.findById(id);
      if (app != null) {
         app.status = "INACTIVA";
         appointmentRepository.persist(app);
         return mapToDto(app);
      }
      return null;
   }

   private CitaRepresentation mapToDto(Cita entity) {
      CitaRepresentation dto = new CitaRepresentation();
      dto.id = entity.id;
      dto.fechaCita = entity.fechaCita;
      dto.status = entity.status;
      dto.doctorId = entity.doctor.id;
      dto.doctor = entity.doctor.nombre + " " + entity.doctor.apellido;
      dto.pacienteId = entity.paciente.id;
      dto.paciente = entity.paciente.nombre + " " + entity.paciente.apellido;

      return dto;
   }
}
