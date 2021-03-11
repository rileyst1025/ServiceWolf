package edu.ncsu.csc216.service_wolf.model.manager;

import java.util.ArrayList;

import edu.ncsu.csc216.service_wolf.model.command.Command;
import edu.ncsu.csc216.service_wolf.model.incident.Incident;
import edu.ncsu.csc216.service_wolf.model.io.ServiceGroupWriter;
import edu.ncsu.csc216.service_wolf.model.io.ServiceGroupsReader;
import edu.ncsu.csc216.service_wolf.model.service_group.ServiceGroup;

/**
 * The ServiceWolfManager class holds a list of service groups, with one of them selected as the current service group which is the service 
 * group that is altered by ServiceWolfManager's methods
 * @author rsthoma5
 *
 */
public class ServiceWolfManager {
	
	/**The list of service groups in the manager**/
	private ArrayList<ServiceGroup> serviceGroups;
	/**The current selected service group**/
	private ServiceGroup currentServiceGroup;
	/**Holds the single allowed instance of ServiceWolfManager**/
	private static ServiceWolfManager singleton = null;
	
	/**
	 * Constructs a new ServiceWolfManager with an empty service group list
	 */
	private ServiceWolfManager() {
		serviceGroups = new ArrayList<ServiceGroup>();
		currentServiceGroup = null;
	}
	/**
	 * Creates an instance of ServiceWolfManager if once hasn't been created already
	 * @return the ServiceWolfManager if it can be created, null if not
	 */
	public static ServiceWolfManager getInstance() {
		if(singleton == null) {
			singleton = new ServiceWolfManager();
		}
		return singleton;
	}
	/**
	 * Adds a new service group to the service group list with the given name
	 * @param serviceGroupName the name of the new service group to add
	 */
	public void addServiceGroup(String serviceGroupName) {
		if(serviceGroupName == null || "".equals(serviceGroupName)) {
			throw new IllegalArgumentException("Invalid service group name.");
		}
		checkDuplicateServiceName(serviceGroupName);
		ServiceGroup addgroup = new ServiceGroup(serviceGroupName);
		addServiceGroupToListByName(addgroup);
		loadServiceGroup(serviceGroupName);
	}
	/**
	 * Adds the given service group to the manager's service group list
	 * @param sg the service group to add
	 */
	private void addServiceGroupToListByName(ServiceGroup sg) {
		boolean notadd = true;
		int sgidx = serviceGroups.size();
		for(int i = 0; i < sgidx; i++) {
			if(sg.getServiceGroupName().toLowerCase().compareTo(serviceGroups.get(i).getServiceGroupName().toLowerCase()) < 0){
				serviceGroups.add(i, sg);
				notadd = false;
				break;
			}
		}
		if(notadd) {
			serviceGroups.add(sg);
		}
	}
	/**
	 * Checks to see if the service group name is a duplicate of a previously added service group
	 * @param name the name to check if duplicate
	 */
	private void checkDuplicateServiceName(String name) {
		for(int i = 0; i < serviceGroups.size(); i++) {
			if(name.toLowerCase().equals(serviceGroups.get(i).getServiceGroupName().toLowerCase())) {
				throw new IllegalArgumentException("Invalid service group name.");
			}
		}
	}
	/**
	 * Changes the managers current service group's name to the given name
	 * @param updateName the name to change to
	 */
	public void editServiceGroup(String updateName) {
		if(updateName == null || "".equals(updateName)) {
			throw new IllegalArgumentException("Invalid service group name.");
		}
		checkDuplicateServiceName(updateName);
		ServiceGroup temp = currentServiceGroup;
		deleteServiceGroup();
		addServiceGroup(updateName);
		loadServiceGroup(updateName);
		for(int i = 0; i < temp.getIncidents().size(); i++) {
			currentServiceGroup.addIncident(temp.getIncidents().get(i));
		}
	}
	/**
	 * Deletes the manager's current service group
	 */
	public void deleteServiceGroup() {
		if(currentServiceGroup == null) {
			throw new IllegalArgumentException("No service group selected.");
		}
		for(int i = 0; i < serviceGroups.size(); i++) {
			if(currentServiceGroup.getServiceGroupName().equals(serviceGroups.get(i).getServiceGroupName())) {
				serviceGroups.remove(i);
				break;
			}
		}
		if(serviceGroups.size() == 0) {
			currentServiceGroup = null;
		}
		else {
			currentServiceGroup = serviceGroups.get(0);
		}
	}
	/**
	 * Makes the service group with the given name the current service group
	 * @param serviceGroupName the name of the service group to make the current service group
	 */
	public void loadServiceGroup(String serviceGroupName) {
		for(int i = 0; i < serviceGroups.size(); i++) {
			if(serviceGroups.get(i).getServiceGroupName().equals(serviceGroupName)) {
				currentServiceGroup = serviceGroups.get(i);
				if(currentServiceGroup.getIncidents().size() > 0) {
					Incident.setCounter(currentServiceGroup.getIncidents().get(currentServiceGroup.getIncidents().size() - 1).getId() + 1);
				}
				else {
					Incident.setCounter(1);
				}
			}
		}
	}
	/**
	 * Gets the current service group's name
	 * @return the name of the current service group
	 */
	public String getServiceGroupName() {
		if(currentServiceGroup == null) {
			return null;
		}
		return currentServiceGroup.getServiceGroupName();
	}
	/**
	 * Gets the manager's list of service groups
	 * @return the list of service groups from the manager
	 */
	public String[] getServiceGroupList(){
		String[] grouplist = new String[serviceGroups.size()];
		for(int i = 0; i < serviceGroups.size(); i++) {
			grouplist[i] = serviceGroups.get(i).getServiceGroupName();
		}
		return grouplist;
	}
	/**
	 * Clears all service groups from the manager
	 */
	public void clearServiceGroups() {
		serviceGroups = new ArrayList<ServiceGroup>();
		currentServiceGroup = null;
	}
	/**
	 * Loads service groups and their incidents from a file and adds them to the manager
	 * @param filename the name of the file to read from
	 */
	public void loadFromFile(String filename) {
		ArrayList<ServiceGroup> unsortlist = ServiceGroupsReader.readServiceGroupsFile(filename);
		String tempbegin = unsortlist.get(0).getServiceGroupName();
		for(int i = 0; i < unsortlist.size(); i++) {
			addServiceGroup(unsortlist.get(i).getServiceGroupName());
			loadServiceGroup(unsortlist.get(i).getServiceGroupName());
			for(int j = 0; j < unsortlist.get(i).getIncidents().size(); j++) {
				currentServiceGroup.addIncident(unsortlist.get(i).getIncidents().get(j));
			}
		}
		loadServiceGroup(tempbegin);
	}
	/**
	 * Saves the managers service group list to a file with the given filename
	 * @param filename the name of the file to save the list to
	 */
	public void saveToFile(String filename) {
		if(currentServiceGroup == null || currentServiceGroup.getIncidents().size() == 0) {
			throw new IllegalArgumentException("Unable to save file.");
		}
		ServiceGroupWriter.writeServiceGroupsToFile(filename, serviceGroups);
	}
	/**
	 * Gets a 2D array of the current group's incidents with id number, state name, title, and status details
	 * @return a 2D of the incidents in the current group
	 */
	public String[][] getIncidentsAsArray(){
		if(currentServiceGroup == null) {
			return null;
		}
		String[][] rarray = new String[currentServiceGroup.getIncidents().size()][4];
		for(int i = 0; i < currentServiceGroup.getIncidents().size(); i++) {
			rarray[i][0] = Integer.toString(currentServiceGroup.getIncidents().get(i).getId());
			rarray[i][1] = currentServiceGroup.getIncidents().get(i).getState();
			rarray[i][2] = currentServiceGroup.getIncidents().get(i).getTitle();
			rarray[i][3] = currentServiceGroup.getIncidents().get(i).getStatusDetails();
		}
		return rarray;
	}
	/**
	 * Gets the incident with the given id from the current service group
	 * @param id the id of the incident to get 
	 * @return the incident from the current service group with the given id
	 */
	public Incident getIncidentById(int id) {
		if(currentServiceGroup != null) {
			for(int i = 0; i < currentServiceGroup.getIncidents().size(); i++) {
				if(currentServiceGroup.getIncidents().get(i).getId() == id) {
					return currentServiceGroup.getIncidents().get(i);
				}
			}
		}
		return null;
	}
	/**
	 * Executes the given command on the incident with the given id from the current service group
	 * @param id the id of the incident to execute the command on
	 * @param command the command to execute
	 */
	public void executeCommand(int id, Command command) {
		if(currentServiceGroup != null) {
			currentServiceGroup.executeCommand(id, command);
		}
	}
	/**
	 * Deletes the incident from the current service group with the given id
	 * @param id the id of the incident to delete
	 */
	public void deleteIncidentById(int id) {
		if(currentServiceGroup != null) {
			currentServiceGroup.deleteIncidentById(id);
		}
	}
	/**
	 * Adds a new incident with the given title, caller, and message to the current service group
	 * @param title the title of the incident to add
	 * @param caller the caller of the incident to add
	 * @param message the message of the incident to add
	 */
	public void addIncidentToServiceGroup(String title, String caller, String message) {
		if(currentServiceGroup != null) {
			Incident addi = new Incident(title, caller, message);
			currentServiceGroup.addIncident(addi);
		}
	}
	/**
	 * Resets the manager by setting the singleton to null
	 */
	protected void resetManager() {
		singleton = null;
	}
}
