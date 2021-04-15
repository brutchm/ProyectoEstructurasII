package com.company.bl;

public class MultipleList {

    private MultipleListNode list;
    private int vertexQuantity;
    private String locationsName;

    public MultipleList(){
        this.list = null;
        this.vertexQuantity = 0;
        this.locationsName = "";
    }

    public MultipleList(String locationsName){
        this.list = null;
        this.vertexQuantity = 0;
        this.locationsName = locationsName;
    }

    public boolean isEmptyTheList(){
        if(this.list == null){
            return true;
        }
        return false;
    }

    public void addVertex(int value){
        this.vertexQuantity++;

        MultipleListNode tmp = new MultipleListNode(value);
        if (this.list == null){
            this.list = tmp;
        }else{
            MultipleListNode aux = this.list;
            while(aux.getNext() != null){
                aux = aux.getNext();
            }
            aux.setNext(tmp);
        }

    }

    public boolean addArchVertex(int idOriginVertex, int idDestinyVertex, int cost){

        MultipleListNode aux = this.list;
        MultipleListNode origin = null;
        MultipleListNode destiny = null;

        //Recorrer lista para encontrar vertices de origen y destino
        while(aux!=null){
            if(aux.getId()== idOriginVertex){
                origin = aux;
            }
            if(aux.getId()== idDestinyVertex){
                destiny = aux;
            }
            aux = aux.getNext();
        }

        MultipleListArchNode auxS = new MultipleListArchNode(cost, destiny);
        MultipleListArchNode auxP =  new MultipleListArchNode(cost, origin);

        origin.addSuccesorArch(auxS);
        destiny.addPredecessorArch(auxP);

        return true;

    }

    public String getVertex(){
        String resultado = "**********Lista de vértices**********\n"
                + "| Vertice | Valor |\n";
        MultipleListNode aux = this.list;

        while(aux !=null){
            resultado += String.format("| %d | %s |\n", aux.getId(), this.locationsName.split(",")[aux.getId()]);
            aux = aux.getNext();
        }

        return resultado;
    }

    public String showMultipleList(){
        String result = "********** Multiple Linked list**********\n";

        MultipleListNode aux = this.list;
        while(aux !=null){
            result += aux.showPredecessor(this.locationsName);
            result += String.format("P| %d | %s |S", aux.getId(), this.locationsName.split(",")[aux.getId()]);
            result += aux.showSuccesors(this.locationsName)+"\n" ;
            aux = aux.getNext();
        }


        return result;

    }

    public String showAdyLocations(int id){
        String result = "********** Multiple Linked list**********\n";

        MultipleListNode aux = this.list;
        while(aux !=null){
            if(aux.getId() == id )  {
                result += aux.showPredecessor(this.locationsName);
                result += String.format("P| %d | %s |S", aux.getId(), this.locationsName.split(",")[aux.getId()]);
                result += aux.showSuccesors(this.locationsName)+"\n" ;

                return result;

            }
            aux = aux.getNext();
        }

        return result;
    }

}
