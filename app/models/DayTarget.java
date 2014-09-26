package models;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;

import javax.persistence.*;

import play.db.ebean.*;
import play.data.validation.*;

/**
 * DayTarget entity managed by Ebean
 */
@Entity
public class DayTarget extends Model {

	public static String createJST() {
		Date date = new Date();
		DateFormat df = new SimpleDateFormat("yyyy/MM/dd");
		df.setTimeZone(TimeZone.getDefault());
		return df.format(date);
	}

    private static final long serialVersionUID = 1L;

	@Id
    public Long id;

    @Constraints.Required
    public String name;

    public String date = createJST();

    @Constraints.Required
    public String usrname;

    @Constraints.Required
    public String maker;

    public boolean cleared  = false;

    public static Finder<Long,DayTarget> findDayTarget = new Finder<Long,DayTarget>(Long.class, DayTarget.class);

}
