package Model;

import java.util.List;

public class DrinkWater {
    private int id;
    private String name;
    private int price;
    private int quantity;

    public DrinkWater() {
    }

    public DrinkWater(int id, String name, int price, int quantity) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.quantity = quantity;
    
    }
    public DrinkWater(String record) {
        String[] filed = record.split(",");
        this.id = Integer.parseInt(filed[0]);
        this.name = filed[1];
        this.price = Integer.parseInt(filed[2]);
        this.quantity = Integer.parseInt(filed[3]);
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

    @Override
    public String toString() {
        return id + "," + name + "," + price + "," + quantity;
    }
}
