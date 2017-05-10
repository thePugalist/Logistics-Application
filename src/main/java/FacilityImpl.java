package src.main.java;

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
    private double minDistance = Double.POSITIVE_INFINITY;
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
    public double getMinDistance() {
        return minDistance;
    }

    @Override
    public void setMinDistance(double distance) {
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

    public boolean inNeighbors(Facility facility, String neighborLocation) {
        return false;
    }

    public Integer getNeighborDistance(Facility facility, String neighborName) {

        return 0;
    }

    @Override
    public String getCity() {
        return getLocation().substring(0, getLocation().length()-4);
    }

    @Override
    public void updateInventory(Item item, Integer quantity) {
        inventory.updateInventoryItem(item, quantity);
    }

    @Override
    public void addNeighbor(FacilityEdge facilityEdge) {
        neighbors.add(facilityEdge);
    }

    public void updateSchedule(Integer day, Integer value) {
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
        return Double.compare(getMinDistance(), otherFacility.getMinDistance());
    }

    @Override
    public boolean containsItem(Item item) {
        return this.inventory.containsItem(item);
    }
}
