package edu.ncsu.csc216.service_wolf.model.incident;

import java.util.ArrayList;

import edu.ncsu.csc216.service_wolf.model.command.Command;

/**
 * The Incident class represents the incident object and all of its different states and handles all the incident information as well as handling 
 * different commands for different states
 * @author rsthoma5
 *
 */
public class Incident {

	/**The name for the new state **/
	public static final String NEW_NAME = "New";
	/**The name for the in progress state **/
	public static final String IN_PROGRESS_NAME = "In Progress";
	/**The name for the on hold state **/
	public static final String ON_HOLD_NAME = "On Hold";
	/**The name for the resolved state **/
	public static final String RESOLVED_NAME = "Resolved";
	/**The name for the cancel state **/
	public static final String CANCELED_NAME = "Canceled";
	/**The details for when incident is awaiting caller**/
	public static final String HOLD_AWAITING_CALLER = "Awaiting Caller";
	/**The details for when incident is awaiting change**/
	public static final String HOLD_AWAITING_CHANGE = "Awaiting Change";
	/**The details for when incident is awaiting vendor**/
	public static final String HOLD_AWAITING_VENDOR = "Awaiting Vendor";
	/**The details for when an incident is permanently solved**/
	public static final String RESOLUTION_PERMANENTLY_SOLVED = "Permanently Solved";
	/**The details for when an incident has been worked around**/
	public static final String RESOLUTION_WORKAROUND = "Workaround";
	/**The details for when an incident is resolved by the caller**/
	public static final String RESOLUTION_CALLER_CLOSED = "Caller Closed";
	/**The details for when an incident has been cancelled for being a duplicate**/
	public static final String CANCELLATION_DUPLICATE = "Duplicate";
	/**The details for when an incident is cancelled for being unnecessary**/
	public static final String CANCELLATION_UNNECESSARY = "Unnecessary";
	/**The details for when an incident is cancelled for not being an incident**/
	public static final String CANCELLATION_NOT_AN_INCIDENT = "Not an Incident";
	/**The details for when an incident is cancelled by the caller**/
	public static final String CANCELLATION_CALLER_CANCELLED = "Caller Cancelled";
	/**Represents an unowned incident**/
	public static final String UNOWNED = "Unowned";
	/**Represents an incident with no status**/
	public static final String NO_STATUS = "No Status";
	/**The unique id for an incident**/
	private int incidentId;
	/**The current state for an incident**/
	private IncidentState currentState;
	/**The title for the incident**/
	private String title;
	/**The caller of the incident**/
	private String caller;
	/**The number of times the incident has been reopened**/
	private int reopenCount;
	/**The name of the owner of the incident**/
	private String owner;
	/**The status details for the incident**/
	private String statusDetails;
	/**The incident log for the incident**/
	private ArrayList<String> incidentLog = new ArrayList<String>();
	/**The number instances, used to create incident id**/
	private static int counter = 1;
	/**
	 * Adds one to counter every time an incident is created
	 */
	public static void incrementCounter() {
		counter++;
	}
	/**
	 * Sets the value for counter
	 * @param c the value to set for counter
	 */
	public static void setCounter(int c) {
		counter = c;
	}
	/**Constant instance for incident in new state**/
	private final NewState newState = new NewState();
	/**Constant instance for incident in progress state**/
	private final InProgressState inProgressState = new InProgressState();
	/**Constant instance for incident in on hold state**/
	private final OnHoldState onHoldState = new OnHoldState();
	/**Constant instance for incident in resolved state**/
	private final ResolvedState resolvedState = new ResolvedState();
	/**Constant instance for incident in cancelled state**/
	private final CanceledState canceledState = new CanceledState();
	/**
	 * The interface for designating states to incidents
	 * @author rsthoma5
	 *
	 */
	private interface IncidentState {
		/**
		 * Designates an incident to its proper handling of the given command based on its current state
		 * @param command the command to apply to the incident
		 */
		void updateState(Command command);
		/**
		 * Gets the name of the state the incident is currently in
		 * @return the currentState of the incident
		 */
		String getStateName();
	}
	/**
	 * Represents an incident in its new state
	 * @author rsthoma5
	 *
	 */
	private class NewState implements IncidentState {
		/**
		 * Updates the incidents current state based on the command and functionality of a new incident
		 * @param command the command to apply to the new incident
		 */
		public void updateState(Command command) {
			if(Command.CommandValue.ASSIGN.equals(command.getCommand())) {
				setOwner(command.getCommandInformation());
				setState(IN_PROGRESS_NAME);
				addMessageToIncidentLog(command.getCommandMessage());
			}
			else if(Command.CommandValue.CANCEL.equals(command.getCommand())) {
				setStatusDetails(command.getCommandInformation());
				setState(CANCELED_NAME);
				addMessageToIncidentLog(command.getCommandMessage());
			}
			else {
				throw new UnsupportedOperationException();
			}
		}
		/**
		 * Gets the state the incident is currently in
		 * @return "New"
		 */
		public String getStateName() {
			return NEW_NAME;
		}
	}
	/**
	 * Represents an incident in its in progress state
	 * @author rsthoma5
	 *
	 */
	private class InProgressState implements IncidentState {
		/**
		 * Updates the state of the incident based on the given command and functionality of the in progress incident
		 * @param command the command to apply to the in progress incident
		 */
		public void updateState(Command command) {
			if(Command.CommandValue.ASSIGN.equals(command.getCommand())) {
				setOwner(command.getCommandInformation());
				addMessageToIncidentLog(command.getCommandMessage());
			}
			else if(Command.CommandValue.HOLD.equals(command.getCommand())) {
				setStatusDetails(command.getCommandInformation());
				setState(ON_HOLD_NAME);
				addMessageToIncidentLog(command.getCommandMessage());
			}
			else if(Command.CommandValue.RESOLVE.equals(command.getCommand())) {
				setStatusDetails(command.getCommandInformation());
				setState(RESOLVED_NAME);
				addMessageToIncidentLog(command.getCommandMessage());
			}
			else if(Command.CommandValue.CANCEL.equals(command.getCommand())) {
				setStatusDetails(command.getCommandInformation());
				setOwner(UNOWNED);
				setState(CANCELED_NAME);
				addMessageToIncidentLog(command.getCommandMessage());
			}
			else {
				throw new UnsupportedOperationException();
			}
		}
		/**
		 * Gets the state the incident is currently in
		 * @return "In progress"
		 */
		public String getStateName() {
			return IN_PROGRESS_NAME;
		}
	}
	/**
	 * Represents an incident in its on hold state
	 * @author rsthoma5
	 *
	 */
	private class OnHoldState implements IncidentState {
		/**
		 * Updates the incidents current state based on the command and functionality of an on hold incident
		 * @param command the command to apply to the on hold incident
		 */
		public void updateState(Command command) {
			if(Command.CommandValue.INVESTIGATE.equals(command.getCommand())) {
				setStatusDetails(NO_STATUS);
				setState(IN_PROGRESS_NAME);
				addMessageToIncidentLog(command.getCommandMessage());
			}
			else {
				throw new UnsupportedOperationException();
			}
		}
		/**
		 * Gets the state the incident is currently in
		 * @return "On Hold"
		 */
		public String getStateName() {
			return ON_HOLD_NAME;
		}
	}
	/**
	 * Represents an incident in its resolved state
	 * @author rsthoma5
	 *
	 */
	private class ResolvedState implements IncidentState {
		/**
		 * Updates the incidents current state based on the command and functionality of a resolved incident
		 * @param command the command to apply to the resolved incident
		 */
		public void updateState(Command command) {
			if(Command.CommandValue.REOPEN.equals(command.getCommand())) {
				setStatusDetails(NO_STATUS);
				setState(IN_PROGRESS_NAME);
				addMessageToIncidentLog(command.getCommandMessage());
				reopenCount++;
			}
			else if(Command.CommandValue.CANCEL.equals(command.getCommand())) {
				setOwner(UNOWNED);
				setStatusDetails(command.getCommandInformation());
				setState(CANCELED_NAME);
				addMessageToIncidentLog(command.getCommandMessage());
			}
			else {
				throw new UnsupportedOperationException();
			}
		}
		/**
		 * Gets the state the incident is currently in
		 * @return "Resolved"
		 */
		public String getStateName() {
			return RESOLVED_NAME;
		}
	}
	/**
	 * Represents an incident in its canceled state
	 * @author rsthoma5
	 *
	 */
	private class CanceledState implements IncidentState {
		/**
		 * Updates the incidents current state based on the command and functionality of a canceled incident
		 * @param command the command to apply to the canceled incident
		 */
		public void updateState(Command command) {
			throw new UnsupportedOperationException();
		}
		/**
		 * Gets the state the incident is currently in
		 * @return "Canceled"
		 */
		public String getStateName() {
			return CANCELED_NAME;
		}
	}
	/**
	 * Constructs an unknown incident in the new state with no status, the given title, caller, and message. 
	 * @param title the title to set
	 * @param caller the caller to set
	 * @param message the message to add, will also go into the incident log
	 */
	public Incident(String title, String caller, String message) {
		setTitle(title);
		setCaller(caller);
		addMessageToIncidentLog(message);
		setId(counter);
		setOwner(UNOWNED);
		setStatusDetails(NO_STATUS);
		setReopenCount(0);
		setState(NEW_NAME);
	}
	/**
	 * Constructs an incident with the given id, state, title, caller, reopen count, owner, status details, and incident log
	 * @param id the id to set
	 * @param state the state to set
	 * @param title the title to set
	 * @param caller the caller to set
	 * @param reopenCount the reopen count to set
	 * @param owner the owner to set
	 * @param statusDetails the status details to set
	 * @param incidentLog the incident log to set
	 */
	public Incident(int id, String state, String title, String caller, int reopenCount, String owner, String statusDetails, ArrayList<String> incidentLog) {
		setId(id);
		setTitle(title);
		setCaller(caller);
		setReopenCount(reopenCount);
		setOwner(owner);
		setStatusDetails(statusDetails);
		if(incidentLog.size() == 0 || incidentLog == null) {
			throw new IllegalArgumentException("Incident cannot be created");
		}
		for(int i = 0; i < incidentLog.size(); i++) {
			addMessageToIncidentLog(incidentLog.get(i));
		}
		setState(state);
	}
	/**
	 * Gets the incident's id
	 * @return the id of the incident
	 */
	public int getId() {
		return incidentId;
	}
	/**
	 * Sets the id to the given number
	 * @param id the id to set
	 * @throws IllegalArgumentException if id is less than or equal to 0
	 */
	private void setId(int id) {
		if(id <= 0) {
			throw new IllegalArgumentException("Incident cannot be created");
		}
		if(id > counter) {
			setCounter(id);
		}
		this.incidentId = id;
		incrementCounter();
	}
	/**
	 * Gets the incident's current state
	 * @return the current state of the incident
	 */
	public String getState() {
		return currentState.getStateName();
	}
	/**
	 * Sets the incident's current state to the given state
	 * @param state the state to set
	 * @throws IllegalArgumentException if the state is not valid for the other parameters of the incident
	 */
	private void setState(String state) {
		if(NEW_NAME.equals(state) && UNOWNED.equals(owner) && NO_STATUS.equals(statusDetails)) {
			currentState = newState;
		}
		else if(IN_PROGRESS_NAME.equals(state) && !UNOWNED.equals(owner) && NO_STATUS.equals(statusDetails)) {
			currentState = inProgressState;
		}
		else if(ON_HOLD_NAME.equals(state) && !UNOWNED.equals(owner) && (HOLD_AWAITING_CALLER.equals(statusDetails) || 
				HOLD_AWAITING_CHANGE.equals(statusDetails) || HOLD_AWAITING_VENDOR.equals(statusDetails))) {
			currentState = onHoldState;
		}
		else if(RESOLVED_NAME.equals(state) && !UNOWNED.equals(owner) && (RESOLUTION_PERMANENTLY_SOLVED.equals(statusDetails) || 
				RESOLUTION_WORKAROUND.equals(statusDetails) || RESOLUTION_CALLER_CLOSED.equals(statusDetails))) {
			currentState = resolvedState;
		}
		else if(CANCELED_NAME.equals(state) && UNOWNED.equals(owner) && (CANCELLATION_DUPLICATE.equals(statusDetails) || 
				CANCELLATION_UNNECESSARY.equals(statusDetails) || CANCELLATION_NOT_AN_INCIDENT.equals(statusDetails) || CANCELLATION_CALLER_CANCELLED.equals(statusDetails))) {
			currentState = canceledState;
		}
		else if(currentState == null){
			throw new IllegalArgumentException("Incident cannot be created");
		}
		else {
			throw new IllegalArgumentException("State could not be updated from " + currentState.getStateName());
		}
	}
	/**
	 * Gets the incident's title
	 * @return the title of the incident
	 */
	public String getTitle() {
		return title;
	}
	/**
	 * Sets the incident's title to the given title
	 * @param title the title to set
	 * @throws IllegalArgumentException if title is null or empty
	 */
	private void setTitle(String title) {
		if(title == null || "".equals(title)) {
			throw new IllegalArgumentException("Incident cannot be created");
		}
		this.title = title;
	}
	/**
	 * Gets the incident's caller
	 * @return the caller of the incident
	 */
	public String getCaller() {
		return caller;
	}
	/**
	 * Sets the incident's caller to the given caller
	 * @param caller the caller to set
	 * @throws IllegalArgumentException if caller is null or empty
	 */
	private void setCaller(String caller) {
		if(caller == null || "".equals(caller)) {
			throw new IllegalArgumentException("Incident cannot be created");
		}
		this.caller = caller;
	}
	/**
	 * Gets the incident's reopen count
	 * @return the reopen count of the incident
	 */
	public int getReopenCount() {
		return reopenCount;
	}
	/**
	 * Sets the incident's reopen count to the given reopen count
	 * @param count the reopen count to set
	 * @throws IllegalArgumentException if count is less than 0
	 */
	private void setReopenCount(int count) {
		if(count < 0) {
			throw new IllegalArgumentException("Incident cannot be created");
		}
		reopenCount = count;
	}
	/**
	 * Gets the incident's owner
	 * @return the owner of the incident
	 */
	public String getOwner() {
		return owner;
	}
	/**
	 * Sets the incident's owner to the given owner
	 * @param owner the owner to set
	 * @throws IllegalArgumentException if owner is null or empty
	 */
	private void setOwner(String owner) {
		if(owner == null || "".equals(owner)) {
			throw new IllegalArgumentException("Incident cannot be created");
		}
		this.owner = owner;
	}
	/**
	 * Gets the incidents status details
	 * @return the status details to set
	 */
	public String getStatusDetails() {
		return statusDetails;
	}
	/**
	 * Sets the incident's status details to the given status details
	 * @param details the status details to set
	 * @throws IllegalArgumentException if details is null or empty
	 */
	private void setStatusDetails(String details) {
		if(details == null || "".equals(details)) {
			throw new IllegalArgumentException("Incident cannot be created");
		}
		statusDetails = details;
	}
	/**
	 * Adds a given message to the incident's message log
	 * @param message the message to add 
	 * @return the index of the new message in the incident's message log
	 * @throws IllegalArgumentException if message is null or empty
	 */
	private int addMessageToIncidentLog(String message) {
		if(message == null || "".equals(message)) {
			throw new IllegalArgumentException("Incident cannot be created");
		}
		incidentLog.add(message);
		return incidentLog.size() - 1;
	}
	/**
	 * Gets the incident's log messages
	 * @return the log messages of the incident
	 */
	public String getIncidentLogMessages() {
		String rstring = "";
		for(int i = 0; i < incidentLog.size(); i++) {
			rstring += "- " + incidentLog.get(i) + "\n";
		}
		return rstring;
	}
	/**
	 * Returns the string representation of the incident, formatted as they are read into a file
	 */
	@Override
	public String toString() {
		return "* " + incidentId + "," + currentState.getStateName() + "," + title + "," + caller + "," + reopenCount + "," + owner + "," + statusDetails + "\n" + getIncidentLogMessages();
	}
	/**
	 * Updates the incident based on the current state and the given command
	 * @param command the action to perform on the incident
	 * @throws UnsupportedOperationException when the command cannot be performed on the incident in its current state
	 */
	public void update(Command command)throws UnsupportedOperationException {
		currentState.updateState(command);
	}
}