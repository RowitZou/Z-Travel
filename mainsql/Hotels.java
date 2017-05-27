package mainsql;

import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.scene.control.CheckBox;

/**
 * Created by rowitzou on 17-5-12.
 */
public final class Hotels {
    private final SimpleStringProperty location = new SimpleStringProperty("");
    private final SimpleStringProperty price = new SimpleStringProperty("");
    private final SimpleStringProperty numRooms = new SimpleStringProperty("");
    private final SimpleStringProperty numAvail = new SimpleStringProperty("");
    private final SimpleStringProperty status = new SimpleStringProperty("");
    private final CheckBox selected = new CheckBox();

    public Hotels(){
    }

    public Hotels(String location, Integer price, Integer numRooms,
                  Integer numAvail, String status) {
        setLocation(location);
        setPrice(price);
        setNumRooms(numRooms);
        setNumAvail(numAvail);
        setStatus(status);
        setSelected(false);
    }

    public String getLocation() {
        return location.get();
    }

    public void setLocation(String location) {
        this.location.set(location);
    }

    public String getPrice() {
        return price.get();
    }

    public void setPrice(Integer price) {
        this.price.set(price.toString());
    }

    public String getNumRooms() {
        return numRooms.get();
    }

    public void setNumRooms(Integer numRooms) {
        this.numRooms.set(numRooms.toString());
    }

    public String getNumAvail() {
        return numAvail.get();
    }

    public void setNumAvail(Integer numAvail) {
        this.numAvail.set(numAvail.toString());
    }

    public String getStatus() {
        return status.get();
    }

    public void setStatus(String status) {
        this.status.set(status);
    }

    public CheckBox getSelected() {
        return selected;
    }

    public void setSelected(boolean selected) {
        this.selected.setSelected(selected);
    }

}