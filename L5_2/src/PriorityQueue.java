import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

public class PriorityQueue {

    private final ArrayList<Item> itemList= new ArrayList<>();
    private final HashMap<Integer, Set<Integer>> hashMap= new HashMap<>();

    static class Item{
        int value;
        double prio;
        int vertex;


        Item(int value, double prio){
            this.value=value;
            this.prio=prio;
        }
    }

    public boolean checkEmpty(){
        return itemList.isEmpty();
    }

    private void addToMap(int value, int pos){
        if(hashMap.get(value)==null){
            HashSet<Integer> hashSet= new HashSet<>();
            hashSet.add(pos);
            hashMap.put(value, hashSet);
        }
        else hashMap.get(value).add(pos);
    }

    private void removeFromMap(int value, int pos){
        if(hashMap.get(value)!=null){
            hashMap.get(value).remove(pos);
            if(hashMap.get(value).isEmpty()){
                hashMap.remove(value);
            }
        }
    }

    private int getPrevious(int pos){
        if(pos==0) return 0;
        return pos -1;
    }

    private int addToQueue(Item item, int pos){
        while(pos>0 & itemList.get(getPrevious(pos)).prio>item.prio){
            removeFromMap(itemList.get(pos).value,pos);
            itemList.set(pos,itemList.get(getPrevious(pos)));
            addToMap(itemList.get(pos).value,pos);
            pos= getPrevious(pos);
        }
        removeFromMap(itemList.get(pos).value,pos);
        itemList.set(pos, item);
        addToMap(itemList.get(pos).value, pos);

        return pos;
    }

    public void insert(int value, double prio, int vertex){
        Item item= new Item(value, prio);
        item.vertex=vertex;
        itemList.add(item);
        int pos= itemList.size()-1;
        addToMap(item.value,pos);
        addToQueue(item,pos);
    }

    public void getTop(){
        if(itemList.isEmpty()){
            System.out.println("");
        }
        else{
            System.out.println(itemList.get(0).value+"|"+itemList.get(0).prio);
        }
    }

    public Item pop(){
        if (itemList.isEmpty()){
            return null;
        }

        Item topItem= itemList.get(0);
        System.out.println("Pop: "+topItem.value+"|"+topItem.prio);
        removeFromMap(itemList.get(0).value,0);
        itemList.set(0,itemList.get(itemList.size()-1));
        addToMap(itemList.get(0).value,0);
        removeFromMap(itemList.get(0).value, itemList.size()-1);
        itemList.remove(itemList.size()-1);
        reorderHeap(0);
        return topItem;

    }

    private void reorderHeap(int pos){
        if(itemList.isEmpty()){
            return;
        }
        Item newItem;
        int newPos=pos;

        if(pos+1< itemList.size() && itemList.get(pos).prio> itemList.get(pos+1).prio){
            newPos= pos+1;
            newItem= itemList.get(newPos);
        }
        else{
            newItem= itemList.get(pos);
        }

        if(newPos!= pos){
            removeFromMap(itemList.get(newPos).value, newPos);
            itemList.set(newPos,itemList.get(pos));
            addToMap(itemList.get(newPos).value, newPos);
            removeFromMap(itemList.get(pos).value, pos);
            itemList.set(pos, newItem);
            addToMap(itemList.get(pos).value, pos);
            reorderHeap(newPos);
        }
    }

    public void setPrio(int value, double prio){
        Set<Integer> set= hashMap.get(value);
        int[] arr= new int[set.size()];
        int index=0;
        for(Integer i: set){
            arr[index]=i;
            index++;
        }

        for(Integer i: arr){
            if(itemList.get(i).prio>prio){
                itemList.get(i).prio=prio;
                int newPos= addToQueue(itemList.get(i),i);
                if(newPos==i){
                    reorderHeap(i);
                }
            }
        }
    }

    public void print(){
        for(int i=0; i<itemList.size();i++){
            System.out.println(i+" | "+itemList.get(i).value+" | "+itemList.get(i).prio);
        }
    }

    public void changePriority(int value, double prio, int vertex){
        if(hashMap.get(value)==null){
            return;
        }
        int pos= hashMap.get(value).iterator().next();
        if(itemList.get(pos).prio> prio){
            itemList.get(pos).prio= prio;
            itemList.get(pos).vertex=vertex;
            int newPos= addToQueue(itemList.get(pos),pos);
            if(newPos==pos){
                reorderHeap(pos);
            }
        }
    }



}
