package jaha.naz.petclinic.repository;

import jaha.naz.petclinic.model.Owner;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;
import java.util.Set;

public interface OwnerRepository extends CrudRepository<Owner,Long> {
    public Optional<Owner> findByLastName(String lastName);
}
