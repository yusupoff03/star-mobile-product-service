package com.example.sofiyaproductservice.repository.tv;

import com.example.sofiyaproductservice.domain.entity.TvEntity;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.UUID;

@Repository
public interface TvRepository extends JpaRepository<TvEntity, UUID> {
    List<TvEntity> searchTvEntitiesByModelContainingIgnoreCase(String model, Pageable pageable);


}
