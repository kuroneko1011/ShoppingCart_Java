/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nhanht.cart;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author Nhan
 */
public class CartObject implements Serializable {

    private Map<String, Integer> items;

    private Map<String, Integer> bookList;

    public Map<String, Integer> getItems() {
        return items;
    }

    public Map<String, Integer> getBookList() {
        if (this.bookList == null) {
            this.bookList = new HashMap<>();
            this.bookList.put("JDK", 100);
            this.bookList.put("Java", 150);
            this.bookList.put("Tomcat", 200);
            this.bookList.put("OOP", 300);
            this.bookList.put("Netbeans", 400);
            this.bookList.put("Servlet", 500);
            this.bookList.put("JavaBeans", 600);
        }

        return bookList;
    }

    public void addItemToCart(String id) {
        //1. Check available items 
        if (this.items == null) {
            this.items = new HashMap<>();
        } //end items sare not existed

        //2. Available, frops item to cart
        int quantity = 1;
        if (this.items.containsKey(id)) {
            quantity = this.items.get(id) + 1;
        }

        this.items.put(id, quantity);
    }

    public void removeItemFromCart(String id) {
        //1. Check available items
        if (this.items == null) {
            return;
        }
        //2. remove item from cart
        if (this.items.containsKey(id)) {
            this.items.remove(id);
            if (this.items.isEmpty()) {
                this.items = null;
            }
        }
    }
}
