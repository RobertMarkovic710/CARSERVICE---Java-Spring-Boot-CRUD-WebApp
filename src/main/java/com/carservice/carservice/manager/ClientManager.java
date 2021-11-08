package com.carservice.carservice.manager;


import com.carservice.carservice.entity.Client;
import com.carservice.carservice.entity.Vehicle;

import java.util.List;
import java.util.Optional;

public interface ClientManager {
    Client createClient(Client client);
    Optional<Client> getClient(Long id);
    List<Client> getAllClients();
    void deleteClient(Long id);
    List<Client> sortClientsAlphabetically();
    List<Vehicle> getServicingVehicles(Long id);
}
