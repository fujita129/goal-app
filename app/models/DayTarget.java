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

    @Formats.DateTime(pattern="yyyy-MM-dd")
    public Date date;

    @Constraints.Required
    public String user;

    public static Finder<Long,DayTarget> findDayTarget = new Finder<Long,DayTarget>(Long.class, DayTarget.class);

}
