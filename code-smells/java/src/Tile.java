
public class Tile //Data Class
{
    public Tile() {
    }

    public Tile(int x, int y, char symbol) {
        X = x;
        Y = y;
        Symbol = symbol;
    }

    public int  X;
    public int  Y;
    public char Symbol;

    boolean isOcuppied() {
        return Symbol != ' ';
    }

    boolean hasSamePlayer(Tile other) {
        return other.Symbol == Symbol;
    }

    boolean hasSamePosition(Tile other) {
        return other.X == X && other.Y == Y;
    }
}
