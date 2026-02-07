package uce.edu.web.api.medical.application.representation;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class PatientRepresentation {
   public Long id;
   public String firstName;
   public String lastName;
   public LocalDate birthDate;
   public String phoneNumber;
   public String email;
   public String address;

   public List<LinkDto> links = new ArrayList<>();
}
