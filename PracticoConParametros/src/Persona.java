import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;

public class Persona {

    //Atributos
    private String nombre;
    private String apellido;
    private String dni;
    private Date fechaNacimiento;
    private int edad;

    //Constructor
    public Persona(){

    }
    public Persona(String nombre, String apellido, String dni, Date fechaNacimiento, int edad) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.dni = dni;
        this.fechaNacimiento = fechaNacimiento;
        this.edad = edad;
    }

    //Setter y Getter

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getDni() {
        return dni;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }

    public Date getFechaNacimiento() {
        return fechaNacimiento;
    }

    public void setFechaNacimiento(Date fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }

    public int getEdad() {
        return edad;
    }

    public void setEdad(int edad) {
        this.edad = edad;
    }

    //Metodos
    //listarCumpleaños

    static void listarCumpleanosCercanos(List<? extends Persona> personas, Date fechaActual) {
        for (Persona persona : personas) {
            if (proximidadCumpleaños(persona.getFechaNacimiento(), fechaActual)) {
                System.out.println("Cumpleaños cercano para " + persona.getNombre());
                System.out.println("Cumpleaños de " + persona.getNombre() + " En la fecha " + persona.getFechaNacimiento() );
            }
        }
    }

    //proximidadCumpleaños
    private static boolean proximidadCumpleaños(Date fechaNacimiento, Date fechaActual) {
        // Conversión de Date a LocalDate
        LocalDate localFechaNacimiento = fechaNacimiento.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        LocalDate localFechaActual = fechaActual.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();

        LocalDate proximoCumpleaños = localFechaNacimiento.withYear(localFechaActual.getYear());
        if (proximoCumpleaños.isBefore(localFechaActual)) {
            proximoCumpleaños = proximoCumpleaños.plusYears(1);
        }
        LocalDate unaSemanaAntes = localFechaActual.plusDays(7);

        return !proximoCumpleaños.isAfter(unaSemanaAntes);
    }
    // ToString
    public String imprimir(){
        return "Persona.nombre = " + this.nombre +
                "\nPersona.apellido = " + this.apellido +
                "\nPersona.dni = " + this.dni +
                "\nPersona.Fecha = " + this.fechaNacimiento +
                "\nPersona.edad = " + this.edad;
    }
}
