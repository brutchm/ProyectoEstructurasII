package com.company.tl;

import com.company.dl.Logic;

import java.io.IOException;

public class Controller {

    private Logic logic;

    public Controller() throws IOException {
        try{
            this.logic = new Logic();

        }catch (Exception e){

            throw e;
        }
    }

    public String getMinCost(int from, int to){
        return this.logic.getMinCost(from, to);
    }


    public String printLocation(int i) {
        return this.logic.printLocation(i);
    }

    public String getAdyLocation(int id){
        return this.logic.getAdyLocations(id);


        }
}
