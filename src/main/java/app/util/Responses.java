package app.util;

import org.eclipse.jetty.http.HttpStatus;
import spark.Response;

public class Responses
{
	public static String updateFailedResponse(Response response)
	{
		response.status(HttpStatus.INTERNAL_SERVER_ERROR_500);
		return "Failed to save the data after transaction";
	}

	public static String insufficientFundsResponse(Response response)
	{
		response.status(HttpStatus.BAD_REQUEST_400);
		return "Insufficient Funds";
	}

	public static String incorrectParameterResponse(Response response, String parameterName)
	{
		response.status(HttpStatus.BAD_REQUEST_400);
		return String.format("Incorrect parameter: %s", parameterName);
	}

	public static String missingResponse(Response response, String parameterName)
	{
		response.status(HttpStatus.BAD_REQUEST_400);
		return String.format("Missing parameter: %s", parameterName);
	}

	public static String accountNotFoundResponse(Response response, int accountId)
	{
		response.status(HttpStatus.NOT_FOUND_404);
		return String.format("Account with id %d not found", accountId);
	}
}
