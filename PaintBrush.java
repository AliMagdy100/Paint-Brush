import java.awt.event.*;
import java.applet.*;
import java.awt.*; 
import java.util.*; 



public class PaintBrush extends Applet{

	// Initializations 
	
		ArrayList<Shape> shapes;
		Line line ;  
        Rectangle rect;		 
		Oval oval ; 
		Pencil pencil;
		Eraser eraser ;
		int x1, x2, y1, y2;
		boolean solidCheck = false; // unchecked by default
		int currentShape;
        char currentColor;
		Graphics g ;
		Button lineButton;
	    Button rectButton;
		Button ovalButton;
		Button pencilButton;
		Button eraserButton;
		Button redButton;
		Button greenButton;
		Button blueButton;
		Button clearAllButton;
		Button undoButton;
	    Checkbox solidCheckBox;  
		
		
	public void init (){
		
	    shapes = new ArrayList<Shape>();
		line = new Line(); 
		rect = new Rectangle();  
		oval = new Oval();
		eraser = new Eraser();
		pencil = new Pencil();
		lineButton = new Button("Line");
		ovalButton = new Button("Oval");
        rectButton = new Button("Rectangle");
        pencilButton = new Button("Pencil");
        redButton = new Button("Red");
        greenButton = new Button("Green");
        blueButton = new Button("Blue");
        eraserButton = new Button("Eraser");
        clearAllButton = new Button("Clear All");
        solidCheckBox = new Checkbox("Filled", solidCheck);
        undoButton = new Button("Undo");  
		redButton.setBackground(Color.RED);
        greenButton.setBackground(Color.GREEN);
        blueButton.setBackground(Color.BLUE);
		
		add(lineButton);
        add(rectButton);
		add(ovalButton);
        add(pencilButton);
        add(redButton);
        add(greenButton);
        add(blueButton);
        add(eraserButton);
        add(clearAllButton);
        add(undoButton);
		add(solidCheckBox);
		
		
		//Registering Listeners to Sources 
		//Utilization of Anonymous Inner Class and Adapters
		
		this.addMouseListener(new MouseAdapter(){
			 
			 public void mousePressed(MouseEvent e){
			 x1 = e.getX();
             y1 = e.getY();  
			 
				switch (currentShape) { 
				case 1:
					line.setX1(x1);
					line.setY1(y1); 
					break;
				case 2:
					rect.setX1(x1);
					rect.setY1(y1); 
					break;
				case 3:
					oval.setX1(x1);
					oval.setY1(y1); 
					break; 
				case 4:
					pencil.setX1(x1);
					pencil.setY1(y1); 
					
				case 5:
					eraser.setX1(x1);
					eraser.setY1(y1); 
					break;  
				}
			} 
		 
			public void mouseReleased(MouseEvent e) { 
				switch (currentShape) {
				case 1:
					shapes.add(new Line(x1, y1, x2, y2, currentColor));
					break;
				case 2:
					shapes.add(new Rectangle(x1, y1, x2, y2, currentColor, solidCheck));
					break;
				case 3:
					shapes.add(new Oval(x1, y1, x2, y2, currentColor, solidCheck ));
					break; 
																
				}		
			}	 
		});
		
		this.addMouseMotionListener(new MouseAdapter(){
			 
			public void mouseDragged(MouseEvent e) { 
				x2 = e.getX();
				y2 = e.getY();  
			 
			 if(currentShape == 1 || currentShape == 2 || currentShape == 3){
				switch (currentShape) { 
				case 1:
					line.setX2(x2);
					line.setY2(y2);
				case 2:
					rect.setX2(x2);
					rect.setY2(y2);
				case 3:
					oval.setX2(x2);
					oval.setY2(y2);
				}
				repaint() ;
			 }	
				
			else {	
				switch (currentShape) {
				case 4:
				
					pencil.setX2(x2);
					pencil.setY2(y2);
					shapes.add(new Pencil(e.getX(), e.getY(), 5, 5, currentColor));
					repaint();
					break;
					
				case 5:
					eraser.setX2(x2);
					eraser.setY2(y2);
					shapes.add(new Eraser(e.getX(), e.getY(), 30, 30, currentColor));
					repaint();
					break;					
				}
				
			repaint();	
			
			}
			}	
		 
		}); 
		  
		lineButton.addActionListener(new ActionListener() {  

            public void actionPerformed(ActionEvent e) {
                currentShape = 1;
            }
        });
		rectButton.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                currentShape = 2;
            }
        }); 
		
		ovalButton.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                currentShape = 3;
            }
        });
		
		pencilButton.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                currentShape = 4;
            }
        });   
		
		eraserButton.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                currentShape = 5;
            }
        });
		
		redButton.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                currentColor = 'R';
            }
        });

        greenButton.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                currentColor = 'G';
            }
        });

        blueButton.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                currentColor = 'B';
            }
        });   
		solidCheckBox.addItemListener(new ItemListener() {

            public void itemStateChanged(ItemEvent e) {
                solidCheck = solidCheckBox.getState();
            }
        }); 
		clearAllButton.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                shapes.clear();
				
				//Handling Corner Case: On Pressing ClearAll the last object isn't cleared 				
                switch (currentShape) {  
                    case 1:
                        line.setX1(0);
                        line.setY1(0);
                        line.setX2(0);
                        line.setY2(0);
                        break;
                    case 2:
                        rect.setX1(0);
                        rect.setY1(0);
                        rect.setX2(0);
                        rect.setY2(0);
                        break;
                    case 3:
                        oval.setX1(0);
                        oval.setY1(0);
                        oval.setX2(0);
                        oval.setY2(0);
                        break;
					
					case 4:
                        pencil.setX1(0);
                        pencil.setY1(0);
                        pencil.setX2(0);
                        pencil.setY2(0);
                        break;
                }
                repaint();
            }
        });  
		
		undoButton.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                if (shapes.size()> 0){
					
                    shapes.remove(shapes.size()-1);
                   
				   //Handling Corner Case: On Pressing Undo the last object isn't cleared
				   switch (currentShape) {  
                        case 1:
                        line.setX1(0);
                        line.setY1(0);
                        line.setX2(0);
                        line.setY2(0);
                        break;
                    case 2:
                        rect.setX1(0);
                        rect.setY1(0);
                        rect.setX2(0);
                        rect.setY2(0);
                        break;
                    case 3:
                        oval.setX1(0);
                        oval.setY1(0);
                        oval.setX2(0);
                        oval.setY2(0);
                        break;
					case 4:
                        pencil.setX1(0);
                        pencil.setY1(0);
                        pencil.setX2(0);
                        pencil.setY2(0);
                        break;

                    }
                    repaint();
                }
            }
        });	
	} 
	
	    public void paint (Graphics g ){
			
		    for (int i= 0; i<shapes.size();i++){
				shapes.get(i).draw(g);
            }  
				switch (currentShape){ 
				case 1:
					line.setColor(currentColor);   
					line.draw(g); 
					break;
				case 2:
					rect.setColor(currentColor); 
					rect.setsolidCheck(solidCheck) ; 
					rect.draw(g); 
					break;
				case 3:
					oval.setColor(currentColor); 
					oval.setsolidCheck(solidCheck) ; 
					oval.draw(g); 
					break;
				case 4:
					pencil.setColor(currentColor);  
					pencil.draw(g); 
					break; 					
					
				case 5:
					eraser.draw(g);
					break;
				   
				}
		}      
}

