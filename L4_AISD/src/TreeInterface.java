import java.io.IOException;

public interface TreeInterface {

    void insert(String key);
    void load(String path) throws IOException;
    void delete(String key);
    boolean find(String key);
    String min();
    String max();
    String successor(String key);
    void inOrder();
}
