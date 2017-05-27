package mainsql;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.sql.*;

public class LoginController {

    private Stage primaryStage;
    private Connection conn = null;

    @FXML
    private Text actiontarget;
    @FXML
    private TextField textField;
    @FXML
    private PasswordField passwordField;

    void setStage(Stage stage) {
        this.primaryStage = stage;
    }

    @FXML
    protected void handleEnterAction(KeyEvent event) throws Exception {
        if (event.getCode() == KeyCode.ENTER) {
            handleSubmitButtonAction();
        }
    }

    @FXML
    protected void handleSubmitButtonAction() throws Exception {
        Boolean success = false;
        String url = "jdbc:mysql://localhost:3306/lab_2?"
                + "useSSL=false&useUnicode=true&characterEncoding=UTF8"
                + "&user=root"
                + "&password=283556";
        actiontarget.setFont(Font.font("AR PL UMing CN", FontWeight.BOLD, 15));
        try {
            Class.forName("com.mysql.jdbc.Driver");
            conn = DriverManager.getConnection(url);
            if (conn != null) {
                String sqlCommand;
                Statement stmt = conn.createStatement();
                stmt.executeQuery("use lab_2");
                sqlCommand = "select passwd from CUSTOMERS where custname='" + textField.getText() + "'";
                ResultSet rs = stmt.executeQuery(sqlCommand);
                if(rs.next() && rs.getString(1).equals(passwordField.getText())) {
                    actiontarget.setText("登录成功！");
                    success = true;
                }else {
                    actiontarget.setText("登录失败,请重试！");
                    success = false;
                }
            }
        } catch (SQLException e) {
            actiontarget.setText("登录失败,请重试！");
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        if (success) {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("reservationPane.fxml"));
            Parent root = fxmlLoader.load();
            Scene scene = new Scene(root);
            ResController resController = fxmlLoader.getController();
            if (resController != null) {
                resController.setConnection(conn, textField.getText());
            } else {
                System.out.println("conn is null!");
            }
            primaryStage.close();
            primaryStage.setScene(scene);
            primaryStage.setTitle("Z Travel");
            primaryStage.setResizable(false);
            primaryStage.show();
        }
    }
}
