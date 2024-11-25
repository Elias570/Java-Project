import java.util.ArrayList;

public class PhysicEngine implements Engine {
    private final ArrayList<DynamicSprite> movingSpriteList; // Liste des sprites dynamiques
    private ArrayList<Sprite> environment; // Liste des éléments solides

    // Constructeur initialisant les listes
    public PhysicEngine() {
        movingSpriteList = new ArrayList<>();
        environment = new ArrayList<>();
    }

    // Méthode pour ajouter un sprite dynamique à la liste des sprites à mouvoir
    public void addToMovingSpriteList(DynamicSprite sprite){
        if (!movingSpriteList.contains(sprite)){
            movingSpriteList.add(sprite);
        }
    }

    // Setter pour définir la liste d'environnement
    public void setEnvironment(ArrayList<Sprite> environment) {
        this.environment = environment;
    }

    // Surcharge de la méthode update pour déplacer tous les sprites dynamiques
    @Override
    public void update() {
        for (DynamicSprite sprite : movingSpriteList) {
            sprite.moveIfPossible(environment); // Appelle moveIfPossible pour chaque sprite

            // Vérification des collisions avec l'environnement
            for (Sprite environmentSprite : environment) {
                if (sprite.getBounds().intersects(environmentSprite.getBounds())) {
                    if (environmentSprite instanceof Trap) {
                        Trap trap = (Trap) environmentSprite;
                        sprite.takeDamage(trap.getDamage()); // Réduit la vie du sprite
                    }
                    // Si c'est un ennemi
                    if (environmentSprite instanceof Enemy && sprite instanceof DynamicSprite) {
                        Enemy enemy = (Enemy) environmentSprite;
                        enemy.checkCollision(sprite); // Appelle la méthode pour vérifier les collisions
                        enemy.attack(sprite); // L'ennemi attaque le sprite
                    }
                    // Ajoutez d'autres types de collisions ici si nécessaire (ennemis, obstacles)
                }
            }
        }
    }


    public void start() {
    }
}
