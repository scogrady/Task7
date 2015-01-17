//Name: Hanze Xu
//Andrew ID: hanzex
//Title: Homework #9 FavoriteForm.java
//Course Number: 08-600
//Date: 2014-12-3
package formbeans;

import java.util.ArrayList;
import java.util.List;

import org.mybeans.form.FormBean;

public class FavoriteForm extends FormBean {
	private String url;
	private String comment;
	private String action;
	
	public String getUrl()  { return url;  }
	public String getComment()  { return comment;  }
	public String getAction()  { return action; }
	
	public void setUrl(String s)  { url = s.trim();        }
	public void setComment(String s)  { comment = s.trim(); }
	public void setAction(String s)  {action = s; }

	public List<String> getValidationErrors() {
		List<String> errors = new ArrayList<String>();

		if (url == null || url.length() == 0) {
			errors.add("URL is required");
		}
		
		if (comment == null || comment.length() == 0) {
			comment = "\n\r";
		}
        
        if (!action.equals("Add Favorite")) errors.add("Invalid action");


		return errors;
	}
}
