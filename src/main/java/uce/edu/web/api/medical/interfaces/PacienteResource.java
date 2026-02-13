package uce.edu.web.api.medical.interfaces;

import java.util.ArrayList;
import java.util.List;

import jakarta.annotation.security.RolesAllowed;
import jakarta.inject.Inject;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.DELETE;
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
import uce.edu.web.api.medical.application.PacienteService;
import uce.edu.web.api.medical.application.representation.LinkDto;
import uce.edu.web.api.medical.application.representation.PacienteRepresentation;

@Path("/pacientes")
public class PacienteResource {

   @Inject
   public PacienteService patientService;

   @Inject
   private UriInfo uriInfo;

   @GET
   @Path("")
   @Produces(MediaType.APPLICATION_JSON)
   // @RolesAllowed({"admin"})
   public List<PacienteRepresentation> listAll() {
      List<PacienteRepresentation> list = new ArrayList<>();
      for (PacienteRepresentation patR : this.patientService.getAll()) {
         list.add(this.buildLinks(patR));
      }
      return list;
   }

   @GET
   @Path("/{id}")
   @Produces(MediaType.APPLICATION_JSON)
   @RolesAllowed({ "admin" })
   public PacienteRepresentation getById(@PathParam("id") Long id) {
      return this.buildLinks(this.patientService.getById(id));
   }

   @POST
   @Path("")
   @Consumes(MediaType.APPLICATION_JSON)
   @Produces(MediaType.APPLICATION_JSON)
   @RolesAllowed({ "admin" })
   public Response save(PacienteRepresentation docR) {
      PacienteRepresentation created = this.patientService.create(docR);
      return Response.status(Response.Status.CREATED).entity(this.buildLinks(created)).build();
   }

   @PUT
   @Path("/{id}")
   @Produces(MediaType.APPLICATION_JSON)
   @Consumes(MediaType.APPLICATION_JSON)
   @RolesAllowed({ "admin" })
   public Response update(@PathParam("id") Long id, PacienteRepresentation patR) {
      PacienteRepresentation updated = this.patientService.update(id, patR);
      if (updated == null) {
         return Response.status(Response.Status.NOT_FOUND).build();
      }
      return Response.ok(this.buildLinks(updated)).build();
   }

   @PATCH
   @Path("/{id}")
   @Produces(MediaType.APPLICATION_JSON)
   @Consumes(MediaType.APPLICATION_JSON)
   @RolesAllowed({ "admin" })
   public Response partialUpdate(@PathParam("id") Long id, PacienteRepresentation patR) {
      PacienteRepresentation updated = this.patientService.partialUpdate(id, patR);
      if (updated == null) {
         return Response.status(Response.Status.NOT_FOUND).build();
      }
      return Response.ok(this.buildLinks(updated)).build();
   }

   @DELETE
   @Path("/{id}")
   @RolesAllowed({ "admin" })
   public Response borrar(@PathParam("id") Long id) {
      this.patientService.delete(id);
      return Response.noContent().build();
   }

   private PacienteRepresentation buildLinks(PacienteRepresentation patR) {
      String self = this.uriInfo.getBaseUriBuilder()
            .path(PacienteResource.class)
            .path(String.valueOf(patR.id))
            .build()
            .toString();

      patR.links = List.of(new LinkDto(self, "self"));
      return patR;
   }
}
