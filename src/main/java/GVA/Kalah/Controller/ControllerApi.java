package GVA.Kalah.Controller;

import GVA.Kalah.Component.BoardLayout;
import GVA.Kalah.Component.KalahGame;
import GVA.Kalah.Component.TimeCash;
import GVA.Kalah.Exception.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Created by v.herasymenko on 2/24/2016.
 */
@RestController
@RequestMapping(value = "api")
public class ControllerApi {
    @Autowired
    TimeCash cash;

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public List<Long> getAvailableGames() {
        return cash.getAvailableGames();
    }

    @RequestMapping(value = "/", method = RequestMethod.POST)
    public long createGame() {
        KalahGame kalahGame = new KalahGame();
        long id = cash.addGame(kalahGame);
        return id;
    }

    public void idCheck(long id) throws NoSuchIdException {
        if (!cash.containsGame(id))
            throw new NoSuchIdException();
    }

    public void playerCheck(String player, KalahGame kalahGame) throws WrongPlayerNameException {
        if (!kalahGame.containsPlayer(player))
            throw new WrongPlayerNameException();
    }

    public void whoseMoveCheck(String player, KalahGame kalahGame) throws NotPlayerMoveException{
        if (! kalahGame.getWhoseMove().equals(player))
            throw new NotPlayerMoveException();
    }

    @RequestMapping(value = "/{id}/{player}", method = RequestMethod.GET)
    public BoardLayout getBoardLayout(@PathVariable long id, @PathVariable String player)throws NoSuchIdException{
        idCheck(id);
        return cash.getGame(id).getBoardLayout(player);
    }

    @RequestMapping(value = "/{id}/{player}", method = RequestMethod.POST)
    public boolean registerPlayer(@PathVariable long id, @PathVariable String player)
            throws NoSuchIdException, CantAddMorePlayerException {
        idCheck(id);
        KalahGame kalahGame = cash.getGame(id);
        kalahGame.addPlayer(player);
        //kalahGame.addPlayer("ai");
        return kalahGame.containsTwoPlayers();
    }

    @RequestMapping(value = "/{id}/{player}/{pit}", method = RequestMethod.POST)
    public boolean makeMove(@PathVariable long id,
                           @PathVariable String player,
                           @PathVariable int pit)
            throws NoSuchIdException, WrongPlayerNameException, EmptyPitException,
            NotPlayerMoveException {
        idCheck(id);
        KalahGame kalahGame = cash.getGame(id);
        playerCheck(player, kalahGame);
        whoseMoveCheck(player, kalahGame);
        kalahGame.makeMove(pit, player);
        return kalahGame.getWhoseMove().equals(player);
    }
}
