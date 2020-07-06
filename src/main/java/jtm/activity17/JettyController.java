package jtm.activity17;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import jtm.activity13.Teacher;
import jtm.activity13.TeacherManager;


@Controller
@RequestMapping(value = "/", produces = "text/html;charset=UTF-8")
public class JettyController {

	TeacherManager manager;

	/**
	 * method which is invoked when root folder (i.e. http://localhost:8080/) of
	 * web application is requested. This method doesn't take any parameters
	 * passed in URL (address).
	 * 
	 * @return string as HTML response to the request using UTF-8 encoding for
	 *         non-Latin characters.
	 */
	@RequestMapping(value = "/", produces = "text/html;charset=UTF-8", method = RequestMethod.GET)
	@ResponseBody
	// This method should work without declared name parameter, request and
	// response objects,
	// but it shows, how passed request and returned response can be used inside
	// method
	public String homePage(@RequestParam(value = "name", required = false) String name, HttpServletRequest request,
			HttpServletResponse response) {
		StringBuilder sb = new StringBuilder();
		sb.append("<a href='/insertTeacher'>Insert teacher<a><br/>\n");
		sb.append("<a href='/findTeacher'>Find teacher<a><br/>\n");
		sb.append("<a href='/deleteTeacher'>Delete teacher<a><br/>\n");
		// Following is also redundant because status is OK by default:
		response.setStatus(HttpServletResponse.SC_OK);
		return sb.toString();
	}

	// TODO Implement insertTeacher() method
	// RMCOMM public String insertTeacher() {
	// START
	@RequestMapping(value = "/insertTeacher", produces = "text/html;charset=UTF-8", method = RequestMethod.GET)
	@ResponseBody
	// TODO Implement insertTeacher() method
	public String insertTeacher(@RequestParam(value = "name", required = false) String name,
			@RequestParam(value = "surname", required = false) String surname, HttpServletRequest request,
			HttpServletResponse response) {

		Boolean status = false;
		if (name == null || surname == null)
			return "<form action=''>\nName: <input type='text' name='name' value=''><br/>\n"
					+ "Surname:<input type='text' name='surname' value=''><br/>\n"
					+ "<input type='submit' value='Insert'></form><br/>\n<a href='/'>Back</a>\n";
		try {
			if (!name.equals("") && !surname.equals("")) {
				manager = new TeacherManager();
				status = manager.insertTeacher(name, surname);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (manager != null)
				manager.closeConnecion();
		}

		if (status)
			response.setStatus(HttpServletResponse.SC_OK);
		else
			response.setStatus(HttpServletResponse.SC_BAD_REQUEST);

		return status.toString() + "<br/>\n" + "<a href='/'>Back</a>\n";
		// END
		// RMCOMM return "";
	}

	// TODO Implement findTeacher() method
	// RMCOMM public String findTeacher() {
	// START
	@RequestMapping(value = "/findTeacher", produces = "text/html;charset=UTF-8", method = RequestMethod.GET)
	@ResponseBody
	public String findTeacher(@RequestParam(value = "name", required = false) String name,
			@RequestParam(value = "surname", required = false) String surname) {

		List<Teacher> teachers = new ArrayList<Teacher>();
		StringBuilder sb = new StringBuilder();

		sb.append("<form action=''>\n");
		sb.append("Name: <input type='text' name='name' value=''><br/>\n");
		sb.append("Surname:<input type='text' name='surname' value=''><br/>\n");
		sb.append("<input type='submit' value='Find'><br/>\n");

		if (name != null && surname != null) {
			manager = new TeacherManager();
			sb.append("<table>\n");
			try {
				teachers = manager.findTeacher(name, surname);
				for (Teacher teacher : teachers) {
					sb.append("<tr>\n");
					sb.append("<td>" + teacher.getId() + "</td>\n");
					sb.append("<td>" + teacher.getFirstName() + "</td>\n");
					sb.append("<td>" + teacher.getLastName() + "</td>\n");
					sb.append("</tr>\n");
				}

			} catch (Exception e) {
				e.printStackTrace();
			}

			finally {
				manager.closeConnecion();
			}
			sb.append("</table><br>\n");
		}

		sb.append("<a href='/'>Back</a>\n");
		return sb.toString();
		// END
		// RMCOMM return "";
	}

	// TODO Implement deleteTeacher() method
	// RMCOMM public String deleteTeacher() {
	// START
	@RequestMapping(value = "/deleteTeacher", produces = "text/html;charset=UTF-8", method = RequestMethod.GET)
	@ResponseBody
	public String deleteTeacher(@RequestParam(value = "id", required = false) String id, HttpServletRequest request,
			HttpServletResponse response) {
		Boolean status = false;
		if (id == null)
			return "" + //
					"<form action=''>\n" + //
					"ID:<input type='text' name='id' value=''><br/>\n" + //
					"<input type='submit' value='Delete'><br/>\n" + //
					"<a href='/'>Back</a>\n";
		try {
			if (!"".equals(id)) {
				manager = new TeacherManager();
				status = manager.deleteTeacher(Integer.parseInt(id));
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (manager != null)
				manager.closeConnecion();
		}

		if (status)
			response.setStatus(HttpServletResponse.SC_OK);
		else
			response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
		return status.toString() + "<br/>\n<a href='/'>Back</a>\n";
		// END
		// RMCOMM return "";
	}
}
