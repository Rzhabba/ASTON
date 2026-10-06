import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;
import static org.junit.jupiter.api.Assertions.*;

public class Block1Test {

    @Test
    @DisplayName("Конструктор создаёт копию MutableCHECK")
    void constructorCreatesDefensiveCopy() {
        // Вводим данные изменяемого класса
        MutableCHECK originalStatus = new MutableCHECK(100, "ON");

        // Вводим данные неизменяемой части
        ImmutableCHECK check = new ImmutableCHECK(1, "Peak", 30, originalStatus);

        // Изменяем оригинал
        originalStatus.setCount(999);
        originalStatus.setMutabilityStatusCheck("OFF");

        // Проверка
        assertEquals(100, check.getStatus2().getCount(),
                "Изменение оригинала не должно влиять на ImmutableCHECK");
        assertEquals("ON", check.getStatus2().getMutabilityStatusCheck(),
                "Изменение оригинала не должно влиять на ImmutableCHECK");
    }

    @Test
    @DisplayName("getStatus2 возвращает копию, а не оригинал")
    void getStatus2ReturnsDefensiveCopy() {
        MutableCHECK status = new MutableCHECK(50, "ACTIVE");
        ImmutableCHECK check = new ImmutableCHECK(1, "Test", 10, status);

        // меняем статус
        MutableCHECK retrievedStatus = check.getStatus2();
        retrievedStatus.setCount(777);

        // Assert - проверяем, что внутреннее состояние не изменилось
        assertEquals(50, check.getStatus2().getCount(),
                "getCount() должен возвращать копию, изменения не должны сохраняться");
    }

    @Test
    @DisplayName("Геттеры возвращают корректные значения")
    void gettersReturnCorrectValues() {
        // заполняем
        MutableCHECK status = new MutableCHECK(100, "ON");
        ImmutableCHECK check = new ImmutableCHECK(42, "Product", 25, status);

        // Проверяем по отдельным геттерам
        assertEquals(42, check.getId());
        assertEquals("Product", check.getNaming());
        assertEquals(25, check.getAmount());
        assertEquals(100, check.getStatus2().getCount());
        assertEquals("ON", check.getStatus2().getMutabilityStatusCheck());
    }

    @Test
    @DisplayName("toString() не выбрасывает исключений")
    void toStringWorksCorrectly() {
        MutableCHECK status = new MutableCHECK(10, "TEST");
        ImmutableCHECK check = new ImmutableCHECK(1, "Item", 5, status);

        String result = check.toString();

        assertNotNull(result);
        assertTrue(result.contains("ID:1"));
        assertTrue(result.contains("Наименование=Item"));
        assertTrue(result.contains("Количество=5"));
    }

    @Test
    @DisplayName("Конструктор принимает null для MutableCHECK")
    void constructorHandlesNullStatus() {
        ImmutableCHECK check = new ImmutableCHECK(1, "Test", 10, null);

        assertNotNull(check);
        assertNull(check.getStatus2());
    }

    @Test
    @DisplayName("Независимость копий - полное тестирование")
    void completeImmutabilityTest() {
        // Создаём оригинал
        MutableCHECK original = new MutableCHECK(100, "INITIAL");

        // Создаём immutable1
        ImmutableCHECK immutable1 = new ImmutableCHECK(1, "First", 50, original);

        // Изменяем оригинал
        original.setCount(200);
        original.setMutabilityStatusCheck("CHANGED");

        // Проверяем, что immutable1 не изменился
        assertEquals(100, immutable1.getStatus2().getCount(),
                "immutable1 должен сохранить исходное значение");
        assertEquals("INITIAL", immutable1.getStatus2().getMutabilityStatusCheck(),
                "immutable1 должен сохранить исходный статус");

        // Получаем статус из immutable1 (новая копия)
        MutableCHECK statusFromFirst = immutable1.getStatus2();

        // Создаём immutable2 из этой копии
        ImmutableCHECK immutable2 = new ImmutableCHECK(2, "Second", 75, statusFromFirst);

        // Изменяем statusFromFirst после создания immutable2
        statusFromFirst.setCount(999);
        statusFromFirst.setMutabilityStatusCheck("MODIFIED");

        // Проверяем, что immutable1 не изменился
        assertEquals(100, immutable1.getStatus2().getCount(),
                "immutable1 должен остаться неизменным");

        // Проверяем, что immutable2 НЕ изменился
        assertEquals(100, immutable2.getStatus2().getCount(),
                "immutable2 получил копию в момент создания, последующие изменения не влияют");
        assertEquals("INITIAL", immutable2.getStatus2().getMutabilityStatusCheck(),
                "immutable2 должен сохранить статус на момент создания");

        // Проверяем, что statusFromFirst действительно изменился
        assertEquals(999, statusFromFirst.getCount(),
                "statusFromFirst должен быть изменён");
    }
}