package com.example.SSM;

import java.util.Date;
import java.util.Timer;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;



@Entity
public class Job {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	private String messageText;
	private Date time;
	private String channel;
	private String status;
	@OneToOne(targetEntity=MyTask.class)
	@Cascade(CascadeType.ALL)
	private MyTask task;

	public Job() {
	}

	public Job(String messageText, Date time) {
		Date newtime = new Date(time.getTime() - 7200000);	//should be timezoneoffset
		this.setMessageText(messageText);
		this.setTime(newtime);
		this.setChannel(channel);
		this.setStatus(status);
		this.task = new MyTask(this.getMessageText());
	    Timer timer = new Timer();
	    timer.schedule(task, this.getTime());
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getMessageText() {
		return messageText;
	}

	public void setMessageText(String messageText) {
		this.messageText = messageText;
	}

	public Date getTime() {
		return time;
	}

	public void setTime(Date time) {
		this.time = time;
	}

	public String getChannel() {
		return channel;
	}

	public void setChannel(String channel) {
		this.channel = channel;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

}
