package uce.edu.web.api.medical.infrastructure;

import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;
import uce.edu.web.api.medical.domain.Appointment;

@ApplicationScoped
public class AppointmentRepository implements PanacheRepository<Appointment> {
   
}
