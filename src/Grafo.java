import java.util.ArrayList;

public class Grafo {
    public int numeroDeVertices;
    public ArrayList<ArrayList<Integer>> listaDeAdj;

    public Grafo(int numeroDeVertices){
        this.numeroDeVertices = numeroDeVertices;
        listaDeAdj = new ArrayList<>(numeroDeVertices);

        for(int i=0;i<numeroDeVertices;i++){
            listaDeAdj.add(new ArrayList<>());
        }

    }

    public void mostrarGrafo(){
        for(int i = 0; i < listaDeAdj.size(); i++){
            System.out.println(i+" "+listaDeAdj.get(i));
        }
    }

    public void adicionarArestas(int vertice1, int vertice2){
        listaDeAdj.get(vertice1).add(vertice2);
        listaDeAdj.get(vertice2).add(vertice1);
    }

    public void adicionarNo(int nosAdicionados){
        for(int i = 0; i < nosAdicionados; i++) {
            listaDeAdj.add(new ArrayList<>());
        }
    }

    public void adicionarNo(){
        listaDeAdj.add(new ArrayList<>());
    }

    public void removerNoPorId(int id){
        listaDeAdj.remove(id);

        for(int i=0; i < listaDeAdj.size(); i++){
            for(int j=0; j < listaDeAdj.get(i).size(); j++){

                int atualIndex = listaDeAdj.get(i).get(j);

                if(atualIndex == id){
                    listaDeAdj.get(i).remove(j); continue;
                }
                if(atualIndex > id){
                    listaDeAdj.get(i).set(j, atualIndex-1);
                }
            }
        }

    }

    public boolean verificarAdjascencia(int vertice1, int vertice2){
        return listaDeAdj.get(vertice1).contains(vertice2);
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

    public void mostraPasseios(){
        for(int i = 0; i < listaDeAdj.size(); i++){
            for(int j = 0; j < listaDeAdj.get(i).size();j ++){
                System.out.println(  i +  "->"  + listaDeAdj.get(i).get(j)   );
            }
        }
    }

    public void mostraTrilhas(){
        for(int i = 0; i < listaDeAdj.size(); i++){
            for(int j = 0; j < listaDeAdj.get(i).size();j ++){
                System.out.println(  i +  "->"  + listaDeAdj.get(i).get(j)   );
            }
        }
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
}

