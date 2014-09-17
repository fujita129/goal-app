package controllers;

import play.*;
import play.mvc.*;

import views.html.*;

public class Application extends Controller {

    public static Result GO_HOME = redirect(
            routes.Application.home()
        );

    public static Result index() {
        return GO_HOME;
    }

    public static Result home() {
    	return ok(
    			home.render()
    			);
    }
}
