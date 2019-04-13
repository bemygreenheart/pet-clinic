package jaha.naz.petclinic.model;

import lombok.*;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "vets")
public class Vet extends Person {

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(joinColumns = @JoinColumn(name = "vet_id"), inverseJoinColumns = @JoinColumn(name = "specialty_id"))
    private Set<Specialty> specialities= new HashSet<>();

    @Builder
    public Vet(Long id,String firstName, String lastName,Set<Specialty> specialities){
        super(id,firstName,lastName);
        this.specialities= specialities;
    }
}
