import javax.swing.JPanel;
import java.util.ArrayList;
import java.util.List;
import java.awt.Graphics;

public class RenderEngine extends JPanel implements Engine {
    // Attribut privé pour stocker la liste des éléments affichables
    private List<Displayable> renderList;

    // Constructeur par défaut qui initialise renderList
    public RenderEngine() {
        renderList = new ArrayList<>();
    }

    // Setter pour renderList
    public void setRenderList(List<Displayable> renderList) {
        this.renderList = renderList;
    }

    // Méthode pour ajouter un élément à renderList
    public void addToRenderList(Displayable displayable) {
        renderList.add(displayable);
    }


    // Méthode pour mettre à jour les éléments de la liste de rendu
    public void update() {
        repaint();
    }

    // Méthode pour ajouter un seul Sprite
    public void addToRenderList(Sprite sprite) {
        renderList.add(sprite);
    }

    // Méthode pour ajouter une collection de Sprites
    public void addToRenderList(List<Sprite> sprites) {
        renderList.addAll(sprites);
    }





        // Surcharge de la méthode paint pour dessiner chaque élément de renderList
    @Override
    public void paint(Graphics g) {
        super.paint(g); // Appel de la méthode paint de JPanel pour effacer l'affichage précédent

        // Parcourir et dessiner chaque élément dans renderList
        for (Displayable displayable : renderList) {
            displayable.draw(g); // Appelle la méthode draw propre au type de l'objet (polymorphisme)
        }
    }

    public void start() {
    }

    // Effacer la liste des objets à rendre
    public void clearRenderList() {
        renderList.clear();
    }
}

