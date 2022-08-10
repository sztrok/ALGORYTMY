public class Prim {
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

    public void primTree(){
        while (!startingVertices.checkEmpty()){
            PriorityQueue.Item item= startingVertices.pop();
            for(Edge edge: edges){
                if(edge.previous==item.value){
                    startingVertices.changePriority(edge.next,edge.weight, edge.next);
                }
                else if(edge.next==item.value){
                    startingVertices.changePriority(edge.previous, edge.weight, edge.next);
                }
            }

            finalVertices[vertIndex]= item;
            vertIndex++;
        }

    }

}
