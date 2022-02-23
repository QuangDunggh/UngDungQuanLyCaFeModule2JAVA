package Service;

import Model.ItemOrder;
import Utill.FileHelper;
import Utill.SortIdItemOrderASC;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ServiceItemOrder implements IServiceItemOrder {
    static final String PATH_FILE_ITEM_ORDER = "src/Data/ItemOrder.csv";
    static final String PATH_FILE_PAYMENT = "src/Data/paidItem.csv";

    public List<ItemOrder> serviceItemOrder() {
        List<String> records = FileHelper.readFile(PATH_FILE_ITEM_ORDER);
        List<ItemOrder> itemOrders = new ArrayList<>();
        for (String record : records) {
            itemOrders.add(new ItemOrder(record));
        }
        return itemOrders;
    }

    @Override
    public void add(ItemOrder itemOrder) {
        List<ItemOrder> itemOrders = serviceItemOrder();
        itemOrders.add(itemOrder);
        try {
            FileHelper.writeFile(PATH_FILE_ITEM_ORDER, itemOrders);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public ItemOrder remove(String name) {
        List<ItemOrder> itemOrders = serviceItemOrder();
        ItemOrder itemOrderRemove = null;
        for (ItemOrder itemOrder : itemOrders) {
            if (itemOrder.getName().equals(name)) {
                itemOrderRemove = itemOrder;
                itemOrders.remove(itemOrder);
                break;
            }
        }
        try {
            FileHelper.writeFile(PATH_FILE_ITEM_ORDER, itemOrders);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return itemOrderRemove;

    }

    @Override
    public boolean checkDuplicateID(int id) {
        List<ItemOrder> itemOrders = serviceItemOrder();
        for (ItemOrder itemOrder : itemOrders) {
            if (itemOrder.getId() == id) {
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean checkDuplicateName(String name) {
        List<ItemOrder> itemOrders = serviceItemOrder();
        for (ItemOrder itemOrder : itemOrders) {
            if (itemOrder.getName().equals(name)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public ItemOrder getByID(int id) {
        List<ItemOrder> itemOrders = serviceItemOrder();
        for (ItemOrder itemOrder : itemOrders) {
            if (itemOrder.getId() == id) {
                return itemOrder;
            }
        }
        return null;
    }

    @Override
    public int getIndexById(int id) {
        List<ItemOrder> itemOrders = serviceItemOrder();
        for (int i = 0; i < itemOrders.size(); i++) {
            if (itemOrders.get(i).getId() == id) {
                return i;
            }
        }
        return -1;
    }

    @Override
    public ItemOrder getByName(String name) {
        return null;
    }

    @Override
    public List<ItemOrder> payment() {
        List<ItemOrder> itemOrders = new ArrayList<>();
        List<ItemOrder> payment = serviceItemOrder();
        try {
            FileHelper.writeFile(PATH_FILE_PAYMENT, payment);
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            FileHelper.writeFile(PATH_FILE_ITEM_ORDER, itemOrders);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return payment;
    }

    @Override
    public List<ItemOrder> sortByIdASC() {
        List<ItemOrder> itemOrders = serviceItemOrder();
        itemOrders.sort(new SortIdItemOrderASC());
        return itemOrders;
    }
}
