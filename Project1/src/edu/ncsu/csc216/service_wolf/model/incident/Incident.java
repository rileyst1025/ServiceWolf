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
	public static final String IN_PROGRESS_NAME = "In progress";
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
	public static final String NO_STATUS = "No status";
	/**The unique id for an incident**/
	private int incidentId;
	/**The current state for an incident**/
	private IncidentState currentState;
	/**The title for the incident**/
	private String title;
	/**The caller of the incident**/
	private String caller;
	/**The number of times the incident has been reopened**/
	private String reopenCount;
	/**The name of the owner of the incident**/
	private String owner;
	/**The status details for the incident**/
	private String statusDetails;
	/**The incident log for the incident**/
	private ArrayList<String> incidentLog;
	/**The number instances, used to create incident id**/
	private static int counter = 0;
	/**
	 * Adds one to counter every time an incident is created
	 */
	public static void incrementCounter() {
		
	}
	/**
	 * Sets the value for counter
	 * @param count the value to set for counter
	 */
	public static void setCounter(int c) {
		
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
		public void updateState(Command command);
		/**
		 * Gets the name of the state the incident is currently in
		 * @return the currentState of the incident
		 */
		public String getStateName();
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
			
		}
		/**
		 * Gets the state the incident is currently in
		 * @return "New"
		 */
		public String getStateName() {
			return null;
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
		 * @param commmand the command to apply to the in progress incident
		 */
		public void updateState(Command command) {
			
		}
		/**
		 * Gets the state the incident is currently in
		 * @return "In progress"
		 */
		public String getStateName() {
			return null;
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
			
		}
		/**
		 * Gets the state the incident is currently in
		 * @return "On Hold"
		 */
		public String getStateName() {
			return null;
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
			
		}
		/**
		 * Gets the state the incident is currently in
		 * @return "Resolved"
		 */
		public String getStateName() {
			return null;
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
			
		}
		/**
		 * Gets the state the incident is currently in
		 * @return "Canceled"
		 */
		public String getStateName() {
			return null;
		}
	}
	/**
	 * Constructs an unknown incident in the new state with no status, the given title, caller, and message. 
	 * @param title the title to set
	 * @param caller the caller to set
	 * @param message the message to add, will also go into the incident log
	 */
	public void Incident(String title, String caller, String message) {
		
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
	public void Incident(int id, String state, String title, String caller, int reopenCount, String owner, String statusDetails, ArrayList<String> incidentLog) {
		
	}
	/**
	 * Gets the incident's id
	 * @return the id of the incident
	 */
	public int getId() {
		return 0;
	}
	/**
	 * Sets the id to the given number
	 * @param id the id to set
	 */
	private void setId(int id) {
		
	}
	/**
	 * Gets the incident's current state
	 * @return the current state of the incident
	 */
	public String getState() {
		return null;
	}
	/**
	 * Sets the incident's current state to the given state
	 * @param state the state to set
	 */
	private void setState(String state) {
		
	}
	/**
	 * Gets the incident's title
	 * @return the title of the incident
	 */
	public String getTitle() {
		return null;
	}
	/**
	 * Sets the incident's title to the given title
	 * @param title the title to set
	 */
	private void setTitle(String title) {
		
	}
	/**
	 * Gets the incident's caller
	 * @return the caller of the incident
	 */
	public String getCaller() {
		return null;
	}
	/**
	 * Sets the incident's caller to the given caller
	 * @param caller the caller to set
	 */
	private void setCaller(String caller) {
		
	}
	/**
	 * Gets the incident's reopen count
	 * @return the reopen count of the incident
	 */
	public int getReopenCount() {
		return 0;
	}
	/**
	 * Sets the incident's reopen count to the given reopen count
	 * @param count the reopen count to set
	 */
	private void setReopenCount(int count) {
		
	}
	/**
	 * Gets the incident's owner
	 * @return the owner of the incident
	 */
	public String getOwner() {
		return null;
	}
	/**
	 * Sets the incident's owner to the given owner
	 * @param owner the owner to set
	 */
	private void setOwner(String owner) {
		
	}
	/**
	 * Gets the incidents status details
	 * @return the status details to set
	 */
	public String getStatusDetails() {
		return null;
	}
	/**
	 * Sets the incident's status details to the given status details
	 * @param details the status details to set
	 */
	private void setStatusDetails(String details) {
		
	}
	/**
	 * Adds a given message to the incident's message log
	 * @param message the message to add 
	 * @return the index of the new message in the incident's message log
	 */
	private int addMessageToIncidentLog(String message) {
		return 0;
	}
	/**
	 * Gets the incident's log messages
	 * @return the log messages of the incident
	 */
	public String getIncidentLogMessages() {
		return null;
	}
	/**
	 * Returns the string representation of the incident, formatted as they are read into a file
	 */
	@Override
	public String toString() {
		return null;
	}
	/**
	 * Updates the incident based on the current state and the given command
	 * @param command the action to perform on the incident
	 * @throws UnsupportedOperationException when the command cannot be performed on the incident in its current state
	 */
	public void update(Command command)throws UnsupportedOperationException {
		
	}
}
