package com.adilson.phonebook.ui.activity;

import static com.adilson.phonebook.ui.activity.ConstsActivities.KEYSTUDENT;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.adilson.phonebook.DAO.StudentDAO;
import com.adilson.phonebook.R;
import com.adilson.phonebook.model.Student;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.List;

public class ListStudentActivity extends AppCompatActivity {

    private FloatingActionButton buttonNewStudent;
    private ListView listView;
    private final StudentDAO dao = new StudentDAO();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_list);

        dao.save(new Student("Adilson", "11111111111", "adilson@gmail.com"));
        dao.save(new Student("lu", "2222222222222", "lu@gmail.com"));
        dao.save(new Student("Gal", "3333333333333", "Gal@gmail.com"));

        buttonNewStudent = findViewById(R.id.activity_student_list_fab_newStudent);
        buttonNewStudent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openFormModeInsertStudent();
            }
        });

    }

    private void openFormModeInsertStudent() {
        startActivity(new Intent(this, FormStudentActivity.class));
    }

    @Override
    protected void onResume() {
        super.onResume();
        showList();
    }

    private void showList() {
        listView = findViewById(R.id.activity_student_list_Listview);
        final List<Student> studentlist = dao.every();
        adapterConfig(studentlist);
        getPositionStudentOnList(studentlist);
    }

    private void adapterConfig(List<Student> studentlist) {
        listView.setAdapter(new ArrayAdapter<>(ListStudentActivity.this,
                android.R.layout.simple_list_item_1,
                studentlist));
    }

    private void getPositionStudentOnList(List<Student> studentList) {

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
                Student studentSelected = studentList.get(position);

                openFormModeEditStudent(studentSelected);
            }
        });
    }

    private void openFormModeEditStudent(Student studentSelected) {
        Intent goToFormStudentActivity = new Intent(ListStudentActivity.this, FormStudentActivity.class);
        goToFormStudentActivity.putExtra(KEYSTUDENT, studentSelected); //Student Class need be Serializable
        startActivity(goToFormStudentActivity);
    }
}

