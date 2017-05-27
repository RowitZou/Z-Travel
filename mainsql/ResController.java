package mainsql;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.layout.*;

import java.io.File;
import java.io.PrintStream;
import java.sql.*;

/**
 * Created by rowitzou on 17-3-28.
 */
public class ResController {
    private Connection conn;
    private String custname;

    @FXML
    private Tab updateTab;
    @FXML
    private TableView reservationTable, updateTable;
    @FXML
    private ComboBox reservationType, updateType;
    @FXML
    private ComboBox<String> reservationScreen;
    @FXML
    private ComboBox<String> updateScreen;
    @FXML
    private TextField reservationText, updateText;
    @FXML
    private Button reservationButton, reservationCancelButton, deleteButton, insertButton;
    @FXML
    private TextField insertText1, insertText2, insertText3, insertText4, insertText5;
    @FXML
    private Label insertLabel1, insertLabel2, insertLabel3, insertLabel4, insertLabel5;
    @FXML
    private Label pathLabel;
    @FXML
    private TextField pathText;
    @FXML
    private Pane pathPane;

    private TableView tableView;
    private ComboBox typeComboBox;
    private ComboBox<String> screenComboBox;
    private TextField textField;

    void setConnection(Connection conn, String custname) throws Exception {
        this.conn = conn;
        this.custname = custname;
        if (!custname.equals("admin")) {
            updateTab.setDisable(true);
            pathLabel.setVisible(false);
            pathText.setVisible(false);
        }
    }

    @FXML
    protected void showReservationScreen() {
        textField = reservationText;
        typeComboBox = reservationType;
        screenComboBox = reservationScreen;
        reservationButton.setDisable(true);
        reservationCancelButton.setDisable(true);
        showScreen();
    }

    @FXML
    protected void showUpdateScreen() {
        textField = updateText;
        typeComboBox = updateType;
        screenComboBox = updateScreen;
        deleteButton.setDisable(true);
        insertButton.setDisable(true);
        showScreen();
        setInsertInformation();
    }

    private void setInsertInformation() {
        if (updateType.getValue() == null) {
            insertLabel1.setVisible(false);
            insertLabel2.setVisible(false);
            insertLabel3.setVisible(false);
            insertLabel4.setVisible(false);
            insertLabel5.setVisible(false);
            insertText1.setVisible(false);
            insertText2.setVisible(false);
            insertText3.setVisible(false);
            insertText4.setVisible(false);
            insertText5.setVisible(false);
            insertButton.setDisable(true);
        }
        if (updateType.getValue().equals("航班")) {
            insertLabel1.setVisible(true);
            insertLabel2.setVisible(true);
            insertLabel3.setVisible(true);
            insertLabel4.setVisible(true);
            insertLabel5.setVisible(true);
            insertText1.setVisible(true);
            insertText2.setVisible(true);
            insertText3.setVisible(true);
            insertText4.setVisible(true);
            insertText5.setVisible(true);
            insertText1.setText("");
            insertText2.setText("");
            insertText3.setText("");
            insertText4.setText("");
            insertText5.setText("");
            insertText1.setPromptText("US0001");
            insertText2.setPromptText("100");
            insertText3.setPromptText("50");
            insertText4.setPromptText("New York");
            insertText5.setPromptText("Shanghai");
            insertButton.setDisable(false);
            insertLabel1.setText("航班号");
            insertLabel2.setText("价格");
            insertLabel3.setText("座位数");
            insertLabel4.setText("起点");
            insertLabel5.setText("终点");
        } else if (updateType.getValue().equals("酒店")) {
            insertLabel1.setVisible(true);
            insertLabel2.setVisible(true);
            insertLabel3.setVisible(true);
            insertLabel4.setVisible(false);
            insertLabel5.setVisible(false);
            insertText1.setVisible(true);
            insertText2.setVisible(true);
            insertText3.setVisible(true);
            insertText4.setVisible(false);
            insertText5.setVisible(false);
            insertButton.setDisable(false);
            insertText1.setText("");
            insertText2.setText("");
            insertText3.setText("");
            insertText4.setText("");
            insertText5.setText("");
            insertText1.setPromptText("Shanghai");
            insertText2.setPromptText("200");
            insertText3.setPromptText("100");
            insertLabel1.setText("地区");
            insertLabel2.setText("价格");
            insertLabel3.setText("房间数");
        } else if (updateType.getValue().equals("出租车")) {
            insertLabel1.setVisible(true);
            insertLabel2.setVisible(true);
            insertLabel3.setVisible(true);
            insertLabel4.setVisible(false);
            insertLabel5.setVisible(false);
            insertText1.setVisible(true);
            insertText2.setVisible(true);
            insertText3.setVisible(true);
            insertText4.setVisible(false);
            insertText5.setVisible(false);
            insertButton.setDisable(false);
            insertText1.setText("");
            insertText2.setText("");
            insertText3.setText("");
            insertText4.setText("");
            insertText5.setText("");
            insertText1.setPromptText("Shanghai");
            insertText2.setPromptText("12");
            insertText3.setPromptText("500");
            insertLabel1.setText("地区");
            insertLabel2.setText("价格");
            insertLabel3.setText("车辆数");
        } else if (updateType.getValue().equals("用户")) {
            insertLabel1.setVisible(false);
            insertLabel2.setVisible(false);
            insertLabel3.setVisible(false);
            insertLabel4.setVisible(true);
            insertLabel5.setVisible(true);
            insertText1.setVisible(false);
            insertText2.setVisible(false);
            insertText3.setVisible(false);
            insertText4.setVisible(true);
            insertText5.setVisible(true);
            insertButton.setDisable(false);
            insertText1.setText("");
            insertText2.setText("");
            insertText3.setText("");
            insertText4.setText("");
            insertText5.setText("");
            insertText4.setPromptText("user");
            insertText5.setPromptText("****");
            insertLabel4.setText("用户名");
            insertLabel5.setText("密码");
        }
    }

