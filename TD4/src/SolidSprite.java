import javax.imageio.ImageIO;
import java.awt.*;
import java.io.File;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.awt.geom.Rectangle2D;


public class SolidSprite extends Sprite {
    public SolidSprite(double x, double y, Image image, double width, double height) {
        //this.image =image;
        // Appel du constructeur de la super-classe Sprite
        super(x, y, image, width, height);
    }

    public Rectangle2D getHitBox() {
        return new Rectangle2D.Double(x,y,(double) width,(double) height);
    }

    public boolean intersect(Rectangle2D.Double hitBox){
        return this.getHitBox().intersects(hitBox);
    }

    // méthode pour vérifier la collision
    public boolean intersects(Sprite other) {
        Rectangle thisBounds = new Rectangle((int) x, (int) y, (int) width, (int) height);
        Rectangle otherBounds = new Rectangle((int) other.getX(), (int) other.getY(), (int) other.getWidth(), (int) other.getHeight());
        return thisBounds.intersects(otherBounds);
    }


}
