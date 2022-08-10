import java.util.*;

public class Test {

    private int V;

    private final LinkedList<Integer>[] adj;

    Test(int v, LinkedList<Integer>[] list){

        V = v;
        adj =list;
    }

    void topologicalSortUtil(int v, boolean[] visited, Stack<Integer> stack){
        visited[v] = true;
        int i;

        for (int n : adj[v]) {
            i = n;
            if (!visited[i])
                topologicalSortUtil(i, visited, stack);
        }

        stack.push(v);
    }

    void topologicalSort(){

        Stack<Integer> stack = new Stack<>();
        boolean[] visited = new boolean[V];

        for (int i = 0; i < V; i++){
            if (!visited[i]) {
                topologicalSortUtil(i, visited, stack);
            }
        }

        while (!stack.empty()) {
            System.out.print(stack.pop() + " ");
        }
    }

    private boolean isCyclic(){

        boolean[] visited = new boolean[V];
        boolean[] recursionStack = new boolean[V];

        for (int i = 0; i < V; i++)
            if (isCyclicUtil(i, visited, recursionStack))
                return true;

        return false;
    }

    private boolean isCyclicUtil(int i, boolean[] visited, boolean[] recursionStack){

        if (recursionStack[i]) return true;
        if (visited[i]) return false;

        visited[i] = true;
        recursionStack[i] = true;
        List<Integer> children = adj[i];

        for (Integer c: children) {
            if (isCyclicUtil(c, visited, recursionStack)) {
                return true;
            }
        }
        recursionStack[i] = false;

        return false;
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

        test.topologicalSort();
        System.out.println();
        if(test.isCyclic()) System.out.println("W tym grafie jest cykl");
        else System.out.println("W tym grafie nie ma cyklu");
    }
}
