package app.mocks;

import spark.Request;

import java.util.Map;
import java.util.TreeMap;

public class RequestMock extends Request
{
	private Map<String, String> mockedParams;
	private Map<String, String> mockedQuerryParams;

	public RequestMock()
	{
		this.mockedParams = new TreeMap<>();
		this.mockedQuerryParams = new TreeMap<>();
	}

	public void setParam(String param, String value)
	{
		mockedParams.put(param, value);
	}

	public String params(String param)
	{
		return mockedParams.get(param);
	}

	public void setQueryParams(String queryParam, String value)
	{
		mockedQuerryParams.put(queryParam, value);
	}

	public String queryParams(String queryParam)
	{
		return mockedQuerryParams.get(queryParam);
	}
}
