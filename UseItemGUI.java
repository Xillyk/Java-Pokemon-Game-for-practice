import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;

public class UseItemGUI extends JFrame {
    private Trainer trainer;
    private ArrayList<Pokemon> pokemonBag;
    private Bag itemBag;
    private int pIndex;

    private JScrollPane s = new JScrollPane();

    public UseItemGUI(Trainer trainer, Pokemon wildPokemon) {
        super("Use Item");
        this.trainer = trainer;

        pokemonBag = trainer.getPokemonBag();
        itemBag = trainer.getBag();
        pIndex = trainer.getPokemonIndex();

        int itemName = itemBag.getNumItemNames();

        setSize(1000, 600);
        setVisible(true);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        add(s);

        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
        JButton leaveButton = new JButton("Leave");
        mainPanel.add(leaveButton);

        leaveButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                new DungeonGUI(trainer, wildPokemon);
                dispose();
            }
        });

        s.setViewportView(mainPanel);

        JPanel[] arrPanels = new JPanel[itemName];
        JLabel[] itemPic = new JLabel[itemName];
        JLabel[] itemNameLabel = new JLabel[itemName];
        ArrayList<JButton> bt = new ArrayList<JButton>();

        for (int i = 0; i < itemName; i++) {
            arrPanels[i] = createPanel();
            itemPic[i] = createPicLabel(i);
            itemNameLabel[i] = createTextLabel(i);
            bt.add(new JButton("Select"));

            arrPanels[i].add(itemPic[i]);
            arrPanels[i].add(itemNameLabel[i]);
            arrPanels[i].add(bt.get(i));

            bt.get(i).addActionListener(new UseItemInBag(trainer, i, wildPokemon));
            mainPanel.add(arrPanels[i]);
        }
    }

    private JPanel createPanel() {
        JPanel panel = new JPanel();
        panel.setPreferredSize(new Dimension(950, 300));
        // JLabel lb = new JLabel("x");
        // panel.add(lb);
        return panel;
    }

    private JLabel createPicLabel(int i) {
        JLabel picLabel = new JLabel(
                new ImageIcon(this.getClass().getResource("item/" + itemBag.getNames().get(i) + ".png")));
        return picLabel;
    }

    private JLabel createTextLabel(int i) {
        JLabel nameLabel = new JLabel(
                itemBag.getNames().get(i) + "    X    " + itemBag.getItemAmount(itemBag.getNames().get(i)) + "  ");
                nameLabel.setFont(new Font("Serif", Font.BOLD, 35));
        return nameLabel;
    }

    public class UseItemInBag implements ActionListener {
        private Trainer trainer;
        private Pokemon wildPokemon;
        private int index;

        public UseItemInBag(Trainer trainer, int index, Pokemon wildPokemon) {
            this.index = index;
            this.trainer = trainer;
            this.wildPokemon = wildPokemon;
        }

        public void actionPerformed(ActionEvent e) {
            //### if game has more item ->  use switch case
            if (index == 0) {
                // feeding

                // check amount
                if (itemBag.getItemAmount(itemBag.getNames().get(index)) > 0) {
                    // check is pokemon hgp still not full
                    if (pokemonBag.get(pIndex).getHungryPoint() < pokemonBag.get(pIndex).getMaxHungryPoint()) {
                        try {
                            pokemonBag.get(pIndex)
                                    .eatBerry(itemBag.getItem(itemBag.getNames().get(index)).getFeedValue());
                            // remove item
                            itemBag.removeItem(itemBag.getNames().get(index));
                            new DungeonGUI(trainer, wildPokemon);
                            dispose();
                        } catch (NullPointerException exception) {
                            System.out.println("NULl EXCEPTION");
                        }
                    } else {
                        // alert if pokemon hgp is full
                        JOptionPane.showMessageDialog(s, "Because pokemon HGP is full ", "You cannot use item",
                                JOptionPane.ERROR_MESSAGE);
                    }
                } else {
                    // alert if run out of item
                    JOptionPane.showMessageDialog(s, "Item is run out", "Error", JOptionPane.ERROR_MESSAGE);
                }

            } else if (index == 1) {
                // healing
                // check amount
                if (itemBag.getItemAmount(itemBag.getNames().get(index)) > 0) {
                    // check is pokemon hgp still not full
                    if (pokemonBag.get(pIndex).getHP() < pokemonBag.get(pIndex).getMaxHP()) {
                        try {
                            pokemonBag.get(pIndex)
                                    .regenHealth(itemBag.getItem(itemBag.getNames().get(index)).getHealValue());
                            // remove item
                            itemBag.removeItem(itemBag.getNames().get(index));

                            new DungeonGUI(trainer, wildPokemon);
                            dispose();
                        } catch (NullPointerException exception) {
                            System.out.println("NULLLL EXCEPTION");
                        }
                    } else {
                        // alert if pokemon hgp is full
                        JOptionPane.showMessageDialog(s, "Because pokemon HP is full ", "You cannot use item",
                                JOptionPane.ERROR_MESSAGE);
                        // new DungeonGUI(trainer, wildPokemon);
                        // dispose();
                    }
                } else {
                    JOptionPane.showMessageDialog(s, "Item is run out", "Error", JOptionPane.ERROR_MESSAGE);
                    // new DungeonGUI(trainer, wildPokemon);
                    // dispose();
                }
            }
        }
    }
}