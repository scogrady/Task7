//Name: Hanze Xu
//Andrew ID: hanzex
//Title: Homework #9 FavoriteBean.java
//Course Number: 08-600
//Date: 2014-12-3
package databeans;
import org.genericdao.PrimaryKey;

@PrimaryKey("favoriteID")
public class FavoriteBean {
	private int    favoriteID;
	private int    userID;
	private String url;
	private String comment;
	private int    clickCount;
	
	public int    getFavoriteID()        { return favoriteID;           }
    public int    getUserID()            { return userID;         }
    public String getUrl()               { return url;     }
    public String getComment()           { return comment;    }
    public int    getClickCount()        { return clickCount;     }

    public void   setFavoriteID(int i)   { favoriteID = i;              }
	public void   setUserID(int i)       { userID = i;            }
	public void   setUrl(String s)       { url = s;        }
	public void   setComment(String s)   { comment = s;       }
	public void   setClickCount(int i)   { clickCount = i;        }
}
