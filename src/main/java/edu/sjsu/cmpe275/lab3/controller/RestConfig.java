package edu.sjsu.cmpe275.lab3.controller;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;
import java.util.concurrent.atomic.AtomicLong;
import javax.validation.Valid;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import edu.sjsu.cmpe275.lab3.handler.*;
import edu.sjsu.cmpe275.lab3.resource.*;


@RestController
@EnableAutoConfiguration
public class RestConfig {
	
	PlayerHandler handler;
	SponsorHandler sponserHndlr;
	OpponentHandler oppHandler;
	
	@RequestMapping("/")
    String home() {
        return "Hello World!";
    }
	
	@RequestMapping(value="/player",method=RequestMethod.POST)
	public Player createPlayer(@Valid @RequestParam(value="firstname") String firstname,
			 @Valid @RequestParam(value="lastname") String lastname,
			 @Valid @RequestParam(value="email") String email,
			 @RequestParam(value = "description", required = false) String description,
			 @RequestParam(value = "street", required = false) String street,
			 @RequestParam(value = "city", required = false) String city,
			 @RequestParam(value = "state", required = false) String state,
			 @RequestParam(value = "zip", required = false) String zip,
			 //@RequestParam(value = "address", required = false) String address,
			 @RequestParam(value = "sponsor", required = false) String sponsor)
	{
		handler=new PlayerHandler();
		Player player = new Player();
		Address addr = null;
		player.setFirstname(firstname);
		player.setLastname(lastname);
		player.setEmail(email);
		if(description!=null)
		{
			player.setDescription(description);
		}
			
//		if(address!=null)
//		{
//			player.setAddress(address);
//		}
				
		if(sponsor!=null)
		{
			player.setSponsor(sponsor);
		}
		if(street!=null && city!=null && state!=null && zip!=null)
		{
			 addr = new Address(street,city,state,zip);
			 player.setAddress(addr);
		}
		System.out.println("player is:::"+player);
		return handler.createPlayer(player);
	}
	
	@RequestMapping(value="/player/{id}",method=RequestMethod.GET)
	public ResponseEntity<String> getPlayer(@PathVariable("id") long id)  
	{
		handler=new PlayerHandler();
		if(handler.getPlayer(id)==null)
		{
			return new ResponseEntity<String>("Player is not created yet.Please create the player.",HttpStatus.NOT_FOUND);
		}
		else
		{
			return new ResponseEntity(handler.getPlayer(id),HttpStatus.OK);
		}
	}
	
	@RequestMapping(value="/player/{id}",method=RequestMethod.DELETE)
	public ResponseEntity<String> deletePlayer(@PathVariable("id") long id)
	{
		handler=new PlayerHandler();
		if(handler.checkPlayerExists(id))
		{
		return new ResponseEntity(handler.delete(id),HttpStatus.OK);
		}
		else
		{
		return new ResponseEntity<String>("Player is not created yet.Cannot be deleted.",HttpStatus.NOT_FOUND);
		}
	}
	
	@RequestMapping(value="/player/{id}",method=RequestMethod.POST)
	public ResponseEntity<String> updatePlayer(@PathVariable("id") long id,
			@Valid @RequestParam(value="firstname") String firstname,
			 @Valid @RequestParam(value="lastname") String lastname,
			 @Valid @RequestParam(value="email") String email,
			 @RequestParam(value = "description", required = false) String description,
			 @RequestParam(value = "address", required = false) String address,
			 @RequestParam(value = "street", required = false) String street,
			 @RequestParam(value = "city", required = false) String city,
			 @RequestParam(value = "state", required = false) String state,
			 @RequestParam(value = "zip", required = false) String zip,
			 @RequestParam(value = "sponsor", required = false) String sponsor)
	{
		Player player = new Player();
		Address addr = new Address();
		if(!handler.checkPlayerExists(id))
		{
			return new ResponseEntity<String>("This user does not exist",HttpStatus.NOT_FOUND);
		}
		else
		{
			player.setId(id);
			player.setFirstname(firstname);
			player.setLastname(lastname);
			player.setEmail(email);
			if(description!=null)
			{
				player.setDescription(description);
			}
			
//			if(address!=null)
//			{
//				player.setAddress(address);
//			}
				
			if(sponsor!=null)
			{
				player.setSponsor(sponsor);
			}
			if(street!=null && city!=null && state!=null && zip!=null)
			{
				 addr = new Address(street,city,state,zip);
				 player.setAddress(addr);
			}
			return new ResponseEntity(handler.updatePlayer(player),HttpStatus.OK);
		}
	}
	
	//sponsor
	
