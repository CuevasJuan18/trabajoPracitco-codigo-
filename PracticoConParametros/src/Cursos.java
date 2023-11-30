import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public abstract class Cursos {

    //Atributos
    protected String nombre;
    protected int codigoCatedra;
    protected String descripcion;
    protected String objetivo;
    protected String publico;
    protected double costo;
    private static List<Cursos> listaCursos = new ArrayList<>();
    private ArrayList<Alumno> listaAlumnosInscritos;
    protected static List<CursoPresencial> listaCursosPresenciales = new ArrayList<>();
    protected static List<CursoOnline> listaCursosOnline = new ArrayList<>();

    //Constructores
    public Cursos(String nombre, int codigoCatedra, String descripcion, String objetivo, String publico, double costo) {
        this.listaAlumnosInscritos = new ArrayList<>();
    }

    public Cursos() {
    }

    //Getter y Setter

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getCodigoCatedra() {
        return codigoCatedra;
    }

    public void setCodigoCatedra(int codigoCatedra) {
        this.codigoCatedra = codigoCatedra;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getObjetivo() {
        return objetivo;
    }

    public void setObjetivo(String objetivo) {
        this.objetivo = objetivo;
    }

    public String getPublico() {
        return publico;
    }

    public void setPublico(String publico) {
        this.publico = publico;
    }

    public double getCosto() {
        return costo;
    }

    public void setCosto(double costo) {
        this.costo = costo;
    }

    public ArrayList<Alumno> getAlumnosInscritos() {
        return listaAlumnosInscritos;
    }

    public static List<Cursos> getListaCursos() {
        return listaCursos;
    }

    //Metodos

    //inscribirAlumno
    public void inscribirAlumno(Alumno alumno) {
        listaAlumnosInscritos.add(alumno);
    }
    public abstract String obtenerInformacion();


    public int obtenerCantidadInscritos() {
        Set<Alumno> alumnosInscritosUnicos = new HashSet<>();

        // Itera sobre todas las instancias de Cursos en la lista
        for (Cursos curso : listaCursos) {
            // Verifica si la instancia es de tipo CursoPresencial o CursoOnline
            if (curso instanceof CursoPresencial) {
                // Agrega los alumnos inscritos del CursoPresencial a la lista única
                alumnosInscritosUnicos.addAll(((CursoPresencial) curso).getAlumnosInscritos());
            } else if (curso instanceof CursoOnline) {
                // Agrega los alumnos inscritos del CursoOnline a la lista única
                alumnosInscritosUnicos.addAll(((CursoOnline) curso).getAlumnosInscritos());
            }
        }

        return alumnosInscritosUnicos.size();
    }

    public static Cursos encontrarCursoMasVendido(List<Cursos> cursos) {
        if (cursos.isEmpty()) {
            return null; // Devuelve null si no hay cursos
        }

        // Encuentra la cantidad máxima de inscritos
        int maxInscritos = cursos.stream()
                .mapToInt(Cursos::obtenerCantidadInscritos)
                .max()
                .orElse(0);

        // Encuentra el primer curso con la cantidad máxima de inscritos
        return cursos.stream()
                .filter(curso -> curso.obtenerCantidadInscritos() == maxInscritos)
                .findFirst()
                .orElse(null);
    }
}
