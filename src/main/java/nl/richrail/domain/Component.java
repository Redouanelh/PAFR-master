package nl.richrail.domain;

public interface Component {

    public boolean hasId(String id);
    public String getId();
    public Integer getSeats();
    public Integer getWeight();

}