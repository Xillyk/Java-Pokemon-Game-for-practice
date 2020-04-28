import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;

public class GetFirstPokemonGUI extends JFrame {
    private Trainer trainer;
    private ArrayList<Pokemon> randPokemons;

    public GetFirstPokemonGUI(Trainer trainer) {
        super("Get Game");
        this.trainer = trainer;
        
        //random 3 pokemons
        this.randPokemons = PokemonRandomizer.getPokemons(3);

        //CONTAINER-----------------------------------------------------------------------------

        Container c = getContentPane();
        c.setLayout(new BoxLayout(c, BoxLayout.Y_AXIS));
        setSize(1500, 900);
        setVisible(true);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        //TITLE PANEL--------------------------------------------------------------------------

        JPanel titlePanel = new JPanel();
        titlePanel.setPreferredSize(new Dimension(1500,300));
        titlePanel.setLayout(new BoxLayout(titlePanel, BoxLayout.Y_AXIS));

        JPanel titlePanelLayer1 = new JPanel();
        JPanel titlePanelLayer2 = new JPanel();
        JPanel titlePanelLayer3 = new JPanel();

        JLabel titleLabel = new JLabel("Get Your First Pokemon");
        titleLabel.setFont(new Font("Serif", Font.BOLD, 40));

        JLabel nameLabel = new JLabel("Give it a name ");
        nameLabel.setFont(new Font("Serif", Font.BOLD, 30));

        JLabel nameAlertlabel = new JLabel("");
        nameAlertlabel.setFont(new Font("Serif", Font.BOLD, 35));
        nameAlertlabel.setForeground(Color.RED);

        JTextField nameTextField = new JTextField(30);

        titlePanelLayer1.add(titleLabel);
        titlePanelLayer2.add(nameLabel);
        titlePanelLayer2.add(nameTextField);
        titlePanelLayer3.add(nameAlertlabel);

        titlePanel.add(titlePanelLayer1);
        titlePanel.add(titlePanelLayer2);
        titlePanel.add(titlePanelLayer3);

        //RANDOM POKEMON PANEL--------------------------------------------------------------------------

        JPanel randomPokemonPanel = new JPanel();
        randomPokemonPanel.setPreferredSize(new Dimension(1500,600));
        randomPokemonPanel.setLayout(new BoxLayout(randomPokemonPanel, BoxLayout.X_AXIS));

        JPanel randomPokemonPanelLayer1 = new JPanel();
        randomPokemonPanelLayer1.setLayout(new BoxLayout(randomPokemonPanelLayer1, BoxLayout.Y_AXIS));
        JPanel randomPokemonPanelLayer2 = new JPanel();
        randomPokemonPanelLayer2.setLayout(new BoxLayout(randomPokemonPanelLayer2, BoxLayout.Y_AXIS));
        JPanel randomPokemonPanelLayer3 = new JPanel();
        randomPokemonPanelLayer3.setLayout(new BoxLayout(randomPokemonPanelLayer3, BoxLayout.Y_AXIS));

        JPanel picPanel1 = new JPanel();
        JPanel picPanel2 = new JPanel();
        JPanel picPanel3 = new JPanel();

        JLabel picLabel1 = new JLabel(new ImageIcon(this.getClass().getResource("pokemon/" + randPokemons.get(0).getName() + ".png")));
        JLabel picLabel2 = new JLabel(new ImageIcon(this.getClass().getResource("pokemon/" + randPokemons.get(1).getName() + ".png")));
        JLabel picLabel3 = new JLabel(new ImageIcon(this.getClass().getResource("pokemon/" + randPokemons.get(2).getName() + ".png")));

        picPanel1.add(picLabel1);
        picPanel2.add(picLabel2);
        picPanel3.add(picLabel3);

        JPanel btPanel1 = new JPanel();
        JPanel btPanel2 = new JPanel();
        JPanel btPanel3 = new JPanel();

        JButton selectPokemon1Button = new JButton();
        selectPokemon1Button.setText(randPokemons.get(0).getName());

        JButton selectPokemon2Button = new JButton();
        selectPokemon2Button.setText(randPokemons.get(1).getName());

        JButton selectPokemon3Button = new JButton();
        selectPokemon3Button.setText(randPokemons.get(2).getName());

        btPanel1.add(selectPokemon1Button);
        btPanel2.add(selectPokemon2Button);
        btPanel3.add(selectPokemon3Button);

        randomPokemonPanelLayer1.add(picPanel1);
        randomPokemonPanelLayer1.add(btPanel1);

        randomPokemonPanelLayer2.add(picPanel2);
        randomPokemonPanelLayer2.add(btPanel2);

        randomPokemonPanelLayer3.add(picPanel3);
        randomPokemonPanelLayer3.add(btPanel3);

        randomPokemonPanel.add(randomPokemonPanelLayer1);
        randomPokemonPanel.add(randomPokemonPanelLayer2);
        randomPokemonPanel.add(randomPokemonPanelLayer3);

        c.add(titlePanel);
        c.add(randomPokemonPanel);

        selectPokemon1Button.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (!nameTextField.getText().equals("") && !nameTextField.getText().equals(null)) {
                    // add pokemon into bag
                    randPokemons.get(0).setNickName(nameTextField.getText());
                    trainer.receivePokemonInFirstTime(randPokemons.get(0));
                    new MainGameGUI(trainer);
                    dispose();
                }
                else 
                    nameAlertlabel.setText("You must set your pokemon name!!");
            }
        });
        
        selectPokemon2Button.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (!nameTextField.getText().equals("") && !nameTextField.getText().equals(null)) {
                    // add pokemon into bag
                    randPokemons.get(1).setNickName(nameTextField.getText());
                    trainer.receivePokemonInFirstTime(randPokemons.get(1));
                    new MainGameGUI(trainer);
                    dispose();
                }
                else 
                    nameAlertlabel.setText("You must set your pokemon name!!");              
            }
        });

        selectPokemon3Button.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (!nameTextField.getText().equals("") && !nameTextField.getText().equals(null)) {
                    // add pokemon into bag
                    randPokemons.get(2).setNickName(nameTextField.getText());
                    trainer.receivePokemonInFirstTime(randPokemons.get(2));
                    new MainGameGUI(trainer);
                    dispose();
                }
                else 
                    nameAlertlabel.setText("You must set your pokemon name!!");               
            }
        });
    }

}