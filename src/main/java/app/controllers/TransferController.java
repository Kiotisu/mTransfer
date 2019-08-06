package app.controllers;

import app.models.Account;
import app.util.Responses;
import spark.Request;
import spark.Response;
import spark.Route;

import java.util.List;

import static app.Main.accountDao;

public class TransferController
{
	public static Route makeTransferPost = (Request request, Response response) ->
	{
		Integer userId;
		try
		{
			String userIdParam = request.params("userId");
			if(userIdParam == null)
				return Responses.missingResponse(response, "userId");
			userId = Integer.parseInt(userIdParam);
		}
		catch (NumberFormatException e)
		{
			return Responses.incorrectParameterResponse(response, "userId");
		}

		Integer receiverId;
		try
		{
			String receiverIdParam = request.queryParams("receiverId");
			if(receiverIdParam == null)
				return Responses.missingResponse(response, "receiverId");
			receiverId = Integer.parseInt(receiverIdParam);
		}
		catch (NumberFormatException e)
		{
			return Responses.incorrectParameterResponse(response, "receiverId");
		}

		Integer amount;
		try
		{
			String amountParam = request.queryParams("amount");
			if(amountParam == null)
				return Responses.missingResponse(response, "amount");
			amount = Integer.parseInt(amountParam);
		}
		catch (NumberFormatException e)
		{
			return Responses.incorrectParameterResponse(response, "amount");
		}
		if(amount < 0)
			return Responses.incorrectParameterResponse(response, "amount");

		Account sender = accountDao.getAccount(userId);
		if(sender == null)
			return Responses.accountNotFoundResponse(response, userId);

		Account receiver = accountDao.getAccount(receiverId);
		if(receiver == null)
			return Responses.accountNotFoundResponse(response, receiverId);

		int senderBalance = sender.getBalance();
		if(senderBalance < amount)
			return Responses.insufficientFundsResponse(response);

		int receiverBalance = receiver.getBalance();

		sender.setBalance(senderBalance-amount);
		receiver.setBalance(receiverBalance+amount);

		boolean result = accountDao.updateAccounts(List.of(sender, receiver));

		if(!result)
			return Responses.updateFailedResponse(response);

		return "Transfer Successful";
	};
}
