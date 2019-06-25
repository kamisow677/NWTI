package sample;

import java.util.*;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import static sample.Constans.*;

public class Model {
    Grid grid;
    Dijkstra dijkstra;
    ArrayList<Label> delta;
    List<Label> P;
    int[] U;
    

    public void runCalculations(){
        grid = new Grid();
        dijkstra = new Dijkstra(grid,SOURCE,DESTINATION);
        delta =new ArrayList();
        P =new ArrayList();
        calculateU();
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
        calculateU();
        printTable("Vertice ", new int[1]);
        printTable("H ", grid.getHTable());
        printTable("G0", grid.getGTable(TT));
        printTable("G1", grid.getGTable(RL));
        printTable("G2", grid.getGTable(JL));


        boolean allDominated = true;
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
            ArrayList<Label> labelsNotDominated = new ArrayList<>();
            for (int accessVertexI : vertices[LepI.getI()].getPhi_a()){
           //     if (delta.size()>0){
                    Stream<Label> stream = delta.stream();
                    Predicate<? super Label> pred = (label) -> label.getI() == accessVertexI;
                    List<Label> existnigLabelsToAccessVertexI = stream.filter(pred).collect(Collectors.toList());

                    int[] d = new int[3];
                    d[TT] = LepI.getD(TT) + vertices[accessVertexI].c(TT);
                    d[RL] = LepI.getD(RL)  + vertices[accessVertexI].c(RL);
                    d[JL] = LepI.getD(JL)  + vertices[accessVertexI].c(JL);
                    Path path = new Path(LepI.getPath());
                    path.epsilon = existnigLabelsToAccessVertexI.size();
                    path.add(accessVertexI);
                    path.end = accessVertexI;

                    Label labelAccesible = new Label(existnigLabelsToAccessVertexI.size(), accessVertexI, d[TT], d[RL], d[JL], vertices[accessVertexI].getH(), path);

                    if (checkC1(labelAccesible, vertices)==true || checkC2(labelAccesible,vertices)==true) {

                    } else {
                        allDominated = false;
                    }

                    if (checkC2(labelAccesible,vertices) == false && checkC1(labelAccesible,vertices)==false){
                        labelsNotDominated.add(labelAccesible);
                    }


           //     }
            }
            if (allDominated == true){
                continue;
            } else {
                allDominated = true;
            }


            //Step 5. For each non-dominated path (identified in step 4), do: if the path is a complete path,
            // insert the label of the path into P and remove labels of the paths in P that are dominated by that path from P;
            // otherwise, insert the label of the path into Λ according to the MTLV rule. Go to step 2.

            for (Label label : labelsNotDominated){
                if (label.getPath().getEnd() == Constans.DESTINATION){
                    P.add(label);
                } else  {
                    delta.add(label);
                }
            }

            ArrayList<Label> temp1 = new ArrayList<Label> (delta);
            delta.clear();
            for (Label label : temp1) {
                if (checkC1(label, vertices) == true || checkC2(label, vertices) == true) {

                } else {
                    delta.add(label);
                }
            }



            //sort by MLTV
            Comparator<? super Label> comp = (l1, l2)  -> {
                if ((l1.dSum()+l1.getH()) > (l2.dSum()+l2.getH()))
                    return 1;
                else
                    return 0;
            };
            ArrayList<Label> temp = new ArrayList<Label> (delta);
            delta.clear();
            while (temp.size() != 0){
                Optional<Label> min =  temp.stream().min(comp);
                delta.add(min.get());
                temp.remove(min.get());
            }
            //delta.sort(comp);
        }
    }

    private void calculateU() {
        this.U = new int[3];
        this.U[TT] = Arrays.stream(grid.getGTable(TT)).max().getAsInt()*2;
        this.U[RL] = Arrays.stream(grid.getGTable(RL)).max().getAsInt()*2;
        this.U[JL] = Arrays.stream(grid.getGTable(JL)).max().getAsInt()*2;
    }

    private boolean checkC2(Label labelAccesible, Vertice[] vertices) {
        boolean dominated = false;
        int[] U = new int[3];
        U[TT] = 10;
        U[RL] = 24;
        U[JL] = 18;
        for (int k = 0; k<3; k++){
           // if (labelAccesible.getD(k) +  vertices[labelAccesible.getI()].c(k) + vertices[labelAccesible.getI()].getG(k) >= U[k]) {
            if (labelAccesible.getD(k) +  vertices[labelAccesible.getI()].getG(k) > U[k]) {
                dominated = true;
            }
        }
        return dominated;
    }

    private boolean checkC1(Label labelAccesible, Vertice[] vertices) {
        boolean dominated = false;
        int counter = 0;
        for (Label labelLambda : P){
            for (int k = 0; k<3; k++){
                //if (labelAccesible.getD(k)+ vertices[labelAccesible.getI()].c(k) + vertices[labelAccesible.getI()].getG(k) >= labelLambda.getD(k)){
                if (labelAccesible.getD(k) + vertices[labelAccesible.getI()].getG(k) >= labelLambda.getD(k)){
                    counter++;
                }
            }
            if (counter == Constans.PARAM_NUM){
                dominated = true;
                return dominated;
            }
            counter = 0;
        }
        return dominated;
    }

    private Label initialize(Vertice[] vertices) {
        int[] d = new int[PARAM_NUM];
        d[TT] = vertices[0].c(TT);
        d[RL] = vertices[0].c(RL);
        d[JL] = vertices[0].c(JL);
        int epsilon = SOURCE;
        Path path = new Path(epsilon, SOURCE, SOURCE);
        Label label = new Label(epsilon, SOURCE, d[TT], d[RL], d[JL], vertices[SOURCE].getH(), path);
        return label;
    }
}
