import java.util.ArrayList;

public class Documento {

    private boolean colors;
    public ArrayList<String> pagesNumber;

    public Documento(ArrayList<String> paginas, boolean emCores) {
        this.colors = emCores;
        pagesNumber = paginas;
    }

    public boolean isEmCores() {
        return this.colors;
    }

    public int getQuantPaginas() {
        return pagesNumber.size();
    }

    public String getPagina(int numeroDaPagina) {
        return pagesNumber.get(numeroDaPagina);
    }
}
