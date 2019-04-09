package jaha.naz.petclinic.springdataJpa;

import jaha.naz.petclinic.model.Specialty;
import jaha.naz.petclinic.repository.SpecialtyRepository;
import jaha.naz.petclinic.service.SpecialtyService;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
@Profile(value = "jpaService")
public class SpecialityDSJpaService implements SpecialtyService {

    private final SpecialtyRepository specialtyRepository;

    public SpecialityDSJpaService(SpecialtyRepository specialtyRepository) {
        this.specialtyRepository = specialtyRepository;
    }

    @Override
    public Specialty findById(Long aLong) {
        return specialtyRepository.findById(aLong).orElse(null);
    }

    @Override
    public Set<Specialty> findAll() {
        Set<Specialty> specialties = new HashSet<>();
        specialtyRepository.findAll().forEach(specialties::add);
        return specialties;
    }

    @Override
    public Specialty save(Specialty object) {
        return specialtyRepository.save(object);
    }

    @Override
    public void deleteById(Long aLong) {
        specialtyRepository.deleteById(aLong);
    }

    @Override
    public void delete(Specialty object) {
        specialtyRepository.delete(object);
    }
}