import java.awt.*;
import java.awt.geom.Ellipse2D;
import java.util.Iterator;
import java.util.LinkedList;

/**
 * Implements a bouncing ball simulation.
 */
public class BouncingBallsSimulationImproved extends Component implements Runnable {

	LinkedList<Ball>[][] hashtable;    // List of balls.
	LinkedList<Ball> balls;            // List that helps to iterate
	Image img;                        // Image to display balls.
	int w, h;                        // Width an height of image.
	Graphics2D gi;                    // Graphics object to draw balls.
	float r;                        // Radius of balls.
	int n;                            // Number of balls.
	Thread thread;                    // Thread that runs simulation loop.
	int m;                            // Size of the hashtable

	/**
	 * Initializes the simulation.
	 *
	 * @param w width of simulation window.
	 * @param h height of simulation window.
	 * @param n number of balls.
	 * @param r radius of balls.
	 * @param v initial velocity of balls.
	 */
	public BouncingBallsSimulationImproved(int w, int h, int n, float r, float v, int m) {
		this.r = r;
		this.n = n;
		this.w = w;
		this.h = h;
		this.m = m;

		// Initialize balls by distributing them randomly.
		balls = new LinkedList<Ball>();
		hashtable = (LinkedList<Ball>[][]) new LinkedList<?>[m][m];
		for (int i = 0; i < hashtable.length; i++) {
			for (int j = 0; j < hashtable[i].length; j++) {
				hashtable[i][j] = new LinkedList<Ball>();
			}
		}
		for (int i = 0; i < n; i++) {
			float vx = 2 * (float) Math.random() - 1;
			float vy = 2 * (float) Math.random() - 1;
			float tmp = (float) Math.sqrt((double) vx * vx + vy * vy);
			vx = vx / tmp * v;
			vy = vy / tmp * v;
			Ball b = new Ball(r + (float) Math.random() * (w - 2 * r), r + (float) Math.random() * (h - 2 * r), vx, vy, r);
			balls.add(b);
			hashtable[0][0].add(b);
		}

	}

	public Dimension getPreferredSize() {
		return new Dimension(w, h);
	}

	/**
	 * Paint the window that displays the simulation. This method is called
	 * automatically by the Java window system as a response to the call to
	 * repaint() in the run() method below.
	 */
	public void paint(Graphics g) {
		gi.clearRect(0, 0, w, h);

		Iterator<Ball> it = balls.iterator();
		while (it.hasNext()) {
			Ball ball = it.next();
			gi.fill(new Ellipse2D.Float(ball.x - r, ball.y - r, 2 * r, 2 * r));
		}

		g.drawImage(img, 0, 0, null);
	}

	/**
	 * Starts the simulation loop.
	 */
	public void start() {
		img = createImage(w, h);
		gi = (Graphics2D) img.getGraphics();
		gi.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

		thread = new Thread(this);
		thread.run();
	}

	/**
	 * The simulation loop.
	 */
	public void run() {
		// Set up timer.
		int c = 0;
		Timer timer = new Timer();
		timer.reset();

		// Loop forever (or until the user closes the main window).
		while (true) {
			for (int i = 0; i < hashtable.length; i++) {
				for (int j = 0; j < hashtable[i].length; j++) {
					Iterator<Ball> it1 = hashtable[i][j].iterator();
					while (it1.hasNext()) {
						Ball bl = it1.next();
						int a = (int) Math.floor(bl.x / w * (m - 1));
						int b = (int) Math.floor(bl.y / h * (m - 1));
						if (a != i || b != j) {
							it1.remove();
							hashtable[a][b].add(bl);
						}
					}
				}
			}

			// Move the ball.
			for (int i = 0; i < hashtable.length; i++) {
				for (int j = 0; j < hashtable[i].length; j++) {
					Iterator<Ball> it3 = hashtable[i][j].iterator();

					while (it3.hasNext()) {
						Ball ball = it3.next();

						// Handle collisions with boundaries.
						if (ball.doesCollide((float) w, 0.f, -1.f, 0.f))
							ball.resolveCollision((float) w, 0.f, -1.f, 0.f);
						if (ball.doesCollide(0.f, 0.f, 1.f, 0.f))
							ball.resolveCollision(0.f, 0.f, 1.f, 0.f);
						if (ball.doesCollide(0.f, (float) h, 0.f, -1.f))
							ball.resolveCollision(0.f, (float) h, 0.f, -1.f);
						if (ball.doesCollide(0.f, 0.f, 0.f, 1.f))
							ball.resolveCollision(0.f, 0.f, 0.f, 1.f);

						//Handle collisions with other balls.
						for (int q = i - 1; q < i + 2; q++) {
							for (int w = j - 1; w < j + 2; w++) {
								if (q >= 0 && w >= 0 && q < hashtable.length && w < hashtable[q].length) {
									Iterator<Ball> it2 = hashtable[q][w].iterator();
									while (it2.hasNext()) {
										Ball ball2 = it2.next();

										//check for collision
										if (ball != ball2 && ball.doesCollide(ball2))
											ball.resolveCollision(ball2);
									}
								}
							}
						}
						//Move the ball
						ball.move();
					}
				}
			}
			// Trigger update of display.
			repaint();

			// Print time per simulation step.
			c++;
			if (c == 10) {
				System.out.printf("Timer per simulation step: %fms\n", (float) timer.timeElapsed() / (float) c);
				timer.reset();
				c = 0;
			}
		}
	}
}
