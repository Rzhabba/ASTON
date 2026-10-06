public class MutableCHECK {
    private int count; //просто ещё один изменяемый параметр для наглядности
    private String mutabilityStatusCheck; //проверяем возможность изменения

    //конструктор
    public MutableCHECK(int count, String mutabilityStatusCheck){
        this.count= count;
        this.mutabilityStatusCheck= mutabilityStatusCheck;
    }
//в изменяемом классе и геттеры и сеттеры
    public void setCount(int count){
        this.count= count;
    }
    public void setMutabilityStatusCheck(String mutabilityStatusCheck){
        this.mutabilityStatusCheck= mutabilityStatusCheck;
    }

    public int getCount(){
        return count;
    }

    public String getMutabilityStatusCheck(){
        return mutabilityStatusCheck;
    }

    @Override //для переопределения строки каждый раз
    public String toString(){
        return "   MutabilityStatus: counter: "+ count + "   status  "+ mutabilityStatusCheck;
    }
}
