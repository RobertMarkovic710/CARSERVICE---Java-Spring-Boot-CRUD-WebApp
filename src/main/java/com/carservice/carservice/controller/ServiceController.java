package com.carservice.carservice.controller;

import com.carservice.carservice.entity.Service;
import com.carservice.carservice.entity.Vehicle;
import com.carservice.carservice.mail.ServiceMailSender;
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
@RequestMapping("/service")
@SessionAttributes("service")
public class ServiceController {

    private final ServiceManager serviceManager;
    private final VehicleManager vehicleManager;
    private final ServiceMailSender serviceMailSender;
    private final ClientManager clientManager;

    public ServiceController(ServiceManager serviceManager, VehicleManager vehicleManager, ServiceMailSender serviceMailSender, ClientManager clientManager) {
        this.serviceManager = serviceManager;
        this.vehicleManager = vehicleManager;
        this.serviceMailSender = serviceMailSender;
        this.clientManager = clientManager;
    }

    @GetMapping("/create")
    private String getServiceCreatePage(Model model){
        List<Vehicle> vehicleList = vehicleManager.getAllVehicles();
        model.addAttribute("service", new Service());
        model.addAttribute("vehicles", vehicleList);
        return "service-create";
    }

    @PostMapping("/create")
    private String createNewService(@ModelAttribute @Validated Service service){
        serviceManager.createService(service);
        Long vehicleId = service.getVehicle().getId();
        return "redirect:/vehicle/info/" + vehicleId;
    }

    @GetMapping("/info/{serviceId}")
    private String getServiceInfoPage(Model model, @PathVariable Long serviceId){
        Optional<Service> serviceOptional = serviceManager.getServiceById(serviceId);
        if(serviceOptional.isPresent()){
            model.addAttribute("service", serviceOptional.get());
            return "service-info";
        }
        else {
            return "redirect:/service-create";
        }
    }

    @GetMapping("/edit/{serviceId}")
    private String getEditServicePage(Model model, @PathVariable Long serviceId) {
        Optional<Service> serviceOptional = serviceManager.getServiceById(serviceId);
        if (serviceOptional.isPresent()) {
            model.addAttribute("service", serviceOptional.get());
        } else {
            model.addAttribute("service", new Service());
        }
        return "service-create";
    }

    @GetMapping("/delete/{serviceId}")
    private String deleteService(@PathVariable Long serviceId){
        serviceManager.deleteServicebyId(serviceId);
        return "redirect:/vehicle/list";
    }

    @GetMapping("/sendMail/{serviceId}")
    private String sendMail(@PathVariable Long serviceId){
        Optional<Service> service = serviceManager.getServiceById(serviceId);
        if(service.isPresent()){
            String clientEmail = vehicleManager.getVehicleByVehicleId(service.get().getVehicle().getId()).getClient().getE_mail();
            serviceMailSender.sendMessage(clientEmail);
        }
        return "redirect:/vehicle/list";
    }
}