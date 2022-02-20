package com.adilson.phonebook.ui.activity;

import static com.adilson.phonebook.ui.activity.ConstsActivities.KEYSTUDENT;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.adilson.phonebook.DAO.StudentDAO;
import com.adilson.phonebook.R;
import com.adilson.phonebook.model.Student;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class ListStudentActivity extends AppCompatActivity {

    private FloatingActionButton buttonNewStudent;
    private ListView listView;
    private final StudentDAO dao = new StudentDAO();
    private ArrayAdapter<Student> adapter;
    private Student studentSelect;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_list);



        configFabNewStudent();
        configList();

        dao.save(new Student("Teste0", "11111111111", "teste0@gmail.com"));
        dao.save(new Student("Teste1", "2222222222222", "teste1@gmail.com"));
        dao.save(new Student("Teste2", "3333333333333", "teste2@gmail.com"));
    }

    @Override
    protected void onResume() {
        super.onResume();
        upLoadStudentList();
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        //menu.add("Remove");
        getMenuInflater().inflate(R.menu.activity_list_students_menu, menu);
    }

    @Override
    public boolean onContextItemSelected(@NonNull MenuItem item) {

        int itemId = item.getItemId();
        AdapterView.AdapterContextMenuInfo menuInfo = (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();
        studentSelect = adapter.getItem(menuInfo.position);

        if (itemId == R.id.activity_list_student_menu_remove){
            removeStudentOfList(studentSelect);
        } else if (itemId == R.id.activity_list_student_menu_edite){
            openFormModeEditStudent();
        }
        return super.onContextItemSelected(item);
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

    private void upLoadStudentList() {
        adapter.clear();
        adapter.addAll(dao.every());
    }

    private void openFormModeInsertStudent() {
        startActivity(new Intent(this, FormStudentActivity.class));
    }

    private void configList() {
        listView = findViewById(R.id.activity_student_list_Listview);
        adapterConfig();
        registerForContextMenu(listView);
    }

    private void removeStudentOfList(Student studentSelect) {
        dao.remove(studentSelect);
        adapter.remove(studentSelect);
    }

    private void adapterConfig() {
        adapter = new ArrayAdapter<>(ListStudentActivity.this,
                android.R.layout.simple_list_item_1);
        listView.setAdapter(adapter);
    }
    
    private void openFormModeEditStudent() {
        Intent goToFormStudentActivity = new Intent(ListStudentActivity.this, FormStudentActivity.class);
        goToFormStudentActivity.putExtra(KEYSTUDENT, studentSelect); //Student Class need be Serializable
        startActivity(goToFormStudentActivity);
    }
}

