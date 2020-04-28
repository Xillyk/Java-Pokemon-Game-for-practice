import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class PokemonGameGUI extends JFrame {
    private Trainer trainer;

    public PokemonGameGUI() {
        super("Pokemon World");
        trainer = new Trainer();

        Container c = getContentPane();

        setSize(800,600);
        setVisible(true);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
        //mainPanel.setPreferredSize(new Dimension(700,500));

        JPanel titlePanel = new JPanel(); 
        JPanel subTitlePanel1 = new JPanel();
        JPanel subTitlePanel2 = new JPanel();

        titlePanel.setBackground(Color.blue);
        subTitlePanel1.setBackground(Color.blue);
        subTitlePanel2.setBackground(Color.blue);

        JLabel titleLabel = new JLabel("Pokemon World");
        JLabel subTitleLabel = new JLabel("Welcome New Pokemon Trainer");
        JLabel pressKeyLabel = new JLabel("Press Space to Start");

        titleLabel.setFont(new Font("Serif", Font.BOLD, 60));
        titleLabel.setForeground(Color.YELLOW);
        subTitleLabel.setFont(new Font("Serif", Font.BOLD, 40));
        subTitleLabel.setForeground(Color.CYAN);
        pressKeyLabel.setFont(new Font("Serif", Font.BOLD, 30));
        pressKeyLabel.setForeground(Color.orange);

        titlePanel.add(titleLabel);
        subTitlePanel1.add(subTitleLabel);
        subTitlePanel2.add(pressKeyLabel);

        mainPanel.add(titlePanel);
        mainPanel.add(subTitlePanel1);
        mainPanel.add(subTitlePanel2);

        c.add(mainPanel);
        
        
        addKeyListener(new KeyAdapter(){
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_SPACE) {
                    new CreateTrainerGUI(trainer);
                    //setVisible(false);
                    dispose();
                }
            }
        });
    }
    public static void main(String args[]) {
        new PokemonGameGUI();
    }
}