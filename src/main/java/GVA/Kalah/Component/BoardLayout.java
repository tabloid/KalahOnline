package GVA.Kalah.Component;

/**
 * Created by v.herasymenko on 2/26/2016.
 */
// json presentation object
public class BoardLayout {
    public String whoseMove;
    public String player;
    public PlayerBoard playerBoard;
    public String opponent;
    public PlayerBoard opponentBoard;

    public void setPlayerInfo(String player, PlayerBoard playerBoard) {
        this.player = player;
        this.playerBoard = playerBoard;
    }

    public void setOpponentInfo(String opponent, PlayerBoard opponentBoard) {
        this.opponent = opponent;
        this.opponentBoard = opponentBoard;
    }

    public void setWhoseMove(String whoseMove) {
        this.whoseMove = whoseMove;
    }

}



