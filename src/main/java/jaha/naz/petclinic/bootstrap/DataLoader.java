package jaha.naz.petclinic.bootstrap;

import jaha.naz.petclinic.model.*;
import jaha.naz.petclinic.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component
public class DataLoader implements CommandLineRunner {

    private final OwnerService ownerService;
    private final SpecialtyService specialtyService;
    private final VetService vetService;
    private final PetTypeservice petTypeservice;
    private final VisitService visitService;

    @Autowired
    public DataLoader(OwnerService ownerService, PetService petService, SpecialtyService specialtyService, VetService vetService, PetTypeservice petTypeservice, VisitService visitService) {
        this.ownerService = ownerService;
        this.specialtyService = specialtyService;
        this.vetService = vetService;
        this.petTypeservice = petTypeservice;
        this.visitService = visitService;
    }

    @Override
    public void run(String... args) throws Exception {

        PetType cat= new PetType();
        cat.setName("cat");
        PetType savedCatType= petTypeservice.save(cat);

        PetType dog= new PetType();
        dog.setName("dog");
        PetType savedDogType= petTypeservice.save(dog);

        Specialty surgery= new Specialty();
        surgery.setDescription("Surgery");
        Specialty savedSurgery= specialtyService.save(surgery);

        Specialty radiology= new Specialty();
        radiology.setDescription("Radiology");
        Specialty savedRadiology= specialtyService.save(radiology);

        Specialty dentistry = new Specialty();
        dentistry.setDescription("Dentistery");
        Specialty savedDentistery= specialtyService.save(dentistry);

        Owner owner1= new Owner();
        owner1.setFirstName("Jaha");
        owner1.setLastName("Nazarov");
        owner1.setAddress("Bagdad");
        owner1.setCity("Los Angles");
        owner1.setTelephone("555556660");

        Pet jack= new Pet();
        jack.setName("jack");
        jack.setPetType(savedCatType);
        jack.setOwner(owner1);
        jack.setBirthDate(LocalDate.now());

        owner1.getPets().add(jack);
        ownerService.save(owner1);

        Visit jackVisit= new Visit();
        jackVisit.setDate(LocalDate.now());
        jackVisit.setDescription("Sneezing hardly...");
        jackVisit.setPet(jack);

        visitService.save(jackVisit);

        Owner owner2= new Owner();
        owner2.setFirstName("Ahadjon");
        owner2.setLastName("Abduhalilov");
        owner2.setTelephone("57334589347");
        owner2.setAddress("New York");
        owner2.setCity("North Crarolin");

        Pet mosh= new Pet();
        mosh.setName("Mosh");
        mosh.setBirthDate(LocalDate.now());
        mosh.setPetType(savedDogType);
        mosh.setOwner(owner2);


        owner2.getPets().add(mosh);
        ownerService.save(owner2);

        Vet vet1= new Vet();
        vet1.setFirstName("Laziz");
        vet1.setLastName("Ibrohimov");
        vet1.getSpecialities().add(surgery);
        vet1.getSpecialities().add(radiology);

        Vet vet2= new Vet();
        vet2.setFirstName("Bahodirjon");
        vet2.setLastName("Rahimov");
        vet2.getSpecialities().add(dentistry);

        vetService.save(vet1);
        vetService.save(vet2);

        System.out.println("All data have been loaded.....");

    }
}
