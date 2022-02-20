package com.adilson.phonebook.ui.activity;

import static com.adilson.phonebook.ui.activity.ConstsActivities.KEYSTUDENT;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
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
    private List<Student> studentlist;
    private ArrayAdapter<Student> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_list);

        configFabNewStudent();
        configList();

        dao.save(new Student("Adilson", "11111111111", "adilson@gmail.com"));
        dao.save(new Student("lu", "2222222222222", "lu@gmail.com"));
        dao.save(new Student("Gal", "3333333333333", "Gal@gmail.com"));


    }

    private void configFabNewStudent() {
        buttonNewStudent = findViewById(R.id.activity_student_list_fab_newStudent);
        buttonNewStudent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openFormModeInsertStudent();
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        adapter.clear();
        adapter.addAll(dao.every());
    }

    private void openFormModeInsertStudent() {
        startActivity(new Intent(this, FormStudentActivity.class));
    }

    private void configList() {
        listView = findViewById(R.id.activity_student_list_Listview);
        adapterConfig();
        getPositionStudentOnList();
        removeItemFromList();
    }

    private void removeItemFromList() {
        listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int position, long id) {
                Student studentSelect = (Student) adapterView.getItemAtPosition(position);
                dao.remove(studentSelect);
                adapter.remove(studentSelect);
                return true;
            }
        });
    }

    private void adapterConfig() {
        adapter = new ArrayAdapter<>(ListStudentActivity.this,
                android.R.layout.simple_list_item_1);
        listView.setAdapter(adapter);
    }

    private void getPositionStudentOnList() {

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
                Student studentSelect = (Student) adapterView.getItemAtPosition(position);

                openFormModeEditStudent(studentSelect);
            }
        });
    }

    private void openFormModeEditStudent(Student studentSelected) {
        Intent goToFormStudentActivity = new Intent(ListStudentActivity.this, FormStudentActivity.class);
        goToFormStudentActivity.putExtra(KEYSTUDENT, studentSelected); //Student Class need be Serializable
        startActivity(goToFormStudentActivity);
    }
}

