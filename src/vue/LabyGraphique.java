/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vue;
import javax.swing.*;  
import java.awt.*; 
import modele.*; 
import java.awt.image.*;
import javax.imageio.*;
import java.io.*;
import javax.swing.border.Border;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyledDocument;

public class LabyGraphique extends JFrame { 
    
    private JPanel pan; // panneau du plateau   
    private JPanel panelButtons; //panneau des boutons
    private JPanel panelTextArea; //panneau des consignes du jeu
    private JTextPane consignes;
    public JButton boutons[][]; // matrice de boutons
    private ImageIcon homeIcon;
    final static boolean shouldFill = true;
    
    //Creation des boutons
    public  JButton button1 = new JButton();
    private JButton button2 = new JButton();
    
    private JButton button5 = new JButton();
    private JButton deplacementProfondeur = new JButton();
    
    //Images du plateau  
    ImageIcon image1 = new ImageIcon("D:/Projet_Java/TP3/Labyrinthe2018/src/images/marioBros.png");
    ImageIcon image2 = new ImageIcon("D:/Projet_Java/TP3/Labyrinthe2018/src/images/grassGame.png");
    ImageIcon marioFace = new ImageIcon("D:/Projet_Java/TP3/Labyrinthe2018/src/images/marioFace5.png");
    ImageIcon arrow = new ImageIcon("D:/Projet_Java/TP3/Labyrinthe2018/src/images/deepDisplacement.png");
    ImageIcon random = new ImageIcon("D:/Projet_Java/TP3/Labyrinthe2018/src/images/random.png");
    ImageIcon dice = new ImageIcon("D:/Projet_Java/TP3/Labyrinthe2018/src/images/random4.png");
    ImageIcon BFS2 = new ImageIcon("D:/Projet_Java/TP3/Labyrinthe2018/src/images/BFS2.png");
    ImageIcon marioJumping = new ImageIcon("D:/Projet_Java/TP3/Labyrinthe2018/src/images/marioJumping.png");
    ImageIcon consignesJeu = new ImageIcon("D:/Projet_Java/TP3/Labyrinthe2018/src/images/consignes.png");
    ImageIcon right = new ImageIcon("D:/Projet_Java/TP3/Labyrinthe2018/src/images/right.png");
    ImageIcon top = new ImageIcon("D:/Projet_Java/TP3/Labyrinthe2018/src/images/top.png");
    ImageIcon bottom = new ImageIcon("D:/Projet_Java/TP3/Labyrinthe2018/src/images/bottom.png");
    ImageIcon personnage = new ImageIcon("D:/Projet_Java/TP3/Labyrinthe2018/src/images/personnage.png");

    //Constructeur
    public LabyGraphique ()   {         
	    setTitle ("Mario Laby :D"); 
            
            //Dimension pour avoir la taille de l'ecran
            Dimension d = getMaximumSize();
            setSize(d.width, d.height);
	    
	    // instancier le panneau du plateau
	    this.pan = new JPanel(); 
	    this.panelButtons = new JPanel();
	    this.panelTextArea = new JPanel();
	    this.panelTextArea.setLayout(null);
            
           // met le fond ou se trouve les boutons en marron
            Color colorBrown = new Color(146,75,12);
	    
	    //split entre les consignes/boutons et le plateau
	    JSplitPane splitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT,
	                                   panelTextArea, panelButtons);
	    
            //split entre les consignes et les boutons
	    JSplitPane splitPaneCenter = new JSplitPane(JSplitPane.VERTICAL_SPLIT,
	                                   splitPane, pan);
	    splitPaneCenter.setDividerLocation(280);
	 
	    //espacement entre les boutons
	    this.panelButtons.setLayout(new GridLayout(0,3,250,250));
            
	    Color color = new Color(146,75,12);
	    this.panelButtons.setBackground(color);
	    
	    //espacement sur les cotes des boutons
	    //(haut,gauche,bas,droit)
	    this.panelButtons.setBorder(BorderFactory.createEmptyBorder(5, 10, 5, 10));
	    
	    //CONSIGNES
	    this.consignes = new JTextPane();
	    this.consignes.setFont(new Font("Arial Black", Font.BOLD, 25));
	    this.consignes.setEditable(false);
	    this.consignes.setSize(500,200);
	    this.consignes.insertIcon( this.consignesJeu);
	    
	    //TEXT-AREA
	    this.panelTextArea.setLayout(new FlowLayout());
	    this.panelTextArea.add( this.consignes);
	    
