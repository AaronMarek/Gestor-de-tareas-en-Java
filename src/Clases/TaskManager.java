package Clases;

import java.util.*;
import java.util.stream.Collectors;


/**
 * Clase TaskManager: Gestiona el conjunto de tareas.
 * Proporciona métodos para agregar, eliminar, buscar y filtrar tareas.
 * 
 * @author [Aaron Marek]
 * @version 1.0
 */
public class TaskManager {
    // Almacena todas las tareas del sistema
    private List<Task> tareas;

    /**
     * Constructor que inicializa la lista de tareas.
     */
    public TaskManager() {
        this.tareas = new ArrayList<>();
    }

    /**
     * Agrega una nueva tarea al sistema de gestión.
     * @param tarea Tarea a ser agregada
     */
    public void agregarTarea(Task tarea) {
        tareas.add(tarea);
    }

    /**
     * Elimina una tarea específica basándose en su ID único.
     * @param id Identificador de la tarea a eliminar
     */
    public void eliminarTarea(UUID id) {
        // Usa removeIf para eliminar la tarea que coincida con el ID
        tareas.removeIf(tarea -> tarea.getId().equals(id));
    }

    /**
     * Busca y recupera una tarea específica por su ID.
     * @param id Identificador de la tarea buscada
     * @return Tarea encontrada, o null si no existe
     */
    public Task buscarTarea(UUID id) {
        return tareas.stream()
                .filter(tarea -> tarea.getId().equals(id))
                .findFirst()
                .orElse(null);
    }

    /**
     * Lista todas las tareas con un estado específico.
     * @param estado Estado de las tareas a filtrar
     * @return Lista de tareas con el estado indicado
     */
    public List<Task> listarTareasPorEstado(EstadoTarea estado) {
        return tareas.stream()
                .filter(tarea -> tarea.getEstado().equals(estado))
                .collect(Collectors.toList());
    }

    /**
     * Lista todas las tareas con una prioridad específica.
     * @param prioridad Nivel de prioridad para filtrar
     * @return Lista de tareas con la prioridad indicada
     */
    public List<Task> listarTareasPorPrioridad(Prioridad prioridad) {
        return tareas.stream()
                .filter(tarea -> tarea.getPrioridad().equals(prioridad))
                .collect(Collectors.toList());
    }

    /**
     * Obtiene una copia de todas las tareas del sistema.
     * @return Lista con todas las tareas
     */
    public List<Task> getTareas() {
        return new ArrayList<>(tareas);
    }
}