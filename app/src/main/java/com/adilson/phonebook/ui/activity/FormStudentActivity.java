package com.adilson.phonebook.ui.activity;

import static com.adilson.phonebook.ui.activity.ConstsActivities.KEYSTUDENT;
import static com.adilson.phonebook.ui.activity.ConstsActivities.TITLE_APPBAR_FORMSTUDENTACTIVITY_EDIT;
import static com.adilson.phonebook.ui.activity.ConstsActivities.TITLE_APPBAR_FORMSTUDENTACTIVITY_INSERT;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import com.adilson.phonebook.DAO.StudentDAO;
import com.adilson.phonebook.R;
import com.adilson.phonebook.model.Student;

public class FormStudentActivity extends AppCompatActivity {

    final StudentDAO dao = new StudentDAO();
    private EditText fieldName;
    private EditText fieldCell;
    private EditText fieldEmail;
    private Button buttonCancel;
    private Student student;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_form_student);

        initializeTheField();
        configButtonSave();
        uploadInformationOfStudents();
        cancel();

    }

    private void uploadInformationOfStudents() {
        Intent dados = getIntent();
        if (dados.hasExtra("student")){
            student = (Student) dados.getSerializableExtra(KEYSTUDENT);
            toEditStudentFillField();
            setTitle(TITLE_APPBAR_FORMSTUDENTACTIVITY_EDIT);
        } else {
            student = new Student();
            setTitle(TITLE_APPBAR_FORMSTUDENTACTIVITY_INSERT);
        }
    }

    private void toEditStudentFillField() {
        fieldName.setText(student.getName());
        fieldCell.setText(student.getCell());
        fieldEmail.setText(student.getEmail());
    }

    private void cancel() {
        buttonCancel = findViewById(R.id.activity_form_student_button_cancel);
        cancelForm();
    }

    private void cancelForm() {
        buttonCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }

    private void configButtonSave() {
        Button saveButton = findViewById(R.id.activity_form_student_save);
        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                FinishForm();
            }
        });
    }

    private void FinishForm() {
        fillFieldObjetStudent();
        if (student.hasIdValid()){
            dao.Edit(student);
        } else {
            dao.save(student);
        }

        finish();
    }

    private void initializeTheField() {
        fieldName = findViewById(R.id.activity_form_student_name);
        fieldCell = findViewById(R.id.activity_form_student_Cellphone);
        fieldEmail = findViewById(R.id.activity_form_student_email);
    }

    private void fillFieldObjetStudent() {
        String name = fieldName.getText().toString();
        String cell = fieldCell.getText().toString();
        String email = fieldEmail.getText().toString();

        student.setName(name);
        student.setCell(cell);
        student.setEmail(email);
    }
}