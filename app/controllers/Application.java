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

    public static Result editDayTarget(Long id) {
        Form<DayTarget> dayForm = form(DayTarget.class).fill(
            DayTarget.findDayTarget.byId(id)
        );
        return ok(
            editDayTargetForm.render(id, dayForm)
        );
    }

    public static Result updateDayTarget(Long id) {
        Form<DayTarget> dayForm = form(DayTarget.class).bindFromRequest();
        if(dayForm.hasErrors()) {
            return badRequest(editDayTargetForm.render(id, dayForm));
        }
        dayForm.get().update(id);
        flash("success", "DayTarget " + dayForm.get().name + " has been updated");
        return GO_HOME;
    }

    public static Result deleteDayTarget(Long id) {
        DayTarget.findDayTarget.ref(id).delete();
        flash("success", "DayTarget has been deleted");
        return GO_HOME;
    }

}
