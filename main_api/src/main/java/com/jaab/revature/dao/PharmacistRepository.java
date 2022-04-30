package com.jaab.revature.dao;

import com.jaab.revature.model.Pharmacist;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PharmacistRepository extends JpaRepository<Pharmacist, Long> {
}