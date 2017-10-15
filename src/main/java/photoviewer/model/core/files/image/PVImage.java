package photoviewer.model.core.files.image;

import photoviewer.model.core.files.unit.PVUnit;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.Serializable;

public class PVImage extends PVUnit implements Serializable {
    public PVImage(File source) throws IOException {
        super(source);
    }

    public PVImage (PVImage source) {
        super(source);
    }

    public BufferedImage getContent() {
        return (BufferedImage) content.getContent();
    }
}