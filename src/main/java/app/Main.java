package app;

import app.controllers.AccountController;
import app.controllers.TransferController;
import app.daos.AccountDao;

import static spark.Spark.*;

public class Main
{
    public static AccountDao accountDao;

    public static void initializeData()
	{
		accountDao = new AccountDao();
		int iterations = 5;
		while(iterations-- > 0)
		{
			int id = accountDao.createAccount();
			accountDao.updateAccount(id, 2000);
		}
	}

	public static void setupPaths()
	{
		get("/account/:id", AccountController.accountGet);
		post("/account", AccountController.accountPost);
		delete("/account/:id", AccountController.accountDelete);
		post("/account/:userId/transfer", TransferController.makeTransferPost);
	}

	public static void main(String[] args)
	{
		initializeData();

		setupPaths();
	}
}