package at.emundo.osgi.module2;

import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceReference;
import org.osgi.util.tracker.ServiceTracker;
import org.osgi.util.tracker.ServiceTrackerCustomizer;

import at.emundo.osgi.persistence.Service2;

public class ServiceActivator implements BundleActivator,
		ServiceTrackerCustomizer {

	private ServiceTracker service2Tracker;
	private BundleContext context;

	@Override
	public void start(BundleContext context) throws Exception {
		this.context = context;

		System.out.println("ServiceActivator");
		
		service2Tracker = new ServiceTracker(context, Service2.class, this);

		service2Tracker.open();
		
	}

	@Override
	public void stop(BundleContext context) throws Exception {
		service2Tracker.close();

	}

	@Override
	public Object addingService(ServiceReference reference) {
		System.out.println("Found Service2 instance");
		Service2 s = (Service2)context.getService(reference);

		s.execute();

		return s;
	}

	@Override
	public void modifiedService(ServiceReference reference, Object service) {
		// TODO Auto-generated method stub

	}

	@Override
	public void removedService(ServiceReference reference, Object service) {
		// TODO Auto-generated method stub

	}

}
