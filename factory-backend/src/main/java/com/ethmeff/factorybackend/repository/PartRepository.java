package com.ethmeff.factorybackend.repository;

import com.ethmeff.factorybackend.model.Part;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PartRepository extends JpaRepository<Part, Long> {

}
