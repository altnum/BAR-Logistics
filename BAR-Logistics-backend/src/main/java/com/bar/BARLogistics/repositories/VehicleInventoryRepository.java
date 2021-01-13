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

    @Transactional
    @Modifying
    @Query("UPDATE VehicleInventory SET order_id = :order_id WHERE id = :vehicleId")
    void attachOrder (Integer order_id, Integer vehicleId);

    @Transactional
    @Modifying
    @Query("UPDATE VehicleInventory SET order_id = 0, availability = true  WHERE order_id = :order_id")
    void freeToGo (Integer order_id);
}
