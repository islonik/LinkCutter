package org.linkcutter.emulation;

import com.sun.grizzly.http.embed.GrizzlyWebServer;
import com.sun.grizzly.http.servlet.ServletAdapter;
import com.sun.jersey.spi.spring.container.servlet.SpringServlet;
import javax.servlet.http.HttpServlet;

/**
 * @author Lipatov Nikita
 */
public class EmulationServer {

    @SuppressWarnings("serial")
    public static class DefaultServlet extends HttpServlet { }

    public static void main(String[] args) throws Exception {
        int port = 8080;

        // Initialize Grizzly HttpServer
        GrizzlyWebServer server = new GrizzlyWebServer(port);
        ServletAdapter adapter = new ServletAdapter("jersey");
        adapter.setContextPath("/api");
        adapter.addContextParameter("contextConfigLocation", "classpath:application.xml");
        adapter.setServletInstance(new SpringServlet());

        adapter.addServletListener("org.springframework.web.context.ContextLoaderListener");
        adapter.addServletListener("org.springframework.web.context.request.RequestContextListener");
        server.addGrizzlyAdapter(adapter, new String[]{ "/" });
        server.start();
    }
}
