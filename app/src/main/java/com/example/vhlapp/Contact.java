package com.example.vhlapp;

public class Contact {

    private int id;
    private String name;
    private String type;
    private String number;

    public Contact(int id, String name, String type, String number) {
        this.id = id;
        this.name = name;
        this.type = type;
        this.number = number;
    }

    public void Contact(){

    }

    @Override
    public String toString() {
        return name;
//        return "Contact{" +
//                "id=" + id +
//                ", name='" + name + '\'' +
//                ", type='" + type + '\'' +
//                ", number='" + number + '\'' +
//                '}';
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

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

}
