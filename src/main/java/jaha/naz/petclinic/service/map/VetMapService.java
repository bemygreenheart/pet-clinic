package jaha.naz.petclinic.service.map;

import jaha.naz.petclinic.model.Specialty;
import jaha.naz.petclinic.model.Vet;
import jaha.naz.petclinic.service.SpecialtyService;
import jaha.naz.petclinic.service.VetService;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import java.util.Set;
@Service
@Profile({"default","map"})
public class VetMapService extends AbstractMapService<Vet,Long> implements VetService {

    private final SpecialtyService specialtyService;

    public VetMapService(SpecialtyService specialtyService) {
        this.specialtyService = specialtyService;
    }


    @Override
    public Vet save(Vet object) {
        if(object!=null) {
            object.getSpecialities().forEach(specialty -> {
                if(specialty!=null){
                    if(specialty.getId()==null){
                        Specialty savedSpecialty= specialtyService.save(specialty);
                        specialty.setId(savedSpecialty.getId());
                    }
                }else{
                    throw new RuntimeException("Specialty can not be null");
                }
            });
            return super.save(object);
        }else{
            return null;
        }
    }

    @Override
    public Set<Vet> findAll() {
        return super.findAll();
    }

    @Override
    public Vet findById(Long aLong) {
        return super.findById(aLong);
    }

    @Override
    public void deleteById(Long aLong) {
        super.deleteById(aLong);
    }

    @Override
    public void delete(Vet object) {
        super.delete(object);
    }
}
