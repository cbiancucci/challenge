package com.mercadolibre.desafio;

import com.google.zxing.*;
import com.google.zxing.client.j2se.BufferedImageLuminanceSource;
import com.google.zxing.common.HybridBinarizer;
import com.google.zxing.common.StringUtils;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

public class Main {

    private static int size = 20;
    private static List<Block> list = new ArrayList<>();
    private static List<BufferedImage> blocks = new ArrayList<>();

    private static Block upLeft;
    private static Block upRight;
    private static Block downLeft;
    private static Block downRight;

    private static List<Block> corners = new ArrayList<>();
    private static List<Block> topBorders = new ArrayList<>();
    private static List<Block> bottomBorders = new ArrayList<>();
    private static List<Block> leftBorders = new ArrayList<>();
    private static List<Block> rightBorders = new ArrayList<>();

    public static void main(String[] args) {
        BufferedImage image = loadImage();
        System.out.println("Imaged loaded");

        makeSubSet(image);
        System.out.println("Subset done");

        findCorners();
        findBorders();
        System.out.println("Bordes superiores: " + topBorders.size());
        System.out.println("Bordes izquierdos: " + bottomBorders.size());
        System.out.println("Bordes inferiores: " + leftBorders.size());
        System.out.println("Bordes derechos: " + rightBorders.size());

        sortBorders();
    }

    private static void sortBorders() {

    }

    private static void findCorners() {
        for (final Block iterable : list) {
            if (iterable.isUpperLeft()) {
                System.out.println("esquina superior izquierda");
                upLeft = iterable;
                corners.add(upLeft);
            } else if (iterable.isUpperRight()) {
                System.out.println("esquina superior derecha");
                upRight = iterable;
                corners.add(upRight);
            } else if (iterable.isDownLeft()) {
                System.out.println("esquina inferior izquierda");
                downLeft = iterable;
                corners.add(downLeft);
            } else if (iterable.isDownRight()) {
                System.out.println("esquina inferior derecha");
                downRight = iterable;
                corners.add(downRight);
            }
        }
    }

    private static void findBorders() {
        for (final Block iterable : list) {
            if (!corners.contains(iterable)) { // Descarto las esquinas

                if (iterable.isTopBorder()) {
                    topBorders.add(iterable);
                } else if (iterable.isBottomBorder()) {
                    bottomBorders.add(iterable);
                } else if (iterable.isLeftBorder()) {
                    leftBorders.add(iterable);
                } else if (iterable.isRightBorder()) {
                    rightBorders.add(iterable);
                }
            }
        }
    }

    private static void findColor() {

        Block block = list.get(0);
        Color color = block.upRight;

        int count = 0;

        for (final Block iterable : list) {
            if (iterable.hasColor(color)) {
                count++;
            }
        }
    }

    private static BufferedImage loadImage() {
        BufferedImage img = null;
        try {
            img = ImageIO.read(new File("/Users/cpbiancucci/Downloads/challenge.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return img;
    }

    private static void makeSubSet(BufferedImage image) {
        int cont = 0;
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                BufferedImage newImage = cropImage(image, new Rectangle((50 * i) + 1, (50 * j) + 1, 49, 49));
                blocks.add(newImage);

                saveImage(newImage, String.valueOf(cont));
                cont++;

                getBlockForImage(newImage);
            }
        }
    }

    private static BufferedImage cropImage(BufferedImage src, Rectangle rect) {
        return src.getSubimage(rect.x, rect.y, rect.width, rect.height);
    }

    private static void saveImage(BufferedImage image, String name) {
        File outputFile = new File("/Users/cpbiancucci/Downloads/desafio/" + name + ".png");
        try {
            ImageIO.write(image, "png", outputFile);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void getBlockForImage(BufferedImage image) {
        Color upLeft = getColor(image.getRGB(5, 5));
        Color upRight = getColor(image.getRGB(45, 5));
        Color downLeft = getColor(image.getRGB(5, 45));
        Color downRight = getColor(image.getRGB(45, 45));

        list.add(new Block(image, upLeft, upRight, downLeft, downRight));
    }

    private static Color getColor(int clr) {
        return new Color(clr, true);
    }

    private static BufferedImage makeQRFromBlocks(String name) {
        BufferedImage result = new BufferedImage(
                1000,
                1000,
                BufferedImage.TYPE_INT_RGB);

        int index = 0;

        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                BufferedImage temp = blocks.get(index);
                result.createGraphics().drawImage(temp, 50 * j, 50 * i, null);
                index++;
            }
        }

        //saveImage(result, "final " + name);
        return result;
    }

    private static String decodeQRCode(BufferedImage bufferedImage) {
        LuminanceSource source = new BufferedImageLuminanceSource(bufferedImage);
        BinaryBitmap bitmap = new BinaryBitmap(new HybridBinarizer(source));

        try {
            Result result = new MultiFormatReader().decode(bitmap);
            return result.getText();
        } catch (NotFoundException e) {
            return null;
        }
    }
}
