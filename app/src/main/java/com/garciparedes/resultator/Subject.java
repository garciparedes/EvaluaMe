package com.garciparedes.resultator;

import java.util.ArrayList;

/**
 * Created by garciparedes on 5/12/14.
 */
public class Subject {

    private String name;
    private String description;
    private ArrayList<Test> testList = new ArrayList<Test>();

    public Subject(String name, String description){
        this.name = name;
        this.description = description;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public ArrayList<Test> getTestList() {
        return testList;
    }

    public Test getTestElement(int i) {
        try {
            return getTestList().get(i);
        } catch (NullPointerException e){
            return null;
        }
    }

    public double getAverage(){

        double sum = 0;

        for (int i = 0 ; i < getTestList().size() ; i++){
            sum += getTestElement(i).getMark();
        }

        return  sum/getTestList().size();
    }
}
