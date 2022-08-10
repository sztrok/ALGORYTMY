import java.util.HashSet;
import java.util.Random;
import java.util.Timer;

public class RandomWalk {

    Random random= new Random();
    Vertex[] vertices;
    HashSet<Integer> visited= new HashSet<>();
    double totalCost=0;
    int jumpsNumb=0;

    static class Vertex{
        double[] costs;
        Vertex(int numb){
            costs= new double[numb];
        }
    }

    public void createVertices(int numb){
        vertices= new Vertex[numb];
        for(int i=0; i<numb; i++){
            vertices[i]= new Vertex(numb);
        }
    }

    public void addEdge(int prev, int next, double weight){
        vertices[prev].costs[next]=weight;
        vertices[next].costs[prev]=weight;
    }

    public void randomWalk(){
//        totalCost=0;
//        jumpsNumb=0;
        random.setSeed(System.currentTimeMillis());
        int currentVertex= random.nextInt(vertices.length);
        visited.add(currentVertex);
        while(visited.size()< vertices.length){
            int next= random.nextInt(vertices.length);
            if(next==currentVertex){
                next=vertices.length-1;
            }
            totalCost+=vertices[currentVertex].costs[next];
            visited.add(next);
            jumpsNumb++;
//            System.out.println("Jump: "+currentVertex+" to: "+next+ " cost: "+vertices[currentVertex].costs[next]);
            currentVertex=next;
        }

        System.out.println("TOTAL COST: "+totalCost);
    }

}
