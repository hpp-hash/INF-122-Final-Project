package tetris.src.sample;

import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;


public class Form {
	Rectangle a;
	Rectangle b;
	Rectangle c;
	Rectangle d;
	Color color;
	private String name;
	public int form = 1;

	public Form(Rectangle a, Rectangle b, Rectangle c, Rectangle d, String name) {
		this.a = a;
		this.b = b;
		this.c = c;
		this.d = d;
		this.name = name;

		switch (name) {
		case "j":
			color = Color.BLUE;
			break;
		case "l":
			color = Color.DARKGOLDENROD;
			break;
		case "o":
			color = Color.INDIANRED;
			break;
		case "s":
			color = Color.FORESTGREEN;
			break;
		case "t":
			color = Color.CADETBLUE;
			break;
		case "z":
			color = Color.HOTPINK;
			break;
		case "i":
			color = Color.SANDYBROWN;
			break;
		}
		
		this.a.setFill(color);
		this.b.setFill(color);
		this.c.setFill(color);
		this.d.setFill(color);
	}


	public String getName() {
		return name;
	}


	public void changeForm() {
		if (form != 4) {
			form++;
		} else {
			form = 1;
		}
	}

	public static Form makeRect() {
		int block = (int) (Math.random() * 100);
		String name;
		Rectangle a = new Rectangle(TetrisGameLogic.SIZE-1, TetrisGameLogic.SIZE-1), b = new Rectangle(TetrisGameLogic.SIZE-1, TetrisGameLogic.SIZE-1), c = new Rectangle(TetrisGameLogic.SIZE-1, TetrisGameLogic.SIZE-1),
				d = new Rectangle(TetrisGameLogic.SIZE-1, TetrisGameLogic.SIZE-1);
		if (block < 15) {
			a.setX(TetrisGameLogic.XMAX / 2 - TetrisGameLogic.SIZE);
			b.setX(TetrisGameLogic.XMAX / 2 - TetrisGameLogic.SIZE);
			b.setY(TetrisGameLogic.SIZE);
			c.setX(TetrisGameLogic.XMAX / 2);
			c.setY(TetrisGameLogic.SIZE);
			d.setX(TetrisGameLogic.XMAX / 2 + TetrisGameLogic.SIZE);
			d.setY(TetrisGameLogic.SIZE);
			name = "j";
		} else if (block < 30) {
			a.setX(TetrisGameLogic.XMAX / 2 + TetrisGameLogic.SIZE);
			b.setX(TetrisGameLogic.XMAX / 2 - TetrisGameLogic.SIZE);
			b.setY(TetrisGameLogic.SIZE);
			c.setX(TetrisGameLogic.XMAX / 2);
			c.setY(TetrisGameLogic.SIZE);
			d.setX(TetrisGameLogic.XMAX / 2 + TetrisGameLogic.SIZE);
			d.setY(TetrisGameLogic.SIZE);
			name = "l";
		} else if (block < 45) {
			a.setX(TetrisGameLogic.XMAX / 2 - TetrisGameLogic.SIZE);
			b.setX(TetrisGameLogic.XMAX / 2);
			c.setX(TetrisGameLogic.XMAX / 2 - TetrisGameLogic.SIZE);
			c.setY(TetrisGameLogic.SIZE);
			d.setX(TetrisGameLogic.XMAX / 2);
			d.setY(TetrisGameLogic.SIZE);
			name = "o";
		} else if (block < 60) {
			a.setX(TetrisGameLogic.XMAX / 2 + TetrisGameLogic.SIZE);
			b.setX(TetrisGameLogic.XMAX / 2);
			c.setX(TetrisGameLogic.XMAX / 2);
			c.setY(TetrisGameLogic.SIZE);
			d.setX(TetrisGameLogic.XMAX / 2 - TetrisGameLogic.SIZE);
			d.setY(TetrisGameLogic.SIZE);
			name = "s";
		} else if (block < 75) {
			a.setX(TetrisGameLogic.XMAX / 2 - TetrisGameLogic.SIZE);
			b.setX(TetrisGameLogic.XMAX / 2);
			c.setX(TetrisGameLogic.XMAX / 2);
			c.setY(TetrisGameLogic.SIZE);
			d.setX(TetrisGameLogic.XMAX / 2 + TetrisGameLogic.SIZE);
			name = "t";
		} else if (block < 90) {
			a.setX(TetrisGameLogic.XMAX / 2 + TetrisGameLogic.SIZE);
			b.setX(TetrisGameLogic.XMAX / 2);
			c.setX(TetrisGameLogic.XMAX / 2 + TetrisGameLogic.SIZE);
			c.setY(TetrisGameLogic.SIZE);
			d.setX(TetrisGameLogic.XMAX / 2 + TetrisGameLogic.SIZE + TetrisGameLogic.SIZE);
			d.setY(TetrisGameLogic.SIZE);
			name = "z";
		} else {
			a.setX(TetrisGameLogic.XMAX / 2 - TetrisGameLogic.SIZE - TetrisGameLogic.SIZE);
			b.setX(TetrisGameLogic.XMAX / 2 - TetrisGameLogic.SIZE);
			c.setX(TetrisGameLogic.XMAX / 2);
			d.setX(TetrisGameLogic.XMAX / 2 + TetrisGameLogic.SIZE);
			name = "i";
		}
		return new Form(a, b, c, d, name);
	}

}