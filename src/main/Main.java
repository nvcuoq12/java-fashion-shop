package main;

import java.util.Scanner;

import service.CategoryService;
import service.CustomerService;
import service.OrderService;
import service.ProductService;

public class Main {
	 static Scanner sc = new Scanner(System.in);

	    public static void main(String[] args) {
	        CategoryService categoryService = new CategoryService();
	        ProductService productService = new ProductService(categoryService);
	        CustomerService customerService = new CustomerService();
	        OrderService orderService = new OrderService(productService, customerService);

	        while (true) {
	            System.out.println("\n===== MENU CHÍNH =====");
	            System.out.println("1. Quản lý chủng loại");
	            System.out.println("2. Quản lý sản phẩm");
	            System.out.println("3. Quản lý khách hàng");
	            System.out.println("4. Quản lý đơn hàng & doanh thu");
	            System.out.println("0. Thoát");
	            System.out.print("Chọn: ");
	            int choice = Integer.parseInt(sc.nextLine());
	            switch (choice) {
	                case 1 -> categoryService.menu();
	                case 2 -> productService.menu();
	                case 3 -> customerService.menu();
	                case 4 -> orderService.menu();
	                case 0 -> {
	                    System.out.println("Thoát chương trình.");
	                    return;
	                }
	                default -> System.out.println("Lựa chọn không hợp lệ.");
	            }
	        }
	    }
}
