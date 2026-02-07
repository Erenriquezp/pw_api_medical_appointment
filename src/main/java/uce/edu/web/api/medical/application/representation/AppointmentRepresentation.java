package uce.edu.web.api.medical.application.representation;

import java.time.LocalDateTime;

public class AppointmentRepresentation {
   public Long id;
   public LocalDateTime appointmentDate;
   public String status;

   public Long doctorId;
   public String doctorName;
   public Long patientId;
   public String patientName;

}
