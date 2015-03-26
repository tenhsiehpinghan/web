package idv.hsiehpinghan.stockweb.configuration;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration("stockWebSpringConfiguration")
@ComponentScan(basePackages = { "idv.hsiehpinghan.stockweb" })
public class SpringConfiguration {
	// private Logger logger = Logger.getLogger(this.getClass().getName());
}
