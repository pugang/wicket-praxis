/*****************************************
 * Quelltexte zum Buch: Praxisbuch Wicket
 * (http://www.hanser.de/978-3-446-41909-4)
 * 
 * Autor: Michael Mosmann
 * (michael@mosmann.de)
 *****************************************/
package de.wicketpraxis.apps.session.pages;

import org.apache.wicket.markup.html.link.BookmarkablePageLink;

public class Start extends AbstractBasePage {

	public Start() {
		add(new BookmarkablePageLink<ProtectedPage>("link", ProtectedPage.class));
	}
}
