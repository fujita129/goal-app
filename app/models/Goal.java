package models;

import javax.persistence.*;

import play.db.ebean.*;
import play.data.validation.*;

/**
 * User entity managed by Ebean
 */
@Entity
public class Goal extends Model {

    private static final long serialVersionUID = 1L;

	@Id
    public Long id;

    public String week;

    public String month;

    public String year;

    public String decade;

    @Constraints.Required
    public String usrname;

    public static Finder<Long,Goal> findGoal = new Finder<Long,Goal>(Long.class, Goal.class);

}
