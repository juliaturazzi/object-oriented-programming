public abstract class Impressora {

    private int pages;
    private int printedDocs;

    public Impressora(){
        this.pages = 0;
        this.printedDocs = 0;
    }

    public boolean imprimirDocumento(Documento documento){
        if (documento.getQuantPaginas() > this.pages){
            return false;
        }

        for (int i = 0; i < documento.getQuantPaginas(); i++) {
            executarImpressaoPagina(documento.getPagina(i));
            this.pages--;
        }
        this.printedDocs +=1;
        return true;
    }

    public void carregarPapel(int numeroDeFolhas) {
        this.pages += numeroDeFolhas;
    }

    public int getQuantidadeFolhasRestantes() {
        return this.pages;
    }

    public int getQuantidadeDocumentosImpressos() {
        return this.printedDocs;
    }

    public abstract void executarRotinaLimpeza();

    public abstract void executarImpressaoPagina(String pagina);
}
