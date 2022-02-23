package show;

import Model.Role;
import Model.User;
import Service.ServiceUser;

import java.util.Scanner;

public class ViewLogin {
    static  final ServiceUser serviceUser = new ServiceUser();
    static final ViewManageProduct VIEW_MANAGE_PRODUCT = new ViewManageProduct();
    static final ViewAdmin VIEW_ADMIN = new ViewAdmin();
    static final ViewOrderItem VIEW_ORDER_ITEM = new ViewOrderItem();
    Scanner scanner = new Scanner(System.in);
    public void login() {
        System.out.println("**************************************************");
        System.out.println("|                   COKECOLE                     |");
        System.out.println("**************************************************");
        String useName;
        String passWord;
        do {
            System.out.println("Nhập tên đăng nhập: ");
            System.out.print("=> ");
            useName = scanner.nextLine();
            System.out.println("Nhập mật khẩu: ");
            System.out.print("=> ");
            passWord = scanner.nextLine();
            if (serviceUser.checkAccount(useName,passWord)) {
                System.out.println("Chào mừng bạn đến với cửa hàng COKECOLA");
            } else {
                System.out.println("Nhập sai, vui lòng nhập lại");
            }
        } while (!serviceUser.checkAccount(useName,passWord));

       User userLogin = serviceUser.getUserByUserName(useName);
       if(userLogin.getRole().equals(Role.ADMIN)) {
           VIEW_ADMIN.showMenu();
       } else if(userLogin.getRole().equals(Role.USER)) {
           VIEW_ORDER_ITEM.showMenu();
       }

    }



}
