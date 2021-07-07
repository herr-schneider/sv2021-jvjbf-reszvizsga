package cinema;

public class MovieNotFoundException extends RuntimeException{

    public MovieNotFoundException() {
    }

    public MovieNotFoundException(String massage){
        super(massage);
    }

    public MovieNotFoundException(String massage, Exception e){
        super(massage, e);
    }

    public MovieNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }
}
