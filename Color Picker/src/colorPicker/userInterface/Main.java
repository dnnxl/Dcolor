package colorPicker.userInterface;

import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class Main extends Application
{
	/**
	 * Attributes of the window
	 */
	private double xOffset;
	private double yOffset;
	
	/**
	 * Method start the application.
	 * Author: Danny Xie Li
	 * Description: The next method open the window.
	 * Last modification: 01/11/17
	 */
	@Override
	public void start(Stage primaryStage) 
	{
		try 
		{
			Parent root = FXMLLoader.load(getClass().getResource("colorPicker.fxml"));//USER_INTERFACE));
		    Scene scene = new Scene(root, 616, 438);
		    root.setOnMousePressed(new EventHandler<MouseEvent>() 
		    {
	            @Override
	            public void handle(MouseEvent event) 
	            {
	                double xOffset = event.getSceneX();
	                double yOffset = event.getSceneY();
	            }
	        });
		    primaryStage.initStyle(StageStyle.UNDECORATED);
			primaryStage.setScene(scene);
			primaryStage.show();
			primaryStage.setIconified(true);
			primaryStage.setResizable(false);
			root.setOnMouseDragged(new EventHandler<MouseEvent>() 
			{
	            @Override
	            public void handle(MouseEvent event) 
	            {
	            	primaryStage.setX(event.getScreenX() - xOffset);
	            	primaryStage.setY(event.getScreenY() - yOffset);
	            }
	        });
			primaryStage.getIcons().add(new Image("/colorPicker/image/rgb.png"));
		} catch(Exception e)
		{
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args)
	{
		launch(args);
	}
}