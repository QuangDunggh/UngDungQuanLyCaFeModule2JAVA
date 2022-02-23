package show;

import Model.DrinkWater;
import Service.ServiceDrinkWater;
import Utill.ValidateUtils;

import java.text.DecimalFormat;
import java.util.List;
import java.util.Objects;
import java.util.Scanner;

public class ViewManageProduct {
    private ServiceDrinkWater serviceDrinkWater = new ServiceDrinkWater();
    DecimalFormat format = new DecimalFormat("###,###,###" + "VND");
    Scanner scanner = new Scanner(System.in);

    public void showMenu() {
        int number = -1;
        do {
            try {
                menu();
                System.out.print("Chọn lựa chọn bạn muốn làm: ");
                number = Integer.parseInt(scanner.nextLine());
            } catch (Exception e) {
                System.out.println("Nhập không đúng kiểu dữ liệu, vui lòng nhập lại!!!");
            }
            switch (number) {
                case 1:
                    add();
                    System.out.println("Nhập phím bất kì để quay trở lại!!!");
                    String choice = scanner.nextLine();
                    break;
                case 2:
                    editDrinkWater();
                    break;
                case 3:
                    removeDrinkWater();
                    System.out.println("Nhập phím bất kì để quay trở lại!!!");
                    choice = scanner.nextLine();
                    break;
                case 4:
                    unlockDrinkWater();
                    System.out.println("Nhập phím bất kì để quay trở lại!!!");
                    choice = scanner.nextLine();
                    break;
                case 5:
                    showDrinkWater();
                    System.out.println("Nhập phím bất kì để quay trở lại!!!");
                    choice = scanner.nextLine();
                    break;
                case 6:
                    searchByName();
                    System.out.println("Nhập phím bất kì để quay trở lại!!!");
                    choice = scanner.nextLine();
                    break;
                case 0:
                    break;
                default:
                    System.out.println("Nhập sai, mời nhập lại!!!");
                    break;
            }
        } while (number != 0);
    }

    public void menu() {
        System.out.println("-----------------------------------");
        System.out.println("| 1. Thêm đồ uống                 |");
        System.out.println("| 2. Sửa đồ uống                  |");
        System.out.println("| 3. Ẩn đồ uống                   |");
        System.out.println("| 4. Mở khóa đồ uống              |");
        System.out.println("| 5. Hiển thị danh sách đồ uống   |");
        System.out.println("| 6. Tìm kiếm theo tên đồ uống    |");
        System.out.println("| 0. Quay lại                     |");
        System.out.println("-----------------------------------");
    }

    public void add() {
        boolean is = false;
        int id = 0;
        do {
            is = false;
            id = getId();
            if (serviceDrinkWater.checkDuplicateId(id)) {
                System.out.println("Id đã tồn tại, vui lòng nhập lại!");
                is = true;
            }
        } while (is);
        String name;
        do {
            is = false;
            System.out.print("Nhập tên sản phẩm bạn muốn thêm: ");
            name = scanner.nextLine();
            if (serviceDrinkWater.checkDuplicateName(name)) {
                System.out.println("Tên sản phẩm đã tồn tại, vui lòng nhập lại tên khác!");
                is = true;
            }
        } while (is);
        int price = getPrice();
        int quantity = getQuantity();
        DrinkWater newDrinkWater = new DrinkWater(id, name, price, quantity);
        serviceDrinkWater.add(newDrinkWater);
        System.out.println("Thêm sản phẩm thành công!!!");
    }

    private int getQuantity() {
        int quantity = -1;
        do {
            try {
                System.out.print("Nhập số lượng sản phẩm: ");
                quantity = Integer.parseInt(scanner.nextLine());
                if (quantity < 0) {
                    System.out.println("Số lượng phải lớn 0");
                }
            } catch (Exception e) {
                System.out.println("Nhập số lượng không hợp lệ, vui lòng nhập lại!");
            }
        } while (quantity < 0);

        return quantity;
    }

    private int getPrice() {
        int price = 0;
        do {
            try {
                System.out.print("Nhập giá sản phẩm: ");
                price = Integer.parseInt(scanner.nextLine());
                if (price < 10000) {
                    System.out.println("Giá không chính xác!!!");
                }
            } catch (Exception e) {
                System.out.println("Nhập giá không hợp lê, vui lòng nhập lại!!");
            }
        } while (price < 10000);

        return price;
    }

