package edu.ncsu.csc216.service_wolf.model.manager;

import edu.ncsu.csc216.service_wolf.model.command.Command;
import edu.ncsu.csc216.service_wolf.model.incident.Incident;
import edu.ncsu.csc216.service_wolf.model.service_group.ServiceGroup;

/**
 * The ServiceWolfManager class holds a list of service groups, with one of them selected as the current service group which is the service 
 * group that is altered by ServiceWolfManager's methods
 * @author rsthoma5
 *
 */
public class ServiceWolfManager {
	
	/**The list of service groups in the manager**/
	private String[] serviceGroups;
	/**The current selected service group**/
	private ServiceGroup currentServiceGroup;
	/**Holds the single allowed instance of ServiceWolfManager**/
	private ServiceWolfManager singleton = null;
	
	/**
	 * Constructs a new ServiceWolfManager with an empty service group list
	 */
	private void ServiceWolfManager() {
		
	}
	/**
	 * Creates an instance of ServiceWolfManager if once hasn't been created already
	 * @return the ServiceWolfManager if it can be created, null if not
	 */
	public static ServiceWolfManager getInstance() {
		return null;
	}
	/**
	 * Adds a new service group to the service group list with the given name
	 * @param serviceGroupName the name of the new service group to add
	 */
	public void addServiceGroup(String serviceGroupName) {
		
	}
	/**
	 * Adds the given service group to the manager's service group list
	 * @param sg the service group to add
	 */
	private void addServiceGroupToListByName(ServiceGroup sg) {
		
	}
	/**
	 * Checks to see if the service group name is a duplicate of a previously added service group
	 * @param name the name to check if duplicate
	 */
	private void checkDuplicateServiceName(String name) {
		
	}
	/**
	 * Changes the managers current service group's name to the given name
	 * @param updateName the name to change to
	 */
	public void editServiceGroup(String updateName) {
		
	}
	/**
	 * Deletes the manager's current service group
	 */
	public void deleteServiceGroup() {
		
	}
	/**
	 * Makes the service group with the given name the current service group
	 * @param serviceGroupName the name of the service group to make the current service group
	 */
	public void loadServiceGroup(String serviceGroupName) {
		
	}
	/**
	 * Gets the current service group's name
	 * @return the name of the current service group
	 */
	public String getServiceGroupName() {
		return null;
	}
	/**
	 * Gets the manager's list of service groups
	 * @return the list of service groups from the manager
	 */
	public String[] getServiceGroupList(){
		return null;
	}
	/**
	 * Clears all service groups from the manager
	 */
	public void clearServiceGroups() {
		
	}
	/**
	 * Loads service groups and their incidents from a file and adds them to the manager
	 * @param filename the name of the file to read from
	 */
	public void loadFromFile(String filename) {
		
	}
	/**
	 * Saves the managers service group list to a file with the given filename
	 * @param filename the name of the file to save the list to
	 */
	public void saveToFile(String filename) {
		
	}
	/**
	 * Gets a 2D array of the current group's incidents with id number, state name, title, and status details
	 * @return a 2D of the incidents in the current group
	 */
	public String[][] getIncidentsAsArray(){
		return null;
	}
	/**
	 * Gets the incident with the given id from the current service group
	 * @param id the id of the incident to get 
	 * @return the incident from the current service group with the given id
	 */
	public Incident getIncidentById(int id) {
		return null;
	}
	/**
	 * Executes the given command on the incident with the given id from the current service group
	 * @param id the id of the incident to execute the command on
	 * @param command the command to execute
	 */
	public void executeCommand(int id, Command command) {
		
	}
	/**
	 * Deletes the incident from the current service group with the given id
	 * @param id the id of the incident to delete
	 */
	public void deleteIncidentById(int id) {
		
	}
	/**
	 * Adds a new incident with the given title, caller, and message to the current service group
	 * @param title the title of the incident to add
	 * @param caller the caller of the incident to add
	 * @param message the message of the incident to add
	 */
	public void addIncidentToServiceGroup(String title, String caller, String message) {
		
	}
	/**
	 * Resets the manager by setting the singleton to null
	 */
	protected void resetManager() {
		
	}
}
