package edu.ncsu.csc216.service_wolf.model.incident;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;

import edu.ncsu.csc216.service_wolf.model.command.Command;

/**
 * Tests the constructing and updating of the incident class
 * @author rsthoma5
 *
 */
public class IncidentTest {

	/**Title of test incident**/
	private static final String TITLE = "Moodle down";
	/**Caller of the test incident**/
	private static final String CALLER = "User1";
	/**Message of the test incident**/
	private static final String MESSAGE = "Moodle won't load";
	/**Id of test incident**/
	private static final int ID = 2;
	/**Owner of the test incident**/
	private static final String OWNER = "Moodle Help Team";
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
	
	@Before
	public void setUp() throws Exception {
		//Reset the counter at the beginning of every test.
		Incident.setCounter(1);
	}
	
	/**
	 * Tests the constructor when given three parameters
	 */
	@Test
	public void testIncidentThreeParams() {
		Incident i = new Incident(TITLE, CALLER, MESSAGE);
		assertEquals(TITLE, i.getTitle());
		assertEquals(CALLER, i.getCaller());
		assertEquals("- " + MESSAGE + "\n", i.getIncidentLogMessages());
		assertEquals(NEW_NAME, i.getState());
		assertEquals(1, i.getId());
		assertEquals(UNOWNED, i.getOwner());
		assertEquals(0, i.getReopenCount());
		assertEquals(NO_STATUS, i.getStatusDetails());
		Incident j = new Incident("Webassign down", "User2", "Webassign ain't workin");
		assertEquals("Webassign down", j.getTitle());
		assertEquals("User2", j.getCaller());
		assertEquals("- Webassign ain't workin\n", j.getIncidentLogMessages());
		assertEquals(NEW_NAME, j.getState());
		assertEquals(2, j.getId());
		assertEquals(UNOWNED, j.getOwner());
		assertEquals(0, i.getReopenCount());
		assertEquals(NO_STATUS, i.getStatusDetails());
		try {
			Incident k = new Incident(null, CALLER, MESSAGE);
			fail();
		} catch(IllegalArgumentException e) {
			assertEquals("Incident cannot be created", e.getMessage());
		}
		try {
			Incident k = new Incident("", CALLER, MESSAGE);
			fail();
		} catch(IllegalArgumentException e) {
			assertEquals("Incident cannot be created", e.getMessage());
		}
		try {
			Incident k = new Incident(TITLE, null, MESSAGE);
			fail();
		} catch(IllegalArgumentException e) {
			assertEquals("Incident cannot be created", e.getMessage());
		}
		try {
			Incident k = new Incident(TITLE, "", MESSAGE);
			fail();
		} catch(IllegalArgumentException e) {
			assertEquals("Incident cannot be created", e.getMessage());
		}
		try {
			Incident k = new Incident(TITLE, CALLER, null);
			fail();
		} catch(IllegalArgumentException e) {
			assertEquals("Incident cannot be created", e.getMessage());
		}
		try {
			Incident k = new Incident(TITLE, CALLER, "");
			fail();
		} catch(IllegalArgumentException e) {
			assertEquals("Incident cannot be created", e.getMessage());
		}
	}

