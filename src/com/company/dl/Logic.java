package com.company.dl;

import com.company.bl.HashTable;
import com.company.bl.LocationNode;
import com.company.bl.Matrix;

import java.io.IOException;

public class Logic {

    private HashTable hashTable;
    private LocationNode[] locations;
    private Matrix matrix;
    private String locationsNames;
    public Logic() throws IOException {
        //Initialize locations array
        this.locations = new LocationNode[25];
        this.locationsNames = "Costa Rica,Estados Unidos,Argentina,Alemania,Suiza,Italia," +
                "Marruecos,Egipto,Etiopía,Japón,China,Corea del Sur,Nueva Zelanda,Australia," +
                "Fiji,Colombia,Guatemala,México,España,Suecia,Noruega,Senegal,Sudáfrica," +
                "Sudán del Sur,Emiratos Árabes Unidos";
        InitializeLocations();
        //Initialize matrix

        try{
            this.matrix = new Matrix();
        }catch (Exception e){
            throw e;
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


}
