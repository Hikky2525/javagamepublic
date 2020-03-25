package stampScreen;

import java.applet.Applet;
import dataModel.*;
import gameObj.*;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Iterator;
import java.util.LinkedList;

import javax.swing.JLabel;
import javax.swing.JPanel;

import common.Common;

import java.applet.AudioClip;
/*
 * Created on 2005/06/06
 *
 */

/**
 * @author mori
 *
 */
public class Game extends Gamen implements Runnable, KeyListener {
    // パネルサイズ
    public static final int WIDTH = 1920;
    public static final int HEIGHT = 1024;
    private AudioClip sound;
    private static Score score = Score.getInstance();
    private Data_keep DataKeep = Data_keep.getInstance();
    private long start;
    Color cl  = new Color(255, 255, 255);
    Font font1 = new Font("MS明朝", Font.PLAIN, 60);
    private boolean running = true;
    // マップ
    private Map map;


    private Image image,waku;

    // プレイヤー
    private Player player;
    private Kuribo kuribo;

    // キーの状態（押されているか、押されてないか）
    private boolean leftPressed;
    private boolean rightPressed;
    private boolean upPressed;

    private LinkedList sprites;
    int coin_cnt=0;

    // ゲームループ用スレッド
    private Thread gameLoop;
    //private Image image;
    public Game() {

        // パネルの推奨サイズを設定、pack()するときに必要
        setPreferredSize(new Dimension(WIDTH, HEIGHT));
        // パネルがキー入力を受け付けるようにする
        setFocusable(true);
     // イメージを読み込む
        image = Toolkit.getDefaultToolkit().getImage(getClass().getResource("image/back.jpg"));
        waku = Toolkit.getDefaultToolkit().getImage(getClass().getResource("image/waku.png"));
        Parts parts = Parts.getInstance();
		map = new Map(parts.getMap());


        // プレイヤーを作成
        player = new Player(192, 1728, "player.gif", map,128,128);

        // キーイベントリスナーを登録
        addKeyListener(this);

        // ゲームループ開始
        gameLoop = new Thread(this);
        gameLoop.start();

    }
    public void start(){

    }
    /**
     * ゲームオーバー
     */
    public void gameOver() {

        // マップを作成

    	//map = new Map("map01.dat");
        //coin_cnt =0;
        // プレイヤーを作成
        player = new Player(192, 1088, "player.gif", map,128,128);
    }

    public void stop(){
    	running = false;
    }
    public long timeStart(){
    	this.start = System.currentTimeMillis();//ゲーム時間測定開始
    	return start;
    }

