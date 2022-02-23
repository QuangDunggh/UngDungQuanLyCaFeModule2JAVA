package Service;

import Model.DrinkWater;
import Utill.FileHelper;
import Utill.SortDrinkWaterById;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class ServiceDrinkWater implements IServiceDrinkWater {

    private static final String PATH_FILE = "src/Data/drinkWater.csv";
    private static final String PATH_FILE_LOCK_PRODUCT = "src/Data/drinkWaterLock.csv";

    @Override
    public List<DrinkWater> getDrinkWater() {
        List<DrinkWater> drinkWaterList = new ArrayList<>();
        List<String> drinkWaterPath = FileHelper.readFile(PATH_FILE);
        for (String record : drinkWaterPath) {
            drinkWaterList.add(new DrinkWater(record));
        }
        return drinkWaterList;
    }

    public List<DrinkWater> getLockDrinkWater() {
        List<DrinkWater> drinkWaterList = new ArrayList<>();
        List<String> drinkWaterPath = FileHelper.readFile(PATH_FILE_LOCK_PRODUCT);
        for (String record : drinkWaterPath) {
            drinkWaterList.add(new DrinkWater(record));
        }
        return drinkWaterList;
    }

    @Override
    public void add(DrinkWater drinkWater) {
        List<DrinkWater> drinkWaterList = getDrinkWater();
        drinkWaterList.add(drinkWater);
        try {
            FileHelper.writeFile(PATH_FILE, drinkWaterList);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public DrinkWater getById(int id) {
        for (DrinkWater drinkWater : getDrinkWater()) {
            if (drinkWater.getId() == id) {
                return drinkWater;
            }
        }
        return null;

    }

    @Override
    public void edit(int id, DrinkWater drinkWater) {
        int index = getIndexById(id);
        List<DrinkWater> drinkWaterList = getDrinkWater();
        drinkWaterList.remove(index);
        drinkWaterList.add(index, drinkWater);
        try {
            FileHelper.writeFile(PATH_FILE, drinkWaterList);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    @Override
    public int getIndexById(int id) {
        for (int i = 0; i < getDrinkWater().size(); i++) {
            if (id == getDrinkWater().get(i).getId()) {
                return i;
            }
        }
        return -1;
    }

    @Override
    public DrinkWater lockDrinkWater(int id) {
        int index = getIndexById(id);
        List<DrinkWater> drinkWaterList = getDrinkWater();
        List<DrinkWater> drinkWaterListLock = getLockDrinkWater();
        drinkWaterListLock.add(drinkWaterList.get(index));
        DrinkWater removeProduct = drinkWaterList.remove(index);
        try {
            FileHelper.writeFile(PATH_FILE_LOCK_PRODUCT, drinkWaterListLock);
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            FileHelper.writeFile(PATH_FILE, drinkWaterList);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return removeProduct;
    }

    @Override
    public DrinkWater unlockDrinkWater(int id) {
        List<DrinkWater> drinkWaterList = getDrinkWater();
        List<DrinkWater> drinkWaterListLock = getLockDrinkWater();
        DrinkWater unlockDrinkWater = null;
        for (DrinkWater drinkWater : drinkWaterListLock) {
            if (drinkWater.getId() == id) {
                drinkWaterListLock.remove(drinkWater);
                unlockDrinkWater = drinkWater;
                break;
            }
        }
        drinkWaterList.add(unlockDrinkWater);
        try {
            FileHelper.writeFile(PATH_FILE_LOCK_PRODUCT, drinkWaterListLock);
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            FileHelper.writeFile(PATH_FILE, drinkWaterList);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return unlockDrinkWater;
    }

    @Override
    public DrinkWater remove(int id) {
        int index = getIndexById(id);
        List<DrinkWater> drinkWaterList = getDrinkWater();
        DrinkWater removeProduct = drinkWaterList.remove(index);
        try {
            FileHelper.writeFile(PATH_FILE, drinkWaterList);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return removeProduct;
    }

    @Override
    public boolean existId(int id) {
        for (DrinkWater drinkWater : getLockDrinkWater()) {
            if (drinkWater.getId() == id) {
                return true;
            }
        }
        for (DrinkWater drinkWater : getDrinkWater()) {
            if (drinkWater.getId() == id) {
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean checkDuplicateId(int id) {
        for (DrinkWater drinkWater : getDrinkWater()) {
            if (drinkWater.getId() == id) {
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean checkDuplicateName(String name) {
        for (DrinkWater drinkWater : getDrinkWater()) {
            if (drinkWater.getName().equals(name)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public List<DrinkWater> sortDrinkWater() {
        List<DrinkWater> drinkWaterList = getDrinkWater();
        drinkWaterList.sort(new SortDrinkWaterById());
        return drinkWaterList;
    }

    @Override
    public List<DrinkWater> searchByName(String name) {
        List<DrinkWater> drinkWaterList = getDrinkWater();
        List<DrinkWater> searchList = new ArrayList<>();
        for (DrinkWater drinkWater: drinkWaterList) {
            if(drinkWater.getName().toLowerCase().contains(name.toLowerCase())) {
                searchList.add(drinkWater);
            }
        }
        searchList.sort(new SortDrinkWaterById());
        return searchList;
    }
}
