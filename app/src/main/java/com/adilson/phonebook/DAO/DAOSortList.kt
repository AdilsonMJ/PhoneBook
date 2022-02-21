package com.adilson.phonebook.DAO

import com.adilson.phonebook.model.Student


class DAOSortList {

    companion object {
        @JvmStatic
        fun sorteListStudent(studentList: List<Student>): List< Student> {
            var sortedList =  studentList.sortedWith(compareBy({it.name}))
            return sortedList
        }
    }


}
