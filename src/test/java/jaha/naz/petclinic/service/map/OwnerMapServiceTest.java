package jaha.naz.petclinic.service.map;

import jaha.naz.petclinic.model.Owner;
import jaha.naz.petclinic.service.OwnerService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class OwnerMapServiceTest {

  private final String lastName= "Smith";
  private final Long id=1L;

  private OwnerService ownerService;

    @BeforeEach
    void setUp() {
       ownerService= new OwnerMapService(new PetTypeMapService(), new PetMapService());
       ownerService.save(new Owner().builder().id(id).lastName(lastName).build());

    }

    @Test
    void saveWithProvidedId() {
        Owner savedOwner=ownerService.save(new Owner().builder().id(id).build());
        assertEquals(id,savedOwner.getId());
    }

    void saveWithoutId(){
        Owner owner= ownerService.save(new Owner());
        Long num=2L;
        assertEquals(num,owner.getId());
    }

    @Test
    void findByLastName() {
        Owner owner= ownerService.findByLastName(lastName);
        assertNotNull(owner);
        assertEquals(lastName,owner.getLastName());
    }

    @Test
    void findById() {
        Owner owner=ownerService.findById(id);
        assertEquals(id,owner.getId());
    }

    @Test
    void deleteById() {
        ownerService.deleteById(id);
        assertEquals(0,ownerService.findAll().size());
    }

    @Test
    void delete() {
        ownerService.delete(ownerService.findById(id));
        assertEquals(0,ownerService.findAll().size());
    }
}