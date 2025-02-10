package vn.vothien.ghost.controller;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.turkraft.springfilter.boot.Filter;

import jakarta.validation.Valid;
import vn.vothien.ghost.domain.Product;
import vn.vothien.ghost.domain.response.ResultPaginationDTO;
import vn.vothien.ghost.service.ProductService;
import vn.vothien.ghost.util.error.IdInvalidException;

@RestController
@RequestMapping("/api/v1")
public class ProductController {
    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    // get list product
    @GetMapping("products")
    public ResponseEntity<ResultPaginationDTO> getAllProduct(@Filter Specification<Product> spec,
            Pageable pageable) {
        return ResponseEntity.status(HttpStatus.OK).body(this.productService.fetchAllProduct(spec, pageable));
    }

    // get Product by id
    @GetMapping("products/{id}")
    public ResponseEntity<Product> getProductById(@PathVariable("id") long id, String email) {
        Product fetchProduct = this.productService.fetchProductById(id);
        return ResponseEntity.status(HttpStatus.OK).body(fetchProduct);
    }

    // create Product
    @PostMapping("/products")
    public ResponseEntity<Product> createProduct(@Valid @RequestBody Product postManProduct) {
        Product ghostProduct = this.productService.handleCreateProduct(postManProduct);
        return ResponseEntity.status(HttpStatus.CREATED).body(ghostProduct);
    }

    // delete Product
    @DeleteMapping("/products/{id}")
    public ResponseEntity<Void> deleteProduct(@PathVariable("id") long id) {
        // Product currentProduct = this.productService.fetchProductById(id);
        this.productService.handleDeleteProduct(id);
        return ResponseEntity.ok(null);
    }

    // put Product

    @PutMapping("/products")
    public ResponseEntity<Product> updateProduct(@Valid @RequestBody Product Product) throws IdInvalidException {
        Product ghostProduct = this.productService.handleUpdateProduct(Product);
        return ResponseEntity.ok(ghostProduct);
    }
}