    private void showScreen() {
        textField.setText("");
        if (typeComboBox.getValue() == null)
            screenComboBox.getItems().clear();
        else if (typeComboBox.getValue().equals("航班")) {
            screenComboBox.getItems().clear();
            screenComboBox.getItems().addAll(
                    "全部",
                    "航班号",
                    "价格",
                    "座位数",
                    "剩余座位数",
                    "起点",
                    "终点"
            );
        } else if (typeComboBox.getValue().equals("酒店")) {
            screenComboBox.getItems().clear();
            screenComboBox.getItems().addAll(
                    "全部",
                    "地区",
                    "价格",
                    "房间数",
                    "剩余房间数"
            );
        } else if (typeComboBox.getValue().equals("出租车")) {
            screenComboBox.getItems().clear();
            screenComboBox.getItems().addAll(
                    "全部",
                    "地区",
                    "价格",
                    "车辆数",
                    "剩余车辆数"
            );
        } else if (typeComboBox.getValue().equals("用户")) {
            screenComboBox.getItems().clear();
            screenComboBox.getItems().addAll(
                    "全部",
                    "用户名",
                    "密码"
            );
        } else if (typeComboBox.getValue().equals("预订信息")) {
            screenComboBox.getItems().clear();
            screenComboBox.getItems().addAll(
                    "全部",
                    "航班",
                    "酒店",
                    "出租车"
            );
            if (custname.equals("admin")) {
                screenComboBox.getItems().addAll("用户名");
            }
        } else {
            screenComboBox.getItems().clear();
        }
    }

    @FXML
    protected void setReservationText() {
        textField = reservationText;
        typeComboBox = reservationType;
        screenComboBox = reservationScreen;
        setText();
    }

    @FXML
    protected void setUpdateText() {
        textField = updateText;
        typeComboBox = updateType;
        screenComboBox = updateScreen;
        setText();
    }

    private void setText() {
        textField.setText("");
        if (screenComboBox.getValue() == null || screenComboBox.getValue().equals("全部"))
            textField.setEditable(false);
        else
            textField.setEditable(true);
    }

    @FXML
    protected void searchReservationInformation() throws Exception {
        textField = reservationText;
        typeComboBox = reservationType;
        screenComboBox = reservationScreen;
        tableView = reservationTable;
        if (typeComboBox.getValue() != null && !typeComboBox.getValue().equals("预订信息"))
            reservationButton.setDisable(false);
        reservationCancelButton.setDisable(false);
        searchInformation();
    }

    @FXML
    protected void searchUpdateInformation() throws Exception {
        textField = updateText;
        typeComboBox = updateType;
        screenComboBox = updateScreen;
        tableView = updateTable;
        deleteButton.setDisable(false);
        insertButton.setDisable(false);
        searchInformation();
    }

    private void searchInformation() throws Exception {
        if (typeComboBox.getValue() == null)
            return;
        if (typeComboBox.getValue().equals("航班"))
            searchFlights();
        else if (typeComboBox.getValue().equals("酒店"))
            searchHotels();
        else if (typeComboBox.getValue().equals("出租车"))
            searchCars();
        else if (typeComboBox.getValue().equals("预订信息"))
            searchReservation();
        else if (typeComboBox.getValue().equals("用户"))
            searchCustomers();
        else
            searchEmpty();
    }

    private void searchFlights() throws Exception {
        String sqlCommand;
        Statement stmt = conn.createStatement();

        if (screenComboBox.getValue() == null || screenComboBox.getValue().equals("全部")
                || textField.getText().equals(""))
            sqlCommand = "select * from FLIGHTS";
        else if (screenComboBox.getValue().equals("航班号")) {
            sqlCommand = "select * from FLIGHTS where flightNum='" + textField.getText() + "'";
        } else if (screenComboBox.getValue().equals("价格")) {
            sqlCommand = "select * from FLIGHTS where price='" + textField.getText() + "'";
        } else if (screenComboBox.getValue().equals("座位数")) {
            sqlCommand = "select * from FLIGHTS where numSeats='" + textField.getText() + "'";
        } else if (screenComboBox.getValue().equals("剩余座位数")) {
            sqlCommand = "select * from FLIGHTS where numAvail='" + textField.getText() + "'";
        } else if (screenComboBox.getValue().equals("起点")) {
            sqlCommand = "select * from FLIGHTS where FromCity='" + textField.getText() + "'";
        } else if (screenComboBox.getValue().equals("终点")) {
            sqlCommand = "select * from FLIGHTS where ArivCity='" + textField.getText() + "'";
        } else {
            sqlCommand = "select * from FLIGHTS";
        }
        ResultSet rs = stmt.executeQuery(sqlCommand);

        ObservableList observableList = tableView.getColumns();
        observableList.clear();
        TableColumn tableColumn;
        tableColumn = new TableColumn("航班号");
        tableColumn.setPrefWidth(80.0);
        tableColumn.setEditable(false);
        tableColumn.setCellValueFactory(new PropertyValueFactory("flightNum"));
        tableColumn.setResizable(false);
        observableList.add(tableColumn);
        tableColumn = new TableColumn("价格");
        tableColumn.setPrefWidth(80.0);
        tableColumn.setResizable(false);
        tableColumn.setEditable(false);
        tableColumn.setCellValueFactory(new PropertyValueFactory("price"));
        observableList.add(tableColumn);
        tableColumn = new TableColumn("座位数");
        tableColumn.setResizable(false);
        tableColumn.setPrefWidth(80.0);
        tableColumn.setEditable(false);
        tableColumn.setCellValueFactory(new PropertyValueFactory("numSeats"));
        observableList.add(tableColumn);
        tableColumn = new TableColumn("剩余座位数");
        tableColumn.setPrefWidth(80.0);
        tableColumn.setCellValueFactory(new PropertyValueFactory("numAvail"));
        tableColumn.setResizable(false);
        tableColumn.setEditable(false);
        observableList.add(tableColumn);
        tableColumn = new TableColumn("起点");
        tableColumn.setPrefWidth(80.0);
        tableColumn.setResizable(false);
        tableColumn.setEditable(false);
        tableColumn.setCellValueFactory(new PropertyValueFactory("fromCity"));
        observableList.add(tableColumn);
        tableColumn = new TableColumn("终点");
        tableColumn.setPrefWidth(80.0);
        tableColumn.setCellValueFactory(new PropertyValueFactory("arivCity"));
        tableColumn.setResizable(false);
        tableColumn.setEditable(false);
        observableList.add(tableColumn);
        tableColumn = new TableColumn("状态");
        tableColumn.setPrefWidth(80.0);
        tableColumn.setCellValueFactory(new PropertyValueFactory("status"));
        tableColumn.setResizable(false);
        tableColumn.setEditable(false);
        observableList.add(tableColumn);
        tableColumn = new TableColumn("选择");
        tableColumn.setResizable(false);
        tableColumn.setPrefWidth(85.0);
        tableColumn.setEditable(true);
        tableColumn.setCellValueFactory(new PropertyValueFactory("selected"));
        observableList.add(tableColumn);

        ObservableList<Flights> data = tableView.getItems();
        data.clear();
        String status = "正常";
        if (tableView == reservationTable) {
            while (rs.next()) {
                Statement stmtTemp = conn.createStatement();
                ResultSet resultSet = stmtTemp.executeQuery("select count(resvKey) from RESERVATIONS where custName='"
                        + custname + "' and resvType=1 and resvKeyName='" + rs.getString(1) + "'");
                if (resultSet.next()) {
                    if (resultSet.getInt(1) <= 0 && rs.getInt(4) <= 0)
                        status = "已售罄";
                    else
                        status = "已预订" + resultSet.getInt(1);
                }
                data.add(new Flights(rs.getString(1), rs.getInt(2), rs.getInt(3),
                        rs.getInt(4), rs.getString(5), rs.getString(6), status));
            }
        } else {
            while (rs.next()) {
                data.add(new Flights(rs.getString(1), rs.getInt(2), rs.getInt(3),
                        rs.getInt(4), rs.getString(5), rs.getString(6), status));
            }
        }
        tableView.setItems(data);
    }

