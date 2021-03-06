package src.main.java;

import src.main.java.exceptions.DataValidationException;
import src.main.java.exceptions.NegativeQuantityException;
import src.main.java.exceptions.NoAvailableDaysException;
import src.main.java.exceptions.NullParameterException;
import src.main.java.interfaces.*;
import src.main.java.interfaces.impl.*;
import src.main.java.shortestpath.FacilityEdge;

import java.util.ArrayList;

/**
 * Created by Jordan on 5/8/2017.
 */
public class FacilityImpl implements Facility, Comparable<Facility> {

    private String location = "";
    private Integer ratePerDay = 0;
    private Integer costPerDay = 300;
    private ArrayList<FacilityEdge> neighbors = new ArrayList<>();

    private Schedule schedule = new FacilitySchedule();
    private Inventory inventory = new FacilityInventory();

    private NeighborPrinter neighborPrinter = new NeighborPrinterImpl();

    // Vertex fields
    private Integer minDistance = Integer.MAX_VALUE;

    private Facility previous = null;

    public FacilityImpl(String location) {
        this.location = location;
    }

    public FacilityImpl(String location, Integer ratePerDay, Integer costPerDay) {
        this.location = location;
        this.ratePerDay = ratePerDay;
        this.costPerDay = costPerDay;
    }


    @Override
    public String getLocation() {
        return location;
    }

    @Override
    public void setLocation(String location) {
        this.location = location;
    }

    @Override
    public Integer getRatePerDay() {
        return ratePerDay;
    }

    @Override
    public void setRatePerDay(Integer ratePerDay) {
        this.ratePerDay = ratePerDay;
    }

    @Override
    public Integer getCostPerDay() {
        return costPerDay;
    }

    @Override
    public void setCostPerDay(Integer costPerDay) {
        this.costPerDay = costPerDay;
    }

    @Override
    public Integer getMinDistance() {
        return minDistance;
    }

    @Override
    public void setMinDistance(Integer distance) {
        this.minDistance = distance;
    }

    @Override
    public Facility getPrevious() {
        return previous;
    }

    @Override
    public void setPrevious(Facility facility) {
        this.previous = facility;
    }

    @Override
    public ArrayList<FacilityEdge> getNeighborList() {
        ArrayList<FacilityEdge> neighborCopy;
        neighborCopy = this.neighbors;
        return neighborCopy;
    }

    @Override
    public String getCity() {
        return getLocation().substring(0, getLocation().length()-4);
    }

    public void setInventory(Item item, Integer quantity) throws DataValidationException, NullParameterException {
        if (item == null || quantity == null) throw new NullParameterException();

        inventory.setInventoryItem(item, quantity);
    }

    @Override
    public void updateInventory(String itemID, Integer quantity) throws DataValidationException, NullParameterException {
        inventory.updateInventoryItem(itemID, quantity);
    }

    @Override
    public void addNeighbor(FacilityEdge facilityEdge) {
        neighbors.add(facilityEdge);
    }

    public void updateSchedule(Integer day, Integer value) throws NegativeQuantityException {
        schedule.setScheduleDay(day, value);
    }

    @Override
    public void printInventory() {
        inventory.printInventory();
    }

    @Override
    public void printNeighbors() {
        neighborPrinter.print(neighbors);
    }

    @Override
    public void printSchedule() {
        schedule.printOutput();
    }

    @Override
    public void printOutput() {
        System.out.println("---------------------------------------------------------------");
        System.out.println("");
        System.out.println(" " + location + "" );
        System.out.println("");
        System.out.println("---------------------");
        System.out.println("");
        System.out.println("Rate per day: " + ratePerDay);
        System.out.println("Cost per day: " + costPerDay);
        System.out.println("");

        // print direct links
        printNeighbors();
        System.out.println("");

        printInventory();
        System.out.println("");

        printSchedule();
    }

    @Override
    public int compareTo(Facility otherFacility) {
        return Integer.compare(getMinDistance(), otherFacility.getMinDistance());
    }

    @Override
    public boolean containsItem(Item item) {
        return this.inventory.containsItem(item);
    }

    @Override
    public Integer getProcessingDays(Integer quantityNeeded) throws NoAvailableDaysException, NullParameterException, DataValidationException, NegativeQuantityException {
        if (quantityNeeded <= 0) throw new DataValidationException("Negative or 0 Quantity");

        Integer itemsNeeded = quantityNeeded;
        Integer processDays = 1;

        while (itemsNeeded > 0) {
            Integer firstAvailable = this.schedule.getFirstAvailableDay();
            if (firstAvailable.equals(this.schedule.getLastScheduleDay())) {
                this.schedule.extendSchedule();
            }
            Integer itemsAvailable = this.schedule.getAvailableItems(firstAvailable);
            itemsNeeded -= itemsAvailable;
            processDays++;
        }
        return processDays;
    }

    @Override
    public void processInventory(Integer quantityNeeded) throws NegativeQuantityException, NoAvailableDaysException {
        if (quantityNeeded < 0)
            throw new NegativeQuantityException();

        Integer itemsNeeded = quantityNeeded;
        // Integer processDays = 1;

        if (itemsNeeded > 0 ) {
            while (itemsNeeded > 0) {
                Integer firstAvailable = this.schedule.getFirstAvailableDay();
                if (firstAvailable.equals(this.schedule.getLastScheduleDay())) {
                    this.schedule.extendSchedule();
                }
                Integer itemsAvailable = this.schedule.getAvailableItems(firstAvailable);
                itemsNeeded -= itemsAvailable;
                itemsAvailable = 0;
                this.schedule.setScheduleDay(firstAvailable, itemsAvailable);
                // processDays++;
            }
        }
    }

    @Override
    public Integer getItemQuantity(Item item) {
        return inventory.getItemQuantity(item);
    }

    @Override
    public Integer getAvailableScheduleItems(Integer day) throws NegativeQuantityException {
        return schedule.getAvailableItems(day);
    }

    public void addScheduleDay(Integer day, Integer itemsAvailable) {
        schedule.addScheduleDay(day, itemsAvailable);
    }

    @Override
    public Integer getNextAvailableDay(Integer startDay) throws NullParameterException, NegativeQuantityException {
        return this.schedule.getNextAvailableDay(startDay);
    }

    @Override
    public void processOrderSchedule(Integer arrivalDay, Integer totalItemsProcessed) throws NullParameterException, NegativeQuantityException {
        schedule.processOrderSchedule(arrivalDay, totalItemsProcessed);
    }
}
