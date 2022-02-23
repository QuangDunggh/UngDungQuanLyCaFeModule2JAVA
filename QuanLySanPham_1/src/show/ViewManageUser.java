package show;

import Model.Role;
import Model.User;
import Service.ServiceUser;
import Utill.ValidateUtils;

import java.util.List;
import java.util.Scanner;

public class ViewManageUser {
    Scanner scanner = new Scanner(System.in);
    private static final ServiceUser serviceUserList = new ServiceUser();

    public void showMenu() {
        int option = -1;
        do {
            try {
                menuManageUser();
                System.out.print("Chọn chức năng bạn muốn làm: ");
                option = Integer.parseInt(scanner.nextLine());
                switch (option) {
                    case 1:
                        addUser();
                        break;
                    case 2:
                        editUser();
                        System.out.println("Nhấn phím bất kì để thoát ra");
                        String choice = scanner.nextLine();
                        break;
                    case 3:
                        lockUser();
                        System.out.println("Nhấn phím bất kì để thoát ra");
                        choice = scanner.nextLine();
                        break;
                    case 4:
                        unlockUser();
                        choice = scanner.nextLine();
                        break;
                    case 5:
                        showUser();
                        System.out.println("Nhấn phím bất kì để thoát ra");
                        choice = scanner.nextLine();
                        break;
                    case 6:
                        showLockUser();
                        System.out.println("Nhấn phím bất kì để thoát ra");
                        choice = scanner.nextLine();
                        break;
                    case 7:
                        searchList();
                        System.out.println("Nhấn phím bất kì để thoát ra");
                        choice = scanner.nextLine();
                        break;
                    case 8:
                        sortList();
                        System.out.println("Nhấn phím bất kì để thoát ra");
                        choice = scanner.nextLine();
                        break;
                    case 0:
                        break;
                    default:
                        System.out.println("Chọn sai chức năng, vui lòng chọn đúng chức năng!!!");
                        break;
                }

            } catch (Exception e) {
                System.out.println("Phím không hợp lệ, vui lòng chọn đúng!!!!");
            }

        } while (option != 0);
    }

    public void menuManageUser() {
        System.out.println("**************************************");
        System.out.println("* 1. Thêm người dùng                 *");
        System.out.println("* 2. Sửa thông tin người dùng        *");
        System.out.println("* 3. Khóa người dùng                 *");
        System.out.println("* 4. Mở Khóa người dùng              *");
        System.out.println("* 5. Hiển thị tất cả người dùng      *");
        System.out.println("* 6. Hiển thị người dùng bị khóa     *");
        System.out.println("* 7. Tìm kiếm theo tên người dùng    *");
        System.out.println("* 8. Sắp xếp theo tên đăng nhập      *");
        System.out.println("* 0. Quay lại                        *");
        System.out.println("**************************************");
    }

    public void addUser() {
        int id = 0;
        int option = -1;
        System.out.println("Nhập 1 nếu bạn muốn thêm sản phẩm, nhập 2 nếu bạn muốn quay lại!!!");
        option = Integer.parseInt(scanner.nextLine());
        switch (option) {
            case 1:
                do {
                    do {
                        id = getId();
                        if (serviceUserList.checkDuplicateID(id)) {
                            System.out.println("Id đã tồn tại, vui lòng nhập lại!!!");
                        }
                    } while (serviceUserList.checkDuplicateID(id));
                    String userName;
                    do {
                        userName = getUseName(1);
                        if (serviceUserList.checkDuplicateUserName(userName)) {
                            System.out.println("Tên đăng nhập đã tồn tại, vui lòng nhập lại");
                        }
                    } while (serviceUserList.checkDuplicateUserName(userName));
                    String passWord = getPassWord(1);
                    String name;
                    do {
                        name = getName(1);
                        if (serviceUserList.checkDuplicateName(name)) {
                            System.out.println("Tên đã tồn tại, vui lòng nhập lại");
                        }
                    } while (serviceUserList.checkDuplicateName(name));
                    String phone;
                    do {
                        phone = getPhone(1);
                        if (serviceUserList.checkDuplicatePhone(phone)) {
                            System.out.println("Số điện thoại đã tồn tại, vui lòng nhập lại");
                        }
                    } while (serviceUserList.checkDuplicatePhone(phone));

                    String email;
                    do {
                        email = getEmail(1);
                        if (serviceUserList.checkDuplicateEmail(email)) {
                            System.out.println("Địa chỉ email đã tồn tại, vui lòng nhập lại");
                        }
                    } while (serviceUserList.checkDuplicateEmail(email));
                    System.out.print("Nhập địa chỉ người dùng: ");
                    String address = scanner.nextLine();
                    User newUser = new User();
                    newUser.setName(name);
                    newUser.setId(id);
                    newUser.setUsername(userName);
                    newUser.setAddress(address);
                    newUser.setEmail(email);
                    newUser.setPhone(phone);
                    newUser.setPassword(passWord);
                    setRole(newUser);
                    serviceUserList.add(newUser);
                    System.out.println("Đã thêm vào thành công!!");
                    System.out.println("Nhập 1 để tiếp tục thêm sản phẩm, nhập 2 để thoát!!!");
                    option = Integer.parseInt(scanner.nextLine());
                } while (option != 2);
                break;
            case 2:
                break;
            default:
                System.out.println("Nhập không đúng chức năng, vui lòng nhập lại");
                break;
        }

    }

