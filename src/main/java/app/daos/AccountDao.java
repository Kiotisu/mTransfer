package app.daos;

import app.models.Account;

import java.util.List;
import java.util.Map;
import java.util.TreeMap;

public class AccountDao
{
	private int counter;
    private Map<Integer, Account> accounts;

	public AccountDao()
	{
		counter = 0;
	    accounts = new TreeMap<>();
	}

	public Account getAccount(int id)
	{
		Account account = accounts.get(id);
		if(account == null)
        	return null;

		return new Account(account);
	}

	public int createAccount()
    {
        accounts.put(++counter, new Account(counter, 0));
        return counter;
    }

	public boolean updateAccount(int id, int newBalance)
	{
		if(accounts.containsKey(id))
		{
			accounts.get(id).setBalance(newBalance);
			return true;
		}
		return false;
	}

    public boolean updateAccounts(List<Account> accountsForUpdate)
    {
    	for(Account account : accountsForUpdate)
		{
			if(!accounts.containsKey(account.getId()))
				return false;
		}
		for(Account account : accountsForUpdate)
		{
			accounts.put(account.getId(), account);
		}
		return true;
    }

    public boolean deleteAccount(int id)
    {
        if(accounts.containsKey(id))
        {
            accounts.remove(id);
            return true;
        }
        return false;
    }
}
