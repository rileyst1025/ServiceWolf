package edu.ncsu.csc216.service_wolf.model.manager;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

/**
 * Tests ServiceWolfManager and all its associated methods
 * @author rsthoma5
 *
 */
public class ServiceWolfManagerTest {

	/**
	 * Resets ServiceWolfManager Before every test
	 */
	@Before
	public void setUp() {
		ServiceWolfManager.getInstance().resetManager();
	}
	/**
	 * Checks that getInstance() returns the single instance of wolfScheduler
	 */
	@Test
	public void testGetInstance() {
		ServiceWolfManager sw = ServiceWolfManager.getInstance();
		assertNotNull(ServiceWolfManager.getInstance());
		assertNotNull(sw);
	}

	/**
	 * Checks that add service group adds a valid group and throws an exception for an invalid or duplicate group
	 */
	@Test
	public void testAddServiceGroup() {
		ServiceWolfManager sw = ServiceWolfManager.getInstance();
		sw.addServiceGroup("Awesome Group");
		assertEquals(1, sw.getServiceGroupList().length);
		assertEquals("Awesome Group", sw.getServiceGroupList()[0]);
		sw.addServiceGroup("Cool Group");
		assertEquals(2, sw.getServiceGroupList().length);
		assertEquals("Awesome Group", sw.getServiceGroupList()[0]);
		assertEquals("Cool Group", sw.getServiceGroupList()[1]);
		sw.addServiceGroup("Boring Group");
		assertEquals(3, sw.getServiceGroupList().length);
		assertEquals("Awesome Group", sw.getServiceGroupList()[0]);
		assertEquals("Boring Group", sw.getServiceGroupList()[1]);
		assertEquals("Cool Group", sw.getServiceGroupList()[2]);
		try {
			sw.addServiceGroup("Awesome Group");
			fail();
		} catch(IllegalArgumentException e) {
			assertEquals("Invalid service group name.", e.getMessage());
		}
		try {
			sw.addServiceGroup("");
			fail();
		} catch(IllegalArgumentException e) {
			assertEquals("Invalid service group name.", e.getMessage());
		}
		try {
			sw.addServiceGroup(null);
			fail();
		} catch(IllegalArgumentException e) {
			assertEquals("Invalid service group name.", e.getMessage());
		}
	}
	
	/**
	 * Checks that the edit service group method correctly edits a service group's name
	 */
	@Test
	public void testEditServiceGroup() {
		ServiceWolfManager sw = ServiceWolfManager.getInstance();
		sw.addServiceGroup("Cool Group");
		sw.addIncidentToServiceGroup("i1", "caller1", "message1");
		sw.addIncidentToServiceGroup("i2", "caller2", "message2");
		sw.editServiceGroup("Awesome Group");
		assertEquals("Awesome Group", sw.getServiceGroupName());
		assertEquals("i1", sw.getIncidentById(1).getTitle());
		assertEquals("i2", sw.getIncidentById(2).getTitle());
		assertEquals("caller1", sw.getIncidentById(1).getCaller());
		assertEquals("caller2", sw.getIncidentById(2).getCaller());
		assertEquals("- message1\n", sw.getIncidentById(1).getIncidentLogMessages());
		assertEquals("- message2\n", sw.getIncidentById(2).getIncidentLogMessages());
		sw.addServiceGroup("Dope Group");
		try {
			sw.editServiceGroup("Dope Group");
		} catch(IllegalArgumentException e) {
			assertEquals("Invalid service group name.", e.getMessage());
		}
		try {
			sw.editServiceGroup("");
		} catch(IllegalArgumentException e) {
			assertEquals("Invalid service group name.", e.getMessage());
		}
		try {
			sw.editServiceGroup(null);
		} catch(IllegalArgumentException e) {
			assertEquals("Invalid service group name.", e.getMessage());
		}
	}
	
	/**
	 * Checks that deleteServiceGroup successfully deletes the current service group
	 */
	@Test
	public void testDeleteServiceGroup() {
		ServiceWolfManager sw = ServiceWolfManager.getInstance();
		try {
			sw.deleteServiceGroup();
		} catch(IllegalArgumentException e) {
			assertEquals(0, sw.getServiceGroupList().length);
		}
		sw.addServiceGroup("Cool Group");
		sw.addServiceGroup("Fine Group");
		sw.addServiceGroup("Very Cool Group");
		assertEquals(3, sw.getServiceGroupList().length);
		assertEquals("Cool Group", sw.getServiceGroupList()[0]);
		assertEquals("Fine Group", sw.getServiceGroupList()[1]);
		assertEquals("Very Cool Group", sw.getServiceGroupList()[2]);
		sw.loadServiceGroup("Fine Group");
		sw.deleteServiceGroup();
		assertEquals(2, sw.getServiceGroupList().length);
		assertEquals("Cool Group", sw.getServiceGroupList()[0]);
		assertEquals("Very Cool Group", sw.getServiceGroupList()[1]);
		assertEquals("Cool Group", sw.getServiceGroupName());
		sw.deleteServiceGroup();
		assertEquals(1, sw.getServiceGroupList().length);
		assertEquals("Very Cool Group", sw.getServiceGroupList()[0]);
		assertEquals("Very Cool Group", sw.getServiceGroupName());
		sw.deleteServiceGroup();
		assertEquals(0, sw.getServiceGroupList().length);
		assertNull(sw.getServiceGroupName());
	}
	
