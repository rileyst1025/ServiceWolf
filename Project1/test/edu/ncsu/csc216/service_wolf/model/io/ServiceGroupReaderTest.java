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
		ArrayList<ServiceGroup> t = ServiceGroupsReader.readServiceGroupsFile("test-files/incidents1.txt");
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
		try {
			ArrayList<ServiceGroup> l = ServiceGroupsReader.readServiceGroupsFile("not_a_file.not");
			assertEquals(1, l.size());
		} catch (IllegalArgumentException e) {
			assertEquals("Unable to load file.", e.getMessage());
		}
		ArrayList<ServiceGroup> msgn = ServiceGroupsReader.readServiceGroupsFile("test-files/incidents4.txt");
		assertEquals(2, msgn.size());
		ArrayList<ServiceGroup> mid = ServiceGroupsReader.readServiceGroupsFile("test-files/incidents5.txt");
		assertEquals(0, mid.size());
		ArrayList<ServiceGroup> isn = ServiceGroupsReader.readServiceGroupsFile("test-files/incidents6.txt");
		assertEquals(0, isn.size());
		ArrayList<ServiceGroup> msn = ServiceGroupsReader.readServiceGroupsFile("test-files/incidents7.txt");
		assertEquals(0, msn.size());
		ArrayList<ServiceGroup> mt = ServiceGroupsReader.readServiceGroupsFile("test-files/incidents8.txt");
		assertEquals(0, mt.size());
		ArrayList<ServiceGroup> ec = ServiceGroupsReader.readServiceGroupsFile("test-files/incidents9.txt");
		assertEquals(0, ec.size());
		ArrayList<ServiceGroup> mc = ServiceGroupsReader.readServiceGroupsFile("test-files/incidents10.txt");
		assertEquals(0, mc.size());
		ArrayList<ServiceGroup> nrc = ServiceGroupsReader.readServiceGroupsFile("test-files/incidents11.txt");
		assertEquals(0, nrc.size());
		ArrayList<ServiceGroup> eo = ServiceGroupsReader.readServiceGroupsFile("test-files/incidents12.txt");
		assertEquals(0, eo.size());
		ArrayList<ServiceGroup> mo = ServiceGroupsReader.readServiceGroupsFile("test-files/incidents13.txt");
		assertEquals(0, mo.size());
		ArrayList<ServiceGroup> es = ServiceGroupsReader.readServiceGroupsFile("test-files/incidents14.txt");
		assertEquals(0, es.size());
		ArrayList<ServiceGroup> ms = ServiceGroupsReader.readServiceGroupsFile("test-files/incidents15.txt");
		assertEquals(0, ms.size());
		ArrayList<ServiceGroup> ml = ServiceGroupsReader.readServiceGroupsFile("test-files/incidents16.txt");
		assertEquals(0, ml.size());
		ArrayList<ServiceGroup> nswio = ServiceGroupsReader.readServiceGroupsFile("test-files/incidents17.txt");
		assertEquals(0, nswio.size());
		ArrayList<ServiceGroup> nswis = ServiceGroupsReader.readServiceGroupsFile("test-files/incidents18.txt");
		assertEquals(0, nswis.size());
		ArrayList<ServiceGroup> ipswio = ServiceGroupsReader.readServiceGroupsFile("test-files/incidents19.txt");
		assertEquals(0, ipswio.size());
		ArrayList<ServiceGroup> ipswis = ServiceGroupsReader.readServiceGroupsFile("test-files/incidents20.txt");
		assertEquals(0, ipswis.size());
		ArrayList<ServiceGroup> ohswio = ServiceGroupsReader.readServiceGroupsFile("test-files/incidents21.txt");
		assertEquals(0, ohswio.size());
		ArrayList<ServiceGroup> ohswis = ServiceGroupsReader.readServiceGroupsFile("test-files/incidents22.txt");
		assertEquals(0, ohswis.size());
		ArrayList<ServiceGroup> rswio = ServiceGroupsReader.readServiceGroupsFile("test-files/incidents23.txt");
		assertEquals(0, rswio.size());
		ArrayList<ServiceGroup> rswis = ServiceGroupsReader.readServiceGroupsFile("test-files/incidents24.txt");
		assertEquals(0, rswis.size());
		ArrayList<ServiceGroup> cswio = ServiceGroupsReader.readServiceGroupsFile("test-files/incidents25.txt");
		assertEquals(0, cswio.size());
		ArrayList<ServiceGroup> cswis = ServiceGroupsReader.readServiceGroupsFile("test-files/incidents26.txt");
		assertEquals(0, cswis.size());
		ArrayList<ServiceGroup> nii = ServiceGroupsReader.readServiceGroupsFile("test-files/incidents27.txt");
		assertEquals(0, nii.size());
	}

}
