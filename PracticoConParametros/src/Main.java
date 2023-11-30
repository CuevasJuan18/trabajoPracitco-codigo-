import java.time.LocalDate;
import java.time.ZoneId;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        System.out.println("Hello world!");

        //////////////////////////Alumnos//////////////////////////
        //ArrayAlumnos
        ArrayList<Alumno> listaAlumnos = new ArrayList<>();
        //Crear Alumnos
        //datos personales como, nombre, apellido, dni y fecha de nacimiento, edad
        Date fechaLucas = crearFecha(1980, Calendar.NOVEMBER, 19);
        //PatronBuilder
        Alumno Lucas = new Alumno.Builder("Lucas", "Krapp", "41425897")
                .fechaNacimiento(fechaLucas)
                .edad(20)
                .create();
        Alumno Lucia = new Alumno("Lucia", "López", "12345678",
                crearFecha(1995, Calendar.NOVEMBER, 29), 27);
        Alumno Tomas = new Alumno("Tomas", "Gómez", "98765432",
                crearFecha(1992, Calendar.AUGUST, 20), 30);
        Alumno Sofia = new Alumno("Sofia", "Pérez", "87654321",
                crearFecha(1996, Calendar.DECEMBER, 12), 24);
        Alumno Pablo = new Alumno("Pablo", "Martínez", "56789012",
                crearFecha(1985, Calendar.FEBRUARY, 14), 32);
        Alumno Juan = new Alumno("Juan", "cuevas", "44237587",
                crearFecha(2003, Calendar.FEBRUARY, 14), 20);
        Alumno Enzo = new Alumno("Enzo", "Toro", "39228987",
                crearFecha(1996, Calendar.DECEMBER, 18), 26);
        //llenarArray con patron factory
        listaAlumnos.add(Alumno.AlumnoFactory.createAlumno("Lucas", "Krapp", "41425897", 1980, Calendar.NOVEMBER, 19, 20));
        listaAlumnos.add(Alumno.AlumnoFactory.createAlumno("Lucia", "López", "12345678", 1995, Calendar.JANUARY, 5, 27));
        listaAlumnos.add(Alumno.AlumnoFactory.createAlumno("Tomas", "Gómez", "98765432", 1992, Calendar.AUGUST, 20, 30));
        listaAlumnos.add(Alumno.AlumnoFactory.createAlumno("Sofia", "Pérez", "87654321", 1996, Calendar.DECEMBER, 12, 24));
        listaAlumnos.add(Alumno.AlumnoFactory.createAlumno("Pablo", "Martínez", "56789012", 1985, Calendar.FEBRUARY, 14, 32));
        listaAlumnos.add(Alumno.AlumnoFactory.createAlumno("Juan", "cuevas", "44237587", 2003, Calendar.FEBRUARY, 14, 20));
        //CrearLegajo
        Lucas.generarLegajo(Lucas.getDni());
        Lucia.generarLegajo(Lucia.getDni());
        Tomas.generarLegajo(Tomas.getDni());
        Sofia.generarLegajo(Sofia.getDni());
        Pablo.generarLegajo(Pablo.getDni());
        Juan.generarLegajo(Juan.getDni());
        //MostrarDatos
        System.out.println(Lucas.toString());
        //////////////////////////Profesores//////////////////////////
        //ArrayProfesores
        ArrayList<Profesor> listaProfesores = new ArrayList<>();
        //CrearProfesores
        //nombre, apellido, dni, fecha de nacimiento, edad y currículo vitae.
        Profesor Julio = new Profesor("Julio", "Pérez", "22675894",
                crearFecha(1980, Calendar.NOVEMBER, 30), 42, "Licenciado en Informática");
        Profesor Maria = new Profesor("Maria", "Gómez", "987654321",
                crearFecha(1975, Calendar.AUGUST, 22), 47, "Doctora en Matemáticas");
        Profesor Carlos = new Profesor("Carlos", "López", "567890123",
                crearFecha(1985, Calendar.FEBRUARY, 10), 37, "Ingeniero en Electricidad");
        Profesor Ana = new Profesor("Ana", "Martínez", "321098765",
                crearFecha(11980, Calendar.MAY, 18), 43, "Licenciada en Biología");
        Profesor Pedro = new Profesor("Pedro", "Rodríguez", "876543210",
                crearFecha(1982, Calendar.JULY, 5), 39, "Economista");
        //llenarArray
        listaProfesores.add(Julio);
        listaProfesores.add(Maria);
        listaProfesores.add(Carlos);
        listaProfesores.add(Ana);
        listaProfesores.add(Pedro);
        //MostrarDatos
        System.out.println(" ");
        System.out.println(Pedro.toString());

        //////////////////////////Personas//////////////////////////
        //Crear una Lista de Personas
        List<Persona> personas = new ArrayList<>();
        //alumnos
        personas.add(Lucas);
        personas.add(Lucia);
        personas.add(Tomas);
        personas.add(Sofia);
        personas.add(Pablo);
        personas.add(Juan);
        //profesores
        personas.add(Julio);
        personas.add(Maria);
        personas.add(Carlos);
        personas.add(Ana);
        personas.add(Pedro);

        //////////////////////////Cursos//////////////////////////
        // Crea una lista de cursos
        List<Cursos> listaCursos = new ArrayList<>();
        //CrearCursoPresencial
        CursoPresencial desarrolloWeb = new CursoPresencial("desarrolloWeb", 12, "Aprende programación web",
                "Crea tu página web", "Programadores", 45000, "Lunes y Miércoles", "18:30");
        //CrearCursoPresencial
        CursoPresencial desarrolloWebCopia = new CursoPresencial("desarrolloWebCopia", 12, "Aprende programación web",
                "Crea tu página web", "Programadores", 45000, "Lunes y Miércoles", "18:30");
        CursoPresencial masterEnJava = new CursoPresencial("Master En Java", 13, "Aprende Java",
                "Desarrollo avanzado en Java", "Programadores", 50000, "Viernes y Sábados", "18:30");
        CursoPresencial masterEnPHP = new CursoPresencial("masterEnPHP", 14, "Aprende PHP",
                "Desarrollo avanzado en PHP", "Programadores", 50000, "Viernes y Sábados", "09:00");
        //CrearCursoOnline
        CursoOnline cursoExcel = new CursoOnline("Curso Excel", 789, "Descripción completa",
                "Aprende excel", "Variado", 1000, "www.link.com.ar", "Lunes y Miercoles");
        CursoOnline cursoVscode = new CursoOnline("Curso Vscode", 12, "Descripción completa",
                "Aprende a usar el IDE ", "Programadores", 2500, "www.link.com.ar", "Martes y viernes");
        //Llenar lista
        listaCursos.add(desarrolloWeb);
        listaCursos.add(desarrolloWebCopia);
        listaCursos.add(masterEnJava);
        listaCursos.add(masterEnPHP);
        listaCursos.add(cursoExcel);
        listaCursos.add(cursoVscode);

        //////////////////////////Actividades//////////////////////////
        //InscribirAlumnos
        System.out.println(" ");
        desarrolloWeb.inscribirAlumno(Juan);
        System.out.println(" ");
        desarrolloWeb.inscribirAlumno(Lucas);
        System.out.println(" ");
        desarrolloWeb.inscribirAlumno(Lucia);
        System.out.println(" ");
        desarrolloWeb.inscribirAlumno(Sofia);
        System.out.println(" ");
        desarrolloWeb.inscribirAlumno(Pablo);
        System.out.println(" ");
        //no podrá contener más de 15 "5" alumnos
        //En caso de superar la inscripción de 15 alumnos en un determinado curso
        // se deberá crear un nuevo curso con un código de catedra distinta.
        desarrolloWeb.inscribirAlumno(Tomas);
        System.out.println(" ");

        //Un alumno que está registrado en más de un curso se le deberá hacer un
        // descuento del 20% del costo por cada curso registrado.
        masterEnPHP.inscribirAlumno(Juan);
        System.out.println(" ");
        masterEnPHP.inscribirAlumno(Lucas);
        System.out.println(" ");

        //Inscribir Docente
        desarrolloWeb.asignarDocente(Julio);
        System.out.println(" ");
        //El profesor no puede dictar más de un curso al mismo tiempo, deben diferir
        // en el horario o en el día. Y alumno
        desarrolloWebCopia.asignarDocente(Julio);
        System.out.println(" ");
        desarrolloWebCopia.inscribirAlumno(Juan);
        System.out.println(" ");

        //////////////////////////Consultas//////////////////////////
        //Listar Curso más vendido
        //Encuentra el curso más vendido
        Cursos cursoMasVendido = Cursos.encontrarCursoMasVendido(listaCursos);
        if (cursoMasVendido != null) {
            System.out.println("Curso más vendido: " + cursoMasVendido.getNombre());
        } else {
            System.out.println("No hay cursos disponibles.");
        }
        System.out.println(" ");

        // Imprimir cantidad de alumnos con descuento
        int cantidadAlumnosConDescuento = Alumno.cantidadAlumnosConDescuento(listaAlumnos);
        System.out.println("Cantidad de alumnos con descuento: " + cantidadAlumnosConDescuento);
        System.out.println(" ");

        // Llamar al método para listar cumpleaños cercanos
        // julio fecha 1980, (20 de noviembre) Lucas (19 de noviembre)
        Date fechaActual = new Date();
        Persona.listarCumpleanosCercanos(personas, fechaActual);
    }
    private static Date crearFecha(int year, int month, int day) {
        Calendar calendar = Calendar.getInstance();
        calendar.set(year, month, day);
        return calendar.getTime();
    }


}