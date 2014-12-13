package de.szengerle.idoit.api.client.rpc;

public class Response {
	
	String message;
	int id;
	boolean success;
	
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public boolean isSuccess() {
		return success;
	}
	public void setSuccess(boolean success) {
		this.success = success;
	}
	@Override
	public String toString() {
		return "Response [message=" + message + ", id=" + id + ", success="
				+ success + "]";
	}
	
	

}
