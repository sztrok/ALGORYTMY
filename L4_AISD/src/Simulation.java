import java.io.*;
import java.util.ArrayList;
import java.util.Random;

public class Simulation {
    public static final Random random= new Random();
    Tree tree;
    int operationsNumb;

    public Simulation(String string) throws IOException{
        switch (string) {
            case "bst" ->{
                tree = new BinarySearchTree();
                start();
            }
            case "rbt" -> {
                tree = new RedBlackTree();
                start();
            }
            case "splay" -> {
                tree = new SplayTree();
                start();
            }
            case "test" -> test();
            case "test2" -> test2();
        }
    }

    public void start() throws IOException{
        BufferedReader bufferedReader= new BufferedReader(new InputStreamReader(System.in));
        operationsNumb= Integer.parseInt(bufferedReader.readLine());
        long startTime= System.currentTimeMillis();
        while (operationsNumb>0){
            String line= bufferedReader.readLine();
            String[] args= line.split("[\\s]");
            String arg= args[0];
            switch (arg) {
                case "load" -> tree.load(args[1]);
                case "insert" -> tree.insert(args[1]);
                case "delete" -> tree.delete(args[1]);
                case "min" -> System.out.println(tree.min());
                case "max" -> System.out.println(tree.max());
                case "inorder" -> tree.inOrder();
                case "successor" -> System.out.println(tree.successor(args[1]));
                case "find" -> System.out.println(tree.find(args[1]));

                default -> {
                    System.out.println("Wrong operation");
                    operationsNumb++;
                }
            }
            operationsNumb--;
        }
        long endTime= System.currentTimeMillis();
        long totalTime=endTime-startTime;

        System.err.println("Total time: " + totalTime);
        System.err.println("Max numb of elements: "+tree.stats.maxElements);
        System.err.println("Last numb of elements: "+tree.stats.currElements);
        System.err.println("Inserts: "+tree.stats.insertOps);
        System.err.println("Deletes: "+tree.stats.deleteOps);
        System.err.println("Loads: "+tree.stats.loadOps);
        System.err.println("Finds: "+tree.stats.findOps);
        System.err.println("In orders: "+tree.stats.inorderOps);
        System.err.println("Min: "+tree.stats.minOps);
        System.err.println("Max: "+tree.stats.maxOps);
        System.err.println("Successors: "+tree.stats.successorOps);


    }

    public void test() throws IOException{
        int reps= 10;
        Tree bst= new BinarySearchTree();
        Tree rbt= new RedBlackTree();
        Tree splay= new RedBlackTree();
        Tree[] trees={bst,rbt,splay};

        String[] randomString= new String[100000];
        FileReader fileReader= new FileReader("D:\\Wszystko\\L4_AISD\\random.txt");
        BufferedReader bufferedReader= new BufferedReader(fileReader);
        String line= bufferedReader.readLine();
        for(int i=0; i<100000; i++){
            randomString[i]=line;
            line= bufferedReader.readLine();
        }

        for(int i=1000; i<=100000; i+=1000){

            int compsCreate,compsFind,compsInsert,compsDelete,compsFindMin,compsFindMax;

            long timeCreate,timeFind,timeInsert,timeDelete,timeFindMin,timeFindMax;

            long start,end;
            for(int j = 0; j < reps; j++) {
                shuffle(randomString);
                random.setSeed(System.currentTimeMillis());
                int findIndex = random.nextInt(reps);
                int deleteIndex = random.nextInt(reps);

                for (Tree tree : trees) {

                    start = System.nanoTime();
                    for (int q = 0; q < i; q++)
                        tree.insert(randomString[q]);
                    end = System.nanoTime();
                    timeCreate = end - start;
                    compsCreate = tree.stats.comp;
                    tree.stats.comp = 0;

                    start = System.nanoTime();
                    tree.find(randomString[findIndex]);
                    end = System.nanoTime();
                    timeFind = end - start;
                    compsFind = tree.stats.comp;
                    tree.stats.comp = 0;

                    start = System.nanoTime();
                    tree.insert("tah");
                    end = System.nanoTime();
                    timeInsert = end - start;
                    compsInsert = tree.stats.comp;
                    tree.stats.comp = 0;

                    start = System.nanoTime();
                    tree.delete(randomString[deleteIndex]);
                    end = System.nanoTime();
                    timeDelete = end - start;
                    compsDelete = tree.stats.comp;
                    tree.stats.comp = 0;

                    tree.stats.substitutes = 0;
                    start = System.nanoTime();
                    tree.min();
                    end = System.nanoTime();
                    timeFindMin = end - start;
                    compsFindMin = tree.stats.substitutes;
                    tree.stats.substitutes = 0;

                    start = System.nanoTime();
                    tree.max();
                    end = System.nanoTime();
                    timeFindMax = end - start;
                    compsFindMax = tree.stats.substitutes;
                    tree.stats.substitutes = 0;

                    writeToFile(tree.name, "stats", i + "," + compsCreate + "," + compsFind + "," + compsDelete + "," + compsInsert + "," + compsFindMin + "," + compsFindMax +
                            "," + timeCreate + "," + timeFind + "," + timeDelete + "," + timeInsert + "," + timeFindMin + "," + timeFindMax);
                }
                trees[0] = new BinarySearchTree();
                trees[1] = new RedBlackTree();
                trees[2] = new SplayTree();
            }

        }

    }

