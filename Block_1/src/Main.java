
public class Main {
    public static void main(String[] args) {
        //заполняем поля изменяемого класса
        MutableCHECK originalStatus = new MutableCHECK(100, "ON");

        //заполняем поля неизменяемого класса
        ImmutableCHECK check = new ImmutableCHECK(1, "Peak", 30, originalStatus);

        //Внутри неизменяемого класса меняем параметры изменяемого. Ничего не должно произойти
        originalStatus.setCount(2);
        originalStatus.setMutabilityStatusCheck("OFF");


        //В рамках неизменяемого класса ничего не произошло, как и должно быть

        System.out.println(check);
    }
}