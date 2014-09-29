package models;

import javax.persistence.*;

import play.db.ebean.*;
import play.data.validation.*;

/**
 * Word entity managed by Ebean
 */
@Entity
public class Word extends Model {

	public String selectColor(){
		String result = "";
		for(int i=0; i<6; i++) {
			int tmp = (int)(Math.random() * 16);
			result += Integer.toHexString(tmp);
		}
		return result;
	}

    private static final long serialVersionUID = 1L;

	@Id
    public Long id;

    @Constraints.Required
    public String content;

    public int location = (int)(Math.random() * 10);

    public int size = (int)((Math.random() * 4) + 4);

    public String color = selectColor();

    public static Finder<Long,Word> findWord = new Finder<Long,Word>(Long.class, Word.class);

}
