import java.awt.*;

public class Trap extends SolidSprite{
    private int damage; // Dégâts infligés par le piège

    public Trap(double x, double y, Image image, double width, double height, int damage) {
        super(x, y, image, width, height);
        this.damage = damage;
    }

    public int getDamage() {
        return damage;
    }


}
