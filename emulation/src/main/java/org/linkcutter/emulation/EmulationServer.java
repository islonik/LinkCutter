package org.linkcutter.emulation;

import com.google.inject.servlet.GuiceFilter;
import com.sun.grizzly.http.embed.GrizzlyWebServer;
import com.sun.grizzly.http.servlet.ServletAdapter;
import org.linkcutter.web.GuiceConfig;

import javax.servlet.http.HttpServlet;

/**
 * @author Lipatov Nikita
 */
public class EmulationServer {

    @SuppressWarnings("serial")
    public static class DefaultServlet extends HttpServlet { }

    public static void main(String[] args) throws Exception {
        int port = 8080;

        GrizzlyWebServer server = new GrizzlyWebServer(port);
        ServletAdapter adapter = new ServletAdapter(new DefaultServlet());
        adapter.addServletListener(GuiceConfig.class.getName());
        adapter.addFilter(new GuiceFilter(), "GuiceFilter", null);
        server.addGrizzlyAdapter(adapter, new String[]{ "/" });
        server.start();
    }
}
