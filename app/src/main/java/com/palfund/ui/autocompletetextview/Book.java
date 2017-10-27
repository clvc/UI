package com.palfund.ui.autocompletetextview;

/**
 * Created by clvc on 2017/10/18.
 * 真正的速度是看不见的 !
 * Today is today , we will go !
 */

public class Book {
    public int id;
    public String name;
    public String author;
    public int price;
    public String pinyin;

    public Book(int id, String name, String author, int price, String pinyin) {
        this.id = id;
        this.name = name;
        this.author = author;
        this.price = price;
        this.pinyin = pinyin;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public String getPinyin() {
        return pinyin;
    }

    public void setPinyin(String pinyin) {
        this.pinyin = pinyin;
    }
}
