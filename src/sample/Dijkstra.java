package sample;

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
        this.vertices = grid.getVerticesTable();
        this.S = new HashSet<Vertice>();
        this.Sgw = new HashSet<Vertice>();
        this.e = new int[Constans.VERTICES_NUM];
        this.s = s;
        this.t = t;
    }
    public void runDijkstraAlgorithm() {
        computeH();
        computeG();
        grid.setVerticesTable(vertices);
    }
    public void computeG(){
        //Step 0. S ← V, S∗ ← ∅,  ← 0, and  ← ∞ for i ∈ V⧹{t}.
        initializeG();

        //Step 1. Select a vertex with the minimum value of  among i ∈ S and let the selected vertex be vertex i∗, i.e., i∗ ← argmini∈S{}. Let S ← S − {i∗} and S∗ ← S∗ ∪ {i∗}.

       // Step 2. For each j ∈ Φa(i), do: if  + ci, let .

       //Step 3. If |S∗| < |V|, go to step 1; otherwise, stop.

    }

    public void computeH(){
        //Step 0. Set S ← V, S∗ ← ∅, ht ← 0, and hi ← ∞ for i ∈ V⧹{t}.
        initializeH();
        //Step 1. Compute the combined cost for all vertices, i.e., for ∀i ∈ V.
        computeCombinedCostOfAllVertices();

        //Step 4. If |S∗| < |V|, go to step 2; otherwise, stop.
        while (Sgw.size()<vertices.length) {
            //Step 2. Select a vertex with the minimum value of hi among i ∈ S and let the selected vertex be vertex i∗, i.e., i∗ ← argmini∈S{hi}. Let S ← S − {i∗} and S∗ ← S∗ ∪ {i∗}.
            int i = step2();
            //Step 3. For each j ∈ Φa(i), do: if hj > hi + ei, let hj ← hi + ei.
            step3(i);
        }
    }


    private void step3(int i) {
        for (int j : vertices[i].getPhi_a()) {
            if (vertices[j].getH()>vertices[i].getH()+e[i]){
                vertices[j].setH(vertices[i].getH()+e[i]);
            }
        }
    }

    private int step2() {
        Stream<Vertice> stream = S.stream();

        Comparator<? super Vertice> compMinH = (ver1, ver2) -> (ver1.h<ver2.h) ? 0 : 1;
        final Optional<Vertice> minVertice = stream.min(compMinH);

        Sgw.add(minVertice.get());
        S.remove(minVertice.get());

        return minVertice.get().getId();
        // displaying elements of Stream using lambda expression
        //stream.forEach(elem->System.out.print(elem+" "));
    }

    private void computeCombinedCostOfAllVertices() {
        for (int i = 0; i< e.length; i++){
            e[i] = vertices[i].c(TT) + vertices[i].c(RL) + vertices[i].c(JL);
        }
    }

    private void initializeH() {
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
    private void initializeG() {
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
