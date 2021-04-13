package com.company.bl;

public class HashTable {

    private LocationNode[] table;
    private final int EVEN = 11;

    public HashTable() {
        this.table = new LocationNode[12];
    }

    public int hashFunction(int value){
        return value%EVEN;
    }

    public void insert(LocationNode location){
        int hashedValue= hashFunction(location.getId());
        if (this.table[hashedValue]== null)
            table[hashedValue]= location;
        else{
            LocationNode aux = table[hashedValue];
            while (aux.getNext() != null){
                aux = aux.getNext();
            }
            aux.setNext(location);
        }
    }
}
