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
        return redirect(routes.Application.register());
    }

    public static Result home() {
        Form<UsrInfo> userForm = form(UsrInfo.class).bindFromRequest();
    	Finder<Long, UsrInfo> userFinder = new Finder<Long, UsrInfo>(Long.class, UsrInfo.class);
    	List<UsrInfo> allUser = userFinder.all();
    	// sessionにセットした値を取得
    	String userName = session("userName");
    	Finder<Long, DayTarget> dayFinder = new Finder<Long, DayTarget>(Long.class, DayTarget.class);
    	List<DayTarget> dayTargets = dayFinder.all();
    	return ok(
    			home.render(allUser, dayTargets, userName)
    			);
    }

    public static Result register() {
        Form<UsrInfo> userForm = Form.form(UsrInfo.class);
        return ok(
            registerForm.render(userForm)
        );
    }

    public static Result submitRegister() {
        Form<UsrInfo> userForm = form(UsrInfo.class).bindFromRequest();
        userForm.get().save();
        flash("success", "User " + userForm.get().name + " has been registerd");
    	return redirect(routes.Application.login());
    }

    public static Result login() {
        Form<UsrInfo> userForm = Form.form(UsrInfo.class);
        return ok(
            loginForm.render(userForm)
        );
    }

    public static Result submitLogin() {
        Form<UsrInfo> userForm = form(UsrInfo.class).bindFromRequest();
    	Finder<Long, UsrInfo> userFinder = new Finder<Long, UsrInfo>(Long.class, UsrInfo.class);
    	List<UsrInfo> users = userFinder.all();
    	String formName, listName, formPass, listPass;
    	formName = userForm.get().name;
    	formPass = userForm.get().password;
    	for(UsrInfo user : users){
    		listName = user.name;
    		listPass = user.password;
    		if( formName.equals(listName) && formPass.equals(listPass) ) {
    			// sessionに値をセット
    			session("userName", userForm.get().name);
    			flash("success", "User " + userForm.get().name + " has been logined");
    			return GO_HOME;
    		}
    	}
    	return redirect(routes.Application.login());
    }

    public static Result createDayTarget(String targetUser) {
        Form<DayTarget> dayForm = Form.form(DayTarget.class);
        return ok(
            createDayTargetForm.render(dayForm, targetUser)
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
