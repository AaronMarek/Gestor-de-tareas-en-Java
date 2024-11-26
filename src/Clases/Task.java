package Clases;

import java.time.LocalDateTime;
import java.util.UUID;
import java.util.Objects;

/**
 * Clase Task: Representa una tarea individual en el sistema de gestión.
 * 
 * Esta clase encapsula toda la información y comportamiento relacionado 
 * con una tarea específica, proporcionando métodos para gestionar 
 * y manipular sus propiedades.
 * s
 * @author [Aaron Marek]
 * @version 1.0
 */
public class Task {
    /**
     * Identificador único de la tarea.
     * Generado automáticamente para garantizar la unicidad.
     */
    private final UUID id;
    
    /**
     * Título de la tarea.
     * Proporciona una descripción corta o nombre de la tarea.
     */
    private String titulo;
    
    /**
     * Descripción detallada de la tarea.
     * Permite una explicación más completa de los objetivos o requisitos.
     */
    private String descripcion;
    
    /**
     * Fecha y hora de creación de la tarea.
     * Registra automáticamente el momento de su creación.
     */
    private final LocalDateTime fechaCreacion;
    
    /**
     * Fecha y hora límite para completar la tarea.
     * Indica el plazo máximo para finalizar la tarea.
     */
    private LocalDateTime fechaVencimiento;
    
    /**
     * Estado actual de la tarea.
     * Representa la etapa actual en el ciclo de vida de la tarea.
     */
    private EstadoTarea estado;
    
    /**
     * Prioridad de la tarea.
     * Define la importancia o urgencia de la tarea.
     */
    private Prioridad prioridad;

    /**
     * Constructor principal para crear una nueva tarea.
     * 
     * Inicializa una tarea con los parámetros proporcionados, 
     * generando un ID único y estableciendo la fecha de creación.
     * 
     * @param titulo Título descriptivo de la tarea (no debe ser nulo o vacío)
     * @param descripcion Descripción detallada de la tarea
     * @param fechaVencimiento Fecha límite para completar la tarea
     * @param prioridad Nivel de prioridad de la tarea
     * @throws IllegalArgumentException si el título es nulo o vacío
     */
    public Task(String titulo, String descripcion, LocalDateTime fechaVencimiento, Prioridad prioridad) {
        // Validaciones de entrada
        if (titulo == null || titulo.trim().isEmpty()) {
            throw new IllegalArgumentException("El título de la tarea no puede ser nulo o vacío");
        }

        // Genera un ID único para cada tarea
        this.id = UUID.randomUUID();
        
        this.titulo = titulo;
        this.descripcion = descripcion != null ? descripcion : "";
        
        // Registra automáticamente la fecha de creación
        this.fechaCreacion = LocalDateTime.now();
        
        this.fechaVencimiento = fechaVencimiento;
        
        // Estado inicial de toda tarea es PENDIENTE
        this.estado = EstadoTarea.PENDIENTE;
        
        // Establece la prioridad, con BAJA como valor predeterminado si es nulo
        this.prioridad = prioridad != null ? prioridad : Prioridad.BAJA;
    }

    /**
     * Obtiene el identificador único de la tarea.
     * 
     * @return UUID identificador de la tarea
     */
    public UUID getId() {
        return id;
    }

    /**
     * Obtiene el título de la tarea.
     * 
     * @return String título de la tarea
     */
    public String getTitulo() {
        return titulo;
    }

    /**
     * Establece un nuevo título para la tarea.
     * 
     * @param titulo Nuevo título de la tarea
     * @throws IllegalArgumentException si el título es nulo o vacío
     */
    public void setTitulo(String titulo) {
        if (titulo == null || titulo.trim().isEmpty()) {
            throw new IllegalArgumentException("El título de la tarea no puede ser nulo o vacío");
        }
        this.titulo = titulo;
    }

    /**
     * Obtiene la descripción de la tarea.
     * 
     * @return String descripción de la tarea
     */
    public String getDescripcion() {
        return descripcion;
    }

    /**
     * Establece una nueva descripción para la tarea.
     * 
     * @param descripcion Nueva descripción de la tarea
     */
    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion != null ? descripcion : "";
    }

    /**
     * Obtiene la fecha de creación de la tarea.
     * 
     * @return LocalDateTime fecha de creación
     */
    public LocalDateTime getFechaCreacion() {
        return fechaCreacion;
    }

    /**
     * Obtiene la fecha de vencimiento de la tarea.
     * 
     * @return LocalDateTime fecha de vencimiento
     */
    public LocalDateTime getFechaVencimiento() {
        return fechaVencimiento;
    }

    /**
     * Establece una nueva fecha de vencimiento para la tarea.
     * 
     * @param fechaVencimiento Nueva fecha de vencimiento
     * @throws IllegalArgumentException si la fecha es anterior a la fecha de creación
     */
    public void setFechaVencimiento(LocalDateTime fechaVencimiento) {
        if (fechaVencimiento != null && fechaVencimiento.isBefore(this.fechaCreacion)) {
            throw new IllegalArgumentException("La fecha de vencimiento no puede ser anterior a la fecha de creación");
        }
        this.fechaVencimiento = fechaVencimiento;
    }

    /**
     * Obtiene el estado actual de la tarea.
     * 
     * @return EstadoTarea estado de la tarea
     */
    public EstadoTarea getEstado() {
        return estado;
    }

    /**
     * Establece un nuevo estado para la tarea.
     * 
     * @param estado Nuevo estado de la tarea
     * @throws IllegalArgumentException si el estado es nulo
     */
    public void setEstado(EstadoTarea estado) {
        if (estado == null) {
            throw new IllegalArgumentException("El estado de la tarea no puede ser nulo");
        }
        this.estado = estado;
    }

    /**
     * Obtiene la prioridad de la tarea.
     * 
     * @return Prioridad nivel de prioridad de la tarea
     */
    public Prioridad getPrioridad() {
        return prioridad;
    }

    /**
     * Establece una nueva prioridad para la tarea.
     * 
     * @param prioridad Nueva prioridad de la tarea
     * @throws IllegalArgumentException si la prioridad es nula
     */
    public void setPrioridad(Prioridad prioridad) {
        if (prioridad == null) {
            throw new IllegalArgumentException("La prioridad de la tarea no puede ser nula");
        }
        this.prioridad = prioridad;
    }

    /**
     * Verifica si la tarea está vencida comparando la fecha actual 
     * con la fecha de vencimiento.
     * 
     * @return boolean true si la tarea está vencida, false en caso contrario
     */
    public boolean estaVencida() {
        return fechaVencimiento != null && LocalDateTime.now().isAfter(fechaVencimiento);
    }

    /**
     * Compara esta tarea con otro objeto para determinar su igualdad.
     * Dos tareas son iguales si tienen el mismo ID.
     * 
     * @param o Objeto a comparar
     * @return boolean true si los objetos son iguales, false en caso contrario
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Task task = (Task) o;
        return Objects.equals(id, task.id);
    }

    /**
     * Genera un código hash para la tarea basado en su ID único.
     * 
     * @return int código hash de la tarea
     */
    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    /**
     * Proporciona una representación en cadena de la tarea.
     * Incluye información relevante para depuración e impresión.
     * 
     * @return String representación de la tarea
     */
    @Override
    public String toString() {
        return "Task{" +
                "id=" + id +
                ", titulo='" + titulo + '\'' +
                ", estado=" + estado +
                ", prioridad=" + prioridad +
                ", vencimiento=" + fechaVencimiento +
                '}';
    }
}