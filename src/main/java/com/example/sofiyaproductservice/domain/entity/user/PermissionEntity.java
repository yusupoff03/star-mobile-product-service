package com.example.sofiyaproductservice.domain.entity.user;

import com.example.sofiyaproductservice.domain.entity.BaseEntity;
import jakarta.persistence.Entity;
import lombok.*;

@Entity(name = "permissions")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class PermissionEntity extends BaseEntity {
    private String permission;
}
