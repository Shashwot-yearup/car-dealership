package com.pluralsight;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws Exception {

        //Creates a scanner
        Scanner scanner = new Scanner(System.in);

        //Creates the displayScreen
        DisplayScreen screen = new DisplayScreen();

        //Calls the method displayMenu in DisplayScreen
        screen.displayMenu();

        //closing the scanner
        scanner.close();
    }
}