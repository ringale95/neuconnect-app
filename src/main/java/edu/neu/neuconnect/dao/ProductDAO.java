package edu.neu.neuconnect.dao;

import edu.neu.neuconnect.model.Product;
import org.springframework.stereotype.Service;

@Service
public class ProductDAO extends DAO<Product> {
    protected ProductDAO() {
        super(Product.class, "Product");
    }
}
