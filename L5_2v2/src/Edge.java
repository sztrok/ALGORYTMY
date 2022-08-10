public class Edge {

    Vertex prev;
    Vertex next;
    double weight;

    Edge(Vertex prev, Vertex next, double weight){
        this.prev=prev;
        this.next=next;
        this.weight=weight;
    }
}
