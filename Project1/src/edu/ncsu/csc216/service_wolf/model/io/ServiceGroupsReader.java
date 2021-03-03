package edu.ncsu.csc216.service_wolf.model.io;

import java.util.ArrayList;
import edu.ncsu.csc216.service_wolf.model.incident.Incident;
import edu.ncsu.csc216.service_wolf.model.service_group.ServiceGroup;

/**
 * The ServiceGroupReader class is responsible for reading in service groups and their 
 * assigned incidents
 * @author rsthoma5
 *
 */
public class ServiceGroupsReader {

	/**
	 * Reads in and returns an array of service groups and their assigned incidents from a file with the given filename
	 * @param filename the name of the file to read from
	 * @return a list of the service groups from the file
	 */
	public static ArrayList<ServiceGroup> readServiceGroupsFile(String filename){
		return null;
	}
	/**
	 * Creates a service group based on the given line of text
	 * @param sg the line of text to create the service group from
	 * @return the service group created from the line
	 */
	private static ServiceGroup processServiceGroup(String sg) {
		return null;
	}
	/**
	 * Creates an incident based on the given line of text
	 * @param i the line of text to create the incident from
	 * @return the incident created from the line
	 */
	private static Incident processIncident(String i) {
		return null;
	}
}
