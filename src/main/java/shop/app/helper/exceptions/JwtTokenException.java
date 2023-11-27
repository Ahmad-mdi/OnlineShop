package shop.app.helper.exceptions;

public class JwtTokenException extends Exception{
    public JwtTokenException(String message) {
        super(message);
    }
}
