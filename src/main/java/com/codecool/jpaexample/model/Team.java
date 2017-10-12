//modify file because it needed to a test git commit 

package com.codecool.jpaexample.model;

import javax.persistence.*;
import java.util.ArrayList;
//1. Use the `@Column` annotation to modify the default O-R mapping! Change the column name for attribute `zipcode` to `Zip`, limit its length to 4, and set the `email` field to `UNIQUE` and `NOT NULL`!


@Entity
public class Team {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String country;
    private String stationIds;


    public Team() {
    }

    public Team(String country, String stationIds) {
        this.country = country;
        this.stationIds = stationIds;

    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getStationIds() {
        return stationIds;
    }

    public void setStationIds(String stationId) {
        this.stationIds = stationId;
    }



    @Override
    public String toString() {
        return "Team{" +
                "id=" + id +
                ", country='" + country + '\'' +
                ", stationIds='" + stationIds + '\'' +
                '}';
    }

}
