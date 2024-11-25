import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TitleScreen extends JFrame{
    private CardLayout cardLayout;
    private JPanel mainPanel;
    private JPanel titleScreen;
    private JPanel gameScreen;
    private RenderEngine renderEngine;
    private GameEngine gameEngine;
    private PhysicEngine physicEngine;
    private DynamicSprite hero;

    public TitleScreen(RenderEngine renderEngine,GameEngine gameEngine,PhysicEngine physicEngine, DynamicSprite hero) {
        this.renderEngine = renderEngine;
        this.gameEngine=gameEngine;
        this.physicEngine = physicEngine;
        this.hero = hero;

        // Configuration de la fenêtre principale
        setTitle("Jeu");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(1230, 600);
        setLocationRelativeTo(null);

        // CardLayout pour alterner entre les écrans
        cardLayout = new CardLayout();
        mainPanel = new JPanel(cardLayout);

        // Initialiser les écrans
        titleScreen = createTitleScreen();
        gameScreen = createGameScreen();

        // Ajouter les écrans au CardLayout
        mainPanel.add(titleScreen, "TitleScreen");
        mainPanel.add(gameScreen, "GameScreen");

        add(mainPanel);

        // Afficher l'écran titre au démarrage
        cardLayout.show(mainPanel, "TitleScreen");
    }

    private JPanel createTitleScreen() {
        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());

        JLabel title = new JLabel("Bienvenue dans le Jeu", JLabel.CENTER);
        title.setFont(new Font("Arial", Font.BOLD, 32));
        panel.add(title, BorderLayout.CENTER);

        JButton startButton = new JButton("Commencer");
        startButton.setFont(new Font("Arial", Font.PLAIN, 24));
        panel.add(startButton, BorderLayout.SOUTH);

        // Action lorsque le bouton est cliqué
        startButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Passer à l'écran du jeu
                cardLayout.show(mainPanel, "GameScreen");
                gameScreen.requestFocusInWindow(); // Redemander le focus pour le jeu
            }
        });

        return panel;
    }

    private JPanel createGameScreen() {
        JPanel panel = new JPanel(new BorderLayout());
        panel.add(renderEngine, BorderLayout.CENTER); // Ajoute le moteur de rendu
        panel.setFocusable(true); // Permet à ce panneau de recevoir les événements clavier
        panel.requestFocusInWindow(); // Force la capture du focus

        // Ajoute le KeyListener pour détecter les déplacements
        panel.addKeyListener(gameEngine);
        return panel;
    }
}