    private void searchHotels() throws Exception {
        String sqlCommand;
        Statement stmt = conn.createStatement();
        if (screenComboBox.getValue() == null || screenComboBox.getValue().equals("全部")
                || textField.getText().equals(""))
            sqlCommand = "select * from HOTELS";
        else if (screenComboBox.getValue().equals("地区")) {
            sqlCommand = "select * from HOTELS where location='" + textField.getText() + "'";
        } else if (screenComboBox.getValue().equals("价格")) {
            sqlCommand = "select * from HOTELS where price='" + textField.getText() + "'";
        } else if (screenComboBox.getValue().equals("房间数")) {
            sqlCommand = "select * from HOTELS where numRooms='" + textField.getText() + "'";
        } else if (screenComboBox.getValue().equals("剩余房间数")) {
            sqlCommand = "select * from HOTELS where numAvail='" + textField.getText() + "'";
        } else {
            sqlCommand = "select * from HOTELS";
        }
        ResultSet rs = stmt.executeQuery(sqlCommand);

        ObservableList<TableColumn> observableList = tableView.getColumns();
        observableList.clear();
        TableColumn tableColumn;
        tableColumn = new TableColumn("地区");
        tableColumn.setPrefWidth(105.0);
        tableColumn.setEditable(false);
        tableColumn.setCellValueFactory(new PropertyValueFactory("location"));
        tableColumn.setResizable(false);
        observableList.add(tableColumn);
        tableColumn = new TableColumn("价格");
        tableColumn.setPrefWidth(105.0);
        tableColumn.setResizable(false);
        tableColumn.setEditable(false);
        tableColumn.setCellValueFactory(new PropertyValueFactory("price"));
        observableList.add(tableColumn);
        tableColumn = new TableColumn("房间数");
        tableColumn.setResizable(false);
        tableColumn.setPrefWidth(105.0);
        tableColumn.setEditable(false);
        tableColumn.setCellValueFactory(new PropertyValueFactory("numRooms"));
        observableList.add(tableColumn);
        tableColumn = new TableColumn("剩余房间数");
        tableColumn.setPrefWidth(105.0);
        tableColumn.setCellValueFactory(new PropertyValueFactory("numAvail"));
        tableColumn.setResizable(false);
        tableColumn.setEditable(false);
        observableList.add(tableColumn);
        tableColumn = new TableColumn("状态");
        tableColumn.setPrefWidth(105.0);
        tableColumn.setCellValueFactory(new PropertyValueFactory("status"));
        tableColumn.setResizable(false);
        tableColumn.setEditable(false);
        observableList.add(tableColumn);
        tableColumn = new TableColumn("选择");
        tableColumn.setResizable(false);
        tableColumn.setPrefWidth(105.0);
        tableColumn.setEditable(true);
        tableColumn.setCellValueFactory(new PropertyValueFactory("selected"));
        observableList.add(tableColumn);

        ObservableList<Hotels> data = tableView.getItems();
        data.clear();
        String status = "正常";
        if (tableView == reservationTable) {
            while (rs.next()) {
                Statement stmtTemp = conn.createStatement();
                ResultSet resultSet = stmtTemp.executeQuery("select count(resvKey) from RESERVATIONS where custName='"
                        + custname + "' and resvType=2 and resvKeyName='" + rs.getString(1) + "'");
                if (resultSet.next()) {
                    if (resultSet.getInt(1) <= 0 && rs.getInt(4) <= 0)
                        status = "已售罄";
                    else
                        status = "已预订" + resultSet.getInt(1);
                }
                data.add(new Hotels(rs.getString(1), rs.getInt(2), rs.getInt(3),
                        rs.getInt(4), status));
            }
        } else {
            while (rs.next()) {
                data.add(new Hotels(rs.getString(1), rs.getInt(2), rs.getInt(3),
                        rs.getInt(4), status));
            }
        }
        tableView.setItems(data);
    }

