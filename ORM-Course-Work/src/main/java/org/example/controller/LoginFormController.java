package org.example.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import org.example.bo.BOFactory;
import org.example.bo.custom.UserBO;
import org.example.dao.Custom.UserDAO;
import org.example.dao.DAOFactory;

import java.io.IOException;

public class LoginFormController {
    @FXML
    private ComboBox<String> cmbRole;

    @FXML
    private AnchorPane root;

    @FXML
    private AnchorPane rootNode;

    @FXML
    private PasswordField txtPassword;

    @FXML
    private TextField txtUserName;

    private final UserBO userBO = (UserBO) BOFactory.getInstance().getBO(BOFactory.BOTypes.USER);
    UserDAO userDAO = (UserDAO) DAOFactory.getInstance().getDAO(DAOFactory.DAOTypes.USER);

    public void initialize(){
        cmbRole.getItems().addAll("Admin","Coordinator");
    }

    @FXML
    void btnLoginOnAction(ActionEvent event) throws IOException {
        String username = txtUserName.getText();
        String password = txtPassword.getText();
        String role=cmbRole.getValue();

        if (userDAO.checkPassword(username,password)){
            if (role.toString().contains("Admin")){
                AnchorPane rootNode = FXMLLoader.load(this.getClass().getResource("/View/admin_dashboard_form.fxml"));

                Scene scene = new Scene(rootNode);

                Stage stage = (Stage) this.rootNode.getScene().getWindow();
                stage.setScene(scene);
                stage.centerOnScreen();
                stage.setTitle("Dashboard Form");
            }else {
                AnchorPane rootNode = FXMLLoader.load(this.getClass().getResource("/View/coordinator_dashboard_form.fxml"));

                Scene scene = new Scene(rootNode);

                Stage stage = (Stage) this.rootNode.getScene().getWindow();
                stage.setScene(scene);
                stage.centerOnScreen();
                stage.setTitle("Dashboard Form");
            }

            //SignInForm.getScene().getWindow().hide();
        }else {
            new Alert(Alert.AlertType.ERROR,"Please Check Username and password !!").show();
        }

        txtUserName.clear();
        txtPassword.clear();
//        cmbRole.clone();
    }



    private void clearFields() {
    }

    private boolean validateLogin() {
        return false;
    }

    @FXML
    void linkRegistrationOnAction(ActionEvent event) {

    }

}
