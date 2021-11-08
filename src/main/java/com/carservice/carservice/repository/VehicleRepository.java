package com.carservice.carservice.repository;

import com.carservice.carservice.entity.Vehicle;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface VehicleRepository extends JpaRepository<Vehicle, Long > {
    List<Vehicle> findAllByClientId(Long clientId); //ova MORA ovdje biti jer ona radi na BAZI
    Vehicle findVehicleById(Long vehicleId);
    List<Vehicle> findVehiclesByClientId(Long clientId);
}
