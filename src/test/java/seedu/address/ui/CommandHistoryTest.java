package seedu.address.ui;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;



public class CommandHistoryTest {

    private CommandHistory commandHistory;

    @BeforeEach
    public void setUp() {
        commandHistory = new CommandHistory();
    }

    @Test
    public void testAddCommand() {
        commandHistory.addCommand("first command");
        commandHistory.addCommand("second command");

        // Moving back in history
        assertEquals("second command", commandHistory.getPreviousCommand());
        assertEquals("first command", commandHistory.getPreviousCommand());

        // Should return null as there's no more previous command
        assertNull(commandHistory.getPreviousCommand());
    }

    @Test
    public void testNextCommand() {
        commandHistory.addCommand("first command");
        commandHistory.addCommand("second command");

        // Go back twice to previous commands
        commandHistory.getPreviousCommand();
        commandHistory.getPreviousCommand();

        // Moving forward in history
        assertEquals("second command", commandHistory.getNextCommand());
        assertNull(commandHistory.getNextCommand()); // No further next command after latest
    }

    @Test
    public void testEmptyHistory() {
        // Initially, history should return null when getting previous/next command
        assertNull(commandHistory.getPreviousCommand());
        assertNull(commandHistory.getNextCommand());
    }

    @Test
    public void testMixedNavigation() {
        commandHistory.addCommand("first command");
        commandHistory.addCommand("second command");
        commandHistory.addCommand("third command");

        // Navigate through commands
        assertEquals("third command", commandHistory.getPreviousCommand());
        assertEquals("second command", commandHistory.getPreviousCommand());

        // Moving forward should bring back "third command"
        assertEquals("third command", commandHistory.getNextCommand());

        // After the latest command, there should be no more next command
        assertNull(commandHistory.getNextCommand());
    }
}
