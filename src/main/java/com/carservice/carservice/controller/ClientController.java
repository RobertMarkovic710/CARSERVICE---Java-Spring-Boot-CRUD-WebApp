package com.carservice.carservice.controller;

import com.carservice.carservice.entity.Client;
import com.carservice.carservice.entity.Service;
import com.carservice.carservice.entity.Vehicle;
import com.carservice.carservice.mailValidator.MailValidator;
import com.carservice.carservice.manager.ClientManager;
import com.carservice.carservice.manager.ServiceManager;
import com.carservice.carservice.manager.VehicleManager;
import com.carservice.carservice.oibValidator.OibValidator;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/client")
@SessionAttributes("client")
public class ClientController {

    private final ClientManager clientManager;
    private final VehicleManager vehicleManager;
    private final ServiceManager serviceManager;

    public ClientController(ClientManager clientManager, VehicleManager vehicleManager, ServiceManager serviceManager) {
        this.clientManager = clientManager;
        this.vehicleManager = vehicleManager;
        this.serviceManager = serviceManager;
    }

    @GetMapping("/create")
    private String getCreateClientPage(Model model){
        Client client = new Client();
        List<Vehicle> allVehiclesList = vehicleManager.getAllVehicles();
        model.addAttribute("client", client); //OVA VARIJABLA MORA BITI ISTA NA FRONTENDU JER JU ŠALJEŠ
        return "client-create";
    }

    @PostMapping("/create") //iz html-a vuce iz action i method, zato je PostMapping za slanje, a GetMapping je samo za rendering site-a
    private String createClientOnSubmit(@ModelAttribute @Validated Client client){
        if(!MailValidator.isValidEmailAddress(client.getE_mail()) || client.getE_mail().isEmpty()) { //posto je static metoda pozoves ju na klasi, pa ne moras injectat gore
            return "possibleErrors/WrongEmail";
        }
        if(!OibValidator.oibValidation(String.valueOf(client.getOib()))){
            return "possibleErrors/WrongOib";
        }
        clientManager.createClient(client);
        return "redirect:/client/list";
    }

    @GetMapping("/list")
    private String getListOfClientsPage(Model model){
        List<Client> allClientsList;
        allClientsList = clientManager.getAllClients();
        allClientsList = clientManager.sortClientsAlphabetically();
        model.addAttribute("allClientsList", allClientsList);
        return "client-list";
    }

    @GetMapping("/delete/{clientId}")
    private String deleteClient(@PathVariable Long clientId){
        List<Vehicle> clientVehiclesList = vehicleManager.getVehiclesByClientId(clientId);

        for(Vehicle vehicle : clientVehiclesList){
            List<Service> vehicleServices = serviceManager.getServicesByVehicleId(vehicle.getId());
            for(Service service : vehicleServices) {
                serviceManager.deleteServicebyId(service.getId());
            }
            vehicleManager.deleteVehicle(vehicle.getId());
        }
        clientManager.deleteClient(clientId);
        return "redirect:/client/list";
    }

    @GetMapping("/info/{clientId}")
    private String getClientInfoPage(Model model, @PathVariable Long clientId){
        Optional<Client> client = clientManager.getClient(clientId);
//        List<Vehicle> vehiclesForSpecificClient = vehicleManager.findAllByClient_IdManager(clientId);

        if(client.isPresent()){
        model.addAttribute("client", client.get()); //uvijek na optional mora ici get()
//        model.addAttribute("vehiclesForSpecificClient", vehiclesForSpecificClient);
        return "client-info";
        }
        else {
            return "redirect:/client/list";
        }
    }

    @GetMapping("/edit/{clientId}")
    private String editClient(Model model, @PathVariable Long clientId){
        Optional<Client> client = clientManager.getClient(clientId);
        if(client.isPresent()){
            model.addAttribute("client", client.get()); //uvijek na optional mora ici get()!!!
        }
        else{
            Client emptyClient = new Client();
            model.addAttribute("client", emptyClient);
        }
        return "client-create";
    }
}
