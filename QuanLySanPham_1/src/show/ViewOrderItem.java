package show;

import Model.DrinkWater;
import Model.ItemOrder;
import Service.ServiceDrinkWater;
import Service.ServiceItemOrder;

import java.text.DecimalFormat;
import java.util.List;
import java.util.Scanner;

public class ViewOrderItem {
    Scanner scanner = new Scanner(System.in);
    private static final ServiceItemOrder SERVICE_ITEM_ORDER = new ServiceItemOrder();
    private static final ServiceDrinkWater SERVICE_DRINK_WATER = new ServiceDrinkWater();
    DecimalFormat decimalFormat = new DecimalFormat("###,###,###" + "VND");

    public void showMenu() {
        int option = -1;
        do {
            try {
                menu();
                System.out.print("Chọn chức năng: ");
                option = Integer.parseInt(scanner.nextLine());
                switch (option) {
                    case 1:
                        orderItem();
                        break;
                    case 2:
                        removeOrderItem();
                        System.out.println("Nhập một phím để quay lại menu chính!!!");
                        String choice = scanner.nextLine();
                        break;
                    case 3:
                        showPaid();
                        System.out.println("Nhập một phím để quay lại menu chính!!!");
                        choice = scanner.nextLine();
                        break;
                    case 0:
                        break;
                    default:
                        System.out.println("Nhập sai chức năng, vui lòng nhập lại");
                        break;
                }
            } catch (Exception e) {
                e.printStackTrace();
                System.out.println("Nhập phím không đúng kiểu dữ liệu, vui lòng nhập lại");
            }

        } while (option != 0);

    }

    private void menu() {
        System.out.println("-------------------------------------");
        System.out.println("| 1. Order đồ uống                  |");
        System.out.println("| 2. Xóa đồ uống đã Order           |");
        System.out.println("| 3. Thanh toán                     |");
        System.out.println("| 0. Quay lại                       |");
        System.out.println("-------------------------------------");

    }

    private void orderItem() {
        int option = -1;
        do {
            try {
                System.out.println("Nhập 1 để Order sản phẩm, nhập 0 để quay lại");
                option = Integer.parseInt(scanner.nextLine());
                switch (option) {
                    case 1:
                        showDrinkWater();
                        boolean is;
                        int id = 0;
                        do {
                            is = false;
                            id = getId(1);
                            if (!SERVICE_DRINK_WATER.checkDuplicateId(id)) {
                                System.out.println("Không có sản phẩm có ID này, vui lòng nhập lại!");
                                is = true;
                            }
                        } while (is);
                        DrinkWater drinkWater = SERVICE_DRINK_WATER.getById(id);
                        String name = drinkWater.getName();
                        System.out.println("Sản phẩm bạn muốn Order là: " + name);
                        int price = drinkWater.getPrice();
                        int quantity = getQuantity(1);
                        int total = price * quantity;
                        ItemOrder newDrinkWater = new ItemOrder(id, name, price, quantity, total);
                        if (SERVICE_ITEM_ORDER.checkDuplicateID(id)) {
                            ItemOrder itemOrder = SERVICE_ITEM_ORDER.getByID(id);
                            itemOrder.setQuantity(itemOrder.getQuantity() + quantity);
                            drinkWater.setQuantity(drinkWater.getQuantity() - quantity);
                            SERVICE_DRINK_WATER.remove(id);
                            SERVICE_DRINK_WATER.add(drinkWater);
                            SERVICE_ITEM_ORDER.remove(itemOrder.getName());
                            SERVICE_ITEM_ORDER.add(itemOrder);
                            System.out.println("Đã order sản phẩm");
                            showItemOrder();
                        } else if (drinkWater.getQuantity() < quantity) {
                            System.out.println("Số lượng không đủ, vui lòng nhập lại!!!");
                        } else {
                            SERVICE_ITEM_ORDER.add(newDrinkWater);
                            drinkWater.setQuantity(drinkWater.getQuantity() - quantity);
                            SERVICE_DRINK_WATER.remove(id);
                            SERVICE_DRINK_WATER.add(drinkWater);
                            System.out.println("Đã order sản phẩm");
                            showItemOrder();
                        }
                        break;
                    case 0:
                        break;
                    default:
                        System.out.println("Chọn không đúng chức năng, vui lòng chọn lại!!!");
                        break;
                }
            } catch (Exception e) {
                System.out.println("Nhập không đúng kiểu dữ liệu, vui lòng nhập lại!!!");
            }
        } while (option != 0);

    }

