package src.main.java.interfaces.impl;

import src.main.java.FacilityManager;
import src.main.java.exceptions.FacilityNotFoundException;
import src.main.java.exceptions.NegativeQuantityException;
import src.main.java.exceptions.NullParameterException;
import src.main.java.interfaces.LogisticsDetail;

/**
 * Created by Jordan on 5/13/2017.
 */
public class LogisticsDetailImpl implements LogisticsDetail {

    private String facilityLocation;
    private Integer totalQuantity;
    private Integer itemsProcessed;
    private Integer processingStart;
    private Integer processingEnd;
    private Integer travelStart;
    private Integer travelEnd;
    
    public String getFacilityLocation() {
        return facilityLocation;
    }

    public String getFullFacilityLocation() throws NullParameterException, FacilityNotFoundException {
        String fullFacilityLocation = FacilityManager.getInstance().getFacilityLocationFromCity(facilityLocation);
        return fullFacilityLocation;
    }

    public void setFacilityLocation(String facilityLocation) {
        if (facilityLocation.isEmpty() || facilityLocation.equals("")) try {
            throw new NullParameterException();
        } catch (NullParameterException e) {
            e.printStackTrace();
        }

        this.facilityLocation = facilityLocation;
    }

    public Integer getTotalQuantity() {
        return totalQuantity;
    }

    public void setTotalQuantity(Integer totalQuantity) {
        if (totalQuantity < 0) try {
            throw new NegativeQuantityException();
        } catch (NegativeQuantityException e) {
            e.printStackTrace();
        }

        this.totalQuantity = totalQuantity;
    }

    public Integer getItemsProcessed() {
        return itemsProcessed;
    }

    public void setItemsProcessed(Integer itemsProcessed) {
        if (itemsProcessed < 0) try {
            throw new NegativeQuantityException();
        } catch (NegativeQuantityException e) {
            e.printStackTrace();
        }

        this.itemsProcessed = itemsProcessed;
    }

    public Integer getProcessingStart() {
        return processingStart;
    }

    public void setProcessingStart(Integer processingStart) {
        if (processingStart < 0) try {
            throw new NegativeQuantityException();
        } catch (NegativeQuantityException e) {
            e.printStackTrace();
        }

        this.processingStart = processingStart;
    }

    public Integer getProcessingEnd() {
        return processingEnd;
    }

    public void setProcessingEnd(Integer processingEnd) {
        if (processingEnd < 0) try {
            throw new NegativeQuantityException();
        } catch (NegativeQuantityException e) {
            e.printStackTrace();
        }
        this.processingEnd = processingEnd;
    }

    public Integer getTravelStart() {
        return travelStart;
    }

    public void setTravelStart(Integer travelStart) {
        if (travelStart < 0) try {
            throw new NegativeQuantityException();
        } catch (NegativeQuantityException e) {
            e.printStackTrace();
        }

        this.travelStart = travelStart;
    }

    public Integer getTravelEnd() {
        return travelEnd;
    }

    public void setTravelEnd(Integer travelEnd) {
        if (travelEnd < 0) try {
            throw new NegativeQuantityException();
        } catch (NegativeQuantityException e) {
            e.printStackTrace();
        }

        this.travelEnd = travelEnd;
    }

    @Override
    public void print() {
        System.out.println("Name: " + facilityLocation + " (" + getItemsProcessed() + " of " + getTotalQuantity() + ")");
        System.out.println("\t\tProcessing Start: \tDay " + getProcessingStart());
        System.out.println("\t\tProcessing End: \tDay " + getProcessingEnd() );
        System.out.println("\t\tTravel Start: \tDay " + getTravelStart());
        System.out.println("\t\tTravel End: \tDay " + getTravelEnd());
        System.out.println("");
    }
}
