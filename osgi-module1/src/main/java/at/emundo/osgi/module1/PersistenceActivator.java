package at.emundo.osgi.module1;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

import org.osgi.framework.Bundle;
import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceReference;
import org.osgi.service.jpa.EntityManagerFactoryBuilder;
import org.osgi.util.tracker.ServiceTracker;
import org.osgi.util.tracker.ServiceTrackerCustomizer;

public class PersistenceActivator implements BundleActivator,
		ServiceTrackerCustomizer {

	private ServiceTracker emfTracker;
	private BundleContext context;

	@Override
	public void start(BundleContext context) throws Exception {
		this.context = context;
		emfTracker = new ServiceTracker(this.context,
				EntityManagerFactory.class.getName(), this);
		emfTracker.open();
	}

	@Override
	public void stop(BundleContext context) throws Exception {

	}

	@Override
	public Object addingService(ServiceReference reference) {
		Bundle b = reference.getBundle();

		String unitName = (String) reference
				.getProperty(EntityManagerFactoryBuilder.JPA_UNIT_NAME);

		System.out.println("JPA Unit Name = " + unitName);

		Object service = b.getBundleContext().getService(reference);

		persistEntity((EntityManagerFactory) service);
		
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

	public void persistEntity(EntityManagerFactory emf) {
		EntityManager em = emf.createEntityManager();
		
		em.getTransaction().begin();
		
		EntityOne o = new EntityOne();
		o.setId(System.currentTimeMillis());
		o.setName("Kris");
		
		em.persist(o);
		em.getTransaction().commit();
	}
}
