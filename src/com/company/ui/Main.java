package com.company.ui;

import com.company.tl.Controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;

public class Main {

    public static BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
    public static PrintStream out = System.out;
    public static Controller controller;

    public static void main(String[] args) throws IOException
    {
        try{
            controller = new Controller();
            menu();

        }catch (Exception e){
            handleError("Error: "+e);

        }

    }

    public static void menu() throws IOException{
        int option = -1;
        print("Welcome to MAP YOUR DREAMS");
        do{
            print("*********************************************\n" +
                    "Menu - Choose an option\n" +
                    "1- Calculate minimum cost\n" +
                    "2- Search location\n" +
                    "0- Exit");

            option = Integer.parseInt(in.readLine());
            executeOption(option);

        }while(option != 0);

    }

     public static void executeOption(int option) throws IOException{
        switch (option){
            case 1:
                minimunCost();
                break;
            case 2:
                searchLocation();
                break;
            case 0:
                print("Thank you for using our software");
                break;
        }
    }

    public static void minimunCost() throws IOException{
        int from, to;
        locationsMenu();
        print("*********************************************\n");
        print("Write the origin country's code:");
        from = Integer.parseInt(in.readLine());
        print("Write the destiny country's code:");
        to = Integer.parseInt(in.readLine());
        print(controller.getMinCost(from,to));

    }

    public static void locationsMenu(){
        print("Locations shown by hash");
        for (int i = 0; i < 25; i++) {
           print(controller.printLocation(i));
        }
    }

    public static void searchLocation()throws IOException{
        locationsMenu();
        print("If you wanna search by hashing please write down the code of the country");
        int code = Integer.parseInt(in.readLine());
        print(controller.printLocation(code));
    }

    public static void print(String msg){
        out.println(msg);
    }


     public static void handleError(String error){
        print(error);
    }
}
