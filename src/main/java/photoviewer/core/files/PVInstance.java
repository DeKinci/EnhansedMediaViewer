package main.java.photoviewer.core.files;

import main.java.photoviewer.core.files.unit.PVUnit;
import main.java.photoviewer.core.files.unit.factory.PVImageFactory;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class PVInstance implements Runnable {
    private PVUnit image;
    private PVImageFactory factory;
    final private String fileExtension = ".epvi";

    private Thread thread;

    public PVInstance() {
        this.factory = PVImageFactory.makeContentFactory();
        openImage();

        this.thread = new Thread(this);
        thread.start();
    }

    @Override
    public void run() {
        importImage();
        saveImage();
    }

    public PVUnit getUnit() {
        return image;
    }

    public BufferedImage getImage() {
        return (BufferedImage) this.image.getContent();
    }

    public void importImage() {
        File img = new File("misc/img.jpg");
        try {
            image = factory.importFile(img);
        } catch (IOException e) {
            System.err.println("File to import is not found");
        }
    }

    public void saveImage() {
        File out = new File("misc/img" + fileExtension);

        try {
            image.saveFile(out);
        } catch (IOException e) {
            System.err.println("File to save is not found");
        }
    }

    public void openImage() {
        File in = new File("misc/img" + fileExtension);
        try {
            image = factory.openFile(in);
        } catch (Exception e) {
            System.err.println("File to open is not found");
        }
    }

    public void exportImage() {
        try {
            ImageIO.write((BufferedImage) image.getContent(), "PNG", new File("misc/out.png"));
        } catch (IOException e) {
            System.err.println("File to export is not found");
        }
    }
}