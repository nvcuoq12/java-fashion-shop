package service;

import model.*;
import java.util.*;

public class OrderService {
    private ArrayList<Order> orders = new ArrayList<>();
    private Scanner sc = new Scanner(System.in);
    private ProductService productService;
    private CustomerService customerService;

    public OrderService(ProductService ps, CustomerService cs) {
        this.productService = ps;
        this.customerService = cs;
    }

    public void menu() {
        while (true) {
            System.out.println("\n=== QUẢN LÝ ĐƠN HÀNG & DOANH THU ===");
            System.out.println("1. Tạo đơn hàng");
            System.out.println("2. Xem danh sách đơn hàng");
            System.out.println("3. Xóa đơn hàng");
            System.out.println("4. Tổng doanh thu toàn hệ thống");
            System.out.println("5. Doanh thu theo khách hàng");
            System.out.println("6. Doanh thu theo sản phẩm");
            System.out.println("0. Quay lại");
            System.out.print("Chọn: ");
            int choice = Integer.parseInt(sc.nextLine());
            switch (choice) {
                case 1 -> createOrder();
                case 2 -> display();
                case 3 -> delete();
                case 4 -> totalRevenue();
                case 5 -> revenueByCustomer();
                case 6 -> revenueByProduct();
                case 0 -> { return; }
                default -> System.out.println("Sai lựa chọn.");
            }
        }
    }

    private void createOrder() {
        System.out.print("ID đơn hàng: ");
        String oid = sc.nextLine();
        System.out.print("Mã khách hàng: ");
        String code = sc.nextLine();
        Customer customer = customerService.findByCode(code);
        if (customer == null) {
            System.out.println("Không tìm thấy khách hàng.");
            return;
        }

        Order order = new Order(oid, customer);
        while (true) {
            System.out.print("ID sản phẩm (x để kết thúc): ");
            String pid = sc.nextLine();
            if (pid.equalsIgnoreCase("x")) break;
            Product p = productService.findById(pid);
            if (p == null) {
                System.out.println("Không tìm thấy sản phẩm.");
                continue;
            }
            System.out.print("Số lượng: ");
            int qty = Integer.parseInt(sc.nextLine());
            order.addItem(new OrderItem(p, qty));
        }
        orders.add(order);
        order.printInvoice();
    }

    private void display() {
        for (Order o : orders) {
            o.printInvoice();
            System.out.println("----------------------");
        }
    }

    private void delete() {
        System.out.print("Nhập ID đơn hàng cần xóa: ");
        String oid = sc.nextLine();
        orders.removeIf(o -> o.getId().equals(oid));
    }

    private void totalRevenue() {
        double sum = 0;
        for (Order o : orders) sum += o.getTotalAmount();
        System.out.println("Tổng doanh thu: " + sum);
    }

    private void revenueByCustomer() {
        System.out.print("Mã khách hàng: ");
        String code = sc.nextLine();
        double sum = 0;
        for (Order o : orders) {
            if (o.getCustomer().getCode().equals(code)) {
                sum += o.getTotalAmount();
            }
        }
        System.out.println("Doanh thu từ khách " + code + ": " + sum);
    }

    private void revenueByProduct() {
        HashMap<String, Double> revenue = new HashMap<>();
        for (Order o : orders) {
            for (OrderItem i : o.getItems()) {
                String pname = i.getProduct().getName();
                revenue.put(pname, revenue.getOrDefault(pname, 0.0) + i.getTotal());
            }
        }
        for (Map.Entry<String, Double> entry : revenue.entrySet()) {
            System.out.println(entry.getKey() + ": " + entry.getValue());
        }
    }
}
