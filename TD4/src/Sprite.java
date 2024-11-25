import java.awt.Image;
import javax.swing.ImageIcon;
import java.awt.Graphics;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;

public class Sprite implements Displayable {
    protected Image image;
    protected double x;
    protected double y;        // Position du sprite
    protected double width;
    protected double height; // Taille du sprite

    // Constructeur
    public Sprite(double x, double y,Image image, double width, double height) {
        this.image =image; //new ImageIcon("./img/tree.png").getImage();
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
    }

    // Surcharge de la méthode draw pour dessiner l'image du sprite
    @Override
    public void draw(Graphics g) {
        // Dessiner l'image aux coordonnées (x, y) et redimensionner si nécessaire
        g.drawImage(image, (int)x, (int)y, (int)width, (int)height, null);
    }

    // Méthode pour obtenir les dimensions du sprite
    public Rectangle2D.Double getBounds() {
        return new Rectangle2D.Double(x, y, width, height);
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    public double getWidth() {
        return width;
    }

    public double getHeight() {
        return height;
    }
}
