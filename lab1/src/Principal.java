import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class Principal {

    static Random meuGerador = new Random();

    public static int obterTamanhoIntersecao(ArrayList<Integer> lista1, ArrayList<Integer> lista2, int tamanho) {
        ArrayList<Integer> intersecao = new ArrayList<>();
        for (int i = 0; i < tamanho; i++){
            for (int j = 0; j < tamanho; j++){
                if (lista1.get(i) == lista2.get(j)){
                    intersecao.add(lista1.get(i));
                }
            }
        }
        int size = intersecao.size();
        System.out.println(intersecao);
        System.out.println("A quantidade de elemento(s): " + size);

        return 0;
    }

    public static int sortearInt(int menor, int maior) {
        return meuGerador.nextInt(maior - menor + 1) + menor;
    }

    public static void main(String[] args) {

        Scanner Scanner = new Scanner(System.in);

        System.out.printf("\nPor favor, insira o tamanho que deseja das listas: ");
        int tamanhoLista = Scanner.nextInt();

        ArrayList<Integer> lista1 = new ArrayList<Integer>(tamanhoLista);
        ArrayList<Integer> lista2 = new ArrayList<Integer>(tamanhoLista);

        for (int i = 0; i < tamanhoLista; i++) {
            lista1.add(sortearInt(1, 10*tamanhoLista));
        }

        for (int i = 0; i < tamanhoLista; i++) {
            lista2.add(sortearInt(1, 10* tamanhoLista));
        }

        System.out.println("\nLista 1: " + lista1);
        System.out.println("Lista 2: " + lista2);

        System.out.print("\nQuantidade de intersecao das listas: ");
        obterTamanhoIntersecao(lista1,lista2,tamanhoLista);

    }
}