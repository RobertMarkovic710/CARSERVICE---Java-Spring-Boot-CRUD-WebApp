package com.carservice.carservice.manager;

import com.carservice.carservice.entity.Service;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

public interface ServiceManager{
    Page<Service> getAllServices(Pageable pageable);
    void createService(Service service);
    Optional<Service> getServiceById(Long serviceId);
    void deleteServicebyId(Long serviceId);
    List<Service> getServicesByVehicleId(Long vehicleId);
}