	/**
	 * Tests the constructor when given all eight parameters
	 */
	@Test
	public void testIncidentEightParams() {
		ArrayList<String> IncidentLog = new ArrayList<String>();
		ArrayList<String> EmptyLog = new ArrayList<String>();
		IncidentLog.add("Moodles down pls help");
		IncidentLog.add("Ok I'll help with that");
		String IncidentLogString = "- Moodles down pls help\n- Ok I'll help with that\n";
		Incident n = new Incident(1, NEW_NAME, TITLE, CALLER, 0, UNOWNED, NO_STATUS, IncidentLog);
		assertEquals(TITLE, n.getTitle());
		assertEquals(CALLER, n.getCaller());
		assertEquals(IncidentLogString, n.getIncidentLogMessages());
		assertEquals(NEW_NAME, n.getState());
		assertEquals(1, n.getId());
		assertEquals(UNOWNED, n.getOwner());
		assertEquals(0, n.getReopenCount());
		assertEquals(NO_STATUS, n.getStatusDetails());
		Incident p = new Incident(3, IN_PROGRESS_NAME, TITLE, CALLER, 1, OWNER, NO_STATUS, IncidentLog);
		assertEquals(TITLE, p.getTitle());
		assertEquals(CALLER, p.getCaller());
		assertEquals(IncidentLogString, p.getIncidentLogMessages());
		assertEquals(IN_PROGRESS_NAME, p.getState());
		assertEquals(3, p.getId());
		assertEquals(OWNER, p.getOwner());
		assertEquals(1, p.getReopenCount());
		assertEquals(NO_STATUS, p.getStatusDetails());
		Incident hc = new Incident(4, ON_HOLD_NAME, TITLE, CALLER, 1, OWNER, HOLD_AWAITING_CALLER, IncidentLog);
		assertEquals(TITLE, hc.getTitle());
		assertEquals(CALLER, hc.getCaller());
		assertEquals(IncidentLogString, hc.getIncidentLogMessages());
		assertEquals(ON_HOLD_NAME, hc.getState());
		assertEquals(4, hc.getId());
		assertEquals(OWNER, hc.getOwner());
		assertEquals(1, hc.getReopenCount());
		assertEquals(HOLD_AWAITING_CALLER, hc.getStatusDetails());
		Incident hh = new Incident(5, ON_HOLD_NAME, TITLE, CALLER, 1, OWNER, HOLD_AWAITING_CHANGE, IncidentLog);
		assertEquals(TITLE, hh.getTitle());
		assertEquals(CALLER, hh.getCaller());
		assertEquals(IncidentLogString, hh.getIncidentLogMessages());
		assertEquals(ON_HOLD_NAME, hh.getState());
		assertEquals(5, hh.getId());
		assertEquals(OWNER, hh.getOwner());
		assertEquals(1, hh.getReopenCount());
		assertEquals(HOLD_AWAITING_CHANGE, hh.getStatusDetails());
		Incident hv = new Incident(6, ON_HOLD_NAME, TITLE, CALLER, 1, OWNER, HOLD_AWAITING_VENDOR, IncidentLog);
		assertEquals(TITLE, hv.getTitle());
		assertEquals(CALLER, hv.getCaller());
		assertEquals(IncidentLogString, hv.getIncidentLogMessages());
		assertEquals(ON_HOLD_NAME, hv.getState());
		assertEquals(6, hv.getId());
		assertEquals(OWNER, hv.getOwner());
		assertEquals(1, hv.getReopenCount());
		assertEquals(HOLD_AWAITING_VENDOR, hv.getStatusDetails());
		Incident rp = new Incident(7, RESOLVED_NAME, TITLE, CALLER, 1, OWNER, RESOLUTION_PERMANENTLY_SOLVED, IncidentLog);
		assertEquals(TITLE, rp.getTitle());
		assertEquals(CALLER, rp.getCaller());
		assertEquals(IncidentLogString, rp.getIncidentLogMessages());
		assertEquals(RESOLVED_NAME, rp.getState());
		assertEquals(7, rp.getId());
		assertEquals(OWNER, rp.getOwner());
		assertEquals(1, rp.getReopenCount());
		assertEquals(RESOLUTION_PERMANENTLY_SOLVED, rp.getStatusDetails());
		Incident rw = new Incident(8, RESOLVED_NAME, TITLE, CALLER, 1, OWNER, RESOLUTION_WORKAROUND, IncidentLog);
		assertEquals(TITLE, rw.getTitle());
		assertEquals(CALLER, rw.getCaller());
		assertEquals(IncidentLogString, rw.getIncidentLogMessages());
		assertEquals(RESOLVED_NAME, rw.getState());
		assertEquals(8, rw.getId());
		assertEquals(OWNER, rw.getOwner());
		assertEquals(1, rw.getReopenCount());
		assertEquals(RESOLUTION_WORKAROUND, rw.getStatusDetails());
		Incident rc = new Incident(9, RESOLVED_NAME, TITLE, CALLER, 1, OWNER, RESOLUTION_CALLER_CLOSED, IncidentLog);
		assertEquals(TITLE, rc.getTitle());
		assertEquals(CALLER, rc.getCaller());
		assertEquals(IncidentLogString, rc.getIncidentLogMessages());
		assertEquals(RESOLVED_NAME, rc.getState());
		assertEquals(9, rc.getId());
		assertEquals(OWNER, rc.getOwner());
		assertEquals(1, rc.getReopenCount());
		assertEquals(RESOLUTION_CALLER_CLOSED, rc.getStatusDetails());
		Incident cd = new Incident(10, CANCELED_NAME, TITLE, CALLER, 1, UNOWNED, CANCELLATION_DUPLICATE, IncidentLog);
		assertEquals(TITLE, cd.getTitle());
		assertEquals(CALLER, cd.getCaller());
		assertEquals(IncidentLogString, cd.getIncidentLogMessages());
		assertEquals(CANCELED_NAME, cd.getState());
		assertEquals(10, cd.getId());
		assertEquals(UNOWNED, cd.getOwner());
		assertEquals(1, cd.getReopenCount());
		assertEquals(CANCELLATION_DUPLICATE, cd.getStatusDetails());
		Incident cu = new Incident(11, CANCELED_NAME, TITLE, CALLER, 1, UNOWNED, CANCELLATION_UNNECESSARY, IncidentLog);
		assertEquals(TITLE, cu.getTitle());
		assertEquals(CALLER, cu.getCaller());
		assertEquals(IncidentLogString, cu.getIncidentLogMessages());
		assertEquals(CANCELED_NAME, cu.getState());
		assertEquals(11, cu.getId());
		assertEquals(UNOWNED, cu.getOwner());
		assertEquals(1, cu.getReopenCount());
		assertEquals(CANCELLATION_UNNECESSARY, cu.getStatusDetails());
		Incident cn = new Incident(12, CANCELED_NAME, TITLE, CALLER, 1, UNOWNED, CANCELLATION_NOT_AN_INCIDENT, IncidentLog);
		assertEquals(TITLE, cn.getTitle());
		assertEquals(CALLER, cn.getCaller());
		assertEquals(IncidentLogString, cn.getIncidentLogMessages());
		assertEquals(CANCELED_NAME, cn.getState());
		assertEquals(12, cn.getId());
		assertEquals(UNOWNED, cn.getOwner());
		assertEquals(1, cn.getReopenCount());
		assertEquals(CANCELLATION_NOT_AN_INCIDENT, cn.getStatusDetails());
		Incident cc = new Incident(13, CANCELED_NAME, TITLE, CALLER, 1, UNOWNED, CANCELLATION_CALLER_CANCELLED, IncidentLog);
		assertEquals(TITLE, cc.getTitle());
		assertEquals(CALLER, cc.getCaller());
		assertEquals(IncidentLogString, cc.getIncidentLogMessages());
		assertEquals(CANCELED_NAME, cc.getState());
		assertEquals(13, cc.getId());
		assertEquals(UNOWNED, cc.getOwner());
		assertEquals(1, cc.getReopenCount());
		assertEquals(CANCELLATION_CALLER_CANCELLED, cc.getStatusDetails());
		try {
			Incident k = new Incident(14, IN_PROGRESS_NAME, null, CALLER, 1, OWNER, NO_STATUS, IncidentLog);
			fail();
		} catch(IllegalArgumentException e) {
			assertEquals("Incident cannot be created", e.getMessage());
		}
		try {
			Incident k = new Incident(14, IN_PROGRESS_NAME, "", CALLER, 1, OWNER, NO_STATUS, IncidentLog);
			fail();
		} catch(IllegalArgumentException e) {
			assertEquals("Incident cannot be created", e.getMessage());
		}
		try {
			Incident k = new Incident(-1, IN_PROGRESS_NAME, TITLE, CALLER, 1, OWNER, NO_STATUS, IncidentLog);
			fail();
		} catch(IllegalArgumentException e) {
			assertEquals("Incident cannot be created", e.getMessage());
		}
		try {
			Incident k = new Incident(14, null, TITLE, CALLER, 1, OWNER, NO_STATUS, IncidentLog);
			fail();
		} catch(IllegalArgumentException e) {
			assertEquals("Incident cannot be created", e.getMessage());
		}
		try {
			Incident k = new Incident(14, "", TITLE, CALLER, 1, OWNER, NO_STATUS, IncidentLog);
			fail();
		} catch(IllegalArgumentException e) {
			assertEquals("Incident cannot be created", e.getMessage());
		}
		try {
			Incident k = new Incident(14, "not a state", TITLE, CALLER, 1, OWNER, NO_STATUS, IncidentLog);
			fail();
		} catch(IllegalArgumentException e) {
			assertEquals("Incident cannot be created", e.getMessage());
		}
		try {
			Incident k = new Incident(14, IN_PROGRESS_NAME, TITLE, null, 1, OWNER, NO_STATUS, IncidentLog);
			fail();
		} catch(IllegalArgumentException e) {
			assertEquals("Incident cannot be created", e.getMessage());
		}
		try {
			Incident k = new Incident(14, IN_PROGRESS_NAME, TITLE, "", 1, OWNER, NO_STATUS, IncidentLog);
			fail();
		} catch(IllegalArgumentException e) {
			assertEquals("Incident cannot be created", e.getMessage());
		}
		try {
			Incident k = new Incident(14, IN_PROGRESS_NAME, TITLE, CALLER, -1, OWNER, NO_STATUS, IncidentLog);
			fail();
		} catch(IllegalArgumentException e) {
			assertEquals("Incident cannot be created", e.getMessage());
		}
		try {
			Incident k = new Incident(14, IN_PROGRESS_NAME, TITLE, CALLER, 1, null, NO_STATUS, IncidentLog);
			fail();
		} catch(IllegalArgumentException e) {
			assertEquals("Incident cannot be created", e.getMessage());
		}
		try {
			Incident k = new Incident(14, IN_PROGRESS_NAME, TITLE, CALLER, 1, "", NO_STATUS, IncidentLog);
			fail();
		} catch(IllegalArgumentException e) {
			assertEquals("Incident cannot be created", e.getMessage());
		}
		try {
			Incident k = new Incident(14, IN_PROGRESS_NAME, TITLE, CALLER, 1, OWNER, null, IncidentLog);
			fail();
		} catch(IllegalArgumentException e) {
			assertEquals("Incident cannot be created", e.getMessage());
		}
		try {
			Incident k = new Incident(14, IN_PROGRESS_NAME, TITLE, CALLER, 1, OWNER, "", IncidentLog);
			fail();
		} catch(IllegalArgumentException e) {
			assertEquals("Incident cannot be created", e.getMessage());
		}
		try {
			Incident k = new Incident(14, IN_PROGRESS_NAME, TITLE, CALLER, 1, OWNER, NO_STATUS, EmptyLog);
			fail();
		} catch(IllegalArgumentException e) {
			assertEquals("Incident cannot be created", e.getMessage());
		}
		try {
			Incident k = new Incident(14, NEW_NAME, TITLE, CALLER, 1, OWNER, NO_STATUS, IncidentLog);
			fail();
		} catch(IllegalArgumentException e) {
			assertEquals("Incident cannot be created", e.getMessage());
		}
		try {
			Incident k = new Incident(14, NEW_NAME, TITLE, CALLER, 1, UNOWNED, RESOLUTION_WORKAROUND, IncidentLog);
			fail();
		} catch(IllegalArgumentException e) {
			assertEquals("Incident cannot be created", e.getMessage());
		}
		try {
			Incident k = new Incident(14, IN_PROGRESS_NAME, TITLE, CALLER, 1, UNOWNED, NO_STATUS, IncidentLog);
			fail();
		} catch(IllegalArgumentException e) {
			assertEquals("Incident cannot be created", e.getMessage());
		}
		try {
			Incident k = new Incident(14, IN_PROGRESS_NAME, TITLE, CALLER, 1, OWNER, RESOLUTION_WORKAROUND, IncidentLog);
			fail();
		} catch(IllegalArgumentException e) {
			assertEquals("Incident cannot be created", e.getMessage());
		}
		try {
			Incident k = new Incident(14, ON_HOLD_NAME, TITLE, CALLER, 1, UNOWNED, HOLD_AWAITING_VENDOR, IncidentLog);
			fail();
		} catch(IllegalArgumentException e) {
			assertEquals("Incident cannot be created", e.getMessage());
		}
		try {
			Incident k = new Incident(14, ON_HOLD_NAME, TITLE, CALLER, 1, OWNER, NO_STATUS, IncidentLog);
			fail();
		} catch(IllegalArgumentException e) {
			assertEquals("Incident cannot be created", e.getMessage());
		}
		try {
			Incident k = new Incident(14, RESOLVED_NAME, TITLE, CALLER, 1, UNOWNED, RESOLUTION_WORKAROUND, IncidentLog);
			fail();
		} catch(IllegalArgumentException e) {
			assertEquals("Incident cannot be created", e.getMessage());
		}
		try {
			Incident k = new Incident(14, RESOLVED_NAME, TITLE, CALLER, 1, OWNER, NO_STATUS, IncidentLog);
			fail();
		} catch(IllegalArgumentException e) {
			assertEquals("Incident cannot be created", e.getMessage());
		}
		try {
			Incident k = new Incident(14, CANCELED_NAME, TITLE, CALLER, 1, OWNER, CANCELLATION_DUPLICATE, IncidentLog);
			fail();
		} catch(IllegalArgumentException e) {
			assertEquals("Incident cannot be created", e.getMessage());
		}
		try {
			Incident k = new Incident(14, CANCELED_NAME, TITLE, CALLER, 1, UNOWNED, NO_STATUS, IncidentLog);
			fail();
		} catch(IllegalArgumentException e) {
			assertEquals("Incident cannot be created", e.getMessage());
		}
	}
	
