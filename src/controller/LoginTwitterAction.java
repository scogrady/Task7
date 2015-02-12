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
				.apiKey("erbdRve3OEYq1qqgf1chyGKw2")
				.apiSecret("sfQxOI7zydKQvEuVUmjDQoab11KhikIy1kGwCpftjPrHCjjfXW")
				.callback("http://127.0.0.1:8080/Task7/getTokenAction.do")
				.build();
		
		Token requestToken = service.getRequestToken();
		request.getSession().setAttribute("oauthService", service);
		request.getSession().setAttribute("requestToken", requestToken);
		String url = service.getAuthorizationUrl(requestToken);


		return url;

	}
}
