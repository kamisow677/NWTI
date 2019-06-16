package sample;

public class Label {
    private int epsilon;
    private int i;
    private int[] d;
    private int h;
    private Path path;

    public Label(int epsilon, int i, int d0, int d1, int d2, int h, Path path) {
        this.epsilon = epsilon;
        this.i = i;
        this.d = new int[3];
        this.d[0]=d0;
        this.d[1]=d1;
        this.d[2]=d2;
        this.h = h;
        this.path = path;
    }

    public int dSum(){
        return d[0] + d[1] +d[2];
    }

    public int getEpsilon() {
        return epsilon;
    }

    public void setEpsilon(int epsilon) {
        this.epsilon = epsilon;
    }

    public int getI() {
        return i;
    }

    public void setI(int i) {
        this.i = i;
    }

    public int getD(int k){
        try {
            switch (k){
                case Constans.TT:{
                    return d[Constans.TT];
                }
                case Constans.RL:{
                    return d[Constans.RL];
                }
                case Constans.JL:{
                    return d[Constans.JL];
                }
                default:
                    throw new Exception();

            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return -1;
    }

    public void setd(int k, int value){
        switch (k) {
            case Constans.TT: {
                d[Constans.TT] = value;
            }
            case Constans.RL: {
                d[Constans.RL] = value;
            }
            case Constans.JL: {
                d[Constans.JL] = value;
            }
        }
    }



    public int getH() {
        return h;
    }

    public void setH(int h) {
        this.h = h;
    }

    public Path getPath() {
        return path;
    }

    public void setPath(Path path) {
        this.path = path;
    }
}
