package models;

import java.util.Date;

import javax.persistence.*;
import play.db.ebean.Model.Finder;
import play.data.validation.*;
import play.data.format.*;
import play.db.ebean.*;
import play.data.validation.*;

@Entity
public class DayTarget {

    private static final long serialVersionUID = 1L;

    @Id
    public Long id;

    @Constraints.Required
    public String name;

    @Formats.DateTime(pattern="yyyy-MM-dd")
    public Date date;

    public static Finder<Long,DayTarget> findDayTarget = new Finder<Long,DayTarget>(Long.class, DayTarget.class);

}
