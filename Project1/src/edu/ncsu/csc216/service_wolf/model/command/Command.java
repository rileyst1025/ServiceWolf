package edu.ncsu.csc216.service_wolf.model.command;

/**
 * The Command class encapsulates user requests to change an Incident
 * @author rsthoma5
 *
 */
public class Command {

	/**Holds the type of command**/
	private CommandValue commandValue;
	/**Gives further information about the reason for the command, not needed and set to null for some CommandValues**/
	private String commandInformation;
	/**The reason from the user for the command**/
	private String commandMessage;
	/**Holds 5 values that represent the 6 different types of commands**/
	public enum CommandValue { ASSIGN, HOLD, INVESTIGATE, RESOLVE, REOPEN, CANCEL };
	
	/**
	 * Constructs a new command with the given type of command, message, and information if applicable
	 * @param command the type of command to set, one of 6 values (Assign, Hold, Investigate, Resolve, Reopen, Cancel)
	 * @param commandInformation the information about the users reason for the command, can be null for commands that don't need further information
	 * @param commandMessage the message from the user to set
	 */
	public Command(CommandValue command, String commandInformation, String commandMessage) {
		if(command == null) {
			throw new IllegalArgumentException("Invalid command value");
		}
		else {
			commandValue = command;
		}
		if((commandValue == Command.CommandValue.ASSIGN || commandValue == Command.CommandValue.HOLD || commandValue == Command.CommandValue.RESOLVE ||
				commandValue == Command.CommandValue.CANCEL) &&  commandInformation != null && !"".equals(commandInformation) || 
				(commandValue == Command.CommandValue.INVESTIGATE || commandValue == Command.CommandValue.REOPEN) && commandInformation == null) {
			this.commandInformation = commandInformation;
		}
		else if(commandValue == Command.CommandValue.ASSIGN || commandValue == Command.CommandValue.HOLD || commandValue == Command.CommandValue.RESOLVE ||
				commandValue == Command.CommandValue.CANCEL) {
			throw new IllegalArgumentException("Invalid information");
		}
		else {
			throw new IllegalArgumentException("Information should be null");
		}
		if(commandMessage == null || "".equals(commandMessage)) {
			throw new IllegalArgumentException("Invalid message");
		}
		else {
			this.commandMessage = commandMessage;
		}
	}
	
	/**
	 * Gets the type of command for the command
	 * @return the commandValue for the command
	 */
	public CommandValue getCommand() {
		return commandValue;
	}
	
	/**
	 * Gets the information for the command
	 * @return the commandInformation for the command
	 */
	public String getCommandInformation() {
		return commandInformation;
	}
	
	/**
	 * Gets the user message for the command
	 * @return the commandMessage for the command
	 */
	public String getCommandMessage() {
		return commandMessage;
	}
}
