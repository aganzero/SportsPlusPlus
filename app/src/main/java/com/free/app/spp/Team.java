package com.free.app.spp;

import java.io.Serializable;

public class Team implements Serializable {
    private String name;
    private String image;
    Team(String name,String image)
    {
        this.name = name;
        this.image = image;
    }
    public String getName()
    {
        return name;
    }
    String getImage()
    {
        return image;
    }
    void setName(String name) {
        this.name = name;
    }
}
