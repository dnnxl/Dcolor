package colorPicker.library;

import java.awt.AWTException;
import java.awt.Color;
import java.awt.Desktop;
import java.awt.Image;
import java.awt.MouseInfo;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.Robot;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.awt.image.BufferedImage;
import java.awt.image.ImagingOpException;
import java.io.File;
import java.io.IOException;

import javafx.embed.swing.SwingFXUtils;
import javafx.scene.image.WritableImage;

import colorPicker.library.*;

public class RobotControler implements IConstant 
{
	private Robot robot;
    private int centralX, centralY;
    private int screenSize = 1000 / 20;
    private int screenRadius = screenSize / 2;
	private Color color;

	public RobotControler() throws AWTException
	{
		robot = new Robot();
	}
	
	public Color getColor()
	{
		return color;
	}
	
	/**
	 * Method open manual
	 * Author: Danny Xie Li
	 * Description: The next method open the manual.
	 * Last modification: 29/11/17
	 */
	public void openManual()
	{
		try
		{
			
            File myFile = new File(getClass().getResource("/Manual DColor.pdf").toURI());
			Desktop.getDesktop().open(myFile);
		}
		catch (Exception e)
		{
			System.out.println("Catch");
		}
	}
	
   /**
  	 * Method mouse dragged  
  	 * Author: Danny Xie Li
  	 * Description: The next mouse dragged  .
  	 * Last modification: 29/11/17
  	 */
	public void mouseDragged() 
	{
        update();
        captureScreen();
    }

   /**
  	 * Method update  
  	 * Author: Danny Xie Li
  	 * Description: The next method update.
  	 * Last modification: 29/11/17
  	 */
    private void update() 
    {
        final Point centralPoint = MouseInfo.getPointerInfo().getLocation();
        centralX = (int) centralPoint.getX();
        centralY = (int) centralPoint.getY();
        System.out.println("Update");
        getPixelColor();
    }
    
    /**
  	 * Method capture screen  
  	 * Author: Danny Xie Li
  	 * Description: The next method capture screen with a robot.
  	 * Last modification: 29/11/17
  	 */
    public javafx.scene.image.Image captureScreen() throws ImagingOpException {
    	
        final Rectangle rectangle = new Rectangle(centralX - screenRadius, centralY - screenRadius, 293, 368);
        BufferedImage capture = robot.createScreenCapture(rectangle);
        WritableImage image = SwingFXUtils.toFXImage(capture, null);
		return image;
    }

    /**
	 * Method get pixel color 
	 * Author: Danny Xie Li
	 * Description: The next method get pixel color.
	 * Last modification: 29/11/17
	 */
	public Color getPixelColor()
	{
		int x = (int) MouseInfo.getPointerInfo().getLocation().getX();
		int y = (int) MouseInfo.getPointerInfo().getLocation().getY();
		Color colorObtenein = robot.getPixelColor(x, y);
		color = colorObtenein;
		return colorObtenein;
	}
	
	/**
	 * Method robot delay
	 * Author: Danny Xie Li
	 * Description: The next method delay the robot.
	 * Last modification: 29/11/17
	 */
	public void delayRobot()
	{
		robot.delay(5000);
	}
	
	public static void main(String[]args) throws AWTException
	{
		RobotControler o = new RobotControler();
		o.openManual();
	}
}