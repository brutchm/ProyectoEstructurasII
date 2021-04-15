package com.company.dl;

import com.company.bl.HashTable;
import com.company.bl.LocationNode;
import com.company.bl.Matrix;
import com.company.bl.MultipleList;

import java.io.IOException;

public class Logic {

    private HashTable hashTable;
    private LocationNode[] locations;
    private Matrix matrix;
    private String locationsNames;
    private MultipleList multipleList;

    public Logic() throws IOException {
        //Initialize locations array
        this.locations = new LocationNode[25];
        this.locationsNames = "Costa Rica,Estados Unidos,Argentina,Alemania,Suiza,Italia," +
                "Marruecos,Egipto,Etiopía,Japón,China,Corea del Sur,Nueva Zelanda,Australia," +
                "Fiji,Colombia,Guatemala,México,España,Suecia,Noruega,Senegal,Sudáfrica," +
                "Sudán del Sur,Emiratos Árabes Unidos";
        InitializeLocations();
        this.hashTable = new HashTable();

        InitializeHash();
        //Initialize matrix


        try{
            this.matrix = new Matrix();
        }catch (Exception e){
            throw e;
        }
        this.multipleList = new MultipleList(this.locationsNames);
        initializeMultipleList();
        initializeArchVertex();
    }

    private void InitializeHash() {
        String[] info = this.locationsNames.split(",");
        for (int i = 0; i < 25; i++) {
            LocationNode node = new LocationNode(i,info[i]);
            this.hashTable.insert(node);
        }
    }

    private void InitializeLocations(){

        //feed the locations array
        for(int i = 0; i < locations.length; i++){
            LocationNode tmp = new LocationNode(i,this.locationsNames.split(",")[i]);
            this.locations[i]=tmp;
        }
    }

    public String getMinCost(int from, int to){

        return this.matrix.getMinCost(from, to, this.locationsNames);

    }


    public String printLocation(int i) {
        return this.hashTable.search(i);
    }

    public void initializeMultipleList(){
        for (int i = 0; i < 25; i++) {
            multipleList.addVertex(i);
        }
    }

    public void initializeArchVertex(){
        int[][] auxMatrix = this.matrix.getMatrix();
        for (int i = 0; i < 25; i++) {
            for (int j = 0; j < 25; j++) {
                if (auxMatrix[i][j]!= -1){
                    multipleList.addArchVertex(i,j,auxMatrix[i][j]);
                }
            }
        }
    }

    public String getAdyLocations(int id){
        return this.multipleList.showAdyLocations(id);

    }
}
