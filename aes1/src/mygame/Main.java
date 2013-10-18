package mygame;

import com.jme3.app.SimpleApplication;
import com.jme3.math.Vector2f;
import com.jme3.renderer.RenderManager;
import com.jme3.scene.Node;
import com.jme3.scene.Spatial;
import java.util.Random;

/**
 * test
 * @author normenhansen
 */
public class Main extends SimpleApplication {
    
    public static Main app;
    
    //holds all areas
    private Node areas;
    
    private float tickTime;
    

    public static void main(String[] args) {
        app = new Main();
        app.start();
    }

    @Override
    public void simpleInitApp() {
        tickTime = 0f;
        
        areas = new Node("areas");
        rootNode.attachChild(areas);
        
        for(int i=0; i<10; i++){
            AreaNode an = new StreetAreaNode(new Vector2f(i*1f,0f), new Vector2f(1f,1f));
            areas.attachChild(an);
            an.setNumHumans((int)(Math.random()*100));
            an.setNumAvailShelters((int)(Math.random()*10));
            if(i == 0) an.setNumZombies(10);
            //if(i == 0) an.setNumHumans(100);
        }
        
        for(int i=0; i<areas.getQuantity()-1; i++){
            ((AreaNode)areas.getChild(i)).connect((AreaNode)areas.getChild(i+1));
            ((AreaNode)areas.getChild(i+1)).connect((AreaNode)areas.getChild(i));
            
        }
        
       
    }

    @Override
    public void simpleUpdate(float tpf) {
        updateLogic(tpf);
        updateGraphics(tpf);
    }

    @Override
    public void simpleRender(RenderManager rm) {
        //TODO: add render code
    }
    
    void updateLogic(float tpf){
        tickTime += tpf;
        
        if(tickTime > 1){
            tickTime -= 1;
            for(Spatial an : areas.getChildren()){
                ((AreaNode)an).planZombieMovements(tpf);
            }
            for(Spatial an : areas.getChildren()){
                ((AreaNode)an).moveZombies(tpf);
            }
            for(Spatial an : areas.getChildren()){
                ((AreaNode)an).humanSearchShelter(tpf);
            }
            for(Spatial an : areas.getChildren()){
                ((AreaNode)an).eatZombies(tpf);
            }
        }
    }
    
    void updateGraphics(float tpf){
        for(Spatial an : areas.getChildren()){
            ((AreaNode)an).updateGraphics(tpf);
        }
    }
    
    
}
