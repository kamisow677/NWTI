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
    public Vertice[] runDijkstraAlgorithm() {
        computeH();
        computeG();
        grid.setVerticesTable(vertices);
        return vertices;
    }
    public void computeG(){
        int k  = 0;
        for (k = 0 ; k<3 ; k++) {
            //Step 0. S ← V, S∗ ← ∅,  ← 0, and  ← ∞ for i ∈ V⧹{t}.
            initializeG(k);

            //Step 3. If |S∗| < |V|, go to step 1; otherwise, stop.
            while (Sgw.size() < vertices.length) {
                //Step 1. Select a vertex with the minimum value of  among i ∈ S and let the selected vertex be vertex i∗, i.e., i∗ ← argmini∈S{}. Let S ← S − {i∗} and S∗ ← S∗ ∪ {i∗}.
                final int i = minGvertex(k);
                // Step 2. For each j ∈ Φa(i), do: if  + ci, let .
                forEachJinPHI_G(i, k);
            }
        }


    }

    public void computeH(){
        //Step 0. Set S ← V, S∗ ← ∅, ht ← 0, and hi ← ∞ for i ∈ V⧹{t}.
        initializeH();
        //Step 1. Compute the combined cost for all vertices, i.e., for ∀i ∈ V.
        computeCombinedCostOfAllVertices();

        //Step 4. If |S∗| < |V|, go to step 2; otherwise, stop.
        while (Sgw.size()<vertices.length) {
            //Step 2. Select a vertex with the minimum value of hi among i ∈ S and let the selected vertex be vertex i∗, i.e., i∗ ← argmini∈S{hi}. Let S ← S − {i∗} and S∗ ← S∗ ∪ {i∗}.
            int i = minHvertex();
            //Step 3. For each j ∈ Φa(i), do: if hj > hi + ei, let hj ← hi + ei.
            forEachJinPHI_H(i);
        }
    }


    private void forEachJinPHI_H(int i) {
        for (int j : vertices[i].getPhi_a()) {
            if (vertices[j].getH()>vertices[i].getH()+e[i]){
                vertices[j].setH(vertices[i].getH()+e[i]);
            }
        }
    }
    private void forEachJinPHI_G(int i , int k) {
        for (int j : vertices[i].getPhi_a()) {
            if (vertices[j].getG(k)>vertices[i].getG(k)+vertices[i].c(k)){
                vertices[j].setG(k ,vertices[i].getG(k)+vertices[i].c(k));
            }
        }
    }



    private int minHvertex() {
        Stream<Vertice> stream = S.stream();

        Comparator<? super Vertice> compMinH = (ver1, ver2) -> (ver1.getH()<ver2.getH()) ? 0 : 1;
        final Optional<Vertice> minVertice = stream.min(compMinH);

        Sgw.add(minVertice.get());
        S.remove(minVertice.get());

        return minVertice.get().getId();
        // displaying elements of Stream using lambda expression
        //stream.forEach(elem->System.out.print(elem+" "));
    }

    private int minGvertex(int k) {
        Stream<Vertice> stream = S.stream();

        Comparator<? super Vertice> compMinG = (ver1, ver2) -> (ver1.getG(k)<ver2.getG(k)) ? 0 : 1;
        final Optional<Vertice> minVertice = stream.min(compMinG);

        Sgw.add(minVertice.get());
        S.remove(minVertice.get());

        return minVertice.get().getId();
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
    private void initializeG(int k) {
        S.addAll(Arrays.asList(vertices));
        Sgw.clear();
        for (int i = 0; i< vertices.length; i++){
            if (i == t) {
                vertices[i].setG(k,0);
            }
            else{
                vertices[i].setG(k, INFINITY);
            }
        }
    }
}
