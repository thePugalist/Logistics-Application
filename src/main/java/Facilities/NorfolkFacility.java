package src.main.java.Facilities;

import src.main.java.Interfaces.Facility;

/**
 * Created by Jordan on 4/14/2017.
 */
public class NorfolkFacility implements Facility {

    private String location = "Norfolk, VA";
    private long ratePerDay = 10;
    private long costPerDay = 300;

    @Override
    public String getLocation() {
        return location;
    }

    @Override
    public void setLocation(String location) {

    }

    @Override
    public Long getRatePerDay() {
        return null;
    }

    @Override
    public void setRatePerDay(Integer ratePerDay) {

    }


    @Override
    public Long getCostPerDay() {
        return null;
    }

    @Override
    public void setCostPerDay(Integer ratePerday) {

    }

    @Override
    public void printActiveInventory() {

    }

    @Override
    public void printDepletedInventory() {

    }

    @Override
    public void printOutput() {

    }
}
