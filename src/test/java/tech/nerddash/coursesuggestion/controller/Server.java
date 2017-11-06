package tech.nerddash.coursesuggestion.controller;

import java.io.File;
import java.io.IOException;
import java.io.Writer;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletResponse;

import org.apache.catalina.Context;
import org.apache.catalina.LifecycleException;
import org.apache.catalina.servlet4preview.http.HttpServletRequest;
import org.apache.catalina.startup.Tomcat;

public class Server {

	public static void main(String[] args) throws LifecycleException, InterruptedException, ServletException {

		Tomcat tomcat = new Tomcat();
		tomcat.setPort(8080);

		Context ctx = tomcat.addContext("/", new File(".").getAbsolutePath());
		String path = ctx.getEncodedPath();
		System.out.println(path);

		Tomcat.addServlet(ctx, "Embedded", new HttpServlet() {
		
			protected void service(HttpServletRequest req, HttpServletResponse resp)
					throws ServletException, IOException {

				Writer w = resp.getWriter();
				w.write("Embedded Tomcat servlet.\n");
				w.flush();
				w.close();
			}
		});

		ctx.addServletMapping("/*", "Embedded");

		tomcat.start();
		tomcat.getServer().await();
	}
}
