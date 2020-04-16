public class Game {
    private char _lastSymbol = ' ';
    Board _board = new Board();

    //Long Parameter List
    //Long Method => extract methods
    //Primitive Obsession => use Tile instead of primitives
    //Switch Statement => remove `else`s & extract methods to guards
    //Comments => extract methods
    //Shotgun Surgery
    //Open Closed
    public void Play(char symbol, int x, int y) throws Exception {
        Tile tile = new Tile(x, y, symbol);

        guardFirstMove(tile);
        guardPlayerTurn(tile);
        guardTileIsOccupied(tile);

        updateGameState(tile);
    }

    private void updateGameState(Tile tile) {
        _lastSymbol = tile.Symbol;
        _board.changeTileAt(tile);
    }

    private void guardTileIsOccupied(Tile tile) throws Exception {
        if (_board.isTileOcuppied(tile)) { //Message Chain & Feature Envy => move conditional to Board
            throw new Exception("Invalid position");
        }
    }

    private void guardPlayerTurn(Tile tile) throws Exception {
        if (tile.Symbol == _lastSymbol) {
            throw new Exception("Invalid next player");
        }
    }

    private void guardFirstMove(Tile tile) throws Exception {
        if (_lastSymbol == ' ') {
            //if player is X
            if (tile.Symbol == 'O') {
                throw new Exception("Invalid first player");
            }
        }
    }
}

