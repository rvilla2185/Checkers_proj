
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Ellipse;


public class Piece extends StackPane {
    private DirectionType piece;
    private boolean king = false;

    private double newRow,newCol;
    private double prevRow, prevCol;

    public DirectionType getType(){
        return piece;
    }

    public double getPrevRow(){
        return prevRow;
    }
    public double getPrevCol(){
        return prevCol;
    }
    /* Singleton Pattern */
    public Piece(DirectionType piece, int x, int y){
        createPiece(piece, x, y);
    }
/* Will create the piece and set the move as need from the board */
    private void createPiece (DirectionType piece, int x, int y) {
        this.piece = piece;
        move(x, y);
        setColorScheme();
        setOnMousePressed(e -> {
            newCol = e.getSceneX();
            newRow = e.getSceneY();
        });
        setOnMouseDragged(e -> {
            relocate(e.getSceneX() - newCol+prevCol, e.getSceneY() - newRow+prevRow);
        });
    }

    /* Sets the color scheme based on the enum type and if the piece is a king */
    public void setColorScheme(){
        Ellipse back_ground = new Ellipse(Checkers.SPACE_SZ * 0.300, Checkers.SPACE_SZ * 0.25);
        back_ground.setFill(Color.BLACK);

        back_ground.setStroke(Color.BLACK);
        back_ground.setStrokeWidth(Checkers.SPACE_SZ * .025);

        back_ground.setTranslateX((Checkers.SPACE_SZ - Checkers.SPACE_SZ * .3 * 2) / 2);
        back_ground.setTranslateY((Checkers.SPACE_SZ - Checkers.SPACE_SZ * .25) / 2);

        Ellipse ellipse = new Ellipse(Checkers.SPACE_SZ * .3, Checkers.SPACE_SZ * .25);
        if (king) {
            ellipse.setFill(piece == DirectionType.BLUE ? Color.RED : Color.YELLOW);
        }else{
            ellipse.setFill(piece == DirectionType.BLUE ? Color.BLUE : Color.GREEN);
        }
        ellipse.setStroke(Color.BLACK);
        ellipse.setStrokeWidth(Checkers.SPACE_SZ * .025);

        ellipse.setTranslateX((Checkers.SPACE_SZ - Checkers.SPACE_SZ * .3 * 2) / 2);
        ellipse.setTranslateY((Checkers.SPACE_SZ - Checkers.SPACE_SZ * .25) / 2);

        getChildren().addAll(back_ground, ellipse);

    }
    public void setKing(){
        this.king = true;
    }
    public boolean isKing(){
        return king;
    }
    public void move(int hor, int ver){
        prevCol = hor * Checkers.SPACE_SZ;
        prevRow = ver * Checkers.SPACE_SZ;
        relocate(prevCol, prevRow);
    }
    public void revertMove(){
        relocate(prevCol, prevRow);
    }
}
