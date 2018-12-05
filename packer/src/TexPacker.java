import com.badlogic.gdx.tools.texturepacker.TexturePacker;

public class TexPacker {

    public static final String SOURCE = "assets/textures/platforms";
    public static final String DEST = "../core/assets/atlases";

    public static void pack() {
        TexturePacker.process(SOURCE, DEST, "platforms");
    }

}
