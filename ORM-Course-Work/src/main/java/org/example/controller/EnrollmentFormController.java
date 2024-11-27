package org.example.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import org.example.bo.BOFactory;
import org.example.bo.custom.EnrollmentBO;
import org.example.bo.custom.ProgramsBO;
import org.example.bo.custom.StudentBO;
import org.example.dto.EnrollmentDto;
import org.example.dto.ProgramsDto;
import org.example.entity.Enrollment;
import org.example.entity.Programs;
import org.example.entity.Student;
import org.example.tm.EnrollmentTm;
import org.example.tm.ProgramsTm;

import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;

public class EnrollmentFormController {

    @FXML
    private ComboBox<String> cmbProgramId;

    @FXML
    private ComboBox<String> cmbStudentId;

    @FXML
    private TableColumn<?, ?> colDate;

    @FXML
    private TableColumn<?, ?> colEnrollmentId;

    @FXML
    private TableColumn<?, ?> colProgram;

    @FXML
    private TableColumn<?, ?> colProgramId;

    @FXML
    private TableColumn<?, ?> colStudentId;

    @FXML
    private TableColumn<?, ?> colStudentName;

    @FXML
    private TableColumn<?, ?> colTotalFee;

    @FXML
    private TableColumn<?, ?> colUpfrontFee;

    @FXML
    private TableView<EnrollmentTm> tblEnrollment;

    @FXML
    private TextField txtDate;

    @FXML
    private TextField txtEnrollmentId;

    @FXML
    private TextField txtProgram;

    @FXML
    private TextField txtStudentName;

    @FXML
    private TextField txtTotalFee;

    @FXML
    private TextField txtUpFrontPayment;

    ObservableList<EnrollmentTm> observableList;
    String ID;
    EnrollmentBO enrollmentBo = (EnrollmentBO) BOFactory.getInstance().getBO(BOFactory.BOTypes.ENROLLMENT);
    StudentBO studentBO = (StudentBO) BOFactory.getInstance().getBO(BOFactory.BOTypes.STUDENT);
    ProgramsBO programsBO = (ProgramsBO) BOFactory.getInstance().getBO(BOFactory.BOTypes.PROGRAMS);

