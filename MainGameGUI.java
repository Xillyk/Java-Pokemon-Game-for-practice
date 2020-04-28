import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

public class MainGameGUI extends JFrame {
    private Trainer trainer;

    public MainGameGUI(Trainer trainer) {
        super("Pokemon World");
        this.trainer = trainer;

        //CONTAINER-------------------------------------------------------------------------------------------------

        Container c = getContentPane();
        c.setLayout(new BoxLayout(c, BoxLayout.X_AXIS));
        setSize(1500, 900);
        setVisible(true);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        //TRAINER PANEL---------------------------------------------------------------------------------------------

        JPanel trainerViewPanel = new JPanel();
        trainerViewPanel.setLayout(new BoxLayout(trainerViewPanel, BoxLayout.Y_AXIS));

        if (trainer.getGender().equals("male")) {
            JLabel trainerPicLabel = new JLabel(new ImageIcon(this.getClass().getResource("trainer/male.gif")));
            trainerViewPanel.add(trainerPicLabel);
        } else if (trainer.getGender().equals("female")) {
            JLabel trainerPicLabel = new JLabel(new ImageIcon(this.getClass().getResource("trainer/female.gif")));
            trainerViewPanel.add(trainerPicLabel);
        }

        JPanel trainerSubPanel = new JPanel();
        trainerSubPanel.setLayout(new BoxLayout(trainerSubPanel, BoxLayout.Y_AXIS));

        JPanel trainerSubPanelLayer0 = new JPanel();
        JPanel trainerSubPanelLayer1 = new JPanel();
        JPanel trainerSubPanelLayer2 = new JPanel();
        JPanel trainerSubPanelLayer3 = new JPanel();
        JPanel trainerSubPanelLayer4 = new JPanel();


        JLabel tNameLabel = new JLabel("Name        :       " + trainer.getName());
        tNameLabel.setFont(new Font("Serif", Font.BOLD, 30));
        JLabel tMoneyLabel = new JLabel("Money      :       " + trainer.getAmountMoney());
        tMoneyLabel.setFont(new Font("Serif", Font.BOLD, 30));
        JLabel tPokemonNumLabel = new JLabel("Pokemon   :       " + trainer.getPokemonBag().size() + " / 10");
        tPokemonNumLabel.setFont(new Font("Serif", Font.BOLD, 30));
        JLabel tKillStatLabel = new JLabel("Eliminate    " + trainer.getKillStat() + "    Pokemon");
        tKillStatLabel.setFont(new Font("Serif", Font.BOLD, 30));


        trainerSubPanelLayer1.add(tNameLabel);
        trainerSubPanelLayer2.add(tMoneyLabel);
        trainerSubPanelLayer3.add(tPokemonNumLabel);
        trainerSubPanelLayer4.add(tKillStatLabel);

        trainerSubPanel.add(trainerSubPanelLayer0);
        trainerSubPanel.add(trainerSubPanelLayer1);
        trainerSubPanel.add(trainerSubPanelLayer2);
        trainerSubPanel.add(trainerSubPanelLayer3);
        trainerSubPanel.add(trainerSubPanelLayer4);

        trainerViewPanel.add(trainerSubPanel);

        //OPTION PANEL---------------------------------------------------------------------------------------------

        JPanel optionPanel = new JPanel(new GridLayout(3, 2, 10, 7));

        JButton goAdventureButton = new JButton("Go adventure");
        JButton feedPokemonButton = new JButton("Feed Pokemon");
        JButton restPokemonButton = new JButton("Get pokemon rest");
        JButton curePokemonButton = new JButton("Go to Pokemon Center");
        JButton openBagButton = new JButton("Open Bag");
        JButton bt = new JButton("Exit");

        goAdventureButton.setFont(new Font("Serif", Font.BOLD, 30));
        feedPokemonButton.setFont(new Font("Serif", Font.BOLD, 30));
        restPokemonButton.setFont(new Font("Serif", Font.BOLD, 30));
        curePokemonButton.setFont(new Font("Serif", Font.BOLD, 30));
        openBagButton.setFont(new Font("Serif", Font.BOLD, 30));
        bt.setFont(new Font("Serif", Font.BOLD, 30));

        optionPanel.add(goAdventureButton);
        optionPanel.add(feedPokemonButton);
        optionPanel.add(restPokemonButton);
        optionPanel.add(curePokemonButton);
        optionPanel.add(openBagButton);
        optionPanel.add(bt);

        c.add(trainerViewPanel);
        c.add(optionPanel);

        // # Action Listener
        goAdventureButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                Pokemon wildPokemon;
                if (trainer.getLevelUpCount() < 6) {
                    wildPokemon = PokemonRandomizer.getOnePokemon(trainer.getLevelUpCount(), 1);
                } else {
                    wildPokemon = PokemonRandomizer.getOnePokemon(GameUtility.randomInt(trainer.getLevelUpCount() - 1, trainer.getLevelUpCount() + 1), 1);
                }

                new DungeonGUI(trainer, wildPokemon);
                dispose();
            }
        });

        feedPokemonButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                //pay money 100 per pokemon
                int fee = 100 * trainer.getPokemonBag().size();
                int check = JOptionPane.showConfirmDialog(c, "You have to pay " + fee + " coins, \nAre you sure?", "Payment Confirmation", JOptionPane.YES_NO_OPTION);
                if (check == 0) {
                    if (trainer.hasEnoughMoney(fee)) {
                        for (int i = 0; i < trainer.getPokemonBag().size(); i++) {
                            trainer.getPokemonBag().get(i).eatBerry(100);    
                        }
                        trainer.pay(fee);
                        JOptionPane.showMessageDialog(c, "All pokemons have full HGP");
                        new MainGameGUI(trainer);
                        dispose();                    
                    } else {
                        JOptionPane.showMessageDialog(c, "You don't have enough money!");
                    }                    
                }
            }
        });

        restPokemonButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                for (int i = 0; i < trainer.getPokemonBag().size(); i++) {
                    trainer.getPokemonBag().get(i).sleep();
                }
                JOptionPane.showMessageDialog(c, "All pokemons have full SP");
            }
        });

        curePokemonButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                new PokemonCenterGUI(trainer);
                dispose();
            }
        });

        openBagButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String[] options = {"Pokemon Bag", "Item Bag"};
                int option = JOptionPane.showOptionDialog(c, "Select bag", "Option", JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, null, options, options[0]);
                new OpenBagGUI(trainer, option);
                dispose();
            }
        });

        bt.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });
    }
}