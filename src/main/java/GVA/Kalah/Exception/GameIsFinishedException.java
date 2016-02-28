package GVA.Kalah.Exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Created by Vsevolod on 29.02.2016.
 */
@ResponseStatus(value= HttpStatus.TEMPORARY_REDIRECT)
public class GameIsFinishedException extends Exception {
    public GameIsFinishedException(String message) {
        super(message);
    }
}
