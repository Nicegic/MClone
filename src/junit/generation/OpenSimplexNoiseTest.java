/*
 * OpenSimplex Noise sample class.
 */
package junit.generation;

import java.awt.Color;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
import java.io.*;
import generation.LayerInfo;
import java.util.Random;

public class OpenSimplexNoiseTest {
    
    public static void main(String[] args) {
        OpenSimplexNoiseTest generator = new OpenSimplexNoiseTest();

        Random seeder = new Random(System.nanoTime());
        for (int i = 0; i < 10; i++) {
            LayerInfo[] testVals = new LayerInfo[3];
            testVals[0] = new LayerInfo(seeder.nextLong(), 400, 9);
            testVals[1] = new LayerInfo(seeder.nextLong(), 100, 3);
            testVals[2] = new LayerInfo(seeder.nextLong(), 25, 1);
            testVals[2] = new LayerInfo(seeder.nextLong(), 1600, 27);

            String filename = "noise" + i;
            int width = 4096;
            int height = 4096;
            try {
                generator.generateImage(width, height, testVals, filename);
            } catch (IOException ioe) {
                System.out.println("Error saving the generated image.");
                ioe.printStackTrace();
            }
        }
    }

    public void generateImage(int width, int height, LayerInfo[] layers, String filename) throws IOException {
        BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);

        //get the total factor of all layers, later used for getting them back into (-1, 1)
        double factor = 0;
        for (LayerInfo l : layers) {
            factor = factor + l.getFactor();
        }
        System.out.println("factor: " + factor);

        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                double value = 0;

                //calculate and add all the layer values, returning them to (-1, 1) afterwards
                for (LayerInfo layer : layers) {
                    value += layer.getNoise().eval(x / layer.getFeatureSize(), y / layer.getFeatureSize(), 0.0) * layer.getFactor();
                }
                value /= factor;

                Color color = setColor(value);

                image.setRGB(x, y, color.getRGB());
            }
        }
        ImageIO.write(image, "png", new File(filename));
    }

    public Color setColor(double value) {
        int shade;

        if (value > 0.9) {
            shade = 247;
        } else if (value > 0.8) {
            shade = 234;
        } else if (value > 0.7) {
            shade = 221;
        } else if (value > 0.6) {
            shade = 208;
        } else if (value > 0.5) {
            shade = 195;
        } else if (value > 0.4) {
            shade = 182;
        } else if (value > 0.3) {
            shade = 169;
        } else if (value > 0.2) {
            shade = 156;
        } else if (value > 0.1) {
            shade = 143;
        } else if (value > 0) {
            shade = 130;
        } else if (value > -0.1) {
            shade = 117;
        } else if (value > -0.2) {
            shade = 104;
        } else if (value > -0.3) {
            shade = 91;
        } else if (value > -0.4) {
            shade = 78;
        } else if (value > -0.5) {
            shade = 65;
        } else if (value > -0.6) {
            shade = 52;
        } else if (value > -0.7) {
            shade = 39;
        } else if (value > -0.8) {
            shade = 26;
        } else if (value > -0.9) {
            shade = 13;
        } else {
            shade = 0;
        }

        //original way of setting RGB values
        //int rgb = 0x010101 * (int) ((value + 1) * 127.5);
        //image.setRGB(x, y, rgb);

        return new Color(shade, shade, shade);
    }
}