    public void editUser() {
        int id = 0;
        int option = -1;
        System.out.println("Nhập 1 nếu bạn muốn Sửa sản phẩm, nhâp 2 nếu bạn muốn quay lại!!!");
        option = Integer.parseInt(scanner.nextLine());
     switch (option){
         case 1:
             do {
//                 System.out.println("Nhập 1 nếu bạn muốn thêm sản phẩm, nhâp 2 nếu bạn muốn quay lại!!!");
                 do {
                     try {
                         System.out.print("Nhập id sản phẩm bạn muốn sửa: ");
                         id = Integer.parseInt(scanner.nextLine());
                         if (!serviceUserList.checkDuplicateID(id)) {
                             System.out.println("Id chưa tồn tại, vui lòng nhập lại!!!");
                         }
                     } catch (Exception e) {
                         System.out.println("Nhập không đúng kiểu dự liệu, vui lòng nhập lại!!!");
                     }

                 } while (id < 0 || !serviceUserList.checkDuplicateID(id));

                 String useName;
                 do {
                     useName = getUseName(2);
                     if (serviceUserList.checkDuplicateUserName(useName)) {
                         System.out.println("Tên đăng nhập đã tồn tại, vui lòng nhập lại");
                     }
                 } while (serviceUserList.checkDuplicateUserName(useName));
                 String passWord = getPassWord(2);
                 String name;
                 do {
                     name = getName(2);
                     if (serviceUserList.checkDuplicateName(name)) {
                         System.out.println("Tên đã tồn tại, vui lòng nhập lại");
                     }
                 } while (serviceUserList.checkDuplicateName(name));
                 String phone;
                 do {
                     phone = getPhone(2);
                     if (serviceUserList.checkDuplicatePhone(phone)) {
                         System.out.println("Số điện thoại đã tồn tại, vui lòng nhập lại");
                     }
                 } while (serviceUserList.checkDuplicatePhone(phone));

                 String email;
                 do {
                     email = getEmail(2);
                     if (serviceUserList.checkDuplicateEmail(email)) {
                         System.out.println("Địa chỉ email đã tồn tại, vui lòng nhập lại");
                     }
                 } while (serviceUserList.checkDuplicateEmail(email));
                 System.out.print("Nhập lại địa chỉ: ");
                 String address = scanner.nextLine();
                 User editUser = serviceUserList.getByID(id);
                 editUser.setName(name);
                 editUser.setUsername(useName);
                 editUser.setAddress(address);
                 editUser.setEmail(email);
                 editUser.setPhone(phone);
                 editUser.setPassword(passWord);
                 User u1 = serviceUserList.edit(id, editUser);
                 System.out.println("Sản phẩm bạn muốn sửa có ID là: " + u1.getId());
                 System.out.println("Nhập 1 để tiếp tục thêm sản phẩm, nhập 2 để thoát!!!");
                 option = Integer.parseInt(scanner.nextLine());
                 System.out.println("Đã sữa thành công!!!");
             } while (option != 2);

             break;
         case 2:
             break;
         case 3:
             System.out.println("Chọn sai chức năng!!!");
         default:
     }

    }

