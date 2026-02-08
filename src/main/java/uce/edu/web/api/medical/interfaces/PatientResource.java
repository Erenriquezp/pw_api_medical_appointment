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
import uce.edu.web.api.medical.application.PatientService;
import uce.edu.web.api.medical.application.representation.LinkDto;
import uce.edu.web.api.medical.application.representation.PatientRepresentation;

@Path("/patients")
public class PatientResource {
 
   @Inject 
   public PatientService patientService;

   @Inject
   private UriInfo uriInfo;

   @GET
   @Path("")
   @Produces(MediaType.APPLICATION_JSON)
   @RolesAllowed({"admin"})
   public List<PatientRepresentation> listAll() {
      List<PatientRepresentation> list = new ArrayList<>();
      for (PatientRepresentation patR : this.patientService.getAll()) {
         list.add(this.buildLinks(patR));
      }
      return list;
   }

   @GET
   @Path("/{id}")
   @Produces(MediaType.APPLICATION_JSON)
   @RolesAllowed({"admin"})
   public PatientRepresentation getById(@PathParam("id") Long id) {
      return this.buildLinks(this.patientService.getById(id));
   }

   @POST
   @Path("")
   @Consumes(MediaType.APPLICATION_JSON)
   @Produces(MediaType.APPLICATION_JSON)
   @RolesAllowed({"admin"})
   public Response save(PatientRepresentation docR) {
      this.patientService.create(docR);
      return Response.status(Response.Status.CREATED).entity(docR).build();
   }

   @PUT
   @Path("/{id}")
   @Produces(MediaType.APPLICATION_JSON)
   @Consumes(MediaType.APPLICATION_JSON)
   @RolesAllowed({"admin"})
   public Response update(@PathParam("id") Long id, PatientRepresentation patR) {
      this.patientService.update(id, patR);
      return Response.status(209).entity(null).build();
   }

   @PATCH
   @Path("/{id}")
   @Produces(MediaType.APPLICATION_JSON)
   @Consumes(MediaType.APPLICATION_JSON)
   @RolesAllowed({"admin"})
   public Response partialUpdate(@PathParam("id") Long id, PatientRepresentation patR) {
      this.patientService.partialUpdate(id, patR);
      return Response.status(204).entity(null).build();
   }

   @DELETE
   @Path("/{id}")
   @RolesAllowed({"admin"})
   public void borrar(@PathParam("id") Long id) {
      this.patientService.delete(id);
   }

   private PatientRepresentation buildLinks(PatientRepresentation patR) {
      String self = this.uriInfo.getBaseUriBuilder()
               .path(DoctorResource.class)
               .path(String.valueOf(patR.id))
               .build()
               .toString();

      patR.links = List.of(new LinkDto(self, "self"));
      return patR;
   }
}
