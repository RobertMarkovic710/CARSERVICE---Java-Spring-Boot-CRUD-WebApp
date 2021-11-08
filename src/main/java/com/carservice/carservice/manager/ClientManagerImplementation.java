package com.carservice.carservice.manager;

import com.carservice.carservice.entity.Client;
import com.carservice.carservice.entity.Vehicle;
import com.carservice.carservice.repository.ClientRepository;
import com.carservice.carservice.repository.VehicleRepository;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
public class ClientManagerImplementation implements ClientManager {

    public final ClientRepository clientRepository;
    public final VehicleRepository vehicleRepository;

    public ClientManagerImplementation(ClientRepository clientRepository, VehicleRepository vehicleRepository){
        this.clientRepository = clientRepository;
        this.vehicleRepository = vehicleRepository;
    }

    @Override
    public Client createClient(Client client) {
        //validacija
        //client.getOib() regex ili nesto tako

        return clientRepository.save(client);
    }

    @Override
    public Optional<Client> getClient(Long id) {
        return clientRepository.findById(id);
    }

    @Override
    public List<Client> getAllClients() {
        return clientRepository.findAll();
    }

    @Override
    public void deleteClient(Long id) {
        clientRepository.deleteById(id);
    }

    @Override
    public List<Client> sortClientsAlphabetically() {
        List<Client> sorteredClientList = clientRepository.findAll(Sort.by(Sort.Direction.ASC, "lastName"));
//      clientList.sort(Comparator.comparing(Client::getLastName));
        return sorteredClientList;
    }

    @Override
    public List<Vehicle> getServicingVehicles(Long id) { //client's id
        //List<Vehicle> listServicingVehicles = vehicleRepository.findAllById(id);
        return Collections.emptyList();
    }
}
