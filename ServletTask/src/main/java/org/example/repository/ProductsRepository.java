package org.example.repository;

import org.example.model.Product;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class ProductsRepository {
    private List<Product> products = new ArrayList<>();
    private static ProductsRepository productsRepository;

    private ProductsRepository() {


    }

    public static ProductsRepository getInstance() {
        if (productsRepository==null){
            productsRepository =new ProductsRepository();
        }
        return productsRepository;
    }

    public List<Product> getProducts() {
        return products;
    }

    public Optional<Product> findProductByName(String name) {
        for (Product product : products) {
            if (product.getName().equalsIgnoreCase(name)) {
                return Optional.of(product);
            }
        }
        return Optional.empty();
    }

    public void addProduct(Product product) {
        if (findProductByName(product.getName()).isPresent()) {
            throw new IllegalArgumentException("Product with this name already exists.");
        }
        products.add(product);
    }

    public void updateProduct(Product product) {
        deleteProduct(product.getName());
        addProduct(product);
    }

    public void deleteProduct(String name) {
        Product productToRemove = null;
        for (Product product : products) {
            if (product.getName().equalsIgnoreCase(name)) {
                productToRemove = product;
                break;
            }
        }

        if (productToRemove != null) {
            products.remove(productToRemove);
        } else {
            throw new IllegalArgumentException("Sorry, The Product not found.");
        }
    }

}