abstract class Shape {
	
	public static final int Line      = 1;
	public static final int Rectangle = 2;
	public static final int Oval      = 3;
	public static final int pencil    = 4;
	public static final int Eraser    = 5;
	public static final char Red      = 'R';
	public static final char Green    = 'G';
	public static final char Blue     = 'B';
	
	private int x1,y1,x2,y2 ;   
	private boolean solidCheck ; 
	private int color ; 
	
    // Constructors Declaration
	public Shape(int x1,int y1,int x2,int y2,int color,boolean solidCheck)
	{
		this.x1 = x1;
        this.y1 = y1;
        this.x2 = x2;
        this.y2 = y2;
        this.color = color;
        this.solidCheck = solidCheck;
	} 
	// Customised Constructor for Line,Pencil and Eraser(Over Loading)
	public Shape(int x1,int y1,int x2,int y2,int color)
	{
		this.x1 = x1;
        this.y1 = y1;
        this.x2 = x2;
        this.y2 = y2;
        this.color = color;
      
	} 
	
	public Shape(){} 
	
	// Setters 
    public void setX1(int x1){
        this.x1 = x1;
    }

    public void setY1(int y1){
        this.y1 = y1;
    }

    public void setX2(int x2){
        this.x2 = x2;
    }

    public void setY2(int y2){
        this.y2 = y2;
    }
    // Getters 
    public int getX1(){
        return x1;
    }

    public int getY1(){
        return y1;
    }

    public int getX2(){
        return x2;
    }

