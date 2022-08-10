import java.util.Arrays;
import java.util.LinkedList;
import java.util.Stack;

/**
 * Kosaraju's algorithm
 */
public class Test {

    private final int verticesNumb;
    private LinkedList<Integer>[] adj;

    Test(int v, LinkedList<Integer>[] list){

        verticesNumb = v;
        adj = list;
    }


    void DFSUtil(int v, boolean[] visited){

        visited[v] = true;
        System.out.print(v + " ");
        int n;
        for (Integer integer : adj[v]) {
            n = integer;
            if (!visited[n])
                DFSUtil(n, visited);
        }
    }

    Test transposeGraph(){

        Test graph = new Test(verticesNumb, adj);
        graph.adj = new LinkedList[verticesNumb];
        for (int i = 0; i< verticesNumb; ++i)
            graph.adj[i] = new LinkedList();
//        System.out.println("BEFORE:" +Arrays.toString(adj));
        for (int v = 0; v < verticesNumb; v++){

            for (Integer integer : adj[v]){
                graph.adj[integer].add(v);
            }
        }
//        System.out.println("AFTER:" +Arrays.toString(graph.adj));
        return graph;
    }

    void order(int v, boolean[] visited, Stack<Integer> stack){

        visited[v] = true;
        for (int n : adj[v]) {
            if (!visited[n])
                order(n, visited, stack);
        }

        stack.push(v);
    }

    void stronglyCC(){

        Stack<Integer> stack = new Stack<>();

        boolean[] visited = new boolean[verticesNumb];

        for (int i = 0; i < verticesNumb; i++) {
            if (!visited[i]) {
                order(i, visited, stack);
            }
        }

        Test transposeGraph = transposeGraph();

        for (int i = 0; i < verticesNumb; i++) {
            visited[i] = false;
        }


        while (!stack.empty()){
            int v = stack.pop();
            if (!visited[v]){
                transposeGraph.DFSUtil(v, visited);
                System.out.println();
            }
        }
    }


    public static void main(String[] args){

        int verticesNumb= Integer.parseInt(args[0]);
        int edgesNumb= Integer.parseInt(args[1]);

        LinkedList[] edges= new LinkedList[verticesNumb];

        for(int i=0; i<verticesNumb; i++){
            edges[i]= new LinkedList();
        }

        for(int i=0; i<edgesNumb*2; i+=2){
            int v1= Integer.parseInt(args[2+i]);
            int v2= Integer.parseInt(args[3+i]);
//            System.out.println("V1: "+v1);
//            System.out.println("V2: "+v2);
            edges[v1].add(v2);

        }

        System.out.println(Arrays.toString(edges));
//        System.out.println(edges[0]);
        Test test = new Test(verticesNumb, edges);
        test.stronglyCC();
    }
}
