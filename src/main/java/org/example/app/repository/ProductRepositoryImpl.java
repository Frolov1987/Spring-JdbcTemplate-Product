package org.example.app.repository;

import org.example.app.entity.Product;
import org.example.app.entity.ProductMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.util.List;
import java.util.Optional;
@Component
public class ProductRepositoryImpl implements ProductRepository {

    JdbcTemplate jdbcTemplate;

    @Autowired
    public ProductRepositoryImpl(DataSource dataSource) {
        jdbcTemplate = new JdbcTemplate(dataSource);
    }

    @Override
    public boolean create(Product product) {
        String sql = "INSERT INTO products (name, quota, price) VALUES (?, ?, ?)";
        return jdbcTemplate.update(sql, product.getName(), product.getQuota(),
                product.getPrice()) > 0;
    }

    @Override
    public Optional<List<Product>> getAll() {
        String sql = "SELECT * FROM products";
        Optional<List<Product>> optional;
        try {
            optional = Optional.of(jdbcTemplate
                    .query(sql, new ProductMapper()));
        } catch (Exception ex) {
            optional = Optional.empty();
        }
        return optional;
    }

    @Override
    public Optional<Product> getById(Integer id) {
        String sql = "SELECT * FROM products WHERE id = ? LIMIT 1";
        Optional<Product> optional;
        try {
            optional = Optional.ofNullable(jdbcTemplate
                    .queryForObject(sql, new ProductMapper(), id));
        } catch (Exception ex) {
            optional = Optional.empty();
        }
        return optional;
    }

    @Override
    public boolean update(Product product) {
        Optional<Product> optional = getById(product.getId());
        if (optional.isEmpty()) return false;
        else {
            String sql = "UPDATE products SET name = ?, quota = ?, price = ? WHERE id = ?";
            return jdbcTemplate.update(sql, product.getName(), product.getQuota(), product.getPrice(),
                    product.getId()) > 0;
        }
    }

    @Override
    public boolean delete(Product product) {
        Optional<Product> optional = getById(product.getId());
        if (optional.isEmpty()) return false;
        else {
            String sql = "DELETE FROM products WHERE id = ?";
            return jdbcTemplate.update(sql, product.getId()) > 0;
        }
    }
}
