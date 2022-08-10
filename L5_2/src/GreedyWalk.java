import java.util.HashSet;
import java.util.Random;

public class GreedyWalk {

    Random random= new Random();
    PriorityQueue[] edges;
    HashSet<Integer> visited= new HashSet<>();
    double totalCost=0;
    int jumpNumb=0;

    public void createEdges(int numb){
        edges= new PriorityQueue[numb];
        for(int i=0; i<numb; i++){
            edges[i]= new PriorityQueue();
        }
    }

    public void addEdge(int prev, int next, double weight){
        edges[prev].insert(prev,weight,next);
        edges[next].insert(next,weight,prev);
    }

    public void greedyWalk(){
        random.setSeed(System.currentTimeMillis());
        int currentEdge= random.nextInt(edges.length);
        visited.add(currentEdge);
        while (visited.size()<edges.length){
            PriorityQueue.Item item= edges[currentEdge].pop();
            if(!visited.contains(item.vertex)){
                jumpNumb++;
                System.out.println("Jump: "+currentEdge+" to: "+item.vertex+" cost: "+item.prio);
                currentEdge= item.vertex;
                totalCost+=item.prio;
                visited.add(item.vertex);
            }
        }
    }
}
