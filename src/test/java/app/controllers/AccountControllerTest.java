package app.controllers;

import app.Main;
import app.mocks.RequestMock;
import app.mocks.ResponseMock;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;
import static app.Main.accountDao;

public class AccountControllerTest
{
	@Before
	public void before()
	{
		Main.initializeData();
	}

	@Test
	public void get()
	{
		//given
		RequestMock request = new RequestMock();
		request.setParam("id", "1");
		ResponseMock response = new ResponseMock();
		String result = null;

		//when
		try
		{
			result = (String) AccountController.accountGet.handle(request, response);
		}
		//then
		catch (Exception e)
		{
			assertNull(e);
			assertNull(e.getMessage());
		}
		assertEquals( "{ \"id\": 1, \"balance\": 2000 }", result);
	}

	@Test
	public void incorrectIdGet()
	{
		//given
		RequestMock request = new RequestMock();
		request.setParam("id", "12");
		ResponseMock response = new ResponseMock();
		String result = null;

		//when
		try
		{
			result = (String) AccountController.accountGet.handle(request, response);
		}
		//then
		catch (Exception e)
		{
			assertNull(e);
			assertNull(e.getMessage());
		}
		assertEquals( "Account with id 12 not found", result);
	}

	@Test
	public void incorrectTypeGet()
	{
		//given
		RequestMock request = new RequestMock();
		request.setParam("id", "gdf");
		ResponseMock response = new ResponseMock();
		String result = null;

		//when
		try
		{
			result = (String) AccountController.accountGet.handle(request, response);
		}
		//then
		catch (Exception e)
		{
			assertNull(e);
			assertNull(e.getMessage());
		}
		assertEquals( "Incorrect parameter: id", result);
	}

	@Test
	public void post()
	{
		//given
		RequestMock request = new RequestMock();
		ResponseMock response = new ResponseMock();
		String result = null;

		//when
		try
		{
			result = (String) AccountController.accountPost.handle(request, response);
		}
		//then
		catch (Exception e)
		{
			assertNull(e);
			assertNull(e.getMessage());
		}
		assertEquals( "{ \"id\": 6, \"balance\": 0 }", result);
		assertNotNull(accountDao.getAccount(6));
	}

	@Test
	public void delete()
	{
		//given
		RequestMock request = new RequestMock();
		request.setParam("id", "1");
		ResponseMock response = new ResponseMock();
		String result = null;

		//when
		try
		{
			result = (String) AccountController.accountDelete.handle(request, response);
		}
		//then
		catch (Exception e)
		{
			assertNull(e);
			assertNull(e.getMessage());
		}
		assertEquals( "Account with id 1 successfully deleted", result);
		assertNull(accountDao.getAccount(1));
	}

	@Test
	public void incorrectIdDelete()
	{
		//given
		RequestMock request = new RequestMock();
		request.setParam("id", "21");
		ResponseMock response = new ResponseMock();
		String result = null;

		//when
		try
		{
			result = (String) AccountController.accountDelete.handle(request, response);
		}
		//then
		catch (Exception e)
		{
			assertNull(e);
			assertNull(e.getMessage());
		}
		assertEquals( "Account with id 21 not found", result);
	}

	@Test
	public void incorrectTypeDelete()
	{
		//given
		RequestMock request = new RequestMock();
		request.setParam("id", "fdg");
		ResponseMock response = new ResponseMock();
		String result = null;

		//when
		try
		{
			result = (String) AccountController.accountDelete.handle(request, response);
		}
		//then
		catch (Exception e)
		{
			assertNull(e);
			assertNull(e.getMessage());
		}
		assertEquals( "Incorrect parameter: id", result);
	}
}
