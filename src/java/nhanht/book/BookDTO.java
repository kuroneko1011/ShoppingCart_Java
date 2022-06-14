/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nhanht.book;

import java.io.Serializable;

/**
 *
 * @author Nhan
 */
public class BookDTO implements Serializable {

    private int bookID;
    private String name;
    private int price;
    private int availableQuantity;

    public BookDTO() {
    }

    public BookDTO(int bookID, String name, int price, int availableQuantity) {
        this.bookID = bookID;
        this.name = name;
        this.price = price;
        this.availableQuantity = availableQuantity;
    }

    public int getBookID() {
        return bookID;
    }

    public void setBookID(int bookID) {
        this.bookID = bookID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getAvailableQuantity() {
        return availableQuantity;
    }

    public void setAvailableQuantity(int availableQuantity) {
        this.availableQuantity = availableQuantity;
    }
    
    
}
