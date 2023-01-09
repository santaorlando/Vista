import java.awt.*;
import java.awt.image.BufferedImage;
import javax.imageio.*;
import java.io.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class character {

	private BufferedImage w1 = null;
	private BufferedImage w2 = null;
	private BufferedImage w3 = null;
	private BufferedImage w4 = null;
	private BufferedImage w5 = null;
	private BufferedImage j1 = null;
	private BufferedImage j2 = null;
	private BufferedImage j3 = null;
	private BufferedImage j4 = null;
	private BufferedImage idle = null;

	private BufferedImage img = null;

	int x = 500, y = 200, width = 160, height = 200;
	int xa = 1;
	int start, end;
	int baseY = 200;
	boolean left = false, right = false, up = false;;

	public void setImg(BufferedImage img) {
		this.img = img;
	}

	public character() {
		try {
			w1 = ImageIO.read(new File("walking1.png"));
			w2 = ImageIO.read(new File("walking2.png"));
			w3 = ImageIO.read(new File("walking3.png"));
			w4 = ImageIO.read(new File("walking4.png"));
			w5 = ImageIO.read(new File("walking5.png"));
			j1 = ImageIO.read(new File("jump1.png"));
			j2 = ImageIO.read(new File("jump2.png"));
			j3 = ImageIO.read(new File("jump3.png"));
			j4 = ImageIO.read(new File("jump4.png"));
			idle = ImageIO.read(new File("idle.png"));
		} catch (IOException e) {
			System.out.println("No Image");
		}
		this.start = x;
	}

	public void keyPressed(KeyEvent e) {
		if (e.getKeyCode() == KeyEvent.VK_A) {
			left = true;
			end = start - 60;
		}
		if (e.getKeyCode() == KeyEvent.VK_D) {
			right = true;
			end = start + 60;
		}
		if (e.getKeyCode() == KeyEvent.VK_W) {
			up = true;
		}

	}

	public void keyReleased(KeyEvent e) {
		if (e.getKeyCode() == KeyEvent.VK_A) {
			left = false;
		}
		if (e.getKeyCode() == KeyEvent.VK_D) {
			right = false;
		}
	}

	boolean top = false;

	public void move(rectangle r) {
		r.collision(this); 
		if (left) {
			x -= 2;
			if (x == end) {
				start = end;
				end -= 60;
			}
		} else if (right) {
			x += 2;
			if (x == end) {
				start = end;
				end += 60;
			}
		}
		if (up) {
			if (y >= baseY - 200 && !top) {
				y -= 4;
				if (y == baseY - 200) {
					top = true;
				}
			}
			if (y <= baseY && top) {
				y += 4;
				if (y == baseY) {
					top = false;
					up = false;
				}
			}
		}
	}

	public void paint(Graphics2D g) {
		if (up && (left || right)) {
			if (y <= baseY && y > baseY - 100 && !top) {
				setImg(j1);
			} else if (y <= baseY-100 && y > baseY - 200  && !top) {
				setImg(j2);
			} else if (y >= baseY - 200 && y < baseY - 100  && top) {
				setImg(j3);
			} else if (y >= baseY-100 && y <= baseY && top) {
				setImg(j4);
			}
		}
		else if (left) {
			if (x <= start && x > start - 6) {
				setImg(w1);
			} else if (x <= start - 12 && x > start - 24) {
				setImg(w2);
			} else if (x <= start - 24 && x > start - 36) {
				setImg(w3);
			} else if (x <= start - 36 && x > start - 48) {
				setImg(w4);
			} else if (x <= start - 48 && x >= end) {
				setImg(w5);
			}
		} else if (right) {
			if (x >= start && x < start + 6) {
				setImg(w1);
			} else if (x >= start + 12 && x < start + 24) {
				setImg(w2);
			} else if (x >= start + 24 && x < start + 36) {
				setImg(w3);
			} else if (x >= start + 36 && x < start + 48) {
				setImg(w4);
			} else if (x >= start + 48 && x < end) {
				setImg(w5);
			}
		}
		if (!right && !left) {
			setImg(idle);
		}

		if (left) {
			g.drawImage(img, x + width, y, -width, height, null);
		} else {
			g.drawImage(img, x, y, width, height, null);
		}

	}
}
