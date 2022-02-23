package Service;

import Model.User;
import Utill.FileHelper;
import Utill.SortIdUser;
import Utill.SortUserNameASC;

import java.io.IOException;
import java.util.*;

public class ServiceUser implements IServiceUser {
    static final String PATH_FILE = "src/Data/user.csv";
    static final String PATH_LOCK_USER_FILE = "src/Data/userLock.csv";

    public List<User> getServiceUser() {
        List<String> records = FileHelper.readFile(PATH_FILE);
        List<User> userList = new ArrayList<>();
        for (String record : records) {
            userList.add(new User(record));
        }
        return userList;
    }

    public List<User> getUserLock() {
        List<String> records = FileHelper.readFile(PATH_LOCK_USER_FILE);
        List<User> userList = new ArrayList<>();
        for (String record : records) {
            userList.add(new User(record));
        }
        return userList;
    }

    @Override
    public void add(User user) {
        List<User> userList = getServiceUser();
        userList.add(user);
        try {
            FileHelper.writeFile(PATH_FILE, userList);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public User edit(int id, User user) {
        List<User> userList = getServiceUser();
        int index = getIndexById(id);
        User editUser = userList.remove(index);
        userList.add(index, user);
        try {
            FileHelper.writeFile(PATH_FILE, userList);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return editUser;
    }

    @Override
    public User lockUser(String useName) {
        List<User> userList = getServiceUser();
        List<User> userListLock = getUserLock();
        for (User user : userList) {
            if (user.getUsername().equals(useName)) {
                User lockUser = user;
                userList.remove(user);
                userListLock.add(lockUser);
                try {
                    FileHelper.writeFile(PATH_LOCK_USER_FILE, userListLock);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                try {
                    FileHelper.writeFile(PATH_FILE, userList);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                return lockUser;
            }
        }

        return null;

    }

    @Override
    public User unlockUser(String userName) {
        List<User> userList = getServiceUser();
        List<User> userLockList = getUserLock();
        for (User user: userLockList) {
            if(user.getUsername().equals(userName)) {
                userList.add(user);
                userLockList.remove(user);
                try {
                    FileHelper.writeFile(PATH_FILE,userList);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                try {
                    FileHelper.writeFile(PATH_LOCK_USER_FILE,userLockList);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                return user;
            }
        }
        return null;
    }

    @Override
    public boolean checkDuplicateID(int id) {
        List<User> userList = getServiceUser();
        List<User> userLock = getUserLock();
        for (User user : userLock) {
            if (user.getId() == id) {
                return true;
            }
        }
        for (User user : userList) {
            if (user.getId() == id) {
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean checkDuplicateUserName(String userName) {
        List<User> userList = getServiceUser();
        List<User> userLock = getUserLock();
        for (User user : userLock) {
            if (user.getUsername().equals(userName)) {
                return true;
            }
        }
        for (User user : userList) {
            if (user.getUsername().equals(userName)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean checkDuplicateName(String name) {
        List<User> userList = getServiceUser();
        List<User> userLock = getUserLock();
        for (User user : userLock) {
            if (user.getName().equals(name)) {
                return true;
            }
        }
        for (User user : userList) {
            if (user.getName().equals(name)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean checkDuplicateEmail(String email) {
        List<User> userList = getServiceUser();
        List<User> userLock = getUserLock();
        for (User user : userLock) {
            if (user.getEmail().equals(email)) {
                return true;
            }
        }
        for (User user : userList) {
            if (user.getEmail().equals(email)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean checkDuplicatePhone(String phone) {
        List<User> userList = getServiceUser();
        List<User> userLock = getUserLock();
        for (User user : userLock) {
            if (user.getPhone().equals(phone)) {
                return true;
            }
        }
        for (User user : userList) {
            if (user.getPhone().equals(phone)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public User getByID(int id) {
        List<User> userList = getServiceUser();
        for (User user : userList) {
            if (user.getId() == id) {
                return user;
            }
        }
        return null;
    }


    @Override
    public int getIndexById(int id) {
        for (int i = 0; i < getServiceUser().size(); i++) {
            if (getServiceUser().get(i).getId() == id) {
                return i;
            }
        }
        return -1;
    }

    @Override
    public User getUserByUserName(String userName) {
        List<User> userList = getServiceUser();
        for (User user: userList) {
            if (user.getUsername().equals(userName)){
                return user;
            }
        }
        return null;
    }

    @Override
    public List<User> sortById() {
        List<User> userList = getServiceUser();
        userList.sort(new SortIdUser());
        return userList;
    }

    @Override
    public List<User> sortByUserName() {
        List<User> userList = getServiceUser();
        Collections.sort(userList, new SortUserNameASC());
        return userList;
    }

    @Override
    public List<User> searchUserByName(String name) {
        List<User> userList = getServiceUser();
        List<User> searchList = new ArrayList<>();
        for (User user : userList) {
            if (user.getName().toLowerCase().contains(name.toLowerCase())) {
                searchList.add(user);
            }
        }
        return searchList;
    }

    @Override
    public boolean checkAccount(String userName, String passWord) {
        List<User> userList = getServiceUser();
        for (User user : userList) {
            if (user.getUsername().equals(userName) && user.getPassword().equals(passWord)) {
                return true;
            }
        }
        return false;
    }
}
