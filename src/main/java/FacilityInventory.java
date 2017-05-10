package src.main.java;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Jordan on 5/9/2017.
 */
public class FacilityInventory implements Inventory {

    Map<Item, Integer> activeInventory = new HashMap<>();
    Map<Item, Integer> depletedInventory = new HashMap<>();

    public void updateInventoryItem(Item item, Integer quantity) {
        if (activeInventory.size() == 0 || activeInventory.get(item) == null) {
            activeInventory.put(item, quantity);
            return;
        }

        Integer originalQuantity = activeInventory.get(item);
        Integer newQuantity = quantity;

        // Need to update depleted inventory
        if (newQuantity < originalQuantity) {
            Integer numberOfDepleted = originalQuantity - newQuantity;
            depletedInventory.put(item, numberOfDepleted);
        }

        activeInventory.put(item, quantity);
    }

    @Override
    public void printInventory() {
        printActiveInventory();
        printDepletedInventory();
    }


    private void printActiveInventory() {
        System.out.println("Active Inventory: ");
        System.out.println("\tItem ID\t\tQuantity");
        for (Map.Entry<Item, Integer> entry : activeInventory.entrySet()) {
            System.out.println("\t" + entry.getKey().getId() + "\t\t" + entry.getValue() );
        }
        System.out.println("");
    }


    private void printDepletedInventory() {
        if (depletedInventory.size() == 0) {
            System.out.println("\tDepleted (Used-Up) Inventory: None");
            return;
        }

        System.out.println("\tDepleted (Used-Up) Inventory: ");
        System.out.println("\tItem ID\tQuantity");
        for (Map.Entry<Item, Integer> entry : activeInventory.entrySet()) {
            System.out.println("\t" + entry.getKey().getId() + "\t" + entry.getValue() );
        }
        System.out.println("");
        System.out.println("");
    }
}