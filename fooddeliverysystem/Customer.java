/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package fooddeliverysystem;


public class Customer {
        private String name;
    private String phone;
    private Address address;

    
    public Customer(String name, String phone, String street, String district, String city) {
        this.name = name;
        this.phone = phone;
        this.address = new Address(street, district, city);
    }

    public String getName() { return name; }
    public String getPhone() { return phone; }
    public Address getAddress() { return address; }

    @Override
    public String toString() {
        return name + " (" + phone + ") - " + address;
    }
    
}
