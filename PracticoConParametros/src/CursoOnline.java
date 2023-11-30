import java.util.ArrayList;
import java.util.List;

public class CursoOnline extends Cursos {

    //Atributos
    protected List<Alumno> alumnosInscritos;

    public ArrayList<Alumno> getAlumnosInscritos() {
        return (ArrayList<Alumno>) alumnosInscritos;
    }

    private String linkAcceso;
    private String fechaCursado;

    //Constructor
    public CursoOnline(String nombre, int codigoCatedra, String descripcion, String objetivo, String publico, double costo, String linkAcceso, String fechaCursado) {
        this.nombre = nombre;
        this.codigoCatedra = codigoCatedra;
        this.descripcion = descripcion;
        this.objetivo = objetivo;
        this.publico = publico;
        this.costo = costo;
        this.linkAcceso = linkAcceso;
        this.fechaCursado = fechaCursado;
        this.alumnosInscritos = new ArrayList<>();
    }
    public CursoOnline(String linkAcceso, String fechaCursado) {
        this.linkAcceso = linkAcceso;
        this.fechaCursado = fechaCursado;
    }

    //Getter y Setter
    public String getLinkAcceso() {
        return linkAcceso;
    }

    public void setLinkAcceso(String linkAcceso) {
        this.linkAcceso = linkAcceso;
    }

    public String getFechaCursado() {
        return fechaCursado;
    }

    public void setFechaCursado(String fechaCursado) {
        this.fechaCursado = fechaCursado;
    }

    //Metodos

    @Override
    public String obtenerInformacion() {
        return null;
    }


    public void inscribirAlumno(Alumno alumno) {
        alumnosInscritos.add(alumno);
        System.out.println(alumno.getNombre() + " inscrito en el curso online " + nombre + ", Cátedra " + "(" + codigoCatedra +")"
                + " Dias "+ fechaCursado + " Link " + linkAcceso + " Costo " + costo);
    }

    public static Cursos obtenerCursoMasVendido() {
        Cursos cursoMasVendido = null;
        int maxAlumnosInscritos = 0;

        for (Cursos curso : Cursos.getListaCursos()) {
            List<Alumno> alumnosInscritos = (List<Alumno>) curso.getAlumnosInscritos();
            if (alumnosInscritos != null) {
                int alumnosInscritosCount = alumnosInscritos.size();
                if (alumnosInscritosCount > maxAlumnosInscritos) {
                    maxAlumnosInscritos = alumnosInscritosCount;
                    cursoMasVendido = curso;
                }
            }
        }

        return cursoMasVendido;
    }

}


