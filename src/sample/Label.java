package sample;

public class Label {
    int epsilon;
    int i;
    int[] d;
    int h;
    Path path;

    public Label(int epsilon, int i, int[] d, int h, Path path) {
        this.epsilon = epsilon;
        this.i = i;
        this.d = d;
        this.h = h;
        this.path = path;
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

    public int getD() {
        return d;
    }

    public void setD(int d) {
        this.d = d;
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
