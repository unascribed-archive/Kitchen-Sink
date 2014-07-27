package gminers.kitchensink;


import java.util.List;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.client.renderer.Tessellator;
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
		final Tessellator tessellator = Tessellator.instance;
		GL11.glEnable(GL11.GL_BLEND);
		GL11.glDisable(GL11.GL_TEXTURE_2D);
		GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);
		GL11.glTranslatef(x, y, 0);
		final float r = (color >> 16 & 255) / 255.0F;
		final float g = (color >> 8 & 255) / 255.0F;
		final float b = (color & 255) / 255.0F;
		final float a = (color >> 24 & 255) / 255.0F;
		GL11.glColor4f(r, g, b, a);
		tessellator.startDrawing(GL11.GL_POLYGON);
		for (int i = 0; i < count; ++i) {
			tessellator.addVertex(Math.sin(i / ((double) count) * 2 * Math.PI) * (radius),
					Math.cos(i / ((double) count) * 2 * Math.PI) * (radius), z);
		}
		tessellator.draw();
		GL11.glEnable(GL11.GL_TEXTURE_2D);
		GL11.glDisable(GL11.GL_BLEND);
		GL11.glPopMatrix();
	}
	
	public static void drawGradientRect(final int x1, final int y1, final int x2, final int y2, final int color1,
			final int color2) {
		final float f = (color1 >> 24 & 255) / 255.0F;
		final float f1 = (color1 >> 16 & 255) / 255.0F;
		final float f2 = (color1 >> 8 & 255) / 255.0F;
		final float f3 = (color1 & 255) / 255.0F;
		final float f4 = (color2 >> 24 & 255) / 255.0F;
		final float f5 = (color2 >> 16 & 255) / 255.0F;
		final float f6 = (color2 >> 8 & 255) / 255.0F;
		final float f7 = (color2 & 255) / 255.0F;
		GL11.glDisable(GL11.GL_TEXTURE_2D);
		GL11.glEnable(GL11.GL_BLEND);
		GL11.glDisable(GL11.GL_ALPHA_TEST);
		GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);
		GL11.glShadeModel(GL11.GL_SMOOTH);
		final Tessellator tessellator = Tessellator.instance;
		tessellator.startDrawingQuads();
		tessellator.setColorRGBA_F(f1, f2, f3, f);
		tessellator.addVertex(x2, y1, 0D);
		tessellator.addVertex(x1, y1, 0D);
		tessellator.setColorRGBA_F(f5, f6, f7, f4);
		tessellator.addVertex(x1, y2, 0D);
		tessellator.addVertex(x2, y2, 0D);
		tessellator.draw();
		GL11.glShadeModel(GL11.GL_FLAT);
		GL11.glDisable(GL11.GL_BLEND);
		GL11.glEnable(GL11.GL_ALPHA_TEST);
		GL11.glEnable(GL11.GL_TEXTURE_2D);
	}
	
	public static void drawHorzGradientRect(final int x1, final int y1, final int x2, final int y2, final int color1,
			final int color2) {
		final float f = (color1 >> 24 & 255) / 255.0F;
		final float f1 = (color1 >> 16 & 255) / 255.0F;
		final float f2 = (color1 >> 8 & 255) / 255.0F;
		final float f3 = (color1 & 255) / 255.0F;
		final float f4 = (color2 >> 24 & 255) / 255.0F;
		final float f5 = (color2 >> 16 & 255) / 255.0F;
		final float f6 = (color2 >> 8 & 255) / 255.0F;
		final float f7 = (color2 & 255) / 255.0F;
		GL11.glDisable(GL11.GL_TEXTURE_2D);
		GL11.glEnable(GL11.GL_BLEND);
		GL11.glDisable(GL11.GL_ALPHA_TEST);
		GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);
		GL11.glShadeModel(GL11.GL_SMOOTH);
		final Tessellator tessellator = Tessellator.instance;
		tessellator.startDrawingQuads();
		tessellator.setColorRGBA_F(f1, f2, f3, f);
		tessellator.addVertex(x1, y2, 0D);
		tessellator.setColorRGBA_F(f5, f6, f7, f4);
		tessellator.addVertex(x2, y2, 0D);
		tessellator.addVertex(x2, y1, 0D);
		tessellator.setColorRGBA_F(f1, f2, f3, f);
		tessellator.addVertex(x1, y1, 0D);
		tessellator.draw();
		GL11.glShadeModel(GL11.GL_FLAT);
		GL11.glDisable(GL11.GL_BLEND);
		GL11.glEnable(GL11.GL_ALPHA_TEST);
		GL11.glEnable(GL11.GL_TEXTURE_2D);
	}
	
	public static void drawTexturedModalRect(final int x, final int y, final int u, final int v, final int width,
			final int height) {
		drawTexturedModalRect(x, y, u, v, width, height, 0);
	}
	
	public static void drawTexturedModalRect(final int x, final int y, final int u, final int v, final int width,
			final int height, final double z) {
		final float f = 0.00390625F;
		final float f1 = 0.00390625F;
		final Tessellator tessellator = Tessellator.instance;
		tessellator.startDrawingQuads();
		tessellator.addVertexWithUV(x + 0, y + height, z, (u + 0) * f, (v + height) * f1);
		tessellator.addVertexWithUV(x + width, y + height, z, (u + width) * f, (v + height) * f1);
		tessellator.addVertexWithUV(x + width, y + 0, z, (u + width) * f, (v + 0) * f1);
		tessellator.addVertexWithUV(x + 0, y + 0, z, (u + 0) * f, (v + 0) * f1);
		tessellator.draw();
	}
	
	private static void drawHorizontalLine(int x1, int x2, final int y1, final int y2) {
		if (x2 < x1) {
			final int i1 = x1;
			x1 = x2;
			x2 = i1;
		}
		
		drawRect(x1, y1, x2 + 1, y1 + 1, y2);
	}
	
	public static void drawRect(final int x1, final int y1, final int x2, final int y2, final int color) {
		drawRect(x1, y1, x2, y2, color, 0.0);
	}
	
	public static void drawRect(final int x1, final int y1, final int x2, final int y2, final int color, final double z) {
		drawRect((float) x1, (float) y1, (float) x2, (float) y2, color, z);
	}
	
	public static void drawRect(float x1, float y1, float x2, float y2, final int color, final double z) {
		float j1;
		
		if (x1 < x2) {
			j1 = x1;
			x1 = x2;
			x2 = j1;
		}
		
		if (y1 < y2) {
			j1 = y1;
			y1 = y2;
			y2 = j1;
		}
		
		final float f = (color >> 24 & 255) / 255.0F;
		final float f1 = (color >> 16 & 255) / 255.0F;
		final float f2 = (color >> 8 & 255) / 255.0F;
		final float f3 = (color & 255) / 255.0F;
		final Tessellator tessellator = Tessellator.instance;
		GL11.glEnable(GL11.GL_BLEND);
		GL11.glDisable(GL11.GL_TEXTURE_2D);
		GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);
		GL11.glColor4f(f1, f2, f3, f);
		tessellator.startDrawingQuads();
		tessellator.addVertex(x1, y2, z);
		tessellator.addVertex(x2, y2, z);
		tessellator.addVertex(x2, y1, z);
		tessellator.addVertex(x1, y1, z);
		tessellator.draw();
		GL11.glEnable(GL11.GL_TEXTURE_2D);
		GL11.glDisable(GL11.GL_BLEND);
	}
	
	public static void drawCenteredString(final FontRenderer fontRenderer, final String str, final int middle,
			final int y, final int color) {
		fontRenderer.drawStringWithShadow(str, middle - fontRenderer.getStringWidth(str) / 2, y, color);
	}
	
	public static void drawSectionHeader(final String str, final int middle, final int y, final int color) {
		final int shadowColor = (color & 16579836) >> 2 | color & -16777216;
		final int imgWidth = Minecraft.getMinecraft().fontRenderer.getStringWidth(str);
		drawHorizontalLine(middle - 200, middle - (imgWidth / 2) - 5, y + 3, color);
		drawHorizontalLine(middle + 200, middle + (imgWidth / 2) + 5, y + 3, color);
		drawHorizontalLine(middle - 200, middle - (imgWidth / 2) - 4, y + 4, shadowColor);
		drawHorizontalLine(middle + 201, middle + (imgWidth / 2) + 6, y + 4, shadowColor);
		drawCenteredString(Minecraft.getMinecraft().fontRenderer, str, middle, y, color);
	}
	
	public static void drawShortSectionHeader(final String str, final int middle, final int y, final int color) {
		final int shadowColor = (color & 16579836) >> 2 | color & -16777216;
		final int imgWidth = Minecraft.getMinecraft().fontRenderer.getStringWidth(str);
		drawHorizontalLine(middle - 115, middle - (imgWidth / 2) - 5, y + 3, color);
		drawHorizontalLine(middle + 115, middle + (imgWidth / 2) + 5, y + 3, color);
		drawHorizontalLine(middle - 115, middle - (imgWidth / 2) - 4, y + 4, shadowColor);
		drawHorizontalLine(middle + 116, middle + (imgWidth / 2) + 6, y + 4, shadowColor);
		drawCenteredString(Minecraft.getMinecraft().fontRenderer, str, middle, y, color);
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
		final Minecraft mc = Minecraft.getMinecraft();
		GL11.glEnable(GL11.GL_TEXTURE_2D);
		mc.renderEngine.bindTexture(logo);
		final ScaledResolution scaledresolution = new ScaledResolution(mc, mc.displayWidth,
				mc.displayHeight);
		final int i = scaledresolution.getScaleFactor();
		final Framebuffer framebuffer = new Framebuffer(scaledresolution.getScaledWidth() * i,
				scaledresolution.getScaledHeight() * i, true);
		framebuffer.bindFramebuffer(false);
		GL11.glMatrixMode(GL11.GL_PROJECTION);
		GL11.glLoadIdentity();
		GL11.glOrtho(0.0D, scaledresolution.getScaledWidth(), scaledresolution.getScaledHeight(), 0.0D, 1000.0D,
				3000.0D);
		GL11.glMatrixMode(GL11.GL_MODELVIEW);
		GL11.glLoadIdentity();
		GL11.glTranslatef(0.0F, 0.0F, -2000.0F);
		GL11.glDisable(GL11.GL_LIGHTING);
		GL11.glDisable(GL11.GL_FOG);
		GL11.glDisable(GL11.GL_DEPTH_TEST);
		GL11.glEnable(GL11.GL_TEXTURE_2D);
		mc.renderEngine.bindTexture(logo);
		final Tessellator tessellator = Tessellator.instance;
		tessellator.startDrawingQuads();
		tessellator.setColorOpaque_I(bg);
		tessellator.addVertexWithUV(0.0D, mc.displayHeight, 0.0D, 0.0D, 0.0D);
		tessellator.addVertexWithUV(mc.displayWidth, mc.displayHeight, 0.0D, 0.0D, 0.0D);
		tessellator.addVertexWithUV(mc.displayWidth, 0.0D, 0.0D, 0.0D, 0.0D);
		tessellator.addVertexWithUV(0.0D, 0.0D, 0.0D, 0.0D, 0.0D);
		tessellator.draw();
		GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
		tessellator.setColorOpaque_I(bg);
		final short short1 = 256;
		final short short2 = 256;
		mc.scaledTessellator((scaledresolution.getScaledWidth() - short1) / 2,
				(scaledresolution.getScaledHeight() - short2) / 2, 0, 0, short1, short2);
		GL11.glDisable(GL11.GL_LIGHTING);
		GL11.glDisable(GL11.GL_FOG);
		framebuffer.unbindFramebuffer();
		framebuffer.framebufferRender(scaledresolution.getScaledWidth() * i, scaledresolution.getScaledHeight() * i);
		GL11.glEnable(GL11.GL_ALPHA_TEST);
		GL11.glAlphaFunc(GL11.GL_GREATER, 0.1F);
		GL11.glFlush();
		mc.func_147120_f();
	}
	
	public static void drawHoveringText(final List<String> text, final int x, final int y, final int width,
			final int height,
			final FontRenderer font) {
		if (!text.isEmpty()) {
			int longestLine = 0;
			for (final String s : text) {
				final int stringWidth = font.getStringWidth(s);
				
				if (stringWidth > longestLine) {
					longestLine = stringWidth;
				}
			}
			
			int textX = x + 12;
			int textY = y - 12;
			int boxHeight = font.FONT_HEIGHT;
			
			if (text.size() > 1) {
				boxHeight += 2 + (text.size() - 1) * 10;
			}
			
			if (textX + longestLine > width) {
				if (textX - (28 + longestLine) >= 0) {
					textX -= 28 + longestLine;
				}
			}
			
			if (textY + boxHeight + 6 > height) {
				textY = getHeight() - boxHeight - 6;
			}
			
			final int color = 0xF0100010;
			GL11.glTranslatef(0.0f, 0.0f, 35.0f);
			drawGradientRect(textX - 3, textY - 4, textX + longestLine + 3, textY - 3, color, color);
			drawGradientRect(textX - 3, textY + boxHeight + 3, textX + longestLine + 3, textY + boxHeight + 4, color,
					color);
			drawGradientRect(textX - 3, textY - 3, textX + longestLine + 3, textY + boxHeight + 3, color, color);
			drawGradientRect(textX - 4, textY - 3, textX - 3, textY + boxHeight + 3, color, color);
			drawGradientRect(textX + longestLine + 3, textY - 3, textX + longestLine + 4, textY + boxHeight + 3, color,
					color);
			final int color1 = 0x505000FF;
			final int color2 = (color1 & 0x9F5F5F1) >> 1 | color1 & 0xFF000000;
			drawGradientRect(textX - 3, textY - 3 + 1, textX - 3 + 1, textY + boxHeight + 3 - 1, color1, color2);
			drawGradientRect(textX + longestLine + 2, textY - 3 + 1, textX + longestLine + 3, textY + boxHeight + 3
					- 1, color1, color2);
			drawGradientRect(textX - 3, textY - 3, textX + longestLine + 3, textY - 3 + 1, color1, color1);
			drawGradientRect(textX - 3, textY + boxHeight + 2, textX + longestLine + 3, textY + boxHeight + 3, color2,
					color2);
			
			for (int i = 0; i < text.size(); ++i) {
				final String s = text.get(i);
				font.drawStringWithShadow(s, textX, textY, 0xFFFFFF);
				
				if (i == 0) {
					textY += 2;
				}
				
				textY += 10;
			}
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
