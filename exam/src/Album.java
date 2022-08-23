import java.util.ArrayList;
import java.util.HashMap;

public class Album<T extends Colecionavel> {
    int tamanhoDoAlbum;
    int quantidadeItemPorPacotinho;

    HashMap<Integer, Colecionavel> figurinhasRepetidas;
    HashMap<Integer, Colecionavel> figurinhasNaoRepetidas;
    ArrayList<Colecionavel> quantidadeMaximaFigurinhas;

    public Album(int tamanhoDoAlbum, int quantidadeItemPorPacotinho) {
        figurinhasNaoRepetidas = new HashMap<>();
        figurinhasRepetidas = new HashMap<>();
        quantidadeMaximaFigurinhas = new ArrayList();

        this.quantidadeItemPorPacotinho = quantidadeItemPorPacotinho;
        this.tamanhoDoAlbum = tamanhoDoAlbum;
    }

    public void receberNovoPacotinho(Pacotinho<T> pacotinho) throws PacotinhoInvalidoException {
        if (quantidadeItemPorPacotinho > pacotinho.size())  throw new PacotinhoInvalidoException();
        if (pacotinho.size() != this.quantidadeItemPorPacotinho)  throw new PacotinhoInvalidoException();


        for(int i = 0; i < pacotinho.size(); i++)
            if (pacotinho.get(i).getPosicao() > tamanhoDoAlbum)  throw new PacotinhoInvalidoException();

        for (T figurinha : pacotinho) {
            quantidadeMaximaFigurinhas.add(figurinha);

            if (figurinhasNaoRepetidas.containsKey(figurinha.getPosicao()))  figurinhasRepetidas.put(figurinha.getPosicao(), figurinha);
            figurinhasNaoRepetidas.put(figurinha.getPosicao(), figurinha);
        }
    }

    public int getTamanho() { return this.tamanhoDoAlbum; }

    public int getQuantItensColados() { return figurinhasNaoRepetidas.size(); }

    public int getQuantItensRepetidos() { return quantidadeMaximaFigurinhas.size() - figurinhasNaoRepetidas.size(); }

    public boolean possuiItemColado(int posicao) { return figurinhasNaoRepetidas.containsKey(posicao); }

    public boolean possuiItemRepetido(int posicao) { return figurinhasRepetidas.containsKey(posicao); }

    public int getQuantItensFaltantes() { return tamanhoDoAlbum - figurinhasNaoRepetidas.size(); }

    public T getItemColado(int posicao) {
        if (figurinhasNaoRepetidas.containsKey(posicao))  return (T) figurinhasNaoRepetidas.get(posicao);

        return null;
    }
}