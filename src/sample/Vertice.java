package sample;
import java.util.ArrayList;

import static sample.Constans.JL;
import static sample.Constans.TT;
import static sample.Constans.RL;


public class Vertice {
    private int id;
    //cost vector
    int[] c;
    int[] g;
    int h;
    ArrayList<Integer> phi_a;
    public Vertice(int id, int[] c) {
        this.id = id;
        this.c = c;
    }
    public Vertice(int id, int TT, int RL, int JL) {
        this.id = id;
        this.phi_a = new ArrayList<>();
        this.c = new int[3];
        this.g = new int[3];
        this.c[Constans.TT]=TT;
        this.c[Constans.RL]=RL;
        this.c[Constans.JL]=JL;
    }
    public int c(int i){
        try {
            switch (i){
                case Constans.TT:{
                    return c[Constans.TT];
                }
                case Constans.RL:{
                    return c[Constans.RL];
                }
                case Constans.JL:{
                    return c[Constans.JL];
                }
                default:
                        throw new Exception();

            }
        } catch (Exception e) {
                e.printStackTrace();
        }
        return -1;
    }
    public int g(int i){
        try {
            switch (i){
                case Constans.TT:{
                    return g[Constans.TT];
                }
                case Constans.RL:{
                    return g[Constans.RL];
                }
                case Constans.JL:{
                    return g[Constans.JL];
                }
                default:
                    throw new Exception();

            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return -1;
    }

    public int getTT(){
        return c[Constans.TT];
    }
    public int getRL(){
        return c[Constans.RL];
    }
    public int getJL(){
        return c[Constans.JL];
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getH() {
        return h;
    }

    public void setH(int h) {
        this.h = h;
    }

    public ArrayList<Integer> getPhi_a() {
        return phi_a;
    }

    public void setPhi_a(ArrayList<Integer> phi_a) {
        this.phi_a = phi_a;
    }
}
