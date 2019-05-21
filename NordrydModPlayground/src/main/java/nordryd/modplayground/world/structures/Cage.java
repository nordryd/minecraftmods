package nordryd.modplayground.world.structures;

import java.io.PrintWriter;
import java.util.Random;

import net.minecraft.init.Blocks;
import net.minecraft.util.Mirror;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.Rotation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IWorld;
import net.minecraft.world.gen.IChunkGenSettings;
import net.minecraft.world.gen.IChunkGenerator;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.NoFeatureConfig;
import net.minecraft.world.gen.feature.template.PlacementSettings;
import net.minecraft.world.gen.feature.template.Template;
import nordryd.modplayground.util.Reference;

public class Cage extends Feature<NoFeatureConfig>
{	
	@Override
	public boolean func_212245_a(IWorld world, IChunkGenerator<? extends IChunkGenSettings> generator, Random rand, BlockPos pos,
			NoFeatureConfig config) {
		int posX = pos.getX(), posZ = pos.getZ(), posY = 70 + rand.nextInt(25);
		
		Template structure = world.getSaveHandler().getStructureTemplateManager().getTemplateDefaulted(new ResourceLocation(Reference.MOD_ID, "cage"));
		structure.addBlocksToWorld(world, new BlockPos(posX, posY, posZ), new PlacementSettings().setReplacedBlock(Blocks.AIR).setRotation(Rotation.NONE).setMirror(Mirror.NONE));
		structure.getSize();
		
		try {
			PrintWriter testFile = new PrintWriter("C:\\Users\\Nordryd\\Desktop\\wtf_is_happening.txt");
			testFile.println("JAVA WAS HERE\nJAVA WAS HERE\nJAVA WAS HERE\nJAVA WAS HERE\nJAVA WAS HERE\nJAVA WAS HERE\nJAVA WAS HERE\nJAVA WAS HERE\n");
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		
		return true;
	}
}
