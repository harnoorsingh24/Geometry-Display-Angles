/*	Triangle.java
 * The main purpose for this class is to create a GUI that is a triangle, which displays angles
 * as the user moves the vertices around. The user can take any of the vertices, and move them
 * around anywhere on the screen. While they move one of the vertices, all the other vertices are 
 * updates, and dynamically display their angle to match the altered shape of the triangle. The
 * angles are displayed out two decimal places, and remain 10 pixels above the vertices. Also,
 * towards the bottom of the program there is a method called updateAngles. The main purpose
 * of this method, is to calculate the angles of each of the three vertices, and then make sure,
 * that they are being updated as the user moves one of the vertices. Basically making sure that 
 * all three of the angles are being updated, not just one. 
 * 
 * Also there are some extra added things. One is that when the user hovers over one of the vertices,
 * that specific vertice will turn black, so that the user knows that is the vertice that they are going
 * to move. Two, while the user drags the vertice, the vertice will start to strobe between the original color
 * and the color black, acting as a flashing type of visual. Three, the color of the lines are determined by the 
 * vertices they connect. So line one connects vertices, red and green, and the color of line 1 is thus brown, because
 * red and green make brown. This process is carried through for each of the lines. Those are some of the things I did,
 * to make my project stand out. 
 * 
 * 
 * 	Harnoor Singh
 * 	EECS 1510 - Intro to Object Oriented
 * 	Project 3 - Geometry
 * 	Dr. Joseph Hobbs
 * 
 */



import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.scene.paint.Color;
import java.text.DecimalFormat;
import javafx.scene.text.Text; 

public class Triangle extends Application 
{
	//Create global angle variables, so that they can be updated with the movement of the vertices. 
	static double angleA;
	static double angleB;
	static double angleC;
	//Offset is created to display the angles, so that they are visible, and not in the center of the circle, and hiding numbers.
	static int offset = -11;
	 	 
	@Override //Override the Start Method 
	public void start(Stage stage) 
	{
		//Create a new Layout Pane 
		Pane pane = new Pane();
		
		//Create the Objects/Variables. Circles, Lines, Texts. 
		Circle circle1 = new Circle(347, 100, 10);//Create three vertices/end-points with a radius of 10, and with initial starting x and y coordinate points. 
        Circle circle2 = new Circle(200, 300, 10);
		Circle circle3 = new Circle(500, 300, 10);
		Line line1 = new Line();//Create three lines, to connect the circles together and form a triangle.
		Line line2 = new Line();
		Line line3 = new Line();
		Text angle1 = new Text();//Create three new texts which will be used to display angles. 
		Text angle2 = new Text();
		Text angle3 = new Text();

		//This is an object that changes the format of my double into a string, and makes it so that it is only two decimal places out. 
		//Basically like the toString method, but with constraints, and allowing it to be two decimal places. 
		DecimalFormat df = new DecimalFormat("#.00");


		//The following lines, up till circle 1, show the initial angles of each of the vertices to the user. 
		updateAngles(circle1.getCenterX(), circle1.getCenterY(), circle2.getCenterX(), 
				circle2.getCenterY(), circle3.getCenterX(), circle3.getCenterY());
		
		//df.format converts double to string, and only displays two decimal places. 
		angle1.setText(df.format(angleA));
		angle2.setText(df.format(angleB));
		angle3.setText(df.format(angleC));
		
		angle1.setX(circle1.getCenterX() + offset); 
		angle1.setY(circle1.getCenterY() + offset);
		
		angle2.setX(circle2.getCenterX() + offset);
		angle2.setY(circle2.getCenterY() + offset);
		
		angle3.setX(circle3.getCenterX() + offset);
		angle3.setY(circle3.getCenterY() + offset);

		//Set the width of the lines. 
//		line1.setStrokeWidth(2);;
//		line2.setStrokeWidth(2);
//		line3.setStrokeWidth(2);
//		
		/*The color of the lines was determined by looking at the two vertices the line connects and what color they can 
		 * make. So line 1 connects vertices colors red and green, which make the color brown. 
		 * That is the same way I determined what other colors the lines should be. 
		 */
		line1.setStroke(Color.BROWN);
		line2.setStroke(Color.CYAN);
		line3.setStroke(Color.MAGENTA);
		
		//Circle 1
		circle1.setStroke(Color.BLACK);//set circle stroke/outline color
		circle1.setFill(Color.RED);//set circle fill color 
		
		
		//Allow circle to be dragged and dropped. 
		circle1.setOnMouseDragged(e -> 
		{ 
			
			circle1.setCenterX(e.getX());
			circle1.setCenterY(e.getY());
			
			//Call the updateAngles method, to just update the angles as the user moves the vertices around. 
			updateAngles(circle1.getCenterX(), circle1.getCenterY(), circle2.getCenterX(), 
					circle2.getCenterY(), circle3.getCenterX(), circle3.getCenterY());
			
			angle1.setText(df.format(angleA));
			angle2.setText(df.format(angleB));
			angle3.setText(df.format(angleC));
			
			//Relocate text items, so that they are visible. 
			angle1.setX(circle1.getCenterX() + offset);
			angle1.setY(circle1.getCenterY() + offset);	
		});
		
        //Connect line3's end-point with the center of circle 1
        line3.endXProperty().bind(circle1.centerXProperty());
        line3.endYProperty().bind(circle1.centerYProperty());
		
		//Connect line1 starting point to the center of circle 1 
		line1.startXProperty().bind(circle1.centerXProperty());
        line1.startYProperty().bind(circle1.centerYProperty());
		
        
        
        
        
		//Circle 2
        circle2.setStroke(Color.BLACK);
        circle2.setFill(Color.GREEN);

        circle2.setOnMouseDragged(e -> 
        {
            circle2.setCenterX(e.getX());
            circle2.setCenterY(e.getY());
            
			updateAngles(circle1.getCenterX(), circle1.getCenterY(), circle2.getCenterX(), 
					circle2.getCenterY(), circle3.getCenterX(), circle3.getCenterY());
			
			angle1.setText(df.format(angleA));
			angle2.setText(df.format(angleB));
			angle3.setText(df.format(angleC));
			
			//Relocate text items, so that they are visible. 
			angle2.setX(circle2.getCenterX() + offset);
			angle2.setY(circle2.getCenterY() + offset); 
        });
        
       
        
        //Connect line1's end-point with the center of circle 2
        line1.endXProperty().bind(circle2.centerXProperty());
        line1.endYProperty().bind(circle2.centerYProperty());
		
        //Connect line2 starting point to the center of circle 2 
		line2.startXProperty().bind(circle2.centerXProperty());
        line2.startYProperty().bind(circle2.centerYProperty());
		
        
        
        
        
        //Circle 3
		circle3.setStroke(Color.BLACK);
		circle3.setFill(Color.DODGERBLUE);
		circle3.setOnMouseDragged(e -> 
		{
			circle3.setCenterX(e.getX());
			circle3.setCenterY(e.getY());
			
			updateAngles(circle1.getCenterX(), circle1.getCenterY(), circle2.getCenterX(), 
					circle2.getCenterY(), circle3.getCenterX(), circle3.getCenterY());
			
			angle1.setText(df.format(angleA));
			angle2.setText(df.format(angleB));
			angle3.setText(df.format(angleC));
			
			//Relocate text items, so that they are visible. 
			angle3.setX(circle3.getCenterX() + offset);
			angle3.setY(circle3.getCenterY() + offset);
		});
		
        //Connect line2's end-point with the center of circle 3
        line2.endXProperty().bind(circle3.centerXProperty());
        line2.endYProperty().bind(circle3.centerYProperty());
        
        //Connect line3 starting point to the center of circle 3 
		line3.startXProperty().bind(circle3.centerXProperty());
        line3.startYProperty().bind(circle3.centerYProperty());
        
        
        //Calls the hover action method, which changes the color of the vertices to black when the user hovers
        //their mouse over them. Just a little added bonus action. 
        hoverAction(circle1, Color.RED, Color.BLACK);
        hoverAction(circle2, Color.GREEN, Color.BLACK);
        hoverAction(circle3, Color.DODGERBLUE, Color.BLACK);
      

		
		//Add all the objects/variables to the pane
		pane.getChildren().addAll(circle1, circle2, circle3, line1, line2, line3, angle1, angle2, angle3);
		
		Scene scene = new Scene(pane, 700, 500);//Create a Scene, place pane in scene, and set pane dimensions.
		stage.setScene(scene);//Place Scene in Stage
		stage.setTitle("Geometry");//Set title for Stage
		stage.show();//Display Stage 
	}
	
	

