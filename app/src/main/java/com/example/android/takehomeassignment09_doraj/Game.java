package com.example.android.takehomeassignment09_doraj;

public class Game {
    public String name;
    public String company;
    public String genre;

    public Game(String name, String company, String genre) {
        this.name = name;
        this.company = company;
        this.genre = genre;
    }

    @Override
    public String toString() {
        return "You Voted:" +
                name + ", produced by "+ company +
                ". It is a " + genre + ".";
    }

    public Game() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }


}