    private String getEmail(int flag) {
        String email;
        do {
            if (flag == 1) {
                System.out.print("Nhập địa chỉ email bạn muốn thêm: ");
            }
            if (flag == 2) {
                System.out.print("Nhập địa chỉ email mới bạn muốn sửa: ");
            }
            email = scanner.nextLine();
            if (!ValidateUtils.isEmailValid(email)) {
                System.out.println("Email không đúng định dạng, vui lòng nhập lại!!!");
            }
        } while (!ValidateUtils.isEmailValid(email));

        return email;
    }

    private String getPhone(int flag) {
        String phone;
        do {
            if (flag == 1) {
                System.out.print("Nhập số điện thoại: ");
            }
            if (flag == 2) {
                System.out.print("Nhập số điện thoại mới bạn muốn sửa: ");
            }

            phone = scanner.nextLine();
            if (!ValidateUtils.isPhoneValid(phone)) {
                System.out.println("Số điện thoại không đúng định dạng, vui lòng nhập lại!!!");
            }
        } while (!ValidateUtils.isPhoneValid(phone));

        return phone;
    }

    private String getName(int flag) {
        String name;
        do {
            if (flag == 1) {
                System.out.print("Nhập tên người dùng bạn muốn thêm: ");
            }
            if (flag == 2) {
                System.out.print("Nhập tên người dùng mới bạn muốn sửa: ");
            }
            name = scanner.nextLine();
            if (!ValidateUtils.isNameValid(name)) {
                System.out.println("Tên không đúng định dạng, vui lòng nhập lại!!!");
            }
        } while (!ValidateUtils.isNameValid(name));

        return name;
    }

    private String getPassWord(int flag) {
        String passWord;
        do {
            if (flag == 1) {
                System.out.print("Nhập mật khẩu của bạn: ");
            }
            if (flag == 2) {
                System.out.print("Nhập mật khẩu mới bạn muốn sửa: ");
            }
            passWord = scanner.nextLine();
            if (!ValidateUtils.isPasswordValid(passWord)) {
                System.out.println("Mật khẩu như Tùng, quá yếu, vui lòng nhập lại!!!");
            }
        } while (!ValidateUtils.isPasswordValid(passWord));
        return passWord;
    }

    private String getUseName(int flag) {
        String useName;
        do {
            if (flag == 1) {
                System.out.print("Nhập tên đăng nhập bạn muốn thêm: ");
            }
            if (flag == 2) {
                System.out.print("Nhập tên đăng nhập mới bạn muốn sửa: ");
            }
            useName = scanner.nextLine();
            if (!ValidateUtils.isNameValid(useName)) {
                System.out.println("Tên không đúng định dạng, vui lòng nhập lại!!!");
            }
        } while (!ValidateUtils.isNameValid(useName));
        return useName;
    }

    private int getId() {
        int id = 0;
        do {
            try {
                System.out.print("Nhập Id người dùng: ");
                id = Integer.parseInt(scanner.nextLine());
                if (id < 0) {
                    System.out.println("Id phải lớn hơn 0!!!");
                }
            } catch (Exception e) {
                System.out.println("Nhập không đứng kiểu dữ liệu, vui lòng nhập lại!!!");
            }
        } while (id < 1);
        return id;
    }

    public void setRole(User user) {
        int option = 0;
        do {
            try {
                System.out.println("= = SET ROLE = =");
                System.out.println("∥   1. USER    ∥");
                System.out.println("∥   2. ADMIN   ∥");
                System.out.println("= = = =  = = = = ");
                System.out.println("Chọn Role: ");
                System.out.print(" ⭆ ");
                option = Integer.parseInt(scanner.nextLine());
                switch (option) {
                    case 1:
                        user.setRole(Role.USER);
                        break;
                    case 2:
                        user.setRole(Role.ADMIN);
                        break;
                    default:
                        System.out.println("Nhập không đúng! Vui lòng nhập lại");
                        break;
                }
            } catch (Exception e) {
                System.out.println("Nhập không đúng kiểu dữ liệu, nhập lại!!!");
            }
        } while (option != 1 && option != 2);

    }