    public void initialize(){
        try {
            LocalDate today = LocalDate.now();
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            txtDate.setText(today.format(formatter));
            getAllEnrollments();
            loadStudentIds();
            loadProgramsIds();
            setCellValueFactory();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        setCellValueFactory();
        generateNextUserId();

    }


    private void generateNextUserId() {
        String nextId = null;
        try {
            nextId = enrollmentBo.generateNewEnrollmentID();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        txtEnrollmentId.setText(nextId);
    }

    private void setCellValueFactory() {
        colEnrollmentId.setCellValueFactory(new PropertyValueFactory<>("eid"));
        colStudentId.setCellValueFactory(new PropertyValueFactory<>("sid"));
        colStudentName.setCellValueFactory(new PropertyValueFactory<>("Studentname"));
        colProgramId.setCellValueFactory(new PropertyValueFactory<>("cid"));
        colProgram.setCellValueFactory(new PropertyValueFactory<>("Coursename"));
        colDate.setCellValueFactory(new PropertyValueFactory<>("date"));
        colTotalFee.setCellValueFactory(new PropertyValueFactory<>("remainingfee"));
        colUpfrontFee.setCellValueFactory(new PropertyValueFactory<>("upfrontpayment"));
    }

    private void loadProgramsIds() throws SQLException, ClassNotFoundException {
        List<String> programIds = programsBO.getAllProgramIds();
        cmbProgramId.getItems().clear();
        cmbProgramId.getItems().addAll(programIds);
    }

    private void loadStudentIds() throws SQLException, ClassNotFoundException {
        List<String> studentIds = studentBO.getAllStudentIds();
        cmbStudentId.getItems().clear();
        cmbStudentId.getItems().addAll(studentIds);
    }

    private void getAllEnrollments() throws SQLException, ClassNotFoundException {
//        tblEnrollment.getItems().clear();
//        observableList = FXCollections.observableArrayList();
//        List<EnrollmentDto> allenrollment = enrollmentBo.getAllEnrollment();
//
//        for (EnrollmentDto enrollmentDTO : allenrollment){
//            observableList.add(new EnrollmentTm(enrollmentDTO.getEid(),enrollmentDTO.getSid(),enrollmentDTO.getStudentname(),enrollmentDTO.getCid(),enrollmentDTO.getCoursename(),enrollmentDTO.getDate(),enrollmentDTO.getUpfrontpayment(),enrollmentDTO.getRemainingfee()));
//            tblEnrollment.setItems(observableList);
//        }
        ObservableList<EnrollmentTm> obList = FXCollections.observableArrayList();

        try {
            List<EnrollmentDto> enrollmentList = enrollmentBo.getAllEnrollment();

            for (EnrollmentDto enrollmentDto : enrollmentList) {

                EnrollmentTm enrollmentTm = new EnrollmentTm(
                        enrollmentDto.getEid(),
                        enrollmentDto.getSid(),
                        enrollmentDto.getStudentname(),
                        enrollmentDto.getCid(),
                        enrollmentDto.getCoursename(),
                        enrollmentDto.getDate(),
                        enrollmentDto.getUpfrontpayment(),
                        enrollmentDto.getRemainingfee()
                );

                obList.add(enrollmentTm);
            }

            tblEnrollment.setItems(obList);

        } catch (Exception e) {
            new Alert(Alert.AlertType.ERROR, "Error loading programss: " + e.getMessage(), ButtonType.OK).show();
        }
    }

    @FXML
    void btnClearOnAction(ActionEvent event) {

    }

    @FXML
    void btnDeleteOnAction(ActionEvent event) throws SQLException, ClassNotFoundException {
        ButtonType yes = new ButtonType("Yes", ButtonBar.ButtonData.OK_DONE);
        ButtonType no = new ButtonType("No", ButtonBar.ButtonData.CANCEL_CLOSE);
        Optional<ButtonType> result = new Alert(Alert.AlertType.INFORMATION, "Are you sure to remove?", yes, no).showAndWait();

        if (result.orElse(no) == yes) {
            if (!enrollmentBo.deleteEnrollment(ID)) {
                new Alert(Alert.AlertType.ERROR, "Error!!").show();
            }
        }
        clearTextFileds();
        generateNextUserId();
        getAll();
        loadStudentIds();
        loadProgramsIds();
    }

    @FXML
    void btnSaveOnAction(ActionEvent event) throws Exception {
        String id = txtEnrollmentId.getText();
        String sid = cmbStudentId.getValue();
        String studentname = txtStudentName.getText();
        String cid = cmbProgramId.getValue();
        String coursename = txtProgram.getText();
        LocalDate date = LocalDate.parse(txtDate.getText());
        Double totalfee = Double.valueOf(txtTotalFee.getText());
        Double upfrontpayment = Double.valueOf(txtUpFrontPayment.getText());
        Double remainingfee = totalfee - upfrontpayment;

        if (enrollmentBo.EnrollmentIdExists(id)){
            new Alert(Alert.AlertType.ERROR, "Enrollment ID " + id + " already exists!").show();
            return;
        }

        if (enrollmentBo.isStudentEnrolledInCourse(sid, cid)) {
            new Alert(Alert.AlertType.ERROR, "Student " + sid + " is already enrolled in course " + cid + "!").show();
            return;
        }

        if (enrollmentBo.saveEnrollment(new EnrollmentDto(id, sid,studentname,cid,coursename,date,upfrontpayment,remainingfee))) {
            clearTextFileds();
            getAll();
            loadStudentIds();
            loadProgramsIds();
            new Alert(Alert.AlertType.CONFIRMATION, "Saved!!").show();
        } else {
            new Alert(Alert.AlertType.ERROR, "Error!!").show();
        }
    }

    private void getAll() throws SQLException, ClassNotFoundException {
        tblEnrollment.getItems().clear();
        observableList = FXCollections.observableArrayList();
        List<EnrollmentDto> allenrollment = enrollmentBo.getAllEnrollment();

        for (EnrollmentDto enrollmentDTO : allenrollment){
            observableList.add(new EnrollmentTm(enrollmentDTO.getEid(),enrollmentDTO.getSid(),enrollmentDTO.getStudentname(),enrollmentDTO.getCid(),enrollmentDTO.getCoursename(),enrollmentDTO.getDate(),enrollmentDTO.getUpfrontpayment(),enrollmentDTO.getRemainingfee()));
            tblEnrollment.setItems(observableList);
        }
    }

    private void clearTextFileds() throws SQLException, ClassNotFoundException {
        txtEnrollmentId.clear();
        cmbStudentId.getItems().clear();
        txtStudentName.clear();
        cmbProgramId.getItems().clear();
        txtProgram.clear();
        txtDate.clear();
        txtUpFrontPayment.clear();
        txtTotalFee.clear();
        loadStudentIds();
        loadProgramsIds();
        LocalDate today = LocalDate.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        txtDate.setText(today.format(formatter));
    }

    @FXML
    void btnUpdateOnAction(ActionEvent event) throws Exception {
        String id = txtEnrollmentId.getText();
        String sid = cmbStudentId.getValue();
        String studentname = txtStudentName.getText();
        String cid = cmbProgramId.getValue();
        String coursename = txtProgram.getText();
        LocalDate date = LocalDate.parse(txtDate.getText());
        Double upfrontpayment = Double.valueOf(txtUpFrontPayment.getText());
        Enrollment enrollmentById = enrollmentBo.findEnrollmentById(id);
        Double newremainfeecalculate = newremainfeecalculate(enrollmentById, upfrontpayment);

        if(enrollmentBo.updateEnrollment(new EnrollmentDto(id, sid,studentname,cid,coursename,date,upfrontpayment,newremainfeecalculate))){
            new Alert(Alert.AlertType.CONFIRMATION, "Update Successfully!!").show();
        }else {
            new Alert(Alert.AlertType.ERROR, "Error!!").show();
        }
        clearTextFileds();
        generateNextUserId();
        getAll();
        loadStudentIds();
        loadProgramsIds();
    }

    private Double newremainfeecalculate(Enrollment enrollment, Double newupfrontpayment) {
        Double upfrontpayment = enrollment.getUpfrontpayment();
        Double remainingfee = enrollment.getRemainingfee();
        double fee = (remainingfee + upfrontpayment) - newupfrontpayment;
        return fee;
    }

    @FXML
    void txtSearchOnAction(ActionEvent event) {

    }

    public void handleCourseSelection(ActionEvent event) throws Exception {
        String selectedProgramId = cmbProgramId.getValue();

        if (selectedProgramId != null && !selectedProgramId.isEmpty()) {
            Programs selectedProgram = programsBO.getProgramById(selectedProgramId);

            if (selectedProgram != null) {
                String courseName = selectedProgram.getName();
                Double fee = selectedProgram.getFee();
                System.out.println("Course ID: " + selectedProgram.getId());
                System.out.println("Course Name: " + courseName);
                System.out.println("Course fee: " + fee);

                txtProgram.setText(courseName);
                txtTotalFee.setText(String.valueOf(fee));

            } else {
                System.out.println("Course not found for ID: " + selectedProgramId);
            }
        }
    }

    public void handleStudentSelection(ActionEvent event) throws Exception {
        String selectedStudentId = cmbStudentId.getValue();

        if (selectedStudentId != null && !selectedStudentId.isEmpty()) {
            Student selectedStudent = studentBO.getStudentById(selectedStudentId);

            if (selectedStudent != null) {
                String studentName = selectedStudent.getName();
                System.out.println("Course ID: " + selectedStudent.getId());
                System.out.println("Course Name: " + studentName);

                txtStudentName.setText(studentName);


            } else {
                System.out.println("Student not found for ID: " + selectedStudent);
            }
        }
    }
}
