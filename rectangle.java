import java.awt.Graphics2D;

public class rectangle {
private int x, y, w, h;
    
    public rectangle(int x, int y, int w, int h) {
        this.x = x;
        this.y = y;
        this.w = w;
        this.h = h; 
    }
    
    //GETTERS & SETTERS
    public int getX() {
		return x;
	}
	public void setX(int x) {
		this.x = x;
	}
	public int getY() {
		return y;
	}


    
    public void collision(character c) {
        if(this.x + this.w > c.x+2 && this.x< c.x) {
        	if(this.y < c.y+c.height) {
        		c.baseY = this.y-c.height; 
        	}
        } else {
        	c.baseY = 200; 
        }
    }
    
    public void paint (Graphics2D g) {
    	g.drawRect(this.getX(), this.getY(),  w, h);

    }
}
