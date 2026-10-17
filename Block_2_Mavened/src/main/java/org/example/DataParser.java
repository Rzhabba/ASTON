package org.example;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken; //Вспомогательный класс от Gson для передачи информации
//о типе коллекции, что в List должен быть именно Student

import java.io.InputStreamReader;
import java.lang.reflect.Type;
import java.util.List;

public class DataParser {
    public static List<Student> dataParse() {
        Gson gson = new Gson();
        //через try, для оптимизации процесса, try сам закроет reader как закончит
        try (InputStreamReader reader = new InputStreamReader(
                DataParser.class.getClassLoader().getResourceAsStream("BookBase.json"))) {
            //Создадим объект Type, который описывает List<Student>
            //отдельнйы анонимный подкласс TypeToken,
            //который закрепляет факт обращения именно к СТУДЕНТАМ, а не просто к листу
            //{} не ошибка. По такой логике работает правильно, надеюсь, логика верна
            Type type = new TypeToken<List<Student>>() {}.getType();
            return gson.fromJson(reader, type); //файл из ридера разобъется на объекты
            //Student и Book, по truename и всей инфе по книге

        } catch (Exception e) {
            //ловим любое исключение. Удобно. Если поймалось - вернуть null и вывести ошибку в консоль
            e.printStackTrace();
            return null;
        }
    }
}