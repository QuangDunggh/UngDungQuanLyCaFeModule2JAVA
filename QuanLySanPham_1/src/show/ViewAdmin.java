package show;

import Service.ServiceUser;

import java.util.Scanner;

public class ViewAdmin {
    Scanner scanner = new Scanner(System.in);
    private static final ViewManageProduct VIEW_MANAGE_PRODUCT = new ViewManageProduct();
    private static final ViewManageUser VIEW_MANAGE_USER = new ViewManageUser();
    private static final ViewOrderItem VIEW_ORDER_ITEM = new ViewOrderItem();

    public void showMenu() {
        int option = -1;
        do {
            try {
                menu();
                System.out.print("Chọn lựa chọn bạn muốn làm: ");
                option = Integer.parseInt(scanner.nextLine());
                switch (option) {
                    case 1:
                        VIEW_MANAGE_USER.showMenu();
                        break;
                    case 2:
                        VIEW_MANAGE_PRODUCT.showMenu();
                        break;
                    case 3:
                        VIEW_ORDER_ITEM.showMenu();
                        break;
                    case 0:
                        System.out.println("Chào tạm biệt và hẹn gặp lại!!!");
                        System.exit(0);
                        break;
                    default:
                        System.out.println("Nhập sai lựa chọn, vui lòng nhập lại!!!");
                        break;
                }
            } catch (Exception e) {
                e.printStackTrace();
                System.out.println("Nhập không đúng kiểu dữ liệu, vui lòng nhập lại!!!");
            }
        } while (option != 0);
    }

    public void menu() {
        System.out.println("***************************");
        System.out.println("* 1. Quản lý người dùng   *");
        System.out.println("* 2. Quản lý sản phẩm     *");
        System.out.println("* 3. Order sản phẩm       *");
        System.out.println("* 0. Thoát                *");
        System.out.println("***************************");
    }


}
