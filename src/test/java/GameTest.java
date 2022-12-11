import domain.Player;
import manager.Game;
import org.junit.jupiter.api.Test;
import repository.NotRegisteredException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class GameTest {
    Game registeredPlayers = new Game();

    Player player1 = new Player(1, "Kolya", 300);
    Player player2 = new Player(22, "Olya", 100);
    Player player3 = new Player(333, "Anya", 250);
    Player player4 = new Player(4, "Dima", 99);
    Player player5 = new Player(55, "Vanya", 300);

    @Test
    public void compareStrengthPlayersIfBothAreUnregistered() {

        assertThrows(NotRegisteredException.class, () -> {
            registeredPlayers.round(player2.getName(), player3.getName());
        });
    }

    @Test
    public void compareStrengthPlayersIfOneIsUnregistered() {
        registeredPlayers.register(player3);

        assertThrows(NotRegisteredException.class, () -> {
            registeredPlayers.round("player3", "player1");
        });
    }

    @Test
    public void compareStrengthPlayersIfAnotherIsUnregistered() {
        registeredPlayers.register(player1);

        assertThrows(NotRegisteredException.class, () -> {
            registeredPlayers.round("player1", "player2");
        });
    }

    @Test
    public void compareStrengthPlayersIfRegistered() {
        registeredPlayers.register(player1);
        registeredPlayers.register(player2);

        assertThrows(NotRegisteredException.class, () -> {
            registeredPlayers.round("player1", "player2");
        });
    }

    @Test
    public void comparePlayersWhenFirstIsStrongerThanSecond() {
        registeredPlayers.register(player1);
        registeredPlayers.register(player2);

        assertEquals(1, registeredPlayers.round(player1.getName(), player2.getName()));
    }

    @Test
    public void comparePlayersWhenFirstIsWeakerThanSecond() {
        registeredPlayers.register(player4);
        registeredPlayers.register(player5);

        assertEquals(2, registeredPlayers.round(player4.getName(), player5.getName()));
    }

    @Test
    public void comparePlayersOfEqualStrength() {
        registeredPlayers.register(player1);
        registeredPlayers.register(player5);

        assertEquals(0, registeredPlayers.round(player1.getName(), player5.getName()));
    }
}
