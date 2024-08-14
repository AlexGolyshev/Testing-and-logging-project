import org.junit.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;

import static junit.framework.TestCase.assertEquals;
import static org.junit.Assert.assertThrows;
import static org.junit.jupiter.api.Assertions.assertSame;


@ExtendWith(MockitoExtension.class)
public class HippodromeTest {
    @Test
    public void NullHorsesExceptionMessage() {
        Throwable exception = assertThrows(
                IllegalArgumentException.class,
                () -> new Hippodrome (new ArrayList<>())
        );
        assertEquals("Horses cannot be null.", exception.getMessage());
    }

    @Test
    public void EmptyHorsesExceptionMessage() {
        Throwable exception = assertThrows(
                IllegalArgumentException.class,
                () -> new Hippodrome(new ArrayList<>() {
                })
        );
        assertEquals("Horses cannot be empty.", exception.getMessage());
    }

    @Test
    public void getSameHorses() {
        List<Horse> horsesOrigin = new ArrayList<>();
        horsesOrigin.add(new Horse("Horse", 1));
        horsesOrigin.add(new Horse("Stud", 3));
        horsesOrigin.add(new Horse("Mare", 2, 1));
        horsesOrigin.add(new Horse("Horsie", 4));
        horsesOrigin.add(new Horse("Studie", 2, 2));
        horsesOrigin.add(new Horse("Marie", 2, 2));
        horsesOrigin.add(new Horse("Hurricane", 1, 5));
        horsesOrigin.add(new Horse("Tornado", 3, 1));
        horsesOrigin.add(new Horse("Typhoon", 1, 1));
        horsesOrigin.add(new Horse("Simoon", 1));
        horsesOrigin.add(new Horse("Blizzard", 3, 3));
        horsesOrigin.add(new Horse("Tempest", 2, 4));
        horsesOrigin.add(new Horse("Blaze", 1, 2));
        horsesOrigin.add(new Horse("Lightning", 3, 5));
        horsesOrigin.add(new Horse("Bolt", 3, 2));
        horsesOrigin.add(new Horse("Spark", 1, 2));
        horsesOrigin.add(new Horse("Flash", 5, 5));
        horsesOrigin.add(new Horse("Whoosh", 3, 4));
        horsesOrigin.add(new Horse("Gust", 4, 4));
        horsesOrigin.add(new Horse("Fling", 3));
        horsesOrigin.add(new Horse("Frisk", 2, 2));
        horsesOrigin.add(new Horse("Charge", 2, 4));
        horsesOrigin.add(new Horse("Dash", 3, 3));
        horsesOrigin.add(new Horse("Leap", 4, 2));
        horsesOrigin.add(new Horse("Flip", 2, 4));
        horsesOrigin.add(new Horse("Skip", 3, 5));
        horsesOrigin.add(new Horse("Vault", 2, 4));
        horsesOrigin.add(new Horse("Hop", 2, 6));
        horsesOrigin.add(new Horse("Flop", 3, 3));
        horsesOrigin.add(new Horse("Kite", 5, 2));

        Hippodrome hippodrome = new Hippodrome(horsesOrigin);
        assertEquals(horsesOrigin, hippodrome.getHorses());
    }

    @Test
    public void moveEveryHorse() {
        List<Horse> mockedHorses = new ArrayList<>();

        for (int i = 0; i < 50; i++) {
            mockedHorses.add(Mockito.mock(Horse.class));
        }
        Hippodrome hippodrome = new Hippodrome(mockedHorses);

        hippodrome.move();

        for (Horse horse : hippodrome.getHorses()) {
            Mockito.verify(horse).move();
        }
    }

    @Test
    public void getRealWinner() {

        Horse first = new Horse("Goner", 1, 1);
        Horse second = new Horse("Walker", 1, 3);
        Horse third = new Horse("Toddler", 1, 2);
        Hippodrome hippodrome = new Hippodrome(List.of(first, second, third));

        assertSame(second, hippodrome.getWinner());
    }
}
