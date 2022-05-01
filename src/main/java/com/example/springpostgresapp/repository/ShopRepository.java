package com.example.springpostgresapp.repository;

import com.example.springpostgresapp.entity.Shop;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ShopRepository extends JpaRepository<Shop, Long> {
}
