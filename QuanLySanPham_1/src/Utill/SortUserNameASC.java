package Utill;

import Model.User;

import java.util.Comparator;

public class SortUserNameASC implements Comparator<User> {
    @Override
    public int compare(User o1, User o2) {
        return Integer.compare(o1.getUsername().compareTo(o2.getUsername()), 0);

    }
}
