package com.example;


import java.io.Serializable;

public class ResultEntity implements Serializable {
    public String name;
    public int img;
    public String jieshao;
    public String price;

    public ResultEntity(String name, int img, String jieshao,String price) {
        this.name = name;
        this.img = img;
        this.jieshao = jieshao;
        this.price=price;
    }
}
