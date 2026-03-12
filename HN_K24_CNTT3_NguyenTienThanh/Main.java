package Ex5.HN_K24_CNTT3_NguyenTienThanh;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        ProductManager manager = new ProductManager();
        int choice = -1;
        while (choice != 0) {
            System.out.println("\n=== PRODUCT MANAGEMENT SYSTEM ===");
            System.out.println("1. Thêm sản phẩm mới");
            System.out.println("2. Hiển thị danh sách sản phẩm");
            System.out.println("3. Cập nhật số lượng theo ID");
            System.out.println("4. Xóa sản phẩm đã hết hàng");
            System.out.println("0. Thoát chương trình");
            System.out.println("===============================");
            System.out.print("Lựa chọn của bạn: ");
            try {
                choice = Integer.parseInt(sc.nextLine());
                switch (choice) {
                    case 1:
                        System.out.print("Nhập ID: ");
                        int id = Integer.parseInt(sc.nextLine());
                        System.out.print("Nhập tên sản phẩm: ");
                        String name = sc.nextLine();
                        System.out.print("Nhập giá: ");
                        double price = Double.parseDouble(sc.nextLine());
                        System.out.print("Nhập số lượng: ");
                        int qty = Integer.parseInt(sc.nextLine());
                        System.out.print("Nhập danh mục: ");
                        String category = sc.nextLine();
                        Product newProduct = new Product(id, name, price, qty, category);
                        manager.addProduct(newProduct);
                        break;
                    case 2:
                        manager.displayProducts();
                        break;
                    case 3:
                        System.out.print("Nhập ID sản phẩm cần cập nhật: ");
                        int updateId = Integer.parseInt(sc.nextLine());
                        System.out.print("Nhập số lượng mới: ");
                        int newQty = Integer.parseInt(sc.nextLine());
                        manager.updateQuantity(updateId, newQty);
                        break;
                    case 4:
                        


                        break;
                    case 0:
                        System.out.println("Đang thoát chương trình.");
                        break;
                    default:
                        System.out.println("Lựa chọn không hợp lệ");
                }
            } catch (NumberFormatException e) {
                System.out.println("Vui lòng nhập vào số");
            } catch (InvalidProductException e) {
                System.out.println(e.getMessage());
            } catch (Exception e) {
                System.out.println("Không xác định: " + e.getMessage());
            }
        }
        sc.close();
    }
}
