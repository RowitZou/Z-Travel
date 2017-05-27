package mainsql;

import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.scene.control.CheckBox;

/**
 * Created by rowitzou on 17-5-12.
 */
public final class Flights {
    private final SimpleStringProperty flightNum = new SimpleStringProperty("");
    private final SimpleStringProperty price = new SimpleStringProperty("");
    private final SimpleStringProperty numSeats = new SimpleStringProperty("");
    private final SimpleStringProperty numAvail = new SimpleStringProperty("");
    private final SimpleStringProperty fromCity = new SimpleStringProperty("");
    private final SimpleStringProperty arivCity = new SimpleStringProperty("");
    private final SimpleStringProperty status = new SimpleStringProperty("");
    private final CheckBox selected = new CheckBox();

    public Flights(){
    }

    public Flights(String flightNum, Integer price, Integer numSeats, Integer numAvail,
                   String fromCity, String arivCity, String status) {
        setFlightNum(flightNum);
        setPrice(price);
        setNumSeats(numSeats);
        setNumAvail(numAvail);
        setFromCity(fromCity);
        setArivCity(arivCity);
        setStatus(status);
        setSelected(false);
    }

    public String getFlightNum() {
        return flightNum.get();
    }

    public void setFlightNum(String flightNum) {
        this.flightNum.set(flightNum);
    }

    public String getPrice() {
        return price.get();
    }

    public void setPrice(Integer price) {
        this.price.set(price.toString());
    }

    public String getNumSeats() {
        return numSeats.get();
    }

    public void setNumSeats(Integer numSeats) {
        this.numSeats.set(numSeats.toString());
    }

    public String getNumAvail() {
        return numAvail.get();
    }

    public void setNumAvail(Integer numAvail) {
        this.numAvail.set(numAvail.toString());
    }

    public String getFromCity() {
        return fromCity.get();
    }

    public void setFromCity(String fromCity) {
        this.fromCity.set(fromCity);
    }

    public String getArivCity() {
        return arivCity.get();
    }

    public void setArivCity(String arivCity) {
        this.arivCity.set(arivCity);
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