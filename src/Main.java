import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {


        int numeroDeVertices = 5;
        Grafo grafo = new Grafo(numeroDeVertices);

        grafo.adicionarArestas(0,1);
        grafo.adicionarArestas(0,4);
        grafo.adicionarArestas(1,2);
        grafo.adicionarArestas(2,3);
        grafo.adicionarArestas(3,4);

        grafo.mostrarGrafo();
        System.out.println("Número de vértices: " + numeroDeVertices);
        System.out.println("Grau de todos os vértices são par: " + grafo.verificaGrauPar());
        System.out.println("O grafo é conexo: " + grafo.verificaSeConexo());
        System.out.println("O grafo é euleriano: " + grafo.verificaSeEuleriano());
        mostraCaminhos(grafo,0);


    }


    public static void mostraCaminhos(Grafo grafo, int verticeInicial){
        if(verticeInicial >= grafo.listaDeAdj.size())return;

        //Printar o vertice atual
        System.out.print(verticeInicial + "->");
        grafo.removerNoPorId(verticeInicial);
        int id = grafo.listaDeAdj.get(verticeInicial).get(0);
        mostraCaminhos(grafo, id);

    }

    public static ArrayList<Integer> escolheVerticePeloId(ArrayList<ArrayList<Integer>> listaAdj, int id){
            return listaAdj.get(id);
    }

}