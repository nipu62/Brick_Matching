package brick.matching;


import java.awt.*;
import java.awt.event.*;
import java.util.Random;
import javax.swing.*;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
/*
* The MainGame class implements all the listener needed for the game to play
* The graphical user interface of the game is written here
*/
public class MainGame implements Runnable,KeyListener,MouseMotionListener,MouseListener {
    public static void main(String args[])
    {
        // MainGame object calls the run method
        MainGame b = new MainGame();
        b.run();

    }

    ScreenManager s;
    /*
    * the flag will check the input is even or odd
    * lev variable updates the grid with level
    * moveClicked counts how many moves are done
    * the name of the images are taken
    * the modes array display the screen
    */
    static int flag, lev , score = 1000, moveClicked = 0;
    Image red,green,blue,yellow,menuImage,back,what,match, level, win, instruction;
    int x,y ;
    boolean gameRunning = true,menu,clk=false, levelOne = false, levelTwo= false, gameComplete = false, inst = false;
    private static final DisplayMode modes1[]=
    {
        
        new DisplayMode(800,600,16,0),
        new DisplayMode(640,480,32,0),
        new DisplayMode(640,480,24,0),
        new DisplayMode(640,480,16,0),
        new DisplayMode(1366,768,16,0),


    };

    grid game;
    public void init()
    {
        /*
        initialize the game necessary variables
        */
        menu=true;
        gameRunning = true;
        clk=false;
        levelOne = false;
        levelTwo= false;
        gameComplete = false;
        score = 1000;
        moveClicked = 0;
        inst = false;
   
        red = new ImageIcon("red.jpg").getImage();
        blue = new ImageIcon("blue.jpg").getImage();
        green = new ImageIcon("green.jpg").getImage();
        yellow = new ImageIcon("yellow.jpg").getImage();
        menuImage = new ImageIcon("menu.jpg").getImage();
        back = new ImageIcon("p1.jpg").getImage();
        what = new ImageIcon("what.jpg").getImage();
        match = new ImageIcon("match.jpg").getImage();
        level = new ImageIcon("level.jpg").getImage();
        win = new ImageIcon("win.jpg").getImage();
        instruction = new ImageIcon("instruction.jpg").getImage();
       lev = 1;
       
    }

 /*
 * the run method runs the whole program
 * takes a displaymode vaiable for full screen manager
 * adds KeyListener, MouseMotionListener ,MouseListener
 */
    public void run()
    {

        s = new ScreenManager();

        try
        {
            DisplayMode dm = s.findFirstCompatibleMode(modes1);
            s.setFullScreen(dm);
            Window w = s.getFullScreenWindow();
            w.addKeyListener(this);
            w.addMouseMotionListener(this);
            w.addMouseListener(this);
            init();
            game  = new grid(lev);
            
            /*
            the game will continue till the gameRunning is true
            */
            while (gameRunning == true)
            {
              //menu image will show till it is false
                if(menu==true)
                {
                    while(menu==true && gameRunning)
                    {
                        Graphics2D g = s.getGraphics();
                        g.drawImage(menuImage,0,0,null);
                        g.dispose();
                        s.update();
                    }
                    menu=false;

                    Graphics2D g = s.getGraphics();
                    g.drawImage(level,0,0,null);
                    g.dispose();
                    s.update();

                }
                //display the level image if the level button is clicked
                else if(menu==false && clk == true )
                {
                    if(levelOne==false && levelTwo == false && inst == false)

                    {
                        Graphics2D g = s.getGraphics();
                        g.drawImage(level,0,0,null);
                        g.dispose();
                        s.update(); 
                    }
                    else if(levelOne==false && levelTwo == false && inst == true)
                    {
                        Graphics2D g = s.getGraphics();
                        g.drawImage(instruction,0,0,null);
                        g.dispose();
                        s.update(); 
                        
                    }
                    
                    
                    
                }
               
                // display the score if all the color is matched
                else if(menu==false && gameComplete==true)
                {
                    Graphics2D g = s.getGraphics();
                    g.drawImage(win,0,0,null);
                    Font f = new Font("Arial", Font.BOLD,40);
                    g.setFont(f);
                    Color m = new Color( 191,255, 0);
                    g.setColor(m);
                    
                    g.drawString("Your Score is : " +score, 150, 300);
                    g.dispose();
                    s.update(); 
                }
                
                
                //otherwise checks the clicked value
                else 
                    
                {
                
                  
                    Graphics2D g = s.getGraphics();
                    g.drawImage(back,0,0,null);
                    
                    paintOne(game,g);
                    g.dispose();
                    s.update();

                    if(clk)
                    {
                        moveClicked++;
                        
                        g = s.getGraphics();
                        g.drawImage(back,0,0,null);
                        game.switchOn[x][y]=true;
                        try{
                            paintOne(game,g);
                            g.dispose();
                            s.update();
                            Thread.sleep(800);
                   
                        
                        }catch(Exception e){};
                        
                        game.switchOn[x][y]=false;
                        
                        pairs.check(x,y,game);
                        gameComplete = pairs.match(game);
                     
                        if(gameComplete==true)
                        score = score - (moveClicked-16) * 10;
                        
                        g = s.getGraphics();
                        g.drawImage(back,0,0,null);

                     
                        
                        try{
                            paintOne(game,g);
                            g.dispose();
                            s.update();
                            Thread.sleep(800);
                            //System.out.println("asfafasf");
                        
                        }catch(Exception e){};
                        
                      

                         clk=false;

                    }
                    else
                    {


                        g.drawImage(back,0,0,null);
                        g.dispose();
                        s.update();

                    }


                }
                        
          }

                                       
        }
                        


        finally
        {
            s.restoreScreen();
        }
    }

