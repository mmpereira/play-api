package controllers;

import java.io.StringWriter;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;

import org.springframework.beans.factory.annotation.Autowired;

import play.libs.Json;
import play.mvc.Controller;
import play.mvc.Result;

import com.genebio.nextprot.domain.Publication;
import com.genebio.nextprot.service.PublicationService;

@org.springframework.stereotype.Controller
public class PublicationController extends Controller {

	@Autowired
	PublicationService publicationService;
	
	public Result get(Integer id) {
		Publication publication = this.publicationService.getPublicationById(6634104);
		
		if(request().accepts("text/html")) {
			return ok("html");
		} else 	if(request().accepts("application/json")) {
			return ok(Json.toJson(publication));
		} else if(request().accepts("application/xml")) {
			try {
				return ok(toXml(publication));
			} catch (JAXBException e) {
				e.printStackTrace();
			}
			return badRequest();
		} else return badRequest("bad type");
	}
	
	private String toXml(Publication publication) throws JAXBException {
		JAXBContext context = JAXBContext.newInstance(Publication.class);
	    Marshaller m = context.createMarshaller();
	    m.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
	    StringWriter writer = new StringWriter();
	    m.marshal(publication, writer);
	    return writer.toString();
	}
}
