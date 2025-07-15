package service;

import model.*;
import java.util.*;

public class ProductService {
    private ArrayList<Product> products = new ArrayList<>();
    private Scanner sc = new Scanner(System.in);
    private CategoryService categoryService;

    public ProductService(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    public void menu() {
        while (true) {
            System.out.println("\n=== QUẢN LÝ SẢN PHẨM ===");
            System.out.println("1. Xem sản phẩm");
            System.out.println("2. Thêm sản phẩm");
            System.out.println("3. Sửa sản phẩm");
            System.out.println("4. Xóa sản phẩm");
            System.out.println("5. Sắp xếp theo tên");
            System.out.println("6. Tìm theo chủng loại");
            System.out.println("7. Tìm theo tên");
            System.out.println("0. Quay lại");
            System.out.print("Chọn: ");
            int choice = Integer.parseInt(sc.nextLine());
            switch (choice) {
                case 1 -> display();
                case 2 -> add();
                case 3 -> edit();
                case 4 -> delete();
                case 5 -> sortByName();
                case 6 -> findByCategory();
                case 7 -> findByName();
                case 0 -> { return; }
                default -> System.out.println("Sai lựa chọn.");
            }
        }
    }

    private void display() {
        for (Product p : products) {
            System.out.println(p);
        }
    }

    private void add() {
        System.out.print("ID: ");
        String id = sc.nextLine();
        System.out.print("Tên: ");
        String name = sc.nextLine();
        System.out.print("Giá: ");
        double price = Double.parseDouble(sc.nextLine());

        System.out.print("ID chủng loại: ");
        String catId = sc.nextLine();
        Category cat = categoryService.findById(catId);
        if (cat == null) {
            System.out.println("Chủng loại không tồn tại.");
            return;
        }
        products.add(new Product(id, name, cat, price));
        System.out.println("Đã thêm sản phẩm.");
    }

    private void edit() {
        System.out.print("ID sản phẩm: ");
        String id = sc.nextLine();
        for (Product p : products) {
            if (p.getId().equals(id)) {
                System.out.print("Tên mới: ");
                p.setName(sc.nextLine());
                System.out.print("Giá mới: ");
                p.setPrice(Double.parseDouble(sc.nextLine()));
                return;
            }
        }
        System.out.println("Không tìm thấy sản phẩm.");
    }

    private void delete() {
        System.out.print("ID cần xóa: ");
        String id = sc.nextLine();
        products.removeIf(p -> p.getId().equals(id));
    }

    private void sortByName() {
        products.sort(Comparator.comparing(Product::getName));
    }

    private void findByCategory() {
        System.out.print("ID chủng loại: ");
        String id = sc.nextLine();
        for (Product p : products) {
            if (p.getCategory().getId().equals(id)) {
                System.out.println(p);
            }
        }
    }

    private void findByName() {
        System.out.print("Từ khóa: ");
        String kw = sc.nextLine().toLowerCase();
        for (Product p : products) {
            if (p.getName().toLowerCase().contains(kw)) {
                System.out.println(p);
            }
        }
    }

    public ArrayList<Product> getAll() {
        return products;
    }

    public Product findById(String id) {
        for (Product p : products) {
            if (p.getId().equals(id)) return p;
        }
        return null;
    }
}