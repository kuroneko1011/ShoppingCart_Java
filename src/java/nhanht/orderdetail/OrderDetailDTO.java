/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nhanht.orderdetail;

import java.io.Serializable;

/**
 *
 * @author Nhan
 */
public class OrderDetailDTO implements Serializable {

    private String username;
    private String item;
    private int price;
    private int quantity;
    private int total;

    public OrderDetailDTO() {

    }

    public OrderDetailDTO(String username, String item, int price, int quantity, int total) {
        this.username = username;
        this.item = item;
        this.price = price;
        this.quantity = quantity;
        this.total = total;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getItem() {
        return item;
    }

    public void setItem(String item) {
        this.item = item;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

}
