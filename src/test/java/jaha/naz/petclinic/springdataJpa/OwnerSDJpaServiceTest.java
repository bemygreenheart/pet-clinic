package jaha.naz.petclinic.springdataJpa;

import jaha.naz.petclinic.model.Owner;
import jaha.naz.petclinic.repository.OwnerRepository;
import jaha.naz.petclinic.repository.PetRepository;
import jaha.naz.petclinic.repository.PetTypeRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class OwnerSDJpaServiceTest {
    public static final String LAST_NAME = "Smith";
    @Mock
    OwnerRepository ownerRepository;
    @Mock
    PetRepository petRepository;
    @Mock
    PetTypeRepository petTypeRepository;

    @InjectMocks
    OwnerSDJpaService ownerSDJpaService;

    Owner returnOwner;
    @BeforeEach
    void setUp() {
        returnOwner= new Owner().builder().id(1L).lastName(LAST_NAME).build();
    }

    @Test
    void findByLastName() {
        when(ownerRepository.findByLastName(any())).thenReturn(Optional.of(returnOwner));
        Owner newOwner=ownerSDJpaService.findByLastName(LAST_NAME);
        assertNotNull(newOwner);
        assertEquals(LAST_NAME,newOwner.getLastName());
    }

    @Test
    void findByIdPresent() {
        when(ownerRepository.findById(any())).thenReturn(Optional.of(returnOwner));
        Owner owner= ownerSDJpaService.findById(1L);
        assertNotNull(owner);
    }

    @Test
    void findByIdNull() {
        when(ownerRepository.findById(any())).thenReturn(Optional.empty());
        Owner owner= ownerSDJpaService.findById(1L);
        assertNull(owner);
    }

    @Test
    void findAll() {
        Set<Owner> ownerSet= new HashSet<>();
        ownerSet.add(new Owner().builder().id(2L).build());
        ownerSet.add(new Owner().builder().id(3L).build());

        when(ownerRepository.findAll()).thenReturn(ownerSet);

        assertEquals(2,ownerSDJpaService.findAll().size());
        verify(ownerRepository,times(1)).findAll();
    }

    @Test
    void save() {
        when(ownerRepository.save(any())).thenReturn(returnOwner);
        Owner owner= ownerSDJpaService.save(returnOwner);

        assertEquals(new Long(1),owner.getId());
    }

    @Test
    void deleteById() {
        ownerSDJpaService.deleteById(1L);
        verify(ownerRepository,times(1)).deleteById(any());
    }

    @Test
    void delete() {
        ownerSDJpaService.delete(returnOwner);
        verify(ownerRepository,times(1)).delete(any());
    }
}