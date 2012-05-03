package net.larbig.rs;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

/**
 */

@Path("/services")
public class LaboRest {
	@Inject
	LaboService laboService;

	@GET
	@Path("/json")
	@Produces({ "application/json" })
	public String getHelloWorldJSON() {
		return "{\"result\":\"" + laboService.createHelloMessage("World") + "\"}";
	}

	@GET
	@Path("/xml")
	@Produces({ "application/xml" })
	public String getHelloWorldXML() {
		return "<xml><result>" + laboService.createHelloMessage("World")
				+ "</result></xml>";
	}

}
