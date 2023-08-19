package com.example.sofiyaproductservice.repository.phone;

import com.example.sofiyaproductservice.domain.entity.PhoneEntity;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface PhoneRepository extends JpaRepository<PhoneEntity, UUID> {
    List<PhoneEntity> searchPhoneEntitiesByModelContainingIgnoreCase(String model, Pageable pageable);

}

