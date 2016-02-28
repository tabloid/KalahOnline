package GVA.Kalah.Component;

import GVA.Kalah.Exception.CantAddMorePlayerException;
import GVA.Kalah.Exception.EmptyPitException;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 * Created by v.herasymenko on 2/24/2016.
 */
public class KalahGame {
    private final Map<String, PlayerBoard> map = new HashMap<>(2);
    private long lastTimeModified = System.currentTimeMillis();
    private String whoseMove;

    //first move has player who created game
    public void addPlayer(String playerName) throws CantAddMorePlayerException{
        if (map.size() < 2){
            if (map.size() == 0)
                whoseMove = playerName;
            PlayerBoard playerBoard = new PlayerBoard();
            map.put(playerName, playerBoard);
        }
        else throw new CantAddMorePlayerException();
    }

    public boolean containsTwoPlayers(){
        return map.size() == 2;
    }

    public boolean containsPlayer(String player){
        return map.containsKey(player);
    }

    private PlayerBoard getOpponentPlayerBoard(String player) {
        return map.get(getOpponent(player));
    }

    private String getOpponent(String player){
        String opponent = null;
        for (String str : map.keySet()) {
            if (str.equals(player))
                continue;
            opponent = str;
        }
        return opponent;
    }

    public void makeMove(int pit, String player) throws EmptyPitException {
        PlayerBoard playerBoard = map.get(player);
        if (playerBoard.pitIsEmpty(pit))
            throw new EmptyPitException();
        PlayerBoard opponentBoard = getOpponentPlayerBoard(player);
        int stones = playerBoard.pickStonesFromPit(pit);
        int start = pit + 1;
        while (stones > 0) {
            stones = playerBoard.putStonesInPits(start, stones);

            //if player move stone to his empty pit, put opponents stones from opposite pit
            if (playerBoard.isLastStoneCameToEmptyPit()) {
                int lastPit = playerBoard.getLastPitPosition();
                int opponentPit = (playerBoard.getPitsNumber() - 1) - lastPit;
                System.out.println("LastPit " + lastPit);
                System.out.println("opponentPit " + opponentPit);
                int opponentStones = opponentBoard.pickStonesFromPit(opponentPit);
                playerBoard.addStonesInKalah(opponentStones);
            }

            //additional move when last stone came to kalah
            if (stones == 1) {
                playerBoard.pullStoneInKalah(stones);
                whoseMove = player;
                return;
            }

            stones = playerBoard.pullStoneInKalah(stones);
            stones = opponentBoard.putStonesInPits(0, stones);
            start = 0;
        }
        whoseMove = getOpponent(player);
    }

    public boolean isFinished() {
        boolean flag = false;
        Iterator<Map.Entry<String, PlayerBoard>> iterator = map.entrySet().iterator();
        while (iterator.hasNext()) {
            Map.Entry<String, PlayerBoard> entry = iterator.next();
            if (entry.getValue().allPitsAreEmpty()) {
                flag = true;
                break;
            }
        }
        return flag;
    }


    public void setLastTimeModified(long lastTimeModified) {
        this.lastTimeModified = lastTimeModified;
    }

    public BoardLayout getBoardLayout(String player) {
        PlayerBoard playerBoard = map.get(player);
        PlayerBoard opponentBoard = getOpponentPlayerBoard(player);
        String opponent = getOpponent(player);
        BoardLayout boardLayout = new BoardLayout();
        boardLayout.setPlayerInfo(player, playerBoard);
        boardLayout.setOpponentInfo(opponent, opponentBoard);
        boardLayout.setWhoseMove(whoseMove);
        return boardLayout;
    }

    public long getLastTimeModified() {
        return lastTimeModified;
    }

    public String getWhoseMove() {
        return whoseMove;
    }
}
