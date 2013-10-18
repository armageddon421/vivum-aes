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
import com.jme3.scene.shape.Quad;

/**
 *
 * @author armageddon
 */
public class StreetAreaNode extends AreaNode {
    
    
    public StreetAreaNode(Vector2f pos, Vector2f size) {
        super(pos,size);
        
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
        
    }
}
