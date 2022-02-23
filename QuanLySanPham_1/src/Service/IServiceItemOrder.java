package Service;

import Model.ItemOrder;
import Model.User;

import java.util.List;


public interface IServiceItemOrder {
    void add(ItemOrder itemOrder);
    ItemOrder remove(String name);
    boolean checkDuplicateID(int id);
    boolean checkDuplicateName(String name);
    ItemOrder getByID(int id);
    int getIndexById(int id);
    ItemOrder getByName(String name);
    List<ItemOrder> payment();
    List<ItemOrder> sortByIdASC();


}
