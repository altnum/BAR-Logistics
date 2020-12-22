package com.bar.BARLogistics.repositories;

import com.bar.BARLogistics.entities.Capitals;
import com.bar.BARLogistics.entities.PartsLocations;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface CapitalsRepository extends JpaRepository<Capitals, String> {
    @Query("SELECT c FROM Capitals c WHERE c.name = :name")
    Capitals findCapitalsByName(String name);
}
