import javax.swing.plaf.synth.SynthOptionPaneUI;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Grafo {
    public int numeroDeVertices;
    public ArrayList<ArrayList<Integer>> listaDeAdj;
    public ArrayList<ArrayList<Float>> valores;
    public Grafo(int numeroDeVertices){
        this.numeroDeVertices = numeroDeVertices;
        listaDeAdj = new ArrayList<>(numeroDeVertices);
        valores = new ArrayList<>(numeroDeVertices);

        for(int i=0;i<numeroDeVertices;i++){
            listaDeAdj.add(new ArrayList<>());
            valores.add(new ArrayList<>());
        }

    }
    public void adicionarArestas(int vertice1, int vertice2, float valor){
        listaDeAdj.get(vertice1).add(vertice2);
        listaDeAdj.get(vertice2).add(vertice1);
        valores.get(vertice1).add(valor);
        valores.get(vertice2).add(valor);
    }
    public void removerArestas(int vertice1, int vertice2){
        for(int i = 0; i < listaDeAdj.get(vertice1).size(); i++){
            if(listaDeAdj.get(vertice1).get(i) == vertice2)listaDeAdj.get(vertice1).remove(i);
        }

        for(int i = 0; i < listaDeAdj.get(vertice2).size(); i++){
            if(listaDeAdj.get(vertice2).get(i) == vertice1)listaDeAdj.get(vertice2).remove(i);
        }
    }
    public void adicionarNo(int nosAdicionados){
        for(int i = 0; i < nosAdicionados; i++) {
            listaDeAdj.add(new ArrayList<>());
            valores.add(new ArrayList<>());
        }
    }
    public void adicionarNo(){
        listaDeAdj.add(new ArrayList<>());
        valores.add(new ArrayList<>());
    }
    public void mostrarGrafo(){
        for(int i = 0; i < listaDeAdj.size(); i++){
            System.out.print(i+" ");

            for(int j = 0; j < listaDeAdj.get(i).size(); j++){
                System.out.print(
                    listaDeAdj.get(i).get(j) +
                    "(" + valores.get(i).get(j) + ")" );
                if(j < listaDeAdj.get(i).size()-1){
                    System.out.print(" -> ");
                }
            }
            System.out.println();
        }
    }
    public int retornaQuantidadeNos(){
        return listaDeAdj.size();
    }
    public int retornaQuantidadeArestas(){
        int arestas=0;

        for(int i = 0; i < listaDeAdj.size(); i++) {
            arestas += listaDeAdj.get(i).size();
        }

        return arestas/2;
    }

    //Todos os vértices de um grafo euleriano tem grau par
    public boolean verificaGrauPar(){
        for(int i=0; i< listaDeAdj.size(); i++){
            if(listaDeAdj.get(i).size() % 2 != 0){
                return false;
            }
        }
        return true;
    }
    //Para ser um grafo euleriano deve ser conexo
    public boolean verificaSeConexo(){
        for(int i=0; i< listaDeAdj.size(); i++){
            if(listaDeAdj.get(i).size() < 1){
                return false;
            }
        }
        return true;
    }
    public boolean verificaSeEuleriano(){
        return verificaGrauPar() && verificaSeConexo();
    }
    public boolean verificarAdjascencia(int vertice1, int vertice2){
        return listaDeAdj.get(vertice1).contains(vertice2);
    }
    public float retornaValorAresta(int verticeDesejado, int arestaDesejada){
        return valores.get(verticeDesejado).get(arestaDesejada);
    }
    private void mostraCaminhoDoisVertices(int start, int end, ArrayList<ArrayList<Integer>> listaDeAdj, ArrayList<Integer> jaPassei){

        //Adjascência trivial
        if(verificarAdjascencia(start, end)){
            System.out.println(start + " -> " + end);
            return;
        }jaPassei.add(start);

        //Caso não tenha adjascência imediata
        for(int conexoes: listaDeAdj.get(start)){

                if(jaPassei.contains(conexoes))continue;
                System.out.print(start);
                System.out.print(" -> ");
                mostraCaminhoDoisVertices(conexoes, end, listaDeAdj, jaPassei);
        }
    }

    //Essa função existe pois eu preciso parametrizar uma função recursiva
    //Ela basicamente é uma interface para a função homônima acima
    public void mostraCaminhoDoisVertices(int start, int end){
        ArrayList<Integer> jaPassei = new ArrayList<>();
        mostraCaminhoDoisVertices(start, end, listaDeAdj, jaPassei);
    }
    public Grafo prim(int inicial,boolean menorCusto){
        Grafo grafo = new Grafo(numeroDeVertices);
        ArrayList<Integer> jaNaArvore = new ArrayList<>();
        jaNaArvore.add(inicial);
        PesoAndIndex arestaIterar = new PesoAndIndex();

        while(jaNaArvore.size() < listaDeAdj.size()){
            arestaIterar = encontraArestaDeMenorPeso(jaNaArvore,menorCusto);

            //Valor válido
            if(arestaIterar.indexA >= 0){
                grafo.adicionarArestas(arestaIterar.indexA, arestaIterar.indexB, arestaIterar.peso);
                jaNaArvore.add(arestaIterar.indexB);continue;
            }

            break;
        }

        return grafo;
    }
    private PesoAndIndex encontraArestaDeMenorPeso(ArrayList<Integer> jaNaArvore, boolean menorCusto){

        PesoAndIndex menorPeso = new PesoAndIndex();
        PesoAndIndex pesoIterar = new PesoAndIndex();
        menorPeso.indexA = -1; menorPeso.indexB = -1; menorPeso.peso = -1;
        boolean primeiraVez = true;

        //Iterar pela lista de membros da arvore, procurando pesos das arestas com mebros de fora
        for (int i = 0; i < jaNaArvore.size(); i++) {
            pesoIterar = retornaArestaDeMenorPesoForaDaArvore(jaNaArvore.get(i), jaNaArvore, menorCusto);

            //Se for válido
            if(menorCusto) {
                if (pesoIterar.indexA >= 0) {
                    //Primeira Vez
                    if (menorPeso.indexA < 0) {
                        menorPeso.indexA = pesoIterar.indexA;
                        menorPeso.indexB = pesoIterar.indexB;
                        menorPeso.peso = pesoIterar.peso;
                        continue;
                    }
                    //Vezes seguintes
                    menorPeso.indexA = pesoIterar.indexA;
                    menorPeso.indexB = pesoIterar.indexB;
                    menorPeso.peso = pesoIterar.peso;
                }
            }

            //Maior Custo
            if (pesoIterar.indexA >= 0) {
                //Primeira Vez
                if (menorPeso.indexA < 0) {
                    menorPeso.indexA = pesoIterar.indexA;
                    menorPeso.indexB = pesoIterar.indexB;
                    menorPeso.peso = pesoIterar.peso;
                    continue;
                }
                //Vezes seguintes
                menorPeso.indexA = pesoIterar.indexA;
                menorPeso.indexB = pesoIterar.indexB;
                menorPeso.peso = pesoIterar.peso;
            }



        }

        return menorPeso;
    }
    private PesoAndIndex retornaArestaDeMenorPesoForaDaArvore(int inicial, ArrayList<Integer> jaNaArvore, boolean menorCusto){

        /*Essa função procura em um vértice a conexão de mais baixo custo que ele pode ter
        E então devolve um conjunto de valores com os vertices da conexão e o peso, Caso
        não tenha nenhuma adjascência com um vertice de fora da árvore, irá devolver
        um PesoAndIndex com o índices negativos*/

        //Arestas indices
        ArrayList<Integer> indices = this.listaDeAdj.get(inicial);
        //Arestas Valores
        ArrayList<Float> valores = this.valores.get(inicial);

        float peso=-1;
        int index = -1;

        //Iterar pelas arestas do índice até encontrar uma aresta que se conecte a um vertice de fora
        for(int i = 0; i < indices.size(); i++){

            //Caso o vértice encontrado na aresta já esteja na árvore, pule
            if(jaNaArvore.contains(indices.get(i)))continue;

            //Encontrou um vértice de fora

            //Caso o index ainda seja -1, isso significa que é a primeira vez que um vértice de fora é encontrado
            if(index == -1) {
                peso = valores.get(i);
                index = i;
                continue;
            }

            //Vezes exceto a primeira
            if(menorCusto) {
                if (peso > valores.get(i)) {
                    peso = valores.get(i);
                    index = i;
                }continue;
            }
            //Maior Custo
            if (peso < valores.get(i)) {
                peso = valores.get(i);
                index = i;
            }

        }

        PesoAndIndex conj = new PesoAndIndex();

        if(index < 0){
            conj.peso = -1;
            conj.indexA = -1;
            conj.indexB = -1;
            return conj;
        }
        conj.peso = peso;
        conj.indexA = inicial;
        conj.indexB = indices.get(index);
        return conj;

    }

    public Grafo kruskal(boolean menorCusto){
        Grafo grafo = new Grafo(numeroDeVertices);
        ArrayList<PesoAndIndex> aa = verticesEmOrdem();
        Collections.sort(aa, new PesoAndIndex.ComparadorPorPeso());
        if(!menorCusto){Collections.reverse(aa);}

        //Para cada aresta, verificar se pode inserir
        for(PesoAndIndex bb: aa){
            verificaSePodeColocarAresta(grafo, bb);
        }

        return grafo;
    }

    private void verificaSePodeColocarAresta(Grafo grafo, PesoAndIndex pai){
        //Se estiver em loop, não pode ser inserida

        //Verificação do loop: verificar se algum tem os dois vertices da nova aresta em comum

            if(verificaSeLoopa(pai.indexA, pai.indexB, new ArrayList<>(), grafo)){
                return;
            }

        grafo.adicionarArestas(pai.indexA, pai.indexB, pai.peso);
    }

    //Verifica se existe um caminho
    private boolean verificaSeLoopa(int start, int end, ArrayList<Integer>verticesVisitados, Grafo grafo){

        verticesVisitados.add(start);
       ArrayList<Integer> vericeAtualLista = grafo.listaDeAdj.get(start);


        //Verifica se existe conexão direta
        if(vericeAtualLista.contains(end)){
            return true;
        }

        //Se não existe conexão direta navegue até achar
        for(int i = 0; i < vericeAtualLista.size(); i++){

            if(verticesVisitados.contains(vericeAtualLista.get(i))) continue;
            boolean resultado =  verificaSeLoopa(vericeAtualLista.get(i), end, verticesVisitados, grafo);
            if(resultado)return true;

        }

        return false;

    }
    private ArrayList<PesoAndIndex> verticesEmOrdem(){

        ArrayList<PesoAndIndex> arestas = new ArrayList<>();

        for(int i = 0; i < listaDeAdj.size(); i++){
            for(int j = 0; j < listaDeAdj.get(i).size(); j++){

                PesoAndIndex aresta = new PesoAndIndex();
                aresta.indexA = i;
                aresta.indexB = listaDeAdj.get(i).get(j);
                aresta.peso = valores.get(i).get(j);

                boolean contem = false;
                //Verifica se já foi incluída
                for(PesoAndIndex a: arestas){
                    if(a.peso == aresta.peso && a.indexA == aresta.indexA && a.indexB == aresta.indexB){contem = true; break;}
                    if(a.peso == aresta.peso && a.indexA == aresta.indexB && a.indexB == aresta.indexA){contem = true; break;}
                }
                if(!contem){arestas.add(aresta);}

            }
        }
        return arestas;
    }

}



