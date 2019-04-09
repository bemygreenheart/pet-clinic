package jaha.naz.petclinic.springdataJpa;

import jaha.naz.petclinic.model.Vet;
import jaha.naz.petclinic.repository.SpecialtyRepository;
import jaha.naz.petclinic.repository.VetRepository;
import jaha.naz.petclinic.service.VetService;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
@Profile(value = "jpaService")
public class VetSDJpaService implements VetService {

    private final VetRepository vetRepository;
    private final SpecialtyRepository specialtyRepository;

    public VetSDJpaService(VetRepository vetRepository, SpecialtyRepository specialtyRepository) {
        this.vetRepository = vetRepository;
        this.specialtyRepository = specialtyRepository;
    }

    @Override
    public Vet findById(Long aLong) {
        return vetRepository.findById(aLong).orElse(null);
    }

    @Override
    public Set<Vet> findAll() {
        Set<Vet> vets= new HashSet<>(20);
        vetRepository.findAll().forEach(vets::add);
        return vets;
    }

    @Override
    public Vet save(Vet object) {
        return vetRepository.save(object);
    }

    @Override
    public void deleteById(Long aLong) {
        vetRepository.deleteById(aLong);

    }

    @Override
    public void delete(Vet object) {
        vetRepository.delete(object);
    }
}
