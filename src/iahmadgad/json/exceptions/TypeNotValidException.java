package iahmadgad.json.exceptions;

public class TypeNotValidException extends Exception
{
	public TypeNotValidException(String message) 
	{
		super(message);
    }

    public TypeNotValidException(Throwable cause) {
        super(cause);
    }

    public TypeNotValidException(String message, Throwable cause) {
        super(message, cause);
    }
}
