package edu.ncsu.csc216.service_wolf.model.io;

import static org.junit.Assert.*;
import java.util.ArrayList;
import org.junit.Test;

import edu.ncsu.csc216.service_wolf.model.incident.Incident;
import edu.ncsu.csc216.service_wolf.model.service_group.ServiceGroup;

/**
 * Tests reading in service groups from a file through the ServiceGroupReader class
 * @author rsthoma5
 *
 */
public class ServiceGroupReaderTest {

	/**
	 * Tests readServiceGroupsFile's ability to correctly read in service groups from a file
	 */
	@Test
	public void testReadServiceGroupsFile() {
		ArrayList<ServiceGroup> s = ServiceGroupsReader.readServiceGroupsFile("test-files/incidents1.txt");
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
		csciti3list.add("Set up test VM. Awaiting verification from caller");
		csciti3list.add("VM works great, please deploy the test");
		csciti3list.add("VMs deployed. Marked resolved");
		csciti3list.add("One of the VMs has the wrong version of Checkstyle installed.");
		csciti3list.add("Updated version of Checkstyle.");
		csciti4list.add("Jenkins requires VPN to access.  Please open to general access.");
		csciti4list.add("Assigned to C. Gurley");
		itecslist.add("I can't install Java on my computer.");
		itecslist.add("Assigned to itecs1");
		oitlist.add("I forgot my password and can't log into NC State accounts");
		oitlist.add("OIT staff member on call with support");
		a.get(0).addIncident(new Incident(2, "Canceled", "Piazza", "sesmith5", 0, "Unowned", "Not an Incident", csciti1list));
		a.get(0).addIncident(new Incident(3, "New", "Moodle down", "sesmith5", 0, "Unowned", "No Status", csciti2list));
		a.get(0).addIncident(new Incident(4, "Resolved", "Set up Jenkins VMs", "sesmith5", 1, "cgurley", "Permanently Solved", csciti3list));
		a.get(0).addIncident(new Incident(9, "In Progress", "Jenkins behind firewall", "sesmith5", 0, "cgurley", "No Status", csciti4list));
		a.get(1).addIncident(new Incident(7, "On Hold", "Java not installed correctly", "zmgrosec", 0, "itecs1", "Awaiting Caller", itecslist));
		a.get(2).addIncident(new Incident(1, "In Progress", "Forgot password", "jctetter", 0, "oit_staff", "No Status", oitlist));
		assertEquals(a.get(0).getServiceGroupName(), s.get(0).getServiceGroupName());
		assertEquals(a.get(0).getIncidents(), a.get(0).getIncidents());
		assertEquals(a.get(1).getServiceGroupName(), s.get(1).getServiceGroupName());
		assertEquals(a.get(1).getIncidents(), a.get(1).getIncidents());
		assertEquals(a.get(2).getServiceGroupName(), s.get(2).getServiceGroupName());
		assertEquals(a.get(2).getIncidents(), a.get(2).getIncidents());
		try {
			ArrayList<ServiceGroup> l = ServiceGroupsReader.readServiceGroupsFile("not_a_file.not");
		} catch (IllegalArgumentException e) {
			assertEquals("Unable to load file.", e.getMessage());
		}
	}

}
