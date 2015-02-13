package controller;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import model.Model;
import model.CustomerDAO;
import model.PositionDAO;
import model.FundDAO;
import model.FundPriceHistoryDAO;

import org.genericdao.RollbackException;
import org.scribe.model.OAuthRequest;
import org.scribe.model.Response;
import org.scribe.model.Token;
import org.scribe.model.Verb;
import org.scribe.model.Verifier;
import org.scribe.oauth.OAuthService;

import databeans.CustomerBean;
import databeans.PositionBean;
import databeans.TransactionBean;
import databeans.UserBean;

public class PostTweetAction extends Action {

	private CustomerDAO customerDAO;
	private PositionDAO positionDAO;
	private FundDAO fundDAO;
	private FundPriceHistoryDAO fundPriceHistoryDAO;

	public PostTweetAction(Model model) {

		customerDAO = model.getCustomerDAO();
		positionDAO = model.getPositionDAO();
		fundDAO = model.getFundDAO();
		fundPriceHistoryDAO = model.getFundPriceHistoryDAO();

	}

	public String getName() {
		return "PostTweet.do";
	}

	public String perform(HttpServletRequest request) {
		String text = request.getParameter("text");
		OAuthService service = (OAuthService) request.getSession().getAttribute("oauthService");
		Verifier verifier = (Verifier) request.getSession().getAttribute("verifier");
		Token accessToken = (Token) request.getSession().getAttribute("accessToken");
		
		String resourceURL;
		try {
			resourceURL = "https://api.twitter.com/1.1/statuses/update.json?status=" + URLEncoder.encode(text, "UTF-8");
			System.out.println(resourceURL);

		
		OAuthRequest httpRequest = new OAuthRequest(Verb.POST, resourceURL);
		//httpRequest.addQuerystringParameter("id", "20,432656548536401920");
		System.out.println(httpRequest.getBodyContents());
		service.signRequest(accessToken, httpRequest);
		Response response = httpRequest.send();
		System.out.println(response.getBody());
		return "index.jsp";
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return "index.jsp";
		}

	}
}
