import java.util.Arrays;
import java.util.LinkedList;



public class Test {

    private final int V;
    public boolean directed;

    private final LinkedList<Integer>[] adj;

    Test(int v, boolean directed, LinkedList<Integer>[] list){

        V = v;
        adj = list;
        this.directed=directed;
    }


    void BFS(int startingVertex){

        boolean[] visited = new boolean[V];
        LinkedList<Integer> queue = new LinkedList<>();
        visited[startingVertex]=true;
        queue.add(startingVertex);

        while (queue.size() != 0){
            startingVertex = queue.poll();
            System.out.print(startingVertex+" ");
            for (int n : adj[startingVertex]){
                if (!visited[n]){
                    visited[n] = true;
                    queue.add(n);
                }
            }
        }
    }


    public static void main(String[] args){

        boolean directed=true;

        if(args[0].equals("u")){
            directed=false;
        }
        int verticesNumb= Integer.parseInt(args[1]);
        int edgesNumb= Integer.parseInt(args[2]);

        LinkedList[] edges= new LinkedList[verticesNumb];

        for(int i=0; i<verticesNumb; i++){
            edges[i]= new LinkedList();
        }

        for(int i=0; i<edgesNumb*2; i+=2){
            int v1= Integer.parseInt(args[3+i]);
            int v2= Integer.parseInt(args[4+i]);
            edges[v1].add(v2);
            if (!directed) {
                edges[v2].add(v1);
            }
        }

        System.out.println(Arrays.toString(edges));
        Test test = new Test(verticesNumb,directed, edges);

        test.BFS(1);
    }
}