    public int getY2(){
        return y2;
    }

    public void setColor(int color){
        this.color = color;
    }

    public int getColor(){
        return color;
    }

    public boolean getsolidCheck(){
        return solidCheck;
    }

    public void setsolidCheck(boolean solidCheck){
        this.solidCheck = solidCheck;
    }

	// Abstract Method Shall Be Overridden By Childerin Classes
    public abstract void draw(Graphics g);
    
	
} 
	//childerin Classes Declaration
class Line extends Shape {

    public Line(int x1,int y1,int x2,int y2,int color) 
	{
		super(x1, y1, x2, y2, color);
	}  
   
	public Line (){}  
	
	
	public void draw(Graphics g) 
	{
		switch (getColor()) 
		{
			case 'R' : 
			g.setColor (Color.red);
			break ;  
			case 'G' : 
			g.setColor (Color.GREEN); 
			break ;  
			case 'B' : 
			g.setColor(Color.BLUE);
			break ;   
			default : 
			g.setColor(Color.BLACK);
			break ;
			
		} 
		g.drawLine(getX1(),getY1(),getX2(),getY2()); 

	}
	
} 

class Rectangle extends Shape {
    int x,y,width,height;
 
	 public Rectangle(int x1,int y1,int x2,int y2,int color,boolean solidCheck) 
	{
		super(x1, y1, x2, y2, color, solidCheck);
	}  
   
	public Rectangle (){}   

      public void draw (Graphics g) 
	{
		switch (getColor() ) 
		{
			case 'R' : 
			g.setColor (Color.red);
			break ;  
			case 'G' : 
			g.setColor (Color.GREEN); 
			break ;  
			case 'B' : 
			g.setColor(Color.BLUE);
			break ;   
			default : 
			g.setColor(Color.BLACK);
			break ;	
		} 
		
		//Handling Corner Case: Drawing Rectangle in any direction
	    if(getX1()<getX2()) 
		    x = getX1();
		else 
			x = getX2();
		
        if(getY1()<getY2()) 
			y = getY1();
		else 
			y = getY2();
		
         width  = Math.abs(getX1() - getX2());
         height = Math.abs(getY1() - getY2());
         
        if (getsolidCheck() == true) 
            g.fillRect(x, y, width, height);
        else 
            g.drawRect(x, y, width, height); 
	}	
} 


class Oval extends Shape {
	
	int x,y,width,height;

    public Oval(){}

    public Oval(int x1,int y1,int x2,int y2,int color,boolean solidCheck) 
	{
		super(x1, y1, x2, y2, color, solidCheck);
	}  

    public void draw(Graphics g) {
        switch (getColor()) 
		{	
			case 'R' : 
			g.setColor (Color.red);
			break ;  
			case 'G' : 
			g.setColor (Color.GREEN); 
			break ;  
			case 'B' : 
			g.setColor(Color.BLUE);
			break ;   
			default : 
			g.setColor(Color.BLACK);
			break ;	
		}
		
		//Handling Corner Case: Drawing oval in any direction
        if(getX1()<getX2()) 
		    x = getX1();
		else 
			x = getX2();
		
        if(getY1()<getY2()) 
			y = getY1();
		else 
			y = getY2();
		
         width  = Math.abs(getX1() - getX2());
         height = Math.abs(getY1() - getY2());

        if (getsolidCheck() == true) 
            g.fillOval(x, y, width, height);
        else 
            g.drawOval(x, y, width, height);  
    }
} 

class Pencil extends Shape {

	int x,y;
	
    public Pencil(int x1,int y1,int x2,int y2,int color) 
	{
		super(x1, y1, x2, y2, color);
	}  
   
	public Pencil (){}  
	
	public void draw(Graphics g) 
	{
		switch (getColor()) 
		{
			case 'R' : 
			g.setColor (Color.red);
			break ;  
			case 'G' : 
			g.setColor (Color.GREEN); 
			break ;  
			case 'B' : 
			g.setColor(Color.BLUE);
			break ;   
			default : 
			g.setColor(Color.BLACK);
			break ;	
		}
				
		g.fillRect(getX1(),getY1(),2,2); 
	}
}

class Eraser extends Shape {

    public Eraser(){}

    public Eraser(int x1,int y1,int x2,int y2,int color) {
        super(x1, y1, x2, y2, color);
    }

    
    public void draw(Graphics g) {
		
		g.setColor(Color.WHITE);
        g.fillRect(getX1(),getY1(),30,30);  
    }
}