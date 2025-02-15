import java.util.Comparator;
public class PesoAndIndex {
    public float peso;
    public int indexA;
    public int indexB;

    @Override
    public String toString() {
        return "Peso: "+ peso + "\nIndexA: " + indexA + "\nIndexB: " + indexB;
    }

    public static class ComparadorPorPeso implements Comparator<PesoAndIndex> {
        @Override
        public int compare(PesoAndIndex o1, PesoAndIndex o2) {
            // Compara os pesos, retornando a diferença entre eles
            if (o1.peso < o2.peso) {
                return -1;  // o1 tem peso menor que o2
            } else if (o1.peso > o2.peso) {
                return 1;   // o1 tem peso maior que o2
            } else {
                return 0;   // pesos iguais
            }
        }
    }

}
