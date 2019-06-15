package sample;
import static sample.Constans.JL;
import static sample.Constans.TT;
import static sample.Constans.RL;


public class Vertice {
    private int id;
    //cost vector
    int[] c;
    public Vertice(int id, int[] c) {
        this.id = id;
        this.c = c;
    }
    public Vertice(int id, int TT, int RL, int JL) {
        this.id = id;
        this.c[Constans.TT]=TT;
        this.c[Constans.RL]=RL;
        this.c[Constans.JL]=JL;
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
}
