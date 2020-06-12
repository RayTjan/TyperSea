
import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.event.*;
import sun.audio.AudioPlayer;
import sun.audio.AudioStream;

public class TyperSeaV2 extends JFrame {

    AudioStream audio = null;
    JButton b = null;
    JPanel p = null;
    JTextField tf = null;
    JLabel l = null;
    JLabel l2 = null;
    int score = 0;
    int life = 6;
    boolean end = false;
    boolean last = false;
    int dx = 0;
    int dy = 100;
    long startTime = 0;
    long endTime = 0;
    long WPM = 0;
    boolean first = true;
    InputStream music;
    ArrayList<String> wordlist;
    TyperSeaV2.WordRun word1 = null;
    TyperSeaV2.WordRun word2 = null;
    TyperSeaV2.WordRun word3 = null;

    public TyperSeaV2() {

        super("TyperSea");//the task
        setSize(1300, 918);
        setLayout(new BorderLayout());

        b = new JButton(" START HERE! ");
        b.addActionListener(new TyperSeaV2.ButtonHandler(this));
        b.setPreferredSize(new Dimension(1200, 30));
        add(b, BorderLayout.NORTH);

        // WPM counter
        // disappear start after first Click
        // what makes my program different, WPM!S
        // confirm to teacher for me to change report

        p = new TyperSeaV2.DrawPanel();
        p.setPreferredSize(new Dimension(1200, 818));
        add(p, BorderLayout.CENTER);

        JPanel p2 = new JPanel();
        p2.setLayout(new FlowLayout());

        l2 = new JLabel("TYPE HERE!");
        p2.add(l2);

        tf = new JTextField(12);
        tf.getDocument().addDocumentListener(new TyperSeaV2.ListenText());//input
        p2.add(tf);

        l = new JLabel("Your Score : " + score + "");
        p2.add(l);

        add(p2, BorderLayout.SOUTH);

        wordlist = new ArrayList<String>();//File Reader from Auriga for easier code???
        //a
        wordlist.add("abandon");
        wordlist.add("abate");
        wordlist.add("abbreviate");
        wordlist.add("abdicate");
        wordlist.add("aberration");
        wordlist.add("abhorrence");
        wordlist.add("ability");
        wordlist.add("ablaze");
        wordlist.add("abnormal");
        wordlist.add("aboard");
        //b
        wordlist.add("boomer");
        wordlist.add("before");
        wordlist.add("between");
        wordlist.add("become");
        wordlist.add("billion");
        wordlist.add("behind");
        wordlist.add("board");
        wordlist.add("budget");
        wordlist.add("brother");
        wordlist.add("break");
        //c
        wordlist.add("capital");
        wordlist.add("crab");
        wordlist.add("chance");
        wordlist.add("create");
        wordlist.add("center");
        wordlist.add("church");
        wordlist.add("collection");
        wordlist.add("college");
        wordlist.add("current");
        wordlist.add("continue");
        //d
        wordlist.add("dark");
        wordlist.add("data");
        wordlist.add("deep");
        wordlist.add("dork");
        wordlist.add("death");
        wordlist.add("decade");
        wordlist.add("dive");
        wordlist.add("dodge");
        wordlist.add("dream");
        wordlist.add("drug");
        //e
        wordlist.add("each");
        wordlist.add("early");
        wordlist.add("entire");
        wordlist.add("every");
        wordlist.add("eclipse");
        wordlist.add("exort");
        wordlist.add("euphoria");
        wordlist.add("envy");
        wordlist.add("eye");
        wordlist.add("explain");
        //f
        wordlist.add("fear");
        wordlist.add("far");
        wordlist.add("film");
        wordlist.add("fish");
        wordlist.add("fried");
        wordlist.add("forward");
        wordlist.add("frown");
        wordlist.add("fuel");
        wordlist.add("fridge");
        wordlist.add("fillet");
        //g
        wordlist.add("green");
        wordlist.add("gas");
        wordlist.add("goal");
        wordlist.add("great");
        wordlist.add("growth");
        wordlist.add("gale");
        wordlist.add("girl");
        wordlist.add("general");
        wordlist.add("guess");
        wordlist.add("ground");
        //h
        wordlist.add("hand");
        wordlist.add("happen");
        wordlist.add("hold");
        wordlist.add("history");
        wordlist.add("hospital");
        wordlist.add("health");
        wordlist.add("human");
        wordlist.add("horrid");
        wordlist.add("hotel");
        wordlist.add("half");
        //i
        wordlist.add("idea");
        wordlist.add("inside");
        wordlist.add("involve");
        wordlist.add("issue");
        wordlist.add("item");
        wordlist.add("image");
        wordlist.add("improve");
        wordlist.add("indeed");
        wordlist.add("inept");
        wordlist.add("impact");
        //j
        wordlist.add("joey");
        wordlist.add("just");
        wordlist.add("join");
        wordlist.add("japan");
        wordlist.add("jungle");
        wordlist.add("jumbo");
        wordlist.add("jitter");
        wordlist.add("jane");
        wordlist.add("joke");
        wordlist.add("jam");
        //k
        wordlist.add("kill");
        wordlist.add("kid");
        wordlist.add("kitchen");
        wordlist.add("know");
        wordlist.add("kind");
        wordlist.add("kelp");
        wordlist.add("kart");
        wordlist.add("kevin");
        wordlist.add("kite");
        wordlist.add("keanu");
        //l
        wordlist.add("lane");
        wordlist.add("love");
        wordlist.add("lawyer");
        wordlist.add("lend");
        wordlist.add("level");
        wordlist.add("laundry");
        wordlist.add("listless");
        wordlist.add("live");
        wordlist.add("loss");
        wordlist.add("line");
        //m
        wordlist.add("machine");
        wordlist.add("marriage");
        wordlist.add("make");
        wordlist.add("malicious");
        wordlist.add("moral");
        wordlist.add("manager");
        wordlist.add("maintain");
        wordlist.add("music");
        wordlist.add("major");
        wordlist.add("memory");
        //n
        wordlist.add("nano");
        wordlist.add("nutrition");
        wordlist.add("nerd");
        wordlist.add("nothing");
        wordlist.add("notice");
        wordlist.add("need");
        wordlist.add("never");
        wordlist.add("nice");
        wordlist.add("note");
        wordlist.add("news");
        //o
        wordlist.add("oil");
        wordlist.add("often");
        wordlist.add("official");
        wordlist.add("origin");
        wordlist.add("oyster");
        wordlist.add("omen");
        wordlist.add("once");
        wordlist.add("occur");
        wordlist.add("onto");
        wordlist.add("operate");
        //p
        wordlist.add("person");
        wordlist.add("pole");
        wordlist.add("postpone");
        wordlist.add("pleaides");
        wordlist.add("pokemon");
        wordlist.add("parent");
        wordlist.add("period");
        wordlist.add("problem");
        wordlist.add("peer");
        wordlist.add("private");
        //p
        wordlist.add("person");
        wordlist.add("pole");
        wordlist.add("postpone");
        wordlist.add("pleaides");
        wordlist.add("pokemon");
        wordlist.add("parent");
        wordlist.add("period");
        wordlist.add("problem");
        wordlist.add("peer");
        wordlist.add("private");
        //q
        wordlist.add("quote");
        wordlist.add("quill");
        wordlist.add("quality");
        wordlist.add("quack");
        wordlist.add("quiz");
        wordlist.add("quarter");
        wordlist.add("quarrel");
        wordlist.add("quirk");
        wordlist.add("quick");
        wordlist.add("qualify");
        //r
        wordlist.add("ray");
        wordlist.add("rat");
        wordlist.add("race");
        wordlist.add("robot");
        wordlist.add("role");
        wordlist.add("ruin");
        wordlist.add("rune");
        wordlist.add("reign");
        wordlist.add("robust");
        wordlist.add("ridiculous");
        //s
        wordlist.add("sorry");
        wordlist.add("sea");
        wordlist.add("soar");
        wordlist.add("sausage");
        wordlist.add("sober");
        wordlist.add("seagull");
        wordlist.add("serial");
        wordlist.add("sans");
        wordlist.add("second");
        wordlist.add("supposedly");
        //t
        wordlist.add("tomb");
        wordlist.add("turn");
        wordlist.add("tutor");
        wordlist.add("typer");
        wordlist.add("talk");
        wordlist.add("target");
        wordlist.add("toll");
        wordlist.add("terrain");
        wordlist.add("turnip");
        wordlist.add("tumeric");
        //u
        wordlist.add("uncanny");
        wordlist.add("umbrella");
        wordlist.add("unknown");
        wordlist.add("UFO");
        wordlist.add("ugly");
        wordlist.add("urge");
        wordlist.add("utility");
        wordlist.add("uniform");
        wordlist.add("useless");
        wordlist.add("urgent");
        //v
        wordlist.add("vegan");
        wordlist.add("vowel");
        wordlist.add("vain");
        wordlist.add("value");
        wordlist.add("verge");
        wordlist.add("virtue");
        wordlist.add("viable");
        wordlist.add("vintage");
        wordlist.add("vengeful");
        wordlist.add("velocity");
        //w
        wordlist.add("whale");
        wordlist.add("world");
        wordlist.add("weird");
        wordlist.add("wiggly");
        wordlist.add("werewolf");
        wordlist.add("wingman");
        wordlist.add("wine");
        wordlist.add("wurst");
        wordlist.add("water");
        wordlist.add("weapon");
        //y
        wordlist.add("yeet");
        wordlist.add("yard");
        wordlist.add("young");
        wordlist.add("yeah");
        wordlist.add("youth");
        wordlist.add("yield");
        wordlist.add("yell");
        wordlist.add("yeast");
        wordlist.add("yogurt");
        wordlist.add("yacht");
        //x
        wordlist.add("xylophone");
        wordlist.add("xenophobia");
        wordlist.add("xenonite");
        wordlist.add("xenon");
        wordlist.add("xylotomy");
        wordlist.add("xiphois");
        wordlist.add("xerorxes");
        wordlist.add("xyloid");
        wordlist.add("xanthin");
        wordlist.add("xenic");
        //z
        wordlist.add("zeus");
        wordlist.add("zombie");
        wordlist.add("zebra");
        wordlist.add("zigzag");
        wordlist.add("zeppelin");
        wordlist.add("zootomic");
        wordlist.add("zinger");
        wordlist.add("zygoid");
        wordlist.add("ziplock");
        wordlist.add("zaffars");
        //randoms
        wordlist.add("yusuf");
        wordlist.add("krismadi");
        wordlist.add("devy");
        wordlist.add("bono");
        wordlist.add("santoso");
        word1 = new TyperSeaV2.WordRun();
        word2 = new TyperSeaV2.WordRun();
        word3 = new TyperSeaV2.WordRun();


        word1.st = null;
        word2.st = null;
        word3.st = null;

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        show();
    }