    private int getQuantity(int flag) {
        int quantity = -1;
        do {
            try {
                if (flag == 1) {
                    System.out.print("Nhập số lượng đồ uống bạn muốn Order: ");

                }
                if (flag == 2) {
                    System.out.print("Nhập số lượng đồ uống bạn muốn hủy Order: ");
                }
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


    public int getId(int flag) {
        int id = 0;
        do {
            try {
                if (flag == 1) {
                    System.out.print("Nhập Id đồ uống bạn muốn order: ");

                }
                if (flag == 2) {
                    System.out.print("Nhập Id đồ uống bạn muốn xóa: ");
                }
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

    private void removeOrderItem() {
        List<ItemOrder> itemOrders = SERVICE_ITEM_ORDER.serviceItemOrder();
        if(itemOrders.size()== 0) {
            System.out.println("Không có order nào!!!");
            return;
        }
        int option = -1;
        do {
            try {
                System.out.println("Nhập phím 1 nếu bạn muốn hủy order, nhập 0 để quay lại");
                option = Integer.parseInt(scanner.nextLine());
                switch (option) {
                    case 1:
                        showItemOrder();
                        boolean is;
                        int id = 0;
                        do {
                            is = false;
                            id = getId(2);
                            if (!SERVICE_ITEM_ORDER.checkDuplicateID(id)) {
                                System.out.println("Không có sản phẩm có ID này được order, vui lòng nhập lại!");
                                is = true;
                            }
                        } while (is);
                        DrinkWater drinkWater = SERVICE_DRINK_WATER.getById(id);
                        ItemOrder itemOrder = SERVICE_ITEM_ORDER.getByID(id);
                        String name = itemOrder.getName();
                        System.out.println("Sản phẩm bạn hủy Order là: " + name);
                        int price = itemOrder.getPrice();
                        int quantity = 0;
                        do {
                            quantity = getQuantity(2);
                            if (quantity > itemOrder.getQuantity()) {
                                System.out.println("Số lượng nước Order nhỏ hơn số lượng nước bạn muốn hủy, vui lòng nhập lại");
                            }
                        } while (quantity > itemOrder.getQuantity());
                        int total = price * quantity;
                        ItemOrder newDrinkWater = new ItemOrder(id, name, price,
                                itemOrder.getQuantity() - quantity, itemOrder.getTotal() - total);
                        if (quantity == itemOrder.getQuantity()) {
                            drinkWater.setQuantity(drinkWater.getQuantity() + quantity);
                            SERVICE_DRINK_WATER.remove(id);
                            SERVICE_DRINK_WATER.add(drinkWater);
                            SERVICE_ITEM_ORDER.remove(name);
                            System.out.println("Đã hủy sản phẩm: " + name);
                        }

                        if (quantity < itemOrder.getQuantity()) {
                            drinkWater.setQuantity(drinkWater.getQuantity() + quantity);
                            SERVICE_DRINK_WATER.remove(id);
                            SERVICE_DRINK_WATER.add(drinkWater);
                            SERVICE_ITEM_ORDER.remove(name);
                            SERVICE_ITEM_ORDER.add(newDrinkWater);
                        }
                        showItemOrder();
                        break;
                    case 0:
                        break;
                    default:
                        System.out.println("Chọn sai chức năng, vui lòng chọn lại!!!!");
                }
            } catch (Exception e) {
                e.printStackTrace();
                System.out.println("Nhập không dúng kiểu dữ liệu, vui lòng nhập lại!!!");
            }
        } while (option != 0);


    }

    private void showDrinkWater() {
        List<DrinkWater> drinkWaterList = SERVICE_DRINK_WATER.sortDrinkWater();
        System.out.println("-------------------------------------------------------------------");
        System.out.println("|                        Sản phẩm hiện có                         |");
        System.out.println("-------------------------------------------------------------------");
        System.out.printf("%-10s%-25s%-20s%-15s\n", "ID", "Name", "Price", "Quantity");
        for (DrinkWater drinkWater : drinkWaterList) {
            System.out.printf("%-10d%-25s%-20s%-15d\n", drinkWater.getId(), drinkWater.getName(),
                    decimalFormat.format(drinkWater.getPrice()), drinkWater.getQuantity());
        }
        System.out.println("-------------------------------------------------------------------");
    }

    private void showItemOrder() {
        List<ItemOrder> itemOrders = SERVICE_ITEM_ORDER.sortByIdASC();
        System.out.println("-------------------------------------------------------------------------------------");
        System.out.println("|                              Đồ uống đã order                                     |");
        System.out.println("-------------------------------------------------------------------------------------");
        System.out.printf("%-10s%-25s%-20s%-15s%-15s\n", "ID", "Name", "Giá tiền", "Số lượng", "Thành tiền");
        for (ItemOrder item : itemOrders) {
            System.out.printf("%-10d%-25s%-20s%-15d%-15s\n", item.getId(), item.getName(),
                    decimalFormat.format(item.getPrice()), item.getQuantity(), decimalFormat.format(item.getTotal()));
        }
        System.out.println("-------------------------------------------------------------------------------------");
    }

    private void showPaid() {

        List<ItemOrder> payment = SERVICE_ITEM_ORDER.payment();
        if(payment.size() == 0) {
            System.out.println("Không có đơn hàng nào để thanh toán!!!");
            return;
        }
        System.out.println("-------------------------------------------------------------------------------------");
        System.out.println("|                              Đồ uống đã order                                     |");
        System.out.println("-------------------------------------------------------------------------------------");
        System.out.printf("%-10s%-25s%-20s%-15s%-15s\n", "ID", "Name", "Giá tiền", "Số lượng", "Thành tiền");
        for (ItemOrder item : payment) {
            System.out.printf("%-10d%-25s%-20s%-15d%-15s\n", item.getId(), item.getName(),
                    decimalFormat.format(item.getPrice()), item.getQuantity(), decimalFormat.format(item.getTotal()));
        }
        System.out.println("-------------------------------------------------------------------------------------");
        int total = 0;
        for (ItemOrder itemOrder : payment) {
            total += itemOrder.getTotal();
        }
        System.out.printf("%-10s%-5s\n", "Tổng: ",decimalFormat.format(total));
        System.out.println("Bạn đã thanh toán thành công");
    }
}
