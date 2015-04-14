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

import edu.sjsu.cmpe275.lab3.handler.SponsorHandler;


@Controller
@EnableAutoConfiguration
@RequestMapping(value = ("/sponsor"))
public class SponsorController {

	SponsorHandler sponserHndlr;
	
	@RequestMapping(method=RequestMethod.POST)
	public ResponseEntity createSponsor(@Valid @RequestParam(value="name") String name,
			 @RequestParam(value = "description", required = false) String description,
			 @RequestParam(value = "state", required = false) String state,
			 @RequestParam(value = "street", required = false) String street,
			 @RequestParam(value = "city", required = false) String city,
			 @RequestParam(value = "zip", required = false) String zip)
	{	
        sponserHndlr= new SponsorHandler();
		return new ResponseEntity(sponserHndlr.createSponsor(name,description,state,street,city,zip),HttpStatus.OK);
	}

	@RequestMapping(value="/{id}",method=RequestMethod.GET)
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
	
	@RequestMapping(value="/{id}",method=RequestMethod.DELETE)
	public ResponseEntity<String> deleteSponsor(@PathVariable("id") long id)
	{
		sponserHndlr= new SponsorHandler();
		if(sponserHndlr.PlayerHasSponsorToDelete(id))
		{
		return new ResponseEntity("This sponsor is related to player and cannot be deleted",HttpStatus.BAD_REQUEST);	
		}
		else 
		{
		if(sponserHndlr.checksponsorExists(id)){
			return new ResponseEntity(sponserHndlr.delete(id),HttpStatus.OK);
		}
		else{
			return new ResponseEntity<String>("Sponsor doesnt exist.Please add the sponsor first.",HttpStatus.NOT_FOUND);
		}
		}
	}

	@RequestMapping(value="/{id}",method=RequestMethod.POST)
	public ResponseEntity updateSponsor(@PathVariable("id") long id,@Valid @RequestParam(value="name") String name,
			 @RequestParam(value = "description", required = false) String description,
			 @RequestParam(value = "state", required = false) String state,
			 @RequestParam(value = "street", required = false) String street,
			 @RequestParam(value = "city", required = false) String city,
			 @RequestParam(value = "zip", required = false) String zip)
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

	
}
