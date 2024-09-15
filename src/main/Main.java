package main;

import ui.StudentUI;

public class Main {
    public static void main(String[] args) {
        System.out.println("Démarrage du programme...");
        
        StudentUI ui = new StudentUI();
        ui.showMenu();
    }
}