    public void test2() throws IOException {
        Tree bst= new BinarySearchTree();
        Tree rbt= new RedBlackTree();
        Tree splay= new SplayTree();

        Tree[] trees={bst,rbt,splay};
        ArrayList<String> aspell= new ArrayList<>();
        ArrayList<String> lotr= new ArrayList<>();
        ArrayList<String> kjb= new ArrayList<>();


        FileReader fileReader= new FileReader("D:\\Wszystko\\L4_AISD\\aspell_wordlist.txt");
        BufferedReader bufferedReader= new BufferedReader(fileReader);
        String line= bufferedReader.readLine();
        while(line!=null){
            aspell.add(line);
            line= bufferedReader.readLine();
        }

        fileReader= new FileReader("D:\\Wszystko\\L4_AISD\\lotr.txt");
        bufferedReader= new BufferedReader(fileReader);

        line= bufferedReader.readLine();
        while(line != null){
            String[] lines = line.split("[\\s,:;.!?\"]");
            for(String s : lines) {
                s = s.replaceAll("^[^a-zA-Z]*", "");
                if(s.compareToIgnoreCase("") != 0) lotr.add(s);
            }
            line = bufferedReader.readLine();
        }

        fileReader= new FileReader("D:\\Wszystko\\L4_AISD\\KJB.txt");
        bufferedReader= new BufferedReader(fileReader);

        line= bufferedReader.readLine();
        while(line != null){
            String[] lines = line.split("[\\s,:;.!?\"]");
            for(String s : lines) {
                s = s.replaceAll("^[^a-zA-Z]*", "");
                if(s.compareToIgnoreCase("") != 0) kjb.add(s);
            }
            line = bufferedReader.readLine();
        }

        for(Tree tree: trees){
            for(String string: aspell){
                tree.insert(string);
            }
            tree.stats.comp=0;
        }

        int randomNumb;

        for(int i=0; i<100; i++){
            randomNumb=random.nextInt(aspell.size()-1);
            int comps;
            for(Tree tree: trees){
                tree.find(aspell.get(randomNumb));
                comps= tree.stats.comp;
                tree.stats.comp=0;
                writeToFile(tree.name,"aspell",""+comps);
            }

        }
        trees[0]= new BinarySearchTree();
        trees[1]= new RedBlackTree();
        trees[2]= new SplayTree();

        String[] aspellArr= aspell.toArray(new String[0]);
        for(int i=0; i<10; i++){

            shuffle(aspellArr);
            for(Tree tree: trees){
                for(String string: aspellArr){
                    tree.insert(string);
                }
                tree.stats.comp=0;

                for(int j=0; j<10; j++){
                    randomNumb=random.nextInt(aspell.size()-1);
                    tree.find(aspell.get(randomNumb));
                    int comps= tree.stats.comp;
                    tree.stats.comp=0;
                    writeToFile(tree.name,"aspellPerm",""+comps);
                }

                trees[0]=new BinarySearchTree();
                trees[1]=new RedBlackTree();
                trees[2]=new SplayTree();
            }
        }

        for(Tree tree: trees){
            for(String string: lotr){
                tree.insert(string);
            }
            tree.stats.comp=0;
        }

        for(int i=0; i<100; i++){
            int comps;
            randomNumb=random.nextInt(lotr.size()-1);
            for(Tree tree: trees){
                tree.find(lotr.get(randomNumb));
                comps= tree.stats.comp;
                tree.stats.comp=0;
                writeToFile(tree.name,"lotr",""+comps);
            }
        }

        for(int i=0; i<10000; i++){
            int comps;
            randomNumb=random.nextInt(lotr.size()-1);
            for(Tree tree: trees){
                if(!tree.name.equals("bst")){
                    tree.find(lotr.get(randomNumb));
                    comps= tree.stats.comp;
                    tree.stats.comp=0;
                    writeToFile(tree.name,"lotr-10k",""+comps);
                }
            }
        }

        trees[0]= new BinarySearchTree();
        trees[1]= new RedBlackTree();
        trees[2]= new SplayTree();

        for(Tree tree: trees){
            for(String string: kjb){
                tree.insert(string);
            }
            tree.stats.comp=0;
        }

        for(int i=0; i<100; i++){
            int comps;
            randomNumb=random.nextInt(kjb.size()-1);
            for(Tree tree: trees){
                tree.find(kjb.get(randomNumb));
                comps= tree.stats.comp;
                tree.stats.comp=0;
                writeToFile(tree.name,"kjb",""+comps);
            }
        }

        for(int i=0; i<10000; i++){
            int comps;
            randomNumb=random.nextInt(kjb.size()-1);
            for(Tree tree: trees){
                if(!tree.name.equals("bst")){
                    tree.find(kjb.get(randomNumb));
                    comps= tree.stats.comp;
                    tree.stats.comp=0;
                    writeToFile(tree.name,"kjb",""+comps);
                }
            }
        }





    }

    public static void shuffle (Object[] array) {
        int n = array.length;
        while (n > 1) {
            n--;
            int k = random.nextInt(n);
            Object temp = array[n];
            array[n] = array[k];
            array[k] = temp;

        }
    }

    public static void writeToFile(String tree, String fileName, String line) throws IOException {
        File file = new File(tree + "-" + fileName + ".csv");
        FileWriter writer = new FileWriter(file,true);

        writer.write(line + '\n');
        writer.flush();
        writer.close();
    }
}
