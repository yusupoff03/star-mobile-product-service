package com.example.sofiyaproductservice.repository.product;

import com.example.sofiyaproductservice.domain.entity.PhoneEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
public interface PhoneRepository extends JpaRepository<PhoneEntity, UUID> {

}
