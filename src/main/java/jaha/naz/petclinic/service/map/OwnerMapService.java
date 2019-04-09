package jaha.naz.petclinic.service.map;

import jaha.naz.petclinic.model.Owner;
import jaha.naz.petclinic.model.Pet;
import jaha.naz.petclinic.service.OwnerService;
import jaha.naz.petclinic.service.PetService;
import jaha.naz.petclinic.service.PetTypeservice;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

@Service
@Profile({"default","map"})
public class OwnerMapService extends AbstractMapService<Owner, Long> implements OwnerService {

    private final PetTypeservice petTypeservice;
    private final PetService petService;

    public OwnerMapService(PetTypeservice petTypeservice, PetService petService) {
        this.petTypeservice = petTypeservice;
        this.petService = petService;
    }

    @Override
    public Owner save(Owner object) {
        if (object != null) {
            if (object.getPets() != null) {
                object.getPets().forEach(pet -> {
                    if (pet.getPetType() != null) {
                        if (pet.getPetType().getId() == null) {
                            pet.setPetType(petTypeservice.save(pet.getPetType()));
                        }
                    } else {
                        throw new RuntimeException("Pet type is required");
                    }

                    if (pet.getId() == null) {
                        Pet savedPet = petService.save(pet);
                        pet.setId(savedPet.getId());
                    }
                });
            }
            return super.save(object);
        } else {
            return null;
        }
    }

    @Override
    public Owner findByLastName(String lastName) {

        return findAll().stream().filter(owner -> owner.
                getLastName().equalsIgnoreCase(lastName)).
                findFirst().orElse(null);
    }

    @Override
    public Owner findById(Long aLong) {
        return super.findById(aLong);
    }

    @Override
    public void deleteById(Long aLong) {
        super.deleteById(aLong);
    }

    @Override
    public void delete(Owner object) {
        super.delete(object);
    }
}
