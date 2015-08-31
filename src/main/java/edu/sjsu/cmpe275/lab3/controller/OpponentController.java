package edu.sjsu.cmpe275.lab3.controller;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import edu.sjsu.cmpe275.lab3.handler.OpponentHandler;


@Controller
@EnableAutoConfiguration
@RequestMapping(value = ("/opponents"))
public class OpponentController {

	OpponentHandler oppHandler;
	/*
	 * method to add two players as opponents
	 * returns created opponent object
	 */
	@RequestMapping(value="/{id1}/{id2}",method=RequestMethod.PUT)
	public ResponseEntity<String> addOpponent(@PathVariable("id1") long id1,@PathVariable("id2") long id2)
	{
		oppHandler = new OpponentHandler();
		if(!oppHandler.checkPlayersExist(id1,id2))
		{
			return new ResponseEntity<String>("Invalid playerids, please check and enter",HttpStatus.NOT_FOUND);
		}
		else
		{
			if(oppHandler.checkAlreadyOpponents(id1,id2))
			{
				return new ResponseEntity<String>("These players are already opponents",HttpStatus.OK);
			}
			else
			{
				return new ResponseEntity(oppHandler.newOpponent(id1,id2),HttpStatus.OK);
			}
		}
	}
	
	/*
	 * method to delete two players as opponents
	 * returns a string message to indicate deletion success/failure 
	 */
	
	@RequestMapping(value="/{id1}/{id2}",method=RequestMethod.DELETE)
	public ResponseEntity<String> removeOpponent(@PathVariable("id1") long id1,@PathVariable("id2") long id2)
	{
		oppHandler = new OpponentHandler();
		if(!oppHandler.checkPlayersExist(id1,id2))
		{
			System.out.println("invalid players");
			return new ResponseEntity<String>("Invalid players, please check player id's and enter",HttpStatus.NOT_FOUND);
		}
		else if(!oppHandler.checkAlreadyOpponents(id1,id2))
		{
			System.out.println("players are not opponents");
			return new ResponseEntity<String>("Players are not opponents, please check and enter",HttpStatus.NOT_FOUND);
		}
		else 
		{
			System.out.println("players are  opponents");
			int result = oppHandler.deleteOpponent(id1,id2);
			if(result>0)
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
