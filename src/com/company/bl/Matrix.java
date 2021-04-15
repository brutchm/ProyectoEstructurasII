package com.company.bl;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Matrix {

    int[][] matrix;
    int nodesQuantity = 25;

    public Matrix(){
        matrix = new int[this.nodesQuantity][this.nodesQuantity];
        loadCostsFromFile();
    }

    public int[][] getMatrix(){
        return this.matrix;
    }

    private void loadCostsFromFile(){
        String fileName;
        String a;
        StringTokenizer d;
        int f=0;
        int c=0;

        try{
            fileName= "locationCosts.txt";
            FileReader ar = new FileReader(fileName);
            BufferedReader b = new  BufferedReader(ar);
            while((a=b.readLine())!=null){
                d = new StringTokenizer(a);
                c=0;
                while(d.hasMoreTokens()){
                    matrix[f][c]=Integer.valueOf(d.nextToken()).intValue();
                    c++;
                }
                f++;
            }
        }
        catch(FileNotFoundException e){
            System.out.print("Error: "+e);
            System.exit(0);
        }
        catch(IOException e1){
            System.out.print("Error: "+e1);
            System.exit(0);
        }
        catch(NumberFormatException e2){
            System.out.print("Error: "+e2);
            System.exit(0);
        }
    }

    public String getMinCost(int from, int to, String locaciones){
        return resolve(from, to, locaciones);
    }

    public String resolve(int from, int to, String locaciones){
        int[][] matrixAux = new int[nodesQuantity][nodesQuantity];
        int countVisitedVertex = 0;
        int minCost = 0;

        ArrayList<Integer> queueVertex = new ArrayList<Integer>();
        queueVertex.add(from);
        //Iterates for every unvisited vertex added to queue
        while (queueVertex.size() > 0 && queueVertex.get(0) != to){
            //Get the min cost of the previous vertex
            minCost = calculateMinCostColumVertex(matrixAux, countVisitedVertex,queueVertex.get(0));

            if(minCost != -1){
                for(int i = 0; i < nodesQuantity; i++){
                    if(matrix[queueVertex.get(0)][i] != -1 ){
                        if( matrixAux[queueVertex.get(0)][i] == 0 ||
                                matrixAux[queueVertex.get(0)][i] > matrix[queueVertex.get(0)][i] + minCost){
                            matrixAux[queueVertex.get(0)][i] = matrix[queueVertex.get(0)][i] + minCost;
                            queueVertex.add(i);
                        }
                    }
                }
            }else{
                return "No hay camino";
            }
            countVisitedVertex++;
            queueVertex.remove(0);
        }
        if(countVisitedVertex == 1){
            return "No hay camino";
        }

        //Get the path and the cost of the mininum track
        int auxFil = to;
        ArrayList<Integer> arrVertexReverse = new ArrayList<Integer>();
        int totalCost = 0;
        int minCostTrack = 100000;
        int indexMin = 0;
        boolean isTrack = false;
        do{
            //for to get the min cost of the column
            arrVertexReverse.add(auxFil);
            for(int f = 0; f < nodesQuantity; f++){
                if(matrixAux[f][auxFil] < minCostTrack && matrixAux[f][auxFil] != 0){
                    minCostTrack = matrixAux[f][auxFil];
                    indexMin = f;
                    isTrack = true;
                }
                if(auxFil == to){
                    //save the final total cost of the to element
                    totalCost = minCostTrack;
                }
            }
            if(!isTrack){
                return "No hay camino";
            }
            //get the fil element to search it's min cost in colum
            auxFil = indexMin;
            if(auxFil == from){
                arrVertexReverse.add(auxFil);
            }
        }while(auxFil != from);
        return getStringResolve(arrVertexReverse, totalCost, locaciones);

    }

    public int calculateMinCostColumVertex(int[][] matrixAux, int countVisitedVertex, int vertex){
        int minCost = 100000;
        if(countVisitedVertex != 0){
            for(int f = 0; f < nodesQuantity; f++){
                if(matrixAux[f][vertex] < minCost
                        && matrixAux[f][vertex] != 0 ){
                    minCost = matrixAux[f][vertex];
                }
            }
            if(minCost == 100000){
                return -1;
            }
            return minCost;
        }else{
            return 0;
        }
    }

    public String getStringResolve(ArrayList<Integer> pathReverse, int totalCost, String locationString){
        String[] locationsNames = locationString.split(",");
        String result = "\nCamino y costo mínimo desde "+locationsNames[pathReverse.get(pathReverse.size()-1)]+" hasta "
                + locationsNames[pathReverse.get(0)]+" es: \n";
        for(int i= pathReverse.size()-1; i >= 0 ;i--) {
            result += "" +
                    locationsNames[pathReverse.get(i)] + (i == 0 ? "" : "->");
        }
        result +=" costo: " +totalCost;
        return result;
    }

    public void printMatrizAux(int[][] matrix){
        for(int f = 0; f < nodesQuantity; f++){
            for(int c = 0; c < nodesQuantity; c++){
                System.out.print(matrix[f][c] +"   ");
            }
            System.out.println("");
        }
    }

}
