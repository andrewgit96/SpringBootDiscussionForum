package ca.sheridancollege.pereir47.database;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;


import ca.sheridancollege.pereir47.beans.Messages;
import ca.sheridancollege.pereir47.beans.Thread;

@Repository
public class DatabaseAccess {
	
	@Autowired
	protected NamedParameterJdbcTemplate jdbc;
	
	
		public void insertThread(Thread thread) {
		
		String query = "INSERT INTO thread (threadName, employee) VALUES (:threadName, :employee)";
		
		MapSqlParameterSource namedParameters = new MapSqlParameterSource();
		namedParameters.addValue("threadName", thread.getThreadName());
		namedParameters.addValue("employee", thread.getEmployee());
		jdbc.update(query, namedParameters);
	}
	
	public void insertMessage(Messages message) {
		String query = "INSERT INTO messages(threadId, message, dates, times, employee) VALUES(:threadId, :message, :dates, :times, :employee)";
		MapSqlParameterSource namedParameters = new MapSqlParameterSource();
		namedParameters.addValue("threadId", message.getThreadId());
		namedParameters.addValue("message", message.getMessage());
		namedParameters.addValue("dates", message.getDates());
		namedParameters.addValue("times", message.getTimes());
		namedParameters.addValue("employee", message.getEmployee());
		jdbc.update(query, namedParameters);
	}
	
	public List<Thread> getAllThreads(){
		
		String query = "SELECT * FROM thread";
		MapSqlParameterSource namedParameters = new MapSqlParameterSource();
		
		return jdbc.query(query, namedParameters, new BeanPropertyRowMapper<Thread>(Thread.class));
	}
	
	
	
	public List<Messages> getAllMessages(){
		
		String query = "SELECT * FROM messages";
		MapSqlParameterSource namedParameters = new MapSqlParameterSource();
		
		return jdbc.query(query, namedParameters, new BeanPropertyRowMapper<Messages>(Messages.class));
	}
		
	
		public List <Messages> threadId(Long threadId){
			MapSqlParameterSource namedParameters = new MapSqlParameterSource();
			String query = "SELECT * FROM messages WHERE threadId = :threadId";		
			namedParameters.addValue("threadId", threadId);
			return jdbc.query(query, namedParameters, new BeanPropertyRowMapper<Messages>(Messages.class));
		}
		



}
