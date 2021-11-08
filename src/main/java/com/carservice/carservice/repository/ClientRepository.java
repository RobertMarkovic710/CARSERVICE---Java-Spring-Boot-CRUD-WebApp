package com.carservice.carservice.repository;

import com.carservice.carservice.entity.Client;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClientRepository extends JpaRepository<Client, Long > { }