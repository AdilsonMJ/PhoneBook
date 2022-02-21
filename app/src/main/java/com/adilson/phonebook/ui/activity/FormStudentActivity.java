package com.adilson.phonebook.ui.activity;

import static com.adilson.phonebook.ui.activity.ConstsActivities.KEYSTUDENT;
import static com.adilson.phonebook.ui.activity.ConstsActivities.TITLE_APPBAR_FORMSTUDENTACTIVITY_EDIT;
import static com.adilson.phonebook.ui.activity.ConstsActivities.TITLE_APPBAR_FORMSTUDENTACTIVITY_INSERT;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.adilson.phonebook.DAO.StudentDAO;
import com.adilson.phonebook.R;
import com.adilson.phonebook.model.Student;

public class FormStudentActivity extends AppCompatActivity {

    final StudentDAO dao = new StudentDAO();
    private EditText fieldName;
    private EditText fieldCell;
    private EditText fieldEmail;
    private Student student;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_form_student);

        initializeTheField();
        uploadInformationOfStudents();

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_form_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int itemId = item.getItemId();

        if (itemId == R.id.activity_form_menu_salvar) {
            finishForm();
        }
        return super.onOptionsItemSelected(item);
    }

    private void uploadInformationOfStudents() {
        Intent dados = getIntent();
        if (dados.hasExtra("student")) {
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

    private void finishForm() {
        fillFieldObjetStudent();
        if (!(student.getName().length() == 0 || student.getName() == null)){
            if (student.hasIdValid()) {
                dao.Edit(student);
            } else {
                dao.save(student);
            }
            finish();
        } else {
            Toast.makeText(FormStudentActivity.this, "Need fill the field The NAME", Toast.LENGTH_LONG).show();
        }
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