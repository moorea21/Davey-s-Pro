package model;

import java.io.Serializable;

/**
 *
 * @author John Phillips
 */
public class Dog implements Serializable {

    private int dogId;
    private String name;
    private String species;
    private String color;
    private double age;

    public Dog() {
        dogId = 0;
        name = "";
        species = "";
        color = "";
        age = 0;
    }

    public Dog(int dogId, String name, String species, String color, double age) {
        this.dogId = dogId;
        this.name = name;
        this.species = species;
        this.color = color;
        this.age = age;
    }

    public int getDogId() {
        return dogId;
    }

    public void setDogId(int dogId) {
        this.dogId = dogId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSpecies() {
        return species;
    }

    public void setSpecies(String species) {
        this.species = species;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public double getAge() {
        return age;
    }

    public void setAge(double age) {
        this.age = age;
    }    

    public String inHTMLRowFormat() {
        return "<tr><td>" + dogId + "</td>"
                + "<td>" + name + "</td>"
                + "<td>" + species + "</td>"
                + "<td>" + color + "</td>"
                + "<td>" + age + "</td></tr>\n";
    }

    @Override
    public String toString() {
        return "Dog{" + "dogId=" + dogId + ", name=" + name
                + ", species=" + species + ", color=" + color
                + ", age=" + age + '}';
    }
}
