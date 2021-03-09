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
	public ServiceGroup(String name) {
		setServiceGroupName(name);
		incident = new ArrayList<Incident>();
	}
	/**
	 * Sets the counter for the incidents in the service group to the max id found in the service group plus 1
	 */
	public void setIncidentCounter() {
		Incident.setCounter(incident.get(incident.size()-1).getId()+1);
	}
	/**
	 * Sets the service groups name to the given name
	 * @param name the name to set
	 */
	public void setServiceGroupName(String name) {
		if(name == null || "".equals(name)) {
			throw new IllegalArgumentException("Service group cannot be created");
		}
		this.serviceGroupName = name;
	}
	/**
	 * Gets the service group's name
	 * @return the name of the service group
	 */
	public String getServiceGroupName() {
		return serviceGroupName;
	}
	/**
	 * Adds the given incident to the service group's list
	 * @param incident the incident to add
	 */
	public void addIncident(Incident incident) {
		boolean notadd = true;
		int size = this.incident.size();
		for(int i = 0; i < size; i++) {
			if(this.incident.get(i).getId() > incident.getId()) {
				this.incident.add(i, incident);
				notadd = false;
			}
			else if(this.incident.get(i).getId() == incident.getId()) {
				throw new IllegalArgumentException("Incident cannot be created");
			}
		}
		if(notadd) {
			this.incident.add(incident);
		}
		setIncidentCounter();
	}
	/**
	 * Gets the service group's list of incidents
	 * @return the list of incidents in the service group
	 */
	public ArrayList<Incident> getIncidents(){
		return incident;
	}
	/**
	 * Gets an incident in the service group's incident list with the given id
	 * @param id the id of the incident to get
 	 * @return the incident with the given id, null if there is no incident with the id
	 */
	public Incident getIncidentById(int id) {
		for(int i = 0; i < incident.size(); i++) {
			if(incident.get(i).getId() == id) {
				return incident.get(i);
			}
		}
		return null;
	}
	/**
	 * Removes an incident in the service group's incident list with the given id
	 * @param id the id of the incident to remove							
	 */
	public void deleteIncidentById(int id) {
		for(int i = 0; i < incident.size(); i++) {
			if(incident.get(i).getId() == id) {
				incident.remove(i);
			}
		}
		setIncidentCounter();
	}
	/**
	 * Executes the given command on the incident with the given id
	 * @param id the id of the incident to execute the command on 
	 * @param c the command to execute
	 */
	public void executeCommand(int id, Command c) {
		for(int i = 0; i < incident.size(); i++) {
			if(incident.get(i).getId() == id) {
				incident.get(i).update(c);
			}
		}
	}
}
