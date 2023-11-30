
import java.util.ArrayList;
import java.util.List;

public class CursoPresencial extends Cursos implements Prototipo {

    //Atributos
    private String dia;
    private String horario;
    private static final int LIMITE_ALUMNOS = 5;  // Tengo q cambiarlo a 15
    private static List<CursoPresencial> listaCursos = new ArrayList<>();
    private ArrayList<Alumno> listaAlumnosInscritos;
    private static List<CursoPresencial> listaCursosPresenciales = new ArrayList<>();
    private List<ObservadorCatedraLlena> observadores = new ArrayList<>();

    private Profesor profesor;

    //Constructores
    public CursoPresencial(String nombre, int codigoCatedra, String descripcion, String objetivo, String publico, double costo, String dia, String horario) {
        this.nombre = nombre;
        this.codigoCatedra = codigoCatedra;
        this.descripcion = descripcion;
        this.objetivo = objetivo;
        this.publico = publico;
        this.costo = costo;
        this.dia = dia;
        this.horario = horario;
        this.listaAlumnosInscritos = new ArrayList<>();
        listaCursos.add(this);
    }

    //Getter y Setter
    public String getDia() {
        return dia;
    }

    public void setDia(String dia) {
        this.dia = dia;
    }

    public String getHorario() {
        return horario;
    }

    public void setHorario(String horario) {
        this.horario = horario;
    }
    public void agregarCursoInscrito(Alumno alumno) {
        listaAlumnosInscritos = new ArrayList<>();
        listaAlumnosInscritos.add(alumno);
    }
    public ArrayList<Alumno> getAlumnosInscritos() {
        return listaAlumnosInscritos;
    }

    //Metodos
    //InscribirAlumno
    public void inscribirAlumno(Alumno alumno) {
        // Verificar cuántos cursos ya tiene inscritos el alumno
        int cursosInscritos = alumno.getCursosInscritos().size();

        if (!estaInscritoEnOtroCurso(alumno) && listaAlumnosInscritos.size() < LIMITE_ALUMNOS) {
            // Calcular el descuento antes de agregar el curso al alumno
            double descuento = cursosInscritos > 0 ? 0.2 : 0.0;
            double costoConDescuento = costo - (costo * descuento);

            // Agregar el curso al alumno
            listaAlumnosInscritos.add(alumno);
            System.out.println("Alumno añadido a listaAlumnosInscritos: " + alumno.getNombre());
            alumno.agregarCursoInscrito(this);

            System.out.println(alumno.getNombre() + " inscrito exitosamente en el curso " +
                    nombre + " (Cátedra " + codigoCatedra + "), Día: " + getDia() + ", Horario: " + getHorario() +
                    ". Costo: " + costo + (descuento > 0 ? ", Costo con descuento: " + costoConDescuento : ""));
        } else if (listaAlumnosInscritos.size() >= LIMITE_ALUMNOS) {
            System.out.println("Cupo lleno para el curso " + nombre + ", Cátedra " + codigoCatedra +
                    ". Se creará un nueva catedra.");
            // crear un nuevo curso con código de catedra distinta
            notificarCatedraLlena();
            CursoPresencial nuevoCurso = crearNuevoCurso();
            nuevoCurso.inscribirAlumno(alumno);
        } else {
            System.out.println("No se puede inscribir a " + alumno.getNombre() +
                    " en el curso " + nombre + " (Cátedra " + codigoCatedra + "). " +
                    "Ya está inscrito en otro curso presencial con el mismo día y horario.");
        }
    }
    //Compara
    private boolean estaInscritoEnOtroCurso(Alumno alumno) {
        for (CursoPresencial curso : listaCursos) {
            if (!curso.equals(this) && curso.listaAlumnosInscritos.contains(alumno) &&
                    curso.getDia().equals(this.getDia()) && curso.getHorario().equals(this.getHorario())) {
                System.out.println("El Alumno " + alumno.getNombre() + " Esta inscrito en el curso " +
                        curso.getNombre() + " con la catedra (numero " + curso.getCodigoCatedra() + ")," +
                        " Ya tiene ocupado el Día: " +
                        curso.getDia() + ", Horario: " + curso.getHorario());
                return true;
            }
        }
        return false;
    }
    @Override
    public String obtenerInformacion() {
        return null;
    }

    //Metodo para Crear CursoNuevo
    public void crearYAgregarNuevaCatedra(Alumno alumno) {
        // Lógica para crear un nuevo curso
        CursoPresencial nuevaCatedra = crearNuevoCurso();
        // Agregar la nueva cátedra a la lista de cursos
        listaCursos.add(nuevaCatedra);
        System.out.println("Nueva cátedra creada con éxito.");
        //Inscribir alumno
        nuevaCatedra.inscribirEnNuevaCatedra(alumno);
    }

    //inscribirCatedra
    public void inscribirEnNuevaCatedra(Alumno alumno) {

    }
    private CursoPresencial crearNuevoCurso(){
        int nuevoCodigoCatedra = getCodigoCatedra() + 1;
        System.out.println("Creando nueva cátedra con código: " + nuevoCodigoCatedra);
        CursoPresencial nuevoCurso = new CursoPresencial(nombre, nuevoCodigoCatedra, descripcion, objetivo, publico, costo, dia, horario);
        listaCursos.add(nuevoCurso);
        return nuevoCurso;
    }

    //AsignarDocente
    public void asignarDocente(Profesor profesor) {
        // Verificar si el docente ya tiene un curso en el mismo horario
        if (!tieneProblemaDeHorario(profesor)) {
            this.profesor = profesor;
            profesor.inscribirCurso(this);
            System.out.println("Profesor " + profesor.getNombre() + " asignado al curso " +
                    nombre + " (Cátedra " + codigoCatedra + ").");
        } else {
            System.out.println("El profesor " + profesor.getNombre() +
                    " ya tiene un curso en el mismo horario.");
        }
    }
    //Compara la hora y el dia
    private boolean tieneProblemaDeHorario(Profesor profesor) {
        for (CursoPresencial curso : profesor.getCursosDictados()) {
            if (curso != this && curso.getDia().equals(this.getDia()) && curso.getHorario().equals(this.getHorario())) {
                return true; // Existe un problema de horario
            }
        }
        return false; // No hay problema de horario
    }

    //toString
    @Override
    public String toString() {
        return "Curso Presencial: " + nombre + ", Cátedra " + codigoCatedra + ", Día: " + dia + ", Horario: " + horario;
    }

    @Override
    public Prototipo clonar() {
        try {
            return (CursoPresencial) super.clone();
        } catch (CloneNotSupportedException e) {
            return null;
        }
    }

    //Patron observer
    public interface ObservadorCatedraLlena {
        void catedraLlena(CursoPresencial curso);
    }
    // Método para agregar observadores
    public void agregarObservador(ObservadorCatedraLlena observador) {
        observadores.add(observador);
    }

    // Método para quitar observadores
    public void quitarObservador(ObservadorCatedraLlena observador) {
        observadores.remove(observador);
    }

    // Método para notificar a los observadores cuando la cátedra está llena
    private void notificarCatedraLlena() {
        for (ObservadorCatedraLlena observador : observadores) {
            observador.catedraLlena(this);
        }
    }

}
