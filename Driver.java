import java.util.Scanner;

public class Driver {
    private static Location currLocation;
    private static ContainerItem myInventory;
    public static void main(String[] args){

        System.out.println("WELCOME TO THE AMAZING DEPAUW WORLD!");
        
        myInventory = new ContainerItem("Backpack", "Container", "What you're carrying");

        Scanner myScanner = new Scanner(System.in);

        createWorld();

        while(true){
            System.out.println("Enter command: ");

            String userInput = myScanner.nextLine();
            String lowerCaseInput = userInput.toLowerCase();
            String[] inputs = lowerCaseInput.split(" ");
            String command = inputs[0];

            switch(command){
                case "quit":
                    if(inputs.length >= 2)
                    {
                        System.out.println("Your command is too long. If you want to quit, just type 'quit'.");
                    }
                    else{
                        System.exit(0);
                    } 
                    break;  

                case "look":
                    if(inputs.length >= 2)
                    {
                        System.out.println("Your command is too long. If you want to look, just type 'look'.");
                    }
                    else{
                        System.out.println(currLocation.getName() + " - " + currLocation.getDescription() + " currently has the following items: ");
                        for(int j = 0; j < currLocation.numItems(); j++){
                            Item temp = currLocation.getItem(j);
                            System.out.println("+ " + temp.getName());
                        }
                    }  
                    break;

                case "examine":
                    if(inputs.length == 1 ){
                        System.out.println("What do you want to examine??");
                    }
                    else if (inputs.length >= 3){
                        System.out.println("Your command is too long. If you want to examine, just type 'examine name'.");
                    }
                    else{
                        String itemName = inputs[1];
                        if (currLocation.hasItem(itemName)){
                                System.out.println(currLocation.getItem(itemName).toString());
                        }
                        else{
                            System.out.println("Cannot find that item here");
                        }
                    }
                    break;

                case "go":
                    if(inputs.length == 1 ){
                        System.out.println("Where do you want to go??");
                    }
                    else if (inputs.length >= 3){
                        System.out.println("Your command is too long. If you want to go, just type 'go direction'.");
                    }
                    else{
                        String direction = inputs[1];
                        if (currLocation.canMove(direction)){
                            currLocation = currLocation.getLocation(direction);
                        }
                        else{
                            System.out.println("You cannot move there.");
                        }
                    }
                    break;

                case "inventory":
                    if(inputs.length >= 2)
                    {
                        System.out.println("Your command is too long. If you want to look at inventory, just type 'inventory'.");
                    }
                    else{
                        System.out.println(myInventory.toString());
                    }  
                break;

                case "take":
                    if(inputs.length == 1 ){
                        System.out.println("What do you want to take??");
                    }
                    else if (inputs.length >= 5){
                        System.out.println("Your command is too long. If you want to take, just type 'take name' or 'take name from container'.");
                    }
                    else if(inputs.length == 2){
                        String itemName = inputs[1];
                        if (currLocation.hasItem(itemName)){
                            Item item = currLocation.getItem(itemName);
                            myInventory.addItem(item);
                            currLocation.removeItem(itemName);
                        }
                        else{
                            System.out.println("Cannot find that item here");                            }
                        }
                    else if(inputs.length == 4 && inputs[2].equals("from")){
                        String itemName = inputs[1];
                        String containerName = inputs[3];
                        if(currLocation.hasItem(containerName)){
                            Item container = currLocation.getItem(containerName);
                            if(container instanceof ContainerItem){
                                ContainerItem container1 = (ContainerItem) container;
                                if(container1.hasItem(itemName)){
                                    Item item = container1.getItem(itemName);
                                    myInventory.addItem(item);
                                    container1.removeItem(itemName);
                                }
                                else{
                                    System.out.println("Cannot find this item in container.");
                                }
                            }
                            else{
                                System.out.println("This item is not a container.");
                            }
                        }
                        else{
                            System.out.println("Cannot find this item in this location.");
                        }
                    }
                    else{
                        System.out.println("I cannot do that");
                    }
                        
                    break;

                case "drop":
                    if(inputs.length == 1 ){
                        System.out.println("What do you want to drop??");
                    }
                    else if (inputs.length >= 3){
                        System.out.println("Your command is too long. If you want to drop, just type 'drop name'.");
                    }
                    else{
                        String itemName = inputs[1];
                        if (myInventory.hasItem(itemName)){
                            Item item = myInventory.getItem(itemName);
                            currLocation.addItem(item);
                            myInventory.removeItem(itemName);
                        }
                        else{
                            System.out.println("Cannot find that item in your inventory");
                        }
                    }
                    break;

                case "help":
                    if(inputs.length >= 2)
                    {
                        System.out.println("Your command is too long. If you want to look, just type 'look'.");
                    }
                    else{
                        helper();
                    }  
                    break;
                
                case "put":
                    if(inputs.length == 1){
                        System.out.println("What do you want to put??");
                    }
                    else if(inputs.length >= 5){
                        System.out.println("Your command is too long. If you want to put, just type 'put name in container'.");
                    }
                    else if(inputs.length == 4 && inputs[2].equals("in")){
                        String itemName = inputs[1];
                        String containerName = inputs[3];
                        if(currLocation.hasItem(containerName)){
                            Item container = currLocation.getItem(containerName);
                            if(container instanceof ContainerItem){
                                ContainerItem container1 = (ContainerItem) container;
                                if(myInventory.hasItem(itemName)){
                                    Item item = myInventory.getItem(itemName);
                                    container1.addItem(item);
                                    myInventory.removeItem(itemName);
                                }
                                else{
                                    System.out.println("Cannot find this item in inventory.");
                                }
                            }
                            else{
                                System.out.println("This item is not a container");
                            }
                        }
                        else{
                            System.out.println("Cannot find container in this location.");
                        }
                    }
                    else{
                        System.out.println("I don't know how to do that");
                    }
                    break;
                    
                default:
                    System.out.println("I don't know how to do that");
                    break;
            }

        }
    }

