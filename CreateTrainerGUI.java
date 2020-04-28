
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class CreateTrainerGUI extends JFrame {
    private Trainer trainer;

    public CreateTrainerGUI(Trainer trainer) {
        super("Pokemon World");
        this.trainer = trainer;
        
        //random items into bag
        int rand = (int) (Math.random() * 5);
        trainer.getBag().addItem(rand);
        System.out.println(rand);

        //trainer get money
        trainer.receiveMoney(1000);

        //CONTAINER-----------------------------------------------------------------------------

        Container c = getContentPane();
        c.setLayout(new BoxLayout(c, BoxLayout.Y_AXIS));
        setSize(1500, 900);
        setVisible(true);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocation((Toolkit.getDefaultToolkit().getScreenSize().width - getSize().width) / 2,
                (Toolkit.getDefaultToolkit().getScreenSize().height - getSize().height) / 2);

        JPanel p1 = new JPanel();
        p1.setLayout(new BoxLayout(p1, BoxLayout.Y_AXIS));

        // #SET NAME PANEL----------------------------------------------------------------------
        JPanel setNamePanel = new JPanel();
        setNamePanel.setLayout(new BoxLayout(setNamePanel, BoxLayout.Y_AXIS));

        JPanel setNamePanelLayer1 = new JPanel();
        JLabel lb1 = new JLabel("Create New Trainer");
        lb1.setFont(new Font("Serif", Font.BOLD, 40));

        setNamePanelLayer1.add(lb1);

        JPanel setNamePanelLayer2 = new JPanel();
        JLabel lb2 = new JLabel("Enter name ");
        lb2.setFont(new Font("Serif", Font.BOLD, 30));

        JTextField tf1 = new JTextField(30);

        JPanel setNamePanelLayer3 = new JPanel();
        JLabel alertlb = new JLabel("");
        alertlb.setFont(new Font("Serif", Font.BOLD, 35));
        alertlb.setForeground(Color.RED);

        setNamePanelLayer2.add(lb2);
        setNamePanelLayer2.add(tf1);
        setNamePanelLayer3.add(alertlb);

        setNamePanel.add(setNamePanelLayer1);
        setNamePanel.add(setNamePanelLayer2);
        setNamePanel.add(setNamePanelLayer3);

        // #SET GENDER PANEL------------------------------------------------------------------

        JPanel setGenderPanel = new JPanel();
        setGenderPanel.setLayout(new BoxLayout(setGenderPanel, BoxLayout.X_AXIS));

        JPanel setGenderMalePanel = new JPanel();
        setGenderMalePanel.setLayout(new BoxLayout(setGenderMalePanel, BoxLayout.Y_AXIS));

        JPanel setGenderMalePanel1 = new JPanel();
        JLabel maleTextLabel = new JLabel("Male");
        maleTextLabel.setFont(new Font("Serif", Font.BOLD, 25));
        maleTextLabel.setForeground(Color.magenta);

        setGenderMalePanel1.add(maleTextLabel);

        JPanel setGenderMalePanel2 = new JPanel();
        JLabel malePictureLabel = new JLabel(new ImageIcon(this.getClass().getResource("trainer/male.gif")));
        setGenderMalePanel2.add(malePictureLabel);

        JPanel setGenderMalePanel3 = new JPanel();
        JButton selectMaleButton = new JButton("Select Male");

        setGenderMalePanel3.add(selectMaleButton);

        setGenderMalePanel.add(setGenderMalePanel1);
        setGenderMalePanel.add(setGenderMalePanel2);
        setGenderMalePanel.add(setGenderMalePanel3);

        JPanel setGenderFemalePanel = new JPanel();
        setGenderFemalePanel.setLayout(new BoxLayout(setGenderFemalePanel, BoxLayout.Y_AXIS));

        JPanel setGenderFemalePanel1 = new JPanel();
        JLabel femaleTextLabel = new JLabel("Female");
        femaleTextLabel.setFont(new Font("Serif", Font.BOLD, 25));
        femaleTextLabel.setForeground(Color.magenta);

        setGenderFemalePanel1.add(femaleTextLabel);

        JPanel setGenderFemalePanel2 = new JPanel();
        JLabel femalePictureLabel = new JLabel(new ImageIcon(this.getClass().getResource("trainer/female.gif")));
        setGenderFemalePanel2.add(femalePictureLabel);

        JPanel setGenderFemalePanel3 = new JPanel();
        JButton selectFemaleButton = new JButton("Select Female");

        setGenderFemalePanel3.add(selectFemaleButton);

        setGenderFemalePanel.add(setGenderFemalePanel1);
        setGenderFemalePanel.add(setGenderFemalePanel2);
        setGenderFemalePanel.add(setGenderFemalePanel3);

        setGenderPanel.add(setGenderMalePanel);
        setGenderPanel.add(setGenderFemalePanel);

        // # container add
        c.add(setNamePanel);
        c.add(setGenderPanel);

        selectMaleButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (!tf1.getText().equals("") && !tf1.getText().equals(null)) {
                    trainer.setName(tf1.getText());
                    trainer.setGender("male");
                    new GetFirstPokemonGUI(trainer);
                    dispose();
                }
                else 
                    alertlb.setText("You must set your name!!");
            }
        });

        selectFemaleButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (!tf1.getText().equals("") && !tf1.getText().equals(null)) {
                    trainer.setName(tf1.getText());
                    trainer.setGender("female");
                    new GetFirstPokemonGUI(trainer);
                    dispose();
                }
                else 
                    alertlb.setText("You must set your name!!");
            }
        });
    }
}