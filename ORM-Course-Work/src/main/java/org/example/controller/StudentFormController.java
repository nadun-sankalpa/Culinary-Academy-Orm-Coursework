package org.example.controller;

import com.sun.mail.imap.protocol.ID;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import org.example.bo.BOFactory;
import org.example.bo.custom.StudentBO;
import org.example.bo.custom.UserBO;
import org.example.bo.custom.impl.StudentBOImpl;
import org.example.dto.StudentDto;
import org.example.dto.UserDto;
import org.example.entity.Student;
import org.example.tm.StudentTm;
import org.example.tm.UserTm;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class StudentFormController {

    @FXML
    private DatePicker btnDOB;
    @FXML
    private DatePicker btnRegDate;
    @FXML
    private ComboBox<String> cmbProgram;

    @FXML
    private RadioButton btnFemale;

    @FXML
    private RadioButton btnMale;

    @FXML
    private TableColumn<?, ?> colAddress;

    @FXML
    private TableColumn<?, ?> colContact;

    @FXML
    private TableColumn<?, ?> colDOB;

    @FXML
    private TableColumn<?, ?> colGender;

    @FXML
    private TableColumn<?, ?> colID;

    @FXML
    private TableColumn<?, ?> colName;

    @FXML
    private TableView<StudentTm> tblStudent;

    @FXML
    private TextField txtAddress;

    @FXML
    private TextField txtContact;

    @FXML
    private TextField txtId;

    @FXML
    private TextField txtName;
    @FXML
    private ComboBox<String> cmbGender;
    @FXML
    private TableColumn<?, ?> colProgram;

    @FXML
    private TableColumn<?, ?> colRegDate;



    StudentBO studentBO = (StudentBO) BOFactory.getInstance().getBO(BOFactory.BOTypes.STUDENT);
    String id;
    ObservableList<Student> observableList;

    public void initialize(){
        setCellValueFactory();
        loadAllStudents();

        cmbGender.getItems().addAll("Male", "Female");
        tblStudent.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue != null) {
                setFieldsWithSelectedRowData(newValue);
            }
        });
    }

    private void setFieldsWithSelectedRowData(StudentTm selectedStudent) {
        txtId.setText(selectedStudent.getId());
        txtName.setText(selectedStudent.getName());
        txtAddress.setText(selectedStudent.getAddress());
        txtContact.setText(selectedStudent.getContact());
        btnDOB.setValue(LocalDate.parse(selectedStudent.getBirthDay()));
        cmbGender.setValue(selectedStudent.getGender());
    }

    private List<StudentDto> loadAllStudents() {
        ObservableList<StudentTm> obList = FXCollections.observableArrayList();

        try {
            List<StudentDto> studentList = studentBO.getAllStudents();

            for (StudentDto studentDto : studentList) {
                String formattedBirthDay = studentDto.getBirthDay().toString();
                StudentTm studentTm = new StudentTm(
                        studentDto.getId(),
                        studentDto.getName(),
                        studentDto.getAddress(),
                        studentDto.getContact(),
                        formattedBirthDay,
                        studentDto.getGender()
                );

                obList.add(studentTm);
            }

            tblStudent.setItems(obList);

        } catch (Exception e) {
            new Alert(Alert.AlertType.ERROR, "Error loading users: " + e.getMessage(), ButtonType.OK).show();
        }
        return null;
    }

    private void setCellValueFactory() {
        colID.setCellValueFactory(new PropertyValueFactory<>("id"));
        colName.setCellValueFactory(new PropertyValueFactory<>("name"));
        colAddress.setCellValueFactory(new PropertyValueFactory<>("address"));
        colContact.setCellValueFactory(new PropertyValueFactory<>("contact"));
        colDOB.setCellValueFactory(new PropertyValueFactory<>("birthDay"));
        colGender.setCellValueFactory(new PropertyValueFactory<>("gender"));
    }

    @FXML
    void btnClearOnAction(ActionEvent event) {

    }

    @FXML
    void btnDeleteOnAction(ActionEvent event) throws SQLException, ClassNotFoundException {
        ButtonType yes = new ButtonType("Yes", ButtonBar.ButtonData.OK_DONE);
        ButtonType no = new ButtonType("No", ButtonBar.ButtonData.CANCEL_CLOSE);
        Optional<ButtonType> result = new Alert(Alert.AlertType.INFORMATION, "Are you sure to remove?", yes, no).showAndWait();

        String id = txtId.getText();
        if (result.orElse(no) == yes) {
            if (!studentBO.delete(id)) {
                new Alert(Alert.AlertType.ERROR, "Error!!").show();
            }
        }
//        generateNextUserId();
        clearFields();
        loadAllStudents();
    }

    @FXML
    void btnSaveOnAction(ActionEvent event) throws SQLException, ClassNotFoundException {
        boolean isSaved = studentBO.save(new StudentDto(
                txtId.getText(),
                txtName.getText(),
                txtAddress.getText(),
                txtContact.getText(),
                btnDOB.getValue(),
                (String) cmbGender.getValue()
        ));
        if (isSaved) {
            loadAllStudents();
            clearFields();
            new Alert(Alert.AlertType.CONFIRMATION, "User Saved").show();
        } else {
            new Alert(Alert.AlertType.ERROR, "User UnSaved").show();
        }
    }

    private void clearFields() {
        txtId.setText("");
        txtName.setText("");
        txtAddress.setText("");
        txtContact.setText("");
        btnDOB.setValue(null);
        cmbGender.getSelectionModel().clearSelection();
    }

    @FXML
    void btnUpdateOnAction(ActionEvent event) throws SQLException, ClassNotFoundException{
        String sid=txtId.getText();
        String name = txtName.getText();
        String address = txtAddress.getText();
        String contact = txtContact.getText();
        LocalDate dob = LocalDate.parse(String.valueOf(btnDOB.getValue()));
        String gender = cmbGender.getValue();

        if(studentBO.update(new StudentDto(sid,name,address,contact,dob,gender))){
            new Alert(Alert.AlertType.CONFIRMATION, "Update Successfully!!").show();
        }else {
            new Alert(Alert.AlertType.ERROR, "Error!!").show();
        }
        clearFields();
        loadAllStudents();
    }

    @FXML
    void txtSearchOnAction(ActionEvent event) {

    }

}
