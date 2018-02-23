package server.response;

import java.lang.reflect.Type;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import server.model.Position;

public class GetMyOldPositionResponse extends BasicResponse {
	private Position pos;

	private static final long serialVersionUID = 1L;

	public GetMyOldPositionResponse() {
		pos = null;
	}

	public GetMyOldPositionResponse(String st, Position pos) {
		super(st);
		this.pos = pos;
	}

	public java.lang.reflect.Type getType() {
		return new TypeToken<GetMyOldPositionResponse>() {
		}.getType();
	}

	public String tojson() {
		Gson gson = new GsonBuilder().create();
		Type type = this.getType();
		return gson.toJson(this, type);
	}

	public Position getPosition() {
		return pos;
	}

	public void setPosition(Position pos) {
		this.pos = pos;
	}

}
