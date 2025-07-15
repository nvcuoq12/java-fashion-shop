package service;

import model.Customer;
import java.util.*;

public class CustomerService {
    private ArrayList<Customer> customers = new ArrayList<>();
    private Scanner sc = new Scanner(System.in);

    public void menu() {
        while (true) {
            System.out.println("\n=== QUẢN LÝ KHÁCH HÀNG ===");
            System.out.println("1. Xem khách hàng");
            System.out.println("2. Thêm khách hàng");
            System.out.println("3. Sửa khách hàng");
            System.out.println("4. Xóa khách hàng");
            System.out.println("5. Sắp xếp theo tên");
            System.out.println("0. Quay lại");
            System.out.print("Chọn: ");
            int choice = Integer.parseInt(sc.nextLine());
            switch (choice) {
                case 1 -> display();
                case 2 -> add();
                case 3 -> edit();
                case 4 -> delete();
                case 5 -> sortByName();
                case 0 -> { return; }
                default -> System.out.println("Sai lựa chọn.");
            }
        }
    }

    private void display() {
        for (Customer c : customers) {
            System.out.println(c);
        }
    }

    private void add() {
        System.out.print("Code: ");
        String code = sc.nextLine();
        System.out.print("ID: ");
        String id = sc.nextLine();
        System.out.print("Tên: ");
        String name = sc.nextLine();
        System.out.print("SĐT: ");
        String phone = sc.nextLine();
        customers.add(new Customer(code, id, name, phone));
    }

    private void edit() {
        System.out.print("Code cần sửa: ");
        String code = sc.nextLine();
        for (Customer c : customers) {
            if (c.getCode().equals(code)) {
                System.out.print("Tên mới: ");
                c.setName(sc.nextLine());
                System.out.print("SĐT mới: ");
                c.setPhone(sc.nextLine());
                return;
            }
        }
        System.out.println("Không tìm thấy khách hàng.");
    }

    private void delete() {
        System.out.print("Code cần xóa: ");
        String code = sc.nextLine();
        customers.removeIf(c -> c.getCode().equals(code));
    }

    private void sortByName() {
        customers.sort(Comparator.comparing(Customer::getName));
    }

    public ArrayList<Customer> getAll() {
        return customers;
    }

    public Customer findByCode(String code) {
        for (Customer c : customers) {
            if (c.getCode().equals(code)) return c;
        }
        return null;
    }
}
