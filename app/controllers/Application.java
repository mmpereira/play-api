package controllers;

import models.User;
import play.mvc.Controller;
import play.mvc.Result;
import views.html.index;

public class Application extends Controller {
  
	public static class Login {
        
        public String email;
        public String password;
        
        public String validate() {
            if(User.authenticate(email, password) == null) {
                return "Invalid user or password";
            }
            return null;
        }
        
    }

    public static Result index() {
        return ok(index.render("Salut"));
    }
  
//    public static Result login() {
//    	return ok(
//    			login.render(form(Login.class))
//    			);
//    }
//    
//    public static Result authenticate() {
//        Form<Login> loginForm = form(Login.class).bindFromRequest();
//        if(loginForm.hasErrors()) {
//            return badRequest(login.render(loginForm));
//        } else {
//            session("email", loginForm.get().email);
//            return redirect(
//                routes.Projects.index()
//            );
//        }
//    }
}
