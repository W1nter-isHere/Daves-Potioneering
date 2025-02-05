package tfar.davespotioneering.init;

import net.minecraft.tileentity.TileEntityType;
import net.minecraftforge.event.RegistryEvent;
import tfar.davespotioneering.blockentity.AdvancedBrewingStandBlockEntity;
import tfar.davespotioneering.blockentity.PotionInjectorBlockEntity;
import tfar.davespotioneering.blockentity.ReinforcedCauldronBlockEntity;

import java.lang.reflect.Field;
import java.util.List;
import java.util.Locale;

public class ModBlockEntityTypes {

    private static List<TileEntityType<?>> MOD_BLOCK_ENTITY_TYPES;

    public static final TileEntityType<AdvancedBrewingStandBlockEntity> ADVANCED_BREWING_STAND = TileEntityType.Builder.create(AdvancedBrewingStandBlockEntity::new,ModBlocks.ADVANCED_BREWING_STAND).build(null);
    public static final TileEntityType<ReinforcedCauldronBlockEntity> REINFORCED_CAULDRON = TileEntityType.Builder.create(ReinforcedCauldronBlockEntity::new,ModBlocks.REINFORCED_CAULDRON).build(null);
    public static final TileEntityType<PotionInjectorBlockEntity> POTION_INJECTOR = TileEntityType.Builder.create(PotionInjectorBlockEntity::new,ModBlocks.POTION_INJECTOR).build(null);

    public static void register(RegistryEvent.Register<TileEntityType<?>> e) {
        for (Field field : ModBlockEntityTypes.class.getFields()) {
            try {
                Object o = field.get(null);
                if (o instanceof TileEntityType) {
                       e.getRegistry().register(((TileEntityType<?>) o).setRegistryName(field.getName().toLowerCase(Locale.ROOT)));
                }
            } catch (IllegalAccessException illegalAccessException) {
                illegalAccessException.printStackTrace();
            }
        }
    }
}
