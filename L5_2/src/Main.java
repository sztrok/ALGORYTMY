import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class Main {


    public static void main(String[] args) throws IOException, InterruptedException {

//        RandomWalk randomWalk= new RandomWalk();
//        randomWalk.createVertices(5);
//        randomWalk.addEdge(0,1,0.1);
//        randomWalk.addEdge(1,2,0.1);
//        randomWalk.addEdge(2,2,0.3);
//        randomWalk.addEdge(2,4,0.8);
//        randomWalk.addEdge(0,4,0.2);
//        randomWalk.addEdge(1,4,0.5);
//        randomWalk.addEdge(3,4,0.3);
//        randomWalk.addEdge(0,3,0.6);
//        randomWalk.addEdge(2,3,0.1);
//        randomWalk.addEdge(1,3,0.3);



//        for(int i=0; i<1000; i++){
//            Thread.sleep(100);
//            long start= System.nanoTime();
//            RandomWalk randomWalk= new RandomWalk();
//            randomWalk.createVertices(5);
//            randomWalk.addEdge(0,1,0.1);
//            randomWalk.addEdge(1,2,0.1);
//            randomWalk.addEdge(2,2,0.3);
//            randomWalk.addEdge(2,4,0.8);
//            randomWalk.addEdge(0,4,0.2);
//            randomWalk.addEdge(1,4,0.5);
//            randomWalk.addEdge(3,4,0.3);
//            randomWalk.addEdge(0,3,0.6);
//            randomWalk.addEdge(2,3,0.2);
//            randomWalk.addEdge(1,3,0.3);
//            randomWalk.randomWalk();
//            long end= System.nanoTime();
//            long time= end- start;
//            writeToFile("randomWalk","test",""+randomWalk.jumpsNumb+","+time);
//        }

//        for(int i=0; i<20; i++){
//            Thread.sleep(100);
//            long start= System.nanoTime();
//            Kruskal kruskal= new Kruskal();
//            kruskal.createVertices(5);
//            kruskal.addEdge(0,1,0.1);
//            kruskal.addEdge(1,2,0.1);
//            kruskal.addEdge(2,2,0.3);
//            kruskal.addEdge(2,4,0.8);
//            kruskal.addEdge(0,4,0.2);
//            kruskal.addEdge(1,4,0.5);
//            kruskal.addEdge(3,4,0.3);
//            kruskal.addEdge(0,3,0.6);
//            kruskal.addEdge(2,3,0.2);
//            kruskal.addEdge(1,3,0.3);
//            kruskal.kruskalTree();


//
//        }
//        for(int i=0; i<1000; i++){
////            Thread.sleep(100);
//            long start= System.nanoTime();
//            Prim prim= new Prim();
//        System.out.println("Prim");
//            prim.createVertices(8);
//            prim.createEdges(11);
//            prim.addEdge(0,2,0.3);
//            prim.addEdge(0,1,0.1);
//            prim.addEdge(1,3,0.7);
//            prim.addEdge(1,5,0.9);
//            prim.addEdge(2,4,0.6);
//            prim.addEdge(3,6,0.8);
//            prim.addEdge(4,3,0.7);
//            prim.addEdge(4,5,0.4);
//            prim.addEdge(5,6,1.0);
//            prim.addEdge(5,7,0.5);
//            prim.addEdge(7,6,0.4);
//            prim.setStartingVert(0);
//            prim.primTree();
//            long end= System.nanoTime();
//            long time= end- start;
//            writeToFile("primTree","test",""+time);
//        }

        Prim prim= new Prim();
        prim.createVertices(8);
        prim.createEdges(11);
        prim.addEdge(0,2,0.3);
        prim.addEdge(0,1,0.1);
        prim.addEdge(1,3,0.7);
        prim.addEdge(1,5,0.9);
        prim.addEdge(2,4,0.6);
        prim.addEdge(3,6,0.8);
        prim.addEdge(4,3,0.7);
        prim.addEdge(4,5,0.4);
        prim.addEdge(5,6,1.0);
        prim.addEdge(5,7,0.5);
        prim.addEdge(7,6,0.4);
        prim.setStartingVert(0);
        prim.primTree();


//        for(int i=0; i<1000; i++){
////            Thread.sleep(100);
//            long start= System.nanoTime();
//            Kruskal kruskal= new Kruskal();
//        System.out.println("Kruskal");
//            kruskal.createVertices(8);
//            kruskal.addEdge(0,2,0.3);
//            kruskal.addEdge(0,1,0.1);
//            kruskal.addEdge(1,3,0.7);
//            kruskal.addEdge(1,5,0.9);
//            kruskal.addEdge(2,4,0.6);
//            kruskal.addEdge(3,6,0.8);
//            kruskal.addEdge(4,3,0.7);
//            kruskal.addEdge(4,5,0.4);
//            kruskal.addEdge(5,6,1.0);
//            kruskal.addEdge(5,7,0.5);
//            kruskal.addEdge(7,6,0.4);
//            kruskal.kruskalTree();
//            long end= System.nanoTime();
//            long time= end- start;
////        System.out.println(time);
//            writeToFile("kruskalTree","test",""+time);
//        }


//        for(int i=0; i<20; i++){
//            Thread.sleep(100);
//            long start= System.nanoTime();
//            GreedyWalk greedyWalk= new GreedyWalk();
//            greedyWalk.createEdges(10);
//            greedyWalk.addEdge(0,1,0.1);
//            greedyWalk.addEdge(1,2,0.1);
//            greedyWalk.addEdge(2,2,0.3);
//            greedyWalk.addEdge(2,4,0.8);
//            greedyWalk.addEdge(0,4,0.2);
//            greedyWalk.addEdge(1,4,0.5);
//            greedyWalk.addEdge(3,4,0.3);
//            greedyWalk.addEdge(0,3,0.6);
//            greedyWalk.addEdge(2,3,0.2);
//            greedyWalk.addEdge(1,3,0.3);
//            greedyWalk.greedyWalk();
//            long end= System.nanoTime();
//            long time= end- start;
//        }
//        for(int i=0; i<1000; i++){
//            long start= System.nanoTime();
//            Dijkstra dijkstra= new Dijkstra();
//            dijkstra.createVertices(8);
//            dijkstra.createEdges(11);
//            dijkstra.addEdge(0,2,0.3);
//            dijkstra.addEdge(0,1,0.1);
//            dijkstra.addEdge(1,3,0.7);
//            dijkstra.addEdge(1,5,0.9);
//            dijkstra.addEdge(2,4,0.6);
//            dijkstra.addEdge(3,6,0.8);
//            dijkstra.addEdge(4,3,0.7);
//            dijkstra.addEdge(4,5,0.4);
//            dijkstra.addEdge(5,6,1.0);
//            dijkstra.addEdge(5,7,0.5);
//            dijkstra.addEdge(7,6,0.4);
//            dijkstra.setStartingVert(0);
//            dijkstra.getShortestPath();
//            long end= System.nanoTime();
//            long time= end- start;
//            writeToFile("dijkstra","test",""+time);
//        }
//        Random random= new Random();
//        random.setSeed(System.currentTimeMillis());
//        PriorityQueue priorityQueue= new PriorityQueue();
//        for(int i=0; i<10; i++){
//            int value= random.nextInt(10);
//            double prio= random.nextFloat();
//            priorityQueue.insert(value,prio,0);
//        }
//        priorityQueue.print();
//        priorityQueue.insert(20,0,0);
//        priorityQueue.getTop();
//        PriorityQueue.Item item= priorityQueue.pop();
//        priorityQueue.getTop();
//        System.out.println(item.value+"|"+item.prio);
//        priorityQueue.pop();
//        priorityQueue.print();


//        for(int i=0; i<1000; i++){
//            randomWalk.randomWalk();

//        }


    }



    public static void writeToFile(String string, String fileName, String line) throws IOException {
        File file = new File(string + "-" + fileName + ".csv");

        // creates a FileWriter Object
        FileWriter writer = new FileWriter(file,true);

        // Writes the content to the file
        writer.write(line + '\n');
        writer.flush();
        writer.close();
    }


}
