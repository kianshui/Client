import java.util.Random;
import java.awt.*;
import java.awt.image.*;
import java.io.*;
import javax.swing.*;

public class Screen extends JPanel implements Runnable{		//interface
	public Thread thread = new Thread(this);
	
	public static Image[] tileset_ground = new Image [100];
	public static Image[] tileset_air = new Image [100];
	public static Image[] tileset_res = new Image[100];
	public static Image[] tileset_mob = new Image[100];		
	public static Image[] gameOver = new Image[100]; 
	
	public static int myWidth, myHeight;
	public static int coinage, health;
	public static int killed = 0, killsToWin = 0, level = 1, maxLevel = 3;
	public static int winTime = 3300, winFrame = 0;
	
	public static boolean isFirst = true;
	public static boolean isDebug = false;
	public static boolean isWin = false;
	
	public static Point mse = new Point(0,0);
	
	public static Room room;
	public static Save save;
	public static Store store;
	
	public static Mob[] mobs= new Mob[100];
	
	public Screen(Frame frame) {		
		frame.addMouseListener(new KeyHandel());
		frame.addMouseMotionListener(new KeyHandel());
		//setBackground(Color.PINK);
	
		thread.start();
	}
	
	public static void hasWon() {
		if(killed == killsToWin) {
			isWin = true;

		}else{
			isWin = false;
		}
	}
	
	public void define() {
		room = new Room();
		save = new Save();
		store = new Store();

		killed = 0;
		coinage = 20;
		health = 10;
		
		for(int i=0; i<tileset_ground.length;i++) {	//creating a image and cropping it
			tileset_ground[i] = new ImageIcon("res/tileset_ground.png").getImage();
			tileset_ground[i] = createImage(new FilteredImageSource(tileset_ground[i].getSource(), new CropImageFilter(0, 26*i, 26, 26)));
		}
		for(int i=0; i<tileset_air.length;i++) {
			tileset_air[i] = new ImageIcon("res/tileset_air.png").getImage();
			tileset_air[i] = createImage(new FilteredImageSource(tileset_air[i].getSource(), new CropImageFilter(0, 26*i, 26, 26)));
		}
		
		tileset_res[0] = new ImageIcon("res/cell.png").getImage();
		tileset_res[1] = new ImageIcon("res/heart.png").getImage();
		tileset_res[2] = new ImageIcon("res/coin.png").getImage();
		tileset_res[3] = new ImageIcon("res/blood.png").getImage();

		tileset_mob[0] = new ImageIcon("res/mob.png").getImage();
		
		gameOver[0] = new ImageIcon("res/gameOver.png").getImage();
		
		save.loadSave(new File("save/mission" + level));
		
		for(int i=0; i<mobs.length;i++) {
			mobs[i] = new Mob();
		}
		
	}
	
	public void paintComponent(Graphics g) {
		if (isFirst) {
			myWidth = getWidth();
			myHeight = getHeight();
			define();
			
			isFirst = false;
		}
		
		//g.clearRect(0, 0, getWidth(), getHeight()); //clearScreen
	
		g.setColor(new Color(70, 70, 70));
		g.fillRect(0, 0, getWidth(), getHeight());

		//side line of the game, just to look nicer
		g.setColor(new Color(0, 0, 0));
		g.drawLine(room.block[0][0].x-1, 0, room.block[0][0].x-1, room.block[room.worldHeight-1][0].y + room.blockSize);	//Drawing the left line
		g.drawLine(room.block[0][room.worldWidth-1].x + room.blockSize, 0, room.block[0][room.worldWidth-1].x + room.blockSize, room.block[room.worldHeight-1][0].y + room.blockSize);	//Drawing the right line
		g.drawLine(room.block[0][0].x, room.block[room.worldHeight-1][0].y + room.blockSize, room.block[0][room.worldWidth-1].x + room.blockSize, room.block[room.worldHeight-1][0].y + room.blockSize); //Drawing the bottom line


		room.draw(g); 	//Drawing the room
		//draw the monster
		for(int i=0;i<mobs.length;i++) {
			if(mobs[i].inGame) {
				mobs[i].draw(g);
				
			}
		}

		store.draw(g);	//Drawing the store
		
		//GameOver screen
		if(health <1) {
			g.setColor(new Color(240, 20, 20));
			g.fillRect(0, 0, myWidth, myHeight);
			g.setColor(new Color(255, 255, 255));
			g.setFont(new Font("Courier new", Font.BOLD, 40));
			g.drawString("Game Over, Unlucky...", 50, myHeight/2);
			//Game over background
			//g.clearRect(0, 0, getWidth(), getHeight()); //clearScreen

			//g.drawImage(Screen.gameOver[0], 0, 0, getWidth(), getHeight(),null);
		}
		
		if(isWin) {
			g.setColor(new Color(255, 255, 255));
			g.fillRect(0, 0, getWidth(), getHeight());
			g.setColor(new Color(161, 18, 136));
			g.setFont(new Font("Courier new", Font.BOLD, 35));
			if(level == maxLevel) {
				g.drawString("You won the whole game!", 50, myHeight/2-30);
				g.drawString("Window closing...", 50, myHeight/2+30);
			} else {
				g.drawString("You won! Congratulation!", 50, myHeight/2-30);
				g.drawString("Loading next level...", 50, myHeight/2+30);
			}
		}


		g.setColor(new Color(38, 32, 142));
		g.setFont(new Font("Courier new", Font.BOLD, 20));
		g.drawString("Enemy " + killed +"/"+ killsToWin , myWidth-170, 20);
	}
	
	public int spawnTime = 2400, spawnFrame = 0;
	//spawnFrame will increment until reaches spawnTime to call mobSpawner()
	public void mobSpawner() {
		if (spawnFrame >= spawnTime) {
			//monster will spawn according to the killsToWin
			for (int i = 0; i < killsToWin; i++) {

				if (!mobs[i].inGame) {
					mobs[i].spawnMob(Value.mobBlue);
					break;
				}
			}
			spawnFrame = 0;
		} else {
			spawnFrame += 1;
		}

	}
	
	public void run() {
		while(true) {
			if(!isFirst && health > 0 && !isWin) {
				room.physic();
				mobSpawner();
				for(int i=0;i<mobs.length;i++) {
					if(mobs[i].inGame){
						mobs[i].physic(); //<< the walking of mob
					}
				}
			} else {
				if(isWin) {
					if(winFrame >= winTime) {
						if(level == maxLevel) {
							System.exit(0);
						} else {
							level += 1;
							define();
							save.loadSave(new File("save/mission" + level));
							isWin = false;
						}
						
						winFrame = 0;
					} else {
						winFrame += 1;
					}
				}
			}
			
			repaint();
			
			try {
				Thread.sleep(1);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
}