    private void searchCars() throws Exception {
        String sqlCommand;
        Statement stmt = conn.createStatement();
        if (screenComboBox.getValue() == null || screenComboBox.getValue().equals("全部")
                || textField.getText().equals(""))
            sqlCommand = "select * from CARS";
        else if (screenComboBox.getValue().equals("地区")) {
            sqlCommand = "select * from CARS where location='" + textField.getText() + "'";
        } else if (screenComboBox.getValue().equals("价格")) {
            sqlCommand = "select * from CARS where price='" + textField.getText() + "'";
        } else if (screenComboBox.getValue().equals("车辆数")) {
            sqlCommand = "select * from CARS where numCars='" + textField.getText() + "'";
        } else if (screenComboBox.getValue().equals("剩余车辆数")) {
            sqlCommand = "select * from CARS where numAvail='" + textField.getText() + "'";
        } else {
            sqlCommand = "select * from CARS";
        }
        ResultSet rs = stmt.executeQuery(sqlCommand);

        ObservableList observableList = tableView.getColumns();
        observableList.clear();
        TableColumn tableColumn;
        tableColumn = new TableColumn("地区");
        tableColumn.setPrefWidth(105.0);
        tableColumn.setEditable(false);
        tableColumn.setCellValueFactory(new PropertyValueFactory("location"));
        tableColumn.setResizable(false);
        observableList.add(tableColumn);
        tableColumn = new TableColumn("价格");
        tableColumn.setPrefWidth(105.0);
        tableColumn.setResizable(false);
        tableColumn.setEditable(false);
        tableColumn.setCellValueFactory(new PropertyValueFactory("price"));
        observableList.add(tableColumn);
        tableColumn = new TableColumn("车辆数");
        tableColumn.setResizable(false);
        tableColumn.setPrefWidth(105.0);
        tableColumn.setEditable(false);
        tableColumn.setCellValueFactory(new PropertyValueFactory("numCars"));
        observableList.add(tableColumn);
        tableColumn = new TableColumn("剩余车辆数");
        tableColumn.setPrefWidth(105.0);
        tableColumn.setCellValueFactory(new PropertyValueFactory("numAvail"));
        tableColumn.setResizable(false);
        tableColumn.setEditable(false);
        observableList.add(tableColumn);
        tableColumn = new TableColumn("状态");
        tableColumn.setPrefWidth(105.0);
        tableColumn.setCellValueFactory(new PropertyValueFactory("status"));
        tableColumn.setResizable(false);
        tableColumn.setEditable(false);
        observableList.add(tableColumn);
        tableColumn = new TableColumn("选择");
        tableColumn.setResizable(false);
        tableColumn.setPrefWidth(105.0);
        tableColumn.setEditable(true);
        tableColumn.setCellValueFactory(new PropertyValueFactory("selected"));
        observableList.add(tableColumn);

        ObservableList data = tableView.getItems();
        data.clear();
        String status = "正常";
        if (tableView == reservationTable) {
            while (rs.next()) {
                Statement stmtTemp = conn.createStatement();
                ResultSet resultSet = stmtTemp.executeQuery("select count(resvKey) from RESERVATIONS where custName='"
                        + custname + "' and resvType=3 and resvKeyName='" + rs.getString(1) + "'");
                if (resultSet.next()) {
                    if (resultSet.getInt(1) <= 0 && rs.getInt(4) <= 0)
                        status = "已售罄";
                    else
                        status = "已预订" + resultSet.getInt(1);
                }
                data.add(new Cars(rs.getString(1), rs.getInt(2), rs.getInt(3),
                        rs.getInt(4), status));
            }
        } else {
            while (rs.next()) {
                data.add(new Cars(rs.getString(1), rs.getInt(2), rs.getInt(3),
                        rs.getInt(4), status));
            }
        }
        tableView.setItems(data);
    }

