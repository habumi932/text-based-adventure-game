import java.util.ArrayList;

public class ContainerItem extends Item{
    
    private ArrayList<Item> items;

    public ContainerItem(String name, String type, String description){
        super(name, type, description);
        items = new ArrayList<Item>();
    }

    public void addItem(Item item){
        items.add(item);
    }
    public boolean hasItem(String findName){
        for (int i = 0; i<items.size(); i++){
            if (items.get(i).getName().equalsIgnoreCase(findName)){
                return true;
            }
        }
        return false;
    }
    
    public Item removeItem(String removeName){
        Item removedItem = null;
        for (int i = 0; i<items.size(); i++){
            if (items.get(i).getName().equalsIgnoreCase(removeName)){
                removedItem = items.get(i);
                items.remove(i);
            }
        }
        return removedItem;
    }

    public Item getItem(String findName){
        for (int i = 0; i<items.size(); i++){
            if (items.get(i).getName().equalsIgnoreCase(findName)){
                return items.get(i);
            }
        }
        return null;
    }

    @Override
    public String toString(){
        String printMe = "";
        printMe += getName() + " [ " + getType() + " ] : " + getDescription() + ":\n";
        for(int i = 0; i < items.size(); i++){
            printMe += "+ " + items.get(i).getName() + "\n";
        }
        return printMe;
    }
}
