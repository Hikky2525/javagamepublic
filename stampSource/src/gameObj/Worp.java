package gameObj;

import java.applet.Applet;
import java.applet.AudioClip;

/*
 * Created on 2005/06/24
 *
 */

/**
 * @author mori
 *
 */
public class Worp extends Sprite {
    // コインをとったときの音
    private AudioClip sound;

    public Worp(double x, double y, String fileName, Map map,int w,int h) {
        super(x, y, fileName, map,w,h);

        // サウンドをロード
        sound = Applet.newAudioClip(getClass().getResource("se/coin03.wav"));
    }

    public void update() {
    }

    /**
     * サウンドを再生
     */
    public void play() {
        sound.play();
    }
}