//package tetris.src.sample;
//
//
//import javafx.scene.shape.Rectangle;
//
//public class Controller {
//	// Getting the numbers and the MESH from Tetris
//	public static final int MOVE = Tetris.MOVE;
//	public static final int SIZE = Tetris.SIZE;
//	public static int XMAX = Tetris.XMAX;
//	public static int YMAX = Tetris.YMAX;
//	public static int[][] MESH = Tetris.MESH;
//
//	public static Form makeRect() {
//		int block = (int) (Math.random() * 100);
//		String name;
//		Rectangle a = new Rectangle(SIZE-1, SIZE-1), b = new Rectangle(SIZE-1, SIZE-1), c = new Rectangle(SIZE-1, SIZE-1),
//				d = new Rectangle(SIZE-1, SIZE-1);
//		if (block < 15) {
//			a.setX(XMAX / 2 - SIZE);
//			b.setX(XMAX / 2 - SIZE);
//			b.setY(SIZE);
//			c.setX(XMAX / 2);
//			c.setY(SIZE);
//			d.setX(XMAX / 2 + SIZE);
//			d.setY(SIZE);
//			name = "j";
//		} else if (block < 30) {
//			a.setX(XMAX / 2 + SIZE);
//			b.setX(XMAX / 2 - SIZE);
//			b.setY(SIZE);
//			c.setX(XMAX / 2);
//			c.setY(SIZE);
//			d.setX(XMAX / 2 + SIZE);
//			d.setY(SIZE);
//			name = "l";
//		} else if (block < 45) {
//			a.setX(XMAX / 2 - SIZE);
//			b.setX(XMAX / 2);
//			c.setX(XMAX / 2 - SIZE);
//			c.setY(SIZE);
//			d.setX(XMAX / 2);
//			d.setY(SIZE);
//			name = "o";
//		} else if (block < 60) {
//			a.setX(XMAX / 2 + SIZE);
//			b.setX(XMAX / 2);
//			c.setX(XMAX / 2);
//			c.setY(SIZE);
//			d.setX(XMAX / 2 - SIZE);
//			d.setY(SIZE);
//			name = "s";
//		} else if (block < 75) {
//			a.setX(XMAX / 2 - SIZE);
//			b.setX(XMAX / 2);
//			c.setX(XMAX / 2);
//			c.setY(SIZE);
//			d.setX(XMAX / 2 + SIZE);
//			name = "t";
//		} else if (block < 90) {
//			a.setX(XMAX / 2 + SIZE);
//			b.setX(XMAX / 2);
//			c.setX(XMAX / 2 + SIZE);
//			c.setY(SIZE);
//			d.setX(XMAX / 2 + SIZE + SIZE);
//			d.setY(SIZE);
//			name = "z";
//		} else {
//			a.setX(XMAX / 2 - SIZE - SIZE);
//			b.setX(XMAX / 2 - SIZE);
//			c.setX(XMAX / 2);
//			d.setX(XMAX / 2 + SIZE);
//			name = "i";
//		}
//		return new Form(a, b, c, d, name);
//	}
//}