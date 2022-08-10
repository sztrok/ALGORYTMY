import java.util.ArrayList;
import java.util.List;

public class Vertex implements Comparable<Vertex> {


    int id;
    double dist= Double.MAX_VALUE;
    boolean visited;
    ArrayList<Edge> adjacentEdge= new ArrayList<>();

    Vertex previous= null;

    Vertex(int id) {
        this.id = id;
    }

    public void addAdjacentEdge(Edge e){
        adjacentEdge.add(e);
    }

    @Override
    public int compareTo(Vertex o) {
        return (int) (this.dist- o.dist);
    }
}