    public void Over() {

        // マップを作成



    		map = new Map("map01.dat");

        //coin_cnt =0;
        // プレイヤーを作成
        player = new Player(192, 608, "player.gif", map,128,128);
    }
    /**
     * ゲームループ
     */
    public void run() {

        while (running) {


            if (leftPressed) {
                // 左キーが押されていれば左向きに加速
                player.accelerateLeft();

            } else if (rightPressed) {
                // 右キーが押されていれば右向きに加速
                player.accelerateRight();
            } else {
                // 何も押されてないときは停止
                player.stop();
            }
            if (upPressed) {
                // ジャンプする
                player.jump();
            }
            // プレイヤーの状態を更新
            player.update();


            // マップにいるスプライトを取得
            LinkedList sprites = map.getSprites();
            Iterator iterator = sprites.iterator();
            while (iterator.hasNext()) {
                Sprite sprite = (Sprite)iterator.next();

                // スプライトの状態を更新する
                sprite.update();

                // プレイヤーと接触してたら
                if (player.isCollision(sprite)) {
                	if(sprite instanceof Mcoin){
                    	Mcoin mcoin = (Mcoin)sprite;
                        // コインは消える
                        sprites.remove(mcoin);
                        //coin_cnt++;
                        score.add(5);
                        // ちゃり～ん
                        mcoin.play();
                        // spritesから削除したので
                        // breakしないとiteratorがおかしくなる
                        break;
                	}
                    if (sprite instanceof Coin) {  // コイン
                        Coin coin = (Coin)sprite;
                        // コインは消える
                        sprites.remove(coin);
                        coin_cnt++;
                        score.add(1);
                        // ちゃり～ん
                        coin.play();
                        // spritesから削除したので
                        // breakしないとiteratorがおかしくなる
                        break;

                    } else if (sprite instanceof Kuribo) {  // 栗ボー
                        Kuribo kuribo = (Kuribo)sprite;
                        // 上から踏まれてたら
                        if ((int)player.getY() < (int)kuribo.getY()) {
                            // 栗ボーは消える
                            sprites.remove(kuribo);
                            //sprites.add(new Kuribo(32, 32, "kuribo.gif", map));
                            // サウンド
                            kuribo.play();
                            // 踏むとプレイヤーは再ジャンプ
                            player.setForceJump(true);
                            player.jump();
                            score.add(1);
                            break;
                        } else {
                            // ゲームオーバー

                            gameOver();
                        }

                    }else if (sprite instanceof Worp) {
                    	Worp worp = (Worp)sprite;
                    	if((int)player.getY() < (int)worp.getY()-16){
                    		Over();
                    		//gameOver引用するだけ
                    	}
                    }
                }

            }

            // 再描画
            repaint();

            // 休止
            try {
                Thread.sleep(20);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 描画処理
     *
     * @param 描画オブジェクト
     */
    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        // 背景を黒で塗りつぶす
        g.setColor(Color.BLACK);
        g.fillRect(0, 0, getWidth(), getHeight());
        g.drawImage(image,0,0,this);

        // X方向のオフセットを計算
        int offsetX = Game.WIDTH / 2 - (int)player.getX();
        // マップの端ではスクロールしないようにする
        offsetX = Math.min(offsetX, 0);
        offsetX = Math.max(offsetX, Game.WIDTH - map.getWidth());

        // Y方向のオフセットを計算
        int offsetY = Game.HEIGHT / 2 - (int)player.getY();
        // マップの端ではスクロールしないようにする
        offsetY = Math.min(offsetY, 0);
        offsetY = Math.max(offsetY, Game.HEIGHT - map.getHeight());
       // g.drawImage(image, offsetX, offsetY, this);
        // マップを描画
        map.draw(g, offsetX, offsetY);



        g.setColor(cl);
        g.setFont(font1);
        long byou = Common.GAMETIME * 1000;
        long goal = System.currentTimeMillis() - start ;
        byou =  (byou - goal);
        int byo = (int) (byou/1000);
        //String hyouji = "のこり" + byo + " びょう";
        String s = "   " + byo;
        String hyouji = "のこり" + s.substring(s.length()-3) + " びょう";

        //String hyouji = "のこり" + String.format("%02d", byo) + " びょう";

        // プレイヤーを描画
        player.draw(g, offsetX, offsetY);

        // スプライトを描画
        // マップにいるスプライトを取得
        LinkedList sprites = map.getSprites();
        Iterator iterator = sprites.iterator();
        while (iterator.hasNext()) {
            Sprite sprite = (Sprite)iterator.next();
            sprite.draw(g, offsetX, offsetY);

        }
        g.drawImage(waku,990,0,this);
        g.drawString(hyouji, 1400,55);
        g.drawString(score.GetToScore(), 1000,55);
    }

    /**
     * キーが押されたらキーの状態を「押された」に変える
     *
     * @param e キーイベント
     */
    public void keyPressed(KeyEvent e) {
        int key = e.getKeyCode();

        if (key == KeyEvent.VK_LEFT) {
            leftPressed = true;
        }
        if (key == KeyEvent.VK_RIGHT) {
            rightPressed = true;
        }
        if (key == KeyEvent.VK_UP) {
            upPressed = true;
        }
    }

    /**
     * キーが離されたらキーの状態を「離された」に変える
     *
     * @param e キーイベント
     */
    public void keyReleased(KeyEvent e) {
        int key = e.getKeyCode();

        if (key == KeyEvent.VK_LEFT) {
            leftPressed = false;
        }
        if (key == KeyEvent.VK_RIGHT) {
            rightPressed = false;
        }

        if (key == KeyEvent.VK_UP) {
            upPressed = false;
        }
    }

    public void keyTyped(KeyEvent e) {
    }
}
