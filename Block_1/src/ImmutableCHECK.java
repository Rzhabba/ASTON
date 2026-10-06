
public final class ImmutableCHECK {
    //в иммутабельном классе все переменные должны быть final
    private final int id;
    private final String naming;
    private final int amount;
    private final MutableCHECK status2;
    //из изменяемого класса достанем статус (2 переменные)

    //конструктор
    public ImmutableCHECK(int id, String naming, int amount, MutableCHECK status2){
        this.id = id;
        this.naming = naming;
        this.amount = amount;
        this.status2 = copyStatus2(status2);
    }

    private MutableCHECK copyStatus2(MutableCHECK original){
        //если класс иммутабельный- взаимодействие должно быть с копией, поэтому
        //статус2 копируем
        if(original== null){
            return null;
        }
        return new MutableCHECK(original.getCount(), original.getMutabilityStatusCheck());
    }
//в иммутабельном классе не должно быть сеттеров- поэтому только геттеры
    public String getNaming(){
        return naming;
    }
    public int getAmount(){
        return amount;
    }
    public int getId(){
        return id;
    }

    public MutableCHECK getStatus2(){
        return copyStatus2(status2);
    }

    public String toString(){
        return "Immutable check\n" + "ID:" + id +
                "   Наименование=" + naming +
                "   Количество=" + amount +
                "   Конец заявки" +
                "   Проверка на изменяемость:  " + status2;
    }
}



