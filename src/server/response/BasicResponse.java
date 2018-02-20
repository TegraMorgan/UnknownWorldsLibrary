package server.response;

import java.io.Serializable;
import java.lang.reflect.Type;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

public class BasicResponse implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String result;

	public BasicResponse() {
		this.result = "success";
	}

	public BasicResponse(String su) {
		this.result = su;
	}

	public java.lang.reflect.Type getType() {
		return new TypeToken<BasicResponse>() {
		}.getType();
	}

	public String tojson() {
		Gson gson = new GsonBuilder().create();
		Type type = this.getType();
		return gson.toJson(this, type);
	}

	public String getResult() {
		return result;
	}

	public void setResult(String response) {
		this.result = response;
	}

	public void setResultSuccess() {
		this.result = "success";
	}

	public void setResultFail() {
		this.result = "fail";
	}

}
