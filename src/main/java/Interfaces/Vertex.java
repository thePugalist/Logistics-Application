package src.main.java.interfaces;

import src.main.java.shortestpath.FacilityEdge;

import java.util.ArrayList;

/**
 * Created by Jordan on 5/2/2017.
 */
public interface Vertex {

    public String getName();

    public void setName(String string);

    public double getMinDistance();

    public void setMinDistance(double distance);

    public Vertex getPrevious();

    public void setPrevious(Vertex vertex);

    public void addNeighbor(FacilityEdge facilityEdge);

    public ArrayList<FacilityEdge> getCopyOfNeighborsList();

    public String toString();
}
