import java.util.ArrayList;
import java.util.concurrent.atomic.AtomicReference;

public class Human implements Player{
    ArrayList<Piece> pieces;
    public Human(Piece piece){
        this.pieces.add(piece);
    }

    @Override
    public Piece movePiece(Piece piece, MoveLogic rules) {
        AtomicReference<Piece> result = null;
        piece.setOnMouseReleased(e->{
            int horNew = rules.retrieveLoc(piece.getLayoutX());
            int verNew = rules.retrieveLoc(piece.getLayoutY());
            Move move = rules.testMove(piece, horNew, verNew);
            result.set(rules.makeMove(piece, move, horNew, verNew));
        });
        return result.get();
    }

    @Override
    public void setKing() {

    }
}
