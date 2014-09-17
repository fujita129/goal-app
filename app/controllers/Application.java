package controllers;

import java.util.List;

import models.DayTarget;
import play.db.ebean.Model.Finder;
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
    	Finder<Long, DayTarget> dayFinder = new Finder<Long, DayTarget>(Long.class, DayTarget.class);
    	List<DayTarget> dayTargets = dayFinder.all();
    	return ok(
    			home.render(dayTargets)
    			);
    }
}
