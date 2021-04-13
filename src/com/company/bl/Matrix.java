package com.company.bl;
import java.util.ArrayList;
import java.util.List;

public class Matrix {

    private int[][] matrixLocation;
    private int nodesQuantity;
    private List conjS;
    private List conjComp_S;
    private List tracks;
    private String tmp;

    public Matrix(){
        this.nodesQuantity = 25;
        this.matrixLocation = new int[this.nodesQuantity][this.nodesQuantity];
        this.conjS = new ArrayList();
        this.conjComp_S = new ArrayList();
        this.tracks = new ArrayList();

    }



}