	/**
	 * Checks that load service group successfully designates the correct group as the current group
	 */
	@Test
	public void testLoadServiceGroup() {
		ServiceWolfManager sw = ServiceWolfManager.getInstance();
		sw.addServiceGroup("Cool Group");
		sw.addServiceGroup("Fine Group");
		sw.addServiceGroup("Very Cool Group");
		sw.loadServiceGroup("Cool Group");
		assertEquals("Cool Group", sw.getServiceGroupName());
		sw.addIncidentToServiceGroup("Cool Group i1", "Cool Group Caller1", "Cool Group Message1");
		sw.addIncidentToServiceGroup("Cool Group i2", "Cool Group Caller2", "Cool Group Message2");
		sw.loadServiceGroup("Dope Group");
		assertEquals("Cool Group", sw.getServiceGroupName());
		sw.loadServiceGroup("Fine Group");
		assertEquals(0, sw.getIncidentsAsArray().length);
		assertEquals("Fine Group", sw.getServiceGroupName());
		sw.loadServiceGroup("Cool Group");
		assertEquals(2, sw.getIncidentsAsArray().length);
		assertEquals("1", sw.getIncidentsAsArray()[0][0]);
		assertEquals("New", sw.getIncidentsAsArray()[0][1]);
		assertEquals("Cool Group i1", sw.getIncidentsAsArray()[0][2]);
		assertEquals("No Status", sw.getIncidentsAsArray()[0][3]);
		assertEquals("2", sw.getIncidentsAsArray()[1][0]);
		assertEquals("New", sw.getIncidentsAsArray()[1][1]);
		assertEquals("Cool Group i2", sw.getIncidentsAsArray()[1][2]);
		assertEquals("No Status", sw.getIncidentsAsArray()[1][3]);
		assertEquals("Cool Group", sw.getServiceGroupName());
	}
	
