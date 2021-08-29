package com.comento.oracleSpringBoot.approval.entity;

import java.text.SimpleDateFormat;
import java.util.Date;

public class ApprovalVo {
	int approvalNo;
	String writer;
	Date writeDate;
	String target;
	String form;
	String body;
	String date;
	public String getDate() {
		return date;
	}
	public void setDate() {
		SimpleDateFormat f = new SimpleDateFormat("yy/MM/dd HH:mm");
		date = f.format(writeDate);
	}
	public int getApprovalNo() {
		return approvalNo;
	}
	public void setApprovalNo(int approvalNo) {
		this.approvalNo = approvalNo;
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
	public String getForm() {
		return form;
	}
	public void setForm(String form) {
		this.form = form;
	}
	public String getBody() {
		return body;
	}
	public void setBody(String body) {
		this.body = body;
	}
}
