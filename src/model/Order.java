package model;

import java.util.ArrayList;

public class Order {
    private String id;
    private Customer customer;
    private ArrayList<OrderItem> items;

    public Order(String id, Customer customer) {
        this.id = id;
        this.customer = customer;
        this.items = new ArrayList<>();
    }

    

	public String getId() {
		return id;
	}



	public void setId(String id) {
		this.id = id;
	}



	public Customer getCustomer() {
		return customer;
	}



	public void setCustomer(Customer customer) {
		this.customer = customer;
	}



	public ArrayList<OrderItem> getItems() {
		return items;
	}



	public void setItems(ArrayList<OrderItem> items) {
		this.items = items;
	}



	public void addItem(OrderItem item) {
        items.add(item);
    }

    public double getTotalAmount() {
        double total = 0;
        for (OrderItem item : items) {
            total += item.getTotal();
        }
        return total;
    }

    public void printInvoice() {
        System.out.println("\n===== HÓA ĐƠN: " + id + " =====");
        System.out.println("Khách: " + customer.getName() + " | SĐT: " + customer.getPhone());
        System.out.println("Danh sách sản phẩm:");
        for (OrderItem item : items) {
            System.out.println("- " + item);
        }
        System.out.println("Tổng tiền: " + getTotalAmount() + " VND");
    }
}
