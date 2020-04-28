import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;

public class DungeonGUI extends JFrame {
    private ArrayList<Pokemon> pokemonBag;
    private Pokemon wildPokemon;
    private int pIndex;
    private String name;

    public DungeonGUI(Trainer trainer, Pokemon wildPokemon) {
        super("Pokemon World");
        System.out.println("Berry :" + trainer.getBag().getItemAmount("Berry") + "  Med : "
                + trainer.getBag().getItemAmount("Medicine"));

        pokemonBag = trainer.getPokemonBag();

        this.wildPokemon = wildPokemon;
        pIndex = trainer.getPokemonIndex();

        Container c = getContentPane();
        c.setLayout(new GridLayout(2, 2));
        setSize(1500, 900);
        setVisible(true);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // ### wild pokemon
        // panel----------------------------------------------------------------------------------------

        JPanel wildPokemonPanel = new JPanel();
        wildPokemonPanel.setLayout(new BoxLayout(wildPokemonPanel, BoxLayout.X_AXIS));
        wildPokemonPanel.setPreferredSize(new Dimension(1500, 450));

        // ## DUNGEON STATUS PANEL
        JPanel statusPanel = new JPanel();
        statusPanel.setPreferredSize(new Dimension(450, 450));
        statusPanel.setLayout(new BoxLayout(statusPanel, BoxLayout.Y_AXIS));

        JPanel statusPanelLayer1 = new JPanel();
        JPanel statusPanelLayer2 = new JPanel();
        JPanel statusPanelLayer3 = new JPanel();

        JLabel blankLabel = new JLabel("");
        blankLabel.setFont(new Font("Serif", Font.BOLD, 30));

        JLabel alertLabel = new JLabel("");
        alertLabel.setFont(new Font("Serif", Font.BOLD, 30));
        alertLabel.setForeground(Color.RED);

        JLabel alertLabel2 = new JLabel("");
        alertLabel2.setFont(new Font("Serif", Font.BOLD, 30));
        alertLabel2.setForeground(Color.RED);

        statusPanelLayer1.add(blankLabel);
        statusPanelLayer2.add(alertLabel);
        statusPanelLayer3.add(alertLabel2);

        statusPanel.add(statusPanelLayer1);
        statusPanel.add(statusPanelLayer2);
        statusPanel.add(statusPanelLayer3);

        // ## wild pokemon picture panel
        JPanel wildPokemonPicturePanel = new JPanel();
        wildPokemonPicturePanel.setPreferredSize(new Dimension(450, 450));
        JLabel wildPokemonPicLabel = new JLabel(
                new ImageIcon(this.getClass().getResource("pokemon/" + wildPokemon.getName() + ".png")));

        wildPokemonPicturePanel.add(wildPokemonPicLabel);

        // ## wild pokemon status panel
        JPanel wildPokemonStatusPanel = new JPanel();
        wildPokemonStatusPanel.setLayout(new BoxLayout(wildPokemonStatusPanel, BoxLayout.Y_AXIS));
        wildPokemonStatusPanel.setPreferredSize(new Dimension(450, 450));

        JPanel wildPokemonStatusPanelLayer1 = new JPanel();
        wildPokemonStatusPanelLayer1.setPreferredSize(new Dimension(400, 80));
        JPanel wildPokemonStatusPanelLayer2 = new JPanel();
        wildPokemonStatusPanelLayer2.setPreferredSize(new Dimension(400, 80));
        JPanel wildPokemonStatusPanelLayer3 = new JPanel();
        wildPokemonStatusPanelLayer3.setPreferredSize(new Dimension(400, 80));
        JPanel wildPokemonStatusPanelLayer4 = new JPanel();
        wildPokemonStatusPanelLayer4.setPreferredSize(new Dimension(400, 80));

        JLabel nickNameValueWildPokemonLabel = new JLabel();
        nickNameValueWildPokemonLabel.setText(wildPokemon.getNickName());
        nickNameValueWildPokemonLabel.setFont(new Font("Serif", Font.BOLD, 30));

        JLabel lvWildLebel = new JLabel("   Lv : ");
        lvWildLebel.setFont(new Font("Serif", Font.BOLD, 30));

        JLabel lvWildValueLebel = new JLabel();
        lvWildValueLebel.setText(String.valueOf(wildPokemon.getLevel()));
        lvWildValueLebel.setFont(new Font("Serif", Font.BOLD, 30));

        JLabel nameValueWildPokemonLabel = new JLabel();
        nameValueWildPokemonLabel.setText(wildPokemon.getName());
        nameValueWildPokemonLabel.setFont(new Font("Serif", Font.BOLD, 30));

        JLabel typeWildPokemonLabel = new JLabel("     Type :");
        typeWildPokemonLabel.setFont(new Font("Serif", Font.BOLD, 30));

        JLabel typeValueWildPokemonLabel = new JLabel();
        typeValueWildPokemonLabel.setText(wildPokemon.getType());
        typeValueWildPokemonLabel.setFont(new Font("Serif", Font.BOLD, 30));

        JLabel hpWildPokemonLabel = new JLabel("HP    ");
        hpWildPokemonLabel.setFont(new Font("Serif", Font.BOLD, 30));

        JLabel hgWildPokemonLabel = new JLabel("HGP    ");
        hgWildPokemonLabel.setFont(new Font("Serif", Font.BOLD, 30));

        // hp process bar
        JProgressBar hpWildBar = new JProgressBar();
        hpWildBar.setMaximum(wildPokemon.getMaxHP());
        hpWildBar.setMinimum(0);
        hpWildBar.setValue(wildPokemon.getHP());
        hpWildBar.setString(wildPokemon.getHPStatus());
        hpWildBar.setStringPainted(true);

        hpWildBar.setValue(wildPokemon.getHP());
        hpWildBar.setString(wildPokemon.getHPStatus());
        if (wildPokemon.getHPStatusForCheck() == 2) {
            hpWildBar.setForeground(Color.GREEN);
        } else if (wildPokemon.getHPStatusForCheck() == 1) {
            hpWildBar.setForeground(Color.ORANGE);
        } else if (wildPokemon.getHPStatusForCheck() == 0) {
            hpWildBar.setForeground(Color.RED);
        }

        // hg process bar
        JProgressBar hgWildBar = new JProgressBar();
        hgWildBar.setMaximum(wildPokemon.getMaxHungryPoint());
        hgWildBar.setMinimum(0);
        hgWildBar.setStringPainted(true);

        hgWildBar.setValue(wildPokemon.getHungryPoint());
        hgWildBar.setString(wildPokemon.getHGStatus());
        if (wildPokemon.getHGStatusForCheck() == 2) {
            hgWildBar.setForeground(Color.GREEN);
        } else if (wildPokemon.getHGStatusForCheck() == 1) {
            hgWildBar.setForeground(Color.ORANGE);
        } else if (wildPokemon.getHGStatusForCheck() == 0) {
            hgWildBar.setForeground(Color.RED);
        }

        wildPokemonStatusPanelLayer1.add(nickNameValueWildPokemonLabel);
        wildPokemonStatusPanelLayer1.add(lvWildLebel);
        wildPokemonStatusPanelLayer1.add(lvWildValueLebel);

        wildPokemonStatusPanelLayer2.add(nameValueWildPokemonLabel);
        wildPokemonStatusPanelLayer2.add(typeWildPokemonLabel);
        wildPokemonStatusPanelLayer2.add(typeValueWildPokemonLabel);

        wildPokemonStatusPanelLayer3.add(hpWildPokemonLabel);
        wildPokemonStatusPanelLayer3.add(hpWildBar);

        wildPokemonStatusPanelLayer4.add(hgWildPokemonLabel);
        wildPokemonStatusPanelLayer4.add(hgWildBar);

        wildPokemonStatusPanel.add(wildPokemonStatusPanelLayer1);
        wildPokemonStatusPanel.add(wildPokemonStatusPanelLayer2);
        wildPokemonStatusPanel.add(wildPokemonStatusPanelLayer3);
        wildPokemonStatusPanel.add(wildPokemonStatusPanelLayer4);

        wildPokemonPanel.add(statusPanel);
        wildPokemonPanel.add(wildPokemonStatusPanel);
        wildPokemonPanel.add(wildPokemonPicturePanel);

        // ### trainer's pokemon panel
        // ------------------------------------------------------------------------------------------------------------------
        JPanel trainerPokemonPanel = new JPanel();
        wildPokemonPanel.setLayout(new BoxLayout(wildPokemonPanel, BoxLayout.X_AXIS));
        trainerPokemonPanel.setPreferredSize(new Dimension(1500, 420));

        // ## trainer's pokemon picture panel
        JPanel trainerPokemonPicturePanel = new JPanel();
        trainerPokemonPicturePanel.setPreferredSize(new Dimension(450, 450));

        JLabel pokemonPicLabel = new JLabel(
                new ImageIcon(this.getClass().getResource("pokemon/" + pokemonBag.get(pIndex).getName() + ".png")));

        trainerPokemonPicturePanel.add(pokemonPicLabel);

        // ## trainer's pokemon status
        JPanel trainerPokemonStatusPanel = new JPanel();
        trainerPokemonStatusPanel.setLayout(new BoxLayout(trainerPokemonStatusPanel, BoxLayout.Y_AXIS));
        trainerPokemonStatusPanel.setPreferredSize(new Dimension(450, 450));

        JPanel trainerPokemonStatusPanelLayer1 = new JPanel();
        // trainerPokemonStatusPanelLayer1.setLayout(new FlowLayout());
        trainerPokemonStatusPanelLayer1.setPreferredSize(new Dimension(400, 80));
        JPanel trainerPokemonStatusPanelLayer2 = new JPanel();
        trainerPokemonStatusPanelLayer2.setPreferredSize(new Dimension(400, 80));
        JPanel trainerPokemonStatusPanelLayer3 = new JPanel();
        trainerPokemonStatusPanelLayer3.setPreferredSize(new Dimension(400, 80));
        JPanel trainerPokemonStatusPanelLayer4 = new JPanel();
        trainerPokemonStatusPanelLayer4.setPreferredSize(new Dimension(400, 80));
        JPanel trainerPokemonStatusPanelLayer5 = new JPanel();
        trainerPokemonStatusPanelLayer5.setPreferredSize(new Dimension(400, 80));

        JLabel nickNameValueLabel = new JLabel();
        nickNameValueLabel.setText(pokemonBag.get(pIndex).getNickName());
        nickNameValueLabel.setFont(new Font("Serif", Font.BOLD, 30));

        JLabel lvLebel = new JLabel("   Lv : ");
        lvLebel.setFont(new Font("Serif", Font.BOLD, 30));

        JLabel lvValueLebel = new JLabel();
        lvValueLebel.setText(String.valueOf(pokemonBag.get(pIndex).getLevel()));
        lvValueLebel.setFont(new Font("Serif", Font.BOLD, 30));

        JLabel nameValueLabel = new JLabel();
        nameValueLabel.setText(pokemonBag.get(pIndex).getName());
        nameValueLabel.setFont(new Font("Serif", Font.BOLD, 30));

        JLabel typeLabel = new JLabel("     Type :");
        typeLabel.setFont(new Font("Serif", Font.BOLD, 30));

        JLabel typeValueLabel = new JLabel();
        typeValueLabel.setText(pokemonBag.get(pIndex).getType());
        typeValueLabel.setFont(new Font("Serif", Font.BOLD, 30));

        JLabel hpLabel = new JLabel("HP     ");
        hpLabel.setFont(new Font("Serif", Font.BOLD, 30));

        // hp process bar
        JProgressBar hpBar = new JProgressBar();
        hpBar.setMaximum(pokemonBag.get(pIndex).getMaxHP());
        hpBar.setMinimum(0);
        hpBar.setValue(pokemonBag.get(pIndex).getHP());
        hpBar.setString(pokemonBag.get(pIndex).getHPStatus());
        hpBar.setStringPainted(true);

        if (pokemonBag.get(pIndex).getHPStatusForCheck() == 2) {
            hpBar.setForeground(Color.GREEN);
        } else if (pokemonBag.get(pIndex).getHPStatusForCheck() == 1) {
            hpBar.setForeground(Color.ORANGE);
        } else if (pokemonBag.get(pIndex).getHPStatusForCheck() == 0) {
            hpBar.setForeground(Color.RED);
        }

        JLabel hpValueLabel = new JLabel();
        hpValueLabel.setFont(new Font("Serif", Font.BOLD, 30));
        hpValueLabel.setText(pokemonBag.get(pIndex).getHPStatus());

        JLabel hgLabel = new JLabel("HGP   ");
        hgLabel.setFont(new Font("Serif", Font.BOLD, 30));

        // hg process bar
        JProgressBar hgBar = new JProgressBar();
        hgBar.setMaximum(pokemonBag.get(pIndex).getMaxHungryPoint());
        hgBar.setMinimum(0);
        hgBar.setStringPainted(true);

        hgBar.setValue(pokemonBag.get(pIndex).getHungryPoint());
        hgBar.setString(pokemonBag.get(pIndex).getHGStatus());
        if (pokemonBag.get(pIndex).getHGStatusForCheck() == 2) {
            hgBar.setForeground(Color.GREEN);
        } else if (pokemonBag.get(pIndex).getHGStatusForCheck() == 1) {
            hgBar.setForeground(Color.ORANGE);
        } else if (pokemonBag.get(pIndex).getHGStatusForCheck() == 0) {
            hgBar.setForeground(Color.RED);
        }

        JLabel spLabel = new JLabel("SP     ");
        spLabel.setFont(new Font("Serif", Font.BOLD, 30));
        // sp process bar
        JProgressBar spBar = new JProgressBar();
        spBar.setMaximum(pokemonBag.get(pIndex).getMaxSleepingPoint());
        spBar.setMinimum(0);
        spBar.setValue(pokemonBag.get(pIndex).getSleepPoint());
        spBar.setString(pokemonBag.get(pIndex).getSPStatus());
        spBar.setStringPainted(true);

        if (pokemonBag.get(pIndex).getSPStatusForCheck() == 2) {
            spBar.setForeground(Color.GREEN);
        } else if (pokemonBag.get(pIndex).getSPStatusForCheck() == 1) {
            spBar.setForeground(Color.ORANGE);
        } else if (pokemonBag.get(pIndex).getSPStatusForCheck() == 0) {
            spBar.setForeground(Color.RED);
        }

        trainerPokemonStatusPanelLayer1.add(nickNameValueLabel);
        trainerPokemonStatusPanelLayer1.add(lvLebel);
        trainerPokemonStatusPanelLayer1.add(lvValueLebel);

        trainerPokemonStatusPanelLayer2.add(nameValueLabel);
        trainerPokemonStatusPanelLayer2.add(typeLabel);
        trainerPokemonStatusPanelLayer2.add(typeValueLabel);

        trainerPokemonStatusPanelLayer3.add(hpLabel);
        trainerPokemonStatusPanelLayer3.add(hpBar);

        trainerPokemonStatusPanelLayer4.add(hgLabel);
        trainerPokemonStatusPanelLayer4.add(hgBar);

        trainerPokemonStatusPanelLayer5.add(spLabel);
        trainerPokemonStatusPanelLayer5.add(spBar);

        trainerPokemonStatusPanel.add(trainerPokemonStatusPanelLayer1);
        trainerPokemonStatusPanel.add(trainerPokemonStatusPanelLayer2);
        trainerPokemonStatusPanel.add(trainerPokemonStatusPanelLayer3);
        trainerPokemonStatusPanel.add(trainerPokemonStatusPanelLayer4);
        trainerPokemonStatusPanel.add(trainerPokemonStatusPanelLayer5);

        // ## option panel
        JPanel trainerOptionPanel = new JPanel();
        trainerOptionPanel.setLayout(new BoxLayout(trainerOptionPanel, BoxLayout.Y_AXIS));
        trainerOptionPanel.setPreferredSize(new Dimension(490, 450));

        JPanel trainerOptionPanelLayer1 = new JPanel();
        trainerOptionPanelLayer1.setPreferredSize(new Dimension(490, 100));
        JPanel trainerOptionPanelLayer2 = new JPanel();
        trainerOptionPanelLayer2.setPreferredSize(new Dimension(490, 100));
        JPanel trainerOptionPanelLayer3 = new JPanel();
        trainerOptionPanelLayer3.setPreferredSize(new Dimension(490, 100));

        JButton attackButton = new JButton("Attack");
        JButton useItemButton = new JButton("Use Item");
        JButton changePokemonButton = new JButton("Change Pokemon");
        JButton catchPokemonButton = new JButton("Catch Pokemon");
        JButton escapeButton = new JButton("Escape");

        attackButton.setPreferredSize(new Dimension(220, 100));
        useItemButton.setPreferredSize(new Dimension(220, 100));
        changePokemonButton.setPreferredSize(new Dimension(220, 100));
        catchPokemonButton.setPreferredSize(new Dimension(220, 100));
        escapeButton.setPreferredSize(new Dimension(220, 100));

        attackButton.setFont(new Font("Serif", Font.BOLD, 30));
        useItemButton.setFont(new Font("Serif", Font.BOLD, 30));
        changePokemonButton.setFont(new Font("Serif", Font.BOLD, 22));
        catchPokemonButton.setFont(new Font("Serif", Font.BOLD, 25));
        escapeButton.setFont(new Font("Serif", Font.BOLD, 30));

        attackButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // #### trainer's pokemon attack first
                alertLabel.setText("");
                alertLabel2.setText("");

                // check if pokemon is still alive and hg ,sp > 0
                if (!pokemonBag.get(pIndex).isDie() && pokemonBag.get(pIndex).getHungryPoint() > 0
                        && pokemonBag.get(pIndex).getSleepPoint() > 0) {
                    // trainer's pokemon attack first
                    pokemonBag.get(pIndex).attack(wildPokemon);
                    // ##set status
                    // #wild pokemon status

                    // set hp
                    hpWildBar.setValue(wildPokemon.getHP());
                    hpWildBar.setString(wildPokemon.getHPStatus());
                    if (wildPokemon.getHPStatusForCheck() == 2) {
                        hpWildBar.setForeground(Color.GREEN);
                    } else if (wildPokemon.getHPStatusForCheck() == 1) {
                        hpWildBar.setForeground(Color.ORANGE);
                    } else if (wildPokemon.getHPStatusForCheck() == 0) {
                        hpWildBar.setForeground(Color.RED);
                    }

                    hgWildBar.setValue(wildPokemon.getHungryPoint());
                    hgWildBar.setString(wildPokemon.getHGStatus());
                    if (wildPokemon.getHGStatusForCheck() == 2) {
                        hgWildBar.setForeground(Color.GREEN);
                    } else if (wildPokemon.getHGStatusForCheck() == 1) {
                        hgWildBar.setForeground(Color.ORANGE);
                    } else if (wildPokemon.getHGStatusForCheck() == 0) {
                        hgWildBar.setForeground(Color.RED);
                    }

                    // check if you attack and win
                    if (wildPokemon.isDie()) {
                        alertLabel.setText("You Win!!");
                        JOptionPane.showMessageDialog(c, "Come and Colect items", "You Win!",
                                JOptionPane.PLAIN_MESSAGE);
                        // update kill stat
                        trainer.successKill();
                        // check if pokemon level up and increase levelupcount
                        if (pokemonBag.get(pIndex).willLevelUp(wildPokemon.getExp()))
                            trainer.increaseLevelUpCount();
                        pokemonBag.get(pIndex).earnExp(wildPokemon.getExp());
                        new WinGUI(trainer, wildPokemon);
                        dispose();
                    } else if (wildPokemon.getHungryPoint() <= 0) {
                        alertLabel.setText(wildPokemon.getNickName() + " has 0 HGP, Attack it!");
                    } else {
                        // wild pokemon attack back
                        wildPokemon.attack(pokemonBag.get(pIndex));
                    }
                }

                // ##set status
                // #wild pokemon status

                // set hp
                hpWildBar.setValue(wildPokemon.getHP());
                hpWildBar.setString(wildPokemon.getHPStatus());
                if (wildPokemon.getHPStatusForCheck() == 2) {
                    hpWildBar.setForeground(Color.GREEN);
                } else if (wildPokemon.getHPStatusForCheck() == 1) {
                    hpWildBar.setForeground(Color.ORANGE);
                } else if (wildPokemon.getHPStatusForCheck() == 0) {
                    hpWildBar.setForeground(Color.RED);
                }

                hgWildBar.setValue(wildPokemon.getHungryPoint());
                hgWildBar.setString(wildPokemon.getHGStatus());
                if (wildPokemon.getHGStatusForCheck() == 2) {
                    hgWildBar.setForeground(Color.GREEN);
                } else if (wildPokemon.getHGStatusForCheck() == 1) {
                    hgWildBar.setForeground(Color.ORANGE);
                } else if (wildPokemon.getHGStatusForCheck() == 0) {
                    hgWildBar.setForeground(Color.RED);
                }

                // ##set status
                // #trainer's pokemon status

                // set hp
                hpBar.setValue(pokemonBag.get(pIndex).getHP());
                hpBar.setString(pokemonBag.get(pIndex).getHPStatus());
                if (pokemonBag.get(pIndex).getHPStatusForCheck() == 2) {
                    hpBar.setForeground(Color.GREEN);
                } else if (pokemonBag.get(pIndex).getHPStatusForCheck() == 1) {
                    hpBar.setForeground(Color.ORANGE);
                } else if (pokemonBag.get(pIndex).getHPStatusForCheck() == 0) {
                    hpBar.setForeground(Color.RED);
                }

                // set hg
                hgBar.setValue(pokemonBag.get(pIndex).getHungryPoint());
                hgBar.setString(pokemonBag.get(pIndex).getHGStatus());
                if (pokemonBag.get(pIndex).getHGStatusForCheck() == 2) {
                    hgBar.setForeground(Color.GREEN);
                } else if (pokemonBag.get(pIndex).getHGStatusForCheck() == 1) {
                    hgBar.setForeground(Color.ORANGE);
                } else if (pokemonBag.get(pIndex).getHGStatusForCheck() == 0) {
                    hgBar.setForeground(Color.RED);
                }

                // set sp
                spBar.setValue(pokemonBag.get(pIndex).getSleepPoint());
                spBar.setString(pokemonBag.get(pIndex).getSPStatus());
                if (pokemonBag.get(pIndex).getSPStatusForCheck() == 2) {
                    spBar.setForeground(Color.GREEN);
                } else if (pokemonBag.get(pIndex).getSPStatusForCheck() == 1) {
                    spBar.setForeground(Color.ORANGE);
                } else if (pokemonBag.get(pIndex).getSPStatusForCheck() == 0) {
                    spBar.setForeground(Color.RED);
                }
                // # end of trainer's pokemon status

                if (pokemonBag.get(pIndex).isDie()) {
                    alertLabel.setText(pokemonBag.get(pIndex).getNickName() + " is dead");
                    alertLabel2.setText("Change Pokemon -OR- Escape");
                }
            }
        });

        useItemButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                alertLabel.setText("");
                alertLabel2.setText("");
                // check if pokemon is die , so trainer cannot use item , trainer must change
                // pokemon before use item
                if (!pokemonBag.get(pIndex).isDie()) {
                    new UseItemGUI(trainer, wildPokemon);
                    dispose();
                } else {
                    // alert if pokemon is dead
                    JOptionPane.showMessageDialog(c,
                            "Because your pokemon is dead" + "\nYou have to change pokemon or Escape!",
                            "You cannot use item", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        changePokemonButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                new ChangePokemonGUI(trainer, wildPokemon);
                dispose();
            }
        });

        catchPokemonButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                alertLabel.setText("");
                alertLabel2.setText("");

                // check if bag is full
                if (trainer.getPokemonBag().size() < 10) {
                    // check is wild pokemon can catch
                    if (wildPokemon.isWeak()) {
                        alertLabel.setText("Catching Complete!");
                        while (true) {
                            String[] options = { "OK" };
                            JPanel panel = new JPanel();
                            JLabel lbl = new JLabel("Enter Your name: ");
                            JTextField txt = new JTextField(10);
                            panel.add(lbl);
                            panel.add(txt);
                            int selectedOption = JOptionPane.showOptionDialog(null, panel, "The Title",
                                    JOptionPane.NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, options, options[0]);

                            if (selectedOption == 0 && !txt.getText().equals("") && !txt.getText().equals(null)) {
                                String text = txt.getText();
                                // wildPokemon.setNickName(text);
                                wildPokemon.getCaught(text);
                                pokemonBag.add(wildPokemon);
                                new MainGameGUI(trainer);
                                dispose();
                                break;
                            }
                        }
                    } else {
                        // JOptionPane.showMessageDialog(c, wildPokemon.getNickName() + " is too strong,
                        // try attack more!", "Catching Incomplete", JOptionPane.ERROR_MESSAGE);
                        alertLabel.setText("It is too strong, try attack more!");
                    }
                } else {
                    alertLabel.setText("Your pokemon bag is full!");
                }
            }
        });

        escapeButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // alertLabel.setText("");
                // alertLabel2.setText("");
                int check = JOptionPane.showConfirmDialog(c, "Are you sure you want to escape?", "Trainer Escape",
                        JOptionPane.YES_NO_OPTION);
                if (check == JOptionPane.YES_OPTION) {
                    dispose();
                    new MainGameGUI(trainer);
                }
                // else do nothing
            }
        });

        trainerOptionPanelLayer1.add(attackButton);
        trainerOptionPanelLayer1.add(useItemButton);

        trainerOptionPanelLayer2.add(changePokemonButton);
        trainerOptionPanelLayer2.add(catchPokemonButton);

        trainerOptionPanelLayer3.add(escapeButton);

        trainerOptionPanel.add(trainerOptionPanelLayer1);
        trainerOptionPanel.add(trainerOptionPanelLayer2);
        trainerOptionPanel.add(trainerOptionPanelLayer3);

        // end of option panel

        trainerPokemonPanel.add(trainerPokemonPicturePanel);
        trainerPokemonPanel.add(trainerPokemonStatusPanel);
        trainerPokemonPanel.add(trainerOptionPanel);

        // add wild pokemon panel into container
        c.add(wildPokemonPanel);
        c.add(trainerPokemonPanel);

        // add trainer panel into container

        // modify
        wildPokemonPanel.setBackground(Color.yellow);
        trainerPokemonPanel.setBackground(Color.green);
    }
}