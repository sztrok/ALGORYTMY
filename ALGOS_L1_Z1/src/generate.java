import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Random;




public class generate {

// n(n-1)/2

    public static void generateGraph(int edgesNumb) throws FileNotFoundException {
        String fileName="x"+ edgesNumb +".txt";
        PrintWriter out= new PrintWriter(fileName);
        String x10String= "";
        Random random= new Random(System.currentTimeMillis());


        int x10EdgesNumb= random.nextInt();

        x10EdgesNumb=x10EdgesNumb%(edgesNumb*(edgesNumb-1)/2) +1;
        if(x10EdgesNumb < 0) x10EdgesNumb= -x10EdgesNumb;
        if(x10EdgesNumb < edgesNumb/2) x10EdgesNumb=5;
//        System.out.println(x10EdgesNumb);
        x10String+= String.valueOf(x10EdgesNumb);
        x10String+=" ";
        for(int i=0; i<x10EdgesNumb; i++){
            int e1= random.nextInt() % edgesNumb ;
            int e2= random.nextInt() % edgesNumb;
            if(e1<0) e1= -e1;
            if(e2<0) e2= -e2;
//            System.out.println("E1: "+e1);
//            System.out.println("E2: "+e2);
            if(e1==e2) e2+=1 %edgesNumb;
            x10String+=String.valueOf(e1);
            x10String+=" ";
            x10String+=String.valueOf(e2);
            x10String+=" ";
        }
        out.print(x10String);

        out.close();
    }

    public static void main(String[] args) throws FileNotFoundException {
        generateGraph(10);
        generateGraph(100);
        generateGraph(1000);
        generateGraph(10000);

    }
}
