package edu.ncsu.csc216.service_wolf.model.io;

import static org.junit.Assert.*;
import java.util.ArrayList;
import org.junit.Test;
import edu.ncsu.csc216.service_wolf.model.incident.Incident;
import edu.ncsu.csc216.service_wolf.model.service_group.ServiceGroup;

/**
 * Tests the ability of ServiceGroupWriter class to write to a file
 * @author rsthoma5
 *
 */
public class ServiceGroupWriterTest {

	/**
	 * Tests that writeServiceGroupsToFile correctly write a list of service groups to a file.
	 */
	@Test
	public void testWriteServiceGroupsToFile() {
		ArrayList<ServiceGroup> a = new ArrayList<ServiceGroup>();
		a.add(new ServiceGroup("CSC IT"));
		a.add(new ServiceGroup("ITECS"));
		a.add(new ServiceGroup("OIT"));
		ArrayList<String> csciti1list = new ArrayList<String>();
		ArrayList<String> csciti2list = new ArrayList<String>();
		ArrayList<String> csciti3list = new ArrayList<String>();
		ArrayList<String> csciti4list = new ArrayList<String>();
		ArrayList<String> itecslist = new ArrayList<String>();
		ArrayList<String> oitlist = new ArrayList<String>();
		csciti1list.add("Set up piazza for Spring 2021");
		csciti1list.add("Canceled; not an NC State IT service");
		csciti2list.add("When I go to wolfware.ncsu.edu, I get a 500 error");
		csciti3list.add("Please set up Jenkins VMs for Spring 2021 semester.");
		csciti3list.add("Assigned to C. Gurley");
		csciti3list.add("Set up test VM. Awaiting verification from caller.");
		csciti3list.add("VM works great, please deploy the rest.");
		csciti3list.add("VMs deployed. Marked resolved.");
		csciti3list.add("One of the VMs has the wrong version of Checkstyle installed.");
		csciti3list.add("Updated version of Checkstyle.");
		csciti4list.add("Jenkins requires VPN to access.  Please open to general access.");
		csciti4list.add("Assigned to C. Gurley");
		itecslist.add("I can't install Java on my computer.");
		itecslist.add("Assigned to itecs1");
		itecslist.add("Awaiting caller's feedback on attempting to install Java from Oracle");
		oitlist.add("I forgot my password and can't log into NC State accounts");
		oitlist.add("OIT staff member on call with support");
		a.get(0).addIncident(new Incident(2, "Canceled", "Piazza", "sesmith5", 0, "Unowned", "Not an Incident", csciti1list));
		a.get(0).addIncident(new Incident(3, "New", "Moodle down", "sesmith5", 0, "Unowned", "No Status", csciti2list));
		a.get(0).addIncident(new Incident(4, "Resolved", "Set up Jenkins VMs", "sesmith5", 1, "cgurley", "Permanently Solved", csciti3list));
		a.get(0).addIncident(new Incident(9, "In Progress", "Jenkins behind firewall", "sesmith5", 0, "cgurley", "No Status", csciti4list));
		a.get(1).addIncident(new Incident(7, "On Hold", "Java not installed correctly", "zmgrosec", 0, "itecs1", "Awaiting Caller", itecslist));
		a.get(2).addIncident(new Incident(1, "In Progress", "Forgot password", "jctetter", 0, "oit_staff", "No Status", oitlist));
		ServiceGroupWriter.writeServiceGroupsToFile("test-files/testgroups.txt", a);
		ArrayList<ServiceGroup> t = ServiceGroupsReader.readServiceGroupsFile("test-files/testgroups.txt");
		assertEquals(a.get(0).getServiceGroupName(), t.get(0).getServiceGroupName());
		assertEquals(a.get(0).getIncidents().get(0).getTitle(), t.get(0).getIncidents().get(0).getTitle());
		assertEquals(a.get(0).getIncidents().get(0).getIncidentLogMessages(), t.get(0).getIncidents().get(0).getIncidentLogMessages());
		assertEquals(a.get(0).getIncidents().get(1).getTitle(), t.get(0).getIncidents().get(1).getTitle());
		assertEquals(a.get(0).getIncidents().get(1).getIncidentLogMessages(), t.get(0).getIncidents().get(1).getIncidentLogMessages());
		assertEquals(a.get(0).getIncidents().get(2).getTitle(), t.get(0).getIncidents().get(2).getTitle());
		assertEquals(a.get(0).getIncidents().get(2).getIncidentLogMessages(), t.get(0).getIncidents().get(2).getIncidentLogMessages());
		assertEquals(a.get(0).getIncidents().get(3).getTitle(), t.get(0).getIncidents().get(3).getTitle());
		assertEquals(a.get(0).getIncidents().get(3).getIncidentLogMessages(), t.get(0).getIncidents().get(3).getIncidentLogMessages());
		assertEquals(a.get(1).getServiceGroupName(), t.get(1).getServiceGroupName());
		assertEquals(a.get(1).getIncidents().get(0).getTitle(), t.get(1).getIncidents().get(0).getTitle());
		assertEquals(a.get(1).getIncidents().get(0).getIncidentLogMessages(), t.get(1).getIncidents().get(0).getIncidentLogMessages());
		assertEquals(a.get(2).getServiceGroupName(), t.get(2).getServiceGroupName());
		assertEquals(a.get(2).getIncidents().get(0).getTitle(), t.get(2).getIncidents().get(0).getTitle());
		assertEquals(a.get(2).getIncidents().get(0).getIncidentLogMessages(), t.get(2).getIncidents().get(0).getIncidentLogMessages());
		ServiceGroup tsg = new ServiceGroup("Test Service Group");
		ArrayList<String> log = new ArrayList<String>();
		log.add("message1");
		tsg.addIncident(new Incident(1, "New", "title", "caller", 0, Incident.UNOWNED, Incident.NO_STATUS, log));
		ArrayList<ServiceGroup> tsglist = new ArrayList<ServiceGroup>();
		tsglist.add(tsg);
		ServiceGroupWriter.writeServiceGroupsToFile("test-files/actual_new.txt", tsglist);
	}
}
