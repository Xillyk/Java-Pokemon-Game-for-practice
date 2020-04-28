
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;

public class WinGUI extends JFrame {
    private Trainer trainer;
    private int pIndex;
    private ArrayList<Pokemon> pokemonBag;
    private Pokemon wildPokemon;

    public WinGUI(Trainer trainer, Pokemon wildPokemon) {
        super("You Win!");
        this.trainer = trainer;
        this.wildPokemon = wildPokemon;
        pIndex = trainer.getPokemonIndex();
        pokemonBag = trainer.getPokemonBag();

        //add items into bag
        int rand = (int) (Math.random() * 4);
        trainer.getBag().addItem(rand);
        int n = trainer.getBag().getNumItemInBag();

        //trainer receive coin
        trainer.receiveMoney(GameUtility.randomInt(50, 150));

        System.out.println(rand);
        for (int i = 0; i < n; i++) {
            System.out.println(trainer.getBag().getAllItem().get(i).getName());
        }
        
        // CONTAINER--------------------------------------------------------------------------------
        
        Container c = getContentPane();
        c.setLayout(new BoxLayout(c, BoxLayout.X_AXIS));
        setSize(800, 600);
        setVisible(true);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // MAIN PANEL---------------------------------------------------------------------------------

        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
        
        JPanel mainPanelLayer1 = new JPanel();
        JPanel mainPanelLayer2 = new JPanel();
        JPanel mainPanelLayer3 = new JPanel();
        JPanel mainPanelLayer4 = new JPanel();

        mainPanelLayer2.setLayout(new BoxLayout(mainPanelLayer2, BoxLayout.X_AXIS));

        JLabel titleLabel = new JLabel("You Win!");
        titleLabel.setFont(new Font("Serif", Font.BOLD, 30));
        
        // #PICTURE PANEL--------------------------------------------------------------------------

        JPanel picPanel = new JPanel();
        JLabel pPicLabel = new JLabel(new ImageIcon("pokemon/" + pokemonBag.get(pIndex).getName() + ".png"));
        
        picPanel.add(pPicLabel);

        // #DETAIL PANEL--------------------------------------------------------------------------

        JPanel detailPanel = new JPanel();
        detailPanel.setLayout(new BoxLayout(detailPanel, BoxLayout.Y_AXIS));
        JLabel pNameLabel = new JLabel(pokemonBag.get(pIndex).getNickName());
        pNameLabel.setFont(new Font("Serif", Font.BOLD, 20));
        JLabel lvLabel = new JLabel("Level   " + pokemonBag.get(pIndex).getLevel());
        lvLabel.setFont(new Font("Serif", Font.BOLD, 20));
        JLabel earnExpLabel = new JLabel("Earns " + wildPokemon.getExp() + " Exp");
        earnExpLabel.setFont(new Font("Serif", Font.BOLD, 20));

        JProgressBar expBar = new JProgressBar();
        expBar.setMaximum(pokemonBag.get(pIndex).getMaxExpPerLevel());
        expBar.setMinimum(0);
        expBar.setValue(pokemonBag.get(pIndex).getExp());
        expBar.setString(pokemonBag.get(pIndex).getEXPStatus());
        expBar.setStringPainted(true);

        detailPanel.add(pNameLabel);
        detailPanel.add(lvLabel);
        detailPanel.add(earnExpLabel);
        detailPanel.add(expBar);

        // #GET ITEM PANEL--------------------------------------------------------------------------

        JPanel getItemPanel = new JPanel();
        JLabel getItemlabel = new JLabel("+ You receive " + rand + " item +");
        getItemlabel.setFont(new Font("Serif", Font.BOLD, 25));
        
        getItemPanel.add(getItemlabel);
        
        // #BUTTON PANEL--------------------------------------------------------------------------

        JPanel btPanel = new JPanel();
        JButton bt1 = new JButton("Keep Explore");
        JButton bt2 = new JButton("Leave Dungeon");

        btPanel.add(bt1);
        btPanel.add(bt2);

        mainPanelLayer1.add(titleLabel);
        mainPanelLayer2.add(picPanel);
        mainPanelLayer2.add(detailPanel);
        mainPanelLayer3.add(getItemPanel);
        mainPanelLayer4.add(btPanel);

        mainPanel.add(mainPanelLayer1);
        mainPanel.add(mainPanelLayer2);
        mainPanel.add(mainPanelLayer3);
        mainPanel.add(mainPanelLayer4);

        c.add(mainPanel);

        bt1.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                Pokemon w;
                if (trainer.getLevelUpCount() < 3) {
                    w = PokemonRandomizer.getOnePokemon(trainer.getLevelUpCount(), 1);
                } else {
                    w = PokemonRandomizer.getOnePokemon(GameUtility.randomInt(trainer.getLevelUpCount() - 1, trainer.getLevelUpCount() + 1), 1);
                }
                new DungeonGUI(trainer, w);
                dispose();
            }
        });
        bt2.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                new MainGameGUI(trainer); 
                dispose();
            }
        });
    }
}