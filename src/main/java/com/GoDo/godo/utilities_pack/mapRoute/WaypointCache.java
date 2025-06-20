package com.GoDo.godo.utilities_pack.mapRoute;

import jakarta.persistence.*;

@Entity
@Table(name = "waypoint_cache", uniqueConstraints = @UniqueConstraint(columnNames = {"lat", "lon"}))
public class WaypointCache {
    @Id
    @GeneratedValue
    private Long id;

    private double lat;
    private double lon;

    @Column(name = "place_name", nullable = false)
    private String placeName;

    public WaypointCache() {}

    public WaypointCache(double lat, double lon, String placeName) {
        this.lat = lat;
        this.lon = lon;
        this.placeName = placeName;
    }

    // Getters/setters

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public double getLat() {
        return lat;
    }

    public void setLat(double lat) {
        this.lat = lat;
    }

    public double getLon() {
        return lon;
    }

    public void setLon(double lon) {
        this.lon = lon;
    }

    public String getPlaceName() {
        return placeName;
    }

    public void setPlaceName(String placeName) {
        this.placeName = placeName;
    }
}

