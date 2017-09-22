package main.java.photoviewer.core.unit.factory;

import main.java.photoviewer.core.image.PVImage;
import main.java.photoviewer.core.unit.PVUnit;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;

public class PVImageFactory implements PVUnitFactory {
    private volatile static PVImageFactory imageFactory;

    public static PVImageFactory makeContentFactory() {
        if (imageFactory == null)
            synchronized (PVImageFactory.class) {
                if (imageFactory == null)
                    imageFactory = new PVImageFactory();
            }

        return imageFactory;
    }

    private PVImageFactory() {

    }

    @Override
    public PVUnit importFile(File source) throws IOException {
        return new PVImage(source);
    }

    @Override
    public PVUnit openFile(File source) throws IOException, ClassNotFoundException {
        FileInputStream fis = new FileInputStream(source);
        ObjectInputStream oin = new ObjectInputStream(fis);
        return new PVImage((PVImage) oin.readObject());
    }
}