package tfar.davespotioneering.datagen.assets;

import net.minecraft.block.Block;
import net.minecraft.block.CauldronBlock;
import net.minecraft.block.HorizontalBlock;
import net.minecraft.block.LecternBlock;
import net.minecraft.data.DataGenerator;
import net.minecraft.util.Direction;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.model.generators.BlockStateProvider;
import net.minecraftforge.client.model.generators.ConfiguredModel;
import net.minecraftforge.client.model.generators.ModelFile;
import net.minecraftforge.common.data.ExistingFileHelper;
import tfar.davespotioneering.DavesPotioneering;
import tfar.davespotioneering.block.AdvancedBrewingStandBlock;
import tfar.davespotioneering.init.ModBlocks;

public class ModBlockstateProvider extends BlockStateProvider {
    public ModBlockstateProvider(DataGenerator gen, ExistingFileHelper exFileHelper) {
        super(gen, DavesPotioneering.MODID, exFileHelper);
    }

    @Override
    protected void registerStatesAndModels() {
        brewingStand();

        getVariantBuilder(ModBlocks.REINFORCED_CAULDRON).forAllStates(state -> {
            ModelFile modelFile = models().getExistingFile(modLoc("block/reinforced_cauldron_level" + state.get(CauldronBlock.LEVEL)));
            return ConfiguredModel.builder().modelFile(modelFile).build();
        });

        getVariantBuilder(ModBlocks.MAGIC_LECTERN).forAllStatesExcept(state -> {
            Direction facing = state.get(LecternBlock.FACING);
            ModelFile modelFile = models().getExistingFile(modLoc("block/magic_lectern"));
            return ConfiguredModel.builder().modelFile(modelFile).rotationY((facing.getHorizontalIndex() + 3) % 4 * 90).build();
        }, LecternBlock.HAS_BOOK, LecternBlock.POWERED);
        blockstateFromExistingModel(ModBlocks.POTION_INJECTOR);
    }

    protected void blockstateFromExistingModel(Block block) {
        ModelFile modelFile = models().getExistingFile(new ResourceLocation(DavesPotioneering.MODID, "block/" + block.getRegistryName().getPath()));
        getVariantBuilder(block).forAllStates(state -> {
            ConfiguredModel.Builder<?> builder = ConfiguredModel.builder().modelFile(modelFile);
            if (state.hasProperty(HorizontalBlock.HORIZONTAL_FACING)) {
                switch(state.get(HorizontalBlock.HORIZONTAL_FACING)) {
                    case EAST:
                        builder.rotationY(90);
                        break;
                    case SOUTH:
                        builder.rotationY(180);
                        break;
                    case WEST:
                        builder.rotationY(270);
                        break;
                    case NORTH:
                    default:
                        builder.rotationY(0);
                        break;
                }
            }
            return builder.build();
        });
    }

    protected void brewingStand() {
        getMultipartBuilder(ModBlocks.ADVANCED_BREWING_STAND)
                .part().modelFile(models().getExistingFile(modLoc("block/advanced_brewing_stand"))).addModel().end()
                .part().modelFile(models().getExistingFile(modLoc("block/advanced_brewing_stand_bottle0"))).addModel().condition(AdvancedBrewingStandBlock.HAS_BOTTLE[0], true).end()
                .part().modelFile(models().getExistingFile(modLoc("block/advanced_brewing_stand_bottle1"))).addModel().condition(AdvancedBrewingStandBlock.HAS_BOTTLE[1], true).end()
                .part().modelFile(models().getExistingFile(modLoc("block/advanced_brewing_stand_bottle2"))).addModel().condition(AdvancedBrewingStandBlock.HAS_BOTTLE[2], true).end()
                .part().modelFile(models().getExistingFile(modLoc("block/advanced_brewing_stand_empty0"))).addModel().condition(AdvancedBrewingStandBlock.HAS_BOTTLE[0], false).end()
                .part().modelFile(models().getExistingFile(modLoc("block/advanced_brewing_stand_empty1"))).addModel().condition(AdvancedBrewingStandBlock.HAS_BOTTLE[1], false).end()
                .part().modelFile(models().getExistingFile(modLoc("block/advanced_brewing_stand_empty2"))).addModel().condition(AdvancedBrewingStandBlock.HAS_BOTTLE[2], false).end()
        ;


    }
}
