import java.util.Arrays;
import java.util.LinkedList;

public class Test {

    private final int V;
    public boolean directed;

    private final LinkedList<Integer>[] adj;

    Test(int v, boolean directed, LinkedList<Integer>[] list){

        V = v;
        adj=list;
        this.directed=directed;
    }

    void DFSUtil(int v, boolean[] visited){

        visited[v] = true;
        System.out.print(v + " ");
        for (int n : adj[v]) {
            if (!visited[n]) {
                DFSUtil(n, visited);
            }
        }

    }

    void DFS(int v){

        System.out.println("Directed: "+directed);
        boolean[] visited = new boolean[V];

        DFSUtil(v, visited);
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

        test.DFS(1);
    }
}
