package org.tests.dsf.gdb.framework;

import java.util.HashMap;
import java.util.Map;

import org.eclipse.cdt.dsf.gdb.service.GdbDebugServicesFactory;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;

import main.Activator;

/**
 * This is a Debug Service Factories Manager which keeps track of the factories provided by specific test cases.
 * This allow individual tests to override DSF-GDB Services which is sometimes needed to validate different
 * code paths.
 * 
 * The test is in charge of providing a unique id for the given factory, however this class will trigger an
 * exception if a duplicate id is detected.
 * 
 * This id can then be shared via launch attributes, since each individual test method has its
 * own launch configuration there is no possibility to override the launch attributes by other tests 
 * 
 * Users can then retrieve/remove the registered factory via the unique factory id provided by the test
 */
public class ServiceFactoriesManager {
	public final static String DEBUG_SERVICES_FACTORY_KEY = Activator.PLUGIN_ID + ".DEBUG_SERVICES_FACTORY";

	private final Map<String, GdbDebugServicesFactory> fTestServiceFactoriesMap = new HashMap<>();

	public void addTestServicesFactory(String id, GdbDebugServicesFactory servicesFactory)
			throws CoreException {
		if (fTestServiceFactoriesMap.containsKey(id)) {
			throw new CoreException(new Status(IStatus.ERROR, Activator.getUniqueIdentifier(),
					"A factory with this id already exists " + id));
		}

		fTestServiceFactoriesMap.put(id, servicesFactory);
	}

	public GdbDebugServicesFactory removeTestServicesFactory(String id) {
		return fTestServiceFactoriesMap.remove(id);
	}
}