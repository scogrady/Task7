package controller;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.json.JSONArray;
import org.json.JSONObject;
import org.json.JSONException;
import org.mybeans.form.FormBeanException;
import org.mybeans.form.FormBeanFactory;
import org.scribe.builder.ServiceBuilder;
import org.scribe.builder.api.TwitterApi;
import org.scribe.model.OAuthRequest;
import org.scribe.model.Response;
import org.scribe.model.Token;
import org.scribe.model.Verb;
import org.scribe.model.Verifier;
import org.scribe.oauth.OAuthService;

import formbeans.BuyForm;
import formbeans.TwitterLoginForm;
import model.Model;
import databeans.TwitterBean;

public class GetTokenAction extends Action {
	private FormBeanFactory<TwitterLoginForm> formBeanFactory = FormBeanFactory
			.getInstance(TwitterLoginForm.class);

	public GetTokenAction(Model model) {

	}

	public String getName() {
		return "getTokenAction.do";
	}

	public String perform(HttpServletRequest request) {

		try {
			TwitterLoginForm form = formBeanFactory.create(request);
			Verifier verifier = new Verifier(form.getOauth_verifier());	
			OAuthService service = (OAuthService) request.getSession().getAttribute("oauthService");
			Token requestToken = (Token) request.getSession().getAttribute("requestToken");
			request.getSession().setAttribute("verifier", verifier);

			Token accessToken = service.getAccessToken(requestToken, verifier);
			request.getSession().setAttribute("accessToken", accessToken);
			String resourceURL = "https://api.twitter.com/1.1/statuses/user_timeline.json";
			OAuthRequest httpRequest = new OAuthRequest(Verb.GET,resourceURL);
			
			//httpRequest.addQuerystringParameter("id", "20,432656548536401920");

			service.signRequest(accessToken, httpRequest);

			// request.addBodyParameter("id", value);
			Response response = httpRequest.send();
			System.out.println(response.getBody());
			JSONArray jsonarray = new JSONArray(response.getBody());
			List<TwitterBean> twitters = new ArrayList<TwitterBean>();
			for (int i = 0; i < jsonarray.length(); i++) {
				TwitterBean twitter = new TwitterBean();
			    JSONObject jsonobject = jsonarray.getJSONObject(i);
			    String createTime = jsonobject.getString("created_at");
			    String text = jsonobject.getString("text");
			    JSONObject user = (JSONObject)jsonobject.get("user");
			    twitter.setCreateTime(createTime);
			    twitter.setText(text);
			    twitter.setUser(user.getString("name"));
			    twitters.add(twitter);
			}
			request.setAttribute("tweets", twitters);
			return "index.jsp";

		} catch (FormBeanException e) {
			return "customer/error.jsp";

		} catch (JSONException e) {
			return "customer/error.jsp";
			// TODO Auto-generated catch block
		}

	}
}
