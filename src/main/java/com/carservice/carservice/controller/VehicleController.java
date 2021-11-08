package com.carservice.carservice.controller;

import com.carservice.carservice.entity.CarType;
import com.carservice.carservice.entity.Client;
import com.carservice.carservice.entity.Service;
import com.carservice.carservice.entity.Vehicle;
import com.carservice.carservice.manager.ClientManager;
import com.carservice.carservice.manager.ServiceManager;
import com.carservice.carservice.manager.VehicleManager;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/vehicle")
@SessionAttributes("vehicle")
public class VehicleController {
    private final VehicleManager vehicleManager;
    private final ClientManager clientManager;
    private final ServiceManager serviceManager;

    public VehicleController(VehicleManager vehicleManager, ClientManager clientManager, ServiceManager serviceManager) {
        this.vehicleManager = vehicleManager;
        this.clientManager = clientManager;
        this.serviceManager = serviceManager;
    }

    @GetMapping("/create")
    private String getCreateVehiclePage(Model model){
        Vehicle vehicle = new Vehicle();
        List<Client> AllClientsList = clientManager.getAllClients();
        model.addAttribute("vehicle", vehicle);
        model.addAttribute("clients", AllClientsList);
        model.addAttribute("carTypes", CarType.values());
        return "vehicle-create";
    }

    @PostMapping("/create")
    private String createNewVehicle(@ModelAttribute @Validated Vehicle vehicle){
        vehicleManager.createVehicle(vehicle);
        Long clientId = vehicle.getClient().getId(); //nakon submit-a vracas na client-a

        return "redirect:/client/info/" + clientId;
    }

    @GetMapping("/list")
    private String ListAllVehicles(Model model){
        List<Vehicle> AllVehiclesList = vehicleManager.getAllVehicles();
        model.addAttribute("AllVehicleList", AllVehiclesList);
        return "vehicle-list";
    }

    @GetMapping("/info/{vehicleId}")
    private String getVehicleInfoPage(Model model, @PathVariable Long vehicleId){
        Optional<Vehicle> vehicleOptional = Optional.ofNullable(vehicleManager.getVehicleByVehicleId(vehicleId));

        if(vehicleOptional.isPresent()){
            List<Service> vehicleOptionalServiceList = serviceManager.getServicesByVehicleId(vehicleId);
            model.addAttribute("vehicle", vehicleOptional.get());
            model.addAttribute("doneServicesList", vehicleOptionalServiceList);
            return "vehicle-info";
        }
            else {
        return "redirect:/vehicle-list";
            }
    }

    @GetMapping("/delete/{vehicleId}")
    private String deleteVehicle(@PathVariable Long vehicleId){
        List<Service> vehicleServices = serviceManager.getServicesByVehicleId(vehicleId);
        for (Service service : vehicleServices) {
            serviceManager.deleteServicebyId(service.getId());
        }
        vehicleManager.deleteVehicle(vehicleId);
        return "redirect:/vehicle/list";
    }

    @GetMapping("/edit/{vehicleId}")
    private String editVehicle(Model model, @PathVariable Long vehicleId){
        Optional<Vehicle> vehicleOptional = Optional.ofNullable(vehicleManager.getVehicleByVehicleId(vehicleId));
        if(vehicleOptional.isPresent()){
            model.addAttribute("vehicleOptional", vehicleOptional.get());
        }
        else{
            model.addAttribute("vehicleOptional", new Vehicle());
        }
        return "vehicle-create";
    }
}
