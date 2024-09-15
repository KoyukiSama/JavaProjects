import java.awt.Color;
import javax.swing.JFrame;

public class Frame extends JFrame {
    public Frame() {

		this.setTitle("Snake Game");
		//this.setIconImage();     // needs import javax.swing.ImageIcon

		this.setSize(500, 500);
		this.setVisible(true);

		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setResizable(false);

		this.getContentPane().setBackground(new Color(0xfecdb8));

	}

    public static void main(String[] args) {
        new Frame();
    }
}
