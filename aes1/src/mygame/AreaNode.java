/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package mygame;

import com.jme3.app.SimpleApplication;
import com.jme3.material.Material;
import com.jme3.math.ColorRGBA;
import com.jme3.math.Vector2f;
import com.jme3.math.Vector3f;
import com.jme3.scene.Geometry;
import com.jme3.scene.Node;
import com.jme3.scene.shape.Box;
import com.jme3.scene.shape.Quad;

/**
 *
 * @author armageddon
 */
public class AreaNode extends Node{
    
    static final float AREA_PADDING = 0.125f;
    
    private int numZombies;
    private int numHumans;
    private int numSoldiers;
    
    private Vector2f pos;
    private Vector2f size;
    
    private Node connections;

    public AreaNode(Vector2f pos, Vector2f size) {
        this.numZombies = 0;
        this.numHumans = 0;
        this.numSoldiers = 0;
        this.pos = pos;
        this.size = size;
        
        connections = new Node("connections");
        attachChild(connections);
        
        //Basic Rectangle
        Quad q = new Quad(size.x-AREA_PADDING*2, size.y-AREA_PADDING*2);
        Geometry geom = new Geometry("Quad", q);
        Material mat = new Material(Main.app.getAssetManager(), "Common/MatDefs/Misc/Unshaded.j3md");
        mat.setColor("Color", ColorRGBA.Blue);
        geom.setMaterial(mat);
        geom.setLocalTranslation(AREA_PADDING, AREA_PADDING, 0f);
        attachChild(geom);
        
        
        
        setLocalTranslation(pos.x, pos.y, 0.0f);
    }
    
    
    public void connect(AreaNode to){
        
        AreaConnectionNode acn = new AreaConnectionNode(this, to);
        connections.attachChild(acn);
        
    }
    
    public Vector2f getPosition(){
        return pos;
    }
    
    public Vector2f getSize(){
        return size;
    }
    
}
