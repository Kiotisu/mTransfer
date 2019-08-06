package app.controllers;

import app.Main;
import app.mocks.RequestMock;
import app.mocks.ResponseMock;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;
import static app.Main.accountDao;

public class TransferControllerTest
{
	@Before
	public void before()
	{
		Main.initializeData();
	}

	@Test
	public void successfulTransfer()
	{
		//given
		RequestMock request = new RequestMock();
		request.setParam("userId", "1");
		request.setQueryParams("receiverId", "2");
		request.setQueryParams("amount", "100");
		ResponseMock response = new ResponseMock();
		String result = null;

		//when
		try
		{
			result = (String) TransferController.makeTransferPost.handle(request, response);
		}
		//then
		catch (Exception e)
		{
			assertNull(e);
			assertNull(e.getMessage());
		}
		assertEquals( "Transfer Successful", result);
		assertEquals(1900, accountDao.getAccount(1).getBalance());
		assertEquals(2100, accountDao.getAccount(2).getBalance());
	}

	@Test
	public void insufficientFunds()
	{
		//given
		RequestMock request = new RequestMock();
		request.setParam("userId", "1");
		request.setQueryParams("receiverId", "2");
		request.setQueryParams("amount", "3500");
		ResponseMock response = new ResponseMock();
		String result = null;

		//when
		try
		{
			result = (String) TransferController.makeTransferPost.handle(request, response);
		}
		//then
		catch (Exception e)
		{
			assertNull(e);
			assertNull(e.getMessage());
		}
		assertEquals( "Insufficient Funds", result);
		assertEquals(2000, accountDao.getAccount(1).getBalance());
		assertEquals(2000, accountDao.getAccount(2).getBalance());
	}

	@Test
	public void wrongTypeId()
	{
		//given
		RequestMock request = new RequestMock();
		request.setParam("userId", "aaa");
		request.setQueryParams("receiverId", "2");
		request.setQueryParams("amount", "100");
		ResponseMock response = new ResponseMock();
		String result = null;

		//when
		try
		{
			result = (String) TransferController.makeTransferPost.handle(request, response);
		}
		//then
		catch (Exception e)
		{
			assertNull(e);
			assertNull(e.getMessage());
		}
		assertEquals( "Incorrect parameter: userId", result);
		assertEquals(2000, accountDao.getAccount(1).getBalance());
		assertEquals(2000, accountDao.getAccount(2).getBalance());
	}

	@Test
	public void missingUserId()
	{
		//given
		RequestMock request = new RequestMock();
		request.setQueryParams("receiverId", "2");
		request.setQueryParams("amount", "100");
		ResponseMock response = new ResponseMock();
		String result = null;

		//when
		try
		{
			result = (String) TransferController.makeTransferPost.handle(request, response);
		}
		//then
		catch (Exception e)
		{
			assertNull(e);
			assertNull(e.getMessage());
		}
		assertEquals( "Missing parameter: userId", result);
		assertEquals(2000, accountDao.getAccount(1).getBalance());
		assertEquals(2000, accountDao.getAccount(2).getBalance());
	}

	@Test
	public void missingReceiverId()
	{
		//given
		RequestMock request = new RequestMock();
		request.setParam("userId", "1");
		request.setQueryParams("amount", "100");
		ResponseMock response = new ResponseMock();
		String result = null;

		//when
		try
		{
			result = (String) TransferController.makeTransferPost.handle(request, response);
		}
		//then
		catch (Exception e)
		{
			assertNull(e);
			assertNull(e.getMessage());
		}
		assertEquals( "Missing parameter: receiverId", result);
		assertEquals(2000, accountDao.getAccount(1).getBalance());
		assertEquals(2000, accountDao.getAccount(2).getBalance());
	}

	@Test
	public void missingAmount()
	{
		//given
		RequestMock request = new RequestMock();
		request.setParam("userId", "1");
		request.setQueryParams("receiverId", "2");
		ResponseMock response = new ResponseMock();
		String result = null;

		//when
		try
		{
			result = (String) TransferController.makeTransferPost.handle(request, response);
		}
		//then
		catch (Exception e)
		{
			assertNull(e);
			assertNull(e.getMessage());
		}
		assertEquals( "Missing parameter: amount", result);
		assertEquals(2000, accountDao.getAccount(1).getBalance());
		assertEquals(2000, accountDao.getAccount(2).getBalance());
	}

	@Test
	public void negativeAmount()
	{
		//given
		RequestMock request = new RequestMock();
		request.setParam("userId", "1");
		request.setQueryParams("receiverId", "2");
		request.setQueryParams("amount", "-100");
		ResponseMock response = new ResponseMock();
		String result = null;

		//when
		try
		{
			result = (String) TransferController.makeTransferPost.handle(request, response);
		}
		//then
		catch (Exception e)
		{
			assertNull(e);
			assertNull(e.getMessage());
		}
		assertEquals( "Incorrect parameter: amount", result);
		assertEquals(2000, accountDao.getAccount(1).getBalance());
		assertEquals(2000, accountDao.getAccount(2).getBalance());
	}

	@Test
	public void nonexistentReceiver()
	{
		//given
		RequestMock request = new RequestMock();
		request.setParam("userId", "1");
		request.setQueryParams("receiverId", "432");
		request.setQueryParams("amount", "100");
		ResponseMock response = new ResponseMock();
		String result = null;

		//when
		try
		{
			result = (String) TransferController.makeTransferPost.handle(request, response);
		}
		//then
		catch (Exception e)
		{
			assertNull(e);
			assertNull(e.getMessage());
		}
		assertEquals( "Account with id 432 not found", result);
		assertEquals(2000, accountDao.getAccount(1).getBalance());
	}
}
