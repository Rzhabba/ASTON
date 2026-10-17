package org.example;

import java.util.List;
import java.util.Optional;

public class Main {
    public static void main(String[] args) {

        List<Student> students = DataParser.dataParse();

        if (students == null) { //если DataParser поймал любую ошибку
            System.out.println("Ошибка загрузки данных");
            return;
        }

        Optional<Integer> resultYear = students.stream()
                //Вывести в консоль каждого студента
                .peek(student -> System.out.println(student))
                //Получить для каждого студента список книг
                .map(student -> student.books)
                //Получить книги
                .flatMap(List::stream)
                //Отсортировать книги по количеству страниц
                .sorted((b1, b2) -> Integer.compare(
                        Integer.parseInt(b1.pages),
                        Integer.parseInt(b2.pages)))
                //Оставить только уникальные книги
                .distinct()
                //Отфильтровать книги, оставив только те
                // которые были выпущены после 2000 года
                .filter(book -> Integer.parseInt(book.year) > 2000)
                //Ограничить стрим на 3 элементах
                .limit(3)
                //Получить из книг годы выпуска
                .map(book -> Integer.parseInt(book.year))
                //При помощи методов короткого замыкания
                // вернуть Optional от года
                .findFirst();
        //вывести в консоль год выпуска найденной книги
        System.out.println(resultYear.isPresent()
                ? "Год выпуска найденной книги: " + resultYear.get() //если значение есть - извлекаем
                : "Такая книга отсутствует"); //если нет - нет
    }
}