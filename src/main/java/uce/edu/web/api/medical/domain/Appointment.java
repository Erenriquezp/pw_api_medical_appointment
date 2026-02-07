package uce.edu.web.api.medical.domain;

import java.time.LocalDateTime;

import io.quarkus.hibernate.orm.panache.PanacheEntityBase;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import jakarta.persistence.GenerationType;

@Entity
@Table(name = "appointments")
@SequenceGenerator(name = "appointment_seq", sequenceName = "appointment_sequence", allocationSize = 1)
public class Appointment extends PanacheEntityBase {
   @Id
   @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "appointment_seq")
   public Long id;

   public LocalDateTime appointmentDate;

   public String status;

   @ManyToOne
   @JoinColumn(name = "doctor_id")
   public Doctor doctor;

   @ManyToOne
   @JoinColumn(name = "patient_id")
   public Patient patient;
}
