package com.nisum.creacionusuarios.repository;

import com.nisum.creacionusuarios.domain.Phone;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PhoneRepository extends JpaRepository<Phone, Long> {
}
