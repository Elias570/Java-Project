import javax.swing.*;
import java.awt.*;

public class Framerate extends JFrame{
    private GamePanel gamePanel;

    public Framerate() {
        // Configuration de la fenêtre principale
        setTitle("Jeu avec Framerate");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(800, 600);
        setLocationRelativeTo(null);

        // Ajout du panneau de jeu
        gamePanel = new GamePanel();
        add(gamePanel);

        // Démarrer la boucle de rendu
        new Timer(16, e -> gamePanel.repaint()).start(); // Environ 60 FPS (1000ms/60 ≈ 16ms)
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            Framerate  frame = new Framerate();
            frame.setVisible(true);
        });
    }

    // Panneau de jeu
    static class GamePanel extends JPanel {
        private long lastTime = System.nanoTime();
        private int frames = 0;
        private double fps = 0.0;

        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);

            // Arrière-plan
            g.setColor(Color.BLACK);
            g.fillRect(0, 0, getWidth(), getHeight());

            // Calcul du FPS
            long currentTime = System.nanoTime();
            frames++;
            if (currentTime - lastTime >= 1_000_000_000) { // Une seconde écoulée
                fps = frames;
                frames = 0;
                lastTime = currentTime;
            }

            // Affichage du framerate
            g.setColor(Color.WHITE);
            g.setFont(new Font("Arial", Font.BOLD, 20));
            g.drawString("FPS: " + (int) fps, 10, 20);

            // Dessiner d'autres éléments du jeu ici
            g.setColor(Color.GREEN);
            g.fillRect(100, 100, 50, 50); // Exemple d'objet de jeu
        }
    }
}
