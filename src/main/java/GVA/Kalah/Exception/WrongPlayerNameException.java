package GVA.Kalah.Exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Created by Vsevolod on 26.02.2016.
 */
@ResponseStatus(value= HttpStatus.NOT_ACCEPTABLE, reason="wrong player name")
public class WrongPlayerNameException extends Exception {
}
