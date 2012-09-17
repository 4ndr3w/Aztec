package lobos.andrew.aztec.http;

public class ErrorFactory {
	public static HTTPResponse notFound()
	{
		return new HTTPResponse(404, "<h1>Not Found</h1>The requested URL was not found.");
	}
	
	public static HTTPResponse internalServerError(String debugInformation)
	{
		return new HTTPResponse(500, "<h1>Internal Server Error</h1>"+debugInformation);
	}
	
	public static HTTPResponse internalServerError()
	{
		return internalServerError("");
	}
	
	public static HTTPResponse notAuthorized()
	{
		return new HTTPResponse(401, "<h1>Not Authorized</h1>");
	}
	
}
