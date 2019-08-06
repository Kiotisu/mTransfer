package app.models;

public class Account
{
	private int id;
	private int balance;

	public Account(int id, int balance)
	{
		this.id = id;
		this.balance = balance;
	}

	public Account(Account original)
	{
		this.id = original.id;
		this.balance = original.balance;
	}

	public int getId()
	{
		return id;
	}

	public int getBalance()
	{
		return balance;
	}

	public void setBalance(int balance)
	{
		this.balance = balance;
	}

	public String toJSONString()
	{
		return String.format("{ \"id\": %d, \"balance\": %d }", id, balance);
	}
}
