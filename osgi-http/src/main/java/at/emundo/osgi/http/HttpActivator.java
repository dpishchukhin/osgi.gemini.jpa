package at.emundo.osgi.http;

import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceReference;
import org.osgi.service.http.HttpService;
import org.osgi.util.tracker.ServiceTracker;
import org.osgi.util.tracker.ServiceTrackerCustomizer;

public class HttpActivator implements BundleActivator, ServiceTrackerCustomizer {

	private BundleContext context;

	private ServiceTracker http;

	private HttpService service;
	
	@Override
	public void start(BundleContext context) throws Exception {
		this.context = context;

		http = new ServiceTracker(this.context, HttpService.class, this);
		http.open();
	}

	@Override
	public void stop(BundleContext context) throws Exception {
		http.close();
		service.unregister("/jpa");
	}

	@Override
	public Object addingService(ServiceReference reference) {

		BundleContext b = reference.getBundle().getBundleContext();
		service = (HttpService)b.getService(reference);

		System.out.println("Register new servlet");
		try {
			service.registerServlet("/jpa", new ClientServlet(context), null,
					null);
		} catch (Exception e) {
			System.out.println("Cannot register jpa servlet");
		}
		return service;
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
