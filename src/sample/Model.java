package sample;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static sample.Constans.*;

public class Model {
    Grid grid;
    Dijkstra dijkstra;
    List<Label> delta;
    

    public void runCalculations(){
        grid = new Grid();
        dijkstra = new Dijkstra(grid,SOURCE,DESTINATION);
        delta =new ArrayList();
        //MLCA CALCULATIONS
        MLCA();

    }
    public void printTable(String description, int[] table){
        System.out.print(description+ " ");
        for (int t : table){
            System.out.print(" "+t);
        }
        System.out.println();

    }
    public void MLCA(){
        //Step 0. Obtain the values of hi and  for i ∈ V and k = 1, 2, 3, using Dijkstra’s algorithm. (See the appendix for procedures.) Let Λ ← ∅.
        Vertice[] vertices = dijkstra.runDijkstraAlgorithm();
        printTable("Vertice ", new int[1]);
        printTable("H ", grid.getHTable());
        printTable("G0", grid.getGTable(0));
        printTable("G1", grid.getGTable(1));
        printTable("G2", grid.getGTable(2));


        //Step 1. Set d1(s, s) ← () and d1(s, i) ← (∞, ∞, ∞), i ∈ V{s}.
        // Insert the label of the path containing only the source vertex, L1(s) = { hs, π1(s, s)}, to Λ, i.e., let Λ ← {L1(s)}.
        Label firstLabel = initialize(vertices);
        delta.add(firstLabel);
        //Step 2. If Λ = ∅, terminate. Otherwise, select the label at the top of Λ, which has the minimum total label-value (and remove it from Λ).
        while (!delta.isEmpty()) {
            Label LepI = delta.get(0);
            delta.remove(0);


            //Step 3. Let Lξ(i) be the selected label in step 2. If there is no vertex directly accessible from vertex i, go to step 2.
            // Otherwise, i.e., if there are vertices directly accessible from vertex i, go to step 4.

            if (vertices[LepI.getI()].getPhi_a().size() == 0)
                continue;

            //Step 4. If each and every (partial) path extended from the current partial path associated with Lξ(i) satisfies
            // either (C1)  for all k = 1, 2, 3, and for some λ = 1, … , |P| or (C2) , , or , that is, if the extended (partial) path would result
            //in an infeasible solution, go to step 2; otherwise, go to step 5.

            for (int accessVertexI : vertices[LepI.getI()].getPhi_a()){
                if (delta.size()>0){
                    Stream<Label> stream = delta.stream();
                    Predicate<? super Label> pred = (label) -> label.getI() == accessVertexI;
                    List<Label> existnigLabelsToAccessVertexI = stream.filter(pred).collect(Collectors.toList());

                    int[] d = new int[3];
                    d[0] = LepI.getD(0) + vertices[accessVertexI].c(0);
                    d[1] = LepI.getD(1)  + vertices[accessVertexI].c(1);
                    d[2] = LepI.getD(2)  + vertices[accessVertexI].c(2);
                    Path path = LepI.getPath();
                    path.epsilon = existnigLabelsToAccessVertexI.size();
                    path.add(path.end);
                    path.end = accessVertexI;

                    Label labelAccesible = new Label(existnigLabelsToAccessVertexI.size(), accessVertexI, d[0], d[1], d[2], vertices[accessVertexI].getH(), path);

                }
            }


            //Step 5. For each non-dominated path (identified in step 4), do: if the path is a complete path,
            // insert the label of the path into P and remove labels of the paths in P that are dominated by that path from P;
            // otherwise, insert the label of the path into Λ according to the MTLV rule. Go to step 2.
        }
    }

    private Label initialize(Vertice[] vertices) {
        int[] d = new int[3];
        d[0] = vertices[0].c(0);
        d[1] = vertices[1].c(1);
        d[2] = vertices[2].c(2);
        int epsilon = SOURCE;
        Path path = new Path(epsilon, SOURCE, SOURCE);
        Label label = new Label(epsilon, SOURCE, d[0], d[1], d[2], vertices[SOURCE].getH(), path);
        return label;
    }
}
