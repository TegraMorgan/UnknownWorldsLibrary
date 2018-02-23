package server.response;

import java.lang.reflect.Type;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import server.model.Position;

/**
 * The Class GetMyOldPositionResponse.
 */
public class GetMyOldPositionResponse extends BasicResponse {
	
	/** The pos. */
	private Position pos;

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/**
	 * Instantiates a new gets the my old position response.
	 */
	public GetMyOldPositionResponse() {
		pos = null;
	}

	/**
	 * Instantiates a new gets the my old position response.
	 *
	 * @param st the st
	 * @param pos the pos
	 */
	public GetMyOldPositionResponse(String st, Position pos) {
		super(st);
		this.pos = pos;
	}

	/* (non-Javadoc)
	 * @see server.response.BasicResponse#getType()
	 */
	public java.lang.reflect.Type getType() {
		return new TypeToken<GetMyOldPositionResponse>() {
		}.getType();
	}

	/* (non-Javadoc)
	 * @see server.response.BasicResponse#tojson()
	 */
	public String tojson() {
		Gson gson = new GsonBuilder().create();
		Type type = this.getType();
		return gson.toJson(this, type);
	}

	/**
	 * Gets the position.
	 *
	 * @return the position
	 */
	public Position getPosition() {
		return pos;
	}

	/**
	 * Sets the position.
	 *
	 * @param pos the new position
	 */
	public void setPosition(Position pos) {
		this.pos = pos;
	}

}