    public void showUser() {
        List<User> userList = serviceUserList.sortById();
        System.out.println("----------------------------------------------------------------------------------------------------------------------");
        System.out.println("|                                               THÔNG TIN NGƯỜI DÙNG                                                 |");
        System.out.println("----------------------------------------------------------------------------------------------------------------------");
        System.out.printf("%-5s%-17s%-18s%-16s%-15s%-25s%-10s%-5s\n", "ID",
                "User Name", "Password", "Name", "Phone", "Email", "Address", "Role");
        for (User user : userList) {
            System.out.printf("%-5d%-17s%-18s%-16s%-15s%-25s%-10s%-5s\n", user.getId(),
                    user.getUsername(), user.getPassword(), user.getName(), user.getPhone(),
                    user.getEmail(), user.getAddress(), user.getRole());
        }
        System.out.println("----------------------------------------------------------------------------------------------------------------------");
    }

    private void searchList() {
        System.out.print("Nhập tên người dùng bạn muốn tìm kiếm: ");
        String searchName = scanner.nextLine();
        List<User> userList = serviceUserList.searchUserByName(searchName);
        System.out.println("----------------------------------------------------------------------------------------------------------------------");
        System.out.println("|                                               KẾT QUẢ TÌM KIẾM                                                     |");
        System.out.println("----------------------------------------------------------------------------------------------------------------------");
        System.out.printf("%-5s%-17s%-18s%-16s%-15s%-25s%-10s%-5s\n", "ID",
                "User Name", "Password", "Name", "Phone", "Email", "Address", "Role");
        for (User user : userList) {
            System.out.printf("%-5d%-17s%-18s%-16s%-15s%-25s%-10s%-5s\n", user.getId(),
                    user.getUsername(), user.getPassword(), user.getName(), user.getPhone(),
                    user.getEmail(), user.getAddress(), user.getRole());
        }
        System.out.println("----------------------------------------------------------------------------------------------------------------------");
    }

    private void sortList() {
        List<User> userList = serviceUserList.sortByUserName();
        System.out.println("----------------------------------------------------------------------------------------------------------------------");
        System.out.println("|                                               THÔNG TIN NGƯỜI DÙNG                                                 |");
        System.out.println("----------------------------------------------------------------------------------------------------------------------");
        System.out.printf("%-5s%-17s%-18s%-16s%-15s%-25s%-10s%-5s\n", "ID",
                "User Name", "Password", "Name", "Phone", "Email", "Address", "Role");
        for (User user : userList) {
            System.out.printf("%-5d%-17s%-18s%-16s%-15s%-25s%-10s%-5s\n", user.getId(),
                    user.getUsername(), user.getPassword(), user.getName(), user.getPhone(),
                    user.getEmail(), user.getAddress(), user.getRole());
        }
        System.out.println("----------------------------------------------------------------------------------------------------------------------");

    }

    private void lockUser() {
        String userName;
        do {
            System.out.print("Nhập tên đăng nhập người dùng bạn muốn khóa: ");
            userName = scanner.nextLine();
            if (!serviceUserList.checkDuplicateUserName(userName)) {
                System.out.println("Tên đăng nhập người dùng không tồn tại, vui lòng nhập lại!!!");
            }
        } while (!serviceUserList.checkDuplicateUserName(userName));
        int option = 0;
        do {
            try {
                System.out.println("Bạn có muốn khóa người dùng có tên đăng nhập:" + userName + " không?");
                System.out.println("Nhập '1' nếu bạn chắc chắn muốn khóa, nhập '2' để quay lại");
                option = Integer.parseInt(scanner.nextLine());
                switch (option) {
                    case 1:
                        User userLock = serviceUserList.lockUser(userName);
                        System.out.println("----------------------------------------------------------------------------------------------------------------------");
                        System.out.println("|                                               THÔNG TIN NGƯỜI DÙNG BỊ KHÓA                                         |");
                        System.out.println("----------------------------------------------------------------------------------------------------------------------");
                        System.out.printf("%-5s%-17s%-18s%-16s%-15s%-25s%-10s%-5s\n", "ID",
                                "User Name", "Password", "Name", "Phone", "Email", "Address", "Role");
                        System.out.printf("%-5d%-17s%-18s%-16s%-15s%-25s%-10s%-5s\n", userLock.getId(),
                                userLock.getUsername(), userLock.getPassword(), userLock.getName(), userLock.getPhone(),
                                userLock.getEmail(), userLock.getAddress(), userLock.getRole());
                        System.out.println("----------------------------------------------------------------------------------------------------------------------");
                        System.out.println("Bạn đã khóa thành công!!!");
                        break;
                    case 2:
                        break;
                    default:
                        System.out.println("Chọn không đúng chức năng, chọn lại");
                        break;
                }
            } catch (Exception e) {
                System.out.println("Nhập sai dữ liệu, mời nhập lại!!!!");
            }
        } while (option != 2 && option != 1);


    }

