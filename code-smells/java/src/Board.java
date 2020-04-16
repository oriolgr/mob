import java.util.ArrayList;
import java.util.List;

//Shotgun Surgery
public class Board
{
    private List<Tile> _plays = new ArrayList<>();

    public Board()
    {
        for (int x = 0; x < 3; x++)
        {
            for (int y = 0; y < 3; y++)
            {
                Tile tile = new Tile(x, y, ' '); //Inappropiate Intimacy => create constructor on Tile
                _plays.add(tile);
            }
        }
    }

    public Tile TileAt(int x, int y)
    {
        for (Tile t : _plays) {
            if (t.X == x && t.Y == y){
                return t;
            }
        }
        return null;
    }

    public void changeTileAt(Tile tile)
    {
        //Dead code => removed
        TileAt(tile.X, tile.Y).Symbol = tile.Symbol;
    }

    //Message Chain => Move method to Board & later to Tile
    //Inappropiate Intimacy => Move method to Board & later to Tile
    //Long Method => Extract common parts to a method
    //Duplicated Code => Extract common parts to a method
    //Comments => Extract common parts to a method & use its name instead of the comment
    //Shotgun Surgery => Move method to Board & later to Tile
    //Open Closed => Move method to Board & later to Tile
    //SRP => Move method to Board & later to Tile
    public char Winner() {
        for (int x = 0; x < 3; x++) {
            if (winningPlayer(x)) {
                return TileAt(x, 0).Symbol;
            }
        }

        return ' '; //Primitive Obsession
    }

    private boolean winningPlayer(int x) {
        if (isRowOccupied(x)) {
            return isRowFullWithSamePlayer(x);
        }
        return false;
    }

    private boolean isRowFullWithSamePlayer(int x) {
        return TileAt(x, 1).hasSamePlayer(TileAt(x, 0)) && TileAt(x, 1).hasSamePlayer(TileAt(x, 2));
    }

    private boolean isRowOccupied(int x) {
        return isTileOcuppied(x, 0) && isTileOcuppied(x, 1) && isTileOcuppied(x, 2);
    }

    boolean isTileOcuppied(int x, int y) {
        return TileAt(x, y).isOcuppied();
    }

    boolean isTileOcuppied(Tile tile) {
        return TileAt(tile).isOcuppied();
    }

    public Tile TileAt(Tile tile)
    {
        for (Tile t : _plays) {
            if (tile.hasSamePosition(t)){
                return t;
            }
        }
        return null;
    }
}
