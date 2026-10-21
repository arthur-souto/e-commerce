package com.e_commerce.store.adapters.out.persistence.repository;

import com.e_commerce.store.adapters.out.persistence.entity.ProductEntity;
import org.jspecify.annotations.NonNull;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface ProductJpaRepository extends JpaRepository<ProductEntity, UUID> {

    public boolean existsById(@NonNull UUID id);
}
