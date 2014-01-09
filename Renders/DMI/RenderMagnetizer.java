/*******************************************************************************
 * @author Reika Kalseki
 * 
 * Copyright 2013
 * 
 * All rights reserved.
 * Distribution of the software in any form is only allowed with
 * explicit, prior permission from the owner.
 ******************************************************************************/
package Reika.RotaryCraft.Renders.DMI;

import net.minecraft.tileentity.TileEntity;
import net.minecraftforge.client.MinecraftForgeClient;

import org.lwjgl.opengl.GL11;

import Reika.DragonAPI.Interfaces.RenderFetcher;
import Reika.DragonAPI.Libraries.ReikaInventoryHelper;
import Reika.DragonAPI.Libraries.Java.ReikaJavaLibrary;
import Reika.RotaryCraft.Auxiliary.IORenderer;
import Reika.RotaryCraft.Auxiliary.ItemStacks;
import Reika.RotaryCraft.Base.RotaryTERenderer;
import Reika.RotaryCraft.Base.TileEntity.RotaryCraftTileEntity;
import Reika.RotaryCraft.Models.Animated.ModelMagnetizer;
import Reika.RotaryCraft.TileEntities.Processing.TileEntityMagnetizer;

public class RenderMagnetizer extends RotaryTERenderer
{

	private ModelMagnetizer MagnetizerModel = new ModelMagnetizer();
	//private ModelMagnetizerV MagnetizerModelV = new ModelMagnetizerV();

	/**
	 * Renders the TileEntity for the position.
	 */
	public void renderTileEntityMagnetizerAt(TileEntityMagnetizer tile, double par2, double par4, double par6, float par8)
	{
		int var9;

		if (!tile.isInWorld())
			var9 = 0;
		else
			var9 = tile.getBlockMetadata();

		ModelMagnetizer var14;
		var14 = MagnetizerModel;
		//ModelMagnetizerV var15;
		//var14 = this.MagnetizerModelV;
		this.bindTextureByName("/Reika/RotaryCraft/Textures/TileEntityTex/magnettex.png");

		this.setupGL(tile, par2, par4, par6);

		int var11 = 0;	 //used to rotate the model about metadata

		if (tile.isInWorld()) {

			switch(tile.getBlockMetadata()) {
			case 0:
				var11 = 180;
				break;
			case 1:
				var11 = 0;
				break;
			case 2:
				var11 = 270;
				break;
			case 3:
				var11 = 90;
				break;
			}

			GL11.glRotatef((float)var11-90, 0.0F, 1.0F, 0.0F);

		}
		//float var12 = tile.prevLidAngle + (tile.lidAngle - tile.prevLidAngle) * par8;
		float var13;/*

            var12 = 1.0F - var12;
            var12 = 1.0F - var12 * var12 * var12;*/
		// if (tile.getBlockMetadata() < 4)


		var14.renderAll(ReikaJavaLibrary.makeListFrom(ReikaInventoryHelper.checkForItemStack(ItemStacks.shaftcore, tile.inv, false)), -tile.phi, 0);
		// else
		//var15.renderAll();
		//GL11.glEnable(GL11.GL_LIGHTING);

		this.closeGL(tile);
	}

	@Override
	public void renderTileEntityAt(TileEntity tile, double par2, double par4, double par6, float par8)
	{
		if (this.isValidMachineRenderpass((RotaryCraftTileEntity)tile))
			this.renderTileEntityMagnetizerAt((TileEntityMagnetizer)tile, par2, par4, par6, par8);
		if (((RotaryCraftTileEntity) tile).isInWorld() && MinecraftForgeClient.getRenderPass() == 1)
			IORenderer.renderIO(tile, par2, par4, par6);
	}

	@Override
	public String getImageFileName(RenderFetcher te) {
		return "magnettex.png";
	}
}