    //Paints the whole grid
    public void paintOne(grid game,Graphics2D g)
    {

        for(int i=0; i<4; i++)
        {
            for(int j=0; j<4; j++)
            {
                if(game.switchOn[i][j]==true)
                    paint(game.arr[i][j],j,i,g);
                else
                    paint(6,j,i,g);

            }

        }


    }
    //display the colour of the clicked button and
    //shows the moves done
    public void paint(int valueGrid, int i,int j,Graphics2D g)
    {
        Font f = new Font("Arial Black", Font.BOLD,30);
        g.setFont(f);
        Color m = new Color(255, 204, 0);
        g.setColor(m);
        g.drawString("Moves : " +moveClicked, 250, 100);
        if(valueGrid==1)
            g.drawImage(red, 250+ i*50,150 +j*50, null);
        else   if(valueGrid==2)
            g.drawImage(blue, 250+ i*50, 150 +j*50, null);
        else if(valueGrid==3)
            g.drawImage(green, 250+ i*50, 150 +j*50, null);
        else  if(valueGrid==4)
            g.drawImage(yellow, 250+ i*50, 150 +j*50, null);
        else  if(valueGrid==6)
            g.drawImage(what, 250+ i*50, 150 +j*50, null);
        else  if(valueGrid==-1)
            g.drawImage(match, 250+ i*50, 150 +j*50, null);



        return ;
    }


    public void keyTyped(KeyEvent e)
    {

    }


    public void keyPressed(KeyEvent e)
    {
        //Escape button works for the Start menu
        if (e.getKeyCode() == KeyEvent.VK_ESCAPE)
        {
            if(menu == true)
            gameRunning =  false;
            else
            {
            menu = true;
            init();
            
            }
            
            
        }
        
       
    }


    @Override
    public void keyReleased(KeyEvent e)
    {

    }

    @Override
    /*
    * gets the co-ordinate of the mouseClicked
    * and checks if the mouse is clicked for the level button
    * or the quit button , otherwise takes the co-ordinates
    */
    
    public void mouseClicked(MouseEvent e)
    {
        x=e.getX();
        y=e.getY();
        clk = true;
        if(menu== true && levelOne==false && levelTwo==false)
                {
                    clk=true;
                    menu = false;
                    //if(x>=218 && y>=262 && x<=528 && y<=342)
                    if(x>=219 && y>= 347 && x <= 525 && y<= 420)
                    {
                        inst = false;
                        gameRunning = false;
                        
                    }
                    else if(x>=219 && y>=427 && x<=524 && y<=483)
                    {
                        inst = true;
                    }
                }
                

        else if(menu==false && levelOne==false && levelTwo==false &&
                x>=208 && y>= 213 && x<=526 && y<=297)
        {
            menu = false;
            clk = false;
            levelOne = true;
            lev = 1;
            game = new grid(lev);
   
            
        }
        else if(menu==false && levelTwo==false && levelOne==false && 
                x>=203 && y>= 305 && x<=525 && y<=387)
        {
            menu = false;
            clk = false;
            levelTwo = true;
            lev = 2;
            game = new grid(lev);
           
        } 
        else if((clk==true && x>=219 && y>=346 && x<=525 && y<=419)||
                (levelOne==false && levelTwo==false &&
                 x>=203 && y>=395 && x<=522 && y<=471))
        {
            gameRunning = false;
        }
        else if(menu==false)
        {
         
            for(int i=0; i<4; i++)
                for(int j=0; j<4; j++)
                {
                    if(250 + i*50 <=x && 300 + i*50 >=x && 150 + j*50 <=y && 200 + j*50 >=y )
                    {

                        x=j;
                        y=i;
                        System.out.println(x+"  "+y);
       
                        break;

                    }
                }
            clk=true;



        }
    }

    @Override
    public void mouseDragged(MouseEvent e)
    {
    }

    @Override
    public void mouseMoved(MouseEvent e)
    {
    }

    @Override
    public void mousePressed(MouseEvent e)
    {
    }

    @Override
    public void mouseReleased(MouseEvent e)
    {
    }

    @Override
    public void mouseEntered(MouseEvent e)
    {
    }

    @Override
    public void mouseExited(MouseEvent e)
    {
    }

    
}