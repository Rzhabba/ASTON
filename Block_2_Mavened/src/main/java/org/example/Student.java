package org.example;

import java.util.List;

public class Student {
    public String truename;
    public List<Book> books;

    //переопределяем toString
    @Override
    public String toString() {
        return "Студент: Имя: " + truename + ", Книги: " + books;
    }
}