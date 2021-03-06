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
    	Form<Word> wordForm = Form.form(Word.class);
        Form<UsrInfo> userForm = form(UsrInfo.class).bindFromRequest();
    	Finder<Long, UsrInfo> userFinder = new Finder<Long, UsrInfo>(Long.class, UsrInfo.class);
    	List<UsrInfo> allUser = userFinder.all();
    	// sessionにセットした値を取得
    	String userName = session("userName");
    	Finder<Long, DayTarget> dayFinder = new Finder<Long, DayTarget>(Long.class, DayTarget.class);
    	List<DayTarget> dayTargets = dayFinder.all();
    	Finder<Long, Word> wordFinder = new Finder<Long, Word>(Long.class, Word.class);
    	List<Word> words = wordFinder.all();
    	return ok(
    		home.render(allUser, dayTargets, userName, words, wordForm)
    	);
    }

    public static Result mymypage() {
    	// sessionにセットした値を取得
    	String userName = session("userName");
    	Finder<Long, DayTarget> dayFinder = new Finder<Long, DayTarget>(Long.class, DayTarget.class);
    	List<DayTarget> dayTargets = dayFinder.all();
    	Finder<Long, Goal> goalFinder = new Finder<Long, Goal>(Long.class, Goal.class);
    	List<Goal> goals = goalFinder.all();
    	return ok(
    		mypage.render(dayTargets, userName, userName, goals)
    	);
    }

    public static Result mypage(String userName) {
    	// sessionにセットした値を取得
    	String lookingUser = session("userName");
    	Finder<Long, DayTarget> dayFinder = new Finder<Long, DayTarget>(Long.class, DayTarget.class);
    	List<DayTarget> dayTargets = dayFinder.all();
    	Finder<Long, Goal> goalFinder = new Finder<Long, Goal>(Long.class, Goal.class);
    	List<Goal> goals = goalFinder.all();
    	return ok(
    		mypage.render(dayTargets, userName, lookingUser, goals)
    	);
    }

    public static Result mymyrecord() {
    	// sessionにセットした値を取得
    	String userName = session("userName");
    	Finder<Long, DayTarget> dayFinder = new Finder<Long, DayTarget>(Long.class, DayTarget.class);
    	List<DayTarget> dayTargets = dayFinder.all();
    	Finder<Long, Goal> goalFinder = new Finder<Long, Goal>(Long.class, Goal.class);
    	List<Goal> goals = goalFinder.all();
    	return ok(
    		myrecord.render(dayTargets, userName, userName, goals)
    	);
    }

    public static Result myrecord(String userName) {
    	// sessionにセットした値を取得
    	String lookingUser = session("userName");
    	Finder<Long, DayTarget> dayFinder = new Finder<Long, DayTarget>(Long.class, DayTarget.class);
    	List<DayTarget> dayTargets = dayFinder.all();
    	Finder<Long, Goal> goalFinder = new Finder<Long, Goal>(Long.class, Goal.class);
    	List<Goal> goals = goalFinder.all();
    	return ok(
    		myrecord.render(dayTargets, userName, lookingUser, goals)
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
    	Finder<Long, UsrInfo> userFinder = new Finder<Long, UsrInfo>(Long.class, UsrInfo.class);
    	List<UsrInfo> users = userFinder.all();
    	for(UsrInfo user : users){
    		if(user.name.equals(userForm.get().name)){
    			flash("false", "このユーザーIDは既に登録されています");
    			return redirect(routes.Application.register());
    		}
    	}
        userForm.get().save();
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
    			return GO_HOME;
    		}
    	}
		flash("false", "ユーザIDとパスワードが一致しません");
    	return redirect(routes.Application.login());
    }

    public static Result logout() {
    	// sessionの値をクリア
    	session().remove("userName");
    	return redirect(
    		routes.Application.login()
    	);
    }

    public static Result createDayTarget(String targetUser) {
    	// sessionにセットした値を取得
    	String maker = session("userName");
        Form<DayTarget> dayForm = Form.form(DayTarget.class);
        return ok(
            createDayTargetForm.render(dayForm, targetUser, maker)
        );
    }

    public static Result saveDayTarget() {
        Form<DayTarget> dayForm = form(DayTarget.class).bindFromRequest();
        dayForm.get().save();
        return redirect(
        	routes.Application.mypage(dayForm.get().usrname)
        );
    }

    public static Result editDayTarget(Long id, String targetUser, String maker) {
        Form<DayTarget> dayForm = form(DayTarget.class).fill(
            DayTarget.findDayTarget.byId(id)
        );
        return ok(
            editDayTargetForm.render(id, dayForm, targetUser, maker)
        );
    }

    public static Result updateDayTarget(Long id) {
        Form<DayTarget> dayForm = form(DayTarget.class).bindFromRequest();
        dayForm.get().update(id);
        return redirect(
    		routes.Application.mypage(dayForm.get().usrname)
    	);
    }

    public static Result clearDayTarget(Long id, String userName) {

        DayTarget clearedDayTarget = DayTarget.findDayTarget.byId(id);
        clearedDayTarget.cleared = true;
        clearedDayTarget.cleardate = DayTarget.createJST();
        clearedDayTarget.save();
        return redirect(
    		routes.Application.mypage(userName)
    	);
    }

    public static Result deleteDayTarget(Long id, String userName, boolean fromMypage) {
        DayTarget.findDayTarget.ref(id).delete();
        if(fromMypage){
        	return redirect(
        			routes.Application.mypage(userName)
			);
        } else {
        	return redirect(
        			routes.Application.myrecord(userName)
			);
        }
    }

    public static Result createGoal() {
    	// sessionにセットした値を取得
    	String userName = session("userName");
        Form<Goal> goalForm = Form.form(Goal.class);
        return ok(
            createGoalForm.render(goalForm, userName)
        );
    }

    public static Result saveGoal() {
        Form<Goal> goalForm = form(Goal.class).bindFromRequest();
        goalForm.get().save();
        return redirect(
        	routes.Application.mypage(goalForm.get().usrname)
        );
    }
    public static Result editGoal(Long id) {
    	// sessionにセットした値を取得
    	String userName = session("userName");
        Form<Goal> goalForm = form(Goal.class).fill(
            Goal.findGoal.byId(id)
        );
        return ok(
            editGoalForm.render(id, goalForm, userName)
        );
    }

    public static Result updateGoal(Long id) {
        Form<Goal> goalForm = form(Goal.class).bindFromRequest();
        goalForm.get().update(id);
        return redirect(
    		routes.Application.mypage(goalForm.get().usrname)
    	);
    }

    public static Result deleteGoal(Long id, String userName) {
        Goal.findGoal.ref(id).delete();
        return redirect(
    		routes.Application.mypage(userName)
    	);
    }

    public static Result createWord() {
        Form<Word> wordForm = form(Word.class).bindFromRequest();
        wordForm.get().save();
        return GO_HOME;
    }
}
