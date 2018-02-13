package br.com.marlus.onitama.webapp;

import java.io.File;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.ResponseBuilder;

import br.com.marlus.onitama.service.ImageService;
import br.com.marlus.onitama.service.OnitamaService;

@Path("/")
public class WebApp {

	private static ImageService imageService = new ImageService();
	private static OnitamaService onitamaService = new OnitamaService();
	
	@GET()
	@Path("/")
	@Produces(MediaType.TEXT_PLAIN)
	public Response index() {
		
		ResponseBuilder response = Response.ok((Object) "hellow world!!!");
		
		return response.build();
	}

	@GET()
	@Produces({"image/jpeg,image/png"})
	@Path("/image")
	public Response image() {
        
		File file = imageService.image();
		
        ResponseBuilder response = Response.ok((Object) file);
        
        return response.build();
	}
	
	@GET()
	@Produces({"image/jpeg,image/png"})
	@Path("/move/{from}/{to}/{template}")
	public Response move(@PathParam("from") String from, @PathParam("to") String to, @PathParam("template") String template) {
		
		onitamaService.move(from, to, template);

		ResponseBuilder response = Response.ok((Object) "success");
        
        return response.build();
	}
}
