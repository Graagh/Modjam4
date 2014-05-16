package immibis.modjam4;

import org.lwjgl.opengl.GL11;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.OpenGlHelper;
import net.minecraft.client.renderer.RenderHelper;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.texture.TextureMap;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.init.Blocks;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.IIcon;

@SideOnly(Side.CLIENT)
public class RenderTileShaft extends TileEntitySpecialRenderer  {

	@Override
	public void renderTileEntityAt(TileEntity te_, double renderX, double renderY, double renderZ, float partialTick) {
		int meta = te_.getBlockMetadata();
		TileShaft te = (TileShaft)te_;
		
		Minecraft.getMinecraft().renderEngine.bindTexture(TextureMap.locationBlocksTexture);
		Tessellator t = Tessellator.instance;
		
		IIcon icon = Blocks.log.getIcon(2, 0);
		
		RenderHelper.disableStandardItemLighting();
		
		GL11.glPushMatrix();
		GL11.glTranslated(renderX+0.5, renderY+0.5, renderZ+0.5);
		if(meta == 2) {
			// Z -> Y
			GL11.glRotatef(90, 1, 0, 0);
		}
		if(meta == 4) {
			// X -> Y
			GL11.glRotatef(90, 0, 0, 1);
		}
		float angle = (float)((te.angle + te.angvel * partialTick) / (4294967296.0 / 360.0));
		GL11.glRotatef(angle, 0, 1, 0);
		GL11.glTranslatef(-0.5f, -0.5f, -0.5f);
		
		t.startDrawingQuads();
		
		t.addVertexWithUV(0.25, 0, 0.25, icon.getMinU(), icon.getMinV());
		t.addVertexWithUV(0.25, 0, 0.75, icon.getMaxU(), icon.getMinV());
		t.addVertexWithUV(0.25, 1, 0.75, icon.getMaxU(), icon.getMaxV());
		t.addVertexWithUV(0.25, 1, 0.25, icon.getMinU(), icon.getMaxV());
		
		t.addVertexWithUV(0.75, 1, 0.25, icon.getMinU(), icon.getMaxV());
		t.addVertexWithUV(0.75, 1, 0.75, icon.getMaxU(), icon.getMaxV());
		t.addVertexWithUV(0.75, 0, 0.75, icon.getMaxU(), icon.getMinV());
		t.addVertexWithUV(0.75, 0, 0.25, icon.getMinU(), icon.getMinV());
		
		t.addVertexWithUV(0.25, 1, 0.25, icon.getMinU(), icon.getMaxV());
		t.addVertexWithUV(0.75, 1, 0.25, icon.getMaxU(), icon.getMaxV());
		t.addVertexWithUV(0.75, 0, 0.25, icon.getMaxU(), icon.getMinV());
		t.addVertexWithUV(0.25, 0, 0.25, icon.getMinU(), icon.getMinV());
		
		t.addVertexWithUV(0.25, 0, 0.75, icon.getMinU(), icon.getMinV());
		t.addVertexWithUV(0.75, 0, 0.75, icon.getMaxU(), icon.getMinV());
		t.addVertexWithUV(0.75, 1, 0.75, icon.getMaxU(), icon.getMaxV());
		t.addVertexWithUV(0.25, 1, 0.75, icon.getMinU(), icon.getMaxV());
		
		icon = Blocks.log.getIcon(0, 0);
		t.addVertexWithUV(0.75, 0, 0.25, icon.getMinU(), icon.getMaxV());
		t.addVertexWithUV(0.75, 0, 0.75, icon.getMaxU(), icon.getMaxV());
		t.addVertexWithUV(0.25, 0, 0.75, icon.getMaxU(), icon.getMinV());
		t.addVertexWithUV(0.25, 0, 0.25, icon.getMinU(), icon.getMinV());
		
		t.addVertexWithUV(0.25, 1, 0.25, icon.getMinU(), icon.getMinV());
		t.addVertexWithUV(0.25, 1, 0.75, icon.getMaxU(), icon.getMinV());
		t.addVertexWithUV(0.75, 1, 0.75, icon.getMaxU(), icon.getMaxV());
		t.addVertexWithUV(0.75, 1, 0.25, icon.getMinU(), icon.getMaxV());
	
		t.draw();
		t.setTranslation(0, 0, 0);
		GL11.glPopMatrix();
	}

}