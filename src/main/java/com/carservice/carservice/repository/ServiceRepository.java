package com.carservice.carservice.repository;

import com.carservice.carservice.entity.Service;
import com.carservice.carservice.entity.Vehicle;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ServiceRepository extends JpaRepository<Service, Long > {
    List<Service> findServicesByVehicleId(Long vehicleId);

}
