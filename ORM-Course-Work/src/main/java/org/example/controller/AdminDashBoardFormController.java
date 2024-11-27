package org.example.controller;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.animation.TranslateTransition;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.util.Duration;
import org.example.bo.BOFactory;
import org.example.bo.custom.ProgramsBO;
import org.example.bo.custom.StudentBO;
import org.example.config.SessionFactoryConfig;
import org.example.dto.ProgramsDto;
import org.example.entity.Programs;
import org.example.entity.Student;
import org.example.entity.User;
import org.example.tm.ProgramsTm;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class AdminDashBoardFormController {



    @FXML
    private Label lblProgramsCount;

    @FXML
    private Label lblStudentCount;
    @FXML
    private Label lblTime;
    @FXML
    private AnchorPane root;
    public AnchorPane rootNode;
    @FXML
    private AnchorPane Load;

    @FXML
    private TableColumn<?, ?> colDuration;

    @FXML
    private TableColumn<?, ?> colFee;

    @FXML
    private TableColumn<?, ?> colID;

    @FXML
    private TableColumn<?, ?> colName;

    @FXML
    private TableView<ProgramsTm> tblProgram;


    private void setStudentCount(int studentCount) {
        lblStudentCount.setText(String.valueOf(studentCount));
    }

    ProgramsBO programsBO = (ProgramsBO) BOFactory.getInstance().getBO(BOFactory.BOTypes.PROGRAMS);
    StudentBO studentBO = (StudentBO) BOFactory.getInstance().getBO(BOFactory.BOTypes.STUDENT);

    ObservableList<Programs> observableList;
    private int studentCount;

    public void initialize() throws IOException {
//        initClock();
        loadAllPrograms();
        setCellValueFactory();

        try {
            int programsCount = programsBO.getCourseCount();
            int studentCount = studentBO.getStudentCount();
            lblProgramsCount.setText(String.valueOf(programsCount));
            lblStudentCount.setText(String.valueOf(studentCount));

        } catch (Exception e) {
            e.printStackTrace();
            new Alert(Alert.AlertType.ERROR, "Failed to load counts from database.").show();
        }
    }

    private void loadAllPrograms() {
        ObservableList<ProgramsTm> obList = FXCollections.observableArrayList();

        try {
            List<ProgramsDto> programsList = programsBO.getAllPrograms();

            for (ProgramsDto programsDto : programsList) {

                ProgramsTm programsTm = new ProgramsTm(
                        programsDto.getId(),
                        programsDto.getName(),
                        programsDto.getDuration(),
                        programsDto.getFee()
                );

                obList.add(programsTm);
            }

            tblProgram.setItems(obList);

        } catch (Exception e) {
            new Alert(Alert.AlertType.ERROR, "Error loading programss: " + e.getMessage(), ButtonType.OK).show();
        }
    }

    private void setCellValueFactory() {
        colID.setCellValueFactory(new PropertyValueFactory<>("id"));
        colName.setCellValueFactory(new PropertyValueFactory<>("name"));
        colDuration.setCellValueFactory(new PropertyValueFactory<>("duration"));
        colFee.setCellValueFactory(new PropertyValueFactory<>("fee"));
    }


//    private void initClock() {
//        Timeline clock = new Timeline(new KeyFrame(Duration.ZERO, e -> {
//            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd        HH:mm:ss");
//            lblTime.setText(LocalDateTime.now().format(formatter));
//        }), new KeyFrame(Duration.seconds(1)));
//        clock.setCycleCount(Animation.INDEFINITE);
//        clock.play();
//    }


    @FXML
    void btnDashboardOnAction(ActionEvent event) throws IOException {
        AnchorPane rootNode = FXMLLoader.load(this.getClass().getResource("/View/admin_dashboard_form.fxml"));

        Scene scene = new Scene(rootNode);

        Stage stage = (Stage) this.root.getScene().getWindow();
        stage.setScene(scene);
        stage.centerOnScreen();
        stage.setTitle("Dashboard Form");
    }

    @FXML
    void btnLogOutOnAction(ActionEvent event) throws IOException {
        AnchorPane anchorPane = FXMLLoader.load(getClass().getResource("/View/login_form.fxml"));

        Stage stage = (Stage) root.getScene().getWindow();
        stage.setScene(new Scene(anchorPane));
        stage.centerOnScreen();
        stage.setTitle("Login Form");
    }

    @FXML
    void btnPaymentOnAction(ActionEvent event) throws IOException {
        URL resource = getClass().getResource("/View/payment_form.fxml");
        assert resource != null;
        Parent load = FXMLLoader.load(resource);
        Load.getChildren().clear();
        Load.getChildren().add(load);
        TranslateTransition transition = new TranslateTransition(Duration.seconds(1), Load);
        transition.setFromX(load.getScene().getWidth());
        transition.setToX(0);
        transition.play();
    }

    @FXML
    void btnEnrollmentOnAction(ActionEvent event) throws IOException {
        URL resource = getClass().getResource("/View/enrollment_form.fxml");
        assert resource != null;
        Parent load = FXMLLoader.load(resource);
        Load.getChildren().clear();
        Load.getChildren().add(load);
        TranslateTransition transition = new TranslateTransition(Duration.seconds(1), Load);
        transition.setFromX(load.getScene().getWidth());
        transition.setToX(0);
        transition.play();
    }

    @FXML
    void btnProgramOnAction(ActionEvent event) throws IOException {
        URL resource = getClass().getResource("/View/program_form.fxml");
        assert resource != null;
        Parent load = FXMLLoader.load(resource);
        Load.getChildren().clear();
        Load.getChildren().add(load);
        TranslateTransition transition = new TranslateTransition(Duration.seconds(1), Load);
        transition.setFromX(load.getScene().getWidth());
        transition.setToX(0);
        transition.play();
    }

    @FXML
    void btnStudentOnAction(ActionEvent event) throws IOException {
        URL resource = getClass().getResource("/View/student_form.fxml");
        assert resource != null;
        Parent load = FXMLLoader.load(resource);
        Load.getChildren().clear();
        Load.getChildren().add(load);
        TranslateTransition transition = new TranslateTransition(Duration.seconds(1), Load);
        transition.setFromX(load.getScene().getWidth());
        transition.setToX(0);
        transition.play();
    }

    @FXML
    void btnUserOnAction(ActionEvent event) throws IOException {
        URL resource = getClass().getResource("/View/user_form.fxml");
        assert resource != null;
        Parent load = FXMLLoader.load(resource);
        Load.getChildren().clear();
        Load.getChildren().add(load);
        TranslateTransition transition = new TranslateTransition(Duration.seconds(1), Load);
        transition.setFromX(load.getScene().getWidth());
        transition.setToX(0);
        transition.play();
    }
}
