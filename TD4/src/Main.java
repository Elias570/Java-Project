import javax.imageio.ImageIO;
import javax.swing.*;
import java.io.File;
import java.util.ArrayList;

import static javax.swing.WindowConstants.EXIT_ON_CLOSE;

public class Main {
    JFrame displayZoneFrame;

    RenderEngine renderEngine;
    GameEngine gameEngine;
    PhysicEngine physicEngine;
    private DynamicSprite hero;

    public Main() throws Exception{
        displayZoneFrame = new JFrame("Java Labs");
        displayZoneFrame.setSize(600,400);
        displayZoneFrame.setDefaultCloseOperation(EXIT_ON_CLOSE);



        DynamicSprite hero = new DynamicSprite(200,300,
                ImageIO.read(new File("./img/heroTileSheetLowRes.png")),48,50);

        SolidSprite trap = new Trap(150, 300,
                ImageIO.read(new File("./img/trap.png")), 32, 32, 10);

        //SolidSprite enemy
        // Créez un ennemi avec un pattern de ronde
        Enemy patrolEnemy= new Enemy(200, 300,
                ImageIO.read(new File("./img/heroTileSheetLowRes.png")), 48, 50, 100,20,
                Enemy.PatternType.RONDE, null); // Pas de cible pour la ronde);

        // Créez un ennemi qui suit le héros
        Enemy chasingEnemy = new Enemy(200, 500,
                ImageIO.read(new File("./img/heroTileSheetLowRes.png")), 48, 50, 100,20,
                Enemy.PatternType.SUIVI, hero); // Le héros est la cible

        Enemy patrolEnemy2= new Enemy(900, 100,
                ImageIO.read(new File("./img/heroTileSheetLowRes.png")), 48, 50, 100,20,
                Enemy.PatternType.RONDE, null); // Pas de cible pour la ronde);

        // Créez un ennemi qui suit le héros
        Enemy chasingEnemy2 = new Enemy(900, 200,
                ImageIO.read(new File("./img/heroTileSheetLowRes.png")), 48, 50, 100,20,
                Enemy.PatternType.SUIVI, hero); // Le héros est la cible

        renderEngine = new RenderEngine();
        physicEngine = new PhysicEngine();
        gameEngine = new GameEngine(hero);

        Timer renderTimer = new Timer(50,(time)-> renderEngine.update());
        Timer gameTimer = new Timer(50,(time)-> gameEngine.update());
        Timer physicTimer = new Timer(50,(time)-> physicEngine.update());

        renderTimer.start();
        gameTimer.start();
        physicTimer.start();

        displayZoneFrame.getContentPane().add(renderEngine);
        displayZoneFrame.setVisible(true);
        SolidSprite testSprite = new SolidSprite(250,300,(ImageIO.read(new File("./img/rock.png"))),64,64);
        renderEngine.addToRenderList(testSprite);

        PlayGround level = new PlayGround("./data/level1.txt");
        //SolidSprite testSprite = new SolidSprite(100,100,(ImageIO.read(new File("./img/rock.png"))),64,64);
        renderEngine.addToRenderList(level.getSpriteList());
        renderEngine.addToRenderList(hero);
        renderEngine.addToRenderList(trap);
        //renderEngine.addToRenderList(enemy);
        renderEngine.addToRenderList(patrolEnemy);
        renderEngine.addToRenderList(chasingEnemy);
        renderEngine.addToRenderList(patrolEnemy2);
        renderEngine.addToRenderList(chasingEnemy2);

        ArrayList<Sprite> environment = new ArrayList<>();
        environment.add(trap);
        //environment.add(enemy);
        environment.add(patrolEnemy);
        environment.add(chasingEnemy);
        environment.add(patrolEnemy2);
        environment.add(chasingEnemy2);
        physicEngine.setEnvironment(environment);


        //renderEngine.addToRenderList(testSprite);
        physicEngine.addToMovingSpriteList(hero);
        physicEngine.addToMovingSpriteList(patrolEnemy);
        physicEngine.addToMovingSpriteList(chasingEnemy);
        physicEngine.addToMovingSpriteList(patrolEnemy2);
        physicEngine.addToMovingSpriteList(chasingEnemy2);
        physicEngine.setEnvironment(level.getSolidSpriteList());


        displayZoneFrame.addKeyListener(gameEngine);
    }

    public static void main (String[] args) throws Exception {
        // write your code here
        //Main main = new Main();
        SwingUtilities.invokeLater(() -> {
            try {
                Main mainApp = new Main();
                TitleScreen frame = new TitleScreen(mainApp.renderEngine,mainApp.gameEngine,mainApp.physicEngine,
                        mainApp.hero);
                frame.setVisible(true);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }
}





