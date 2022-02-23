package Utill;

import Model.ItemOrder;

import java.util.Comparator;

public class SortIdItemOrderASC implements Comparator<ItemOrder> {
    @Override
    public int compare(ItemOrder o1, ItemOrder o2) {
        if(o1.getId() - o2.getId() < 0) {
            return -1;
        } else if(o1.getId() - o2.getId() > 0) {
            return 1;
        } else {
            return 0;
        }
    }
}
