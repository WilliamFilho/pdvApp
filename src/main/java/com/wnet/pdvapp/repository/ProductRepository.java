package com.wnet.pdvapp.repository;

import com.wnet.pdvapp.entity.Product;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends CustomJpaRepository<Product, Long> {
}
