package controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

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

			Token accessToken = service.getAccessToken(requestToken, verifier);
			String resourceURL = "https://api.twitter.com/1.1/statuses/lookup.json";
			OAuthRequest httpRequest = new OAuthRequest(Verb.GET,resourceURL);
			httpRequest.addQuerystringParameter("id", "20,432656548536401920");

			service.signRequest(accessToken, httpRequest);

			// request.addBodyParameter("id", value);
			Response response = httpRequest.send();
			System.out.println(response.getBody());

			return "login.jsp";

		} catch (FormBeanException e) {
			return "customer/error.jsp";

		}

	}
}
