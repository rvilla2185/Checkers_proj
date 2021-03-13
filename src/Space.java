import javafx.scene.shape.Rectangle;
import javafx.scene.paint.Color;

public class Space extends Rectangle {

    private Piece piece;

    public boolean hasPiece() {
        return piece != null;
    }

    public Piece getPiece(){
        return piece;
    }

    public void setPiece(Piece piece){
        this.piece = piece;
    }

    public Space(boolean red, int row, int col){
        setWidth(Checkers.SPACE_SZ);
        setHeight(Checkers.SPACE_SZ);

        relocate(col * Checkers.SPACE_SZ, row * Checkers.SPACE_SZ);
        setFill(red ? Color.RED : Color.BLACK);
    }
}
