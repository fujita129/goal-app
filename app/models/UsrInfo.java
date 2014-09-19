package models;

import javax.persistence.*;

import play.db.ebean.*;
import play.data.validation.*;

/**
 * User entity managed by Ebean
 */
@Entity
public class UsrInfo extends Model {

    private static final long serialVersionUID = 1L;

	@Id
    public Long id;

    @Constraints.Required
    public String name;

    @Constraints.Required
    public String password;

    public static Finder<Long,UsrInfo> findUser = new Finder<Long,UsrInfo>(Long.class, UsrInfo.class);

}
