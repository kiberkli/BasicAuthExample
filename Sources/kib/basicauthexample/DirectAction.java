package kib.basicauthexample;

import com.webobjects.appserver.WOActionResults;
import com.webobjects.appserver.WORequest;

import er.extensions.appserver.ERXDirectAction;
import er.extensions.appserver.ERXRequest;

import kib.basicauthexample.components.AuthRequired;
import kib.basicauthexample.components.Main;

public class DirectAction extends ERXDirectAction {
	public DirectAction(WORequest request) {
		super(request);
	}

	@Override
	public WOActionResults defaultAction() {
		boolean userAuthenticated = false;
		ERXRequest request = (ERXRequest)request();

		if (request.basicAuthUsername() != null) {
			log.info("Attempted login as user: "+request.basicAuthUsername()+" Password: "+request.basicAuthPassword());
			// Insert authentication code here ===================
			userAuthenticated = true;
		}

		if (userAuthenticated) {
			// User is authenticated:
			Main returnPage = (Main)pageWithName(Main.class.getName());
			return returnPage;
		} else {
			// User login required:
			AuthRequired returnPage = (AuthRequired)pageWithName(AuthRequired.class.getName());
			returnPage.requiresAuthentication("A Special Place");
			return returnPage;
		}

	}

}
