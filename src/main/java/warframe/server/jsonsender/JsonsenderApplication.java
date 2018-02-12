package warframe.server.jsonsender;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.cors.CorsConfiguration;

@SpringBootApplication
@RestController

public class JsonsenderApplication {

	@CrossOrigin
	@GetMapping("/")
	public String json() {
		final String uri = "http://content.warframe.com/dynamic/worldState.php";
		RestTemplate restTemplate = new RestTemplate();
		String result = restTemplate.getForObject(uri, String.class);
		return result;
	}

	public static void main(String[] args) {
		SpringApplication.run(JsonsenderApplication.class, args);
	}

	@Configuration
	@EnableWebSecurity
	@EnableGlobalMethodSecurity(prePostEnabled = true)
	public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

		@Override
		protected void configure(HttpSecurity httpSecurity) throws Exception {
			httpSecurity.cors().configurationSource(request -> new CorsConfiguration().applyPermitDefaultValues());
		}
	}
}