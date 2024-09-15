import java.awt.Color;
import java.awt.Font;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class Frame extends JFrame {
    private JLabel scoreLabel;

    public Frame() {

        ///// FRAME //////
		this.setTitle("Snake Game");
		//this.setIconImage();     // needs import javax.swing.ImageIcon

		this.setSize(500, 500);

		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setResizable(false);

		this.getContentPane().setBackground(new Color(0xfecdb8));

        ///// JPanel /////
        JPanel topBar = new JPanel();

        ///// JLABELS /////
        scoreLabel = new JLabel("SCORE: 0");
        scoreLabel.setFont(new Font("Monospaced", Font.BOLD, 18));

        topBar.add(scoreLabel);

        this.add(topBar);

        this.setVisible(true);

	}

    public static void main(String[] args) {
        new Frame();
    }
}
