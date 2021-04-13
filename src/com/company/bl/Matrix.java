package com.company.bl;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Matrix {

    private int[][] matrix;
    private int nodesQuantity;
    private List conjS;
    private List conjCompS;
    private List tracks;
    private String tmp;
    private String fileName;

    public Matrix() throws IOException{
        this.nodesQuantity = 25;
        this.matrix = new int[this.nodesQuantity][this.nodesQuantity];
        this.conjS = new ArrayList();
        this.conjCompS = new ArrayList();
        this.tracks = new ArrayList();
        this.fileName = "locationCosts.txt";
        loadCostsFromFile();
    }

    private void loadCostsFromFile() throws IOException{
        String fileName;
        String stringAux;
        StringTokenizer tokenizer;
        int f=0;
        int c;

        try{
            fileName= this.fileName;
            FileReader fileReaderr = new FileReader(fileName);
            BufferedReader bufferedReader = new  BufferedReader(fileReaderr);
            while((stringAux=bufferedReader.readLine())!=null){
                tokenizer = new StringTokenizer(stringAux);
                c=0;
                while(tokenizer.hasMoreTokens()){
                    matrix[f][c]=Integer.valueOf(tokenizer.nextToken()).intValue();
                    c++;
                }
                f++;
            }
        }
        catch(FileNotFoundException e){
           throw e;
        }
        catch(IOException e1){
            throw e1;
        }
        catch(NumberFormatException e2){
            throw e2;

        }
    }

    public String getMinCost(int from, int to, String locations){
        matrix[from][from]=0;
        return resolve(from, to, locations);
    }

    private int min(int dest){
        int min=-1;
        int nod=0;
        int originNode=-1;
        int aux;

        //Loop for the visited vertexes
        for(int i=0;i<conjS.size();i++){
            //nod=Integer.valueOf((String)(conj_S.get(i))).intValue();
            //get the visited vertex
            nod = (int) conjS.get(i);

            /*
            if there's cost from visited vertex to itself
            AND
            there's cost from the visited Vertex to the destiny vertex
                -> aux = add between from visited vertex to itself AND from the visitedVertex to the destiny vertex(TOTAL COST)
            else
                aux = -1 (there's no track)
            */
            if(matrix[nod][nod]!=-1 && matrix[nod][dest]!=-1)
                aux=matrix[nod][nod]+matrix[nod][dest];
            else
                aux=-1;

            /* if aux is < min AND aux != -1
                OR
                min == -1
                    min = aux
                    originNode = nod
             Here we're gonna save the min cost with the visited vertex
            * */
            if((aux<min && aux!=-1)||min==-1){
                min=aux;
                originNode=nod;
            }
        }

        /* if there's is a min cost
         *   ->Save in matrix[destiny][destiny] the min cost
         *   ->Save in tracks, int destiny's track the previous visited vertex with the min cost        *
         * */
        if(min != -1){
            matrix[dest][dest]=min;
            tracks.set(dest,String.valueOf(originNode));
        }
        //return the min, it could be -1 or whatever the min cost is
        return min;
    }

    private String resolve(int from, int to, String locations){
        int nod;
        int minimo;
        int aux;
        int nodCambio=0;
        int intento;

        //Unvisited vertexes
        conjCompS.clear();
        tracks.clear();
        //visited vertexes
        conjS.clear();
        //initialize lists
        for(int i = 0; i < nodesQuantity; i++){
            if(i != from)
                conjCompS.add(i);
            else
                conjS.add(i);
            tracks.add("");
        }

        //diksjtra loop
        for(int i = 0; i< nodesQuantity; i++){
            minimo=-1;

            //unvisited vertexes loop
            for(int j=0; j<conjCompS.size() ;j++){
                //get the unvisited vertex
                //nod=Integer.valueOf((String)(conjComp_S.get(j))).intValue();
                nod = (int)conjCompS.get(j);

                //calculate the min cost associate to the destiny vertex
                aux=min(nod);

                /*
                 * save aux as minimo when aux is < minimo) and nodCambio will be the unvisited vertex with the min cost
                 * */
                if(minimo == -1 || (aux < minimo && aux != -1)){
                    minimo=aux;
                    nodCambio=j;
                }
            }
            /*-save the min cost if minimo is not -1
             -add to visited vertex the unvisited vertex with the min cost
             -remove from the unvisited vertex the unvisited vertex with the min cost*/
            if(minimo != -1){
                conjS.add(conjCompS.get(nodCambio));
                conjCompS.remove(nodCambio);
            }
        }

        //after visit all the vertex

        //get min track
        //for for set the tracks from the origin vertex to any vertex
        for(int k = 0; k< tracks.size(); k++) {
            /*if k is not the actual vertex:
             *   tmp = previous vertex + to itself
             *   and in tracks set in the itself index the tmp(path)
             * */
            if (k != from) {
                tmp = tracks.get(k) + String.valueOf(k);
                //tmp = (String) (tracks.get(k)) + (char) (k + 65);
                tracks.set(k, tmp);
            }
        }

        /*iterate tracks elements
         * */
        for(int j = 0; j< tracks.size(); j++) {
            /*if j is not the origin vertex:
             *   tmp = get the itself track
             *
             * */
            if (j != from) {
                intento = 0;
                tmp = (String) (tracks.get(j));

                // while the first element of the track is not the origin vertex and try is < 10
                while (Character.getNumericValue(tmp.charAt(0)) != from && intento < 10) {
                    //get the index of the first element of the track
                    aux = Character.getNumericValue(tmp.charAt(0));
                    //get entire track: concat track of the index element and the index of the last element of the track

                    tmp = tracks.get(aux) + tmp.substring(1);
                    if (++intento == 10)
                        tmp = "*".concat(tmp);
                }

                if (Character.getNumericValue(tmp.charAt(tmp.length() - 1)) == to && tmp.charAt(0) != '*') {
                    return toStringMinCost(tmp, j, to, locations);
                }

            }
        }

        return "No hay camino";
    }

    private String toStringMinCost(String cam, int from, int to, String locations){
        String[] locationsNames = locations.split(",");
        String result = "\nCamino y costo mínimo desde "+locationsNames[Character.getNumericValue(cam.charAt(0))]+" hasta "
                + locationsNames[to]+" es: \n";

        for(int i=0;i<cam.length();i++) {
            result += "" +
                    locationsNames[Character.getNumericValue(cam.charAt(i))] + (i == cam.length() - 1 ? "" : "->");
        }
        result +=" costo: "+matrix[from][from];
        return result;
    }

}
