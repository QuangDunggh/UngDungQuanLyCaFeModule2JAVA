package Service;

import Model.User;

import java.util.List;

public interface IServiceUser {
    void add(User user);
    User edit(int id,User user);
    User lockUser(String useName);
    User unlockUser(String userName);
    boolean checkDuplicateID(int id);
    boolean checkDuplicateUserName(String userName);
    boolean checkDuplicateName(String name);
    boolean checkDuplicateEmail(String email);
    boolean checkDuplicatePhone(String phone);
    User getByID(int id);
    int getIndexById(int id);
    User getUserByUserName(String userName);
    List<User> sortById();
    List<User> sortByUserName();
    List<User> searchUserByName(String name);
    boolean checkAccount(String userName, String passWord);
}
