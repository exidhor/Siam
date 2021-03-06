package Siam.Interface;

import Siam.*;
import Siam.Enum.Orientation;
import Siam.Enum.Theme;
import Siam.Sons.Musique;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;

public class VueJeu implements ActionListener, Constantes {

    private Jeu jeu;
    private JFrame fenetre;
    private OutilsFont outilsFont;
    private String pseudoJoueurActif;

    private DetectionSouris souris;

    private JLabel joueurActif;

    private JButton poser;
    private JButton deplacer;
    private JButton sortir;
    private JButton orienter;
    private JButton flecheHaut;
    private JButton flecheDroite;
    private JButton flecheGauche;
    private JButton flecheBas;

    private JMenuBar menuBar;
    private JMenu menu;
    private JMenu options;
    private JMenuItem nouvellepartie;
    private JMenuItem instructions;
    private JMenuItem retourMenu;
    private JMenuItem themeSuivant;
    private JMenuItem musique;
    private JMenuItem sauvegarder;

    public VueJeu(Jeu jeu, JFrame fenetre, DetectionSouris souris){
        this.jeu = jeu;
        this.fenetre = fenetre;
        this.souris = souris;

        outilsFont = new OutilsFont();

        pseudoJoueurActif = jeu.getJoueurActif().getPseudo();

        initVueJeu();
        affichageVueJeu();
        setControlBouton(this);

        fenetre.setTitle("Siam");
        fenetre.setResizable(false);
        fenetre.setLocationRelativeTo(null);
        fenetre.setVisible(true);
    }

    public void initVueJeu() {
        joueurActif = new JLabel(jeu.getJoueurActif().getPseudo());

        poser = new JButton("Poser une piece");
        deplacer = new JButton("Deplacer une piece");
        sortir = new JButton("Sortir une piece");
        orienter = new JButton("Orienter une piece");
        flecheHaut = new JButton("Haut");
        flecheDroite = new JButton("Droite");
        flecheGauche = new JButton("Gauche");
        flecheBas = new JButton("Bas");

        menu = new JMenu("Menu");
        options = new JMenu("Options");

        nouvellepartie = new JMenuItem("Nouvelle Partie");
        instructions = new JMenuItem("Regles");
        retourMenu = new JMenuItem("Retour au Menu");
        sauvegarder = new JMenuItem("Sauvegarder");

        themeSuivant = new JMenuItem("Theme Suivant");
        if (jeu.isSon()) musique = new JMenuItem("Musique On");
        else musique = new JMenuItem("Musique Off");
    }

