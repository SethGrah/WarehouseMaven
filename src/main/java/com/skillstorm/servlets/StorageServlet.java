package com.skillstorm.servlets;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.skillstorm.daos.MySqlStorageDaoImpl;
import com.skillstorm.daos.StorageDao;
import com.skillstorm.models.NotFound;
import com.skillstorm.models.Storage;
import com.skillstorm.services.URLParserService;

@WebServlet(urlPatterns = "/storage/*")
public class StorageServlet extends HttpServlet {

	@Override
	public void init() throws ServletException {

		System.out.println("StorageServlet Created!");
		super.init();
	}

	@Override
	public void destroy() {
		System.out.println("StorageServlet Destroyed!");
		super.destroy();
	}


	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		System.out.println("Servicing request!");
		super.service(req, resp);
	}
	private static final long serialVersionUID = 4400752673037174458L;
	StorageDao dao = new MySqlStorageDaoImpl();
	ObjectMapper mapper = new ObjectMapper();
	URLParserService urlService = new URLParserService();

	// Returns all artists
	// /artists/{id}
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		try {
			int id = urlService.extractIdFromURL(req.getPathInfo());
			Storage storage = dao.findById(id);
			if (storage != null) {
				resp.setContentType("application/json");
				resp.getWriter().print(mapper.writeValueAsString(storage));
			} else {
				resp.setStatus(404);
				resp.getWriter().print(mapper.writeValueAsString(new NotFound("No item in storage with the provided Id found")));
			}
		} catch (Exception e) {
			List<Storage> currStorage = dao.findAll();
			System.out.println(currStorage);
			resp.setContentType("application/json");
			resp.getWriter().print(mapper.writeValueAsString(currStorage));
		}

	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		InputStream reqBody = req.getInputStream();
		Storage newStorage = mapper.readValue(reqBody, Storage.class);
//		validatorService.validate(newStorage); // Could be a service
		newStorage = dao.save(newStorage); // IF the id changed
		if (newStorage != null) {
			resp.setContentType("application/json");
			resp.getWriter().print(mapper.writeValueAsString(newStorage));
			resp.setStatus(201);
		} else {
			resp.setStatus(400);
			resp.getWriter().print(mapper.writeValueAsString(new NotFound("Unable to create a new item")));
		}
	}

}
