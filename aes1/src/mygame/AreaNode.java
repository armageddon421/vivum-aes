/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package mygame;

import com.jme3.font.BitmapFont;
import com.jme3.font.BitmapText;
import com.jme3.material.Material;
import com.jme3.math.ColorRGBA;
import com.jme3.math.Vector2f;
import com.jme3.scene.Geometry;
import com.jme3.scene.Node;
import com.jme3.scene.Spatial;
import com.jme3.scene.shape.Quad;

/**
 *
 * @author armageddon
 */
public class AreaNode extends Node{
    
    static final float AREA_PADDING = 0.125f;
    static final float AREA_FONT_SIZE = 0.1f;
    static final float AREA_FONT_PADDING = AREA_PADDING + 0.05f;
    
    private int numZombies;
    private int numHumans;
    private int numSoldiers;
    
    private Vector2f pos;
    private Vector2f size;
    
    private Node connections;
    
    BitmapText statusText;

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
        
        //Text
        
        BitmapFont guiFont = Main.app.getAssetManager().loadFont("Interface/Fonts/Default.fnt");
        statusText = new BitmapText(guiFont, false);
        statusText.setSize(AREA_FONT_SIZE);
        statusText.setText("Hello World\ntest");
        statusText.setLocalTranslation(AREA_FONT_PADDING, size.y-AREA_FONT_PADDING, 0.02f);
        attachChild(statusText);
        
        
        
        setLocalTranslation(pos.x, pos.y, 0.0f);
    }
    
    public void updateGraphics(float tpf){
        statusText.setText(String.format("Z: %d\nH: %d\nS: %d", numZombies, numHumans, numSoldiers));
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

    void planZombieMovements(float tpf) {
        if(numZombies > 0){
            for(Spatial sp : connections.getChildren()){
                AreaConnectionNode acn = (AreaConnectionNode)sp;
                AreaNode an = acn.getTo();
                if(numZombies > an.getNumZombies()){
                    acn.moveZombies(((numZombies-an.getNumZombies())/4));
                }
            }
        }
    }
    
    void moveZombies(float tpf) {
        for(Spatial sp : connections.getChildren()){
            AreaConnectionNode acn = (AreaConnectionNode)sp;
            acn.executeMovements();
        }
    }

    void eatZombies(float tpf) {
        if(numHumans > 0){
            double ph = (1f-1f/Math.sqrt(numHumans/5.0+1f))*0.2+0.05;
            double pz = (0.7f/Math.sqrt(numZombies/20.0+1f))+0.3f;
            int numBitten = (int)(numZombies * ph * pz);
            if(numBitten > numHumans) numBitten = numHumans;
            numHumans -= numBitten;
            numZombies += numBitten;
        }
    }

    public int getNumZombies() {
        return numZombies;
    }

    public int getNumHumans() {
        return numHumans;
    }

    public int getNumSoldiers() {
        return numSoldiers;
    }

    public void setNumZombies(int numZombies) {
        this.numZombies = numZombies;
    }

    public void setNumHumans(int numHumans) {
        this.numHumans = numHumans;
    }

    public void setNumSoldiers(int numSoldiers) {
        this.numSoldiers = numSoldiers;
    }
    
    
    
    
}
