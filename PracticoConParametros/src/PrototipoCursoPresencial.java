public class PrototipoCursoPresencial implements Prototipo {
    private CursoPresencial prototipoCursoPresencial;

    public PrototipoCursoPresencial(CursoPresencial prototipoCursoPresencial) {
        this.prototipoCursoPresencial = prototipoCursoPresencial;
    }

    @Override
    public Prototipo clonar() {
        // Utilizar la clonación para crear una nueva instancia
        return prototipoCursoPresencial.clonar();
    }
}