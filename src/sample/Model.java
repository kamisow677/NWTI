package sample;

public class Model {
    Grid grid = new Grid();
    public void runCalculations(){

        MLCA();
    }
    public void MLCA(){
        //Step 0. Obtain the values of hi and  for i ∈ V and k = 1, 2, 3, using Dijkstra’s algorithm. (See the appendix for procedures.) Let Λ ← ∅.

        //Step 1. Set d1(s, s) ← () and d1(s, i) ← (∞, ∞, ∞), i ∈ V{s}.
        // Insert the label of the path containing only the source vertex, L1(s) = { hs, π1(s, s)}, to Λ, i.e., let Λ ← {L1(s)}.

        //Step 2. If Λ = ∅, terminate. Otherwise, select the label at the top of Λ, which has the minimum total label-value (and remove it from Λ).

        //Step 3. Let Lξ(i) be the selected label in step 2. If there is no vertex directly accessible from vertex i, go to step 2.
        // Otherwise, i.e., if there are vertices directly accessible from vertex i, go to step 4.

        //Step 4. If each and every (partial) path extended from the current partial path associated with Lξ(i) satisfies
        // either (C1)  for all k = 1, 2, 3, and for some λ = 1, … , |P| or (C2) , , or , that is, if the extended (partial) path would result
        //in an infeasible solution, go to step 2; otherwise, go to step 5.

        //Step 5. For each non-dominated path (identified in step 4), do: if the path is a complete path,
        // insert the label of the path into P and remove labels of the paths in P that are dominated by that path from P;
        // otherwise, insert the label of the path into Λ according to the MTLV rule. Go to step 2.
    }
}
