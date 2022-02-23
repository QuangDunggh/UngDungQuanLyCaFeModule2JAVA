package Service;

import Model.DrinkWater;

import java.util.List;

public interface IServiceDrinkWater {
    List<DrinkWater> getDrinkWater();
    void add(DrinkWater drinkWater);
    DrinkWater getById(int id);
    void edit(int id,DrinkWater drinkWater);
    int getIndexById(int id);
    DrinkWater lockDrinkWater(int id);
    DrinkWater unlockDrinkWater(int id);
    DrinkWater remove(int id);
    boolean existId(int id);
    boolean checkDuplicateId(int id);
    boolean checkDuplicateName(String name);
    List<DrinkWater> sortDrinkWater();
    List<DrinkWater> searchByName(String name);
}
