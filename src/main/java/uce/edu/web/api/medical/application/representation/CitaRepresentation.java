package uce.edu.web.api.medical.application.representation;

import java.time.LocalDateTime;

public class CitaRepresentation {
   public Long id;
   public LocalDateTime fechaCita;
   public String status;

   public Long doctorId;
   public String doctor;
   public Long pacienteId;
   public String paciente;

}
