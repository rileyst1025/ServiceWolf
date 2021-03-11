package edu.ncsu.csc216.service_wolf.model.io;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.util.ArrayList;
import edu.ncsu.csc216.service_wolf.model.service_group.ServiceGroup;

/**
 * The ServiceGroupWriter class is responsible for writing a list of service groups to a file
 * @author rsthoma5
 *
 */
public class ServiceGroupWriter {

	/**
	 * Writes a given list of service groups to a file with the given filename
	 * @param filename the name of the file to write to
	 * @param grouplist the list of service groups to write to the file
	 */
	public static void writeServiceGroupsToFile(String filename, ArrayList<ServiceGroup> grouplist) {
		try {
			PrintStream fileWriter = new PrintStream(new File(filename));
			for (int i = 0; i < grouplist.size(); i++) {
			    fileWriter.println("# " + grouplist.get(i).getServiceGroupName());
			    for(int j = 0; j < grouplist.get(i).getIncidents().size(); j++) {
				    fileWriter.println(grouplist.get(i).getIncidents().get(j).toString().substring(0, grouplist.get(i).getIncidents().get(j).toString().length() - 1));
			    }
			}
			fileWriter.close();
		} catch(FileNotFoundException e) {
			throw new IllegalArgumentException("Unable to save file.");
		}
	}
}
