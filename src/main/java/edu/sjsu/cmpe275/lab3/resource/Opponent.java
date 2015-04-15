package edu.sjsu.cmpe275.lab3.resource;

import javax.persistence.Table;

import org.hibernate.annotations.Proxy;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

@JsonSerialize(include=JsonSerialize.Inclusion.NON_NULL)
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@Proxy(lazy=false)
@Table(name="Opponents")
public class Opponent 

{
	long player1;
	long player2;
	long opponentid;
	String msg;
	
	public String getMsg()
	{
		return msg;
	}
	public void setMsg(String msg) {
		this.msg = msg;
	}
	public long getPlayer1() {
		return player1;
	}
	public void setPlayer1(long player1) {
		this.player1 = player1;
	}
	public long getPlayer2() {
		return player2;
	}
	public void setPlayer2(long player2) {
		this.player2 = player2;
	}
	public long getOpponentid() {
		return opponentid;
	}
	public void setOpponentid(long opponentid) {
		this.opponentid = opponentid;
	}
}
