package edu.sjsu.cmpe275.lab3.controller;
import javax.validation.Valid;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import edu.sjsu.cmpe275.lab3.handler.OpponentHandler;
import edu.sjsu.cmpe275.lab3.handler.PlayerHandler;
import edu.sjsu.cmpe275.lab3.resource.Opponent;
import edu.sjsu.cmpe275.lab3.resource.Player;

@RestController
@EnableAutoConfiguration
public class RestConfig {
	PlayerHandler handler;
	OpponentHandler oppHandler;
	@RequestMapping("/")
    String home() {
        return "Hello World!";
    }
	
	@RequestMapping(value="/player",method=RequestMethod.POST)
	public ResponseEntity<String> createPlayer(
	//public Player createPlayer(
			@Valid @RequestParam(value="firstname") String firstname,
			 @Valid @RequestParam(value="lastname") String lastname,
			 @Valid @RequestParam(value="email") String email,
			 @RequestParam(value = "description", required = false) String description,
			 @RequestParam(value = "address", required = false) String address,
			 @RequestParam(value = "sponsor", required = false) String sponsor)
	{
		handler=new PlayerHandler();
		Player player = new Player();
		player.setFirstname(firstname);
		player.setLastname(lastname);
		player.setEmail(email);
		if(description!=null)
		{
			player.setDescription(description);
		}		
		if(address!=null)
		{
			player.setAddress(address);
		}	
		if(sponsor!=null)
		{
			player.setSponsor(sponsor);
		}
		System.out.println("player is:::"+player);
		return new ResponseEntity(handler.createPlayer(player),HttpStatus.OK);
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
	
	@RequestMapping(value="/player/{id}",method=RequestMethod.POST)
	public ResponseEntity<String> updatePlayer(@PathVariable("id") long id,
			@Valid @RequestParam(value="firstname") String firstname,
			 @Valid @RequestParam(value="lastname") String lastname,
			 @Valid @RequestParam(value="email") String email,
			 @RequestParam(value = "description", required = false) String description,
			 @RequestParam(value = "address", required = false) String address,
			 @RequestParam(value = "sponsor", required = false) String sponsor)
	{
		handler=new PlayerHandler();
		Player player = new Player();
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
			
			if(address!=null)
			{
				player.setAddress(address);
			}
				
			if(sponsor!=null)
			{
				player.setSponsor(sponsor);
			}
			return new ResponseEntity(handler.updatePlayer(player),HttpStatus.OK);
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
