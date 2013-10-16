package mygame;

import com.jme3.app.SimpleApplication;
import com.jme3.math.Vector2f;
import com.jme3.renderer.RenderManager;
import com.jme3.scene.Node;

/**
 * test
 * @author normenhansen
 */
public class Main extends SimpleApplication {
    
    public static Main app;
    
    //holds all areas
    private Node areas;
    

    public static void main(String[] args) {
        app = new Main();
        app.start();
    }

    @Override
    public void simpleInitApp() {
        
        areas = new Node("areas");
        rootNode.attachChild(areas);
        
        for(int i=0; i<10; i++){
            AreaNode an = new AreaNode(new Vector2f(i*1f,0f), new Vector2f(1f,1f));
            areas.attachChild(an);
        }
        
        for(int i=0; i<areas.getQuantity()-1; i++){
            ((AreaNode)areas.getChild(i)).connect((AreaNode)areas.getChild(i+1));
        }
        
        
    }

    @Override
    public void simpleUpdate(float tpf) {
        //TODO: add update code
    }

    @Override
    public void simpleRender(RenderManager rm) {
        //TODO: add render code
    }
}
