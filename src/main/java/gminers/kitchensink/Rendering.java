package gminers.kitchensink;


import java.util.Iterator;
import java.util.List;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.RenderHelper;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.WorldRenderer;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.client.shader.Framebuffer;
import net.minecraft.util.ResourceLocation;

import org.lwjgl.opengl.GL11;


/**
 * Mostly methods stolen from random places in Minecraft, like from GuiScreen, so that they can be used outside of GuiScreens.
 * 
 */
public class Rendering {
	
	public static void drawPolygon(final int x, final int y, final int radius, final int color, final int count,
			final int z) {
		GL11.glPushMatrix();
		final WorldRenderer worldRenderer = Tessellator.getInstance().getWorldRenderer();
		GL11.glEnable(GL11.GL_BLEND);
		GL11.glDisable(GL11.GL_TEXTURE_2D);
		GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);
		GL11.glTranslatef(x, y, 0);
		final float r = (color >> 16 & 255) / 255.0F;
		final float g = (color >> 8 & 255) / 255.0F;
		final float b = (color & 255) / 255.0F;
		final float a = (color >> 24 & 255) / 255.0F;
		GL11.glColor4f(r, g, b, a);
		worldRenderer.startDrawing(GL11.GL_POLYGON);
		for (int i = 0; i < count; ++i) {
			worldRenderer.addVertex(Math.sin(i / ((double) count) * 2 * Math.PI) * (radius),
					Math.cos(i / ((double) count) * 2 * Math.PI) * (radius), z);
		}
		Tessellator.getInstance().draw();
		GL11.glEnable(GL11.GL_TEXTURE_2D);
		GL11.glDisable(GL11.GL_BLEND);
		GL11.glPopMatrix();
	}
	
	public static void drawGradientRect(int left, int top, int right, int bottom, int startColor, int endColor, float z) {
		float f = (float)(startColor >> 24 & 255) / 255.0F;
        float f1 = (float)(startColor >> 16 & 255) / 255.0F;
        float f2 = (float)(startColor >> 8 & 255) / 255.0F;
        float f3 = (float)(startColor & 255) / 255.0F;
        float f4 = (float)(endColor >> 24 & 255) / 255.0F;
        float f5 = (float)(endColor >> 16 & 255) / 255.0F;
        float f6 = (float)(endColor >> 8 & 255) / 255.0F;
        float f7 = (float)(endColor & 255) / 255.0F;
        GlStateManager.disableTexture2D();
        GlStateManager.enableBlend();
        GlStateManager.disableAlpha();
        GlStateManager.tryBlendFuncSeparate(770, 771, 1, 0);
        GlStateManager.shadeModel(7425);
        Tessellator tessellator = Tessellator.getInstance();
        WorldRenderer worldrenderer = tessellator.getWorldRenderer();
        worldrenderer.startDrawingQuads();
        worldrenderer.setColorRGBA_F(f1, f2, f3, f);
        worldrenderer.addVertex((double)right, (double)top, (double)z);
        worldrenderer.addVertex((double)left, (double)top, (double)z);
        worldrenderer.setColorRGBA_F(f5, f6, f7, f4);
        worldrenderer.addVertex((double)left, (double)bottom, (double)z);
        worldrenderer.addVertex((double)right, (double)bottom, (double)z);
        tessellator.draw();
        GlStateManager.shadeModel(7424);
        GlStateManager.disableBlend();
        GlStateManager.enableAlpha();
        GlStateManager.enableTexture2D();
	}
	
	public static void drawHorzGradientRect(int left, int top, int right, int bottom, int startColor, int endColor, float z) {
		float f = (float)(startColor >> 24 & 255) / 255.0F;
        float f1 = (float)(startColor >> 16 & 255) / 255.0F;
        float f2 = (float)(startColor >> 8 & 255) / 255.0F;
        float f3 = (float)(startColor & 255) / 255.0F;
        float f4 = (float)(endColor >> 24 & 255) / 255.0F;
        float f5 = (float)(endColor >> 16 & 255) / 255.0F;
        float f6 = (float)(endColor >> 8 & 255) / 255.0F;
        float f7 = (float)(endColor & 255) / 255.0F;
        GlStateManager.disableTexture2D();
        GlStateManager.enableBlend();
        GlStateManager.disableAlpha();
        GlStateManager.tryBlendFuncSeparate(770, 771, 1, 0);
        GlStateManager.shadeModel(7425);
        Tessellator tessellator = Tessellator.getInstance();
        WorldRenderer worldrenderer = tessellator.getWorldRenderer();
        worldrenderer.startDrawingQuads();
        worldrenderer.setColorRGBA_F(f5, f6, f7, f4);
        worldrenderer.addVertex((double)right, (double)top, (double)z);
        worldrenderer.setColorRGBA_F(f1, f2, f3, f);
        worldrenderer.addVertex((double)left, (double)top, (double)z);
        worldrenderer.addVertex((double)left, (double)bottom, (double)z);
        worldrenderer.setColorRGBA_F(f5, f6, f7, f4);
        worldrenderer.addVertex((double)right, (double)bottom, (double)z);
        tessellator.draw();
        GlStateManager.shadeModel(7424);
        GlStateManager.disableBlend();
        GlStateManager.enableAlpha();
        GlStateManager.enableTexture2D();
	}
	
	public void drawTexturedModalRect(float xCoord, float yCoord, int minU, int minV, int maxU, int maxV, float z) {
        float f2 = 0.00390625F;
        float f3 = 0.00390625F;
        Tessellator tessellator = Tessellator.getInstance();
        WorldRenderer worldrenderer = tessellator.getWorldRenderer();
        worldrenderer.startDrawingQuads();
        worldrenderer.addVertexWithUV((double)(xCoord + 0.0F), (double)(yCoord + (float)maxV), (double)z, (double)((float)(minU + 0) * f2), (double)((float)(minV + maxV) * f3));
        worldrenderer.addVertexWithUV((double)(xCoord + (float)maxU), (double)(yCoord + (float)maxV), (double)z, (double)((float)(minU + maxU) * f2), (double)((float)(minV + maxV) * f3));
        worldrenderer.addVertexWithUV((double)(xCoord + (float)maxU), (double)(yCoord + 0.0F), (double)z, (double)((float)(minU + maxU) * f2), (double)((float)(minV + 0) * f3));
        worldrenderer.addVertexWithUV((double)(xCoord + 0.0F), (double)(yCoord + 0.0F), (double)z, (double)((float)(minU + 0) * f2), (double)((float)(minV + 0) * f3));
        tessellator.draw();
    }

    public void drawTexturedModalRect(int xCoord, int yCoord, TextureAtlasSprite textureSprite, int p_175175_4_, int p_175175_5_, float z) {
        Tessellator tessellator = Tessellator.getInstance();
        WorldRenderer worldrenderer = tessellator.getWorldRenderer();
        worldrenderer.startDrawingQuads();
        worldrenderer.addVertexWithUV((double)(xCoord + 0), (double)(yCoord + p_175175_5_), (double)z, (double)textureSprite.getMinU(), (double)textureSprite.getMaxV());
        worldrenderer.addVertexWithUV((double)(xCoord + p_175175_4_), (double)(yCoord + p_175175_5_), (double)z, (double)textureSprite.getMaxU(), (double)textureSprite.getMaxV());
        worldrenderer.addVertexWithUV((double)(xCoord + p_175175_4_), (double)(yCoord + 0), (double)z, (double)textureSprite.getMaxU(), (double)textureSprite.getMinV());
        worldrenderer.addVertexWithUV((double)(xCoord + 0), (double)(yCoord + 0), (double)z, (double)textureSprite.getMinU(), (double)textureSprite.getMinV());
        tessellator.draw();
    }

    public static void drawModalRectWithCustomSizedTexture(int x, int y, float u, float v, int width, int height, float textureWidth, float textureHeight) {
        float f4 = 1.0F / textureWidth;
        float f5 = 1.0F / textureHeight;
        Tessellator tessellator = Tessellator.getInstance();
        WorldRenderer worldrenderer = tessellator.getWorldRenderer();
        worldrenderer.startDrawingQuads();
        worldrenderer.addVertexWithUV((double)x, (double)(y + height), 0.0D, (double)(u * f4), (double)((v + (float)height) * f5));
        worldrenderer.addVertexWithUV((double)(x + width), (double)(y + height), 0.0D, (double)((u + (float)width) * f4), (double)((v + (float)height) * f5));
        worldrenderer.addVertexWithUV((double)(x + width), (double)y, 0.0D, (double)((u + (float)width) * f4), (double)(v * f5));
        worldrenderer.addVertexWithUV((double)x, (double)y, 0.0D, (double)(u * f4), (double)(v * f5));
        tessellator.draw();
    }

    public static void drawScaledCustomSizeModalRect(int x, int y, float u, float v, int uWidth, int vHeight, int width, int height, float tileWidth, float tileHeight) {
        float f4 = 1.0F / tileWidth;
        float f5 = 1.0F / tileHeight;
        Tessellator tessellator = Tessellator.getInstance();
        WorldRenderer worldrenderer = tessellator.getWorldRenderer();
        worldrenderer.startDrawingQuads();
        worldrenderer.addVertexWithUV((double)x, (double)(y + height), 0.0D, (double)(u * f4), (double)((v + (float)vHeight) * f5));
        worldrenderer.addVertexWithUV((double)(x + width), (double)(y + height), 0.0D, (double)((u + (float)uWidth) * f4), (double)((v + (float)vHeight) * f5));
        worldrenderer.addVertexWithUV((double)(x + width), (double)y, 0.0D, (double)((u + (float)uWidth) * f4), (double)(v * f5));
        worldrenderer.addVertexWithUV((double)x, (double)y, 0.0D, (double)(u * f4), (double)(v * f5));
        tessellator.draw();
    }
	
    public static void drawHorizontalLine(int startX, int endX, int y, int color) {
        if (endX < startX) {
            int i1 = startX;
            startX = endX;
            endX = i1;
        }

        drawRect(startX, y, endX + 1, y + 1, color);
    }

    public static void drawVerticalLine(int x, int startY, int endY, int color) {
        if (endY < startY) {
            int i1 = startY;
            startY = endY;
            endY = i1;
        }

        drawRect(x, startY + 1, x + 1, endY, color);
    }

    public static void drawRect(int left, int top, int right, int bottom, int color)
    {
        int j1;

        if (left < right)
        {
            j1 = left;
            left = right;
            right = j1;
        }

        if (top < bottom)
        {
            j1 = top;
            top = bottom;
            bottom = j1;
        }

        float f3 = (float)(color >> 24 & 255) / 255.0F;
        float f = (float)(color >> 16 & 255) / 255.0F;
        float f1 = (float)(color >> 8 & 255) / 255.0F;
        float f2 = (float)(color & 255) / 255.0F;
        Tessellator tessellator = Tessellator.getInstance();
        WorldRenderer worldrenderer = tessellator.getWorldRenderer();
        GlStateManager.enableBlend();
        GlStateManager.disableTexture2D();
        GlStateManager.tryBlendFuncSeparate(770, 771, 1, 0);
        GlStateManager.color(f, f1, f2, f3);
        worldrenderer.startDrawingQuads();
        worldrenderer.addVertex((double)left, (double)bottom, 0.0D);
        worldrenderer.addVertex((double)right, (double)bottom, 0.0D);
        worldrenderer.addVertex((double)right, (double)top, 0.0D);
        worldrenderer.addVertex((double)left, (double)top, 0.0D);
        tessellator.draw();
        GlStateManager.enableTexture2D();
        GlStateManager.disableBlend();
    }
	
	public static void drawCenteredString(final FontRenderer fontRenderer, final String str, final int middle,
			final int y, final int color) {
		fontRenderer.drawStringWithShadow(str, middle - fontRenderer.getStringWidth(str) / 2, y, color);
	}
	
	public static void drawSectionHeader(final String str, final int middle, final int y, final int color) {
		final int shadowColor = (color & 16579836) >> 2 | color & -16777216;
		final int imgWidth = Minecraft.getMinecraft().fontRendererObj.getStringWidth(str);
		drawHorizontalLine(middle - 200, middle - (imgWidth / 2) - 5, y + 3, color);
		drawHorizontalLine(middle + 200, middle + (imgWidth / 2) + 5, y + 3, color);
		drawHorizontalLine(middle - 200, middle - (imgWidth / 2) - 4, y + 4, shadowColor);
		drawHorizontalLine(middle + 201, middle + (imgWidth / 2) + 6, y + 4, shadowColor);
		drawCenteredString(Minecraft.getMinecraft().fontRendererObj, str, middle, y, color);
	}
	
	public static void drawShortSectionHeader(final String str, final int middle, final int y, final int color) {
		final int shadowColor = (color & 16579836) >> 2 | color & -16777216;
		final int imgWidth = Minecraft.getMinecraft().fontRendererObj.getStringWidth(str);
		drawHorizontalLine(middle - 115, middle - (imgWidth / 2) - 5, y + 3, color);
		drawHorizontalLine(middle + 115, middle + (imgWidth / 2) + 5, y + 3, color);
		drawHorizontalLine(middle - 115, middle - (imgWidth / 2) - 4, y + 4, shadowColor);
		drawHorizontalLine(middle + 116, middle + (imgWidth / 2) + 6, y + 4, shadowColor);
		drawCenteredString(Minecraft.getMinecraft().fontRendererObj, str, middle, y, color);
	}
	
	public static void drawOutlinedString(final FontRenderer fontRenderer, final String str, final int x, final int y,
			int color, final boolean invert) {
		int shadowColor = (color & 16579836) >> 2 | color & -16777216;
		if (invert) {
			final int col = color;
			color = shadowColor;
			shadowColor = col;
		}
		if (!fontRenderer.getUnicodeFlag()) {
			fontRenderer.drawString(str, x - 1, y, shadowColor);
			fontRenderer.drawString(str, x + 1, y, shadowColor);
			fontRenderer.drawString(str, x, y - 1, shadowColor);
			fontRenderer.drawString(str, x, y + 1, shadowColor);
			
			fontRenderer.drawString(str, x + 1, y - 1, shadowColor);
			fontRenderer.drawString(str, x + 1, y + 1, shadowColor);
			
			fontRenderer.drawString(str, x - 1, y - 1, shadowColor);
			fontRenderer.drawString(str, x - 1, y + 1, shadowColor);
		}
		// this translate stuff makes a 1px outline on Normal GUI scale, which makes HD fonts look nicer and looks better with unicode font
		GL11.glPushMatrix();
		GL11.glTranslatef(-0.5f, 0f, 0f);
		fontRenderer.drawString(str, x, y, shadowColor, false);
		GL11.glPopMatrix();
		
		GL11.glPushMatrix();
		GL11.glTranslatef(0.5f, 0f, 0f);
		fontRenderer.drawString(str, x, y, shadowColor, false);
		GL11.glPopMatrix();
		
		GL11.glPushMatrix();
		GL11.glTranslatef(0f, -0.5f, 0f);
		fontRenderer.drawString(str, x, y, shadowColor, false);
		GL11.glPopMatrix();
		
		GL11.glPushMatrix();
		GL11.glTranslatef(0f, 0.5f, 0f);
		fontRenderer.drawString(str, x, y, shadowColor, false);
		GL11.glPopMatrix();
		
		GL11.glPushMatrix();
		GL11.glTranslatef(0.5f, -0.5f, 0f);
		fontRenderer.drawString(str, x, y, shadowColor, false);
		GL11.glPopMatrix();
		
		GL11.glPushMatrix();
		GL11.glTranslatef(0.5f, 0.5f, 0f);
		fontRenderer.drawString(str, x, y, shadowColor, false);
		GL11.glPopMatrix();
		
		GL11.glPushMatrix();
		GL11.glTranslatef(-0.5f, -0.5f, 0f);
		fontRenderer.drawString(str, x, y, shadowColor, false);
		GL11.glPopMatrix();
		
		GL11.glPushMatrix();
		GL11.glTranslatef(-0.5f, 0.5f, 0f);
		fontRenderer.drawString(str, x, y, shadowColor, false);
		GL11.glPopMatrix();
		
		GL11.glPushMatrix();
		GL11.glTranslatef(0f, 0f, 0.01f);
		fontRenderer.drawString(str, x, y, color, false);
		GL11.glPopMatrix();
	}
	
	public static final ResourceLocation	MOJANG_LOGO	= new ResourceLocation("textures/gui/title/mojang.png");
	
	public static void drawFullScreenLogo(final ResourceLocation logo, final int bg) {
		Minecraft mc = Minecraft.getMinecraft();
		ScaledResolution scaledresolution = new ScaledResolution(mc, mc.displayWidth, mc.displayHeight);
        int i = scaledresolution.getScaleFactor();
        Framebuffer framebuffer = new Framebuffer(scaledresolution.getScaledWidth() * i, scaledresolution.getScaledHeight() * i, true);
        framebuffer.bindFramebuffer(false);
        GlStateManager.matrixMode(5889);
        GlStateManager.loadIdentity();
        GlStateManager.ortho(0.0D, (double)scaledresolution.getScaledWidth(), (double)scaledresolution.getScaledHeight(), 0.0D, 1000.0D, 3000.0D);
        GlStateManager.matrixMode(5888);
        GlStateManager.loadIdentity();
        GlStateManager.translate(0.0F, 0.0F, -2000.0F);
        GlStateManager.disableLighting();
        GlStateManager.disableFog();
        GlStateManager.disableDepth();
        GlStateManager.enableTexture2D();
        mc.getTextureManager().bindTexture(logo);
        Tessellator tessellator = Tessellator.getInstance();
        WorldRenderer worldrenderer = tessellator.getWorldRenderer();
        worldrenderer.startDrawingQuads();
        worldrenderer.setColorOpaque_I(16777215);
        worldrenderer.addVertexWithUV(0.0D, (double)mc.displayHeight, 0.0D, 0.0D, 0.0D);
        worldrenderer.addVertexWithUV((double)mc.displayWidth, (double)mc.displayHeight, 0.0D, 0.0D, 0.0D);
        worldrenderer.addVertexWithUV((double)mc.displayWidth, 0.0D, 0.0D, 0.0D, 0.0D);
        worldrenderer.addVertexWithUV(0.0D, 0.0D, 0.0D, 0.0D, 0.0D);
        tessellator.draw();
        GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
        worldrenderer.setColorOpaque_I(bg);
        short short1 = 256;
        short short2 = 256;
        mc.scaledTessellator((scaledresolution.getScaledWidth() - short1) / 2, (scaledresolution.getScaledHeight() - short2) / 2, 0, 0, short1, short2);
        GlStateManager.disableLighting();
        GlStateManager.disableFog();
        framebuffer.unbindFramebuffer();
        framebuffer.framebufferRender(scaledresolution.getScaledWidth() * i, scaledresolution.getScaledHeight() * i);
        GlStateManager.enableAlpha();
        GlStateManager.alphaFunc(516, 0.1F);
        mc.updateDisplay();
	}
	
	protected void drawHoveringText(List textLines, int x, int y, FontRenderer font) {
        if (!textLines.isEmpty()) {
        	Minecraft mc = Minecraft.getMinecraft();
            GlStateManager.disableRescaleNormal();
            RenderHelper.disableStandardItemLighting();
            GlStateManager.disableLighting();
            GlStateManager.disableDepth();
            int k = 0;
            Iterator iterator = textLines.iterator();

            while (iterator.hasNext()) {
                String s = (String)iterator.next();
                int l = font.getStringWidth(s);

                if (l > k) {
                    k = l;
                }
            }

            int j2 = x + 12;
            int k2 = y - 12;
            int i1 = 8;

            if (textLines.size() > 1) {
                i1 += 2 + (textLines.size() - 1) * 10;
            }

            if (j2 + k > getWidth()) {
                j2 -= 28 + k;
            }

            if (k2 + i1 + 6 > getHeight()) {
                k2 = getHeight() - i1 - 6;
            }

            float z = 300.0F;
            int j1 = -267386864;
            drawGradientRect(j2 - 3, k2 - 4, j2 + k + 3, k2 - 3, j1, j1, z);
            drawGradientRect(j2 - 3, k2 + i1 + 3, j2 + k + 3, k2 + i1 + 4, j1, j1, z);
            drawGradientRect(j2 - 3, k2 - 3, j2 + k + 3, k2 + i1 + 3, j1, j1, z);
            drawGradientRect(j2 - 4, k2 - 3, j2 - 3, k2 + i1 + 3, j1, j1, z);
            drawGradientRect(j2 + k + 3, k2 - 3, j2 + k + 4, k2 + i1 + 3, j1, j1, z);
            int k1 = 1347420415;
            int l1 = (k1 & 16711422) >> 1 | k1 & -16777216;
            drawGradientRect(j2 - 3, k2 - 3 + 1, j2 - 3 + 1, k2 + i1 + 3 - 1, k1, l1, z);
            drawGradientRect(j2 + k + 2, k2 - 3 + 1, j2 + k + 3, k2 + i1 + 3 - 1, k1, l1, z);
            drawGradientRect(j2 - 3, k2 - 3, j2 + k + 3, k2 - 3 + 1, k1, k1, z);
            drawGradientRect(j2 - 3, k2 + i1 + 2, j2 + k + 3, k2 + i1 + 3, l1, l1, z);

            for (int i2 = 0; i2 < textLines.size(); ++i2) {
                String s1 = (String)textLines.get(i2);
                font.drawStringWithShadow(s1, j2, k2, -1);

                if (i2 == 0) {
                    k2 += 2;
                }

                k2 += 10;
            }

            GlStateManager.enableLighting();
            GlStateManager.enableDepth();
            RenderHelper.enableStandardItemLighting();
            GlStateManager.enableRescaleNormal();
        }
    }
	
	private static int getHeight() {
		final Minecraft mc = Minecraft.getMinecraft();
		if (mc.currentScreen != null) {
			return mc.currentScreen.height;
		} else {
			return new ScaledResolution(mc, mc.displayWidth, mc.displayHeight).getScaledHeight();
		}
	}
	
	private static int getWidth() {
		final Minecraft mc = Minecraft.getMinecraft();
		if (mc.currentScreen != null) {
			return mc.currentScreen.width;
		} else {
			return new ScaledResolution(mc, mc.displayWidth, mc.displayHeight).getScaledWidth();
		}
	}
	
}