    public void affichageVueJeu() {
        PanelPlateau panelPlateau = new PanelPlateau(jeu, souris);
        JPanel panelJeu = new JPanel();
        JPanel panelBouton = new JPanel();
        JPanel panelTexte = new JPanel();
        JPanel panelPoser = new JPanel();
        JPanel panelDeplacer = new JPanel();
        JPanel panelSortir = new JPanel();
        JPanel panelOrienter = new JPanel();
        JPanel panelHaut = new JPanel();
        JPanel panelFleche = new JPanel();
        JPanel panelGauche = new JPanel();
        JPanel panelDroite = new JPanel();
        JPanel panelBas = new JPanel();

        menuBar = new JMenuBar();

        menu.add(nouvellepartie);
        menu.add(sauvegarder);
        menu.add(instructions);
        menu.add(retourMenu);

        options.add(themeSuivant);
        options.add(musique);

        menuBar.add(menu);
        menuBar.add(options);

        panelJeu.setOpaque(false);
        panelBouton.setOpaque(false);
        panelTexte.setOpaque(false);
        panelPoser.setOpaque(false);
        panelDeplacer.setOpaque(false);
        panelSortir.setOpaque(false);
        panelOrienter.setOpaque(false);
        panelHaut.setOpaque(false);
        panelBas.setOpaque(false);
        panelFleche.setOpaque(false);
        panelGauche.setOpaque(false);
        panelDroite.setOpaque(false);

        panelJeu.add(panelPlateau);

        deplacer.setEnabled(false);
        sortir.setEnabled(false);
        orienter.setEnabled(false);
        flecheHaut.setEnabled(false);
        flecheBas.setEnabled(false);
        flecheGauche.setEnabled(false);
        flecheDroite.setEnabled(false);

        panelTexte.add(joueurActif);
        panelPoser.add(poser);
        panelDeplacer.add(deplacer);
        panelSortir.add(sortir);
        panelOrienter.add(orienter);

        panelFleche.setLayout(new BorderLayout());
        panelHaut.add(flecheHaut);
        panelGauche.add(flecheGauche);
        panelDroite.add(flecheDroite);
        panelFleche.add(panelDroite, BorderLayout.EAST);
        panelFleche.add(panelGauche, BorderLayout.WEST);
        panelBas.add(flecheBas);

        panelBouton = new JPanel() {
            BufferedImage image = ImageLibrairie.imageLibrairie.getImage(jeu.getTheme(), "FondBouton");
            public void paintComponent(Graphics g) {
                super.paintComponent(g);
                fenetre.repaint();
                g.drawImage(image, 0, 0, 350, HAUTEUR_FENETRE, this);
            }
        };

        changerPolice();

        panelBouton.setPreferredSize(new Dimension(300,HAUTEUR_AFFICHAGE_PLATEAU));
        panelBouton.add(panelTexte);
        panelBouton.add(panelPoser);
        panelBouton.add(panelDeplacer);
        panelBouton.add(panelSortir);
        panelBouton.add(panelOrienter);
        panelBouton.add(panelHaut);
        panelBouton.add(panelFleche);
        panelBouton.add(panelBas);
        panelBouton.setLayout(new GridLayout(8, 1));
        panelJeu.add(panelBouton);

        panelJeu.setLayout(new BoxLayout(panelJeu, BoxLayout.X_AXIS));
        fenetre.setJMenuBar(menuBar);
        fenetre.setContentPane(panelJeu);
    }

    private void changerPolice() {
        switch (jeu.getTheme()) {
            case STANDARD:
                outilsFont.changerFontJLabel(joueurActif, 30, Color.orange, outilsFont.getStandardFontTexte());
                outilsFont.changerFontButton(poser, 30, Color.orange, outilsFont.getStandardFontTexte());
                outilsFont.changerFontButton(deplacer, 30, Color.orange, outilsFont.getStandardFontTexte());
                outilsFont.changerFontButton(sortir, 30, Color.orange, outilsFont.getStandardFontTexte());
                outilsFont.changerFontButton(orienter, 30, Color.orange, outilsFont.getStandardFontTexte());
                outilsFont.changerFontButton(flecheHaut, 30, Color.orange, outilsFont.getStandardFontTexte());
                outilsFont.changerFontButton(flecheBas, 30, Color.orange, outilsFont.getStandardFontTexte());
                outilsFont.changerFontButton(flecheGauche, 30, Color.orange, outilsFont.getStandardFontTexte());
                outilsFont.changerFontButton(flecheDroite, 30, Color.orange, outilsFont.getStandardFontTexte());
                break;
            case NOEL:
                outilsFont.changerFontJLabel(joueurActif, 30, Color.red, outilsFont.getStandardFontTexte());
                outilsFont.changerFontButton(poser, 30, Color.red, outilsFont.getStandardFontTexte());
                outilsFont.changerFontButton(deplacer, 30, Color.red, outilsFont.getStandardFontTexte());
                outilsFont.changerFontButton(sortir, 30, Color.red, outilsFont.getStandardFontTexte());
                outilsFont.changerFontButton(orienter, 30, Color.red, outilsFont.getStandardFontTexte());
                outilsFont.changerFontButton(flecheHaut, 30, Color.red, outilsFont.getStandardFontTexte());
                outilsFont.changerFontButton(flecheBas, 30, Color.red, outilsFont.getStandardFontTexte());
                outilsFont.changerFontButton(flecheGauche, 30, Color.red, outilsFont.getStandardFontTexte());
                outilsFont.changerFontButton(flecheDroite, 30, Color.red, outilsFont.getStandardFontTexte());
                break;
            case STARWARS:
                outilsFont.changerFontJLabel(joueurActif, 20, Color.yellow, outilsFont.getStarWarsTexte());
                outilsFont.changerFontButton(poser, 20, Color.yellow, outilsFont.getStarWarsTexte());
                outilsFont.changerFontButton(deplacer, 20, Color.yellow, outilsFont.getStarWarsTexte());
                outilsFont.changerFontButton(sortir, 20, Color.yellow, outilsFont.getStarWarsTexte());
                outilsFont.changerFontButton(orienter, 20, Color.yellow, outilsFont.getStarWarsTexte());
                outilsFont.changerFontButton(flecheHaut, 20, Color.yellow, outilsFont.getStarWarsTexte());
                outilsFont.changerFontButton(flecheBas, 20, Color.yellow, outilsFont.getStarWarsTexte());
                outilsFont.changerFontButton(flecheGauche, 20, Color.yellow, outilsFont.getStarWarsTexte());
                outilsFont.changerFontButton(flecheDroite, 20, Color.yellow, outilsFont.getStarWarsTexte());
                break;
        }
    }

