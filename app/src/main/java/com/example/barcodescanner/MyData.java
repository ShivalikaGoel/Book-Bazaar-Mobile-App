package com.example.barcodescanner;

class MyData {
    private int id;
    private String description,image_link,edition,cost,name,enrollnum;

    public MyData(int id, String bookcondition, String imageLink, String cost, String edition, String name,String enrollnum) {
        this.id = id;
        this.description = bookcondition;
        this.image_link = imageLink;
        this.cost=cost;
        this.name=name;
        this.edition=edition;
        this.enrollnum=enrollnum;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
        public String getName() {
        return name;
    }
    public void setName(String Name) {
        this.name = Name;
    }
    public String getCost() {
        return cost;
    }
    public void setCost(String Cost) {
        this.cost = Cost;
    }
    public String getEdition() {
        return edition;
    }
    public void setEdition(String edition) {
        this.edition = edition;
    }
    public String getEnrollnum() {
        return enrollnum;
    }
    public void setEnrollnum(String enrollnum) {
        this.enrollnum = enrollnum;
    }
    public String getImage_link() {
        return image_link;
    }

    public void setImage_link(String image_link) {
        this.image_link = image_link;
    }
}

