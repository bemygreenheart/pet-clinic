package jaha.naz.petclinic.springdataJpa;

import jaha.naz.petclinic.model.Pet;
import jaha.naz.petclinic.model.PetType;
import jaha.naz.petclinic.repository.PetTypeRepository;
import jaha.naz.petclinic.service.PetTypeservice;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
@Profile(value = "jpaService")
public class PetTypeDSJpaService implements PetTypeservice {

    private final PetTypeRepository petTypeRepository;

    public PetTypeDSJpaService(PetTypeRepository petTypeRepository) {
        this.petTypeRepository = petTypeRepository;
    }

    @Override
    public PetType findById(Long aLong) {
        return petTypeRepository.findById(aLong).orElse(null);
    }

    @Override
    public Set<PetType> findAll() {
        Set<PetType> petTypes= new HashSet<>();
        petTypeRepository.findAll().forEach(petTypes::add);
        return petTypes;
    }

    @Override
    public PetType save(PetType object) {
        return petTypeRepository.save(object);
    }

    @Override
    public void deleteById(Long aLong) {
        petTypeRepository.deleteById(aLong);
    }

    @Override
    public void delete(PetType object) {
        petTypeRepository.delete(object);
    }
}
