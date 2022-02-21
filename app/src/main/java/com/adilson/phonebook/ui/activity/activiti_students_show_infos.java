package com.adilson.phonebook.ui.activity;

import static com.adilson.phonebook.ui.activity.ConstsActivities.KEYSTUDENT;
import static com.adilson.phonebook.ui.activity.ConstsActivities.TITLE_APPBAR_FORMSTUDENTACTIVITY_EDIT;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import com.adilson.phonebook.R;
import com.adilson.phonebook.model.Student;

public class activiti_students_show_infos extends AppCompatActivity {

    private TextView textName;
    private TextView textCell;
    private TextView textEmail;
    private Student student;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_activiti_students_show_infos);

        inicializeFields();
        uploadInfosByIntent();

    }

    private void uploadInfosByIntent() {
        Intent dados = getIntent();
        student = (Student) dados.getSerializableExtra(KEYSTUDENT);
        toEditStudentFillField();
        setTitle(TITLE_APPBAR_FORMSTUDENTACTIVITY_EDIT);
    }

    private void inicializeFields() {
        textName = findViewById(R.id.activity_student_info_name);
        textCell = findViewById(R.id.activity_student_info_cel);
        textEmail = findViewById(R.id.activity_student_info_email);
    }

    private void toEditStudentFillField() {
        textName.setText(student.getName());
        textCell.setText(student.getCell());
        textEmail.setText(student.getEmail());
    }
}