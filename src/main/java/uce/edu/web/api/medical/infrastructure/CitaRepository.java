package uce.edu.web.api.medical.infrastructure;

import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;
import uce.edu.web.api.medical.domain.Cita;

@ApplicationScoped
public class CitaRepository implements PanacheRepository<Cita> {
   
}
