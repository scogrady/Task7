package controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.scribe.builder.ServiceBuilder;
import org.scribe.builder.api.TwitterApi;
import org.scribe.model.OAuthRequest;
import org.scribe.model.Response;
import org.scribe.model.Token;
import org.scribe.model.Verb;
import org.scribe.model.Verifier;
import org.scribe.oauth.OAuthService;

import model.Model;

public class LoginTwitterAction extends Action {

	public LoginTwitterAction(Model model) {

	}

	public String getName() {
		return "loginTwitter.do";
	}

	public String perform(HttpServletRequest request) {

		OAuthService service = new ServiceBuilder()
				.provider(TwitterApi.class)
				.apiKey("t5tzzNMVl1qX7FQucZdvVZmct")
				.apiSecret("JbAJvT1tLsxEWJK76tnTwPfvxgp1aox9R0vFzGETow9LBHcJzB")
				.callback("www.baidu.com").build();

		Token requestToken = service.getRequestToken();

		String url = service.getAuthorizationUrl(requestToken);
		request.getSession().setAttribute("oauthService", service);
		request.getSession().setAttribute("requestToken", requestToken);

		return url;

	}
}
