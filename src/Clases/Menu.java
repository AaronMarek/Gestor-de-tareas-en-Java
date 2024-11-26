package Clases;

import java.time.LocalDateTime;
import java.util.Scanner;
import java.util.UUID;

/**
 * Clase Menu: Interfaz de consola para interactuar con TaskManager.
 * Permite al usuario realizar varias operaciones en el sistema de gestión
 * de tareas de manera dinámica.
 * 
 * @author [Aaron Marek]
 * @version 1.0
 */
public class Menu {
    private final TaskManager gestor;
    private final Scanner scanner;

    /**
     * Constructor del menú.
     * Inicializa el gestor de tareas y el escáner.
     */
    public Menu() {
        this.gestor = new TaskManager();
        this.scanner = new Scanner(System.in);
    }

    /**
     * Muestra las opciones del menú principal y gestiona la interacción.
     */
    public void iniciar() {
        int opcion;
        do {
            mostrarOpciones();
            opcion = leerOpcion();
            procesarOpcion(opcion);
        } while (opcion != 6);
    }

    /**
     * Muestra las opciones disponibles en el menú.
     */
    private void mostrarOpciones() {
        System.out.println("\n--- MENÚ PRINCIPAL ---");
        System.out.println("1. Agregar tarea");
        System.out.println("2. Eliminar tarea");
        System.out.println("3. Buscar tarea por ID");
        System.out.println("4. Listar tareas por estado");
        System.out.println("5. Listar tareas por prioridad");
        System.out.println("6. Salir");
        System.out.print("Seleccione una opción: ");
    }

    /**
     * Lee la opción ingresada por el usuario, asegurando su validez.
     * 
     * @return La opción seleccionada como un entero.
     */
    private int leerOpcion() {
        while (!scanner.hasNextInt()) {
            System.out.print("Por favor, ingrese un número válido: ");
            scanner.next();
        }
        return scanner.nextInt();
    }

    /**
     * Procesa la opción seleccionada por el usuario.
     * 
     * @param opcion Opción seleccionada.
     */
    private void procesarOpcion(int opcion) {
        scanner.nextLine(); // Limpiar el buffer
        switch (opcion) {
            case 1 -> agregarTarea();
            case 2 -> eliminarTarea();
            case 3 -> buscarTarea();
            case 4 -> listarTareasPorEstado();
            case 5 -> listarTareasPorPrioridad();
            case 6 -> System.out.println("Saliendo del programa. ¡Adiós!");
            default -> System.out.println("Opción no válida. Intente nuevamente.");
        }
    }

    private void agregarTarea() {
        System.out.print("Ingrese el título de la tarea: ");
        String titulo = scanner.nextLine();

        System.out.print("Ingrese la descripción de la tarea: ");
        String descripcion = scanner.nextLine();

        System.out.print("Ingrese la fecha de vencimiento (AAAA-MM-DD HH:MM): ");
        String fechaInput = scanner.nextLine();
        LocalDateTime fechaVencimiento = LocalDateTime.parse(fechaInput.replace(" ", "T"));

        System.out.print("Seleccione la prioridad (BAJA, MEDIA, ALTA, URGENTE): ");
        Prioridad prioridad = Prioridad.valueOf(scanner.nextLine().toUpperCase());

        Task nuevaTarea = new Task(titulo, descripcion, fechaVencimiento, prioridad);
        gestor.agregarTarea(nuevaTarea);
        System.out.println("Tarea agregada exitosamente: " + nuevaTarea);
    }

    private void eliminarTarea() {
        System.out.print("Ingrese el ID de la tarea a eliminar: ");
        UUID id = UUID.fromString(scanner.nextLine());

        gestor.eliminarTarea(id);
        System.out.println("Tarea eliminada (si existía).");
    }

    private void buscarTarea() {
        System.out.print("Ingrese el ID de la tarea a buscar: ");
        UUID id = UUID.fromString(scanner.nextLine());

        Task tarea = gestor.buscarTarea(id);
        if (tarea != null) {
            System.out.println("Tarea encontrada: " + tarea);
        } else {
            System.out.println("No se encontró ninguna tarea con ese ID.");
        }
    }

    private void listarTareasPorEstado() {
        System.out.print("Seleccione el estado (PENDIENTE, EN_PROGRESO, COMPLETADA, CANCELADA): ");
        EstadoTarea estado = EstadoTarea.valueOf(scanner.nextLine().toUpperCase());

        gestor.listarTareasPorEstado(estado).forEach(System.out::println);
    }

    private void listarTareasPorPrioridad() {
        System.out.print("Seleccione la prioridad (BAJA, MEDIA, ALTA, URGENTE): ");
        Prioridad prioridad = Prioridad.valueOf(scanner.nextLine().toUpperCase());

        gestor.listarTareasPorPrioridad(prioridad).forEach(System.out::println);
    }

}
