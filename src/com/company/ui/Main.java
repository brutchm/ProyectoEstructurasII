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
            out.println(controller.getMinCost(0,6));
        }catch (Exception e){
            out.println("Error: "+e);
        }



    }
}
