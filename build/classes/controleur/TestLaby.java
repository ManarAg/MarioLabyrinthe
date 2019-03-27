/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package controleur;

import java.awt.Color;
import java.io.File;
import java.io.IOException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFileChooser;
import modele.*;
import vue.LabyConsole;
import vue.LabyGraphique;
import javax.swing.*; 
import java.awt.event.*;
import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;

import java.io.File; 

/**
 *
 * @author segado
 */
public class TestLaby implements ActionListener {

    private static Labyrinthe laby;
    private static LabyConsole console;
    
    // définir et instancier un objet de LabyGraphique
    private static LabyGraphique fen = new LabyGraphique(); 

    /**
     * Constructeur qui initialise le labyrinthe à partir du fichier en
     * paramètre
     *
     * @param fic : fichier du labyrinthe
     * @throws FileFormatException : problème de format de ficher
     */
    public TestLaby(File fic) throws FileFormatException {
        //laby = new Labyrinthe(fic);
        File pfile = new File("");
        //Selectionner un fichier (labyrinthe.txt) en mode graphique
        JFileChooser choixF = new JFileChooser();
        FileNameExtensionFilter ff = new FileNameExtensionFilter("Text files", "txt");
        choixF.setFileFilter(ff);
        int retour = choixF.showOpenDialog(null);
        if (retour == JFileChooser.APPROVE_OPTION){
            pfile = choixF.getSelectedFile();
            try {
				System.out.println("Etape 2.0 - chemin fichier : " + pfile.getCanonicalPath());
				
				System.out.println("Etape 2.1 fin creation du labyrinthe ");
				
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
        }
        laby = new Labyrinthe(pfile);
        
        System.out.println("Etape 3");
    }

    /**
     * @param cheminRepertoire
     * @param nomFichier
     * @throws FileFormatException
     */
    public TestLaby(String cheminRepertoire, String nomFichier) throws FileFormatException {
    	String cheminFichier = cheminRepertoire + "//" + nomFichier;
    	System.out.println("Etape 1.0 - chemin fichier : " + cheminFichier);
    	File pfile = new File(cheminFichier);
    	try {
			System.out.println("Etape 2.0 - chemin fichier : " + pfile.getCanonicalPath());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        laby = new Labyrinthe(pfile);
        System.out.println("Etape 3");
    }
    
    /**
     * Déplacement récursif en profondeur dans le labyrinthe
     *
     * @param ligne de la case
     * @param colonne de la case
     * @return d'un booléen d'arrêt du déplacement
     */
    public boolean deplacerDFS(int ligne, int colonne) {
        boolean stop = false;
        Case macase;

        // Si la sortie, on s'arrête
        if (colonne == laby.getArriveeX() && ligne == laby.getArriveeY()) {
            System.out.println("ARRIVEE");
            stop = true;
        } else {
            // visiter la case
            macase = laby.getCase(ligne, colonne);
            macase.setVisited();

            // afficher position de la case visitée et le labyrinthe
            console.affiche(macase); 
            
            // visiter récursivemet tous les voisins non marqués de macase
            for (int i = 0; i < macase.getNbVoisins(); i++) {
                Case voisin = macase.getVoisin(i);
                if (!stop && !voisin.getVisited()) {
                    stop = deplacerDFS(voisin.getPositionY(), voisin.getPositionX());
                }
            }
        }
        return stop;
    }

    /**
     * Déplacement aléatoire dans le labyrinthe
     *
     * @return d'un booléen d'arrêt du déplacement 
     */
    public boolean deplacerAuto() {
        boolean stop = false;
        Case macase;

        while (!stop) {
            try {
                // se déplacer aléatoirement
                laby.autoMove();

                // afficher position de la case visitée et le labyrinthe
                macase = laby.getCase(laby.getCurrentPositionY(), laby.getCurrentPositionX());
   
                // Si la sortie, on s'arrête
                if (laby.getCurrentPositionX() == laby.getArriveeX() && laby.getCurrentPositionY() == laby.getArriveeY()) {
                    System.out.println("ARRIVEE");
                    stop = true;
                }
            } catch (ImpossibleMoveException ex) {
            }
        }
        return stop;
    }

    //public void 
    public static void main(String[] args) {
        try {
            //fen.setVisible (true);           
            console = new LabyConsole(); //  instancier la console            
            TestLaby test;

            System.out.println("Entrez le nom du fichier du labyrinthe :");
            
            // Appel rapide pour les tests
            test = new TestLaby("C://Users//manar//OneDrive//Documents//ECE//Semestre2//POO-Java//TP3//Labyrinthe2018", "labyrinthe.txt");     
            // Affiche le labyrinthe en console
            fen.affiche(laby); 
             
            //JOptionPane.showMessageDialog(null, "Action");
            fen.messageEntree();
            
            //ajout des actions aux differents boutons
            fen.getButton1().addActionListener(test);
            fen.getButton2().addActionListener(test);
            fen.getButton5().addActionListener(test);

            char choix = console.menu(); // afficher le menu labyrinthe en mode console

            switch (choix) {
                case '1': // en profendeur
                    test.deplacerDFS(laby.getDepartY(), laby.getDepartX());
                    break;
                case '2': // aléatoire 
                    test.deplacerAuto();
                    break;
                case '0':
                    System.exit(0);
                    break;
                default:
                    System.out.println("Erreur de choix");
            }
        } catch (FileFormatException ffe) {
            System.out.println("Problème de format du fichier !");
        }  
    }
    
    
   @Override
    public void actionPerformed(ActionEvent e) {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    	System.out.println("Button clicked : " + fen.getButton1().getText());
        
        if (e.getSource() == fen.getButton1()){
            this.deplacerDFS(laby.getDepartY(), laby.getDepartX());
            fen.miseAJourPositionPersonnage(laby);
        }
        else if (e.getSource() == fen.getButton2()){
            this.deplacerAuto();
            fen.miseAJourPositionPersonnage(laby);
        }
        else if (e.getSource() == fen.getButton5()){
            System.exit(0);
        }
    }
    
  
}
