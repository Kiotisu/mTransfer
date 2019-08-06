package app.daos;

import app.models.Account;
import org.junit.Test;
import java.util.List;

import static org.junit.Assert.*;

public class AccountDaoTest
{
	@Test
	public void getFromEmptyDao()
	{
		//given
		AccountDao dao = new AccountDao();

		//when
		Account account = dao.getAccount(33);

		//then
		assertNull(account);
	}

	@Test
	public void createAccount()
	{
		//given
		AccountDao dao = new AccountDao();

		//when
		int idOne = dao.createAccount();
		int idTwo = dao.createAccount();

		//then
		assertEquals(1, idOne);
		Account account = dao.getAccount(idOne);
		assertNotNull(account);
		assertEquals(0, account.getBalance());

		assertEquals(2, idTwo);
		account = dao.getAccount(idTwo);
		assertNotNull(account);
		assertEquals(0, account.getBalance() );
	}

	@Test
	public void deleteAccount()
	{
		//given
		AccountDao dao = new AccountDao();
		dao.createAccount();

		//when
		boolean result = dao.deleteAccount(1);

		//then
		assertEquals(true, result);
		Account account = dao.getAccount(1);
		assertNull(account);
	}

	@Test
	public void deleteAccountTwice()
	{
		//given
		AccountDao dao = new AccountDao();
		dao.createAccount();

		//when
		boolean resultOne = dao.deleteAccount(1);
		boolean resultTwo = dao.deleteAccount(1);

		//then
		assertEquals(true, resultOne);
		assertEquals(false, resultTwo);
		Account account = dao.getAccount(1);
		assertNull(account);
	}

	@Test
	public void updateSingleAccount()
	{
		//given
		AccountDao dao = new AccountDao();
		dao.createAccount();

		//when
		boolean result = dao.updateAccount(1, 200);

		//then
		assertEquals(true, result);
		Account account = dao.getAccount(1);
		assertEquals(200, account.getBalance());
	}

	@Test
	public void updateNotExistingAccount()
	{
		//given
		AccountDao dao = new AccountDao();

		//when
		boolean result = dao.updateAccount(1, 200);

		//then
		assertEquals(false, result);
	}

	@Test
	public void updateMultipleAccounts()
	{
		//given
		AccountDao dao = new AccountDao();
		Account accountOne = dao.getAccount(dao.createAccount());
		Account accountTwo = dao.getAccount(dao.createAccount());

		//when
		accountOne.setBalance(300);
		accountTwo.setBalance(250);
		boolean result = dao.updateAccounts(List.of(accountOne, accountTwo));

		//then
		assertEquals(true, result);
		Account account = dao.getAccount(1);
		assertEquals(300, account.getBalance());
		account = dao.getAccount(2);
		assertEquals(250, account.getBalance());
	}

	@Test
	public void updateMultipleAccountsFailed()
	{
		//given
		AccountDao dao = new AccountDao();
		Account accountOne = dao.getAccount(dao.createAccount());

		//when
		accountOne.setBalance(300);
		boolean result = dao.updateAccounts(List.of(accountOne, new Account(2, 240)));

		//then
		assertEquals(false, result);
		Account account = dao.getAccount(1);
		assertEquals(0, account.getBalance());
	}
}
