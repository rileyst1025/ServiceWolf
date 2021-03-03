package edu.ncsu.csc216.service_wolf.model.command;

import static org.junit.Assert.*;
import org.junit.Test;

/**
 * Tests the Command class construction
 * @author riley
 *
 */
public class CommandTest {

	public static String VALID_MESSAGE = "Message";
	
	public static String VALID_INFO = "Information";
	/**
	 * Tests Command constructor for all command values and with valid and invalid paramters
	 */
	@Test
	public void testCommand() {
		try {
			Command c = new Command(Command.CommandValue.ASSIGN, VALID_INFO, VALID_MESSAGE);
			assertEquals(Command.CommandValue.ASSIGN, c.getCommand());
			assertEquals(VALID_INFO, c.getCommandInformation());
			assertEquals(VALID_MESSAGE, c.getCommandMessage());
		} catch (IllegalArgumentException e) {
			fail(e.getMessage());
		}
		try {
			Command c = new Command(Command.CommandValue.HOLD, VALID_INFO, VALID_MESSAGE);
			assertEquals(Command.CommandValue.HOLD, c.getCommand());
			assertEquals(VALID_INFO, c.getCommandInformation());
			assertEquals(VALID_MESSAGE, c.getCommandMessage());
		} catch (IllegalArgumentException e) {
			fail(e.getMessage());
		}
		try {
			Command c = new Command(Command.CommandValue.RESOLVE, VALID_INFO, VALID_MESSAGE);
			assertEquals(Command.CommandValue.RESOLVE, c.getCommand());
			assertEquals(VALID_INFO, c.getCommandInformation());
			assertEquals(VALID_MESSAGE, c.getCommandMessage());
		} catch (IllegalArgumentException e) {
			fail(e.getMessage());
		}
		try {
			Command c = new Command(Command.CommandValue.CANCEL, VALID_INFO, VALID_MESSAGE);
			assertEquals(Command.CommandValue.CANCEL, c.getCommand());
			assertEquals(VALID_INFO, c.getCommandInformation());
			assertEquals(VALID_MESSAGE, c.getCommandMessage());
		} catch (IllegalArgumentException e) {
			fail(e.getMessage());
		}
		try {
			Command c = new Command(Command.CommandValue.INVESTIGATE, null, VALID_MESSAGE);
			assertEquals(Command.CommandValue.INVESTIGATE, c.getCommand());
			assertNull(c.getCommandInformation());
			assertEquals(VALID_MESSAGE, c.getCommandMessage());
		} catch (IllegalArgumentException e) {
			fail(e.getMessage());
		}
		try {
			Command c = new Command(Command.CommandValue.REOPEN, null, VALID_MESSAGE);
			assertEquals(Command.CommandValue.REOPEN, c.getCommand());
			assertNull(c.getCommandInformation());
			assertEquals(VALID_MESSAGE, c.getCommandMessage());
		} catch (IllegalArgumentException e) {
			fail(e.getMessage());
		}
		try {
			Command c = new Command(Command.CommandValue.ASSIGN, null, VALID_MESSAGE);
			fail();
		} catch (IllegalArgumentException e) {
			assertEquals("Invalid information", e.getMessage());
		}
		try {
			Command c = new Command(Command.CommandValue.HOLD, null, VALID_MESSAGE);
			fail();
		} catch (IllegalArgumentException e) {
			assertEquals("Invalid information", e.getMessage());
		}
		try {
			Command c = new Command(Command.CommandValue.RESOLVE, null, VALID_MESSAGE);
			fail();
		} catch (IllegalArgumentException e) {
			assertEquals("Invalid information", e.getMessage());
		}
		try {
			Command c = new Command(Command.CommandValue.CANCEL, null, VALID_MESSAGE);
			fail();
		} catch (IllegalArgumentException e) {
			assertEquals("Invalid information", e.getMessage());
		}
		try {
			Command c = new Command(Command.CommandValue.INVESTIGATE, VALID_INFO, VALID_MESSAGE);
			fail();
		} catch (IllegalArgumentException e) {
			assertEquals("Information should be null", e.getMessage());
		}
		try {
			Command c = new Command(Command.CommandValue.REOPEN, VALID_INFO, VALID_MESSAGE);
			fail();
		} catch (IllegalArgumentException e) {
			assertEquals("Information should be null", e.getMessage());
		}
		try {
			Command c = new Command(Command.CommandValue.ASSIGN, VALID_INFO, null);
			fail();
		} catch (IllegalArgumentException e) {
			assertEquals("Invalid message", e.getMessage());
		}
		try {
			Command c = new Command(null, VALID_INFO, VALID_MESSAGE);
			fail();
		} catch (IllegalArgumentException e) {
			assertEquals("Invalid command value", e.getMessage());
		}
	}

}
