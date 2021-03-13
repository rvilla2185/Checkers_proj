public class Move {
    private MoveType move;
    private Piece piece;

    public MoveType getType(){
        return move;
    }
    public Piece getPiece(){
        return piece;
    }
    public Move(MoveType move){
        this(move, null);
    }
    public Move(MoveType move, Piece piece){
        this.move = move;
        this.piece = piece;
    }

}
