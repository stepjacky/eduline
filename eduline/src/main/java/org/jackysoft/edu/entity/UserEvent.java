package org.jackysoft.edu.entity;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

import org.jackysoft.edu.annotation.AutoValue;
import org.jackysoft.edu.annotation.Column;
import org.jackysoft.edu.annotation.IdStrategy;
import org.jackysoft.edu.annotation.Table;

@Table(label = "用户事件")
public class UserEvent implements Cloneable,Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1237065306448160066L;

	@Column(id=true,idStrategy=IdStrategy.UUID)
	private String id;
	
	@Column(autoValue=AutoValue.CURRENT_USERNAME)
	private String username;
	
	@Column(autoValue=AutoValue.CURRENT_USERNICK)
	private String nickname;
	
	@Column(autoValue=AutoValue.CURRENT_TIMEMILLIS)
	private long firetime;
	
	@Column
	private long starttime=-1;
	
	@Column
	private long endtime=-1;
	
	@Column
	private String name;
	
	
	//0 全体可见,1仅老师可见，2仅自己可见,3仅自己可见并且重复
	@Column
	private int eventType = 0;
	
	//0不重复，1重复
	@Column
	private int repeated=0;
	
	@Column(label="重复开始时间")
	private long repeatStart=0L;
	
	@Column(label="重复结束时间")
	private long repeatEnd=0L;
	
	//-1代表非重复事件
	@Column(label="重复点【周X】")
	private int dayOfweek=-1;
	
	@Column
	private String content;

    @Column(label="事件形式"
    		,data={"event-important", "event-success", "event-warning"
              , "event-info", "event-inverse", "event-special"})
    private String className;
	
	public String getId() {
		return id;
	}


	public void setId(String id) {
		this.id = id;
	}


	public String getUsername() {
		return username;
	}


	public void setUsername(String username) {
		this.username = username;
	}


	public String getNickname() {
		return nickname;
	}


	public void setNickname(String nickname) {
		this.nickname = nickname;
	}


	public long getFiretime() {
		return firetime;
	}


	public void setFiretime(long firetime) {
		this.firetime = firetime;
	}


	public long getStarttime() {
		return starttime;
	}


	public void setStarttime(long starttime) {
		this.starttime = starttime;
	}


	public long getEndtime() {
		return endtime;
	}


	public void setEndtime(long endtime) {
		this.endtime = endtime;
	}


	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	public String getContent() {
		return content;
	}


	public void setContent(String content) {
		this.content = content;
	}


	public int getEventType() {
		return eventType;
	}


	public void setEventType(int eventType) {
		this.eventType = eventType;
	}


	public int getRepeated() {
		return repeated;
	}


	public void setRepeated(int repeat) {
		this.repeated = repeat;
	}


	public long getRepeatStart() {
		return repeatStart;
	}


	public void setRepeatStart(long repeatStart) {
		this.repeatStart = repeatStart;
	}


	public long getRepeatEnd() {
		return repeatEnd;
	}


	public void setRepeatEnd(long repeatEnd) {
		this.repeatEnd = repeatEnd;
	}


	public int getDayOfweek() {
		return dayOfweek;
	}


	public void setDayOfweek(int dayOfweek) {
		this.dayOfweek = dayOfweek;
	}


	public String getClassName() {
		return className;
	}


	public void setClassName(String className) {
		this.className = className;
	}


	@Override
	public UserEvent clone() {
		//将对象写到流里    
		ByteArrayOutputStream bo=new ByteArrayOutputStream();    
		ObjectOutputStream oo = null;
		ObjectInputStream oi = null;
		Object target = null;
		try {
			oo = new ObjectOutputStream(bo);
			oo.writeObject(this);    
			ByteArrayInputStream bi=new ByteArrayInputStream(bo.toByteArray());    
			oi =new ObjectInputStream(bi);    
			target = oi.readObject();    
		} catch (IOException | ClassNotFoundException e) {
			
			e.printStackTrace();
		}    
		
		return (UserEvent)target;
		
	}
	
	
	
	
	
	
}
