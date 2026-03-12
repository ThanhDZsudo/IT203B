package Ex5.HN_K24_CNTT3_NguyenTienThanh;


import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class ProductManager {
    private List<Product> products;

    public ProductManager() {
        this.products = new ArrayList<>();
    }

    public void addProduct(Product product) throws InvalidProductException {
        boolean isExist = products.stream()
                .anyMatch(p -> p.getId() == product.getId());
        if (isExist) {
            throw new InvalidProductException("ID sản phẩm " + product.getId() + " đã tồn tại trong hệ thống");
        }
        products.add(product);
        System.out.println("Đã thêm sản phẩm thành công");
    }

    public void displayProducts() {
        if (products.isEmpty()) {
            System.out.println("Kho hàng hiện đang trống");
            return;
        }
        System.out.printf("%-5s | %-20s | %-12s | %-10s | %-15s%n", "ID", "Tên sản phẩm", "Giá", "Số lượng", "Danh mục");
        System.out.println("-------------------------------------------------------------------");
        products.forEach(p -> {
            System.out.printf("%-5d | %-20s | %-12.2f | %-10d | %-15s%n",
                    p.getId(), p.getName(), p.getPrice(), p.getQuantity(), p.getCategory());
        });
    }

    public void updateQuantity(int id, int newQuantity) throws InvalidProductException {
        Optional<Product> optionalProduct = products.stream()
                .filter(p -> p.getId() == id)
                .findFirst();

        if (optionalProduct.isPresent()) {
            optionalProduct.get().setQuantity(newQuantity);
            System.out.println("Cập nhật thành công cho sản phẩm ID " + id);
        } else {
            throw new InvalidProductException("Không tìm thấy sản phẩm có ID " + id + " để cập nhật");
        }
    }
}
