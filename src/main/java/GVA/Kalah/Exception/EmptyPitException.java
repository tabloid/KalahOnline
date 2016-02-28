package GVA.Kalah.Exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Created by v.herasymenko on 2/25/2016.
 */
@ResponseStatus(value= HttpStatus.NOT_ACCEPTABLE, reason="can't move on empty pit")
public class EmptyPitException extends Exception {
}
