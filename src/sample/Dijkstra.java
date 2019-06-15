package sample;

import java.lang.reflect.Array;
import java.util.*;
import java.util.stream.Stream;

import static sample.Constans.*;

public class Dijkstra {
    private  Set S;
    private  Set Sgw;
    private Grid grid;
    private Vertice[] vertices;
    private int[] e;
    //source
    private int s;
    //destination
    private int t;

    public Dijkstra(Grid grid, int s, int t) {
        this.grid = grid;

        this.vertices = grid.getVertices();
        this.S = new HashSet<Vertice>();
        this.Sgw = new HashSet<Vertice>();
        this.s = s;
        this.t = t;
    }
    public void runDijkstraAlgorithm(){
        //Step 0. Set S ← V, S∗ ← ∅, ht ← 0, and hi ← ∞ for i ∈ V⧹{t}.
        initialize();
        //Step 1. Compute the combined cost for all vertices, i.e., for ∀i ∈ V.
        computeCombinedCostOfAllVertices();
        //Step 2. Select a vertex with the minimum value of hi among i ∈ S and let the selected vertex be vertex i∗, i.e., i∗ ← argmini∈S{hi}. Let S ← S − {i∗} and S∗ ← S∗ ∪ {i∗}.
        step2();
        //Step 3. For each j ∈ Φa(i), do: if hj > hi + ei, let hj ← hi + ei.
        step3();
        //Step 4. If |S∗| < |V|, go to step 2; otherwise, stop.
    }

    private void step3() {
    }

    private void step2() {
        Stream<Vertice> stream = S.stream();

        Comparator<? super Vertice> compMinH = (ver1, ver2) -> (ver1.h<ver2.h) ? 0 : 1;
        final Optional<Vertice> minVertice = stream.min(compMinH);

        Sgw.add(minVertice.get());
        S.remove(minVertice.get());
        // displaying elements of Stream using lambda expression
        //stream.forEach(elem->System.out.print(elem+" "));
    }

    private void computeCombinedCostOfAllVertices() {
        for (int i = 0; i< e.length; i++){
            e[i] = vertices[i].c(TT) + vertices[i].c(RL) + vertices[i].c(JL);
        }
    }

    private void initialize() {
        S.addAll(Arrays.asList(vertices));
        Sgw.clear();
        for (int i = 0; i< vertices.length; i++){
            if (i == t) {
                vertices[i].setH(0);
            }
            else{
                vertices[i].setH(INFINITY);
            }
        }
    }
}
