package models;

import java.util.*;
import javax.persistence.*;

import play.db.ebean.*;
import play.data.format.*;
import play.data.validation.*;

/**
 * DayTarget entity managed by Ebean
 */
@Entity
public class DayTarget extends Model {

    private static final long serialVersionUID = 1L;

	@Id
    public Long id;

    @Constraints.Required
    public String name;

    @Version
    @Formats.DateTime(pattern="yyyy-MM-dd hh:mm:ss")
    public Date date = new Date();

    @Constraints.Required
    public String usrname;

    @Constraints.Required
    public String maker;

    public static Finder<Long,DayTarget> findDayTarget = new Finder<Long,DayTarget>(Long.class, DayTarget.class);

}
