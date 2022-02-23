package Utill;

import Model.DrinkWater;

import java.util.Comparator;

public class SortDrinkWaterById implements Comparator<DrinkWater> {
    @Override
    public int compare(DrinkWater o1, DrinkWater o2) {
        if(o1.getId() - o2.getId() < 0) {
            return -1;
        } else if(o1.getId() - o2.getId() > 0) {
            return 1;
        } else {
            return 0;
        }
    }
}
