package app.mocks;

import spark.Response;

public class ResponseMock extends Response
{
	private int status;
	private String type;


	public void status(int statusCode) {
		status = statusCode;
	}

	public int status() {
		return status;
	}

	public void type(String contentType) {
		type = contentType;
	}

	public String type() {
		return type;
	}
}