	/**
	 * Checks that the load from file correctly reads in service groups from a file and the first group from the file is made the current service group
	 */
	@Test
	public void testLoadFromfile() {
		ServiceWolfManager sw = ServiceWolfManager.getInstance();
		sw.loadFromFile("test-files/incidents1.txt");
		assertEquals("CSC IT", sw.getServiceGroupList()[0]);
		assertEquals("ITECS", sw.getServiceGroupList()[1]);
		assertEquals("OIT", sw.getServiceGroupList()[2]);
		assertEquals("CSC IT", sw.getServiceGroupName());
		assertEquals("2", sw.getIncidentsAsArray()[0][0]);
		assertEquals("Canceled", sw.getIncidentsAsArray()[0][1]);
		assertEquals("Piazza", sw.getIncidentsAsArray()[0][2]);
		assertEquals("Not an Incident", sw.getIncidentsAsArray()[0][3]);
		assertEquals("3", sw.getIncidentsAsArray()[1][0]);
		assertEquals("New", sw.getIncidentsAsArray()[1][1]);
		assertEquals("Moodle down", sw.getIncidentsAsArray()[1][2]);
		assertEquals("No Status", sw.getIncidentsAsArray()[1][3]);
		assertEquals("4", sw.getIncidentsAsArray()[2][0]);
		assertEquals("Resolved", sw.getIncidentsAsArray()[2][1]);
		assertEquals("Set up Jenkins VMs", sw.getIncidentsAsArray()[2][2]);
		assertEquals("Permanently Solved", sw.getIncidentsAsArray()[2][3]);
		assertEquals("9", sw.getIncidentsAsArray()[3][0]);
		assertEquals("In Progress", sw.getIncidentsAsArray()[3][1]);
		assertEquals("Jenkins behind firewall", sw.getIncidentsAsArray()[3][2]);
		assertEquals("No Status", sw.getIncidentsAsArray()[3][3]);
		sw.loadServiceGroup("ITECS");
		assertEquals("ITECS", sw.getServiceGroupName());
		assertEquals("7", sw.getIncidentsAsArray()[0][0]);
		assertEquals("On Hold", sw.getIncidentsAsArray()[0][1]);
		assertEquals("Java not installed correctly", sw.getIncidentsAsArray()[0][2]);
		assertEquals("Awaiting Caller", sw.getIncidentsAsArray()[0][3]);
		sw.loadServiceGroup("OIT");
		assertEquals("OIT", sw.getServiceGroupName());
		assertEquals("1", sw.getIncidentsAsArray()[0][0]);
		assertEquals("In Progress", sw.getIncidentsAsArray()[0][1]);
		assertEquals("Forgot password", sw.getIncidentsAsArray()[0][2]);
		assertEquals("No Status", sw.getIncidentsAsArray()[0][3]);
		sw.resetManager();
		sw = ServiceWolfManager.getInstance();
		sw.loadFromFile("test-files/incidents3.txt");
		assertEquals("CSC IT", sw.getServiceGroupList()[0]);
		assertEquals("ITECS", sw.getServiceGroupList()[1]);
		assertEquals("OIT", sw.getServiceGroupList()[2]);
		assertEquals("OIT", sw.getServiceGroupName());
		assertEquals("OIT", sw.getServiceGroupName());
		assertEquals("1", sw.getIncidentsAsArray()[0][0]);
		assertEquals("In Progress", sw.getIncidentsAsArray()[0][1]);
		assertEquals("Forgot password", sw.getIncidentsAsArray()[0][2]);
		assertEquals("No Status", sw.getIncidentsAsArray()[0][3]);
		sw.loadServiceGroup("CSC IT");
		assertEquals("2", sw.getIncidentsAsArray()[0][0]);
		assertEquals("Canceled", sw.getIncidentsAsArray()[0][1]);
		assertEquals("Piazza", sw.getIncidentsAsArray()[0][2]);
		assertEquals("Not an Incident", sw.getIncidentsAsArray()[0][3]);
		assertEquals("3", sw.getIncidentsAsArray()[1][0]);
		assertEquals("New", sw.getIncidentsAsArray()[1][1]);
		assertEquals("Moodle down", sw.getIncidentsAsArray()[1][2]);
		assertEquals("No Status", sw.getIncidentsAsArray()[1][3]);
		assertEquals("4", sw.getIncidentsAsArray()[2][0]);
		assertEquals("Resolved", sw.getIncidentsAsArray()[2][1]);
		assertEquals("Set up Jenkins VMs", sw.getIncidentsAsArray()[2][2]);
		assertEquals("Permanently Solved", sw.getIncidentsAsArray()[2][3]);
		assertEquals("9", sw.getIncidentsAsArray()[3][0]);
		assertEquals("In Progress", sw.getIncidentsAsArray()[3][1]);
		assertEquals("Jenkins behind firewall", sw.getIncidentsAsArray()[3][2]);
		assertEquals("No Status", sw.getIncidentsAsArray()[3][3]);
		sw.loadServiceGroup("ITECS");
		assertEquals("ITECS", sw.getServiceGroupName());
		assertEquals("7", sw.getIncidentsAsArray()[0][0]);
		assertEquals("On Hold", sw.getIncidentsAsArray()[0][1]);
		assertEquals("Java not installed correctly", sw.getIncidentsAsArray()[0][2]);
		assertEquals("Awaiting Caller", sw.getIncidentsAsArray()[0][3]);
		try {
			sw.loadFromFile("not_a_file.not");
			fail();
		} catch(IllegalArgumentException e) {
			assertEquals("Unable to load file.", e.getMessage());
		}
	}
	
	/**
	 * Checks that save to file successfully write the managers service group list to a file as well as throws the correct exception when there isnt anything to write to said file
	 */
	@Test
	public void testSaveToFile() {
		ServiceWolfManager sw = ServiceWolfManager.getInstance();
		sw.addServiceGroup("Cool Group");
		sw.addServiceGroup("Dope Group");
		sw.loadServiceGroup("Cool Group");
		sw.addIncidentToServiceGroup("Cool Group i1", "Cool Group Caller1", "Cool Group Message1");
		sw.addIncidentToServiceGroup("Cool Group i2", "Cool Group Caller2", "Cool Group Message2");
		sw.loadServiceGroup("Dope Group");
		sw.addIncidentToServiceGroup("Dope Group i1", "Dope Group Caller1", "Dope Group Message1");
		sw.saveToFile("test-files/testgroups.txt");
		sw.resetManager();
		sw = ServiceWolfManager.getInstance();
		sw.loadFromFile("test-files/testgroups.txt");
		assertEquals("Cool Group", sw.getServiceGroupName());
		assertEquals("1", sw.getIncidentsAsArray()[0][0]);
		assertEquals("New", sw.getIncidentsAsArray()[0][1]);
		assertEquals("Cool Group i1", sw.getIncidentsAsArray()[0][2]);
		assertEquals("No Status", sw.getIncidentsAsArray()[0][3]);
		assertEquals("2", sw.getIncidentsAsArray()[1][0]);
		assertEquals("New", sw.getIncidentsAsArray()[1][1]);
		assertEquals("Cool Group i2", sw.getIncidentsAsArray()[1][2]);
		assertEquals("No Status", sw.getIncidentsAsArray()[1][3]);
		sw.loadServiceGroup("Dope Group");
		assertEquals("Dope Group", sw.getServiceGroupName());
		assertEquals("1", sw.getIncidentsAsArray()[0][0]);
		assertEquals("New", sw.getIncidentsAsArray()[0][1]);
		assertEquals("Dope Group i1", sw.getIncidentsAsArray()[0][2]);
		assertEquals("No Status", sw.getIncidentsAsArray()[0][3]);
	}
}
