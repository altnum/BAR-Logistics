package com.bar.BARLogistics.repositories;

import com.bar.BARLogistics.entities.VehicleInventory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface VehicleInventoryRepository extends JpaRepository<VehicleInventory, String> {
    @Query("SELECT v FROM VehicleInventory v WHERE v.type.type = :type")
    List<VehicleInventory> findByType(String type);
}
