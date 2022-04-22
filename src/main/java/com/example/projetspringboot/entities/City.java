package com.example.projetspringboot.entities;

import javax.persistence.*;
import java.util.List;

@Entity
public class City {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Column(unique = true)
    private String name;

    private int zipCode;

    @OneToMany(mappedBy = "city")
    private List<Library> libraries;

    public City() {
    }

    public City(String name, int zipCode) {
        this.name = name;
        this.zipCode = zipCode;
    }

    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getZipCode() {
        return zipCode;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setZipCode(int zipCode) {
        this.zipCode = zipCode;
    }

    public List<Library> getLibraries() {
        return libraries;
    }

    public void setLibraries(List<Library> libraries) {
        this.libraries = libraries;
    }
}
