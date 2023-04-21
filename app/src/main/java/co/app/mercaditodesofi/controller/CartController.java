package co.app.mercaditodesofi.controller;

import java.util.ArrayList;

import co.app.mercaditodesofi.model.Product;

public class CartController {

    private static ArrayList<Integer> cantidades = new ArrayList<>();
    private static ArrayList<Product> products = new ArrayList<>();

    public static void addProduct(Product product) {
        products.add(product);
    }

    public static void addCantidad(int cantidad) { cantidades.add(cantidad); }

    public static ArrayList<Product> getProducts() {
        return products;
    }

    public static ArrayList<Integer> getCantidades() { return cantidades; }

    public static void clean() { products.clear(); cantidades.clear(); }

    public static void clearCantidades() { cantidades.clear(); }

}
