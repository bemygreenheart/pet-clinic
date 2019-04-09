package jaha.naz.petclinic.springdataJpa;

import jaha.naz.petclinic.model.Visit;
import jaha.naz.petclinic.repository.VisitRepository;
import jaha.naz.petclinic.service.VisitService;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
@Profile(value = "jpaService")
public class VisitSDJpaService implements VisitService {
    private final VisitRepository visitRepository;

    public VisitSDJpaService(VisitRepository visitRepository) {
        this.visitRepository = visitRepository;
    }


    @Override
    public Visit findById(Long aLong) {
        return visitRepository.findById(aLong).orElse(null);
    }

    @Override
    public Set<Visit> findAll() {
        Set visits= new HashSet();
        visitRepository.findAll().forEach(visits ::add);
        return visits;
    }

    @Override
    public Visit save(Visit object) {
        return visitRepository.save(object);
    }

    @Override
    public void deleteById(Long aLong) {
        visitRepository.deleteById(aLong);
    }

    @Override
    public void delete(Visit object) {
        visitRepository.delete(object);
    }
}
