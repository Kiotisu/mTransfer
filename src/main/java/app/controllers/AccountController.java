package app.controllers;

import app.models.Account;
import app.util.Responses;
import spark.Request;
import spark.Response;
import spark.Route;
import static app.Main.accountDao;

public class AccountController
{
	public static Route accountGet = (Request request, Response response) ->
	{
		Integer userId;
		try
		{
			userId = Integer.parseInt(request.params("id"));
		}
		catch (NumberFormatException e)
		{
			return Responses.incorrectParameterResponse(response, "id");
		}
		Account account = accountDao.getAccount(userId);
		if(account == null)
			return Responses.accountNotFoundResponse(response, userId);

		response.type("application/json");
		return account.toJSONString();
	};

	public static Route accountPost = (Request request, Response response) ->
	{
		response.type("application/json");
		return accountDao.getAccount(accountDao.createAccount()).toJSONString();
	};

	public static Route accountDelete = (Request request, Response response) ->
	{
		Integer userId;
		try
		{
			userId = Integer.parseInt(request.params("id"));
		}
		catch (NumberFormatException e)
		{
			return Responses.incorrectParameterResponse(response, "id");
		}
		boolean result = accountDao.deleteAccount(userId);
		if(!result)
			return Responses.accountNotFoundResponse(response, userId);

		return String.format("Account with id %d successfully deleted", userId);
	};
}
