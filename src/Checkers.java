import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;


public class Checkers extends Application {

    private Group spaceGroup = new Group();
    private Group pieceGroup =  new Group();
    private Label message;  // Label for displaying messages to the user.
    Button newGameButton;
    private MoveLogic rules;
    private Player player1;
    private Player player2;
    private int count;

    public static final int SPACE_SZ = 100;
    public static final int X = 8;
    public static final int Y = 8;

    private Space[][] board = new Space[X][Y];

    private Parent createContent() {
        newGameButton.relocate(X*SPACE_SZ+20, 20);
        message.relocate(200, 20 + Y*SPACE_SZ);
        newGameButton.setManaged(false);
        newGameButton.resize(100,30);
        Pane root = new Pane();
        root.setPrefSize(125 + X*SPACE_SZ, 75 + Y*SPACE_SZ );
        root.getChildren().addAll(spaceGroup, pieceGroup, newGameButton, message);
        newBoard();
        return root;
    }

    public void start(Stage primaryStage){

        /* Create the label that may show messages. */
        message = new Label("Checkers");
        message.setTextFill( Color.RED ); // Light green.
        message.setFont( Font.font(null, FontWeight.BOLD, 18) );



        newGameButton = new Button("New Game");
        newGameButton.setOnAction( e -> doNewGame() );

        Scene scene = new Scene(createContent());
        primaryStage.setTitle("Checkers");
        primaryStage.setScene(scene);
        primaryStage.show();
        /*
        player1 = new Player1();
        player2 = new Player1();
        count = 0;
         */
    }
    private Piece pieceLoc(DirectionType directionType, int x, int y){
        Piece piece = new Piece(directionType, x, y);
        rules = new MoveLogic(board);
        final Piece[] result = {null};
/*
        if(count % 2 == 0){
            result[0] = player1.movePiece(piece, rules);
            count++;
        }else {
            result[0] = player2.movePiece(piece, rules);
            count=0;
        }
*/
        piece.setOnMouseReleased(e->{
            int horNew = rules.retrieveLoc(piece.getLayoutX());
            int verNew = rules.retrieveLoc(piece.getLayoutY());
            Move move = rules.testMove(piece, horNew, verNew);
            result[0] = rules.makeMove(piece, move, horNew, verNew);
            if(result[0] != null) {
                pieceGroup.getChildren().remove(result[0]);
            }
        });
        return piece;
    }
    void doNewGame() {
        newBoard();
    }
    void newBoard(){
        for(int row = 0; row < Y; row++){
            for(int col = 0; col < X; col++){
                Space space = new Space((row+col) % 2 == 0, col, row);
                board[col][row] = space;

                spaceGroup.getChildren().add(space);

                Piece piece = null;
                if((row < 3) && (row + col) % 2 != 0){
                    piece = pieceLoc(DirectionType.BLUE,col,row );
                }
                if((row > 4) && (row + col) % 2 != 0){
                    piece = pieceLoc(DirectionType.GREEN,col,row);
                }
                if (piece != null){
                    space.setPiece(piece);
                    pieceGroup.getChildren().add(piece);
                }
            }
        }
    }

    public static void run(String[] args){ launch(args);}
}
