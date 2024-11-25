import java.awt.*;
import java.awt.image.BufferedImage;
import java.awt.geom.Rectangle2D;
import java.util.ArrayList;



public class DynamicSprite extends SolidSprite {
    private boolean isWalking = true; // Par défaut à true
    private double speed = 5.0; // Vitesse par défaut
    private boolean isRunning = false; // Indique si le héros court
    private double runningSpeed = 10.0; // Vitesse en courant
    private Direction direction = Direction.SOUTH; // Direction actuelle
    int timeBetweenFrame = 200;
    int spriteSheetNumberOfColumn = 10;

    // Constructeur de DynamicSprite appelant le constructeur de SolidSprite
    public DynamicSprite(double x, double y, Image image, double width, double height) {
        super(x, y,image, width, height);
    }

    // Setter pour la variable direction
    public void setDirection(Direction direction) {
        this.direction = direction;
    }

    // Méthode de mouvement
    private void move() {
        speed = isRunning ? runningSpeed : speed; // Ajuste la vitesse
        switch (direction) {
            case NORTH -> this.y -= speed;
            case SOUTH -> this.y += speed;
            case EAST -> this.x += speed;
            case WEST -> this.x -= speed;
        }
    }


    // Vérification si le mouvement est possible
    private boolean isMovingPossible(ArrayList<Sprite> environment) {
        // Création de la hitbox décalée
        Rectangle2D.Double hitBox = new Rectangle2D.Double(x, y, width, height);
        switch (direction) {
            case NORTH -> hitBox.y -= speed;
            case SOUTH -> hitBox.y += speed;
            case EAST -> hitBox.x += speed;
            case WEST -> hitBox.x -= speed;
        }

        // Vérification des collisions
        for (Sprite sprite : environment) {
            if (sprite instanceof SolidSprite && !(sprite instanceof Trap) && sprite != this) {
                if (hitBox.intersects(sprite.getBounds())) {
                    return false; // Collision détectée, sauf si c'est un piège
                }
            }
            /*if (sprite instanceof SolidSprite && sprite != this) { // Vérifie si c'est un SolidSprite et pas lui-même
                if (hitBox.intersects(sprite.getBounds())) {
                    return false; // Collision détectée
                }
            }*/
        }
        return true; // Aucun obstacle, le mouvement est possible
    }

    public void setRunning(boolean running) {
        this.isRunning = running;
    }

    // Méthode pour déplacer si possible
    public void moveIfPossible(ArrayList<Sprite> environment) {
        if (isMovingPossible(environment)) {
            move(); // Effectue le mouvement si possible
        }
    }


    int health = 100; // Vie maximale
    private long lastDamageTime = 0; // Temps de la dernière collision


    // Dessiner la barre de vie
    public void drawHealthBar(Graphics g) {
        int barWidth = 50; // Largeur de la barre de vie
        int barHeight = 5; // Hauteur de la barre
        int healthWidth = (int) ((health / 100.0) * barWidth);

        // Dessiner une bordure de barre de vie
        g.setColor(Color.BLACK);
        g.fillRect((int) x, (int) y - 10, barWidth, barHeight);

        // Dessiner la barre de vie en fonction des points de vie restants
        g.setColor(Color.RED);
        g.fillRect((int) x, (int) y - 10, healthWidth, barHeight);
    }

    // Réduit la vie si le personnage n'est pas invincible
    public void takeDamage(int damage) {
        long currentTime = System.currentTimeMillis();
        if (currentTime - lastDamageTime > 2000) { // 2 secondes d'invincibilité
            health -= damage;
            lastDamageTime = currentTime; // Réinitialisation du temps de dernière collision
        }
        if (health <= 0) {
            System.out.println("Game Over!");

        }
    }

    @Override
    public void draw(Graphics g) {
        long currentTime = System.currentTimeMillis();
        //int timeBetweenFrame = 200;
        //int spriteSheetNumberOfColumn = 10;
        int index = (int) ((currentTime / timeBetweenFrame) % spriteSheetNumberOfColumn);

        // Récupération de l'attitude correspondant à la direction
        int attitude = direction.ordinal();

        // Définir les coordonnées de l'image source pour drawImage

        int srcX1 = index * (int) width;
        int srcY1 = attitude * (int) height;
        int srcX2 = srcX1 + (int) width;
        int srcY2 = srcY1 + (int) height;


        //System.out.println("int : " + srcX1 + " " + srcY1 + " " + srcX2 + " " + srcY2);

    // Affichage de la portion de la spritesheet correspondant à la frame actuelle
        g.drawImage(image, (int) x, (int) y, (int) (x + width), (int) (y + height),
                srcX1, srcY1, srcX2, srcY2, null);


        drawHealthBar(g); // Appel pour afficher la barre de vie
    }

    // Getter pour la vie
    public int getHealth() {
        return health;
    }

    // Getter pour X
    public double getX() {
        return x;
    }

    // Setter pour X
    public void setX(double x) {
        this.x = x;
    }

    // Getter pour Y
    public double getY() {
        return y;
    }

    // Setter pour Y
    public void setY(double y) {
        this.y = y;
    }


}
