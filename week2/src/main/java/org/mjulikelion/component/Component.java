package org.mjulikelion.component;

public class Component {
    public Component(String name, int price, String company) {
        this.name = name;
        this.price = price;
        this.company = company;
    }

    private String name;
    private int price;
    private String company;

    protected String getName(){
        return this.name;
    }


}
