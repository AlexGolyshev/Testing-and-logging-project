import org.junit.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.mockito.MockedStatic;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.lang.reflect.Field;

import static junit.framework.TestCase.assertEquals;
import static org.junit.Assert.assertThrows;

@ExtendWith(MockitoExtension.class)
public class HorseTest {
    @Test
    public void nullNameExceptionMessage() {
        double speed = 2.5;
        double distance = 0;

        Throwable exception = assertThrows(
                IllegalArgumentException.class,
                () -> new Horse (null, speed, distance)
        );
        assertEquals("Name cannot be null.", exception.getMessage());
    }

    @ParameterizedTest
    @ValueSource(strings = {"", "   ", "\t\t", "\n\n\n\n\n"})
    public void blankNameExceptionMessage(String name) {
        double speed = 2.5;
        double distance = 0;

        Throwable exception = assertThrows(
                IllegalArgumentException.class,
                () -> new Horse (name, speed, distance)
        );
        assertEquals("Name cannot be blank.", exception.getMessage());
    }

    @Test
    public void negativeSpeed() {
        String name = "name";
        double distance = 0;

        Throwable exception = assertThrows(
                IllegalArgumentException.class,
                () -> {
                    new Horse (name, -2, distance);
                }
        );
        assertEquals( "Speed cannot be negative.", exception.getMessage());
    }

    @Test
    public void negativeDistance() {
        String name = "name";
        double speed = 2.5;

        Throwable exception = assertThrows(
                IllegalArgumentException.class,
                () -> {
                    new Horse (name, speed, -3);
                }
        );
        assertEquals( "Distance cannot be negative.", exception.getMessage());
    }

    @Test
    public void getName() throws NoSuchFieldException, IllegalAccessException {
        Horse horse = new Horse("horse", 2);

        Field name = Horse.class.getDeclaredField("name");
        name.setAccessible(true);
        String nameValue = (String) name.get(horse);
        assertEquals("horse", nameValue);
    }

    @Test
    public void getSpeed() {
        Horse horse = new Horse("horse", 2.0, 1.0);
        assertEquals(2.0, horse.getSpeed());
    }

    @Test
    public void getDistance() {
        Horse horse = new Horse("horse", 2.0, 1.0);
        assertEquals(1.0, horse.getDistance());
    }

    @Test
    public void distanceZeroIfNoParam() {
        Horse horse = new Horse("horse", 2);
        assertEquals(0.0, horse.getDistance());
    }

    @Test
    public void moveInvokesGetRandom() {
        try (MockedStatic<Horse> mockedHorse = Mockito.mockStatic(Horse.class)) {
            new Horse("horse", 2, 1).move();
            mockedHorse.verify(() -> Horse.getRandomDouble(0.2, 0.9));
        }
    }

    @ParameterizedTest
    @ValueSource(doubles = {0.3, 0.4, 0.5})
    void moveAssignsValue(double arg) {
        try (MockedStatic<Horse> mockedHorse = Mockito.mockStatic(Horse.class)) {
            Horse horse = new Horse("horse", 3, 1);
            mockedHorse.when(() -> Horse.getRandomDouble(0.2, 0.9)).thenReturn(arg);

            horse.move();

            assertEquals(1 + 3 * arg, horse.getDistance());
        }
    }
}
