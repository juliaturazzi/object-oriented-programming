import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class jujuba {

    static Random meuGerador = new Random();

    public static int obterTamanhoIntersecao(ArrayList<Integer> list1, ArrayList<Integer> list2) {

        list1.retainAll(list2);

        ArrayList<Integer> pureList = new ArrayList<Integer>();

        for (Integer element : list1) {
            if (!pureList.contains(element)) {
                pureList.add(element);
            }
        }

        int size = pureList.size();

        if (size <= 0) {
            System.out.println("Nao existem numeros em comum entre as listas");
        } else {
            System.out.println(pureList);
        }
        int size2 = pureList.size();

        return size2;
    }

    public static int sortearInt(int menor, int maior) {
        return meuGerador.nextInt(maior - menor + 1) + menor;
    }

    public static void main(String[] args) {

        Scanner Scanner = new Scanner(System.in);

        System.out.printf("Insira o tamanho das listas: ");
        int listSize = Scanner.nextInt();

        ArrayList<Integer> list1 = new ArrayList<Integer>(listSize);
        ArrayList<Integer> list2 = new ArrayList<Integer>(listSize);

        for (int i = 0; i < listSize; i++) {
            list1.add(sortearInt(1, 10*listSize));
        }

        for (int i = 0; i < listSize; i++) {
            list2.add(sortearInt(1, 10*listSize));
        }

        System.out.println("Lista 1: " + list1);
        System.out.println("Lista 2: " + list2);

        System.out.printf("Intersecao das listas: ");
        System.out.printf("Tamanho da intersecao das listas: " + obterTamanhoIntersecao(list1, list2));

    }
}
