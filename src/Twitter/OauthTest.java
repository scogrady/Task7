package Twitter;

import java.io.IOException;
import java.util.Scanner;

import net.sf.json.JSONObject;

import org.scribe.builder.ServiceBuilder;
import org.scribe.builder.api.Api;
import org.scribe.builder.api.TwitterApi;
import org.scribe.model.OAuthRequest;
import org.scribe.model.Response;
import org.scribe.model.Token;
import org.scribe.model.Verb;
import org.scribe.model.Verifier;
import org.scribe.oauth.OAuthService;

public class OauthTest {

	private static final String PROTECTED_RESOURCE_URL = "https://api.twitter.com/1.1/account/verify_credentials.json";

	public static void main(String[] args) {
		// If you choose to use a callback, "oauth_verifier" will be the return
		// value by Twitter (request param)
		OAuthService service = new ServiceBuilder()
				.provider(TwitterApi.class)
				.apiKey("t5tzzNMVl1qX7FQucZdvVZmct")
				.apiSecret("JbAJvT1tLsxEWJK76tnTwPfvxgp1aox9R0vFzGETow9LBHcJzB")
				.callback("http://www.baidu.com").build();
		Scanner in = new Scanner(System.in);

		System.out.println("=== Twitter's OAuth Workflow ===");
		System.out.println();

		// Obtain the Request Token
		System.out.println("Fetching the Request Token...");
		Token requestToken = service.getRequestToken();
		// Token requestToken = new
		// Token("3015481311-QwGXcAv4SMR0CtDbhis7ELAX28LM3Mjgf9jDzbF",
		// "hgKoWNmg2ANi7oFANFv5kK1Dh3zpEoM6AOl0n8Q6xcbYK");
		System.out.println("Got the Request Token!");
		System.out.println();
		System.out.println();

		System.out.println("Now go and authorize Scribe here:");
		System.out.println(service.getAuthorizationUrl(requestToken));
		System.out.println("And paste the verifier here");
		System.out.print(">>");
		Verifier verifier = new Verifier(in.nextLine());
		// Verifier verifier = new
		// Verifier("kYjzVBB8Y0ZFabxSWbWovY3uYSQ2pTgmZeNu2VS4cg");

		System.out.println();

		// Trade the Request Token and Verfier for the Access Token
		System.out.println("Trading the Request Token for an Access Token...");
		Token accessToken = service.getAccessToken(requestToken, verifier);
		System.out.println("Got the Access Token!");
		System.out.println("(if you're curious, it looks like this: "
				+ accessToken + " )");
		System.out.println();

		// Now let's go and ask for a protected resource!
		System.out.println("Now we're going to access a protected resource...");
		// OAuthRequest request = new OAuthRequest(Verb.GET,
		// PROTECTED_RESOURCE_URL);
		OAuthRequest request = new OAuthRequest(Verb.GET,
				"https://api.twitter.com/1.1/statuses/lookup.json");
		request.addQuerystringParameter("id", "20,432656548536401920");

		service.signRequest(accessToken, request);

		// request.addBodyParameter("id", value);
		Response response = request.send();
		System.out.println("Got it! Lets see what we found...");
		System.out.println();

		System.out.println(response.getBody());
		System.out.println();

		/*
		 * JSONObject jsonObject = JSONObject.fromObject(response.getBody());
		 * String aString = jsonObject.getString("text");
		 * System.out.println("========" + aString);
		 */
		// System.out.println();
		// System.out.println("That's it man! Go and build something awesome with Scribe! :)");
	}
}
