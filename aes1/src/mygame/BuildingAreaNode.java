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
import com.jme3.math.Vector3f;
import com.jme3.scene.Geometry;
import com.jme3.scene.shape.Box;

/**
 *
 * @author armageddon
 */
public class BuildingAreaNode extends AreaNode {
    
        //generation parameters
    static final int AREA_GEN_SHELTER_MAX =100;
    static final int AREA_GEN_HUMANS_MAX  =40;
    static final int AREA_GEN_ZOMBIES_MAX =5;
    
    float building_height = 0.3f;
    
    public BuildingAreaNode(Vector2f pos, Vector2f size) {
        super(pos,size);
        
        //Basic Box
        //Box b = new Box(size.x-AREA_PADDING*2, size.y-AREA_PADDING*2, building_height);
        Box b = new Box(new Vector3f(0,0,0), new Vector3f(size.x-AREA_PADDING*2, size.y-AREA_PADDING*2, building_height));
        Geometry geom = new Geometry("Box", b);
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
        statusText.setLocalTranslation(AREA_FONT_PADDING, size.y-AREA_FONT_PADDING, building_height + 0.02f);
        attachChild(statusText);
    }
    
    @Override
    public void updateGraphics(float tpf){
        statusText.setLocalTranslation(AREA_FONT_PADDING, size.y-AREA_FONT_PADDING, building_height + 0.02f);
        statusText.setText(String.format("Z: %d\nH: %d\nAS: %d\nHS: %d\nS: %d", numZombies, numHumans, numAvailShelters, numHumansInShelters, numSoldiers));
    }
}
