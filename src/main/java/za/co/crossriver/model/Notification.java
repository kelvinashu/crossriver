package za.co.crossriver.model;

import java.io.Serializable;
import java.util.Date;

/**
 *
 * @author Temi
 */
public class Notification implements Serializable {

	private static final long serialVersionUID = 1L;
	private Integer id;
	private Date dateSent;
	private Date dateRead;
	private String sentFrom;
	private String sentTo;
	private String message;
	private Boolean isRead;

	public Notification() {
	}

	public Notification(Integer id) {
		this.id = id;
	}

	public Notification(Integer id, Date dateSent, String sentFrom, String sentTo, String message) {
		this.id = id;
		this.dateSent = dateSent;
		this.sentFrom = sentFrom;
		this.sentTo = sentTo;
		this.message = message;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Date getDateSent() {
		return dateSent;
	}

	public void setDateSent(Date dateSent) {
		this.dateSent = dateSent;
	}

	public Date getDateRead() {
		return dateRead;
	}

	public void setDateRead(Date dateRead) {
		this.dateRead = dateRead;
	}

	public String getSentFrom() {
		return sentFrom;
	}

	public void setSentFrom(String sentFrom) {
		this.sentFrom = sentFrom;
	}

	public String getSentTo() {
		return sentTo;
	}

	public void setSentTo(String sentTo) {
		this.sentTo = sentTo;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public Boolean getIsRead() {
		return isRead;
	}

	public void setIsRead(Boolean isRead) {
		this.isRead = isRead;
	}

	@Override
	public int hashCode() {
		int hash = 0;
		hash += (id != null ? id.hashCode() : 0);
		return hash;
	}

	@Override
	public boolean equals(Object object) {
		if (!(object instanceof Notification)) {
			return false;
		}
		Notification other = (Notification) object;
		if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		return "Notification [id=" + id + ", dateSent=" + dateSent + ", dateRead=" + dateRead + ", sentFrom=" + sentFrom
				+ ", sentTo=" + sentTo + ", message=" + message + ", isRead=" + isRead + "]";
	}

}