
package uni;

import java.io.Serializable;
import java.util.Date;

public class Message implements Serializable, Comparable<Message>  {

	private static final long serialVersionUID = 1L;
	
	private String content;
    private Date date;
    private User author;
    private User receiver;

    public Message() {}
    public Message(String content, Date date, User author, User receiver) {
    	this.content = content;
    	this.date = date;
    	this.author = author;
    	this.receiver = receiver;
    }
    
    public String getContent() {
        return this.content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Date getDate() {
        return this.date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public User getAuthor() {
        return this.author;
    }

    public String getAuthorFullName(){return this.author.getName() + " " + this.author.getSurname() + " " + this.author.getUsername();}

    public void setAuthor(User author) {
        this.author = author;
    }

    public User getReceiver() {
        return this.receiver;
    }

	public String getReceiverFullName(){return this.receiver.getName() + " " + this.receiver.getSurname() + " " + this.receiver.getUsername();}

    public void setReceiver(User receiver) {
        this.receiver = receiver;
    }
    
	@Override
	public int compareTo(Message o) {
		return 0;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((author == null) ? 0 : author.hashCode());
		result = prime * result + ((content == null) ? 0 : content.hashCode());
		result = prime * result + ((date == null) ? 0 : date.hashCode());
		result = prime * result + ((receiver == null) ? 0 : receiver.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Message other = (Message) obj;
		if (author == null) {
			if (other.author != null)
				return false;
		} else if (!author.equals(other.author))
			return false;
		if (content == null) {
			if (other.content != null)
				return false;
		} else if (!content.equals(other.content))
			return false;
		if (date == null) {
			if (other.date != null)
				return false;
		} else if (!date.equals(other.date))
			return false;
		if (receiver == null) {
			if (other.receiver != null)
				return false;
		} else if (!receiver.equals(other.receiver))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Message [content=" + content + ", date=" + date + ", author=" + author + ", receiver=" + receiver + "]";
	}
}
