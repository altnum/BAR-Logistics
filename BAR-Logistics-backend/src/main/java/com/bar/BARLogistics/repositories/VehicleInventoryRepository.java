package com.bar.BARLogistics.repositories;

import com.bar.BARLogistics.entities.VehicleInventory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import javax.transaction.Transactional;
import java.util.List;

public interface VehicleInventoryRepository extends JpaRepository<VehicleInventory, Integer> {
    @Query("SELECT v FROM VehicleInventory v WHERE v.type.type = :type")
    List<VehicleInventory> findByType(String type);

    @Query("SELECT v FROM VehicleInventory v WHERE v.availability = true")
    List<VehicleInventory> findVehicleInventoriesByAvailability();

    @Transactional
    @Modifying
    @Query("UPDATE VehicleInventory SET availability = :availability WHERE id = :vehicleId")
    void changeStatus ( boolean availability, Integer vehicleId);
}
