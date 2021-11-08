package com.carservice.carservice.manager;

import com.carservice.carservice.entity.Client;
import com.carservice.carservice.entity.Service;
import com.carservice.carservice.repository.ServiceRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;


import java.util.List;
import java.util.Optional;


@org.springframework.stereotype.Service //jer ima entitet istog imena pa zato ide path da se bolje razlikuje
public class ServiceManagerImplementation implements ServiceManager{

    public final ServiceRepository serviceRepository;

    public ServiceManagerImplementation(ServiceRepository serviceRepository) {
        this.serviceRepository = serviceRepository;
    }

    @Override
    public Page<Service> getAllServices(Pageable pageable) {
        return serviceRepository.findAll(pageable);
    }

    @Override
    public void createService(Service service) {
        serviceRepository.save(service);
    }

    @Override
    public Optional<Service> getServiceById(Long serviceId) {
        return serviceRepository.findById(serviceId);
    }

    @Override
    public void deleteServicebyId(Long serviceId) {
        serviceRepository.deleteById(serviceId);
    }

    @Override
    public List<Service> getServicesByVehicleId(Long vehicleId) {
        List<Service> oneVehicleServicesList = serviceRepository.findServicesByVehicleId(vehicleId);
        return  oneVehicleServicesList;
    }
}
