/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package fooddeliverysystem;


public class MenuItem {
       public static class InvalidQuantityException extends Exception {
        public InvalidQuantityException(String message) {
            super(message);
        }
    }

    private String name;
    private double price;
    private int quantity;

    public MenuItem(String name, double price, int quantity) throws InvalidQuantityException {
        this.name = name;
        setPrice(price);
        setQuantity(quantity);
    }

    public String getName() { return name; }

    public void setName(String name) { this.name = name; }

    public double getPrice() { return price; }

    public void setPrice(double price) {
        if (price <= 0) {
            throw new IllegalArgumentException("Price must be positive: " + price);
        }
        this.price = price;
    }

    public int getQuantity() { return quantity; }

    public void setQuantity(int quantity) throws InvalidQuantityException {
        if (quantity <= 0) {
            throw new InvalidQuantityException("Quantity cannot be zero or negative: " + quantity);
        }
        this.quantity = quantity;
    }

    public double getTotalPrice() {
        return price * quantity;
    }

    @Override
    public String toString() {
        return String.format("%s x%d @ %.2f TL = %.2f TL", name, quantity, price, getTotalPrice());
    }
}
