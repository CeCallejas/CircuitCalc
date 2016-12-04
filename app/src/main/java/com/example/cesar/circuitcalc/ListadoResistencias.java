package com.example.cesar.circuitcalc;


class ListadoResistencias {

    private String title;
    private String categoryId;
    private String description;



    public ListadoResistencias(String categoryId, String title, String description) {
        super();
        this.title = title;
        this.description = description;
        this.categoryId = categoryId;
    }


    public String getTitle() {
        return title;
    }

    public void setTittle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }


    public String getCategoryId(){return categoryId;}

    public void setCategoryId(String categoryId){this.categoryId = categoryId;}

}