    private void searchReservation() throws Exception {
        String sqlCommand;
        Statement stmt = conn.createStatement();
        if (custname.equals("admin")) {
            if (screenComboBox.getValue() == null || screenComboBox.getValue().equals("全部"))
                sqlCommand = "select * from RESERVATIONS";
            else if (screenComboBox.getValue().equals("用户名")) {
                if (textField.getText().equals(""))
                    sqlCommand = "select * from RESERVATIONS";
                else
                    sqlCommand = "select * from RESERVATIONS where custName='" + textField.getText() + "'";
            } else if (screenComboBox.getValue().equals("航班")) {
                if (textField.getText().equals(""))
                    sqlCommand = "select * from RESERVATIONS where resvType=1";
                else
                    sqlCommand = "select * from RESERVATIONS where resvType=1 and resvKeyName='" + textField.getText() + "'";
            } else if (screenComboBox.getValue().equals("酒店")) {
                if (textField.getText().equals(""))
                    sqlCommand = "select * from RESERVATIONS where resvType=2";
                else
                    sqlCommand = "select * from RESERVATIONS where resvType=2 and resvKeyName='" + textField.getText() + "'";
            } else if (screenComboBox.getValue().equals("出租车")) {
                if (textField.getText().equals(""))
                    sqlCommand = "select * from RESERVATIONS where resvType=3";
                else
                    sqlCommand = "select * from RESERVATIONS where resvType=3 and resvKeyName='" + textField.getText() + "'";
            } else {
                sqlCommand = "select * from RESERVATIONS";
            }
        } else {
            if (screenComboBox.getValue() == null || screenComboBox.getValue().equals("全部"))
                sqlCommand = "select * from RESERVATIONS where custName='" + custname + "'";
            else if (screenComboBox.getValue().equals("航班")) {
                if (textField.getText().equals(""))
                    sqlCommand = "select * from RESERVATIONS where resvType=1 and custName='" + custname + "'";
                else
                    sqlCommand = "select * from RESERVATIONS where resvType=1 and custName='" + custname + "' and resvKeyName='" + textField.getText() + "'";
            } else if (screenComboBox.getValue().equals("酒店")) {
                if (textField.getText().equals(""))
                    sqlCommand = "select * from RESERVATIONS where resvType=2 and custName='" + custname + "'";
                else
                    sqlCommand = "select * from RESERVATIONS where resvType=2 and custName='" + custname + "' and resvKeyName='" + textField.getText() + "'";
            } else if (screenComboBox.getValue().equals("出租车")) {
                if (textField.getText().equals(""))
                    sqlCommand = "select * from RESERVATIONS where resvType=3 and custName='" + custname + "'";
                else
                    sqlCommand = "select * from RESERVATIONS where resvType=3 and custName='" + custname + "' and resvKeyName='" + textField.getText() + "'";
            } else {
                sqlCommand = "select * from RESERVATIONS where custName='" + custname + "'";
            }
        }
        ResultSet rs = stmt.executeQuery(sqlCommand);

        ObservableList observableList = tableView.getColumns();
        observableList.clear();
        TableColumn tableColumn;
        tableColumn = new TableColumn("用户");
        tableColumn.setPrefWidth(120.0);
        tableColumn.setEditable(false);
        tableColumn.setCellValueFactory(new PropertyValueFactory("custName"));
        tableColumn.setResizable(false);
        observableList.add(tableColumn);
        tableColumn = new TableColumn("预定类型");
        tableColumn.setPrefWidth(120.0);
        tableColumn.setResizable(false);
        tableColumn.setEditable(false);
        tableColumn.setCellValueFactory(new PropertyValueFactory("resvType"));
        observableList.add(tableColumn);
        tableColumn = new TableColumn("订单编号");
        tableColumn.setResizable(false);
        tableColumn.setPrefWidth(150.0);
        tableColumn.setEditable(false);
        tableColumn.setCellValueFactory(new PropertyValueFactory("resvKey"));
        observableList.add(tableColumn);
        tableColumn = new TableColumn("状态");
        tableColumn.setPrefWidth(120.0);
        tableColumn.setCellValueFactory(new PropertyValueFactory("status"));
        tableColumn.setResizable(false);
        tableColumn.setEditable(false);
        observableList.add(tableColumn);
        tableColumn = new TableColumn("选择");
        tableColumn.setResizable(false);
        tableColumn.setPrefWidth(120.0);
        tableColumn.setEditable(true);
        tableColumn.setCellValueFactory(new PropertyValueFactory("selected"));
        observableList.add(tableColumn);

        ObservableList<Reservations> data = tableView.getItems();
        data.clear();

        while (rs.next()) {
            data.add(new Reservations(rs.getString(1), rs.getInt(2), rs.getString(3), "正常"));
        }
        tableView.setItems(data);
    }

    private void searchCustomers() throws Exception {
        String sqlCommand;
        Statement stmt = conn.createStatement();
        if (screenComboBox.getValue() == null || screenComboBox.getValue().equals("全部")
                || textField.getText().equals(""))
            sqlCommand = "select * from CUSTOMERS";
        else if (screenComboBox.getValue().equals("用户名")) {
            sqlCommand = "select * from CUSTOMERS where custName='" + textField.getText() + "'";
        } else if (screenComboBox.getValue().equals("密码")) {
            sqlCommand = "select * from CUSTOMERS where passwd='" + textField.getText() + "'";
        } else {
            sqlCommand = "select * from CUSTOMERS";
        }
        ResultSet rs = stmt.executeQuery(sqlCommand);

        ObservableList<TableColumn> observableList = tableView.getColumns();
        observableList.clear();
        TableColumn tableColumn;
        tableColumn = new TableColumn("用户名");
        tableColumn.setPrefWidth(155.0);
        tableColumn.setEditable(false);
        tableColumn.setCellValueFactory(new PropertyValueFactory("custName"));
        tableColumn.setResizable(false);
        observableList.add(tableColumn);
        tableColumn = new TableColumn("密码");
        tableColumn.setPrefWidth(155.0);
        tableColumn.setResizable(false);
        tableColumn.setEditable(false);
        tableColumn.setCellValueFactory(new PropertyValueFactory("passwd"));
        observableList.add(tableColumn);
        tableColumn = new TableColumn("状态");
        tableColumn.setPrefWidth(155.0);
        tableColumn.setCellValueFactory(new PropertyValueFactory("status"));
        tableColumn.setResizable(false);
        tableColumn.setEditable(false);
        observableList.add(tableColumn);
        tableColumn = new TableColumn("选择");
        tableColumn.setResizable(false);
        tableColumn.setPrefWidth(155.0);
        tableColumn.setEditable(true);
        tableColumn.setCellValueFactory(new PropertyValueFactory("selected"));
        observableList.add(tableColumn);

        ObservableList<Customers> data = tableView.getItems();
        data.clear();
        while (rs.next()) {
            data.add(new Customers(rs.getString(1), rs.getString(2), "正常"));
        }
        tableView.setItems(data);
    }

    private void searchEmpty() throws Exception {
        ObservableList observableList = tableView.getColumns();
        observableList.clear();
    }

