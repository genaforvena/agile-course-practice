package ru.unn.agile.MarksAccounting.viewmodel;

import ru.unn.agile.MarksAccounting.model.*;

import javax.swing.*;

public class TestDataInitializer {

    private TestDataInitializer() { }

    public static TableOfMarks initTableOfMarks() {
        TableOfMarks tableOfMarks = new TableOfMarks();
        tableOfMarks.addGroup(new Group("1"));
        tableOfMarks.addGroup(new Group("2"));
        tableOfMarks.addGroup(new Group("3"));
        tableOfMarks.addAcademicSubject(new Group("1"), "Maths");
        tableOfMarks.addAcademicSubject(new Group("1"), "History");
        tableOfMarks.addAcademicSubject(new Group("2"), "Science");
        tableOfMarks.addStudent(new Group("1"), new Student("Sidorov"));
        tableOfMarks.addStudent(new Group("1"), new Student("Petrov"));
        tableOfMarks.addStudent(new Group("3"), new Student("Ivanov"));
        try {
            tableOfMarks.addNewMark(new Mark(4, "Maths", DateParser.parseDate("10-05-2015")),
                    new Student("Sidorov"), new Group("1"));
        } catch (Exception e) {
            return tableOfMarks;
        }
        return tableOfMarks;
    }

    public static ComboBoxModel<String> getAllGroupsInComboBoxModel() {
        String[] groupNumbers = {"1", "2", "3"};
        return new JComboBox<String>(groupNumbers).getModel();
    }
}
