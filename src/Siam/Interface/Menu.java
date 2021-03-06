package Siam.Interface;

import Siam.Constantes;
import Siam.Enum.Theme;
import Siam.Jeu;
import Siam.Sons.Musique;
import Siam.Sons.SoundsLibrary;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;

public class Menu extends JFrame implements ActionListener, Constantes {

    private Jeu jeu;
    private OutilsFont outilsFont;
    private JFrame fenetre;
    private SoundsLibrary soundsLibrary;

    private JLabel titre;
    private JButton jouer;
    private JButton instructions;
    private JButton option;
    private JButton themeSuivant;
    private JButton scores;
    private JButton quitter;
    private JButton charger;
    private JLabel optionL;
    private JButton couperSon;
    private JButton retour;

    private JPanel panPrincipal;
    private JPanel panTitre;
    private JPanel panBoutons;
    private JPanel panVide;
    private JPanel panVide2;

    private Theme theme;
    private Musique musique;
    private boolean son;

    public Menu(Jeu jeu, JFrame fenetre, Theme theme, Musique musique, boolean son, SoundsLibrary soundsLibrary){
        this.jeu = jeu;
        this.fenetre = fenetre;
        this.musique = musique;
        this.son = son;
        outilsFont = new OutilsFont();
        this.theme = theme;

        this.soundsLibrary = soundsLibrary;
        fenetre.setSize(LARGEUR_FENETRE, HAUTEUR_FENETRE);
        fenetre.setLocationRelativeTo(null);
        fenetre.setResizable(false);
        fenetre.setTitle("Siam");
        fenetre.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        initMenu();
        lanceMenu();
        setControlMenu(this);
    }

    public void lanceMenu(){
        afficheMenu();
        fenetre.setVisible(true);
    }

    public void initMenu(){
        titre = new JLabel("Siam");
        jouer = new JButton("Jouer");
        charger = new JButton("Charger");
        instructions = new JButton("Regles");
        scores = new JButton("Scores");
        quitter = new JButton("Quitter");

        optionL = new JLabel("Options", SwingConstants.CENTER);
        retour = new JButton("Retour");
        option = new JButton("Options");
        if (son) couperSon = new JButton("Musique On");
        else couperSon = new JButton("Musique Off");
        themeSuivant = new JButton("Theme suivant");
    }

    public void initPanel(){
        panPrincipal = new JPanel();
        panTitre = new JPanel();
        panBoutons = new JPanel();
        panVide = new JPanel();
        panVide2 = new JPanel();

        panPrincipal.setOpaque(false);
        panTitre.setOpaque(false);
        panBoutons.setOpaque(false);
        panVide.setOpaque(false);
        panVide2.setOpaque(false);
    }

    public void afficheMenu(){
        initPanel();

        panTitre.add(titre);
        panBoutons.add(jouer);
        panBoutons.add(charger);
        panBoutons.add(scores);
        panBoutons.add(option);
        panBoutons.add(quitter);

        chargerImage();
        changerPolice();

        panBoutons.setLayout(new GridLayout(6, 1));
        panPrincipal.setLayout(new GridLayout(2, 2));

        panPrincipal.add(panTitre);
        panPrincipal.add(panVide);
        panPrincipal.add(panVide2);
        panPrincipal.add(panBoutons);

        fenetre.setContentPane(panPrincipal);
    }

    public void afficheMenuOption(){
        initPanel();

        panTitre.add(titre);
        panBoutons.add(optionL);
        panBoutons.add(instructions);
        panBoutons.add(themeSuivant);
        panBoutons.add(couperSon);
        panBoutons.add(retour);

        chargerImage();
        changerPolice();

        panBoutons.setLayout(new GridLayout(6, 1));
        panPrincipal.setLayout(new GridLayout(2, 2));

        panPrincipal.add(panTitre);
        panPrincipal.add(panVide);
        panPrincipal.add(panVide2);
        panPrincipal.add(panBoutons);

        fenetre.setContentPane(panPrincipal);
    }

