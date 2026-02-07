package uce.edu.web.api.medical.application;

import java.util.ArrayList;
import java.util.List;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import uce.edu.web.api.medical.application.representation.AppointmentRepresentation;
import uce.edu.web.api.medical.domain.Appointment;
import uce.edu.web.api.medical.domain.Doctor;
import uce.edu.web.api.medical.domain.Patient;
import uce.edu.web.api.medical.infrastructure.AppointmentRepository;
import uce.edu.web.api.medical.infrastructure.DoctorRepository;
import uce.edu.web.api.medical.infrastructure.PatientRepository;

@ApplicationScoped
public class AppointmentService {
   @Inject 
   AppointmentRepository appointmentRepository;

   @Inject
   DoctorRepository doctorRepository;

   @Inject
   PatientRepository patientRepository;

   public List<AppointmentRepresentation> getAll() {
      List<AppointmentRepresentation> appointments = new ArrayList<>();
      for (Appointment appointment : this.appointmentRepository.listAll()) {
         appointments.add(this.mapToDto(appointment));
      }
      return appointments;
   }

   @Transactional
   public AppointmentRepresentation create(AppointmentRepresentation dto) {
      Appointment appointment = new Appointment();
      appointment.appointmentDate = dto.appointmentDate;
      appointment.status = "ACTIVE";

      Doctor doc = doctorRepository.findById(dto.doctorId);
      Patient pat = patientRepository.findById(dto.patientId);

      if (doc == null || pat == null) {
         throw new IllegalArgumentException("Doctor or Patient not found");         
      }
      appointment.doctor = doc;
      appointment.patient = pat;

      appointmentRepository.persist(appointment);

      return mapToDto(appointment);
   }

   @Transactional
   public void cancelAppointment(Long id) {
      Appointment app = appointmentRepository.findById(id);
      if (app != null) {
         app.status = "INACTIVE";         
      }
   }

   private AppointmentRepresentation mapToDto(Appointment entity) {
      AppointmentRepresentation dto = new AppointmentRepresentation();
      dto.id = entity.id;
      dto.appointmentDate = entity.appointmentDate;
      dto.status = entity.status;
      dto.doctorId = entity.doctor.id;
      dto.doctorName = entity.doctor.firstName + " " + entity.doctor.lastName;
      dto.patientId = entity.patient.id;
      dto.patientName = entity.patient.firstName + " " + entity.patient.lastName;

      return dto;
   }
}
