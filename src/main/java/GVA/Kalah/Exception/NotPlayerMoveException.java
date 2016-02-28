package GVA.Kalah.Exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Created by Vsevolod on 28.02.2016.
 */
@ResponseStatus(value= HttpStatus.NOT_ACCEPTABLE, reason="wait for your turn")
public class NotPlayerMoveException extends Exception{
}
