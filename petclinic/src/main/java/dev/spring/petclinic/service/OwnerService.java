package dev.spring.petclinic.service;

import dev.spring.petclinic.model.Owner;

import java.util.List;
import java.util.Optional;

public interface OwnerService {
    public Owner findByLastName(String lastName);

    List<Owner> findAllByLastNameLike(String lastName);

    Optional<Owner> findById(Long id);

    List<Owner> findAll();

    Owner findOwnerById(Long id);

    Owner save(Owner owner);

    void delete(Owner owner);

}

