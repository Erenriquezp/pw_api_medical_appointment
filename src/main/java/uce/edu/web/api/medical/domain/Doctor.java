package uce.edu.web.api.medical.domain;

import io.quarkus.hibernate.orm.panache.PanacheEntityBase;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;

import jakarta.persistence.Table;
import jakarta.persistence.GenerationType;

@Entity
@Table(name = "doctors")
@SequenceGenerator(name = "doctor_seq", sequenceName = "doctor_sequence", allocationSize = 1)
public class Doctor extends PanacheEntityBase {
   @Id
   @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "doctor_seq")
   public Long id;

   public String nombre;
   public String apellido;
   public String especialidad;
   public String email;
   public String telefono;
   public String numOficina;
   public String status = "ACTIVO";
}
