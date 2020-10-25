package saaf.common.fmw.bean;

/**
 * Created by Administrator on 2016/10/10.
 */
public class ImageConfig {

    private int hieght;
    private int width;
    private boolean logo = false;
    private boolean compress = false;

    public ImageConfig() {
    }

    ;

    public ImageConfig(Integer hieght, Integer width, Boolean logo, Boolean compress) {
        this.hieght = hieght;
        this.width = width;
        this.logo = logo;
        this.compress = compress;
    }

    public int getHieght() {
        return hieght;
    }

    public void setHieght(int hieght) {
        this.hieght = hieght;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public boolean isLogo() {
        return logo;
    }

    public void setLogo(boolean logo) {
        this.logo = logo;
    }

    public boolean isCompress() {
        return compress;
    }

    public void setCompress(boolean compress) {
        this.compress = compress;
    }


}
