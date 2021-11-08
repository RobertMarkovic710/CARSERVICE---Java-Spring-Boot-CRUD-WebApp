package com.carservice.carservice.manager;

import com.carservice.carservice.entity.Vehicle;

import java.util.List;

public interface VehicleManager {
    List<Vehicle> findAllByClient_IdManager(Long id);
    void createVehicle(Vehicle vehicle);
    List<Vehicle> getAllVehicles();
    Vehicle getVehicleByVehicleId(Long clientId);
    void deleteVehicle(Long vehicleId);
    List<Vehicle> getVehiclesByClientId(Long clientId);
}