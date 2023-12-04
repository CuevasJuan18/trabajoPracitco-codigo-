
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.time.temporal.Temporal;
import java.util.*;

public class Alumno extends Persona {

    //Atributos
    private String legajo;
    private List<Cursos> cursosInscritos;
    private boolean accesoDescuento;

    //Constructor
    public Alumno(String nombre, String apellido, String dni, Date crearFecha, int edad) {
        super(nombre, apellido, dni, crearFecha, edad);
        this.cursosInscritos = new ArrayList<>();
    }

    private Alumno(Builder builder) {
        super(builder.nombre, builder.apellido, builder.dni, builder.fechaNacimiento, builder.edad);
        this.cursosInscritos = new ArrayList<>();
        this.legajo = generarLegajo(builder.dni);
        this.accesoDescuento = false;
    }

    //Getter y Setter

    public String getLegajo() {
        return legajo;
    }

    public void setAccesoDescuento(boolean accesoDescuento) {
        this.accesoDescuento = accesoDescuento;
    }

    public List<Cursos> getCursosInscritos() {
        return cursosInscritos;
    }

    public void agregarCursoInscrito(Cursos curso) {
        cursosInscritos.add(curso);
    }

    //Metodos

    public String toString() {
        return "Alumno Nombre = " + this.getNombre() +
                "\nAlumno Apellido = " + this.getApellido() +
                "\nAlumno Dni = " + this.getDni() +
                "\nAlumno Fecha = " + this.getFechaNacimiento() +
                "\nAlumno Edad = " + this.getEdad() +
                "\nAlumno Legajo = " + this.getLegajo();
    }

    //CrearLegajo
    public String generarLegajo(String dni) {
        String ultimoDigito = getDni().substring(getDni().length() - 3);
        SimpleDateFormat dateFormat = new SimpleDateFormat("yy"); // Obtener los dos primeros dígitos del año actual
        String anio = dateFormat.format(new Date());
        legajo = ultimoDigito + "." + anio;
        return legajo;
    }

    //Descuento
    public boolean tieneAccesoDescuento() {
        System.out.println("Evaluando descuento para " + getNombre() + ": " + (getCursosInscritos().size() > 1));
        return getCursosInscritos().size() > 1;
    }

    public static int cantidadAlumnosConDescuento(List<Alumno> alumnos) {
        int count = 0;
        for (Alumno alumno : alumnos) {
            if (alumno.tieneAccesoDescuento()) {
                count++;
            }
        }
        return count;
    }

    public static class Builder {
        private String nombre;
        private String apellido;
        private String dni;
        private Date fechaNacimiento;
        private int edad;

        public Builder(String nombre, String apellido, String dni) {
            this.nombre = nombre;
            this.apellido = apellido;
            this.dni = dni;
        }

        public Builder fechaNacimiento(Date fechaNacimiento) {
            this.fechaNacimiento = fechaNacimiento;
            return this;
        }

        public Builder edad(int edad) {
            this.edad = edad;
            return this;
        }

        public Alumno create() {
            return new Alumno(this);
        }
    }

    //PatronFactory
    // Método estático para crear instancias de Alumno utilizando la fábrica
    public static Alumno createAlumno(AlumnoFactory factory, String nombre, String apellido, String dni, int year, int month, int day, int edad) {
        return factory.createAlumno(nombre, apellido, dni, year, month, day, edad);
    }

    // Interfaz de la fábrica
    public interface AlumnoFactory {
        Alumno createAlumno(String nombre, String apellido, String dni, int year, int month, int day, int edad);
    }

    // Implementación concreta de la fábrica
    public static class ConcreteAlumnoFactory implements AlumnoFactory {
        @Override
        public Alumno createAlumno(String nombre, String apellido, String dni, int year, int month, int day, int edad) {
            return new Alumno.Builder(nombre, apellido, dni)
                    .fechaNacimiento(crearFecha(year, month, day))
                    .edad(edad)
                    .create();
        }

        private static Date crearFecha(int year, int month, int day) {
            Calendar calendar = Calendar.getInstance();
            calendar.set(year, month, day);
            return calendar.getTime();
        }
    }
}


