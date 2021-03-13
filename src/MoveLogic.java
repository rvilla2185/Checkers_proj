public class MoveLogic {

    private Space[][] board;
    public MoveLogic(Space[][] board){
        this.board = board;
    }
    public Move testMove(Piece piece, int newCol, int newRow){

        /* If the space is occupied or the space in not a on black space, do not move */
        if(board[newCol][newRow].hasPiece() || (newRow + newCol) % 2 == 0 ||
        newRow > 7 || newCol > 7){
            return new Move(MoveType.NONE);
        }
        int init_x = retrieveLoc(piece.getPrevCol());
        int init_y = retrieveLoc(piece.getPrevRow());
        int nextCol = init_x + (newCol - init_x) / 2;
        int nextRow = init_y + (newRow - init_y) / 2;

        if(!piece.isKing()) {
            /* Check if the piece is being kinged */
            if ((piece.getType() == DirectionType.BLUE && newRow == 7) ||
                    (piece.getType() == DirectionType.GREEN && newRow == 0)) {
                piece.setKing();
                piece.setColorScheme();
            }
            if (Math.abs(newCol - init_x) == 1 && (newRow - init_y) == piece.getType().direction) {
                return new Move(MoveType.MOVE);
            } else if ((Math.abs(newCol - init_x) == 2 && (newRow - init_y) == piece.getType().direction * 2)) {
                if (board[nextCol][nextRow].hasPiece() && board[nextCol][nextRow].getPiece().getType() != piece.getType()) {
                    return new Move(MoveType.JUMP, board[nextCol][nextRow].getPiece());
                }
            }
        }
        else {
             if(board[nextCol][nextRow].hasPiece() && board[nextCol][nextRow].getPiece().getType() != piece.getType()) {
                return new Move(MoveType.JUMP, board[nextCol][nextRow].getPiece());
            } else if ((Math.abs(newCol - init_x) >= 1)){
                return new Move(MoveType.MOVE, board[nextCol][nextRow].getPiece());
            }
        }
        return new Move(MoveType.NONE);
    }
    public int retrieveLoc(double pixel){
        return (int)(pixel + Checkers.SPACE_SZ / 2) / Checkers.SPACE_SZ;
    }
    public Piece makeMove(Piece piece, Move move, int x, int y){
        Piece result = null;
        int initHor = retrieveLoc(piece.getPrevCol());
        int initVer = retrieveLoc(piece.getPrevRow());

        /* strategy deign pattern based on the enum of MoveType */
        switch(move.getType()){
            case NONE:
                piece.revertMove();
                break;
            case MOVE:
                piece.move(x, y);
                board[initHor][initVer].setPiece(null);
                board[x][y].setPiece(piece);
                break;
            case JUMP:
                piece.move(x, y);
                board[initHor][initVer].setPiece(null);
                board[x][y].setPiece(piece);

                Piece notUrPiece = move.getPiece();
                board[retrieveLoc(notUrPiece.getPrevCol())][retrieveLoc(notUrPiece.getPrevRow())].setPiece(null);
                result = notUrPiece;
                break;
        }
        return result;

    }
}
