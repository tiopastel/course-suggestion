package tech.nerddash.coursesuggestion.controller;

import static br.com.caelum.vraptor.view.Results.json;

import javax.inject.Inject;

import br.com.caelum.vraptor.Controller;
import br.com.caelum.vraptor.Get;
import br.com.caelum.vraptor.Result;
import tech.nerddash.coursesuggestion.model.Content;
import tech.nerddash.coursesuggestion.model.Course;
import tech.nerddash.coursesuggestion.model.Discipline;

@Controller
public class Json {

	private Content content;
	private Discipline discipline;
	private Course course;
	private Result result;

	@Inject
	public Json(Result result) {
		this.result = result;
	}

	public Json() {
		this(null);
	}

	@Get("/json/course")
	public void course() {

		course = new Course();

		course.setName("Web Development");
		course.setDescription(
				"Web development is a broad term for the work involved in developing a web site for the Internet (World Wide Web) or an intranet (a private network). Web development can range from developing the simplest static single page of plain text to the most complex web-based internet applications (or just 'web apps') electronic businesses, and social network services. A more comprehensive list of tasks to which web development commonly refers, may include web engineering, web design, web content development, client liaison, client-side/server-side scripting, web server and network security configuration, and e-commerce development. Among web professionals, \"web development\" usually refers to the main non-design aspects of building web sites: writing markup and coding. Most recently Web development has come to mean the creation of content management systems or CMS.");
		course.setJustification("Becouse We can");

		System.out.println(course);

		result.use(json()).from(course).recursive().serialize();
	}

	@Get("/json/discipline")
	public void discipline() {

		discipline = new Discipline();
		course = new Course();
		course.setId(1L);

		discipline.setName("Server Implementation");
		discipline.setDescription("In this discipline students lear how to implements servers");
		discipline.setJustification("Helps students to understand how and way to implements servers");

		discipline.setCourse(course);

		System.out.println(discipline);

		result.use(json()).from(discipline).recursive().serialize();
	}

	@Get("/json/content")
	public void content() {
		content = new Content();

		content.setName("Docker");
		content.setDescription(
				"Docker is a software technology providing containers, promoted by the company Docker, Inc.[6] Docker provides an additional layer of abstraction and automation of operating-system-level virtualization on Windows and Linux.[7] Docker uses the resource isolation features of the Linux kernel such as cgroups and kernel namespaces, and a union-capable file system such as OverlayFS and others[8] to allow independent \"containers\" to run within a single Linux instance, avoiding the overhead of starting and maintaining virtual machines (VMs).[9]");
		content.setJustification("Docker is a tecnologie used in many applications in the actual development cenario.");

		System.out.println(content);

		result.use(json()).from(content).recursive().serialize();

	}
}
