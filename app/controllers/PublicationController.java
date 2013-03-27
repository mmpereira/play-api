	package controllers;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;

import play.libs.Json;
import play.mvc.Content;
import play.mvc.Controller;
import play.mvc.Result;
import play.mvc.Security;

import com.genebio.nextprot.domain.Author;
import com.genebio.nextprot.domain.Publication;
import com.genebio.nextprot.service.AuthorService;
import com.genebio.nextprot.service.PublicationService;

//@Security.Authenticated(Secured.class)
@org.springframework.stereotype.Controller
public class PublicationController extends Controller {

	@Autowired
	PublicationService publicationService;
	@Autowired
	AuthorService authorService;
	
	public Result get(Long id) { 
		Publication publication = this.publicationService.getPublicationById(id);
		
		if(request().accepts("text/html")) {
			return ok("html");
		} else 	if(request().accepts("application/json")) {
			return ok(Json.toJson(publication));
		} else if(request().accepts("application/xml")) {
				Content xml = views.xml.publication.render(publication);
				return ok(xml);
		} else return badRequest("bad type");
	}
	
	
	public Result list(String title) {
		//String title = "correction";
		
		if(title != null) {
			List<Publication> publications = this.publicationService.getPublicationByTitle("%"+title+"%");
		
		if(request().accepts("application/xml")) {
			Content xml = views.xml.publication_list.render(publications);
			return ok(xml);
		} else return badRequest();
		} else return ok("null");
		
		
	}
	
	public Result superlist(Long id) {
		Publication pub = this.publicationService.getPublicationById(id);
		List<Author> authors = this.authorService.getAuthorByPublicationId(pub.getId());
		
		Map<Long, List<Long>> authorPublicationsMap = new HashMap<Long, List<Long>>();
		for(Author a : authors) {
			authorPublicationsMap.put(Long.valueOf(a.getId()), publicationService.getPublicationIdsByAuthor(a.getLastName()));
		}
		if(request().accepts("application/xml")) {
			Content xml = views.xml.superlist.render(pub, authors);
			return ok(xml);
		}
		return ok("coiso");
	}
	
//	@(publications: List[com.genebio.nextprot.domain.Publication])(content: Xml)
//
//	<publications>
//		@for(publication <- publication) {
//			@content
//		}
//	</publications>
}