    @FXML
    protected void reservation() throws Exception {
        reservationButton.setDisable(true);
        reservationCancelButton.setDisable(true);
        Statement stmt = conn.createStatement();
        ResultSet rs;
        if (reservationType.getValue().equals("航班")) {
            ObservableList<Flights> data = tableView.getItems();
            if (data == null) return;
            for (Flights flight : data) {
                if (flight.getSelected().isSelected()) {
                    rs = stmt.executeQuery("select numAvail from FLIGHTS where flightNum='" + flight.getFlightNum() + "'");
                    if (rs.next() && rs.getInt(1) <= 0) {
                        continue;
                    }
                    stmt.execute("update FLIGHTS set numAvail=numAvail-1 where flightNum='" + flight.getFlightNum() + "'");
                    rs = stmt.executeQuery("select max(resvKeyNum) from RESERVATIONS where custName='"
                            + custname + "' and resvType=1 and resvKeyName='" + flight.getFlightNum() + "'");
                    int rnum;
                    if (!rs.next()) {
                        rnum = 1;
                    } else {
                        rnum = rs.getInt(1) + 1;
                    }
                    stmt.execute("insert into RESERVATIONS values('" + custname + "', 1, '" + custname + 1 + flight.getFlightNum() + rnum + "', '" + flight.getFlightNum() + "', " + rnum + ")");
                    rs = stmt.executeQuery("select count(resvKey) from RESERVATIONS where custName='"
                            + custname + "' and resvType=1 and resvKeyName='" + flight.getFlightNum() + "'");
                    if (!rs.next()) {
                        rnum = 0;
                    } else {
                        rnum = rs.getInt(1);
                    }
                    flight.setStatus("已预订" + rnum);
                    flight.setNumAvail(Integer.parseInt(flight.getNumAvail()) - 1);
                }
            }
            tableView.setItems(data);
            tableView.refresh();
        } else if (reservationType.getValue().equals("酒店")) {
            ObservableList<Hotels> data = tableView.getItems();
            if (data == null) return;
            for (Hotels hotel : data) {
                if (hotel.getSelected().isSelected()) {
                    rs = stmt.executeQuery("select numAvail from HOTELS where location='" + hotel.getLocation() + "'");
                    if (rs.next() && rs.getInt(1) <= 0) {
                        continue;
                    }
                    stmt.execute("update HOTELS set numAvail=numAvail-1 where location='" + hotel.getLocation() + "'");
                    rs = stmt.executeQuery("select max(resvKeyNum) from RESERVATIONS where custName='"
                            + custname + "' and resvType=2 and resvKeyName='" + hotel.getLocation() + "'");
                    int rnum;
                    if (!rs.next()) {
                        rnum = 1;
                    } else {
                        rnum = rs.getInt(1) + 1;
                    }
                    stmt.execute("insert into RESERVATIONS values('" + custname + "', 2, '" + custname + 2 + hotel.getLocation() + rnum + "', '" + hotel.getLocation() + "', " + rnum + ")");
                    rs = stmt.executeQuery("select count(resvKey) from RESERVATIONS where custName='"
                            + custname + "' and resvType=2 and resvKeyName='" + hotel.getLocation() + "'");
                    if (!rs.next()) {
                        rnum = 0;
                    } else {
                        rnum = rs.getInt(1);
                    }
                    hotel.setStatus("已预订" + rnum);
                    hotel.setNumAvail(Integer.parseInt(hotel.getNumAvail()) - 1);
                }
            }
            tableView.setItems(data);
            tableView.refresh();
        } else if (reservationType.getValue().equals("出租车")) {
            ObservableList<Cars> data = tableView.getItems();
            if (data == null) return;
            for (Cars car : data) {
                if (car.getSelected().isSelected()) {
                    rs = stmt.executeQuery("select numAvail from CARS where location='" + car.getLocation() + "'");
                    if (rs.next() && rs.getInt(1) <= 0) {
                        continue;
                    }
                    stmt.execute("update CARS set numAvail=numAvail-1 where location='" + car.getLocation() + "'");
                    rs = stmt.executeQuery("select max(resvKeyNum) from RESERVATIONS where custName='"
                            + custname + "' and resvType=3 and resvKeyName='" + car.getLocation() + "'");
                    int rnum;
                    if (!rs.next()) {
                        rnum = 1;
                    } else {
                        rnum = rs.getInt(1) + 1;
                    }
                    stmt.execute("insert into RESERVATIONS values('" + custname + "', 3, '" + custname + 3 + car.getLocation() + rnum + "', '" + car.getLocation() + "', " + rnum + ")");
                    rs = stmt.executeQuery("select count(resvKey) from RESERVATIONS where custName='"
                            + custname + "' and resvType=3 and resvKeyName='" + car.getLocation() + "'");
                    if (!rs.next()) {
                        rnum = 0;
                    } else {
                        rnum = rs.getInt(1);
                    }
                    car.setStatus("已预订" + rnum);
                    car.setNumAvail(Integer.parseInt(car.getNumAvail()) - 1);
                }
            }
            tableView.setItems(data);
            tableView.refresh();
        }
        reservationButton.setDisable(false);
        reservationCancelButton.setDisable(false);
    }

