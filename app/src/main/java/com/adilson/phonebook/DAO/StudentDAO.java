package com.adilson.phonebook.DAO;

import androidx.annotation.Nullable;

import com.adilson.phonebook.model.Student;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class StudentDAO {

    private final static List<Student> studentList = new ArrayList<>();
    private static int countID = 1;

    public void save(Student student) {
            student.setID(countID);
            studentList.add(student);
            upDateIdToTheNextUser();
    }

    private void upDateIdToTheNextUser() {
        countID++;
    }

    public void Edit(Student student) {
        Student studentFound = lookingIfStudentExist(student);

        if (studentFound != null) {
            int positionStudent = studentList.indexOf(studentFound);
            studentList.set(positionStudent, student);
        }

    }

    @Nullable
    private Student lookingIfStudentExist(Student student) {

        for (Student s :
                studentList) {
            if (s.getId() == student.getId()) {
                return s;
            }
        }
        return null;
    }

    public List<Student> every() {
        return new ArrayList<>(DAOSortList.sorteListStudent(studentList));
    }

    public void remove(Student studentSelect) {
        Student studentReturn = lookingIfStudentExist(studentSelect);
        if (studentReturn != null){
            studentList.remove(studentReturn);
        }
    }



}
