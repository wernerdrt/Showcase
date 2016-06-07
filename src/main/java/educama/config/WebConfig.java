package educama.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@Configuration
public class WebConfig extends WebMvcConfigurerAdapter {

	/**
	 * @See https://camunda.github.io/camunda-bpm-spring-boot-starter/#properties
	 * 
	 * Quote: "By default the starter registers a controller to redirect / to
	 * camundas bundled index.html.
	 * 
	 * To disable this you have to add
	 * camunda.bpm.webapp.index-redirect-enabled=false to your application
	 * properties."
	 * 
	 * However, this does not seem to work. Therefore we redirect with a higher
	 * precedence.
	 */
	@Override
	public void addViewControllers(ViewControllerRegistry registry) {
		registry.addRedirectViewController("/", "/index2.html");
		registry.setOrder(Ordered.HIGHEST_PRECEDENCE);
		super.addViewControllers(registry);
	}
}
