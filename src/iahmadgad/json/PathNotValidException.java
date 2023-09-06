package iahmadgad.json.exceptions;

public class PathNotValidException extends Exception
{
	public PathNotValidException(String message) 
	{
		super(message);
    }

    public PathNotValidException(Throwable cause) {
        super(cause);
    }

    public PathNotValidException(String message, Throwable cause) {
        super(message, cause);
    }
}
