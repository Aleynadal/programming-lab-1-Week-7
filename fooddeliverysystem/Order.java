/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package fooddeliverysystem;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Order {
    
    
    public static class ItemNotFoundException extends Exception {
        public ItemNotFoundException(String message) {
            super(message);
        }
    }

    public static class MinimumOrderException extends Exception {
        public MinimumOrderException(String message) {
            super(message);
        }
    }

    
    private static int nextOrderNumber = 1001;
    public static final double MIN_ORDER = 100;
    public static final double DELIVERY_FEE = 20;
    private static int completedOrders = 0;
    private static double totalRevenue = 0;

   
    private final int orderNumber;
    private Customer customer;
    private ArrayList<MenuItem> items = new ArrayList<>();
    private Coupon coupon;
    private boolean completed = false;

    public Order(Customer customer) {
        this.orderNumber = nextOrderNumber++;
        this.customer = customer;
    }

    public int getOrderNumber() { return orderNumber; }
    public Customer getCustomer() { return customer; }

    public void addItem(MenuItem item) throws MenuItem.InvalidQuantityException {
        for (MenuItem existing : items) {
            if (existing.getName().equalsIgnoreCase(item.getName())) {
                existing.setQuantity(existing.getQuantity() + item.getQuantity());
                return;
            }
        }
        items.add(item);
    }

    public void removeItem(String name) throws ItemNotFoundException {
        items.remove(findItem(name));
    }

    public void updateQuantity(String name, int quantity)
            throws ItemNotFoundException, MenuItem.InvalidQuantityException {
        findItem(name).setQuantity(quantity);
    }

    private MenuItem findItem(String name) throws ItemNotFoundException {
        for (MenuItem item : items) {
            if (item.getName().equalsIgnoreCase(name)) {
                return item;
            }
        }
        throw new ItemNotFoundException("Item '" + name + "' was not found in the order.");
    }

    public double getSubtotal() {
        double sum = 0;
        for (MenuItem item : items) {
            sum += item.getTotalPrice();
        }
        return sum;
    }

    
    public List<MenuItem> getItems() {
        return Collections.unmodifiableList(items);
    }

    public void applyCoupon(Coupon coupon) {
        this.coupon = coupon;
    }

   
    private double getCouponDiscount() {
        if (coupon != null && getSubtotal() >= coupon.getMinSubtotal()) {
            return getSubtotal() * coupon.getDiscountRate() / 100.0;
        }
        return 0;
    }

    public double checkout() throws MinimumOrderException {
        if (completed) {
            throw new IllegalStateException("Order #" + orderNumber + " has already been completed.");
        }
        double subtotal = getSubtotal();
        if (subtotal < MIN_ORDER) {
            throw new MinimumOrderException(String.format(
                    "Subtotal (%.2f TL) is below the minimum order amount (%.2f TL).", subtotal, MIN_ORDER));
        }
        double discount = getCouponDiscount();
        double total = subtotal - discount + DELIVERY_FEE;

        System.out.println("===== RECEIPT =====");
        System.out.println("Order No        : " + orderNumber);
        System.out.println("Customer        : " + customer.getName());
        System.out.println("Address         : " + customer.getAddress());
        System.out.println("--- Items ---");
        for (MenuItem item : items) {
            System.out.println(item);
        }
        System.out.printf("Subtotal        : %.2f TL%n", subtotal);
        System.out.printf("Coupon discount : -%.2f TL%n", discount);
        System.out.printf("Delivery fee    : %.2f TL%n", DELIVERY_FEE);
        System.out.printf("TOTAL           : %.2f TL%n", total);
        System.out.println("===================");

        completed = true;
        completedOrders++;
        totalRevenue += total;
        return total;
    }

    public static int getCompletedOrders() { return completedOrders; }
    public static double getTotalRevenue() { return totalRevenue; }
    
}
