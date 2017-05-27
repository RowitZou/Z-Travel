package mainsql;

import javafx.beans.property.SimpleStringProperty;
import javafx.scene.control.CheckBox;

/**
 * Created by rowitzou on 17-5-12.
 */
public final class Reservations {
    private final SimpleStringProperty custName = new SimpleStringProperty("");
    private final SimpleStringProperty resvType = new SimpleStringProperty("");
    private final SimpleStringProperty resvKey = new SimpleStringProperty("");
    private final SimpleStringProperty status = new SimpleStringProperty("");
    private final CheckBox selected = new CheckBox();

    public Reservations(){
    }

    public Reservations(String custName, Integer resvType, String resvKey, String status) {
        setCustName(custName);
        setResvType(resvType);
        setResvKey(resvKey);
        setStatus(status);
        setSelected(false);
    }

    public String getCustName() {
        return custName.get();
    }

    public void setCustName(String custName) {
        this.custName.set(custName);
    }

    public String getResvType() {
        return resvType.get();
    }

    public void setResvType(Integer resvType) {
        this.resvType.set(resvType.toString());
    }

    public String getResvKey() {
        return resvKey.get();
    }

    public void setResvKey(String resvKey) {
        this.resvKey.set(resvKey.toString());
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