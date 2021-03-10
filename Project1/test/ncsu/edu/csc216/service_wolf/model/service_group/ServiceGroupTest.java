package ncsu.edu.csc216.service_wolf.model.service_group;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Test;

import edu.ncsu.csc216.service_wolf.model.command.Command;
import edu.ncsu.csc216.service_wolf.model.incident.Incident;
import edu.ncsu.csc216.service_wolf.model.service_group.ServiceGroup;

public class ServiceGroupTest {

	/**Title of test incident**/
	private static final String TITLE = "Moodle down";
	/**Caller of the test incident**/
	private static final String CALLER = "User1";
	/**Message of the test incident**/
	private static final String MESSAGE = "Moodle won't load";
	/**Owner of the test incident**/
	private static final String OWNER = "Moodle Help Team";
	/**Name of test service group**/
	private static final String SERVICE_GROUP_NAME = "Moodle Help Team";
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
	/**
	 * Tests that the service group constructor creates a serviec group with the given name
	 */
	@Test
	public void testServiceGroup() {
		ServiceGroup s = new ServiceGroup(SERVICE_GROUP_NAME);
		assertEquals(SERVICE_GROUP_NAME, s.getServiceGroupName());
		assertEquals(0, s.getIncidents().size());
		try {
			ServiceGroup i = new ServiceGroup(null);
			fail();
		} catch(IllegalArgumentException e) {
			assertEquals("Service group cannot be created", e.getMessage());
		}
		try {
			ServiceGroup i = new ServiceGroup("");
			fail();
		} catch(IllegalArgumentException e) {
			assertEquals("Service group cannot be created", e.getMessage());
		}
	}
	
	/**
	 * Tests that set service group name sets the name correctly
	 */
	@Test
	public void testSetServiceGroupName() {
		ServiceGroup s = new ServiceGroup(SERVICE_GROUP_NAME);
		assertEquals(SERVICE_GROUP_NAME, s.getServiceGroupName());
		s.setServiceGroupName("New name");
		assertEquals("New name", s.getServiceGroupName());
	}

	/**
	 * Tests that addIncident successfully adds a valid incident with the correct id
	 */
	@Test
	public void testAddIncident() {
		ServiceGroup s = new ServiceGroup(SERVICE_GROUP_NAME);
		ArrayList<String> IncidentLog = new ArrayList<String>();
		IncidentLog.add("Moodles down pls help");
		Incident i = new Incident(2, IN_PROGRESS_NAME, TITLE, CALLER, 0, OWNER, NO_STATUS, IncidentLog);
		Incident j = new Incident(1, IN_PROGRESS_NAME, TITLE, CALLER, 0, OWNER, NO_STATUS, IncidentLog);
		Incident k = new Incident(4, IN_PROGRESS_NAME, TITLE, CALLER, 0, OWNER, NO_STATUS, IncidentLog);
		Incident l = new Incident(4, IN_PROGRESS_NAME, TITLE, CALLER, 0, OWNER, NO_STATUS, IncidentLog);
		s.addIncident(i);
		assertEquals(s.getIncidents().size(), 1);
		assertEquals(i, s.getIncidents().get(0));
		s.addIncident(j);
		assertEquals(s.getIncidents().size(), 2);
		assertEquals(j, s.getIncidents().get(0));
		assertEquals(i, s.getIncidents().get(1));
		s.addIncident(k);
		assertEquals(s.getIncidents().size(), 3);
		assertEquals(j, s.getIncidents().get(0));
		assertEquals(i, s.getIncidents().get(1));
		assertEquals(k, s.getIncidents().get(2));
		try {
			s.addIncident(l);
			fail();
		} catch(IllegalArgumentException e) {
			assertEquals("Incident cannot be created", e.getMessage());
		}
	}
	
