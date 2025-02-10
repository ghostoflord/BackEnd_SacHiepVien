package vn.vothien.ghost.service;

import java.time.Instant;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import vn.vothien.ghost.domain.Product;
import vn.vothien.ghost.domain.User;
import vn.vothien.ghost.domain.response.ResProductDTO;
import vn.vothien.ghost.domain.response.ResUserDTO;
import vn.vothien.ghost.domain.response.ResultPaginationDTO;
import vn.vothien.ghost.repository.ProductRepository;

@Service
public class ProductService {
    private final ProductRepository productRepository;

    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    // take product list
    public ResultPaginationDTO fetchAllProduct(Specification<Product> spec, Pageable pageable) {
        Page<Product> pageProduct = this.productRepository.findAll(spec, pageable);
        ResultPaginationDTO rs = new ResultPaginationDTO();
        ResultPaginationDTO.Meta mt = new ResultPaginationDTO.Meta();

        mt.setPage(pageable.getPageNumber() + 1);
        mt.setPageSize(pageable.getPageSize());

        mt.setPages(pageProduct.getTotalPages());
        mt.setTotal(pageProduct.getTotalElements());

        rs.setMeta(mt);
        rs.setResult(pageProduct.getContent());

        // remove sensitive data
        List<ResProductDTO> listProduct = pageProduct.getContent()
                .stream().map(item -> this.convertToResProductDTO(item))
                .collect(Collectors.toList());

        rs.setResult(listProduct);
        return rs;
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

    // convert data product
    public ResProductDTO convertToResProductDTO(Product product) {
        ResProductDTO res = new ResProductDTO();
        res.setId(product.getId());
        res.setDescription(product.getDescription());
        res.setImage(product.getImage());
        res.setNameAuthor(product.getNameAuthor());
        res.setNameProduct(product.getNameProduct());
        res.setUpdatedAt(product.getUpdatedAt());
        res.setCreatedAt(product.getCreatedAt());
        return res;
    }
}
