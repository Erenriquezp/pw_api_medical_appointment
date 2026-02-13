package uce.edu.web.api.medical.application;

import java.util.ArrayList;
import java.util.List;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import uce.edu.web.api.medical.application.representation.DoctorRepresentation;
import uce.edu.web.api.medical.domain.Doctor;
import uce.edu.web.api.medical.infrastructure.DoctorRepository;

@ApplicationScoped
public class DoctorService {

   @Inject
   DoctorRepository doctorRepository;

   public List<DoctorRepresentation> getAll() {
      List<DoctorRepresentation> list = new ArrayList<>();
      for (Doctor doc : doctorRepository.listAll()) {
         list.add(this.mapper(doc));
      }
      return list;
   }

   public DoctorRepresentation getById(Long id) {
      return this.mapper(this.doctorRepository.findById(id));
   }

   @Transactional
   public DoctorRepresentation create(DoctorRepresentation docR) {
      Doctor doc = this.mapperToDoctor(docR);
      this.doctorRepository.persist(doc);
      return this.mapper(doc);
   }

   @Transactional
   public DoctorRepresentation update(Long id, DoctorRepresentation docR) {
      Doctor doc = this.doctorRepository.findById(id);
      if (doc != null) {
         doc.nombre = docR.nombre;
         doc.apellido = docR.apellido;
         doc.especialidad = docR.especialidad;
         doc.numOficina = docR.numOficina;
         doc.telefono = docR.telefono;
         doc.email = docR.email;
         doc.status = docR.status;
         return this.mapper(doc);
      }
      return null;
   }

   @Transactional
   public DoctorRepresentation partialUpdate(Long id, DoctorRepresentation docR) {
      Doctor doc = this.doctorRepository.findById(id);
      if (doc != null) {
         if (docR.nombre != null) {
            doc.nombre = docR.nombre;
         }
         if (docR.apellido != null) {
            doc.apellido = docR.apellido;
         }
         if (docR.especialidad != null) {
            doc.especialidad = docR.especialidad;
         }
         if (docR.telefono != null) {
            doc.telefono = docR.telefono;
         }
         if (docR.numOficina != null) {
            doc.numOficina = docR.numOficina;
         }
         if (docR.email != null) {
            doc.email = docR.email;
         }

         return this.mapper(doc);
      }
      return null;
   }

   @Transactional
   public void delete(Long id) {
      Doctor doc = this.doctorRepository.findById(id);
      if (doc != null) {
         doc.status = "INACTIVO";
      }
   }

   private DoctorRepresentation mapper(Doctor doc) {
      DoctorRepresentation docR = new DoctorRepresentation();
      docR.id = doc.id;
      docR.nombre = doc.nombre;
      docR.apellido = doc.apellido;
      docR.especialidad = doc.especialidad;
      docR.telefono = doc.telefono;
      docR.numOficina = doc.numOficina;
      docR.email = doc.email;
      docR.status = doc.status;
      return docR;
   }

   private Doctor mapperToDoctor(DoctorRepresentation docR) {
      Doctor doc = new Doctor();
      doc.id = docR.id;
      doc.nombre = docR.nombre;
      doc.apellido = docR.apellido;
      doc.especialidad = docR.especialidad;
      doc.telefono = docR.telefono;
      doc.numOficina = docR.numOficina;
      doc.email = docR.email;
      return doc;
   }
}
