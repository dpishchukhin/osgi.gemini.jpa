package at.emundo.osgi.module2;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import at.emundo.osgi.module1.Service1;
import at.emundo.osgi.persistence.Service2;

@Repository
@Configurable
public class DefaultService2 implements Service2 {

	private EntityManagerFactory emf;
	private EntityTwoRepository repo;

	@PersistenceContext
	private EntityManager entityManager;
	
	private Service1 service1;

	public DefaultService2() {

	}

	public void setRepo(EntityTwoRepository repo) {
		this.repo = repo;
	}

//	public void setEmf(EntityManagerFactory emf) {
//		this.emf = emf;
//	}

	public void setService1(Service1 service1) {
		this.service1 = service1;
	}

	@Autowired
	public DefaultService2(EntityManagerFactory emf, Service1 service1) {
		this.service1 = service1;
		this.emf = emf;
	}

	@Override
	@Transactional
	public void execute() {
		System.out.println(repo);
		System.out.println("Service1");
		service1.execute();

		System.out.println("Executing Service2");
		EntityTwo noah = new EntityTwo();
		noah.setName("marion");
		noah.setId(System.currentTimeMillis());

		System.out.println(emf);
//		EntityManager em = emf.createEntityManager();
//		em.getTransaction().begin();
		entityManager.persist(noah);

		// repo.save(noah);

//		em.getTransaction().commit();

	}
	/*
    {at.emundo.osgi.module2.Service2}
    =
    {org.eclipse.gemini.blueprint.bean.name=spring2Bean, org.springframework.osgi.bean.name=spring2Bean, osgi.service.blueprint.compname=spring2Bean, Bundle-SymbolicName=at.emundo.osgi.module2, Bundle-Version=0.1.0.SNAPSHOT, service.id=64}
    
    {org.eclipse.gemini.blueprint.context.DelegatedExecutionOsgiBundleApplicationContext, 
     org.eclipse.gemini.blueprint.context.ConfigurableOsgiBundleApplicationContext, 
     org.springframework.context.ConfigurableApplicationContext, 
     org.springframework.context.ApplicationContext, 
     org.springframework.context.Lifecycle, 
     java.io.Closeable, 
     org.springframework.beans.factory.ListableBeanFactory, 
     org.springframework.beans.factory.HierarchicalBeanFactory, 
     org.springframework.context.MessageSource, 
     org.springframework.context.ApplicationEventPublisher, 
     org.springframework.core.io.support.ResourcePatternResolver, 
     org.springframework.beans.factory.BeanFactory, 
     org.springframework.core.io.ResourceLoader, 
     java.lang.AutoCloseable, 
     org.springframework.beans.factory.DisposableBean}
    =
    {org.eclipse.gemini.blueprint.context.service.name=at.emundo.osgi.module2, 
    org.springframework.context.service.name=at.emundo.osgi.module2, 
    Bundle-SymbolicName=at.emundo.osgi.module2, 
    Bundle-Version=0.1.0.SNAPSHOT, 
    service.id=65}
*/
}
