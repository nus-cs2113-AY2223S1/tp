package seedu.duke.design;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

/**
 * ASCII Art Generator in Java. 
 * Prints a given text as an ASCII text art on the console. 
 * This code is licensed under - CC Attribution CC BY 4.0.
 * @author www.quickprogrammingtips.com
 */
public class AsciiArtGenerator {

    public static final int ART_SIZE_SMALL = 12;
    public static final int ART_SIZE_MEDIUM = 18;
    public static final int ART_SIZE_LARGE = 24;
    public static final int ART_SIZE_HUGE = 32;

    private static final String DEFAULT_ART_SYMBOL = "*";

    public enum AsciiArtFont {
        ART_FONT_DIALOG("Dialog"), ART_FONT_DIALOG_INPUT("DialogInput"),
        ART_FONT_MONO("Monospaced"),ART_FONT_SERIF("Serif"), ART_FONT_SANS_SERIF("SansSerif");

        private final String value;

        public String getValue() {
            return value;
        }

        AsciiArtFont(String value) {
            this.value = value;
        }
    }

    public static void main(String[] args) {
        AsciiArtGenerator artGen = new AsciiArtGenerator();

        System.out.println();
        printTextArt("Hello", AsciiArtGenerator.ART_SIZE_MEDIUM);
        System.out.println();

        System.out.println();
        printTextArt("Love is life!", AsciiArtGenerator.ART_SIZE_SMALL, AsciiArtFont.ART_FONT_MONO,"@");
        System.out.println();
    }

    /**
     * Prints ASCII art for the specified text. For size, you can use predefined sizes or a custom size.
     * Usage - printTextArt("Hi",30,ASCIIArtFont.ART_FONT_SERIF,"@").
     * @param artText text to be displayed
     * @param textHeight - Use a predefined size or a custom type
     * @param fontType - Use one of the available fonts
     * @param artSymbol - Specify the character for printing the ascii art
     */
    private static void printTextArt(String artText, int textHeight, AsciiArtFont fontType, String artSymbol) {
        String fontName = fontType.getValue();
        int imageWidth = findImageWidth(textHeight, artText, fontName);

        BufferedImage image = new BufferedImage(imageWidth, textHeight, BufferedImage.TYPE_INT_RGB);
        Graphics g = image.getGraphics();
        Font font = new Font(fontName, Font.BOLD, textHeight);
        g.setFont(font);

        Graphics2D graphics = (Graphics2D) g;
        graphics.drawString(artText, 0, getBaselinePosition(g, font));

        for (int y = 0; y < textHeight; y++) {
            StringBuilder sb = new StringBuilder();
            for (int x = 0; x < imageWidth; x++) {
                sb.append(image.getRGB(x, y) == Color.WHITE.getRGB() ? artSymbol : " ");
            }
            if (sb.toString().trim().isEmpty()) {
                continue;
            }
            System.out.println(sb);
        }
    }

    /**
     * Convenience method for printing ascii text art.
     * Font default - Dialog,  Art symbol default - *
     * @param artText text to be displayed
     * @param textHeight height of text
     */
    public static void printTextArt(String artText, int textHeight) {
        printTextArt(artText, textHeight, AsciiArtFont.ART_FONT_DIALOG, DEFAULT_ART_SYMBOL);
    }

    /**
     * Using the Current font and current art text find the width of the full image.
     * @param textHeight height of text
     * @param artText text to be displayed
     * @param fontName font style
     * @return returns font metrics
     */
    private static int findImageWidth(int textHeight, String artText, String fontName) {
        BufferedImage im = new BufferedImage(1, 1, BufferedImage.TYPE_INT_RGB);
        Graphics g = im.getGraphics();
        g.setFont(new Font(fontName, Font.BOLD, textHeight));
        return g.getFontMetrics().stringWidth(artText);
    }

    /**
     * Find where the text baseline should be drawn so that the characters are within image.
     * @param g type of graphics
     * @param font font
     * @return ascent
     */
    private static int getBaselinePosition(Graphics g, Font font) {
        FontMetrics metrics = g.getFontMetrics(font);
        return metrics.getAscent() - metrics.getDescent();
    }
}