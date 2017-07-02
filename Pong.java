
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import javax.swing.JFrame;


public class Pong extends JFrame implements MouseListener, KeyListener, Runnable, MouseMotionListener
{
    Image dbImage;
    Graphics dbg;
    boolean startGame = false;
    Rectangle r1,r2,p1,p2,ball;
    int p1x=0,p1y=400,p2x=1575,p2y=400;//player paddles
    int p1inc,p2inc;
    int bx=1200,by=100;//ball
    int ballXInc=1, ballYInc=1;
    int points1,points2;
    Pong()
    {
        this.setSize(1600, 900);
        this.setVisible(true);
        this.setDefaultCloseOperation(Pong.EXIT_ON_CLOSE);
        r1 = new Rectangle(725, 275,150,50);
        r2 = new Rectangle(725, 475,150,50);
        p1 = new Rectangle(p1x,p1y,25,100);
        p2 = new Rectangle(p2x,p2y,25,100);
        ball = new Rectangle(bx,by,25,25);
        addMouseListener(this);
        addKeyListener(this);
        addMouseMotionListener(this);
        points1 = 0;
        points2 = 0;
    }
    public static void main(String ar[])
    {
        Pong p = new Pong();
        Thread t1 = new Thread(p);
        t1.start();
    }
    public void paint(Graphics g)
    {
        dbImage = this.createImage(getWidth(),getHeight());
        dbg = dbImage.getGraphics();
        paintComponent(dbg);
        g.drawImage(dbImage, 0, 0, this);
    }
    public void paintComponent(Graphics g)
    {
        if(!startGame)
        {
            g.setColor(Color.GRAY);
            g.fillRect(0,0,1600,900);
            g.setColor(Color.CYAN);
            g.fillRect(r1.x,r1.y,r1.width,r1.height);
            g.fillRect(r2.x, r2.y, r2.width, r2.height);
            g.setColor(Color.DARK_GRAY);
            g.setFont(new Font("Arial",Font.BOLD,18));
            g.drawString("Start",750,300);
            g.drawString("Quit",750,500);
        }
        else
        {
            g.fillRect(p1.x,p1.y,p1.width,p1.height);
            g.fillRect(p2.x,p2.y,p2.width,p2.height);
            g.fillRect(ball.x,ball.y,ball.width,ball.height);
            g.drawString(Integer.toString(points1),100,100);
            g.drawString(Integer.toString(points2),1300,100);
            //bound check for paddles 
            if(p1.y<25)
                p1.y=25;
            if(p1.y>825)
                p1.y=825;
            if(p2.y<25)
                p2.y=25;
            if(p2.y>825)
                p2.y=825;
            //ball movement
            if(ball.x>1575)
            {
                points1++;
                setBallXDir(-1);
            }
            if(ball.x<=0)
            {
                points2++;
                setBallXDir(1);
            }
            if(ball.y>=825)
                setBallYDir(-1);
            if(ball.y<=25)
                setBallYDir(1);
            if(ball.intersects(p1))
            {
                setBallXDir(1);
            }
            if(ball.intersects(p2))
            {
                setBallXDir(-1);
            }
            
        }
        repaint();
    }
    public void run()
    {
        try
            {
            while(true)
            {
                move();
                Thread.sleep(3);
            }
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
    }
    
    public void move()
    {
        p1.y = p1.y + p1inc;
        //p2.y = p2.y + p2inc;
        ball.x = ball.x + ballXInc;
        ball.y = ball.y + ballYInc;
    }
    public void setP1Dir(int y)
    {
        p1inc = y;
    }
    public void setP2Dir(int y)
    {
        p2inc = y;
    }
    public void setBallXDir(int x)
    {
        ballXInc = x;
    }
    public void setBallYDir(int y)
    {
        ballYInc = y;
    }
    //overridding methods
    @Override
    public void mouseClicked(MouseEvent e) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void mousePressed(MouseEvent e) {
        int mx,my;
        mx = e.getX();
        my = e.getY();
        if(mx>r1.x && mx<(r1.x+r1.width) && my>r1.y && my <(r1.y+r1.height))
        {
            startGame = true;
        }
    }
    
    @Override
    public void mouseReleased(MouseEvent e) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void mouseEntered(MouseEvent e) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void mouseExited(MouseEvent e) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void keyTyped(KeyEvent e) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void keyPressed(KeyEvent e) {
        int key = e.getKeyCode();
        if(key == e.VK_UP)
        {
            setP1Dir(-1);
        }
        if(key == e.VK_DOWN)
        {
            setP1Dir(1);
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        int key = e.getKeyCode();
        if(key == e.VK_UP)
        {
            setP1Dir(0);
        }
        if(key == e.VK_DOWN)
        {
            setP1Dir(0);
        }
    }
    //end of overridding methods

    @Override
    public void mouseDragged(MouseEvent e) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void mouseMoved(MouseEvent e) {
        int my;
        my = e.getY();
        p2.y = my;
    }
    
}