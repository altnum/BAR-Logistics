package com.bar.BARLogistics.repositories;

import com.bar.BARLogistics.entities.PartsLocations;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface PartsLocationsRepository extends JpaRepository<PartsLocations, String> {
    @Query("SELECT p_l FROM PartsLocations p_l WHERE p_l.name = :name")
    List<PartsLocations> findPartsLocationsByName(String name);
}
