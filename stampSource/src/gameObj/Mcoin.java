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
public class Mcoin extends Sprite {
    // コインをとったときの音
    private AudioClip sound;

    public Mcoin(double x, double y, String fileName, Map map) {
        super(x, y, fileName, map,96,96);

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
