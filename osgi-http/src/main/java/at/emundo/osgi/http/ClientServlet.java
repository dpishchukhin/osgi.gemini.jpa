package at.emundo.osgi.http;

import java.io.IOException;
import java.io.Writer;

import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceReference;

import at.emundo.osgi.persistence.Service2;

public class ClientServlet extends HttpServlet {

	private BundleContext context;

	public ClientServlet(BundleContext context) {
		this.context = context;
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {

		final Writer writer = resp.getWriter();

		ServiceReference service2Reference = context.getServiceReference(Service2.class);
		
		if (service2Reference == null) {
			writer.write("the ServiceReference is null");
			return;
		}

		final BundleContext other = service2Reference.getBundle().getBundleContext();

		Object service2 = other.getService(service2Reference);

		if (service2 == null)
			writer.write("\nservice2 does not exist\n");
		else {
			writer.write("Execution service2\n");

			((Service2) service2).execute();

			writer.write("\nI win!\n");
		}
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		super.doPost(req, resp);
	}

	@Override
	public void service(ServletRequest req, ServletResponse res)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		super.service(req, res);
	}

}
