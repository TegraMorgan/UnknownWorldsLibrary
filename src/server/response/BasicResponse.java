package server.response;

import java.io.Serializable;
import java.lang.reflect.Type;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

/**
 * The Class BasicResponse.
 */
public class BasicResponse implements Serializable {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;
	
	/** The result. */
	private String result;

	/**
	 * Instantiates a new basic response.
	 */
	public BasicResponse() {
		this.result = "success";
	}

	/**
	 * Instantiates a new basic response.
	 *
	 * @param su the su
	 */
	public BasicResponse(String su) {
		this.result = su;
	}

	/**
	 * Gets the type.
	 *
	 * @return the type
	 */
	public java.lang.reflect.Type getType() {
		return new TypeToken<BasicResponse>() {
		}.getType();
	}

	/**
	 * Tojson.
	 *
	 * @return the string
	 */
	public String tojson() {
		Gson gson = new GsonBuilder().create();
		Type type = this.getType();
		return gson.toJson(this, type);
	}

	/**
	 * Gets the result.
	 *
	 * @return the result
	 */
	public String getResult() {
		return result;
	}

	/**
	 * Sets the result.
	 *
	 * @param response the new result
	 */
	public void setResult(String response) {
		this.result = response;
	}

	/**
	 * Sets the result success.
	 */
	public void setResultSuccess() {
		this.result = "success";
	}

	/**
	 * Sets the result fail.
	 */
	public void setResultFail() {
		this.result = "fail";
	}

}
