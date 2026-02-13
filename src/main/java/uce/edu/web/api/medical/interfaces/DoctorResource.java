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
import uce.edu.web.api.medical.application.DoctorService;
import uce.edu.web.api.medical.application.representation.DoctorRepresentation;
import uce.edu.web.api.medical.application.representation.LinkDto;

@Path("/doctores")
public class DoctorResource {

   @Inject
   DoctorService doctorService;

   @Inject
   private UriInfo uriInfo;

   @GET
   @Path("")
   @Produces(MediaType.APPLICATION_JSON)
   // @RolesAllowed({"admin"})
   public List<DoctorRepresentation> listAll() {
      List<DoctorRepresentation> list = new ArrayList<>();
      for (DoctorRepresentation docR : this.doctorService.getAll()) {
         list.add(this.buildLinks(docR));
      }
      return list;
   }

   @GET
   @Path("/{id}")
   @Produces(MediaType.APPLICATION_JSON)
   @RolesAllowed({ "admin" })
   public DoctorRepresentation getById(@PathParam("id") Long id) {
      return this.buildLinks(this.doctorService.getById(id));
   }

   @POST
   @Path("")
   @Consumes(MediaType.APPLICATION_JSON)
   @Produces(MediaType.APPLICATION_JSON)
   @RolesAllowed({ "admin" })
   public Response save(DoctorRepresentation docR) {
      DoctorRepresentation created = this.doctorService.create(docR);
      return Response.status(Response.Status.CREATED).entity(this.buildLinks(created)).build();
   }

   @PUT
   @Path("/{id}")
   @Produces(MediaType.APPLICATION_JSON)
   @Consumes(MediaType.APPLICATION_JSON)
   @RolesAllowed({ "admin" })
   public Response update(@PathParam("id") Long id, DoctorRepresentation docR) {
      DoctorRepresentation updated = this.doctorService.update(id, docR);
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
   public Response partialUpdate(@PathParam("id") Long id, DoctorRepresentation docR) {
      DoctorRepresentation updated = this.doctorService.partialUpdate(id, docR);
      if (updated == null) {
         return Response.status(Response.Status.NOT_FOUND).build();
      }
      return Response.ok(this.buildLinks(updated)).build();
   }

   @DELETE
   @Path("/{id}")
   @RolesAllowed({ "admin" })
   public void borrar(@PathParam("id") Long id) {
      this.doctorService.delete(id);
   }

   private DoctorRepresentation buildLinks(DoctorRepresentation docR) {
      String self = this.uriInfo.getBaseUriBuilder()
            .path(DoctorResource.class)
            .path(String.valueOf(docR.id))
            .build()
            .toString();

      docR.links = List.of(new LinkDto(self, "self"));
      return docR;
   }
}
