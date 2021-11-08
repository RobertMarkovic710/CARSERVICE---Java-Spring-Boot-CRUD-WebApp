package com.carservice.carservice.controller;

import com.carservice.carservice.entity.Service;
import com.carservice.carservice.manager.ServiceManager;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class HomePageController {

    private final ServiceManager serviceManager;

    public HomePageController(ServiceManager serviceManager) {
        this.serviceManager = serviceManager;
    }

    @GetMapping("/")
    private String getHomePage(Model model){
        Page<Service> serviceList;
        serviceList = serviceManager.getAllServices(PageRequest.of(0, 10, Sort.by("date").descending()));

        model.addAttribute("sortedServicesList", serviceList);

        //pageimpl
        //how to use pageimpl
        //how to get 10 record from database using pageimpl springboot
        return "index";
    }
}