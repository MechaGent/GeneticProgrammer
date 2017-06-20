package EnemyLevelScaling.GeneticProgrammer.Mk7;

	import java.awt.Graphics;
	import javax.swing.JFrame;
	import javax.swing.JPanel;

	public class JPanelTest extends JPanel {

	    /**
		 * 
		 */
		private static final long serialVersionUID = -5168400749343766685L;

		@Override
	    public void paintComponent(Graphics g) {
	        // Draw Tree Here
	        g.drawOval(5, 5, 25, 25);
	    }

	    public static void main(String[] args) {
	        JFrame jFrame = new JFrame();
	        jFrame.add(new JPanelTest());
	        jFrame.setSize(500, 500);
	        jFrame.setVisible(true);
	    }

	}
