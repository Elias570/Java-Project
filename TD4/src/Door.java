import java.awt.*;

public class Door extends SolidSprite{
    private String targetLevel; // Nom ou chemin du niveau cible
    private double targetX, targetY; // Position où le héros apparaîtra dans le niveau cible

    public Door(double x, double y, Image image, double width, double height, String targetLevel, double targetX, double targetY) {
        super(x, y, image, width, height);
        this.targetLevel = targetLevel;
        this.targetX = targetX;
        this.targetY = targetY;
    }

    // Méthode pour charger le niveau
    public void activate(GameEngine gameEngine) {
        gameEngine.loadLevel(targetLevel, targetX, targetY);
    }

}
