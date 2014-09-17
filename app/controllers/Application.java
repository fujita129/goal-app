package controllers;

import java.util.List;
import play.mvc.*;
import play.data.*;
import play.db.ebean.Model.Finder;
import static play.data.Form.*;
import views.html.*;
import models.*;

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

    public static Result createDayTarget() {
        Form<DayTarget> dayForm = Form.form(DayTarget.class);
        return ok(
            createDayTargetForm.render(dayForm)
        );
    }

    public static Result saveDayTarget() {
        Form<DayTarget> dayForm = form(DayTarget.class).bindFromRequest();
        dayForm.get().save();
        flash("success", "DayTarget " + dayForm.get().name + " has been created");
        return GO_HOME;
    }

}
