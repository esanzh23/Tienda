/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.tienda;

/**
 *
 * @author jpyntiquilla
 */
import java.util.HashMap;
public class ProductManager {
    private static ProductManager instance;
    private HashMap<String, Product> products;
private ProductManager() {
        products = new HashMap<>();
    }

    public static ProductManager getInstance() {
        if (instance == null) {
            instance = new ProductManager();
        }
        return instance;
    }

    public boolean addProduct(Product product) {
        if (!products.containsKey(product.getId())) {
            products.put(product.getId(), product);
            return true;
        }
        return false;
    }

    public Product getProduct(String id) {
        return products.get(id);
    }

    public boolean updateProduct(Product product) {
        if (products.containsKey(product.getId())) {
            products.put(product.getId(), product);
            return true;
        }
        return false;
    }

    public HashMap<String, Product> getProducts() {
        return products;
    }
}
