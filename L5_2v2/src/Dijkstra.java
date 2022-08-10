import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.PriorityQueue;

public class Dijkstra {

    Vertex source;

    PriorityQueue<Vertex> vertexQueue= new PriorityQueue<>();

    Dijkstra(Vertex source){
        this.source=source;
    }

    public void calcPath(){
        source.dist=0;
        vertexQueue.add(source);

        while (!vertexQueue.isEmpty()){
            Vertex actualVertex= vertexQueue.peek();

            for(Edge edge: actualVertex.adjacentEdge){
                Vertex vertex= edge.next;
                if(!vertex.visited){
                    if(actualVertex.dist+edge.weight< vertex.dist){
                        vertex.dist=actualVertex.dist+edge.weight;
                        vertex.previous= actualVertex;
                        vertexQueue.add(vertex);
                    }
                }
            }

            vertexQueue.poll();
            actualVertex.visited=true;
        }
    }


    ArrayList<Vertex> shortestPath(Vertex vertex){
        ArrayList<Vertex> path= new ArrayList<>();
        path.add(vertex);
        while (vertex.previous!=null){
            path.add(vertex.previous);
            vertex= vertex.previous;
        }

        Collections.reverse(path);
        return path;
    }

}
