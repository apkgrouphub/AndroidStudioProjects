package com.mrcom.digitaldiary;

/**
 * Created by PACHU on 28-01-2017.
 */

public class Prodect
{
    private  int image_id;
    private String title;
    private String year;
    private String month;
    private String day;

    public Prodect(int image_id, String title, String year, String month, String day)
    {
        this.image_id=image_id;
        this.title=title;
        this.year=year;
        this.month=month;
        this.day=day;
    }

    public int getImage_id() {
        return image_id;
    }

    public void setImage_id(int image_id) {
        this.image_id = image_id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year=year;
    }
    public String getMonth() {
        return month;
    }

    public void setMonth(String year) {
        this.month=month;
    }
    public String getDay() {
        return day;
    }

    public void setDay(String year) {
        this.day=day;
    }

}
