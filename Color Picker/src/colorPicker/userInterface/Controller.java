package colorPicker.userInterface;

import static java.lang.Integer.toHexString;

import java.awt.AWTException;
import java.awt.Color;
import java.awt.KeyEventDispatcher;
import java.awt.MouseInfo;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.net.URL;
import java.util.ResourceBundle;

import javax.swing.ImageIcon;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXColorPicker;

import colorPicker.library.RobotControler;
import javafx.embed.swing.SwingFXUtils;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;
import javafx.stage.Stage;

public class Controller implements Initializable
{
	@FXML private JFXButton buttonClose;
	@FXML private JFXButton buttonHide;
	@FXML private JFXColorPicker colorPickerObject;
	@FXML private AnchorPane pane;
	@FXML private Label	rgbLabel;
	@FXML private Label	hexLabel;
	@FXML private Label labelChooseColor;
	@FXML private Label labelRgb;
	@FXML private Label labelHex;
	@FXML private JFXButton buttonSelect;
	@FXML private ImageView image;
	private RobotControler robot;
	private String color;
	private Color colorSelected;
	
	/**
	 * Method set color to the labels
	 * Author: Danny Xie Li
	 * Description: The next method will change the color of the text of the labels.
	 * Last modification:29/11/17
	 * @param pColor
	 */
	public void setColorText(String pColor)
	{
		labelChooseColor.setStyle("-fx-text-fill:" + pColor);		
		labelRgb.setStyle("-fx-text-fill:" + pColor);
		labelHex.setStyle("-fx-text-fill:" + pColor);
		rgbLabel.setStyle("-fx-text-fill:" + pColor);
		hexLabel.setStyle("-fx-text-fill:" + pColor);
	}
	
	/**
	 * Method change color to the panel
	 * Author: Danny Xie Li
	 * Description: The next method change the color of the panel
	 * Last modification: 29/11/17
	 */
	public void changeColor()
	{
		pane.setStyle("-fx-background-color: #" +color);// color);	
		hexLabel.setText("#"+color);
		rgbLabel.setText(String.valueOf(colorSelected.getRed()) +", "+ String.valueOf(colorSelected.getGreen())+ ", "+String.valueOf(colorSelected.getBlue()));
	}
	
	/**
	 * Method set image to the pane
	 * Author: Danny Xie Li
	 * Description: The next method set image to the pane
	 * Last modification: 29/11/17
	 * @param pImage
	 */
	public void setImagePane(javafx.scene.image.Image pImage)
	{
		image.setImage(pImage);
		setColorSelected();
	}
	
	/**
	 * Method set color selected
	 * Author: Danny Xie Li
	 * Description: The next method set color selected.
	 * Last modification: 29/11/17
	 */
	public void setColorSelected()
	{
		Color colorSelected = robot.getColor();
		this.colorSelected = colorSelected;
        color = toHexString(colorSelected.getRGB()).substring(2);
		System.out.println(color);
		changeColor();
	}
	
	/**
	 * Method close button
	 * Author: Danny Xie Li
	 * Description: The next method close the window.
	 * Last modification: 29/11/17
	 */
	public void closeAction()
	{
		Stage stage = (Stage) buttonClose.getScene().getWindow();
		stage.close();
	}
	
	/**
	 * Method close button
	 * Author: Danny Xie Li
	 * Description: The next method hide the window.
	 * Last modification: 29/11/17
	 */
	public void hideAction()
	{
		Stage stage = (Stage) buttonHide.getScene().getWindow();
		stage.setIconified(true);
	}
	
	/**
	 * Method open manual
	 * Author: Danny Xie Li
	 * Description: The next method open the manual.
	 * Last modification: 29/11/17
	 */
	public void openManual()
	{
		robot.openManual();
	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1)
	{
		rgbLabel.setText("");
		hexLabel.setText("");
		try {
			robot = new RobotControler();
		} catch (AWTException e1) {
			e1.printStackTrace();
		}
		buttonSelect.setOnMouseDragged(e->{
				robot.mouseDragged();
				setImagePane(robot.captureScreen());
            }
		);
	}
}