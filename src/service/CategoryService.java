package service;

import model.Category;
import java.util.*;

public class CategoryService {
    private ArrayList<Category> categories = new ArrayList<>();
    private Scanner sc = new Scanner(System.in);

    public void menu() {
        while (true) {
            System.out.println("\n=== QUẢN LÝ CHỦNG LOẠI ===");
            System.out.println("1. Xem danh sách");
            System.out.println("2. Thêm chủng loại");
            System.out.println("3. Sửa chủng loại");
            System.out.println("4. Xóa chủng loại");
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
        System.out.println("\n-- Danh sách chủng loại --");
        for (Category c : categories) {
            System.out.println(c);
        }
    }

    private void add() {
        System.out.print("ID: ");
        String id = sc.nextLine();
        System.out.print("Tên: ");
        String name = sc.nextLine();
        categories.add(new Category(id, name));
        System.out.println("Đã thêm chủng loại.");
    }

    private void edit() {
        System.out.print("Nhập ID cần sửa: ");
        String id = sc.nextLine();
        for (Category c : categories) {
            if (c.getId().equals(id)) {
                System.out.print("Tên mới: ");
                c.setName(sc.nextLine());
                System.out.println("Đã cập nhật.");
                return;
            }
        }
        System.out.println("Không tìm thấy chủng loại.");
    }

    private void delete() {
        System.out.print("Nhập ID cần xóa: ");
        String id = sc.nextLine();
        categories.removeIf(c -> c.getId().equals(id));
        System.out.println("Đã xóa nếu tồn tại.");
    }

    private void sortByName() {
        categories.sort(Comparator.comparing(Category::getName));
        System.out.println("Đã sắp xếp.");
    }

    public ArrayList<Category> getAll() {
        return categories;
    }

    public Category findById(String id) {
        for (Category c : categories) {
            if (c.getId().equals(id)) return c;
        }
        return null;
    }
}