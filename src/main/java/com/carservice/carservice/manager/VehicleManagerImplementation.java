package com.carservice.carservice.manager;

import com.carservice.carservice.entity.Vehicle;
import com.carservice.carservice.repository.VehicleRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class VehicleManagerImplementation implements VehicleManager{

    public final VehicleRepository vehicleRepository;

    public VehicleManagerImplementation(VehicleRepository vehicleRepository) {
        this.vehicleRepository = vehicleRepository;
    }

    @Override //zbrka je bila jer su mi se ova metoda isto zvala kao ona iz repozitorija (nisam razlikovao)
    public List<Vehicle> findAllByClient_IdManager(Long id) {
        List<Vehicle> clientVehiclesList = vehicleRepository.findAllByClientId(id);
        return clientVehiclesList;
    }

    //PAZI OVA METODA NE BI TREBALA BITI VOID, ZASTO TREBA VRAĆAT VEHICLE?
    @Override
    public void createVehicle(Vehicle vehicle) {
        vehicleRepository.save(vehicle);
    }

    @Override
    public List<Vehicle> getAllVehicles() {
        List<Vehicle> allVehicleList = vehicleRepository.findAll();
        return allVehicleList;
    }

    @Override
    public Vehicle getVehicleByVehicleId(Long vehicleId) {
        return vehicleRepository.findVehicleById(vehicleId);
    }

    @Override
    public void deleteVehicle(Long vehicleId) {
        vehicleRepository.deleteById(vehicleId);
    }

    @Override
    public List<Vehicle> getVehiclesByClientId(Long clientId) {
        List<Vehicle> oneClientVehicleList = vehicleRepository.findVehiclesByClientId(clientId);
        return oneClientVehicleList;
    }
}
