package com.pluralsight;

import com.googlecode.lanterna.screen.Screen;
import com.googlecode.lanterna.screen.TerminalScreen;
import com.googlecode.lanterna.terminal.DefaultTerminalFactory;
import com.googlecode.lanterna.input.KeyStroke;
import com.googlecode.lanterna.input.KeyType;
import com.googlecode.lanterna.graphics.TextGraphics;
import com.googlecode.lanterna.TextColor;
import java.awt.*;
import java.io.IOException;

public class DisplayScreen {

    public void displayMenu() throws IOException {

        // Create terminal
        Screen screen = new TerminalScreen(
                new DefaultTerminalFactory().createTerminal()
        );

        screen.startScreen();

        int selected = 0;
        
        String[] options = {
                "By price range",
                "By make/modle",
                "By year range",
                "By color",
                "By mileage range",
                "By vehicle type",
                "list All vehicles",
                "Add Vehicle",
                "Remove Vehicle",
                "Exit"
        };

        boolean running = true;

        while (running) {

            screen.clear();

            TextGraphics graphics = screen.newTextGraphics();

            graphics.setForegroundColor(TextColor.ANSI.RED);
            graphics.putString(5, 1, " DEALERSHIP SYSTEM ");
            graphics.putString(5, 3, "Find your Vehicle:");

            // Display menu
            for (int i = 0; i < options.length; i++) {

                if (i == selected) {
                    graphics.putString(5, 5 + i, "> " + options[i]);
                } else {
                    graphics.putString(5, 5 + i, "  " + options[i]);
                }
            }

            graphics.putString(4, 18, "Use UP/DOWN and ENTER");

            screen.refresh();

            // Get keyboard input
            KeyStroke key = screen.readInput();

            switch (key.getKeyType()) {

                case ArrowUp:
                    selected--;

                    if (selected < 0) {
                        selected = options.length - 1;
                    }
                    break;

                case ArrowDown:
                    selected++;

                    if (selected >= options.length) {
                        selected = 0;
                    }
                    break;

                case Enter:

                    switch (selected) {
                        case 0 -> addVehicleScreen(screen);
                        //case 1 -> // by price
                        //case 2 -> //
                        //case 3 ->
                        //case 4 ->
                        //case 5 ->
                        //case 6 ->
                        //case 7 ->
                        case 11 -> running = false;
                        default -> throw new IllegalStateException("Unexpected value: " + selected);
                    }

                    break;

                case Escape:
                    running = false;
                    break;
            }
        }

        screen.stopScreen();
    }

    private String getInput(Screen screen, TextGraphics graphics,
                            int y) throws IOException {

        StringBuilder input = new StringBuilder();

        while (true) {

            // Display what the user types
            graphics.putString(14, y, input.toString() + " ");
            screen.refresh();

            KeyStroke key = screen.readInput();

            if (key.getKeyType() == KeyType.Enter) {
                return input.toString();
            } else if (key.getKeyType() == KeyType.Backspace) {

                if (input.length() > 0) {
                    input.deleteCharAt(input.length() - 1);
                }
            } else if (key.getKeyType() == KeyType.Character) {
                input.append(key.getCharacter());
            } else if (key.getKeyType() == KeyType.Escape) {
                return null;
            }
        }
    }

    private void addVehicleScreen(Screen screen) throws IOException {

        screen.clear();

        TextGraphics graphics = screen.newTextGraphics();

        graphics.putString(3, 2, " ADD VEHICLE ");

        graphics.putString(5, 5, "VIN:");
        graphics.putString(5, 6, "Make:");
        graphics.putString(5, 7, "Model:");
        graphics.putString(5, 8, "Year:");
        graphics.putString(5, 9, "Price:");

        graphics.putString(5, 12, "Press ESC to return to menu");

        screen.refresh();

        screen.refresh();

        // Get vehicle information
        String vin = getInput(screen, graphics, 5);

        if (vin == null) return;

        String make = getInput(screen, graphics, 6);

        if (make == null) return;

        String model = getInput(screen, graphics, 7);

        if (model == null) return;

        String year = getInput(screen, graphics, 8);

        if (year == null) return;

        String price = getInput(screen, graphics, 9);

        if (price == null) return;

        // Display entered information
        screen.clear();

        graphics = screen.newTextGraphics();

        graphics.putString(5, 2, "====== VEHICLE INFORMATION ======");

        graphics.putString(5, 5, "VIN: " + vin);
        graphics.putString(5, 6, "Make: " + make);
        graphics.putString(5, 7, "Model: " + model);
        graphics.putString(5, 8, "Year: " + year);
        graphics.putString(5, 9, "Price: " + price);

        graphics.putString(5, 12, "Press any key to return");

        screen.refresh();

        screen.readInput();

        screen.clear();

        screen.refresh();
    }

    private void searchVehicleScreen(Screen screen) throws IOException {

        screen.clear();

        TextGraphics graphics = screen.newTextGraphics();

        graphics.putString(5, 2, "========== Search vehicle ==========");

        graphics.putString(5, 5, "VIN:");
        graphics.putString(5, 6, "Make:");
        graphics.putString(5, 7, "Model:");
        graphics.putString(5, 8, "Year:");
        graphics.putString(5, 9, "Price:");

        graphics.putString(5, 12, "Press ESC to return to menu");

        screen.refresh();

        screen.refresh();

        // Get vehicle information
        String vin = getInput(screen, graphics, 5);

        if (vin == null) return;

        String make = getInput(screen, graphics, 6);

        if (make == null) return;

        String model = getInput(screen, graphics, 7);

        if (model == null) return;

        String year = getInput(screen, graphics, 8);

        if (year == null) return;

        String price = getInput(screen, graphics, 9);

        if (price == null) return;

        // Display entered information
        screen.clear();

        graphics = screen.newTextGraphics();

        graphics.putString(5, 2, "====== VEHICLE INFORMATION ======");

        graphics.putString(5, 5, "VIN: " + vin);
        graphics.putString(5, 6, "Make: " + make);
        graphics.putString(5, 7, "Model: " + model);
        graphics.putString(5, 8, "Year: " + year);
        graphics.putString(5, 9, "Price: " + price);

        graphics.putString(5, 12, "Press any key to return");

        screen.refresh();

        screen.readInput();

        screen.clear();

        screen.refresh();
    }
}