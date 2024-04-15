package edu.neu.neuconnect.controller.rest;

import edu.neu.neuconnect.controller.rest.options.PaginationOption;
import edu.neu.neuconnect.controller.rest.types.PaginationResponseType;
import edu.neu.neuconnect.dao.ProductDAO;
import edu.neu.neuconnect.model.Product;
import edu.neu.neuconnect.model.User;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/products")
public class ProductController {
    @Autowired
    private ProductDAO productDAO;

    @PostMapping()
    public Product createProduct(@RequestBody Product product, HttpServletRequest request, HttpServletResponse response) throws Exception {
        long productId = (Long) request.getSession().getAttribute("productId");
        return productDAO.create(product);
    }

    @GetMapping("/{id}")
    @ResponseBody
    public Product getProductByID(@PathVariable long id, HttpServletRequest request) {
        try {
            return productDAO.getById(id);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @PostMapping("/fetch")
    @ResponseBody
    public ResponseEntity pagination(@RequestBody PaginationOption options){
        List records = productDAO.pagination(options);
        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body(new PaginationResponseType<User>(records, options.getPageNumber(), options.getPageSize()));
    }
}