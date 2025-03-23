import java.util.ArrayList;
import java.util.Collections;

public class Main {
    public static void main(String[] args) {

        int numeroDeVertices = 7;
        Grafo grafo = new Grafo(numeroDeVertices);

        grafo.adicionarArestas(0,1,1);
        grafo.adicionarArestas(2,0,1);
        grafo.adicionarArestas(2,1,2);
        grafo.adicionarArestas(2,3,3);
        grafo.adicionarArestas(2,4,4);
        grafo.adicionarArestas(2,5,5);
        grafo.adicionarArestas(2,6,6);
        grafo.adicionarArestas(3,4,1);

        System.out.println("Busca em profundidade");
        boolean buscado = grafo.buscaEmProfundidade(0,5);
        System.out.println(buscado);

        System.out.println("\n\n");

        System.out.println("Busca em largura");
        boolean buscadoL = grafo.buscaEmLargura(0,5);
        System.out.println(buscadoL);

        System.out.println("\n\n");
        System.out.println("Grafo é conexo?: " + grafo.verificaSeConexo());

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

        grafo.mostraConjuntosIndependentes();
        grafo.mostraCliques();






    }

}