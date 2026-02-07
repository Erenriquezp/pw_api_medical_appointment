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
   public void create(DoctorRepresentation docR) {
      this.doctorRepository.persist(this.mapperToDoctor(docR));
   }

   @Transactional
   public void update(Long id, DoctorRepresentation docR) {
      Doctor doc = this.doctorRepository.findById(id);
      if (doc != null) {
         doc.firstName = docR.firstName;
         doc.lastName = docR.lastName;
         doc.specialty = docR.specialty;
         doc.officeNumber = docR.officeNumber;
         doc.phoneNumber = docR.phoneNumber;
         doc.email = docR.email;         
      }
   }

   @Transactional
   public void partialUpdate(Long id, DoctorRepresentation docR) {
      Doctor doc = this.doctorRepository.findById(id);
      if (doc != null) {
         if (docR.firstName != null) {
            doc.firstName = docR.firstName;
         }
         if (docR.lastName != null) {
            doc.lastName = docR.lastName;
         }
         if (docR.specialty != null) {
            doc.specialty = docR.specialty;            
         }
         if (docR.phoneNumber != null) {
            doc.phoneNumber = docR.phoneNumber;       
         }
         if (docR.officeNumber != null) {
            doc.officeNumber = docR.officeNumber;
         }
         if (docR.email != null) {
            doc.email = docR.email;            
         }
      }
   }

   @Transactional
   public void delete(Long id) {
      this.doctorRepository.deleteById(id);
   }

   private DoctorRepresentation mapper(Doctor doc) {
      DoctorRepresentation docR = new DoctorRepresentation();
      docR.id = doc.id;
      docR.firstName = doc.firstName;
      docR.lastName = doc.lastName;
      docR.specialty = doc.specialty;
      docR.phoneNumber = doc.phoneNumber;
      docR.officeNumber = doc.officeNumber;
      docR.email = doc.email;

      return docR;
   }

   private Doctor mapperToDoctor(DoctorRepresentation docR) {
      Doctor doc = new Doctor();
      doc.id = docR.id;
      doc.firstName = docR.firstName;
      doc.lastName = docR.lastName;
      doc.specialty = docR.specialty;
      doc.phoneNumber = docR.phoneNumber;
      doc.officeNumber = docR.officeNumber;
      doc.email = docR.email;

      return doc;
   }
}
