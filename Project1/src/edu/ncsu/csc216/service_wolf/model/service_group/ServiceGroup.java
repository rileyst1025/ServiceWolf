package edu.ncsu.csc216.service_wolf.model.service_group;

import java.util.ArrayList;
import edu.ncsu.csc216.service_wolf.model.command.Command;
import edu.ncsu.csc216.service_wolf.model.incident.Incident;

/**
 * The ServiceGroup class represents an object with a name and a list of incidents that are assigned to it
 * @author rsthoma5
 *
 */
public class ServiceGroup {

	/**The name of the service group**/
	private String serviceGroupName;
	/**The list of incidents assigned to the service group**/
	private ArrayList<Incident> incident;
	/**
	 * Constructs a new service group with the given name and an empty incident list
	 * @param name the name to set
	 */
	public void ServiceGroup(String name) {
		
	}
	/**
	 * Sets the counter for the incidents in the service group to the max id found in the service group plus 1
	 */
	public void setIncidentCounter() {
		
	}
	/**
	 * Gets the service group's name
	 * @return the name of the service group
	 */
	public String getServiceGroupName() {
		return null;
	}
	/**
	 * Adds the given incident to the service group's list
	 * @param incident the incident to add
	 */
	public void addIncident(Incident incident) {
		
	}
	/**
	 * Gets the service group's list of incidents
	 * @return the list of incidents in the service group
	 */
	public ArrayList<Incident> getIncidents(){
		return null;
	}
	/**
	 * Gets an incident in the service group's incident list with the given id
	 * @param id the id of the incident to get
 	 * @return the incident with the given id, null if there is no incident with the id
	 */
	public Incident getIncidentById(int id) {
		return null;
	}
	/**
	 * Removes an incident in the service group's incident list with the given id
	 * @param id the id of the incident to remove							
	 */
	public void deleteIncidentById(int id) {
		
	}
	/**
	 * Executes the given command on the incident with the given id
	 * @param id the id of the incident to execute the command on 
	 * @param c the command to execute
	 */
	public void executeCommand(int id, Command c) {
		
	}
}