	/**
	 * Tests that getIncidentById returns the correct incident for the id and null if there is no incident with the id
	 */
	@Test
	public void testGetIncidentById() {
		ServiceGroup s = new ServiceGroup(SERVICE_GROUP_NAME);
		ArrayList<String> IncidentLog = new ArrayList<String>();
		IncidentLog.add("Moodles down pls help");
		Incident i = new Incident(2, IN_PROGRESS_NAME, TITLE, CALLER, 0, OWNER, NO_STATUS, IncidentLog);
		Incident j = new Incident(1, IN_PROGRESS_NAME, TITLE, CALLER, 0, OWNER, NO_STATUS, IncidentLog);
		Incident k = new Incident(4, IN_PROGRESS_NAME, TITLE, CALLER, 0, OWNER, NO_STATUS, IncidentLog);
		s.addIncident(i);
		s.addIncident(j);
		s.addIncident(k);
		assertEquals(i, s.getIncidentById(2));
		assertEquals(j, s.getIncidentById(1));
		assertEquals(k, s.getIncidentById(4));
		assertNull(s.getIncidentById(6));
	}
	
	/**
	 * Tests that deleteIncidentById successfully deletes the incident with the given id
	 */
	@Test
	public void testDeleteIncidentById() {
		ServiceGroup s = new ServiceGroup(SERVICE_GROUP_NAME);
		ArrayList<String> IncidentLog = new ArrayList<String>();
		IncidentLog.add("Moodles down pls help");
		Incident i = new Incident(2, IN_PROGRESS_NAME, TITLE, CALLER, 0, OWNER, NO_STATUS, IncidentLog);
		Incident j = new Incident(1, IN_PROGRESS_NAME, TITLE, CALLER, 0, OWNER, NO_STATUS, IncidentLog);
		Incident k = new Incident(4, IN_PROGRESS_NAME, TITLE, CALLER, 0, OWNER, NO_STATUS, IncidentLog);
		s.addIncident(i);
		s.addIncident(j);
		s.addIncident(k);
		s.deleteIncidentById(1);
		assertEquals(2, s.getIncidents().size());
		assertEquals(i, s.getIncidents().get(0));
		assertEquals(k, s.getIncidents().get(1));
		s.deleteIncidentById(6);
		assertEquals(2, s.getIncidents().size());
		assertEquals(i, s.getIncidents().get(0));
		assertEquals(k, s.getIncidents().get(1));
	}
	
	/**
	 * Tests that execute command executes the correct command on the incident with the given id
	 */
	@Test
	public void testExecuteCommand() {
		ServiceGroup s = new ServiceGroup(SERVICE_GROUP_NAME);
		ArrayList<String> IncidentLog = new ArrayList<String>();
		IncidentLog.add("Moodles down pls help");
		Incident i = new Incident(1, NEW_NAME, TITLE, CALLER, 0, UNOWNED, NO_STATUS, IncidentLog);
		s.addIncident(i);
		Command assign = new Command(Command.CommandValue.ASSIGN, OWNER, "Assigned Moodle Help Team");
		Command hold = new Command(Command.CommandValue.HOLD, HOLD_AWAITING_CALLER, "Put on hold");
		Command investigate = new Command(Command.CommandValue.INVESTIGATE, null, "Investigated");
		Command resolve = new Command(Command.CommandValue.RESOLVE, RESOLUTION_WORKAROUND, "Resolved");
		Command reopen = new Command(Command.CommandValue.REOPEN, null, "Reopened");
		Command cancel = new Command(Command.CommandValue.CANCEL, CANCELLATION_DUPLICATE, "Cancelled");
		s.executeCommand(1, assign);
		assertEquals(IN_PROGRESS_NAME, s.getIncidentById(1).getState());
		s.executeCommand(1, hold);
		assertEquals(ON_HOLD_NAME, s.getIncidentById(1).getState());
		s.executeCommand(1, investigate);
		assertEquals(IN_PROGRESS_NAME, s.getIncidentById(1).getState());
		s.executeCommand(1, resolve);
		assertEquals(RESOLVED_NAME, s.getIncidentById(1).getState());
		s.executeCommand(1, reopen);
		assertEquals(IN_PROGRESS_NAME, s.getIncidentById(1).getState());
		s.executeCommand(1, cancel);
		assertEquals(CANCELED_NAME, s.getIncidentById(1).getState());
	}
}
