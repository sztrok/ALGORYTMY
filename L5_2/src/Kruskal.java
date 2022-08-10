public class Kruskal {

    Vertex[] vertices;
    PriorityQueue startingEdges = new PriorityQueue();
    PriorityQueue.Item[] finalEdges;

    int edgeIndex=0;

    static class Vertex{
        int value;
        double rank;
        Vertex parent;
        Vertex(int value){
            this.value=value;
            this.rank=0;
            this.parent=this;
        }
    }

    public void createVertices(int numb){
        vertices= new Vertex[numb];
        for(int i=0; i<numb; i++){
            vertices[i]= new Vertex(i);
        }
        finalEdges = new PriorityQueue.Item[numb-1];
    }

    private Vertex findParent(int value){
        Vertex temp= vertices[value];
        if(!temp.parent.equals(temp)){
            temp.parent=findParent(temp.parent.value);
        }
        return temp.parent;
    }

    private void union(int index1, int index2){
        Vertex v1= findParent(index1);
        Vertex v2= findParent(index2);
        if(v1.rank> v2.rank){
            v2.parent= v1;
        }
        else{
            v1.parent=v2;
            if(v1.rank== v2.rank){
                v2.rank++;
            }
        }
    }

    public void addEdge(int prev, int next, double weight){
        startingEdges.insert(prev,weight,next);
    }

    public void kruskalTree(){
        while (!startingEdges.checkEmpty()){
            PriorityQueue.Item item= startingEdges.pop();
            if(!findParent(item.value).equals(findParent(item.vertex))){
                finalEdges[edgeIndex]= item;
                edgeIndex++;
                union(item.value, item.vertex);
            }
        }

        for (PriorityQueue.Item finalEdge : finalEdges) {
            System.out.println(finalEdge.value + " | " + finalEdge.vertex + " | " + finalEdge.prio);
        }
    }

}
