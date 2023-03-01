package com.example.demo.game.window;

import com.example.demo.game.App;
import com.example.demo.game.perso.Perso;
import com.example.demo.game.perso.Warrior;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import java.util.Objects;

public class Animation {

    private BufferedImage[] idleAnim , runAnim , chestAnim, demonAnim, littleDemonANim;
    private BufferedImage image, dragonImage;
    private int map = 64;


    public Animation() {
        this.idleAnim = new BufferedImage[3];
        this.runAnim = new BufferedImage[3];
        this.chestAnim = new BufferedImage[3];
        this.demonAnim = new BufferedImage[3];
        this.littleDemonANim = new BufferedImage[3];
    }

    public void importImg() {
        InputStream is = getClass().getResourceAsStream("/player_sprites.png");
        try {
            this.image = ImageIO.read(Objects.requireNonNull(is));
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                assert is != null;
                is.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }


    public BufferedImage[] getLittleDemonANim() {
        return littleDemonANim;
    }

    public void litteDemon() {
        for (int i = 0; i < littleDemonANim.length; i++) {
            this.littleDemonANim[i] = this.image.getSubimage(i * 16 + 368, 328, 16, 28);
        }
    }
    public void idleAnim() {
        Perso player = App.getInstance().getPersonnage();
        if (player instanceof Warrior) {
            for (int i = 0; i < idleAnim.length; i++) {
                this.idleAnim[i] = this.image.getSubimage(i * 16 + 128, 100, 16, 28);
            }
        } else {
            for (int i = 0; i < idleAnim.length; i++) {
                this.idleAnim[i] = this.image.getSubimage(i * 16 + 128, 164, 16, 28);
            }
        }
    }
    public void runAnim() {
        Perso player = App.getInstance().getPersonnage();
        if (player instanceof Warrior) {
            for (int i = 0; i < idleAnim.length; i++) {
                this.idleAnim[i] = this.image.getSubimage(i * 16 + 192, 100, 16, 28);
            }
        } else {
            for (int i = 0; i < idleAnim.length; i++) {
                this.idleAnim[i] = this.image.getSubimage(i * 16 + 192, 164, 16, 28);
            }
        }
    }

    public void chestAnim() {
        for (int i = 0; i < chestAnim.length; i++) {
            this.chestAnim[i] = this.image.getSubimage(i * 16 + 304, 320, 16, 28);
        }
    }

    public void demonANim() {
        for (int i = 0; i < demonAnim.length; i++) {
            this.demonAnim[i] = this.image.getSubimage(i * 32 + 16, 364, 32, 36);
        }
    }

    public BufferedImage[] getDemonAnim() {
        return demonAnim;
    }

    public BufferedImage getfloorDraw() {
          return  image.getSubimage(16, 64,16,16);
    }
    public BufferedImage getWallDraw() {
        return  image.getSubimage(32, 16,16,16);
    }

    public BufferedImage[] getChestAnim() {
        return chestAnim;
    }

    public BufferedImage dragIMG() {return dragonImage.getSubimage(0,0,32,32);}
    public BufferedImage getTopWallDraw() {
        return  image.getSubimage(16, 0,16,16);
    }
    public BufferedImage getRedBanner() {return image.getSubimage(16,32,16,16);}

    public BufferedImage[] getIdleAnim() {
        return idleAnim;
    }

    public BufferedImage[] getRunAnim() {
        return runAnim;
    }

    public BufferedImage getImage() {
        return image;
    }
}
