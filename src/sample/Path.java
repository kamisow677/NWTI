package sample;

import java.util.ArrayList;

public class Path {
    int epsilon;
    int start;
    int end;
    ArrayList listOfVertices;

    public Path(Path path) {
        this.epsilon = path.epsilon;
        this.start = path.start;
        this.end = path.end;
        this.listOfVertices = new ArrayList();
        this.listOfVertices.addAll(path.listOfVertices);
    }

    public Path(int epsilon, int start, int end) {
        this.epsilon = epsilon;
        this.start = start;
        this.end = end;
        this.listOfVertices = new ArrayList();
        this.listOfVertices.add(start);
    }

    public void add(int i){
        listOfVertices.add(i);
    }

    public int getStart() {
        return start;
    }

    public void setStart(int start) {
        this.start = start;
    }

    public int getEnd() {
        return end;
    }

    public void setEnd(int end) {
        this.end = end;
    }
}