	/**
	 * Tests that the toString method returns the correct string
	 */
	@Test
	public void testToString() {
		ArrayList<String> IncidentLog = new ArrayList<String>();
		IncidentLog.add("Moodles down pls help");
		IncidentLog.add("Ok I'll help with that");
		String IncidentLogString = "- Moodles down pls help\n- Ok I'll help with that\n";
		Incident i = new Incident(ID, IN_PROGRESS_NAME, TITLE, CALLER, 1, OWNER, NO_STATUS, IncidentLog);
		assertEquals("* " + ID + "," + IN_PROGRESS_NAME + "," + TITLE + "," + CALLER + "," + 1 + "," + OWNER + "," + NO_STATUS + "\n" + IncidentLogString, i.toString());
	}
	/**
	 * Tests that update correctly updates the incident based on the command
	 */
	@Test
	public void testUpdate() {
		ArrayList<String> IncidentLog = new ArrayList<String>();
		IncidentLog.add("Moodles down pls help");
		String IncidentLogString = "- Moodles down pls help\n";
		Incident n = new Incident(1, NEW_NAME, TITLE, CALLER, 0, UNOWNED, NO_STATUS, IncidentLog);
		Incident p = new Incident(2, IN_PROGRESS_NAME, TITLE, CALLER, 0, OWNER, NO_STATUS, IncidentLog);
		Incident h = new Incident(3, ON_HOLD_NAME, TITLE, CALLER, 0, OWNER, HOLD_AWAITING_CHANGE, IncidentLog);
		Incident r = new Incident(4, RESOLVED_NAME, TITLE, CALLER, 0, OWNER, RESOLUTION_WORKAROUND, IncidentLog);
		Incident c = new Incident(5, CANCELED_NAME, TITLE, CALLER, 0, UNOWNED, CANCELLATION_DUPLICATE, IncidentLog);
		Command assign = new Command(Command.CommandValue.ASSIGN, OWNER, "Assigned Moodle Help Team");
		Command hold = new Command(Command.CommandValue.HOLD, HOLD_AWAITING_CALLER, "Put on hold");
		Command investigate = new Command(Command.CommandValue.INVESTIGATE, null, "Investigated");
		Command resolve = new Command(Command.CommandValue.RESOLVE, RESOLUTION_WORKAROUND, "Resolved");
		Command reopen = new Command(Command.CommandValue.REOPEN, null, "Reopened");
		Command cancel = new Command(Command.CommandValue.CANCEL, CANCELLATION_DUPLICATE, "Cancelled");
		try {
			n.update(assign);
			assertEquals(TITLE, n.getTitle());
			assertEquals(CALLER, n.getCaller());
			assertEquals(IncidentLogString + "- Assigned Moodle Help Team\n", n.getIncidentLogMessages());
			assertEquals(IN_PROGRESS_NAME, n.getState());
			assertEquals(1, n.getId());
			assertEquals(OWNER, n.getOwner());
			assertEquals(0, n.getReopenCount());
			assertEquals(NO_STATUS, n.getStatusDetails());
		} catch(UnsupportedOperationException e) {
			fail();
		}
		n = new Incident(1, NEW_NAME, TITLE, CALLER, 0, UNOWNED, NO_STATUS, IncidentLog);
		try {
			n.update(hold);
			fail();
		} catch(UnsupportedOperationException e) {
			assertEquals(TITLE, n.getTitle());
			assertEquals(CALLER, n.getCaller());
			assertEquals(IncidentLogString, n.getIncidentLogMessages());
			assertEquals(NEW_NAME, n.getState());
			assertEquals(1, n.getId());
			assertEquals(UNOWNED, n.getOwner());
			assertEquals(0, n.getReopenCount());
			assertEquals(NO_STATUS, n.getStatusDetails());
		}
		n = new Incident(1, NEW_NAME, TITLE, CALLER, 0, UNOWNED, NO_STATUS, IncidentLog);
		try {
			n.update(investigate);
			fail();
		} catch(UnsupportedOperationException e) {
			assertEquals(TITLE, n.getTitle());
			assertEquals(CALLER, n.getCaller());
			assertEquals(IncidentLogString, n.getIncidentLogMessages());
			assertEquals(NEW_NAME, n.getState());
			assertEquals(1, n.getId());
			assertEquals(UNOWNED, n.getOwner());
			assertEquals(0, n.getReopenCount());
			assertEquals(NO_STATUS, n.getStatusDetails());
		}
		n = new Incident(1, NEW_NAME, TITLE, CALLER, 0, UNOWNED, NO_STATUS, IncidentLog);
		try {
			n.update(resolve);
			fail();
		} catch(UnsupportedOperationException e) {
			assertEquals(TITLE, n.getTitle());
			assertEquals(CALLER, n.getCaller());
			assertEquals(IncidentLogString, n.getIncidentLogMessages());
			assertEquals(NEW_NAME, n.getState());
			assertEquals(1, n.getId());
			assertEquals(UNOWNED, n.getOwner());
			assertEquals(0, n.getReopenCount());
			assertEquals(NO_STATUS, n.getStatusDetails());
		}
		n = new Incident(1, NEW_NAME, TITLE, CALLER, 0, UNOWNED, NO_STATUS, IncidentLog);
		try {
			n.update(reopen);
			fail();
		} catch(UnsupportedOperationException e) {
			assertEquals(TITLE, n.getTitle());
			assertEquals(CALLER, n.getCaller());
			assertEquals(IncidentLogString, n.getIncidentLogMessages());
			assertEquals(NEW_NAME, n.getState());
			assertEquals(1, n.getId());
			assertEquals(UNOWNED, n.getOwner());
			assertEquals(0, n.getReopenCount());
			assertEquals(NO_STATUS, n.getStatusDetails());
		}
		n = new Incident(1, NEW_NAME, TITLE, CALLER, 0, UNOWNED, NO_STATUS, IncidentLog);
		try {
			n.update(cancel);
			assertEquals(TITLE, n.getTitle());
			assertEquals(CALLER, n.getCaller());
			assertEquals(IncidentLogString + "- Cancelled\n", n.getIncidentLogMessages());
			assertEquals(CANCELED_NAME, n.getState());
			assertEquals(1, n.getId());
			assertEquals(UNOWNED, n.getOwner());
			assertEquals(0, n.getReopenCount());
			assertEquals(CANCELLATION_DUPLICATE, n.getStatusDetails());
		} catch(UnsupportedOperationException e) {
			fail();
		}
		try {
			p.update(assign);
			assertEquals(TITLE, p.getTitle());
			assertEquals(CALLER, p.getCaller());
			assertEquals(IncidentLogString + "- Assigned Moodle Help Team\n", p.getIncidentLogMessages());
			assertEquals(IN_PROGRESS_NAME, p.getState());
			assertEquals(2, p.getId());
			assertEquals(OWNER, p.getOwner());
			assertEquals(0, p.getReopenCount());
			assertEquals(NO_STATUS, p.getStatusDetails());
		} catch(UnsupportedOperationException e) {
			fail();
		}
		p = new Incident(2, IN_PROGRESS_NAME, TITLE, CALLER, 0, OWNER, NO_STATUS, IncidentLog);
		try {
			p.update(hold);
			assertEquals(TITLE, p.getTitle());
			assertEquals(CALLER, p.getCaller());
			assertEquals(IncidentLogString + "- Put on hold\n", p.getIncidentLogMessages());
			assertEquals(ON_HOLD_NAME, p.getState());
			assertEquals(2, p.getId());
			assertEquals(OWNER, p.getOwner());
			assertEquals(0, p.getReopenCount());
			assertEquals(HOLD_AWAITING_CALLER, p.getStatusDetails());
		} catch(UnsupportedOperationException e) {
			fail();
		}
		p = new Incident(2, IN_PROGRESS_NAME, TITLE, CALLER, 0, OWNER, NO_STATUS, IncidentLog);
		try {
			p.update(investigate);
			fail();
		} catch(UnsupportedOperationException e) {
			assertEquals(TITLE, p.getTitle());
			assertEquals(CALLER, p.getCaller());
			assertEquals(IncidentLogString, p.getIncidentLogMessages());
			assertEquals(IN_PROGRESS_NAME, p.getState());
			assertEquals(2, p.getId());
			assertEquals(OWNER, p.getOwner());
			assertEquals(0, p.getReopenCount());
			assertEquals(NO_STATUS, p.getStatusDetails());
		}
		p = new Incident(2, IN_PROGRESS_NAME, TITLE, CALLER, 0, OWNER, NO_STATUS, IncidentLog);
		try {
			p.update(resolve);
			assertEquals(TITLE, p.getTitle());
			assertEquals(CALLER, p.getCaller());
			assertEquals(IncidentLogString + "- Resolved\n", p.getIncidentLogMessages());
			assertEquals(RESOLVED_NAME, p.getState());
			assertEquals(2, p.getId());
			assertEquals(OWNER, p.getOwner());
			assertEquals(0, p.getReopenCount());
			assertEquals(RESOLUTION_WORKAROUND, p.getStatusDetails());
		} catch(UnsupportedOperationException e) {
			fail();
		}
		p = new Incident(2, IN_PROGRESS_NAME, TITLE, CALLER, 0, OWNER, NO_STATUS, IncidentLog);
		try {
			p.update(reopen);
			fail();
		} catch(UnsupportedOperationException e) {
			assertEquals(TITLE, p.getTitle());
			assertEquals(CALLER, p.getCaller());
			assertEquals(IncidentLogString, p.getIncidentLogMessages());
			assertEquals(IN_PROGRESS_NAME, p.getState());
			assertEquals(2, p.getId());
			assertEquals(OWNER, p.getOwner());
			assertEquals(0, p.getReopenCount());
			assertEquals(NO_STATUS, p.getStatusDetails());
		}
		p = new Incident(2, IN_PROGRESS_NAME, TITLE, CALLER, 0, OWNER, NO_STATUS, IncidentLog);
		try {
			p.update(cancel);
			assertEquals(TITLE, p.getTitle());
			assertEquals(CALLER, p.getCaller());
			assertEquals(IncidentLogString + "- Cancelled\n", p.getIncidentLogMessages());
			assertEquals(CANCELED_NAME, p.getState());
			assertEquals(2, p.getId());
			assertEquals(UNOWNED, p.getOwner());
			assertEquals(0, p.getReopenCount());
			assertEquals(CANCELLATION_DUPLICATE, p.getStatusDetails());
		} catch(UnsupportedOperationException e) {
			fail();
		}
		try {
			h.update(assign);
			fail();
		} catch (UnsupportedOperationException e) {
			assertEquals(TITLE, h.getTitle());
			assertEquals(CALLER, h.getCaller());
			assertEquals(IncidentLogString, h.getIncidentLogMessages());
			assertEquals(ON_HOLD_NAME, h.getState());
			assertEquals(3, h.getId());
			assertEquals(OWNER, h.getOwner());
			assertEquals(0, h.getReopenCount());
			assertEquals(HOLD_AWAITING_CHANGE, h.getStatusDetails());
		}
		h = new Incident(3, ON_HOLD_NAME, TITLE, CALLER, 0, OWNER, HOLD_AWAITING_CHANGE, IncidentLog);
		try {
			h.update(hold);
			fail();
		} catch (UnsupportedOperationException e) {
			assertEquals(TITLE, h.getTitle());
			assertEquals(CALLER, h.getCaller());
			assertEquals(IncidentLogString, h.getIncidentLogMessages());
			assertEquals(ON_HOLD_NAME, h.getState());
			assertEquals(3, h.getId());
			assertEquals(OWNER, h.getOwner());
			assertEquals(0, h.getReopenCount());
			assertEquals(HOLD_AWAITING_CHANGE, h.getStatusDetails());
		}
		h = new Incident(3, ON_HOLD_NAME, TITLE, CALLER, 0, OWNER, HOLD_AWAITING_CHANGE, IncidentLog);
		try {
			h.update(investigate);
			assertEquals(TITLE, h.getTitle());
			assertEquals(CALLER, h.getCaller());
			assertEquals(IncidentLogString + "- Investigated\n", h.getIncidentLogMessages());
			assertEquals(IN_PROGRESS_NAME, h.getState());
			assertEquals(3, h.getId());
			assertEquals(OWNER, h.getOwner());
			assertEquals(0, h.getReopenCount());
			assertEquals(NO_STATUS, h.getStatusDetails());
		} catch (UnsupportedOperationException e) {
			fail();
		}
		h = new Incident(3, ON_HOLD_NAME, TITLE, CALLER, 0, OWNER, HOLD_AWAITING_CHANGE, IncidentLog);
		try {
			h.update(resolve);
			fail();
		} catch (UnsupportedOperationException e) {
			assertEquals(TITLE, h.getTitle());
			assertEquals(CALLER, h.getCaller());
			assertEquals(IncidentLogString, h.getIncidentLogMessages());
			assertEquals(ON_HOLD_NAME, h.getState());
			assertEquals(3, h.getId());
			assertEquals(OWNER, h.getOwner());
			assertEquals(0, h.getReopenCount());
			assertEquals(HOLD_AWAITING_CHANGE, h.getStatusDetails());
		}
		h = new Incident(3, ON_HOLD_NAME, TITLE, CALLER, 0, OWNER, HOLD_AWAITING_CHANGE, IncidentLog);
		try {
			h.update(reopen);
			fail();
		} catch (UnsupportedOperationException e) {
			assertEquals(TITLE, h.getTitle());
			assertEquals(CALLER, h.getCaller());
			assertEquals(IncidentLogString, h.getIncidentLogMessages());
			assertEquals(ON_HOLD_NAME, h.getState());
			assertEquals(3, h.getId());
			assertEquals(OWNER, h.getOwner());
			assertEquals(0, h.getReopenCount());
			assertEquals(HOLD_AWAITING_CHANGE, h.getStatusDetails());
		}
		h = new Incident(3, ON_HOLD_NAME, TITLE, CALLER, 0, OWNER, HOLD_AWAITING_CHANGE, IncidentLog);
		try {
			h.update(cancel);
			fail();
		} catch (UnsupportedOperationException e) {
			assertEquals(TITLE, h.getTitle());
			assertEquals(CALLER, h.getCaller());
			assertEquals(IncidentLogString, h.getIncidentLogMessages());
			assertEquals(ON_HOLD_NAME, h.getState());
			assertEquals(3, h.getId());
			assertEquals(OWNER, h.getOwner());
			assertEquals(0, h.getReopenCount());
			assertEquals(HOLD_AWAITING_CHANGE, h.getStatusDetails());
		}
		try {
			r.update(assign);
			fail();
		} catch (UnsupportedOperationException e) {
			assertEquals(TITLE, r.getTitle());
			assertEquals(CALLER, r.getCaller());
			assertEquals(IncidentLogString, r.getIncidentLogMessages());
			assertEquals(RESOLVED_NAME, r.getState());
			assertEquals(4, r.getId());
			assertEquals(OWNER, r.getOwner());
			assertEquals(0, r.getReopenCount());
			assertEquals(RESOLUTION_WORKAROUND, r.getStatusDetails());
		}
		r = new Incident(4, RESOLVED_NAME, TITLE, CALLER, 0, OWNER, RESOLUTION_WORKAROUND, IncidentLog);
		try {
			r.update(hold);
			fail();
		} catch (UnsupportedOperationException e) {
			assertEquals(TITLE, r.getTitle());
			assertEquals(CALLER, r.getCaller());
			assertEquals(IncidentLogString, r.getIncidentLogMessages());
			assertEquals(RESOLVED_NAME, r.getState());
			assertEquals(4, r.getId());
			assertEquals(OWNER, r.getOwner());
			assertEquals(0, r.getReopenCount());
			assertEquals(RESOLUTION_WORKAROUND, r.getStatusDetails());
		}
		r = new Incident(4, RESOLVED_NAME, TITLE, CALLER, 0, OWNER, RESOLUTION_WORKAROUND, IncidentLog);
		try {
			r.update(investigate);
			fail();
		} catch (UnsupportedOperationException e) {
			assertEquals(TITLE, r.getTitle());
			assertEquals(CALLER, r.getCaller());
			assertEquals(IncidentLogString, r.getIncidentLogMessages());
			assertEquals(RESOLVED_NAME, r.getState());
			assertEquals(4, r.getId());
			assertEquals(OWNER, r.getOwner());
			assertEquals(0, r.getReopenCount());
			assertEquals(RESOLUTION_WORKAROUND, r.getStatusDetails());
		}
		r = new Incident(4, RESOLVED_NAME, TITLE, CALLER, 0, OWNER, RESOLUTION_WORKAROUND, IncidentLog);
		try {
			r.update(resolve);
			fail();
		} catch (UnsupportedOperationException e) {
			assertEquals(TITLE, r.getTitle());
			assertEquals(CALLER, r.getCaller());
			assertEquals(IncidentLogString, r.getIncidentLogMessages());
			assertEquals(RESOLVED_NAME, r.getState());
			assertEquals(4, r.getId());
			assertEquals(OWNER, r.getOwner());
			assertEquals(0, r.getReopenCount());
			assertEquals(RESOLUTION_WORKAROUND, r.getStatusDetails());
		}
		r = new Incident(4, RESOLVED_NAME, TITLE, CALLER, 0, OWNER, RESOLUTION_WORKAROUND, IncidentLog);
		try {
			r.update(reopen);
			assertEquals(TITLE, r.getTitle());
			assertEquals(CALLER, r.getCaller());
			assertEquals(IncidentLogString + "- Reopened\n", r.getIncidentLogMessages());
			assertEquals(IN_PROGRESS_NAME, r.getState());
			assertEquals(4, r.getId());
			assertEquals(OWNER, r.getOwner());
			assertEquals(1, r.getReopenCount());
			assertEquals(NO_STATUS, r.getStatusDetails());
		} catch (UnsupportedOperationException e) {
			fail();
		}
		r = new Incident(4, RESOLVED_NAME, TITLE, CALLER, 0, OWNER, RESOLUTION_WORKAROUND, IncidentLog);
		try {
			r.update(cancel);
			assertEquals(TITLE, r.getTitle());
			assertEquals(CALLER, r.getCaller());
			assertEquals(IncidentLogString + "- Cancelled\n", r.getIncidentLogMessages());
			assertEquals(CANCELED_NAME, r.getState());
			assertEquals(4, r.getId());
			assertEquals(UNOWNED, r.getOwner());
			assertEquals(0, r.getReopenCount());
			assertEquals(CANCELLATION_DUPLICATE, r.getStatusDetails());
		} catch (UnsupportedOperationException e) {
			fail();
		}
		try {
			c.update(assign);
			fail();
		} catch (UnsupportedOperationException e) {
			assertEquals(TITLE, c.getTitle());
			assertEquals(CALLER, c.getCaller());
			assertEquals(IncidentLogString, c.getIncidentLogMessages());
			assertEquals(CANCELED_NAME, c.getState());
			assertEquals(5, c.getId());
			assertEquals(UNOWNED, c.getOwner());
			assertEquals(0, c.getReopenCount());
			assertEquals(CANCELLATION_DUPLICATE, c.getStatusDetails());
		}
		try {
			c.update(hold);
			fail();
		} catch (UnsupportedOperationException e) {
			assertEquals(TITLE, c.getTitle());
			assertEquals(CALLER, c.getCaller());
			assertEquals(IncidentLogString, c.getIncidentLogMessages());
			assertEquals(CANCELED_NAME, c.getState());
			assertEquals(5, c.getId());
			assertEquals(UNOWNED, c.getOwner());
			assertEquals(0, c.getReopenCount());
			assertEquals(CANCELLATION_DUPLICATE, c.getStatusDetails());
		}
		try {
			c.update(investigate);
			fail();
		} catch (UnsupportedOperationException e) {
			assertEquals(TITLE, c.getTitle());
			assertEquals(CALLER, c.getCaller());
			assertEquals(IncidentLogString, c.getIncidentLogMessages());
			assertEquals(CANCELED_NAME, c.getState());
			assertEquals(5, c.getId());
			assertEquals(UNOWNED, c.getOwner());
			assertEquals(0, c.getReopenCount());
			assertEquals(CANCELLATION_DUPLICATE, c.getStatusDetails());
		}
		try {
			c.update(resolve);
			fail();
		} catch (UnsupportedOperationException e) {
			assertEquals(TITLE, c.getTitle());
			assertEquals(CALLER, c.getCaller());
			assertEquals(IncidentLogString, c.getIncidentLogMessages());
			assertEquals(CANCELED_NAME, c.getState());
			assertEquals(5, c.getId());
			assertEquals(UNOWNED, c.getOwner());
			assertEquals(0, c.getReopenCount());
			assertEquals(CANCELLATION_DUPLICATE, c.getStatusDetails());
		}
		try {
			c.update(reopen);
			fail();
		} catch (UnsupportedOperationException e) {
			assertEquals(TITLE, c.getTitle());
			assertEquals(CALLER, c.getCaller());
			assertEquals(IncidentLogString, c.getIncidentLogMessages());
			assertEquals(CANCELED_NAME, c.getState());
			assertEquals(5, c.getId());
			assertEquals(UNOWNED, c.getOwner());
			assertEquals(0, c.getReopenCount());
			assertEquals(CANCELLATION_DUPLICATE, c.getStatusDetails());
		}
		try {
			c.update(cancel);
			fail();
		} catch (UnsupportedOperationException e) {
			assertEquals(TITLE, c.getTitle());
			assertEquals(CALLER, c.getCaller());
			assertEquals(IncidentLogString, c.getIncidentLogMessages());
			assertEquals(CANCELED_NAME, c.getState());
			assertEquals(5, c.getId());
			assertEquals(UNOWNED, c.getOwner());
			assertEquals(0, c.getReopenCount());
			assertEquals(CANCELLATION_DUPLICATE, c.getStatusDetails());
		}
	}
}
