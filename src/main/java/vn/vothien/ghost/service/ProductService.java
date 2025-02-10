package vn.vothien.ghost.service;

import java.util.Optional;

import org.springframework.stereotype.Service;

import vn.vothien.ghost.domain.Product;
import vn.vothien.ghost.repository.ProductRepository;

@Service
public class ProductService {
    private final ProductRepository productRepository;

    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    // handle create Product
    public Product handleCreateProduct(Product product) {
        return this.productRepository.save(product);
    }

    // take Product by id
    public Product fetchProductById(long id) {
        Optional<Product> ProductOptional = this.productRepository.findById(id);
        if (ProductOptional.isPresent()) {
            return ProductOptional.get();
        }
        return null;
    }

    // update Product
    public Product handleUpdateProduct(Product reqProduct) {
        Product currentProduct = this.fetchProductById(reqProduct.getId());
        if (currentProduct != null) {
            currentProduct.setNameProduct(reqProduct.getNameProduct());
            currentProduct.setNameAuthor(reqProduct.getNameAuthor());
            currentProduct.setDescription(reqProduct.getDescription());

            // update
            currentProduct = this.productRepository.save(currentProduct);
        }
        return currentProduct;
    }

    // delete use
    public void handleDeleteProduct(long id) {
        this.productRepository.deleteById(id);
    }
}
