package uce.edu.web.api.medical.application.representation;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class PacienteRepresentation {
   public Long id;
   public String nombre;
   public String apellido;
   public LocalDate fechaNacimiento;
   public String telefono;
   public String email;
   public String direccion;

   public List<LinkDto> links = new ArrayList<>();
}