	/*This method was created for a little bonus action. The main point of this method, is to change
	 * the color of the vertices whenever the user hovers their mouse over one of them. 
	 * I thought this would be a cool way for the user to interact with the vertices. 
	 */
    public static void hoverAction(Circle c, Color previousColor, Color hoverColor) {
        c.setOnMouseEntered( e -> c.setFill(hoverColor));
        c.setOnMouseExited(e -> c.setFill(previousColor));
    }
    
	
	
	
	//This is the method that will update the lengths of the lines as well as the angles, whenever the vertices move.  
	//Method will be called whenever the end-points move. 
	public static void updateAngles(double x1, double y1, double x2, double y2, double x3, double y3) 
	{
		
		//Calculate the distance.
		//Use those distances, and plug them into the angle formula
		//Set those angles equal to angleA, angleB, angleC
		
		
		//Calculate the distance between end-points/vertices. 
		double distanceA = Math.sqrt(Math.pow(x2 - x3, 2) + Math.pow(y2 - y3, 2));
		double distanceB = Math.sqrt(Math.pow(x1 - x3, 2) + Math.pow(y1 - y3, 2));
		double distanceC = Math.sqrt(Math.pow(x1 - x2, 2) + Math.pow(y1 - y2, 2));
		
		//Calculate the angles, and update global angle variable.  
		angleA = Math.toDegrees(Math.acos((Math.pow(distanceA, 2) - Math.pow(distanceB, 2) - Math.pow(distanceC, 2))
				/ (-2 * distanceB * distanceC)));
		
		angleB = Math.toDegrees(Math.acos((Math.pow(distanceB, 2) - Math.pow(distanceA, 2) - Math.pow(distanceC, 2))
				/ (-2 * distanceA * distanceC)));
		
		angleC = Math.toDegrees(Math.acos((Math.pow(distanceC, 2) - Math.pow(distanceB, 2) - Math.pow(distanceA, 2))
				/ (-2 * distanceA * distanceB)));
					
	}//End of method start. 
	
	//Main Method. 
	public static void main(String[] args) 
	{
		//launch the application 
	    launch(args);
	}
}//End of class Triangle. 



