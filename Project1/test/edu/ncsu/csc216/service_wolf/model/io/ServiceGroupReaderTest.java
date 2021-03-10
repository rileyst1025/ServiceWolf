package edu.ncsu.csc216.service_wolf.model.io;

import static org.junit.Assert.*;
import java.util.ArrayList;
import org.junit.Test;
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
		assertEquals(3, s.size());
	}

}
