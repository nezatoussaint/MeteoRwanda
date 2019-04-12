package com.example.rugajupascy.meteorwanda;
public class ListItem {
    private String title,locationSensor,descriptionSensor;
    public ListItem(String title, String locationSensor, String descriptionSensor) {
        this.title = title;
        this.locationSensor = locationSensor;
        this.descriptionSensor = descriptionSensor;
    }
    public String getTitle() {
        return title;
    }
    public String getLocationSensor() {
        return locationSensor;
    }
    public String getDescriptionSensor() {
        return descriptionSensor;
    }
}
