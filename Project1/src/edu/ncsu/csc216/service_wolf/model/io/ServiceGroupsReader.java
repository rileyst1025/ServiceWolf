package edu.ncsu.csc216.service_wolf.model.io;

import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;
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
		try {
			File f = new File(filename);
			Scanner fileReader = new Scanner(f);
			String fileinfo = "\n";
			while (fileReader.hasNextLine()) {
				fileinfo += fileReader.nextLine() + "\n";
			}
			fileReader.close();
			ArrayList<ServiceGroup> rlist = new ArrayList<ServiceGroup>();
			String[] ServiceGroupTokens = fileinfo.split("\\r?\\n[#]");
			for(int i = 1; i < ServiceGroupTokens.length; i++) {
				String[] IncidentTokens = ServiceGroupTokens[i].split("\\r?\\n?[*]");
				ServiceGroup addgroup = processServiceGroup(IncidentTokens[0]);
				for(int j = 1; j < IncidentTokens.length; j++) {
					addgroup.addIncident(processIncident(IncidentTokens[j]));
				}
				rlist.add(addgroup);
			}
			return rlist;
		} catch(FileNotFoundException e) {
			throw new IllegalArgumentException("Unable to load file");
		}
	}
	/**
	 * Creates a service group based on the given line of text
	 * @param sg the line of text to create the service group from
	 * @return the service group created from the line
	 */
	private static ServiceGroup processServiceGroup(String sg) {
		ServiceGroup rgroup = new ServiceGroup(sg.trim());
		return rgroup;
	}
	/**
	 * Creates an incident based on the given line of text
	 * @param i the line of text to create the incident from
	 * @return the incident created from the line
	 */
	private static Incident processIncident(String i) {
		String[] incidentLines = i.split("\\r?\\n?[-]");
		String[] incidentParams = incidentLines[0].split(",");
		ArrayList<String> messageLog = new ArrayList<String>();
		for(int k = 1; k < incidentLines.length; k++) {
			messageLog.add(incidentLines[k].substring(1).trim());
		}
		return new Incident(Integer.parseInt(incidentParams[0].trim()), incidentParams[1], incidentParams[2], incidentParams[3], Integer.parseInt(incidentParams[4]), incidentParams[5], incidentParams[6], messageLog);
	}
}
