import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;

public class PokemonCenterGUI extends JFrame {
    private ArrayList<Pokemon> pokemonBag;
    private Trainer trainer;
    private int pIndex;
    private int numPokemon;
    private int option; // 0 = heal , 1 = donate, 2 = not select
    private JLabel selectLabel;
    private Container c;

    public PokemonCenterGUI(Trainer trainer) {
        super("Pokemon Center");
        option = 2;
        this.trainer = trainer;
        pokemonBag = trainer.getPokemonBag();
        pIndex = trainer.getPokemonIndex();
        numPokemon = pokemonBag.size();

        c = getContentPane();
        c.setLayout(new BoxLayout(c, BoxLayout.Y_AXIS));
        setSize(1500, 900);
        setVisible(true);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel mainPanel = new JPanel();

        JLabel picLabel = new JLabel(new ImageIcon("etc/PokemonCenter.png"));

        JPanel subPanel = new JPanel();
        subPanel.setLayout(new BoxLayout(subPanel, BoxLayout.Y_AXIS));

        JPanel subPanelLayer1 = new JPanel();
        JPanel subPanelLayer2 = new JPanel();
        JPanel subPanelLayer3 = new JPanel();
        JPanel subPanelLayer4 = new JPanel();
        JPanel subPanelLayer5 = new JPanel();

        JLabel welcomLabel = new JLabel("Welcome to Pokemon Center");
        welcomLabel.setFont(new Font("Serif", Font.BOLD, 35));

        JLabel tMoneyLabel = new JLabel("Money : " + trainer.getAmountMoney() + " coins");
        tMoneyLabel.setFont(new Font("Serif", Font.BOLD, 25));
        tMoneyLabel.setForeground(Color.magenta);

        JLabel tDonateStatLabel = new JLabel("      Donate : " + trainer.getDonateStat());
        tDonateStatLabel.setFont(new Font("Serif", Font.BOLD, 25));
        tDonateStatLabel.setForeground(Color.magenta);

        JButton bt1 = new JButton("Heal a Pokemon");
        bt1.setFont(new Font("Serif", Font.BOLD, 30));

        JButton bt2 = new JButton("Donate a Pokemon");
        bt2.setFont(new Font("Serif", Font.BOLD, 30));

        JButton bt3 = new JButton("Leave");
        bt3.setFont(new Font("Serif", Font.BOLD, 30));

        JButton bt4 = new JButton("Heal All pokemons");
        bt4.setFont(new Font("Serif", Font.BOLD, 30));

        selectLabel = new JLabel("Please select option before select pokemon");
        selectLabel.setFont(new Font("Serif", Font.BOLD, 25));
        selectLabel.setForeground(Color.red);
        JPanel listPanel = new JPanel();

        ArrayList<JButton> bt = new ArrayList<JButton>();

        for (int i = 0; i < numPokemon; i++) {
            bt.add(new JButton(pokemonBag.get(i).getNickName()));
            bt.get(i).setFont(new Font("Serif", Font.BOLD, 30));
            listPanel.add(bt.get(i));
            bt.get(i).addActionListener(new Action(trainer, i, option));
        }

        subPanelLayer1.add(welcomLabel);
        subPanelLayer2.add(tMoneyLabel);
        subPanelLayer2.add(tDonateStatLabel);
        subPanelLayer3.add(bt1);
        subPanelLayer3.add(bt4);
        subPanelLayer3.add(bt2);
        subPanelLayer3.add(bt3);
        subPanelLayer4.add(selectLabel);
        subPanelLayer5.add(listPanel);

        subPanel.add(subPanelLayer1);
        subPanel.add(subPanelLayer2);
        subPanel.add(subPanelLayer3);
        subPanel.add(subPanelLayer4);
        subPanel.add(subPanelLayer5);

        mainPanel.add(picLabel);
        mainPanel.add(subPanel);

        c.add(mainPanel);

        bt1.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                option = 0;
                selectLabel.setText("You have selected Heal Pokemon");
                selectLabel.setForeground(Color.green);
            }
        });

        bt2.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                option = 1;
                selectLabel.setText("You have selected Donate Pokemon");
                selectLabel.setForeground(Color.green);
            }
        });

        bt3.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                new MainGameGUI(trainer);
                dispose();
            }
        });

        bt4.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                int fee = 100 * trainer.getPokemonBag().size();
                int check = JOptionPane.showConfirmDialog(c, "You have to pay " + fee + " coins \nAre you sure?",
                        "Payment Confirmation", JOptionPane.YES_NO_OPTION);
                if (check == 0) {
                    if (trainer.hasEnoughMoney(fee)) {
                        trainer.pay(fee);
                        for (int i = 0; i < trainer.getPokemonBag().size(); i++) {
                            trainer.getPokemonBag().get(i).getCured();
                        }
                        JOptionPane.showMessageDialog(c, "All pokemons have full HP");
                        new PokemonCenterGUI(trainer);
                        dispose();
                    } else {
                        JOptionPane.showMessageDialog(c, "You don't have enough money!");
                    }
                }
            }
        });
    }

    public class Action implements ActionListener {
        private ArrayList<Pokemon> pokemonBag;
        private Trainer trainer;
        private int pIndex;

        public Action(Trainer trainer, int index, int option) {
            pIndex = index;
            this.trainer = trainer;
            pokemonBag = trainer.getPokemonBag();
        }

        public void actionPerformed(ActionEvent e) {
            if (option == 0) {
                if (pokemonBag.get(pIndex).getHP() < pokemonBag.get(pIndex).getMaxHP()) {
                    int check = JOptionPane.showConfirmDialog(c, "You have to pay 100 coins \nAre you sure?",
                            "Payment Confirmation", JOptionPane.YES_NO_OPTION);
                    if (check == 0) {
                        if (trainer.hasEnoughMoney(100)) {
                            pokemonBag.get(pIndex).getCured();
                            trainer.pay(100);
                            JOptionPane.showMessageDialog(c, "Your pokemon has full HP");
                            new PokemonCenterGUI(trainer);
                            dispose();
                        } else {
                            // alert don't have enough money
                            JOptionPane.showMessageDialog(c, "You don't have enough money");
                        }
                    }
                } else {
                    // alert if pokemon hp is full
                    JOptionPane.showMessageDialog(c, "Your pokemon just has full HP");
                }
            } else if (option == 1) {
                int check = JOptionPane.showConfirmDialog(c, "Are you sure to donate this pokemon?",
                        "Donate Confirmation", JOptionPane.YES_NO_OPTION);
                if (check == 0) {
                    if (pokemonBag.size() != 1) {
                        trainer.donatePokemon(pIndex);
                        JOptionPane.showMessageDialog(c, "Thank you");
                        new PokemonCenterGUI(trainer);
                        dispose();
                    } else {
                        JOptionPane.showMessageDialog(c, "You have only 1 pokemon, you cannot donate!!");
                    }
                }
            } else if (option == 2) {
                selectLabel.setText("You must select option first!");
            }
        }
    }
}