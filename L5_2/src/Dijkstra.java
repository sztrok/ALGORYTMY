public class Dijkstra {

    PriorityQueue startingVertices= new PriorityQueue();
    Edge[] edges;
    PriorityQueue.Item[] finalVertices;
    int startingVert;
    int edgeIndex=0;
    int vertIndex=0;




    public void createVertices(int numb){
        finalVertices= new PriorityQueue.Item[numb];
        for(int i=0; i<numb; i++){
            startingVertices.insert(i,Integer.MAX_VALUE,0);
        }
    }

    public void createEdges(int numb){
        edges= new Edge[numb];
    }

    public void addEdge(int prev, int next, double weight){
        edges[edgeIndex]= new Edge(prev,next,weight);
        edgeIndex++;
    }

    public void setStartingVert(int id){
        startingVert=id;
        startingVertices.setPrio(id,0);
    }

    public void getShortestPath(){
        while (!startingVertices.checkEmpty()){
            PriorityQueue.Item vertex= startingVertices.pop();
            for (Edge edge : edges) {
                if (edge.previous == vertex.value) {
                    double newWeight = vertex.prio + edge.weight;
                    startingVertices.changePriority(edge.next, newWeight, edge.previous);
                }
            }
            finalVertices[vertIndex]=vertex;
            vertIndex++;

        }


        for (PriorityQueue.Item finalVertex : finalVertices) {
            System.out.println(finalVertex.value + " | " + finalVertex.prio);
        }
    }
}
