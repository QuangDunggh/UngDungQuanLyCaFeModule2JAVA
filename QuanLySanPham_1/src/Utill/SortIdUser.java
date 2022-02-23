package Utill;

import Model.User;

import java.util.Comparator;

public class SortIdUser implements Comparator<User> {
    @Override
    public int compare(User o1, User o2) {
        if(o1.getId() - o2.getId() < 0) {
            return -1;
        } else if(o1.getId() - o2.getId() > 0) {
            return 1;
        } else {
            return 0;
        }
    }
}
