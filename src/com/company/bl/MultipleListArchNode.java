package com.company.bl;

public class MultipleListArchNode {

    private int cost;
    private MultipleListArchNode next;
    private MultipleListNode vertex;

    public MultipleListArchNode(){
        this.next = null;
        this.vertex = null;
    }

    public MultipleListArchNode(int cost){
        this.cost = cost;
        this.next = null;
        this.vertex = null;
    }

    public MultipleListArchNode(int cost, MultipleListNode vertex){
        this.cost = cost;
        this.next = null;
        this.vertex = vertex;
    }

    public int getCost(){
        return this.cost;
    }

    public MultipleListArchNode getNext(){
        return this.next;
    }

    public MultipleListNode getVertex(){
        return this.vertex;
    }

    public void setCost(int cost){
        this.cost = cost;
    }

    public void setNext(MultipleListArchNode next){
        this.next = next;
    }

    public void setVertex(MultipleListNode vertex){
        this.vertex = vertex;
    }


}
