package com.bar.BARLogistics.repositories;

import com.bar.BARLogistics.entities.VehicleInventory;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VehicleInventoryRepository extends JpaRepository<VehicleInventory, String> {
}
