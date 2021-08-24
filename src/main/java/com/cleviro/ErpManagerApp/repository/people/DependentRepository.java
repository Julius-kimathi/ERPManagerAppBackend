package com.cleviro.ErpManagerApp.repository.people;

import com.cleviro.ErpManagerApp.model.people.Dependent;
import org.springframework.data.repository.CrudRepository;

import java.time.LocalDate;
import java.util.Optional;

public interface DependentRepository extends CrudRepository<Dependent,Long> {
    Optional<Dependent> findByIdNo(String idNo);

    Optional<Dependent> findByEmail(String email);

    Optional<Dependent> findByFirstNameAndMiddleNameAndLastNameAndDob(String firstName, String middleName, String lastName, LocalDate dob);
}