    public int getId() {
        int id = 0;
        do {
            try {
                System.out.print("Nhập Id bạn muốn thêm: ");
                id = Integer.parseInt(scanner.nextLine());
                if (id < 1) {
                    System.out.println("Id phải lớn hơn 1");
                }
            } catch (Exception e) {
                System.out.println("Nhập không đúng kiểu dữ liệu, vui lòng nhập lại!");
            }
        } while (id < 1);
        return id;
    }

    public void showDrinkWater() {

        List<DrinkWater> drinkWaterList = serviceDrinkWater.sortDrinkWater();

        System.out.println("-------------------------------------------------------------------");
        System.out.println("|                        Sản phẩm hiện có                         |");
        System.out.println("-------------------------------------------------------------------");
        System.out.printf("%-10s%-25s%-20s%-15s\n", "ID", "Name", "Price", "Quantity");
        for (DrinkWater drinkWater : drinkWaterList) {
            System.out.printf("%-10d%-25s%-20s%-15d\n", drinkWater.getId(), drinkWater.getName(),
                    format.format(drinkWater.getPrice()), drinkWater.getQuantity());
        }
        System.out.println("-------------------------------------------------------------------");
    }

    public void editDrinkWater() {
        int option = 0;
        do {
            try {
                System.out.println("Nhập '1' nếu bạn muốn edit sản phẩm, nhập '2' để quay lại");
                option = Integer.parseInt(scanner.nextLine());
                        switch (option) {
                            case 1:
                                showDrinkWater();
                                int id = 0;
                                do {
                                    System.out.print("Nhập Id đồ uống bạn muốn sửa: ");
                                    id = Integer.parseInt(scanner.nextLine());
                                    if (!serviceDrinkWater.checkDuplicateId(id)) {
                                        System.out.println("Id đồ uống không tồn tại, vui lòng nhập lại Id!!!");
                                    }
                                } while (!serviceDrinkWater.checkDuplicateId(id));
                                DrinkWater editProduct = serviceDrinkWater.getById(id);
                                System.out.println("Sản phẩm bạn muốn sửa là: " + editProduct.getName());
                                boolean is = false;
                                String name;
                                do {
                                    is = false;
                                    System.out.print("Nhập tên bạn muốn sửa: ");
                                    name = scanner.nextLine();
                                    if (serviceDrinkWater.checkDuplicateName(name) && !Objects.equals(editProduct.getName(), name)) {
                                        System.out.println("Tên sản phẩm đã tồn tại, đổi lại sản phẩm khác!");
                                        is = true;
                                    }

                                    if(ValidateUtils.isNameValid(name)) {
                                        System.out.println("Không đúng dạng tên, vui lòng nhập lại");
                                        is = true;
                                    }
                                } while (is);
                                int price = getPrice();
                                int quantity = getQuantity();
                                int number = 0;
                                do {
                                    System.out.println("Chọn 1 nếu bạn muốn sửa, chọn 2 nếu bạn không muốn sửa");
                                    number = Integer.parseInt(scanner.nextLine());
                                    switch (number) {
                                        case 1:
                                            editProduct.setId(id);
                                            editProduct.setName(name);
                                            editProduct.setPrice(price);
                                            editProduct.setQuantity(quantity);
                                            serviceDrinkWater.edit(id, editProduct);
                                            System.out.println("Sản phẩm có id: " + editProduct.getId() + " đã được sửa");
                                            break;
                                        case 2:
                                            break;
                                        default:
                                            System.out.println("Chọn không đúng, chọn lại");
                                    }
                                } while (number != 1 && number != 2);
                                break;
                            case 2:
                                break;
                            default:
                                System.out.println("Nhập không đúng chức năng, vui lòng chọn lại!!!!");
                                break;
                        }
            } catch (Exception e) {
                System.out.println("Nhập sai kiểu dữ liệu, vui lòng nhập lại!!!!");
            }
        } while (option != 2);


    }