    class DrawPanel extends JPanel {

        @Override
        public void paintComponent(Graphics g) {

            int Hscore = 0;
            File file = new File("data/HighScore.txt");
            BufferedReader br;
            try {
                br = new BufferedReader(new FileReader(file));
                try {
                    Hscore = Integer.parseInt(br.readLine());
                } catch (IOException ex) {
                    Logger.getLogger(TyperSeaV2.class.getName()).log(Level.SEVERE, null, ex);
                }
            } catch (FileNotFoundException ex) {
                Logger.getLogger(TyperSeaV2.class.getName()).log(Level.SEVERE, null, ex);
            }

            super.paintComponent(g);
            Graphics2D g2 = (Graphics2D) g;
            Font f = new Font("Monospaced", Font.BOLD, 30);
            g2.setColor(Color.BLACK);
            g2.setFont(f);
            Font Gover = new Font("Monospaced", Font.BOLD, 100);
            Font big = new Font("Monospaced", Font.BOLD, 75);
            Font smol = new Font("Monospaced", Font.BOLD, 35);
            String background = "data/sea.png";
            String health = "data/heart.png";
            String character = "data/dive2.png";
            String rope = "data/rope.png";
            String imagePath = "data/shak2.gif";
            Color c = new Color(0f, 0f, 0f, .6f);

            try {
                BufferedImage myPicture = ImageIO.read(new File(background));
                g2.drawImage(myPicture, 0, 0, 1815, 818, this);
            } catch (IOException ex) {
                Logger.getLogger(TyperSeaV2.class.getName()).log(Level.SEVERE, null, ex);
            }

            if (word1.st != null) {
                try {
                    BufferedImage myPicture = ImageIO.read(new File(imagePath));
                    g2.drawImage(myPicture, word1.posx - 105, word1.posy - 75, 350, 142, this);
                } catch (IOException ex) {
                    Logger.getLogger(TyperSeaV2.class.getName()).log(Level.SEVERE, null, ex);
                }
                g2.drawString(word1.st, word1.posx, word1.posy);
            }
            if (word2.st != null) {
                try {
                    BufferedImage myPicture = ImageIO.read(new File(imagePath));
                    g2.drawImage(myPicture, word2.posx - 105, word2.posy - 75, 350, 142, this);
                } catch (IOException ex) {
                    Logger.getLogger(TyperSeaV2.class.getName()).log(Level.SEVERE, null, ex);
                }
                g2.drawString(word2.st, word2.posx, word2.posy);
            }
            if (word3.st != null) {
                try {
                    BufferedImage myPicture = ImageIO.read(new File(imagePath));
                    g2.drawImage(myPicture, word3.posx - 105, word3.posy - 75, 350, 142, this);
                } catch (IOException ex) {
                    Logger.getLogger(TyperSeaV2.class.getName()).log(Level.SEVERE, null, ex);
                }
                g2.drawString(word3.st, word3.posx, word3.posy);
            }

            if (life > 0) {//health

                try {//ROPE
                    BufferedImage myPicture = ImageIO.read(new File(rope));
                    g2.drawImage(myPicture, -69, -10, 400, 533, this);
                } catch (IOException ex) {
                    Logger.getLogger(TyperSeaV2.class.getName()).log(Level.SEVERE, null, ex);
                }
                try {//DIVER
                    BufferedImage myPicture = ImageIO.read(new File(character));
                    g2.drawImage(myPicture, dx, dy, 400, 533, this);
                } catch (IOException ex) {
                    Logger.getLogger(TyperSeaV2.class.getName()).log(Level.SEVERE, null, ex);
                }
                try {
                    BufferedImage myPicture = ImageIO.read(new File(health));
                    g2.drawImage(myPicture, 10, 10, 75, 75, this);
                } catch (IOException ex) {
                    Logger.getLogger(TyperSeaV2.class.getName()).log(Level.SEVERE, null, ex);
                }
                if (life > 1) {//HEALTH
                    try {
                        BufferedImage myPicture = ImageIO.read(new File(health));
                        g2.drawImage(myPicture, 95, 10, 75, 75, this);
                    } catch (IOException ex) {
                        Logger.getLogger(TyperSeaV2.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    if (life > 2) {
                        try {
                            BufferedImage myPicture = ImageIO.read(new File(health));
                            g2.drawImage(myPicture, 180, 10, 75, 75, this);
                        } catch (IOException ex) {
                            Logger.getLogger(TyperSeaV2.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    }
                }
            }
            if (last == false) {
                g2.setColor(c);
                g2.fillRect(0, 0, 1400, 1018);
                g2.setFont(smol);
                g2.setColor(Color.WHITE);
                g2.drawString("START HERE", 540, 29);
                g2.setFont(big);
                g2.drawString("Welcome to TyperSea", 230, 414);
                g2.setFont(smol);
                g2.drawString("TYPE HERE", 540, 808);
            }
            if (end == true) {

                g2.setColor(c);
                g2.fillRect(0, 0, 1400, 818);

                g2.setColor(Color.WHITE);
                if (score == 0) {
                    score = 1;
                }

                WPM = score / ((System.currentTimeMillis() - startTime) / 6000);
                g2.setFont(smol);
                g2.drawString("RETRY HERE", 540, 29);
                g2.setFont(Gover);
                g2.drawString("GAME OVER", 380, 330);
                if (score == 1) {
                    score = 0;
                    WPM = 0;
                }
                g2.setFont(smol);
                if (score > Hscore) {
                    FileWriter writer;
                    try {
                        writer = new FileWriter("data/HighScore.txt");
                        writer.write(String.valueOf(score));
                        writer.close();
                    } catch (IOException ex) {
                        Logger.getLogger(TyperSeaV2.class.getName()).log(Level.SEVERE, null, ex);
                    }

                    g2.drawString("High Score :" + score, 500, 530);
                } else {
                    g2.drawString("High Score :" + Hscore, 500, 530);
                }
                g2.drawString("Your Score :" + score, 500, 420);
                g2.drawString("WPM :" + (int) WPM * 2, 500, 480);
                last = true;
            }
        }
    }

    class WordRun implements Runnable {

        String st = null;
        int posx;
        int posy;
        boolean shift = true;
        int time = 10;

        public void run() {
            if (first == true) {
                startTime = System.currentTimeMillis();
                first = false;
            }

            try {
                Random rand = null;//if reach the end or deleted
                while (true) {
                    if (end == true) {
                        endTime = System.currentTimeMillis();
                        continue;
                    }
                    if ((st == null) || (posx <= 270)) {
                        if (posx <= 270) {
                            if (life < 4) {
                                try {
                                    music = new FileInputStream(new File("data/oof2.wav"));
                                    AudioStream audio1 = new AudioStream(music);
                                    AudioPlayer.player.start(audio1);
                                } catch (Exception j) {
                                }
                            }

                            life--;

                            repaint();
                        }
                        if (life < 1) {
                            end = true;
                        }
                        rand = new Random();
                        st = wordlist.get(rand.nextInt(265));
                        posx = 1500 + rand.nextInt(240);
                        if (score > 250) {
                            time /= 1.00000000000000012;//hard
                        } else {
                            //time /= 1.00000000000000002212;//normal
                            time /= 1.00000000000000010912;//normal
                        }
                        do {
                            posy = rand.nextInt(p.getHeight() - 200) + 100;
                        } while (posy > (p.getHeight()));
                        tf.setText("");
                    } else {
                        Thread.currentThread().sleep(time);
                        posx--;

                        if (shift == true && posx % 45 == 0) {
                            if (posx % 45 == 0) {
                                dx -= 1;
                                dy -= 1;
                            } else if (posx % 30 == 0) {
                                dx -= 1;
                                dy += 1;
                            }
                            shift = false;
                        } else if (shift == false && posx % 45 == 0) {
                            if (posx % 45 == 0) {
                                dx += 1;
                                dy += 1;
                            } else if (posx % 30 == 0) {
                                dx += 1;
                                dy -= 1;
                            }
                            shift = true;
                        }

                    }
                    repaint();
                }
            } catch (Exception e) {
            }
        }
    }

    class ButtonHandler implements ActionListener {

        TyperSeaV2 t;

        public ButtonHandler(TyperSeaV2 obj) {
            t = obj;

        }

        public void actionPerformed(ActionEvent e) {
            Thread t1 = new Thread(word1);
            Thread t2 = new Thread(word2);
            Thread t3 = new Thread(word3);
            try {
                if (e.getActionCommand() == " START HERE! " && last == false) {
                    last = true;
                    t1.start();
                    t2.start();
                    t3.start();
                    music = new FileInputStream(new File("data/UndertheSea.wav"));
                    audio = new AudioStream(music);
                    AudioPlayer.player.start(audio);
                } else if (e.getActionCommand() == " START HERE! " && last == true) {
                    AudioPlayer.player.stop(audio);// why won't you work
                    t.dispose();
                    t = new TyperSeaV2();
                }
            } catch (Exception j) {
            }

        }
    }

    class ListenText implements DocumentListener {

        @Override
        public void changedUpdate(DocumentEvent e) {
        }

        @Override
        public void removeUpdate(DocumentEvent e) {
        }

        @Override
        public void insertUpdate(DocumentEvent e) {
            if (tf.getText().equals(word1.st)) {
                try {
                    music = new FileInputStream(new File("data/oof.wav"));
                    AudioStream audio2 = new AudioStream(music);
                    AudioPlayer.player.start(audio2);
                } catch (Exception j) {
                }
                word1.st = null;
                word1.posx = 1500;
                score += 10;
                l.setText("Your Score : " + score + "");
            }
            if (tf.getText().equals(word2.st)) {
                try {
                    music = new FileInputStream(new File("data/oof.wav"));
                    AudioStream audio2 = new AudioStream(music);
                    AudioPlayer.player.start(audio2);
                } catch (Exception j) {
                }
                word2.st = null;
                word2.posx = 1500;
                score += 10;
                l.setText("Your Score : " + score + "");
            }
            if (tf.getText().equals(word3.st)) {
                try {
                    music = new FileInputStream(new File("data/oof.wav"));
                    AudioStream audio2 = new AudioStream(music);
                    AudioPlayer.player.start(audio2);
                } catch (Exception j) {
                }
                word3.st = null;
                word3.posx = 1500;
                score += 10;
                l.setText("Your Score : " + score + "");
            }
        }
    }

    public static void main(String[] args) {
        TyperSeaV2 ap = new TyperSeaV2();

    }
}