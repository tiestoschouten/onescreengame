//internal java classes import
import javafx.application.*;

import javafx.stage.*;

import javafx.scene.*;
import javafx.scene.canvas.*;
import javafx.scene.image.*;
import javafx.scene.input.*;
import javafx.scene.layout.*;

import javafx.event.*;


// CUSTOM CLASSES IMPORT:
import gamesource.main.*;



public class MainApplication extends Application
{

	public static final int SCREEN_WIDTH = 800;
	public static final int SCREEN_HEIGHT = 600;


	private Pane _myPane = new Pane();
	private Scene _myScene;
	private Canvas _myCanvas;

	private GraphicsContext _myContext;
	private CustomAnimationTimer _animationTimer;

	private GameManager _gameManager;


	public static void main(String[] args)
	{

		launch(args);

	}


	public void start(Stage primaryStage)
	{

		_myScene = new Scene(_myPane);
		_myCanvas = new Canvas(SCREEN_WIDTH, SCREEN_HEIGHT);
		_myContext = _myCanvas.getGraphicsContext2D();

		_myPane.getChildren().add(_myCanvas);


		// REGISTER MOUSE & KEYBOARD INPUT:
		_myPane.addEventHandler(MouseEvent.MOUSE_PRESSED, Input.OnMousePressed);
		_myPane.addEventHandler(MouseEvent.MOUSE_RELEASED, Input.OnMouseReleased);
		_myPane.addEventHandler(MouseEvent.MOUSE_MOVED, Input.OnMouseMove);
		_myPane.addEventHandler(MouseEvent.MOUSE_DRAGGED, Input.OnMouseMove);
		_myPane.addEventHandler(KeyEvent.KEY_PRESSED, Input.OnKeyPressed);
		_myPane.addEventHandler(KeyEvent.KEY_RELEASED, Input.OnKeyReleased);
		_myPane.requestFocus();


		_gameManager = new GameManager(_myPane);


		primaryStage.setScene(_myScene);
		primaryStage.setTitle("EXAMPLE GAME");
		primaryStage.setResizable(false);
		primaryStage.sizeToScene();
		primaryStage.show();


		_animationTimer = new CustomAnimationTimer(this::GameLoop);
		_animationTimer.start();

	}



	public void GameLoop(long currentNanoTime)
	{
		// UPDATE THE GAME STATE:
		_gameManager.Update();


		// RESET THE CURRENT PIXEL BUFFER, THEN DRAW THE CURRENT STATE OF THE GAME:
		_myContext.clearRect(0, 0, SCREEN_WIDTH, SCREEN_WIDTH);
		_gameManager.Draw();
		//_gameManager.DrawGUI();


		// CLEAR RELEASE INPUT FOR NEXT ITERATION:
		Input.KEYS_RELEASED.clear();
	}




}