    public void changerPolice(){
        switch (theme) {
            case STANDARD:
                outilsFont.changerFontJLabel(titre, 150, Color.orange, outilsFont.getStandardFontMenu());
                outilsFont.changerFontButton(jouer, 60, Color.orange, outilsFont.getStandardFontTexte());
                outilsFont.changerFontButton(charger, 60, Color.orange, outilsFont.getStandardFontTexte());
                outilsFont.changerFontButton(scores, 60, Color.orange, outilsFont.getStandardFontTexte());
                outilsFont.changerFontButton(option, 60, Color.orange, outilsFont.getStandardFontTexte());
                outilsFont.changerFontButton(quitter, 60, Color.orange, outilsFont.getStandardFontTexte());

                outilsFont.changerFontJLabel(optionL, 65, Color.orange, outilsFont.getStandardFontMenu());
                outilsFont.changerFontButton(instructions, 55, Color.orange, outilsFont.getStandardFontTexte());
                outilsFont.changerFontButton(couperSon, 55, Color.orange, outilsFont.getStandardFontTexte());
                outilsFont.changerFontButton(themeSuivant, 55, Color.orange, outilsFont.getStandardFontTexte());
                outilsFont.changerFontButton(retour, 55, Color.orange, outilsFont.getStandardFontTexte());
                break;
            case NOEL:
                outilsFont.changerFontJLabel(titre, 150, Color.red, outilsFont.getNoelFontMenu());
                outilsFont.changerFontButton(jouer, 70, Color.red, outilsFont.getNoelFontTexte());
                outilsFont.changerFontButton(charger, 70, Color.red, outilsFont.getNoelFontTexte());
                outilsFont.changerFontButton(scores, 70, Color.red, outilsFont.getNoelFontTexte());
                outilsFont.changerFontButton(option, 70, Color.red, outilsFont.getNoelFontTexte());
                outilsFont.changerFontButton(quitter, 70, Color.red, outilsFont.getNoelFontTexte());

                outilsFont.changerFontJLabel(optionL, 65, Color.red, outilsFont.getNoelFontMenu());
                outilsFont.changerFontButton(instructions, 65, Color.red, outilsFont.getNoelFontTexte());
                outilsFont.changerFontButton(couperSon, 65, Color.red, outilsFont.getNoelFontTexte());
                outilsFont.changerFontButton(themeSuivant, 65, Color.red, outilsFont.getNoelFontTexte());
                outilsFont.changerFontButton(retour, 65, Color.red, outilsFont.getNoelFontTexte());
                break;
            case STARWARS:
                outilsFont.changerFontJLabel(titre, 150, Color.yellow, outilsFont.getStarWarsTexte());
                outilsFont.changerFontButton(jouer, 50, Color.yellow, outilsFont.getStarWarsTexte());
                outilsFont.changerFontButton(charger, 50, Color.yellow, outilsFont.getStarWarsTexte());
                outilsFont.changerFontButton(scores, 50, Color.yellow, outilsFont.getStarWarsTexte());
                outilsFont.changerFontButton(option, 50, Color.yellow, outilsFont.getStarWarsTexte());
                outilsFont.changerFontButton(quitter, 50, Color.yellow, outilsFont.getStarWarsTexte());

                outilsFont.changerFontJLabel(optionL, 50, Color.yellow, outilsFont.getStarWarsTexte());
                outilsFont.changerFontButton(instructions, 50, Color.yellow, outilsFont.getStarWarsTexte());
                outilsFont.changerFontButton(couperSon, 50, Color.yellow, outilsFont.getStarWarsTexte());
                outilsFont.changerFontButton(themeSuivant, 50, Color.yellow, outilsFont.getStarWarsTexte());
                outilsFont.changerFontButton(retour, 50, Color.yellow, outilsFont.getStarWarsTexte());
                break;
        }
    }

    public void chargerImage(){
        panPrincipal = new JPanel() {
            BufferedImage image = ImageLibrairie.imageLibrairie.getImage(theme, "FondMenu");
            public void paintComponent(Graphics g) {
                super.paintComponent(g);
                fenetre.repaint();
                g.drawImage(image, 0, 0, LARGEUR_FENETRE, HAUTEUR_FENETRE, this);
            }
        };
    }

    public void setControlMenu(ActionListener listener){
        jouer.addActionListener(listener);
        charger.addActionListener(listener);
        instructions.addActionListener(listener);
        scores.addActionListener(listener);
        quitter.addActionListener(listener);
        retour.addActionListener(listener);
        option.addActionListener(listener);
        themeSuivant.addActionListener(listener);
        couperSon.addActionListener(listener);
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        Object source = e.getSource();
        soundsLibrary.playBouttonSound(theme);

        if(source == quitter) {
            System.exit(0);
        }
        else if(source == jouer) {
            new ChoixCamp(jeu, jeu.getFenetre(), theme, musique, son, soundsLibrary);
        }
        else if(source == instructions) {
            new Instructions(theme);
        }
        //else if(source == scores) {
            //TODO Afficher les scores
        //}
        else if(source == retour) {
            lanceMenu();
        }
        else if(source == charger){
            jeu.charger();
        }
        else if(source == option) {
            afficheMenuOption();
            fenetre.setVisible(true);
        }
        else if(source == themeSuivant) {
            switch (theme) {
                case STANDARD:
                    theme = Theme.NOEL;
                    break;
                case NOEL:
                    theme = Theme.STARWARS;
                    break;
                case STARWARS:
                    theme = Theme.STANDARD;
                    break;
            }
            jeu.setTheme(theme);
            if (son) {
                musique.arret();
                musique = new Musique(theme);
                musique.start();
            }
            afficheMenuOption();
            fenetre.setVisible(true);
        }
        else if(source == couperSon) {
            if (son){
                couperSon = new JButton("Musique Off");
                musique.arret();
                couperSon.addActionListener(this);
                son = false;
            }
            else{
                couperSon = new JButton("Musique On");
                musique = new Musique(theme);
                musique.start();
                couperSon.addActionListener(this);
                son = true;
            }
            afficheMenuOption();
            fenetre.setVisible(true);
        }
    }
}