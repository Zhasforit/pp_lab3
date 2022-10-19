/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package izt2_pp_lab3;

/**
 *
 * @author jasic
 */
public class Currency {
    int id;  
    String name;
    String country;
    int cost;

    public Currency() {
        this.id = 0;
        this.name = "";
        this.country = "";
        this.cost = 0;
    }

    public Currency(String name, String country, int cost) {
        this.id = 0;
        this.name = name;
        this.country = country;
        this.cost = cost;
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

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public int getCost() {
        return cost;
    }

    public void setCost(int cost) {
        this.cost = cost;
    }
    @Override
    public String toString() {
        return String.format("Валюта=%s, Страна=%s, Цена=%d", name, country, cost);
    }
}
