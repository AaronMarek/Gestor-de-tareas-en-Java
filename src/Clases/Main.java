package Clases;

/**
 * Clase Main: Punto de entrada del programa.
 * Simplemente inicia el menú para que el usuario pueda interactuar con el sistema.
 * 
 * @author [Aaron Marek]
 * @version 1.0
 */
public class Main {
    public static void main(String[] args) {
        // Crear una instancia del menú
        Menu menu = new Menu();

        // Iniciar el menú
        menu.iniciar();
    }
}