	    //BOUTONS
            
	    this.button1.setIcon(this.arrow);
            this.button1.setText("Deep Displacement");
	    this.button1.setPreferredSize(new Dimension(250, 250));
	    this.panelButtons.add( this.button1);
	    
	    this.button2.setText("Aléatoire");
	    this.button2.setIcon( this.dice);
	    this.button2.setPreferredSize(new Dimension(250, 250));
	    this.panelButtons.add( this.button2);
	 
	    this.button5.setIcon( this.marioFace);
	    this.button5.setPreferredSize(new Dimension(250, 250));
	    this.panelButtons.add( this.button5);
	      
	    //ajouter le panneau du plateau et des boutons dans la fenêtre
	    getContentPane().add(splitPaneCenter);  
	    
	    setIconImage(Toolkit.getDefaultToolkit().getImage("C:/Users/manar/OneDrive/Documents/ECE/Semestre2/POO-Java/TP3/marioIcon.jpg"));
    } 
    
    //Message d'entree
    public void messageEntree(){
          
        JFrame hello = new JFrame("Hello :D");
        //Pour que la fenetre prenne la taille de l'ecran
        Dimension d = getMaximumSize();
        hello.setSize(d.width, d.height);
       
        
        ImageIcon gif = new ImageIcon("D:/Projet_Java/TP3/Labyrinthe2018/src/images/gif.gif");
        JTextPane panelText = new JTextPane();
        JLabel panelVideo = new JLabel("", gif, JLabel.CENTER);
        
        panelText.setBackground(Color.blue);
        panelText.setText("WELCOME TO MARIO \n BROS LABY !!!!!");
        panelText.setFont(new Font("Arial", Font.BOLD, 100));
        
        //Code pour aligner le texte dans le panelText
        //Insipire de StackOverflow
        //************************************
        StyledDocument doc = panelText.getStyledDocument();
        SimpleAttributeSet center = new SimpleAttributeSet();
        StyleConstants.setAlignment(center, StyleConstants.ALIGN_CENTER);
        doc.setParagraphAttributes(0, doc.getLength(), center, false);
        //************************************

        //met le texte en blanc
        panelText.setForeground(Color.WHITE);
        
        JSplitPane splitPanelEntry = new JSplitPane(JSplitPane.VERTICAL_SPLIT,
	                                   panelText, panelVideo);
        splitPanelEntry.setDividerLocation(280);
        
        hello.add(splitPanelEntry);
        
        hello.setVisible(true);
       
        }
    
    //Boutons 
	public JButton getButton1() {
		return button1;
	}
	public void setButton1(JButton button1) {
		this.button1 = button1;
	}
	public JButton getButton2() {
		return button2;
	}
	public void setButton2(JButton button2) {
		this.button2 = button2;
	}
        
	public JButton getButton5() {
		return button5;
	}
	public void setButton5(JButton button5) {
		this.button5 = button5;
	}
	public JButton getDeplacementProfondeur() {
		return deplacementProfondeur;
	}
	public JPanel getPan() {
		return pan;
	}
	public void setPan(JPanel pan) {
		this.pan = pan;
	}
	public JPanel getPanelButtons() {
		return panelButtons;
	}
	public void setPanelButtons(JPanel panelButtons) {
		this.panelButtons = panelButtons;
	}
	public JPanel getPanelTextArea() {
		return panelTextArea;
	}
	public void setPanelTextArea(JPanel panelTextArea) {
		this.panelTextArea = panelTextArea;
	}
	public JTextPane getConsignes() {
		return consignes;
	}
	public void setConsignes(JTextPane consignes) {
		this.consignes = consignes;
	}
	public JButton[][] getBoutons() {
		return boutons;
	}
	public void setBoutons(JButton[][] boutons) {
		this.boutons = boutons;
	}
	public ImageIcon getHomeIcon() {
		return homeIcon;
	}
	public void setHomeIcon(ImageIcon homeIcon) {
		this.homeIcon = homeIcon;
	}
	public ImageIcon getImage1() {
		return image1;
	}
	public void setImage1(ImageIcon image1) {
		this.image1 = image1;
	}
	public ImageIcon getImage2() {
		return image2;
	}
	public void setImage2(ImageIcon image2) {
		this.image2 = image2;
	}
	public ImageIcon getMarioFace() {
		return marioFace;
	}
	public void setMarioFace(ImageIcon marioFace) {
		this.marioFace = marioFace;
	}
	public ImageIcon getArrow() {
		return arrow;
	}
	public void setArrow(ImageIcon arrow) {
		this.arrow = arrow;
	}
	public ImageIcon getRandom() {
		return random;
	}
	public void setRandom(ImageIcon random) {
		this.random = random;
	}
	public ImageIcon getDice() {
		return dice;
	}
	public void setDice(ImageIcon dice) {
		this.dice = dice;
	}
	public ImageIcon getMarioJumping() {
		return marioJumping;
	}
	public void setMarioJumping(ImageIcon marioJumping) {
		this.marioJumping = marioJumping;
	}
	public ImageIcon getConsignesJeu() {
		return consignesJeu;
	}
	public void setConsignesJeu(ImageIcon consignesJeu) {
		this.consignesJeu = consignesJeu;
	}
	public void setDeplacementProfondeur(JButton deplacementProfondeur) {
		this.deplacementProfondeur = deplacementProfondeur;
	}

	// Méthode qui affiche la grille du labyrinthe 
    public void affiche(Labyrinthe laby) { 
        // mise en forme avec une grille
    	this.getPan().setLayout(new GridLayout(laby.getTailleY(), laby.getTailleX()));
        // instancier les lignes de la matrice de boutons
        //boutons = new JButton[laby.getTailleY()][]; 
        this.setBoutons(new JButton[laby.getTailleY()][]);
        
        for (int i = 0; i < laby.getTailleY(); i++)  {
        	// Pour chaque ligne de la matrice, instancier les boutons      
        	//boutons[i] = new JButton[laby.getTailleX()];
        	this.getBoutons()[i] = new JButton[laby.getTailleX()];
        }
        
        // Ajouter les boutons dans le panneau 
        for (int i = 0; i < laby.getTailleY(); i++) {             
            for (int j = 0; j < laby.getTailleX(); j++) {
                // instancier chaque bouton
                this.getBoutons()[i][j] = new JButton();
                this.getPan().add(this.getBoutons()[i][j]);
                
                /*if(boutons[i][j].canMoveToCase()){
                    boutons[i][j].setBackground(new Color (255, 255, 255));      
                }
                else{
                    boutons[i][j].setBackground(new Color(0,0,0));*/
                    
                }
            }  
    
        // Lire les cases du labyrinthe
        for (int y = 0; y < laby.getTailleY(); y++) { // lignes
            for (int x = 0; x < laby.getTailleX(); x++) {
                Case c = laby.getCase(y, x);
                if (c instanceof CaseMur) {
                    //Mur
                    Image icon = this.image1.getImage().getScaledInstance(380, 200, Image.SCALE_SMOOTH);
                    this.image1.setImage(icon);
                    this.getBoutons()[x][y].setIcon(this.image1);
                } else {
                    if (c.getVisited()) {
                        //Personnage
                        this.getBoutons()[x][y].setIcon(this.personnage);
                    } else {
                        //herbe
                        Image icon = this.image2.getImage().getScaledInstance(380, 200, Image.SCALE_SMOOTH);
                        this.image2.setImage(icon);
                        this.getBoutons()[x][y].setIcon(this.image2);
                    }
                }
            }
            System.out.println();
        }
        // rendre la fenetre visible         
        this.setVisible(true);
    }
    
    //Mettre a jour la position du personnage 
    public void miseAJourPositionPersonnage(Labyrinthe laby){
    
    for (int y = 0; y < laby.getTailleY(); y++) { // lignes
            for (int x = 0; x < laby.getTailleX(); x++) {
                Case c = laby.getCase(y, x);
                if (c instanceof CaseMur) {
                    //Mur
                    Image icon = this.image1.getImage().getScaledInstance(380, 200, Image.SCALE_SMOOTH);
                    this.image1.setImage(icon);
                    this.getBoutons()[x][y].setIcon(this.image1);
                } else {
                    if (c.getVisited()) {
                        //Personnage
                        this.getBoutons()[x][y].setIcon(this.personnage);
                    } else {
                        //herbe
                        Image icon = this.image2.getImage().getScaledInstance(380, 200, Image.SCALE_SMOOTH);
                        this.image2.setImage(icon);
                        this.getBoutons()[x][y].setIcon(this.image2);
                    }
                }
            }
            System.out.println();
        }
        // rendre la fenetre visible         
        this.setVisible(true);
    }   
}
  

