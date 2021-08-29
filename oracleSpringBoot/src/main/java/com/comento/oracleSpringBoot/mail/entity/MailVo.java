package com.comento.oracleSpringBoot.mail.entity;

import java.text.SimpleDateFormat;
import java.util.Date;

public class MailVo {
	int mailNo;
	String writer;
	Date writeDate;
	String target;
	String title;
	String body;
	String date;
	public String getDate() {
		return date;
	}
	public void setDate() {
		SimpleDateFormat f = new SimpleDateFormat("yy/MM/dd HH:mm");
		date = f.format(writeDate);
	}
	public int getMailNo() {
		return mailNo;
	}
	public void setMailNo(int mailNo) {
		this.mailNo = mailNo;
	}
	public String getWriter() {
		return writer;
	}
	public void setWriter(String writer) {
		this.writer = writer;
	}
	public Date getWriteDate() {
		return writeDate;
	}
	public void setWriteDate(Date writeDate) {
		this.writeDate = writeDate;
		setDate();
	}
	public String getTarget() {
		return target;
	}
	public void setTarget(String target) {
		this.target = target;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getBody() {
		return body;
	}
	public void setBody(String body) {
		this.body = body;
	}
}
