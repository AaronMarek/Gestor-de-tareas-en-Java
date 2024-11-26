package Clases;

/**
 * Enumeración que define los posibles estados de una tarea.
 * Permite un seguimiento claro del progreso de cada tarea.
 * 
 * @author [Aaron Marek]
 * @version 1.0
 */
public enum EstadoTarea {
    PENDIENTE,     // Tarea creada pero no iniciada
    EN_PROGRESO,   // Tarea está siendo trabajada actualmente
    COMPLETADA,    // Tarea finalizada exitosamente
    CANCELADA      // Tarea interrumpida o no será realizada
}