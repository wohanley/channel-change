package com.wohanley.channelChange;

import processing.core.*;

public class App extends PApplet
{
	private boolean drawn = false;
	private PImage img;
	private ChannelRotator rotator;
	
	public void setup()
	{
		img = loadImage("../resources/wind2.jpg");
		size(img.width, img.height);
		image(img, 0, 0);
		rotator = new ChannelRotator();
	}
	
	public void draw()
	{
		if (!drawn) {
			drawSelf();
			saveFrame("output.jpg");
		}
	}
	
	private void drawSelf()
	{
		loadPixels();
		
		for (int i = 0; i < pixels.length; i++)
		{
			pixels[i] = rotator.rotateChannels(pixels[i], ChannelRotationDirection.RedToGreenToBlue);
		}
		
		updatePixels();
		
		drawn = true;
	}
}
