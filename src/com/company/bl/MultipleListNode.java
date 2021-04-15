package com.company.bl;

public class MultipleListNode {
    private int id;
    private MultipleListArchNode predecessors;
    private MultipleListArchNode succesors;
    private MultipleListNode next;

    public MultipleListNode(){
        this.id=0;
        this.predecessors= null;
        this.succesors= null;
        this.next = null;

    }
    public MultipleListNode(int id){
        this.id= id;
        this.predecessors= null;
        this.succesors= null;
        this.next = null;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public MultipleListArchNode getPredecessors() {
        return predecessors;
    }

    public void setPredecessors(MultipleListArchNode predecessors) {
        this.predecessors = predecessors;
    }

    public MultipleListArchNode getSuccesors() {
        return succesors;
    }

    public void setSuccesors(MultipleListArchNode succesors) {
        this.succesors = succesors;
    }

    public MultipleListNode getNext() {
        return next;
    }

    public void setNext(MultipleListNode next) {
        this.next = next;
    }

    public void addPredecessorArch(MultipleListArchNode arch){
        if(this.predecessors==null){
            this.predecessors=arch;
        }else{
            MultipleListArchNode aux = this.predecessors;
            while(aux.getNext()!=null){
                aux = aux.getNext();
            }
            aux.setNext(arch);
        }
    }

    public void addSuccesorArch(MultipleListArchNode arch){
        if(this.succesors==null){
            this.succesors=arch;
        }else{
            MultipleListArchNode aux = this.succesors;
            while(aux.getNext()!=null){
                aux = aux.getNext();
            }
            aux.setNext(arch);
        }
    }

    public String showPredecessor(String locationsName){
        String result="";
        if (this.predecessors==null){
            result+="| -- ";
        }else{
            MultipleListArchNode aux = this.predecessors;
            while (aux!=null){
                result+=String.format("| %d | %s |->",aux.getCost(), locationsName.split(",")[aux.getVertex().getId()]);
                aux=aux.getNext();
            }
        }
        return result;
    }

    public String showSuccesors(String locationsName){
        String result="";
        if (this.succesors==null){
            result+="| -- ";
        }else{
            MultipleListArchNode aux = this.succesors;
            while (aux!=null){
                result+=String.format("| %d | %s |->",aux.getCost(), locationsName.split(",")[aux.getVertex().getId()]);
                aux=aux.getNext();
            }
        }
        return result;
    }

}
