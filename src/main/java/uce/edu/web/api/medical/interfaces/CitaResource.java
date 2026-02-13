package uce.edu.web.api.medical.interfaces;

import java.util.List;

import jakarta.inject.Inject;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.PATCH;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.PUT;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.core.UriInfo;
import uce.edu.web.api.medical.application.CitaService;
import uce.edu.web.api.medical.application.representation.CitaRepresentation;
import uce.edu.web.api.medical.application.representation.LinkDto;

@Path("/citas")
public class CitaResource {
   @Inject
   private CitaService appointmentService;

   @Inject
   private UriInfo uriInfo;

   @GET
   @Produces(MediaType.APPLICATION_JSON)
   public List<CitaRepresentation> listAll() {
      return appointmentService.getAll().stream().map(this::buildLinks).toList();
   }

   @POST
   @Consumes(MediaType.APPLICATION_JSON)
   @Produces(MediaType.APPLICATION_JSON)
   public Response create(CitaRepresentation dto) {
      try {
         CitaRepresentation created = this.appointmentService.create(dto);
         return Response.status(Response.Status.CREATED).entity(this.buildLinks(created)).build();
      } catch (IllegalArgumentException e) {
         return Response.status(Response.Status.BAD_REQUEST).build();
      }
   }

   @PUT
   @Path("/{id}")
   @Consumes(MediaType.APPLICATION_JSON)
   @Produces(MediaType.APPLICATION_JSON)
   public Response update(@PathParam("id") Long id, CitaRepresentation dto) {
      try {
         CitaRepresentation updated = this.appointmentService.update(id, dto);
         return Response.ok(this.buildLinks(updated)).build();
      } catch (IllegalArgumentException e) {
         return Response.status(Response.Status.BAD_REQUEST).build();
      }
   }

   @PATCH
   @Path("/{id}/cancelar")
   public Response cancel(@PathParam("id") Long id) {
      CitaRepresentation canceled = appointmentService.cancelAppointment(id);
      if (canceled == null) {
         return Response.status(Response.Status.NOT_FOUND).build();
      }
      return Response.ok(this.buildLinks(canceled)).build();
   }

   private CitaRepresentation buildLinks(CitaRepresentation cita) {
      String self = this.uriInfo.getBaseUriBuilder()
            .path(CitaResource.class)
            .path(String.valueOf(cita.id))
            .build()
            .toString();

      String doctor = this.uriInfo.getBaseUriBuilder()
            .path(DoctorResource.class)
            .path(String.valueOf(cita.doctorId))
            .build()
            .toString();

      String paciente = this.uriInfo.getBaseUriBuilder()
            .path(PacienteResource.class)
            .path(String.valueOf(cita.pacienteId))
            .build()
            .toString();

      String cancelar = this.uriInfo.getBaseUriBuilder()
            .path(CitaResource.class)
            .path(String.valueOf(cita.id))
            .path("cancelar")
            .build()
            .toString();

      cita.links = List.of(
            new LinkDto(self, "self"),
            new LinkDto(doctor, "doctor"),
            new LinkDto(paciente, "paciente"),
            new LinkDto(cancelar, "cancel"));
      return cita;
   }
}
