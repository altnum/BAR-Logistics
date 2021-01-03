package com.bar.BARLogistics.controllers;

import com.bar.BARLogistics.entities.VehicleInventory;
import com.bar.BARLogistics.repositories.VehicleInventoryRepository;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/test/user/vehicle")
public class VehicleController {

    private VehicleInventoryRepository vehicleInventoryRepository;

    public VehicleController(VehicleInventoryRepository vehicleInventoryRepository) {
        this.vehicleInventoryRepository = vehicleInventoryRepository;
    }

    @GetMapping("/example")
    public VehicleInventory getExampleVehicle (String volume) {
        int volumeOfParts = Integer.parseInt(volume);
        List<VehicleInventory> vehicle;

        if (volumeOfParts <= 200) {
            vehicle = vehicleInventoryRepository.findByType("Van");
        } else if (volumeOfParts <= 500) {
            vehicle = vehicleInventoryRepository.findByType("MicroBus");
        } else {
            vehicle = vehicleInventoryRepository.findByType("TIR");
        }

        return vehicle.get(0);
    }

    @GetMapping("/free")
    public List<VehicleInventory> getAvailableVehicles() {
        return vehicleInventoryRepository.findVehicleInventoriesByAvailability();
    }

}
