package mainsql;

import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.scene.control.CheckBox;

/**
 * Created by rowitzou on 17-5-12.
 */
public final class Cars {
    private final SimpleStringProperty location = new SimpleStringProperty("");
    private final SimpleStringProperty price = new SimpleStringProperty("");
    private final SimpleStringProperty numCars = new SimpleStringProperty("");
    private final SimpleStringProperty numAvail = new SimpleStringProperty("");
    private final SimpleStringProperty status = new SimpleStringProperty("");
    private final CheckBox selected = new CheckBox();

    public Cars(){
    }

    public Cars(String location, Integer price, Integer numCars,
                  Integer numAvail, String status) {
        setLocation(location);
        setPrice(price);
        setNumCars(numCars);
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

    public String getNumCars() {
        return numCars.get();
    }

    public void setNumCars(Integer numCars) {
        this.numCars.set(numCars.toString());
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