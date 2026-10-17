package org.example;

import java.util.Objects;

public class Book {
    public String title;
    public String year; //чтоб не бесилось ничего, год и страницы оставить строками
    public String pages;

    @Override
    public String toString() {
        return "Книга: Название: " + title +
                ", Количество страниц: " + pages +
                ", Год выпуска: " + year;
    }

    @Override
    //без equals distinct() вернет повторяющуюся книгу оба раза,
    //т.к. по ссылкам они будут являться разными объектами
    public boolean equals(Object o) {
        //если сравниваем объект с самим собой
        if (this == o)
            return true;
        //если объект нулевой, или объект другого класса
        if (o == null || getClass() != o.getClass())
            return false;
        Book book = (Book) o;
        //возвращаем если совпадает по всем полям
        return Objects.equals(title, book.title) &&
                Objects.equals(year, book.year) &&
                Objects.equals(pages, book.pages);
    }

    @Override
    //гугл говорит, если переопределить equals- надо и hashcode,
    //для корректной работы HashMap и hashset
    public int hashCode() {
        return Objects.hash(title, pages, year);
    }
}