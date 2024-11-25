import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;

public class GameEngine implements KeyListener {
    private final DynamicSprite hero;
    private PlayGround currentLevel; // Niveau en cours
    private RenderEngine renderEngine;
    private PhysicEngine physicEngine;

    // Constructeur de GameEngine
    public GameEngine(DynamicSprite hero) {
        this.hero = hero;

    }

    @Override
    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_CONTROL) {
            hero.setRunning(true); // Active la course
        }
        switch (e.getKeyCode()) {
            case KeyEvent.VK_UP:
                hero.setDirection(Direction.NORTH);
                break;
            case KeyEvent.VK_DOWN:
                hero.setDirection(Direction.SOUTH);
                break;
            case KeyEvent.VK_LEFT:
                hero.setDirection(Direction.WEST);
                break;
            case KeyEvent.VK_RIGHT:
                hero.setDirection(Direction.EAST);
                break;
            default:
                break;
        }
    }

    // Méthode pour charger un nouveau niveau
    public void loadLevel(String levelPath, double heroX, double heroY) {
        try {
            // Charge le nouveau niveau
            PlayGround newLevel = new PlayGround(levelPath);

            // Met à jour le niveau courant
            this.currentLevel = newLevel;

            // Repositionne le héros
            hero.setX(heroX);
            hero.setY(heroY);

            // Met à jour les moteurs
            renderEngine.clearRenderList(); // Vide les anciens objets
            renderEngine.addToRenderList(newLevel.getSpriteList());
            renderEngine.addToRenderList(hero);

            physicEngine.setEnvironment(newLevel.getSolidSpriteList());

            System.out.println("Niveau chargé : " + levelPath);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_CONTROL) {
            hero.setRunning(false); // Désactive la course
        }
        // Vous pouvez gérer les événements de relâchement de touche si nécessaire
    }

    @Override
    public void keyTyped(KeyEvent e) {
        // Vous pouvez gérer les événements de frappe de touche si nécessaire
    }


    public void update() {

    }
}
