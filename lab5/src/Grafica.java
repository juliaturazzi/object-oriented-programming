import java.util.ArrayList;

public class Grafica {
    private float colorfulInkPrice;
    private float blackInkPrice;
    private float budget;

    public ArrayList<Impressora> printerList  = new ArrayList<>();
    int counter = 0;

    public void imprimirDocumento(Documento documento) {
        printerList.get(counter).imprimirDocumento(documento);
        if (counter < printerList.size() - 1 || counter > printerList.size() - 1){
            counter++;
            return;
        }
        counter = 0;
    }

    public float orcarImpressao(Documento documento) {
        if (!documento.isEmCores()) {
            budget = blackInkPrice * (documento.getQuantPaginas());
            return budget;
        }
        budget = colorfulInkPrice * (documento.getQuantPaginas());
        return budget;
    }

    public void adicionarImpressora(Impressora impressora) {
        printerList.add(impressora);
    }

    public void setPrecoPorPagina(float precoPorPagina, boolean emCores) {
        if (!emCores) {
            this.blackInkPrice = precoPorPagina;
            return;
        }
        colorfulInkPrice = precoPorPagina;
    }
}

