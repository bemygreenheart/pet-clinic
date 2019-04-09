package jaha.naz.petclinic.service;


import jaha.naz.petclinic.model.Owner;

public interface OwnerService extends CrudService<Owner, Long>{
    Owner findByLastName(String lastName);

}
