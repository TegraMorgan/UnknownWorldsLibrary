package server.model;

import com.sun.jmx.snmp.Timestamp;

public class owns {
	private int uid;
	private int bid;
	private Timestamp dateOf;
	public int getUid() {
		return uid;
	}
	public void setUid(int uid) {
		this.uid = uid;
	}
	public int getBid() {
		return bid;
	}
	public void setBid(int bid) {
		this.bid = bid;
	}
	public Timestamp getDateOf() {
		return dateOf;
	}
	public void setDateOf(Timestamp dateOf) {
		this.dateOf = dateOf;
	}
}