    @FXML
    protected void cancelReservation() throws Exception {
        reservationButton.setDisable(true);
        reservationCancelButton.setDisable(true);
        Statement stmt = conn.createStatement();
        ResultSet rs;
        if(reservationType.getValue() == null) return ;
        if (reservationType.getValue().equals("航班")) {
            ObservableList<Flights> data = tableView.getItems();
            if (data == null) return;
            for (Flights flight : data) {
                if (flight.getSelected().isSelected()) {
                    rs = stmt.executeQuery("select max(resvKeyNum) from RESERVATIONS where custName='"
                            + custname + "' and resvType=1 and resvKeyName='" + flight.getFlightNum() + "'");
                    int rnum = 0;
                    if (rs.next()) {
                        rnum = rs.getInt(1);
                        if (rnum == 0)
                            continue;
                    }
                    stmt.execute("delete from RESERVATIONS where custName='" + custname + "' and resvType=1 and resvKeyName='" + flight.getFlightNum() + "' and resvKeyNum=" + rnum);
                    stmt.execute("update FLIGHTS set numAvail=numAvail+1 where flightNum='" + flight.getFlightNum() + "'");
                    rs = stmt.executeQuery("select count(resvKey) from RESERVATIONS where custName='"
                            + custname + "' and resvType=1 and resvKeyName='" + flight.getFlightNum() + "'");
                    if (rs.next()) {
                        rnum = rs.getInt(1);
                    }
                    flight.setStatus("已预订" + rnum);
                    flight.setNumAvail(Integer.parseInt(flight.getNumAvail()) + 1);
                }
            }
            tableView.setItems(data);
            tableView.refresh();
        } else if (reservationType.getValue().equals("酒店")) {
            ObservableList<Hotels> data = tableView.getItems();
            if (data == null) return;
            for (Hotels hotel : data) {
                if (hotel.getSelected().isSelected()) {
                    rs = stmt.executeQuery("select max(resvKeyNum) from RESERVATIONS where custName='"
                            + custname + "' and resvType=2 and resvKeyName='" + hotel.getLocation() + "'");
                    int rnum = 0;
                    if (rs.next()) {
                        rnum = rs.getInt(1);
                        if (rnum == 0)
                            continue;
                    }
                    stmt.execute("delete from RESERVATIONS where custName='" + custname + "' and resvType=2 and resvKeyName='" + hotel.getLocation() + "' and resvKeyNum=" + rnum);
                    stmt.execute("update HOTELS set numAvail=numAvail+1 where location='" + hotel.getLocation() + "'");
                    rs = stmt.executeQuery("select count(resvKey) from RESERVATIONS where custName='"
                            + custname + "' and resvType=2 and resvKeyName='" + hotel.getLocation() + "'");
                    if (rs.next()) {
                        rnum = rs.getInt(1);
                    }
                    hotel.setStatus("已预订" + rnum);
                    hotel.setNumAvail(Integer.parseInt(hotel.getNumAvail()) + 1);
                }
            }
            tableView.setItems(data);
            tableView.refresh();
        } else if (reservationType.getValue().equals("出租车")) {
            ObservableList<Cars> data = tableView.getItems();
            if (data == null) return;
            for (Cars car : data) {
                if (car.getSelected().isSelected()) {
                    rs = stmt.executeQuery("select max(resvKeyNum) from RESERVATIONS where custName='"
                            + custname + "' and resvType=3 and resvKeyName='" + car.getLocation() + "'");
                    int rnum = 0;
                    if (rs.next()) {
                        rnum = rs.getInt(1);
                        if (rnum == 0)
                            continue;
                    }
                    stmt.execute("delete from RESERVATIONS where custName='" + custname + "' and resvType=3 and resvKeyName='" + car.getLocation() + "' and resvKeyNum=" + rnum);
                    stmt.execute("update CARS set numAvail=numAvail+1 where location='" + car.getLocation() + "'");
                    rs = stmt.executeQuery("select count(resvKey) from RESERVATIONS where custName='"
                            + custname + "' and resvType=3 and resvKeyName='" + car.getLocation() + "'");
                    if (rs.next()) {
                        rnum = rs.getInt(1);
                    }
                    car.setStatus("已预订" + rnum);
                    car.setNumAvail(Integer.parseInt(car.getNumAvail()) + 1);
                }
            }
            tableView.setItems(data);
            tableView.refresh();
        } else if (reservationType.getValue().equals("预订信息")) {
            ObservableList<Reservations> data = tableView.getItems();
            if (data == null) return;
            for (int i = 0; i < data.size(); ++i) {
                Reservations res = data.get(i);
                if (res.getSelected().isSelected()) {
                    if (res.getResvType().equals("1")) {
                        rs = stmt.executeQuery("select resvKeyName from RESERVATIONS where resvKey='" + res.getResvKey() + "'");
                        String flightnum = "";
                        if (rs.next()) {
                            flightnum = rs.getString(1);
                        }
                        stmt.execute("update FLIGHTS set numAvail=numAvail+1 where flightNum='" + flightnum + "'");
                    } else if (res.getResvType().equals("2")) {
                        rs = stmt.executeQuery("select resvKeyName from RESERVATIONS where resvKey='" + res.getResvKey() + "'");
                        String location = "";
                        if (rs.next()) {
                            location = rs.getString(1);
                        }
                        stmt.execute("update HOTELS set numAvail=numAvail+1 where location='" + location + "'");
                    } else if (res.getResvType().equals("3")) {
                        rs = stmt.executeQuery("select resvKeyName from RESERVATIONS where resvKey='" + res.getResvKey() + "'");
                        String location = "";
                        if (rs.next()) {
                            location = rs.getString(1);
                        }
                        stmt.execute("update CARS set numAvail=numAvail+1 where location='" + location + "'");
                    }
                    stmt.execute("delete from RESERVATIONS where resvKey='" + res.getResvKey() + "'");
                    data.remove(i);
                    i = -1;
                }
            }
            tableView.setItems(data);
            tableView.refresh();
        }
        if (!reservationType.getValue().equals("预订信息"))
            reservationButton.setDisable(false);
        reservationCancelButton.setDisable(false);
    }

