package uce.edu.web.api.medical.interfaces;

import java.util.List;

import jakarta.inject.Inject;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.PATCH;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.core.Response;
import uce.edu.web.api.medical.application.AppointmentService;
import uce.edu.web.api.medical.application.representation.AppointmentRepresentation;

@Path("/appointments")
public class AppointmentResource {
   @Inject
   AppointmentService appointmentService;

   @GET
   public List<AppointmentRepresentation> listAll() {
      return appointmentService.getAll();
   }

   @POST
   public Response create(AppointmentRepresentation dto) {
      try {
         AppointmentRepresentation created = this.appointmentService.create(dto);
         return Response.status(Response.Status.CREATED).entity(created).build();
      } catch (IllegalArgumentException e) {
         return Response.status(Response.Status.BAD_REQUEST).build();
      }
   }

   @PATCH
   @Path("/{id}/cancel")
   public Response cancel(@PathParam("id") Long id) {
      appointmentService.cancelAppointment(id);
      return Response.ok().build();
   }
}