    public void removeDrinkWater() {
        int id = -1;
        do {
            try {
                showDrinkWater();
                System.out.print("Nhập Id sản phẩm bạn muốn ẩn: ");
                id = Integer.parseInt(scanner.nextLine());
                if (!serviceDrinkWater.existId(id)) {
                    System.out.println("Id không tồn tại, vui lòng nhập lại!!!");
                }
            } catch (Exception e) {
                System.out.println("Id không hợp lệ, vui lòng nhập lại!!!");
            }
        } while (!serviceDrinkWater.existId(id));
        DrinkWater removeProduct = serviceDrinkWater.getById(id);
        System.out.println("Sản phẩm bạn muốn ẩn có tên là: " + removeProduct.getName());
        int number = 0;
        do {
            System.out.println("Chọn 1 nếu bạn muốn ẩn đồ uống này, chọn 2 nếu bạn muốn quay lại Menu chính!!!");
            System.out.print("Nhập lựa chọn bạn muốn: ");
            number = Integer.parseInt(scanner.nextLine());
            switch (number) {
                case 1:
                    serviceDrinkWater.lockDrinkWater(id);
                    System.out.println("Sản phẩm có tên là: " + removeProduct.getName() + " đã được ẩn thành công");
                    break;
                case 2:
                    break;
                default:
                    System.out.println("Không có lựa chọn này, vui lòng chọn lại!!!");
            }
        } while (number != 1 && number != 2);

    }

    private void unlockDrinkWater() {
        if (serviceDrinkWater.getLockDrinkWater().size() == 0) {
            System.out.println("Hiện không có sản phẩm nào đang bị khóa!!!!");
            return;
        }
        int id = -1;
        do {
            try {
                showLockDrinkWater();
                System.out.print("Nhập Id sản phẩm bạn muốn mở khóa: ");
                id = Integer.parseInt(scanner.nextLine());
                if (!serviceDrinkWater.existId(id)) {
                    System.out.println("Id không tồn tại, vui lòng nhập lại!!!");
                }
                if (serviceDrinkWater.checkDuplicateId(id)) {
                    System.out.println("Id vẫn chưa bị ẩn, vui lòng chọn lại id khác!!!!");
                }
            } catch (Exception e) {
                System.out.println("Id không hợp lệ, vui lòng nhập lại!!!");
            }
        } while (!serviceDrinkWater.existId(id) || serviceDrinkWater.checkDuplicateId(id));
        List<DrinkWater> removeProductList = serviceDrinkWater.getLockDrinkWater();
        DrinkWater removeProduct = null;
        for (DrinkWater drinkWater : removeProductList) {
            if (drinkWater.getId() == id) {
                removeProduct = drinkWater;
                break;
            }
        }
        System.out.println("Sản phẩm bạn muốn mở khóa có tên là: " + removeProduct.getName());
        int number = 0;
        do {
            System.out.println("Chọn 1 nếu bạn muốn mở lại đồ uống này, chọn 2 nếu bạn muốn quay lại Menu chính!!!");
            System.out.print("Nhập lựa chọn bạn muốn: ");
            number = Integer.parseInt(scanner.nextLine());
            switch (number) {
                case 1:
                    serviceDrinkWater.unlockDrinkWater(id);
                    System.out.println("Sản phẩm có tên là: " + removeProduct.getName() + " đã được mở khóa thành công");
                    break;
                case 2:
//                    showMenu();
                    break;
                default:
                    System.out.println("Không có lựa chọn này, vui lòng chọn lại!!!");
                    break;
            }
        } while (number != 1 && number != 2);
    }

    private void searchByName() {
        System.out.print("Nhập tên đồ uống bạn muốn tìm kiếm :");
        String name = scanner.nextLine();
        List<DrinkWater> searchByName = serviceDrinkWater.searchByName(name);
        System.out.println("-------------------------------------------------------------------");
        System.out.println("|                        Sản phẩm hiện có                         |");
        System.out.println("-------------------------------------------------------------------");
        System.out.printf("%-10s%-25s%-20s%-15s\n", "ID", "Name", "Price", "Quantity");
        for (DrinkWater drinkWater : searchByName) {
            System.out.printf("%-10d%-25s%-20s%-15d\n", drinkWater.getId(), drinkWater.getName(),
                    format.format(drinkWater.getPrice()), drinkWater.getQuantity());
        }
        System.out.println("-------------------------------------------------------------------");

    }
    private void showLockDrinkWater() {
        List<DrinkWater> lockDrinkWater = serviceDrinkWater.getLockDrinkWater();
        System.out.println("-------------------------------------------------------------------");
        System.out.println("|                    Sản phẩm đang bị khóa                         |");
        System.out.println("-------------------------------------------------------------------");
        System.out.printf("%-10s%-25s%-20s%-15s\n", "ID", "Name", "Price", "Quantity");
        for (DrinkWater drinkWater : lockDrinkWater) {
            System.out.printf("%-10d%-25s%-20s%-15d\n", drinkWater.getId(), drinkWater.getName(),
                    format.format(drinkWater.getPrice()), drinkWater.getQuantity());
        }
        System.out.println("-------------------------------------------------------------------");
    }

}