    private void unlockUser() {
        String userName;
        List<User> userListLock = serviceUserList.getUserLock();
        if (userListLock.size() == 0) {
            System.out.println("Hiện không có tài khoản nào bị khóa");
            System.out.println("Nhấn phím bất kì để quay lại!!!");
            return;
        }
        do {
            System.out.print("Nhập tên đăng nhập người dùng bạn muốn mở khóa: ");
            userName = scanner.nextLine();
            if (!serviceUserList.checkDuplicateUserName(userName)) {
                System.out.println("Tên đăng nhập này chưa tồn tại, vui lòng nhập lại tên khác!!!");
            }
            if (!ValidateUtils.isNameValid(userName)) {
                System.out.println("Nhập không đúng định dạng tên đăng nhập, vui lòng nhập lại");
            }
        } while (!ValidateUtils.isNameValid(userName) || !serviceUserList.checkDuplicateUserName(userName));

        int option = 0;
        do {
            try {
                System.out.println("Bạn có muốn mở khóa người dùng có tên đăng nhập:" + userName + " không?");
                System.out.println("Nhập '1' nếu bạn chắc chắn muốn mở khóa, nhập '2' để quay lại");
                option = Integer.parseInt(scanner.nextLine());
                switch (option) {
                    case 1:
                        User userUnlock = serviceUserList.unlockUser(userName);
                        System.out.println("----------------------------------------------------------------------------------------------------------------------");
                        System.out.println("|                                         THÔNG TIN NGƯỜI DÙNG ĐƯỢC MỞ KHÓA                                          |");
                        System.out.println("----------------------------------------------------------------------------------------------------------------------");
                        System.out.printf("%-5s%-17s%-18s%-16s%-15s%-25s%-10s%-5s\n", "ID",
                                "User Name", "Password", "Name", "Phone", "Email", "Address", "Role");
                        System.out.printf("%-5d%-17s%-18s%-16s%-15s%-25s%-10s%-5s\n", userUnlock.getId(),
                                userUnlock.getUsername(), userUnlock.getPassword(), userUnlock.getName(), userUnlock.getPhone(),
                                userUnlock.getEmail(), userUnlock.getAddress(), userUnlock.getRole());
                        System.out.println("----------------------------------------------------------------------------------------------------------------------");
                        System.out.println("Bạn đã mở khóa thành công!!!");
                        break;
                    case 2:
                        break;
                    default:
                        System.out.println("Chọn không đúng chức năng, chọn lại");
                        break;
                }
            } catch (Exception e) {
                System.out.println("Nhập sai dữ liệu, mời nhập lại!!!!");
            }
        } while (option != 2 && option != 1);
    }

    private void showLockUser() {
        List<User> userList = serviceUserList.getUserLock();
        if(userList.size() == 0) {
            System.out.println("Hiện không có người dùng nào bị khóa!!!");
            return;
        }
        System.out.println("----------------------------------------------------------------------------------------------------------------------");
        System.out.println("|                                           THÔNG TIN NGƯỜI DÙNG BỊ KHÓA                                              |");
        System.out.println("----------------------------------------------------------------------------------------------------------------------");
        System.out.printf("%-5s%-17s%-18s%-16s%-15s%-25s%-10s%-5s\n", "ID",
                "User Name", "Password", "Name", "Phone", "Email", "Address", "Role");
        for (User user : userList) {
            System.out.printf("%-5d%-17s%-18s%-16s%-15s%-25s%-10s%-5s\n", user.getId(),
                    user.getUsername(), user.getPassword(), user.getName(), user.getPhone(),
                    user.getEmail(), user.getAddress(), user.getRole());
        }
        System.out.println("----------------------------------------------------------------------------------------------------------------------");
    }
}

