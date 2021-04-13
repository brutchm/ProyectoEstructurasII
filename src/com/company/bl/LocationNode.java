package com.company.bl;

public class LocationNode {
    private int id;
    private String name;
    private LocationNode next;

    public LocationNode() {
        this.id=0;
        this.name="";
        this.next = null;
    }

    public LocationNode(int id, String name) {
        this.id = id;
        this.name = name;
        this.next = null;
    }

    public LocationNode(int id, String name, LocationNode next) {
        this.id = id;
        this.name = name;
        this.next = next;
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

    public LocationNode getNext() {
        return next;
    }

    public void setNext(LocationNode next) {
        this.next = next;
    }
}
