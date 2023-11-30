import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Profesor extends Persona{

    //Atributos
    private String curriculum;
    private List<CursoPresencial> cursosDictados;

    //Constructor
    public Profesor(String nombre, String apellido, String dni, Date fechaNacimiento, int edad, String curriculum) {
        super(nombre, apellido, dni, fechaNacimiento, edad);
        this.curriculum = curriculum;
        this.cursosDictados = new ArrayList<>();
    }

    //Getter Y Setter
    public String getCurriculum() {
        return curriculum;
    }

    public void setCurriculum(String curriculum) {
        this.curriculum = curriculum;
    }

    //Metodos
    public List<CursoPresencial> getCursosDictados() {
        return cursosDictados;
    }

    public void inscribirCurso(CursoPresencial curso) {
        cursosDictados.add(curso);
    }
    @Override
    public String toString() {
        return  "Profesor Nombre = " + this.getNombre() +
                "\nProfesor Apellido = " + this.getApellido() +
                "\nProfesor Dni = " + this.getDni() +
                "\nProfesor Fecha = " + this.getFechaNacimiento() +
                "\nProfesor Edad = " + this.getEdad() +
                "\n Profesor Curriculum = " + getCurriculum();
    }
}
