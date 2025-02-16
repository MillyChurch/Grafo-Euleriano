import java.util.ArrayList;
import java.util.Collections;

public class Main {
    public static void main(String[] args) {

        int numeroDeVertices = 5;
        Grafo grafo = new Grafo(numeroDeVertices);

        grafo.adicionarArestas(0,1,1);
        grafo.adicionarArestas(1,2,2);
        grafo.adicionarArestas(2,3,3);
        grafo.adicionarArestas(3,4,4);
        grafo.adicionarArestas(4,0,5);
        grafo.adicionarArestas(2,4,6);

        System.out.println("GRAFO");
        grafo.mostrarGrafo();

        System.out.println("\nARVORE GERADORA CUSTO MÍNIMO (PRIM) ");
        grafo.prim(0,true).mostrarGrafo();

        System.out.println("\nARVORE GERADORA CUSTO MÁXIMO (PRIM) ");
        grafo.prim(0, false).mostrarGrafo();

        System.out.println("\n:ARVORE GERADORA CUSTO MÍNIMO (Kruskal)");
        grafo.kruskal(true).mostrarGrafo();
        System.out.println("\n:ARVORE GERADORA CUSTO MÁXIMO (Kruskal)");
        grafo.kruskal(false).mostrarGrafo();




    }

}