package tfar.davespotioneering.init;

import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraftforge.event.RegistryEvent;
import tfar.davespotioneering.block.AdvancedBrewingStandBlock;

import java.lang.reflect.Field;
import java.util.List;
import java.util.Locale;

public class ModBlocks {

    private static List<Block> MOD_BLOCKS;

    public static final Block ADVANCED_BREWING_STAND = new AdvancedBrewingStandBlock(AbstractBlock.Properties.create(Material.IRON)
            .setRequiresTool().hardnessAndResistance(0.5F).setLightLevel(state -> 1).notSolid());

    public static void register(RegistryEvent.Register<Block> e) {
        for (Field field : ModBlocks.class.getFields()) {
            try {
                Object o = field.get(null);
                if (o instanceof Block) {
                       e.getRegistry().register(((Block) o).setRegistryName(field.getName().toLowerCase(Locale.ROOT)));
                }
            } catch (IllegalAccessException illegalAccessException) {
                illegalAccessException.printStackTrace();
            }
        }
    }
}
