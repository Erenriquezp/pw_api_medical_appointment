package uce.edu.web.api.medical.application.representation;

import java.util.ArrayList;
import java.util.List;

public class DoctorRepresentation {
   public Long id;

   public String firstName;
   public String lastName;
   public String specialty;
   public String email;
   public String phoneNumber;
   public String officeNumber;   

   public List<LinkDto> links = new ArrayList<>();

}
