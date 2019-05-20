package com.edu.eci.ieti.trophy;

public class Bettor {

    private String name;
    private String info;
    private int image;

    public Bettor(String name, String info, int image) {
        this.name = name;
        this.info = info;
        this.image = image;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }
}
