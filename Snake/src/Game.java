import java.awt.Color;
import java.awt.Font;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class Game extends JFrame {
    private JLabel scoreLabel;

    public Game() {

        ///// FRAME //////
		this.setTitle("Snake Game");
		//this.setIconImage();     // needs import javax.swing.ImageIcon
		this.setSize(500, 500);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setResizable(false);
		this.getContentPane().setBackground(new Color(0xfecdb8));


        GamePanel gamePanel = new GamePanel();
        this.add(gamePanel);

        ///// JPanel /////
        JPanel topBar = new JPanel();

        ///// JLABELS /////
        scoreLabel = new JLabel("SCORE: 0");
        scoreLabel.setFont(new Font("Monospaced", Font.BOLD, 18));

        topBar.add(scoreLabel);

        this.add(topBar);

        this.setVisible(true);

	}

    public void setScoreLabel(int score) {
        scoreLabel.setText("SCORE: " + score);
    }

}
