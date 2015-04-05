package edu.sjsu.cmpe275.lab3.RestPersistenceApplication;

import java.util.concurrent.atomic.AtomicLong;

import javax.validation.Valid;

import org.apache.commons.lang3.StringUtils;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * Hello world!
 *
 */

@RestController
@EnableAutoConfiguration
public class App 
{
	private final AtomicLong counter = new AtomicLong(1000);
	@RequestMapping("/")
    String home() 
	{
        return "Hello World!";
    }
	
	@RequestMapping(value="/player",method=RequestMethod.POST)
	public Player createPlayer(@Valid @RequestParam(value="firstname") String firstname,
			 @Valid @RequestParam(value="lastname") String lastname,
			 @Valid @RequestParam(value="email") String email,
			 @RequestParam(value = "description", required = false) String description,
			 @RequestParam(value = "address", required = false) String address,
			 @RequestParam(value = "sponsor", required = false) String sponsor)
	{
		HibernateHandler handler = new HibernateHandler();
		Player player = new Player();
		player.setId(counter.incrementAndGet());
		player.setFirstname(firstname);
		player.setLastname(lastname);
		player.setEmail(email);
		if(StringUtils.isNotBlank(description))
		{
			
			player.setDescription(description);
		}
			
		if(StringUtils.isNotBlank(address))
		{
			player.setAddress(address);
		}
				
		if(StringUtils.isNotBlank(sponsor))
		{
			player.setSponsor(sponsor);
		}
		
		return handler.createPlayer(player);
	}
	
	public static void main(String[] args) throws Exception
	{
        SpringApplication.run(App.class, args);
    }
}
