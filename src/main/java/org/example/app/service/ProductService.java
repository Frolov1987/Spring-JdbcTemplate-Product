package org.example.app.service;

import org.example.app.entity.Product;
import org.example.app.exeptions.ProductDataException;
import org.example.app.repository.ProductRepositoryImpl;
import org.example.app.utils.Constants;
import org.example.app.utils.IdValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicInteger;
@Component
public class ProductService {

    @Autowired
    Product contact;
    @Autowired
    ProductRepositoryImpl repoImpl;

    Map<String, String> errors = new HashMap<>();

    public String create(Product product) {
        validateData(product);
        if (!errors.isEmpty()) {
            try {
                throw new ProductDataException("Check inputs", errors);
            } catch (ProductDataException e) {
                return e.getErrors(errors);
            }
        }

        if (repoImpl.create(product)) {
            return Constants.DATA_INSERT_MSG;
        } else {
            return Constants.SMTH_WRONG_MSG;
        }
    }

    public String getAll() {

        Optional<List<Product>> optional = repoImpl.getAll();
        if (optional.isPresent()) {
            List<Product> list = optional.get();
            if (list.isEmpty()) {
                return Constants.DATA_ABSENT_MSG;
            }

            AtomicInteger count = new AtomicInteger(0);
            StringBuilder stringBuilder = new StringBuilder();
            list.forEach((contact) ->
                    stringBuilder.append(count.incrementAndGet())
                            .append(") ")
                            .append(contact.toString())
            );
            return stringBuilder.toString();
        } else {
            return Constants.DATA_ABSENT_MSG;
        }
    }

    public String getById(String id) {
        validateId(id);
        if (!errors.isEmpty()) {
            try {
                throw new ProductDataException("Check inputs", errors);
            } catch (ProductDataException e) {
                return e.getErrors(errors);
            }
        }

        Optional<Product> optional = repoImpl.getById(Integer.parseInt(id));
        if (optional.isEmpty()) {
            return Constants.DATA_ABSENT_MSG;
        } else {
            Product product = optional.get();
            return product.toString();
        }
    }

    public String update(Product product) {
        validateData(product);
        validateId(String.valueOf(product.getId()));
        if (!errors.isEmpty()) {
            try {
                throw new ProductDataException("Check inputs",
                        errors);
            } catch (ProductDataException e) {
                return e.getErrors(errors);
            }
        }

        if (repoImpl.update(product)) {
            return Constants.DATA_UPDATE_MSG;
        } else {
            return Constants.SMTH_WRONG_MSG;
        }
    }

    public String delete(String id) {
        validateId(id);
        if (!errors.isEmpty()) {
            try {
                throw new ProductDataException("Check inputs", errors);
            } catch (ProductDataException e) {
                return e.getErrors(errors);
            }
        }

        contact.setId(Integer.parseInt(id));
        if (repoImpl.delete(contact)) {
            return Constants.DATA_DELETE_MSG;
        } else {
            return Constants.SMTH_WRONG_MSG;
        }
    }

    private void validateData(Product product) {
        if (product.getName().isEmpty())
            errors.put("name", Constants.INPUT_REQ_MSG);
        if (product.getQuota() <= 0)
            errors.put("quota", Constants.INPUT_REQ_MSG);
        if (product.getPrice() <= 0)
            errors.put("price", Constants.INPUT_REQ_MSG);
    }

    private void validateId(String id) {
        if (IdValidator.isIdValid(id))
            errors.put("id", Constants.ID_ERR_MSG);
    }
}