	@RequestMapping(value="/sponsor",method=RequestMethod.POST)
	public ResponseEntity createSponsor(@Valid @RequestParam(value="name") String name,
			 @RequestParam(value = "description", required = false) String description,
		//	 @RequestParam(value = "address", required = false) String address,//temporary delet after figuring out address as embedded
			 @RequestParam(value = "state", required = false) String state,
			 @RequestParam(value = "street", required = false) String street,
			 @RequestParam(value = "city", required = false) String city,
			 @RequestParam(value = "zip", required = false) String zip)
	{
		
        sponserHndlr= new SponsorHandler();
		return new ResponseEntity(sponserHndlr.createSponsor(name,description,state,street,city,zip),HttpStatus.OK);
	}

	@RequestMapping(value="/sponsor/{id}",method=RequestMethod.GET)
	public ResponseEntity<String> getSponsor(@PathVariable("id") long id)  
	{
		sponserHndlr= new SponsorHandler();
		if(sponserHndlr.getSponsor(id)==null)
		{
			return new ResponseEntity<String>("Sponsor doesnt exist.Please add the sponsor first.",HttpStatus.NOT_FOUND);
		}
		else
		{
			return new ResponseEntity(sponserHndlr.getSponsor(id),HttpStatus.OK);
		}
	}
	
	@RequestMapping(value="/sponsor/{id}",method=RequestMethod.DELETE)
	public ResponseEntity<String> deleteSponsor(@PathVariable("id") long id)
	{
		sponserHndlr= new SponsorHandler();
		if(sponserHndlr.checksponsorExists(id))
		{
		return new ResponseEntity(sponserHndlr.delete(id),HttpStatus.OK);
		}
		else
		{
		return new ResponseEntity<String>("Sponsor doesnt exist.Please add the sponsor first.",HttpStatus.NOT_FOUND);
		}
	}

	@RequestMapping(value="/sponsor/{id}",method=RequestMethod.POST)
	public ResponseEntity updateSponsor(@PathVariable("id") long id,@Valid @RequestParam(value="name") String name,
			 @RequestParam(value = "description", required = false) String description,
			 //@RequestParam(value = "address", required = false) String address,
			 @RequestParam(value = "state", required = false) String state,
			 @RequestParam(value = "street", required = false) String street,
			 @RequestParam(value = "city", required = false) String city,@RequestParam(value = "zip", required = false) String zip)
		{
		sponserHndlr= new SponsorHandler();
			if(!sponserHndlr.checksponsorExists(id))
			{
				return new ResponseEntity<String>("This Sponsor does not exist",HttpStatus.NOT_FOUND);
			}
			else
			{
			return new ResponseEntity(sponserHndlr.updateSponsor(id,name,description,state,street,city,zip),HttpStatus.OK);
			}
		}
	
	
	//opponents
	
	@RequestMapping(value="/opponents/{id1}/{id2}",method=RequestMethod.PUT)
	public ResponseEntity<String> addOpponent(@PathVariable("id1") long id1,@PathVariable("id2") long id2)
	{
		oppHandler = new OpponentHandler();
		Opponent opponent = new Opponent();
		if(!oppHandler.checkPlayersExist(id1,id2))
		{
			return new ResponseEntity<String>("Invalid playerids, please check and enter",HttpStatus.NOT_FOUND);
		}
		else
		{
			//opponent.setOpponentid(Long.parseLong(df.format(new Date())));
			opponent.setPlayer1(id1);
			opponent.setPlayer2(id2);
			return new ResponseEntity(oppHandler.newOpponent(opponent),HttpStatus.OK);
		}
		
		
	}
	
	@RequestMapping(value="/opponents/{id1}/{id2}",method=RequestMethod.DELETE)
	public ResponseEntity<String> removeOpponent(@PathVariable("id1") long id1,@PathVariable("id2") long id2)
	{
		oppHandler = new OpponentHandler();
		Opponent opponent = new Opponent();
		if(!oppHandler.checkPlayersExist(id1,id2))
		{
			System.out.println("invalid players");
			return new ResponseEntity<String>("Invalid playerids, please check and enter",HttpStatus.NOT_FOUND);
		}
		else if(!oppHandler.checkPlayersAsOpponents(id1,id2))
		{
			System.out.println("players are not opponents");
			return new ResponseEntity<String>("Players are not opponents, please check and enter",HttpStatus.NOT_FOUND);
		}
		else 
			//if(oppHandler.checkPlayersAsOpponents(id1,id2))
		{
			System.out.println("players are  opponents");
			int result = oppHandler.deleteOpponent(id1,id2);
			if(result == 1)
			{
				return new ResponseEntity<String>("Opponents deleted successfully",HttpStatus.OK);
			}
			else
			{
				return new ResponseEntity<String>("Could not delete opponents",HttpStatus.OK);
			}
		}	
	}
	
}