    @FXML
    protected void deleteItems() throws Exception {
        deleteButton.setDisable(true);
        insertButton.setDisable(true);
        Statement stmt = conn.createStatement();
        ResultSet rs;
        if(updateType.getValue() == null) {
            deleteButton.setDisable(false);
            insertButton.setDisable(false);
            return ;
        }
        if (updateType.getValue().equals("航班")) {
            ObservableList<Flights> data = tableView.getItems();
            if (data == null) return;
            for (int i = 0; i < data.size(); ++i) {
                Flights flight = data.get(i);
                if (flight.getSelected().isSelected()) {
                    stmt.execute("delete from RESERVATIONS where resvKeyName='" + flight.getFlightNum() + "'");
                    stmt.execute("delete from FLIGHTS where flightNum='" + flight.getFlightNum() + "'");
                    data.remove(i);
                    i = -1;
                }
            }
            tableView.setItems(data);
            tableView.refresh();
        } else if (updateType.getValue().equals("酒店")) {
            ObservableList<Hotels> data = tableView.getItems();
            if (data == null) return;
            for (int i = 0; i < data.size(); ++i) {
                Hotels hotel = data.get(i);
                if (hotel.getSelected().isSelected()) {
                    stmt.execute("delete from RESERVATIONS where resvKeyName='" + hotel.getLocation() + "' and resvType=" + 2);
                    stmt.execute("delete from HOTELS where location='" + hotel.getLocation() + "'");
                    data.remove(i);
                    i = -1;
                }
            }
            tableView.setItems(data);
            tableView.refresh();
        } else if (updateType.getValue().equals("出租车")) {
            ObservableList<Cars> data = tableView.getItems();
            if (data == null) return;
            for (int i = 0; i < data.size(); ++i) {
                Cars car = data.get(i);
                if (car.getSelected().isSelected()) {
                    stmt.execute("delete from RESERVATIONS where resvKeyName='" + car.getLocation() + "' and resvType=" + 3);
                    stmt.execute("delete from CARS where location='" + car.getLocation() + "'");
                    data.remove(i);
                    i = -1;
                }
            }
            tableView.setItems(data);
            tableView.refresh();
        } else if (updateType.getValue().equals("用户")) {
            ObservableList<Customers> data = tableView.getItems();
            if (data == null) return;
            for (int i = 0; i < data.size(); ++i) {
                Customers cust = data.get(i);
                if (cust.getSelected().isSelected()) {
                    if (cust.getCustName().equals("admin"))
                        continue;
                    rs = stmt.executeQuery("select * from RESERVATIONS where custName='" + cust.getCustName() + "'");
                    Statement stmtTemp = conn.createStatement();
                    while (rs.next()) {
                        if (rs.getInt(2) == 1) {
                            stmtTemp.execute("update FLIGHTS set numAvail=numAvail+1 where flightNum='" + rs.getString(4) + "'");
                        } else if (rs.getInt(2) == 2) {
                            stmtTemp.execute("update HOTELS set numAvail=numAvail+1 where location='" + rs.getString(4) + "'");
                        } else if (rs.getInt(2) == 3) {
                            stmtTemp.execute("update CARS set numAvail=numAvail+1 where location='" + rs.getString(4) + "'");
                        }
                        stmtTemp.execute("delete from RESERVATIONS where resvKey='" + rs.getString(3) + "'");
                    }
                    stmt.execute("delete from CUSTOMERS where custName='" + cust.getCustName() + "'");
                    data.remove(i);
                    i = -1;
                }
            }
            tableView.setItems(data);
            tableView.refresh();
        }
        deleteButton.setDisable(false);
        insertButton.setDisable(false);
    }

    @FXML
    protected void insertItems() throws Exception {
        if(updateType.getValue() == null || insertText4.getText().equals("") || insertText1.getText().equals("")){
            return ;
        }
        if (updateType.getValue().equals("航班")) {
            Statement stmt = conn.createStatement();
            stmt.execute("insert into FLIGHTS values('" + insertText1.getText() + "', " + insertText2.getText() + ", "
                    + insertText3.getText() + ", " + insertText3.getText() + ", '" + insertText4.getText() + "', '" + insertText5.getText() + "')");
        } else if (updateType.getValue().equals("酒店")) {
            Statement stmt = conn.createStatement();
            stmt.execute("insert into HOTELS values('" + insertText1.getText() + "', " + insertText2.getText() + ", "
                    + insertText3.getText() + ", " + insertText3.getText() + ")");
        } else if (updateType.getValue().equals("出租车")) {
            Statement stmt = conn.createStatement();
            stmt.execute("insert into CARS values('" + insertText1.getText() + "', " + insertText2.getText() + ", "
                    + insertText3.getText() + ", " + insertText3.getText() + ")");
        } else if (updateType.getValue().equals("用户")) {
            Statement stmt = conn.createStatement();
            stmt.execute("insert into CUSTOMERS values('" + insertText4.getText() + "', '" + insertText5.getText() + "')");
        }
        searchUpdateInformation();
    }

    @FXML
    protected void showPath() throws Exception {
        Statement stmt = conn.createStatement();
        String spath = "/tmp/ZTravel.dot";
        String ppath = "/tmp/ZTravel.jpg";
        File file = new File(spath);
        PrintStream ps = new PrintStream(file);
        ps.println("digraph g{");
        ps.println("node[shape = ellipse, fontcolor = black ,style = filled];");
        ResultSet rs;
        String query;

        if (custname.equals("admin")) {
            if (pathText.getText().equals(""))
                query = "select * from RESERVATIONS where custName='admin' and resvType=1";
            else
                query = "select * from RESERVATIONS where custName='" + pathText.getText() + "' and resvType=1";
        } else {
            query = "select * from RESERVATIONS where custName='" + custname + "' and resvType=1";
        }
        rs = stmt.executeQuery(query);

        while (rs.next()) {
            Statement stmtTemp = conn.createStatement();
            ResultSet rsTemp;
            rsTemp = stmtTemp.executeQuery("select * from FLIGHTS where flightNum='" + rs.getString(4) + "'");
            if(rsTemp.next()){
                ps.println(rsTemp.getString(5).replaceAll(" ", "_") + "->" + rsTemp.getString(6).replaceAll(" ", "_") + ";");
            }
        }
        ps.println("}");
        ps.close();
        Process process = Runtime.getRuntime().exec("dot -Tjpg " + spath + " -o " + ppath);
        process.waitFor();
        pathPane.setBackground(new Background(new BackgroundImage(new Image("file://" + ppath), BackgroundRepeat.NO_REPEAT,
                BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER, BackgroundSize.DEFAULT)));
    }
}
