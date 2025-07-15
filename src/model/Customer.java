package model;

public class Customer {
    private String code;
    private String id;
    private String name;
    private String phone;

    public Customer(String code, String id, String name, String phone) {
        this.code = code;
        this.id = id;
        this.name = name;
        this.phone = phone;
    }

    public String getCode() { return code; }
    public String getId() { return id; }
    public String getName() { return name; }
    public String getPhone() { return phone; }

    public void setCode(String code) { this.code = code; }
    public void setName(String name) { this.name = name; }
    public void setPhone(String phone) { this.phone = phone; }

    @Override
    public String toString() {
        return code + " | " + id + " | " + name + " | " + phone;
    }
}
