package application;

import java.util.Timer;

import play.Application;
import play.GlobalSettings;
import service.twitter.TwitterFetcherTask;
import application.modules.ProductionModule;

import com.google.inject.Guice;
import com.google.inject.Injector;

public class Global extends GlobalSettings {

	private Injector injector;

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void onStart(final Application app) {
		this.injector = createInjector();
		final Timer twitterFetcher = new Timer();
		twitterFetcher.schedule(new TwitterFetcherTask(), 0, 60 * 1000);
		super.onStart(app);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public <A> A getControllerInstance(final Class<A> controllerClass) throws Exception {
		return getInjector().getInstance(controllerClass);
	}

	private final Injector getInjector() {
		if (this.injector == null) {
			this.injector = createInjector();
		}
		return injector;
	}

	private final Injector createInjector() {
		return Guice.createInjector(new ProductionModule());
	}

}