    public void setControlBouton(ActionListener actionListener) {
        poser.addActionListener(actionListener);
        deplacer.addActionListener(actionListener);
        sortir.addActionListener(actionListener);
        orienter.addActionListener(actionListener);
        flecheHaut.addActionListener(actionListener);
        flecheBas.addActionListener(actionListener);
        flecheDroite.addActionListener(actionListener);
        flecheGauche.addActionListener(actionListener);

        nouvellepartie.addActionListener(actionListener);
        instructions.addActionListener(actionListener);
        retourMenu.addActionListener(actionListener);
        sauvegarder.addActionListener(actionListener);
        themeSuivant.addActionListener(actionListener);
        musique.addActionListener(actionListener);
    }

    public void changerJoueurActif() {
        joueurActif.setText(jeu.getJoueurActif().getPseudo());
    }

    @Override
    public void actionPerformed(ActionEvent event) {
        Object source = event.getSource();
        jeu.getSoundsLibrary().playControlSound(jeu.getTheme());
        
        if (source == poser && !jeu.isSelectionnerOrientation()) {
            jeu.setPlacerPiece(true);
            if (jeu.getAnimalSelectionnee() != null) jeu.deselection();
        }
        else if (source == sortir && jeu.isPieceSelectionnee())
        {
            if (jeu.getAnimalSelectionnee().getAbscisse() == 0 ||
                    jeu.getAnimalSelectionnee().getAbscisse() == 4 ||
                    jeu.getAnimalSelectionnee().getOrdonnee() == 0 ||
                    jeu.getAnimalSelectionnee().getOrdonnee() == 4) {
                jeu.getSoundsLibrary().playSortieSound(jeu.getTheme());
                jeu.setSortirPiece(true);
                jeu.getJoueurActif().sortirPiece(jeu.getAnimalSelectionnee().getAbscisse(),
                        jeu.getAnimalSelectionnee().getOrdonnee());
                jeu.changerJoueurActif();
            } else {
                jeu.getAnimalSelectionnee().setSelectionnee(false);
            }
            jeu.setPieceSelectionnee(false);
            jeu.setAnimalSelectionnee(null);
            jeu.setSelectionnerOrientation(false);
            jeu.setSortirPiece(false);
        }
        else if (source == deplacer && jeu.isPieceSelectionnee())
        {
            jeu.setDeplacerPiece(true);
            jeu.setChangerOrientation(false);
            jeu.setSelectionnerOrientation(false);
        }
        else if (source == orienter && jeu.isPieceSelectionnee())
        {
            jeu.setDeplacerPiece(false);
            jeu.setChangerOrientation(true);
            jeu.setSelectionnerOrientation(true);
        }
        else if (jeu.isSelectionnerOrientation() && source == flecheHaut)
        {
            jeu.getSoundsLibrary().playOrientationSound(jeu.getTheme());
            jeu.getAnimalSelectionnee().setOrientation(Orientation.HAUT);
            jeu.deselection();
            jeu.setEnCoursDeDeplacement(false);
            if (jeu.isChangerOrientation()) jeu.setChangerOrientation(false);
            jeu.changerJoueurActif();
        }
        else if (jeu.isSelectionnerOrientation() && source == flecheDroite)
        {
            jeu.getSoundsLibrary().playOrientationSound(jeu.getTheme());
            jeu.getAnimalSelectionnee().setOrientation(Orientation.DROITE);
            jeu.deselection();
            jeu.setEnCoursDeDeplacement(false);
            if (jeu.isChangerOrientation()) jeu.setChangerOrientation(false);
            jeu.changerJoueurActif();
        }
        else if (jeu.isSelectionnerOrientation() && source == flecheBas)
        {
            jeu.getSoundsLibrary().playOrientationSound(jeu.getTheme());
            jeu.getAnimalSelectionnee().setOrientation(Orientation.BAS);
            jeu.deselection();
            jeu.setEnCoursDeDeplacement(false);
            if (jeu.isChangerOrientation()) jeu.setChangerOrientation(false);
            jeu.changerJoueurActif();
        }
        else if (jeu.isSelectionnerOrientation() && source == flecheGauche)
        {
            jeu.getSoundsLibrary().playOrientationSound(jeu.getTheme());
            jeu.getAnimalSelectionnee().setOrientation(Orientation.GAUCHE);
            jeu.deselection();
            jeu.setEnCoursDeDeplacement(false);
            if (jeu.isChangerOrientation()) jeu.setChangerOrientation(false);
            jeu.changerJoueurActif();
        }
        else if (source == nouvellepartie)
        {
            menuBar.removeAll();
            new ChoixCamp(jeu, fenetre, jeu.getTheme(), jeu.getMusique(), jeu.isSon(), jeu.getSoundsLibrary());
        }
        else if (source == instructions)
        {
            new Instructions(jeu.getTheme());
        }
        else if (source == retourMenu)
        {
            menuBar.removeAll();
            new Menu(jeu, fenetre, jeu.getTheme(), jeu.getMusique(), jeu.isSon(), jeu.getSoundsLibrary());
        }
        else if (source == sauvegarder){
            jeu.sauvegarder();
        }
        else if (source == themeSuivant)
        {
            switch (jeu.getTheme()) {
                case STANDARD:
                    jeu.setTheme(Theme.NOEL);
                    break;
                case NOEL:
                    jeu.setTheme(Theme.STARWARS);
                    break;
                case STARWARS:
                    jeu.setTheme(Theme.STANDARD);
                    break;
            }
            if (jeu.isSon()) {
                jeu.getMusique().arret();
                jeu.setMusique(new Musique(jeu.getTheme()));
                jeu.getMusique().start();
            }
            affichageVueJeu();
            fenetre.setVisible(true);
        }
        else if (source == musique)
        {
            if (jeu.isSon())
            {
                musique.setText("Musique Off");
                jeu.getMusique().arret();
                jeu.setSon(false);
            }
            else
            {
                musique.setText("Musique On");
                jeu.setMusique(new Musique(jeu.getTheme()));
                jeu.getMusique().start();
                jeu.setSon(true);
            }
            affichageVueJeu();
            fenetre.setVisible(true);
        }
    }

    public JButton getPoser() {
        return poser;
    }

    public JButton getDeplacer() {
        return deplacer;
    }

    public JButton getSortir() {
        return sortir;
    }

    public JButton getOrienter() {
        return orienter;
    }

    public JButton getFlecheHaut() {
        return flecheHaut;
    }

    public JButton getFlecheDroite() {
        return flecheDroite;
    }

    public JButton getFlecheGauche() {
        return flecheGauche;
    }

    public JButton getFlecheBas() {
        return flecheBas;
    }

    public JMenuBar getMenuBar() {
        return menuBar;
    }
}
