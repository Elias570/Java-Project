import java.awt.*;
import java.util.ArrayList;

public class Enemy extends DynamicSprite{
    private int damage;
    private long lastDamageTime = 0; // Temps de la dernière attaque
    private int attackCooldown = 2000; // Temps d'attente entre deux attaques (en ms)

   /* public Enemy(double x, double y, Image image, double width, double height, int damage) {
        super(x, y, image, width, height);
        this.damage = damage;
    }

    public int getDamage() {
        return damage;
    }*/

       private PatternType patternType; // Type de pattern (RONDE ou SUIVI)
       private DynamicSprite target;    // Cible (le héros)
       private double patrolRadius = 50; // Rayon pour les rondes
       private double initialX, initialY; // Position initiale pour la ronde
       private double angle = 0; // Angle pour le mouvement circulaire

       // Enumération des types de pattern
       public enum PatternType {
           RONDE, // Ronde circulaire
           SUIVI // Suit le héros
       }

       public Enemy(double x, double y, Image image, double width, double height, int health,int damage, PatternType patternType, DynamicSprite target) {
           super(x, y, image, width, height);
           this.health = health;
           this.damage = damage;
           this.patternType = patternType;
           this.target = target;
           this.initialX = x; // Enregistre la position initiale
           this.initialY = y;
       }

    public int getDamage() {
        return damage;
    }

    // Vérifie si l'ennemi peut infliger des dégâts
    public boolean canAttack() {
        long currentTime = System.currentTimeMillis();
        return currentTime - lastDamageTime >= attackCooldown;
    }

    // Inflige des dégâts et met à jour le temps de dernière attaque
    public void attack(DynamicSprite target) {
        if (canAttack()) {
            target.takeDamage(damage);
            lastDamageTime = System.currentTimeMillis(); // Met à jour le cooldown
        }
    }



    @Override
       public void moveIfPossible(ArrayList<Sprite> environment) {
           switch (patternType) {
               case RONDE -> moveInCircle();
               case SUIVI -> followTarget();
           }
           super.moveIfPossible(environment); // Effectue la vérification des collisions
       }

       // Mouvement en cercle pour le pattern "RONDE"
       private void moveInCircle() {
           angle += 0.05; // Ajustez cette valeur pour changer la vitesse
           this.x = initialX + patrolRadius * Math.cos(angle);
           this.y = initialY + patrolRadius * Math.sin(angle);
       }

       // Mouvement vers le héros pour le pattern "SUIVI"
       private void followTarget() {
           if (target != null) {
               double deltaX = target.getX() - this.x;
               double deltaY = target.getY() - this.y;

               // Normalisation pour déplacer l'ennemi vers le héros
               double distance = Math.sqrt(deltaX * deltaX + deltaY * deltaY);
               if (distance > 0) {
                   double speed = 2.0;
                   /*this.x += (deltaX / distance) * speed;
                   this.y += (deltaY / distance) * speed;*/
                   // Normalisation des déplacements
                   double normalizedX = deltaX / distance;
                   double normalizedY = deltaY / distance;

                   // Mise à jour des coordonnées
                   this.x += normalizedX * speed;
                   this.y += normalizedY * speed;

               }
           }
       }

    // Vérification des collisions avec le héros
    public void checkCollision(DynamicSprite hero) {
        if (this.getBounds().intersects(hero.getBounds())) {
            this.attack(hero); // Inflige des dégâts au héros
        }
        }

       // Affichage des ennemis
       @Override
       public void draw(Graphics g) {
           super.draw(g);
           g.setColor(Color.RED); // Dessinez une indication visuelle pour le pattern
           g.drawRect((int) x, (int) y, (int) width, (int) height);
       }
   }

