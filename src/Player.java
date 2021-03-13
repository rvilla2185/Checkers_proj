public interface Player {
    Piece movePiece(Piece piece, MoveLogic rules);
    void setKing();
}
