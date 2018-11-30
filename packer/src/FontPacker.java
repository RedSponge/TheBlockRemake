import com.badlogic.gdx.Files;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.tools.bmfont.BitmapFontWriter;

import java.io.*;
import java.util.Arrays;

public class FontPacker {

    public static final String INPUT_DIR = "assets/fonts/";
    public static final String OUTPUT_DIR = "../core/assets/fonts/";

    public static void pack() {
        File fonts = new File(INPUT_DIR + "fonts.fontlist");
        String fontlist = readFile(fonts);

        for(String font : fontlist.split(",")) {
            FileHandle fontHandle = new FileHandle(new File(INPUT_DIR + font + ".ttf"));


            for(int size : Arrays.stream(readFile(new File(INPUT_DIR + font + ".fontsizes")).split(",")).map(Integer::parseInt).toArray(Integer[]::new)) {
                FreeTypeFontGenerator.FreeTypeFontParameter parameter = new FreeTypeFontGenerator.FreeTypeFontParameter();
                parameter.size = size;
//                FreeTypeFontGenerator.FreeTypeBitmapFontData f = generator.generateData(parameter);

//                BitmapFontWriter.writeFont(f, new String[] {font + ".png"}, new FileHandle(new File(OUTPUT_DIR + font + ".fnt")), new BitmapFontWriter.FontInfo(), 512, 512);
            }
//            generator.dispose();
        }
    }

    public static String readFile(File file) {
        try {
            StringBuilder out = new StringBuilder();
            BufferedReader in = new BufferedReader(new FileReader(file));
            String line;

            while((line = in.readLine()) != null) {
                out.append(line);
            }
            in.close();
            return out.toString();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            throw new IllegalStateException("File " + file + "Not Found!");
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException("EXCEPTION!", e);
        }
    }

}
