package org.example.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import org.example.bo.BOFactory;
import org.example.bo.custom.ProgramsBO;
import org.example.bo.custom.UserBO;
import org.example.bo.custom.impl.ProgramsBOImpl;
import org.example.dto.ProgramsDto;
import org.example.dto.StudentDto;
import org.example.dto.UserDto;
import org.example.entity.Programs;
import org.example.entity.User;
import org.example.tm.ProgramsTm;
import org.example.tm.UserTm;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public class ProgramFormController {

    @FXML
    private TableColumn<?, ?> colDuration;

    @FXML
    private TableColumn<?, ?> colFee;

    @FXML
    private TableColumn<?, ?> colID;

    @FXML
    private TableColumn<?, ?> colName;

    @FXML
    private AnchorPane root;

    @FXML
    private TableView<ProgramsTm> tblProgram;

    @FXML
    private TextField txtDuration;

    @FXML
    private TextField txtFee;

    @FXML
    private TextField txtId;

    @FXML
    private TextField txtName;

    ProgramsBO programsBO = (ProgramsBO) BOFactory.getInstance().getBO(BOFactory.BOTypes.PROGRAMS);
    ObservableList<Programs> observableList;

    public void initialize(){
        setCellValueFactory();
        loadAllPrograms();

        tblProgram.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue != null) {
                setFieldsWithSelectedRowData(newValue);
            }
        });
    }

    private void setFieldsWithSelectedRowData(ProgramsTm newValue) {
        txtId.setText(newValue.getId());
        txtName.setText(newValue.getName());
        txtDuration.setText(newValue.getDuration());
        txtFee.setText(String.valueOf(newValue.getFee()));
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

    @FXML
    void btnClearOnAction(ActionEvent event) {

    }

    @FXML
    void btnDeleteOnAction(ActionEvent event) throws SQLException, ClassNotFoundException{
        ButtonType yes = new ButtonType("Yes", ButtonBar.ButtonData.OK_DONE);
        ButtonType no = new ButtonType("No", ButtonBar.ButtonData.CANCEL_CLOSE);
        Optional<ButtonType> result = new Alert(Alert.AlertType.INFORMATION, "Are you sure to remove?", yes, no).showAndWait();

        String id = txtId.getText();
        if (result.orElse(no) == yes) {
            if (!programsBO.delete(id)) {
                new Alert(Alert.AlertType.ERROR, "Error!!").show();
            }
        }
//        generateNextUserId();
        clearFields();
        loadAllPrograms();
    }

    @FXML
    void btnSaveOnAction(ActionEvent event) throws SQLException, ClassNotFoundException {
        boolean isSaved = programsBO.save(new ProgramsDto(
                txtId.getText(),
                txtName.getText(),
                txtDuration.getText(),
                Double.valueOf(txtFee.getText())
        ));
        if (isSaved) {
            loadAllPrograms();
            clearFields();
            new Alert(Alert.AlertType.CONFIRMATION, "User Saved").show();
        } else {
            new Alert(Alert.AlertType.ERROR, "User UnSaved").show();
        }
    }

    private void clearFields() {
        txtId.clear();
        txtName.clear();
        txtDuration.clear();
        txtFee.clear();
    }

    @FXML
    void btnUpdateOnAction(ActionEvent event) throws SQLException, ClassNotFoundException {
        String pid=txtId.getText();
        String name = txtName.getText();
        String duration = txtDuration.getText();
        Double fee = Double.valueOf(txtFee.getText());

        if(programsBO.update(new ProgramsDto(pid,name,duration,fee))){
            new Alert(Alert.AlertType.CONFIRMATION, "Update Successfully!!").show();
        }else {
            new Alert(Alert.AlertType.ERROR, "Error!!").show();
        }
        clearFields();
        loadAllPrograms();
    }

    @FXML
    void txtSearchOnAction(ActionEvent event) {

    }

}
