import java.util.ArrayList;

public class Main {

    public static void main(String[] args){
        Vertex[] vertex = {new Vertex(0), new Vertex(1), new Vertex(2)};

        Edge[] edges={new Edge(vertex[0],vertex[1],0.3), new Edge(vertex[1],vertex[2],0.2), new Edge(vertex[0],vertex[2],0.7)};

        vertex[0].addAdjacentEdge(edges[0]);
        vertex[0].addAdjacentEdge(edges[2]);
        vertex[1].addAdjacentEdge(edges[0]);
        vertex[1].addAdjacentEdge(edges[1]);
        vertex[2].addAdjacentEdge(edges[1]);
        vertex[2].addAdjacentEdge(edges[2]);

        Dijkstra dijkstra= new Dijkstra(vertex[0]);
        dijkstra.calcPath();
        ArrayList<Vertex> path= dijkstra.shortestPath(vertex[2]);
        System.out.println(path.get(0).id+" "+path.get(1).id);
    }
}
