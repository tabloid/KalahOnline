package GVA.Kalah.Exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Created by Vsevolod on 26.02.2016.
 */
@ResponseStatus(value= HttpStatus.NOT_FOUND, reason="no such id")
public class NoSuchIdException extends Exception{
}
