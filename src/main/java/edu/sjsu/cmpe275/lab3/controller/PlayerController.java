package edu.sjsu.cmpe275.lab3.controller;

import javax.validation.Valid;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import edu.sjsu.cmpe275.lab3.handler.PlayerHandler;
import edu.sjsu.cmpe275.lab3.handler.SponsorHandler;
import edu.sjsu.cmpe275.lab3.resource.Player;

@Controller
@EnableAutoConfiguration
@RequestMapping(value = ("/player"))
public class PlayerController {
	PlayerHandler handler;
	
	/*
	 * Post api to create a player
	 * returns the player object
	 */
	@RequestMapping(method=RequestMethod.POST)
	public ResponseEntity<Player> createPlayer(@Valid @RequestParam(value="firstname") String firstname,
			 @Valid @RequestParam(value="lastname") String lastname,
			 @Valid @RequestParam(value="email") String email,
			 @RequestParam(value = "description", required = false) String description,
			 @RequestParam(value = "street", required = false) String street,
			 @RequestParam(value = "city", required = false) String city,
			 @RequestParam(value = "state", required = false) String state,
			 @RequestParam(value = "zip", required = false) String zip,
			 @RequestParam(value = "sponsor", required = false) Long sponsor)
	{
		handler=new PlayerHandler();
		if(handler.checkIfEmailAlreadyRegistered(email))
		{
			return new ResponseEntity("Email Id is already Registered.Please use a different email id.",HttpStatus.BAD_REQUEST);	
		}
		else
		{
			return new ResponseEntity<Player>(handler.createPlayer(firstname,lastname,email,description,street,city,state,zip,sponsor),HttpStatus.OK);	
		}
		// have to throw different error if email id already exists
	}
	
	@RequestMapping(value="/{id}",method=RequestMethod.GET)
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
	
	@RequestMapping(value="/{id}",method=RequestMethod.DELETE)
	public ResponseEntity<String> deletePlayer(@PathVariable("id") long id)
	{
		handler=new PlayerHandler();
		if(handler.checkPlayerExists(id))
		{
			return new ResponseEntity(handler.delete(id),HttpStatus.OK);
		}
		else
		{
			return new ResponseEntity<String>("This Player does not exist.Cannot be deleted.",HttpStatus.NOT_FOUND);
		}
	}
	
	@RequestMapping(value="/{id}",method=RequestMethod.POST)
	public ResponseEntity updatePlayer(@PathVariable("id") long id,
			@Valid @RequestParam(value="firstname") String firstname,
			 @Valid @RequestParam(value="lastname") String lastname,
			 @Valid @RequestParam(value="email") String email,
			 @RequestParam(value = "description", required = false) String description,
			 @RequestParam(value = "street", required = false) String street,
			 @RequestParam(value = "city", required = false) String city,
			 @RequestParam(value = "state", required = false) String state,
			 @RequestParam(value = "zip", required = false) String zip,
			 @RequestParam(value = "sponsor", required = false) Long sponsor)
	{
		handler=new PlayerHandler();
		if(!handler.checkPlayerExists(id))
		{
			return new ResponseEntity<String>("This Player does not exist.Cannot be updated",HttpStatus.NOT_FOUND);
		}
		else
		{
			SponsorHandler spHandler = new SponsorHandler();
			if(!spHandler.checksponsorExists(sponsor))
			{
				return new ResponseEntity<String>("This Sponsor does not exist, "
						+ "please enter a valid sponsor and update",HttpStatus.BAD_REQUEST);
			}
			else
			{
				return new ResponseEntity(handler.updatePlayer(id,firstname,lastname,email,description,street,city,state,zip,sponsor),HttpStatus.OK);
			}
		}
	}

}
