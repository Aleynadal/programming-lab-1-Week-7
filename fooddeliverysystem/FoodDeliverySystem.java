/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package fooddeliverysystem;


public class FoodDeliverySystem {


    public static void main(String[] args) {
        Customer c1 = new Customer("Ali Yilmaz", "0532 111 22 33", "Ataturk St. No:5", "Melikgazi", "Kayseri");
        Customer c2 = new Customer("Ayse Demir", "0533 444 55 66", "Cumhuriyet St. No:12", "Talas", "Kayseri");

        Order o1 = new Order(c1);
        Order o2 = new Order(c2);
        System.out.println("Order numbers: " + o1.getOrderNumber() + ", " + o2.getOrderNumber());

        try {
           
            o1.addItem(new MenuItem("Pizza", 80, 1));
            o1.addItem(new MenuItem("Pizza", 80, 1));
            o1.addItem(new MenuItem("Ayran", 15, 2));
            o1.addItem(new MenuItem("Salad", 40, 1));
            System.out.println("o1 items: " + o1.getItems());

            
            o1.updateQuantity("Ayran", 3);
            o1.removeItem("Salad");
            System.out.printf("o1 subtotal: %.2f TL%n", o1.getSubtotal());

            
            o1.applyCoupon(new Coupon("SAVE10", 10, 100));
            o1.checkout();

            
            o2.addItem(new MenuItem("Doner", 120, 1));
            o2.applyCoupon(new Coupon("BIG50", 50, 500));
            o2.checkout();
        } catch (Exception e) {
            System.out.println("Unexpected error: " + e.getMessage());
        }

        
        try {
            o1.removeItem("Hamburger");
        } catch (Order.ItemNotFoundException e) {
            System.out.println("ItemNotFoundException: " + e.getMessage());
        }

        
        try {
            Order o3 = new Order(c1);
            o3.addItem(new MenuItem("Soup", 30, 1));
            o3.updateQuantity("Soup", 0);
        } catch (MenuItem.InvalidQuantityException | Order.ItemNotFoundException e) {
            System.out.println(e.getClass().getSimpleName() + ": " + e.getMessage());
        }

        
        try {
            Order o4 = new Order(c2);
            o4.addItem(new MenuItem("Water", 5, 2));
            o4.checkout();
        } catch (Order.MinimumOrderException | MenuItem.InvalidQuantityException e) {
            System.out.println(e.getClass().getSimpleName() + ": " + e.getMessage());
        }

        
        try {
            o1.checkout();
        } catch (IllegalStateException | Order.MinimumOrderException e) {
            System.out.println("Error: " + e.getMessage());
        }

        
        System.out.println("Completed orders: " + Order.getCompletedOrders());
        System.out.printf("Total revenue: %.2f TL%n", Order.getTotalRevenue());
    }
    
}
