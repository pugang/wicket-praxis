/*****************************************
 * Quelltexte zum Buch: Praxisbuch Wicket
 * (http://www.hanser.de/978-3-446-41909-4)
 * 
 * Autor: Michael Mosmann
 * (michael@mosmann.de)
 *****************************************/
package de.wicketpraxis.web.thema.models;

import java.util.Date;

import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.link.Link;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.LoadableDetachableModel;

public class DetachedModelPage extends WebPage {

	public DetachedModelPage() {
		IModel<String> message = new LoadableDetachableModel<String>() {

			@Override
			protected String load() {
				return "Jetzt ist " + new Date();
			}
		};

		add(new Label("message", message));

		add(new Link("doNothing") {

			@Override
			public void onClick() {

			}
		});
	}
}
