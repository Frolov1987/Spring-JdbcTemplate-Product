package org.example.app.repository;

import org.example.app.entity.Product;

import java.util.List;
import java.util.Optional;

public interface ProductRepository {

    boolean create(Product product);
    Optional<List<Product>> getAll();
    Optional<Product> getById(Integer id);
    boolean update(Product product);
    boolean delete(Product product);
}
