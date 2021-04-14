package com.company.bl;

import javax.xml.stream.Location;

public class HashTable {

    private LocationNode[] table;
    private final int EVEN = 11;

    public HashTable() {
        this.table = new LocationNode[12];
    }

    public int hashFunction(int value){
        return value%EVEN;
    }

    public void insert(LocationNode location) {
        int hashedValue = hashFunction(location.getId());
        if (this.table[hashedValue] == null){
            table[hashedValue] = location;
    }else{
            LocationNode aux = table[hashedValue];
            while (aux.getNext() != null){
                aux = aux.getNext();
            }
            aux.setNext(location);
        }
    }

    public String search(int index){
        int hashedValue = hashFunction(index);
        if (this.table[hashedValue]== null){
            return "Information not found";
        }else {
            LocationNode aux = this.table[hashedValue];
            while (aux!=null){
                if (aux.getId()==index){
                    return "Location{ id= "+aux.getId()+", name= "+aux.getName()+"}";
                }
                aux= aux.getNext();
            }
        }
        return"Information not found";
    }
}
