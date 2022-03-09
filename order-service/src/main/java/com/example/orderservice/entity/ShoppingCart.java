package com.example.orderservice.entity;/*

package com.example.restfulapi.entity;

import com.google.gson.Gson;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.OneToMany;
import java.util.*;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class ShoppingCart {

    private Integer id;
    private Integer customerId;
    // ds sản phẩm.
    HashMap<Integer, CartItem> mapItem; // lưu theo key - value, value giống với set items. Giúp xử lý crud cart, khi lưu thì lại chuyển sang set.
    @OneToMany
    Set<CartItem> setItem;

    @Override
    public String toString() {
        return new Gson().toJson(mapItem);
    }

    public Order prepareOrder(){
        Order order = new Order(); // generate id, tính tổng tiền, set ngày tháng, set các thông tin
        Set<OrderDetail> orderDetails = new HashSet<>(); // chuyển từ cart item sang
        for (CartItem item : this.getListItem()) {
            OrderDetail orderDetail = new OrderDetail();
            // mapping
            orderDetails.add(orderDetail);
        }
        order.setOrderDetails(orderDetails);
        return order;
    }

    // lấy map sản phẩm
    public HashMap<Integer, CartItem> getMapItem() {
        if (mapItem == null) {
            mapItem = new HashMap<>();
        }
        return mapItem;
    }

    public List<CartItem> getListItem() {
        return new ArrayList<CartItem>(mapItem.values());
    }

    // hàm này phải gọi trước khi đưa vào db.
    public void changeMapToSet() {
        setItem = new HashSet<CartItem>(mapItem.values());
    }

    public void add(Product product, int quantity) {
        // validate
        if (product == null || quantity <= 0) {
            return;
        }
        if (mapItem == null) {
            mapItem = new HashMap<>();
        }
        // cứ khởi tạo cái túi mới.
        CartItem item = null;
        // có sản phẩm này trong xe chưa.
        if (mapItem.containsKey(product.getId())) {
            // nếu có túi cũ thì lấy ra.
            item = mapItem.get(product.getId());
            // update số lượng đang có.
            item.setQuantity(item.getQuantity() + quantity);
        } else {
            // trường hợp chưa có thì tạo túi mới.
            item = new CartItem();
            item.setProductId(product.getId());
            item.setProductName(product.getName());
            item.setThumbnail(product.getThumbnail());
            item.setUnitPrice(product.getPrice());
            item.setQuantity(quantity);
        }
        mapItem.put(product.getId(), item);

    }

    public void update(Product product, int quantity) {
        // validate
        if (product == null || quantity <= 0) {
            return;
        }
        if (mapItem == null) {
            return;
        }
        // có sản phẩm này trong xe chưa.
        if (mapItem.containsKey(product.getId())) {
            // nếu có túi cũ thì lấy ra.
            CartItem item = mapItem.get(product.getId());
            // update số lượng đang có.
            item.setQuantity(quantity);
            mapItem.put(product.getId(), item);
        }
    }

    public void remove(int productId) {
        if (productId <= 0 || mapItem == null) {
            return;
        }
        if (!mapItem.containsKey(productId)) {
            System.err.println("Product is not found!");
        }
        mapItem.remove(productId);
    }

    public void clear() {
        if (mapItem == null) {
            System.err.println("Shopping cart is empty!");
            return;
        }
        mapItem.clear();
    }

    public double getTotalPrice() {
        List<CartItem> list = this.getListItem();
        double totalPrice = 0;
        for (CartItem item :
                list) {
            totalPrice += item.getUnitPrice() * item.getQuantity();
        }
        return totalPrice;
    }

    public static void main(String[] args) {
        // móc trong db.
        Product product1 = new Product();
        product1.setId(1);
        product1.setName("Sản phẩm 01");
        product1.setPrice(2000);
        product1.setThumbnail("http://anh.png");
        Product product2 = new Product();
        product2.setId(2);
        product2.setName("Sản phẩm 02");
        product2.setPrice(3000);
        product2.setThumbnail("http://anh.png");
        Product product3 = new Product();
        product3.setId(3);
        product3.setName("Sản phẩm 03");
        product3.setPrice(5000);
        product3.setThumbnail("http://anh.png");

        ShoppingCart shoppingCart = new ShoppingCart();
        shoppingCart.add(product1, 10);
        shoppingCart.add(product1, 30);
        shoppingCart.add(product1, 30);
        shoppingCart.update(product1, 1);
        shoppingCart.add(product2, 50);
        shoppingCart.add(product3, 20);
        shoppingCart.add(product3, 20);
        shoppingCart.add(product3, 20);
        shoppingCart.remove(3);
        //shoppingCart.clear();

        List<CartItem> items = shoppingCart.getListItem();
        System.out.println(shoppingCart);
    }
}

*/
