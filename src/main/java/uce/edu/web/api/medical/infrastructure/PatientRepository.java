package uce.edu.web.api.medical.infrastructure;

import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;
import uce.edu.web.api.medical.domain.Patient;

@ApplicationScoped
public class PatientRepository implements PanacheRepository<Patient> {
   
}
