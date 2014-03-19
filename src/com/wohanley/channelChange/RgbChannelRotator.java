package com.wohanley.channelChange;

import java.nio.ByteBuffer;

public class RgbChannelRotator extends ChannelRotator {

	@Override
	public Integer rotateChannels(Integer pixel, ChannelRotationDirection direction)
	{
		byte[] originalBytes = toBytes(pixel);
		byte[] rotatedBytes = toBytes(pixel);
		
		// The first byte is alpha, so we actually want 1, 2, and 3. Inside the
		// loop we add 1 to account for that. It's easier this way to deal with
		// the modular arithmetic.
		for (int i = 0; i < 3; i++)
		{
			rotatedBytes[i + 1] = originalBytes[addMod(3, i, rotationSteps.get(direction)) + 1];
		}
		
		return ByteBuffer.wrap(rotatedBytes).getInt();
	}
}
