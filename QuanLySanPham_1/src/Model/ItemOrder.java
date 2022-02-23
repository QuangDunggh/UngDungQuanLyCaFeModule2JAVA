package Model;

import java.util.List;

public class ItemOrder {
    private int id;
    private String name;
    private int price;
    private int quantity;
    private int total;

    public ItemOrder() {
    }

    public ItemOrder(int id, String name, int price, int quantity, int total) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.quantity = quantity;
        this.total = total;
    }

    public ItemOrder(String record) {
        String[] fields = record.split(",");
        this.id = Integer.parseInt(fields[0]);
        this.name = fields[1];
        this.price = Integer.parseInt(fields[2]);
        this.quantity = Integer.parseInt(fields[3]);
        this.total = Integer.parseInt(fields[4]);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    @Override
    public String toString() {
        return id + "," + name + "," + price + "," + quantity + "," + total;
    }
}
