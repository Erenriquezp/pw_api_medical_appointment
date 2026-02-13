package uce.edu.web.api.medical.application.representation;

import java.util.ArrayList;
import java.util.List;

public class DoctorRepresentation {
   public Long id;

   public String nombre;
   public String apellido;
   public String especialidad;
   public String email;
   public String telefono;
   public String numOficina;

   public List<LinkDto> links = new ArrayList<>();

}
