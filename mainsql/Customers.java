package mainsql;

import javafx.beans.property.SimpleStringProperty;
import javafx.scene.control.CheckBox;

/**
 * Created by rowitzou on 17-5-12.
 */
public final class Customers {
    private final SimpleStringProperty custName = new SimpleStringProperty("");
    private final SimpleStringProperty passwd = new SimpleStringProperty("");
    private final SimpleStringProperty status = new SimpleStringProperty("");
    private final CheckBox selected = new CheckBox();

    public Customers(){
    }

    public Customers(String custName, String passwd, String status) {
        setCustName(custName);
        setPasswd(passwd);
        setStatus(status);
        setSelected(false);
    }

    public String getCustName() {
        return custName.get();
    }

    public void setCustName(String custName) {
        this.custName.set(custName);
    }

    public String getPasswd() {
        return passwd.get();
    }

    public void setPasswd(String passwd) {
        this.passwd.set(passwd.toString());
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