    public static void createWorld(){
        Location location1 = new Location("Lucy", "The worst dorm at DePauw that is smelly and old");
        Location location2 = new Location("UB", "Union Building where students hang out and study");
        Location location3 = new Location("Reese", "The residence hall where the office of housing is located");
        Location location4 = new Location("Mason", "This is an old and cozy house that has an open field facing west");

        location4.connect("north", location1);
        location1.connect("south", location4);
        location4.connect("east", location2);
        location2.connect("west", location4);
        location4.connect("west", location3);
        location3.connect("east", location4);

        Item item1 = new Item("Vacuum", "Appliance", "This is very dusty");
        Item item2 = new Item("Chair", "Furniture", "An old dusty chair");
        Item item3 = new Item("Keys", "Valuable", "A golden key that can open the doors");
        Item item4 = new Item("Knife", "Utensil", "A silver metal knife");
        Item item5 = new Item("Shirt", "Clothe", "A free shirt that has DePauw logo on it");
        ContainerItem container1 = new ContainerItem("Closet", "Furniture", "Old brown closet");
        ContainerItem container2 = new ContainerItem("Bottle", "Container", "Starbucks thermal bottle");
        Item item6 = new Item("Hat", "Clothe", "A pink flower hat");
        Item item7 = new Item("Jeans", "Clothe", "A navy Forever21 jeans");
        Item item8 = new Item("Water", "Drink", "H2O");
        Item item9 = new Item("Clock", "Decoration", "A vintage brown clock");
        container1.addItem(item6);
        container1.addItem(item7);
        container2.addItem(item8);

        location1.addItem(item1);
        location2.addItem(item2);
        location3.addItem(item3);
        location4.addItem(item4);
        location2.addItem(item5);
        location1.addItem(container1);
        location2.addItem(container2);
        location4.addItem(item9);

        currLocation = location4;
    }

    public static void helper(){
        System.out.println("Below is the list of commands you can use:");
        System.out.println("+ quit : stops the game");
        System.out.println("+ look : information about your location");
        System.out.println("+ examine _____ : examine a specific item");
        System.out.println("+ go _____ : travel south/norht/east/west");
        System.out.println("+ inventory : examine your inventory");
        System.out.println("+ take ____ : take an item from your location");
        System.out.println("+ drop ____ : drop an item from your inventory");
        System.out.println("+ help : lists all commands");
    }
}