import java.util.ArrayList;
import java.util.HashMap;

public class Location {
    
    private String name;
    private String description;
    private ArrayList<Item> items;
    private HashMap<String, Location> connections;

    public Location(String name, String description){
        this.name = name;
        this.description = description;
        items = new ArrayList<Item>();
        connections = new HashMap<String, Location>();
    }

    //Getters
    public String getName(){
        return name;
    }
    public String getDescription(){
        return description;
    }

    //Setters
    public void setName(String name){
        this.name = name;
    }
    public void setDescription(String description){
        this.description = description;
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

    public Item getItem(String findName){
        for (int i = 0; i<items.size(); i++){
            if (items.get(i).getName().equalsIgnoreCase(findName)){
                return items.get(i);
            }
        }
        return null;
    }
   
    public Item getItem (int index){
        if (index >= items.size() || index < 0){
            return null;
        }
        return items.get(index);
    }

    public int numItems(){
        return items.size();
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

    public void connect(String direction, Location location){
        connections.put(direction, location);
    }

    public boolean canMove(String direction){
        return connections.containsKey(direction);
    }

    public Location getLocation(String direction){
        return connections.get(direction);
    }

}
