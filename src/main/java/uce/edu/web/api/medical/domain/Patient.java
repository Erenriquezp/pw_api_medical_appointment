package uce.edu.web.api.medical.domain;

import java.time.LocalDate;

import io.quarkus.hibernate.orm.panache.PanacheEntityBase;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import jakarta.persistence.GenerationType;


@Entity
@Table(name = "patients")
@SequenceGenerator(name = "patients_seq", sequenceName = "patients_sequence", allocationSize = 1)
public class Patient extends PanacheEntityBase {
   @Id
   @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "patients_sequence")
   public Long id;

   public String firstName;
   public String lastName;
   public LocalDate birthDate;
   public String phoneNumber;
   public String email;
   public String address;
}
