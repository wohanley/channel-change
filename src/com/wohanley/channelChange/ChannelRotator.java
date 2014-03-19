package com.wohanley.channelChange;

import java.util.HashMap;
import java.util.Map;
import java.nio.ByteBuffer;

public class ChannelRotator
{
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
	
	private Map<ChannelRotationDirection, Integer> rotationSteps = new HashMap<ChannelRotationDirection, Integer>()
	{{
		put(ChannelRotationDirection.RedToGreenToBlue, -1);
		put(ChannelRotationDirection.RedToBlueToGreen, 1);
	}};
	
	private byte[] toBytes(int number)
	{
		return new byte[]
		{
            (byte)(number >>> 24),
            (byte)(number >>> 16),
            (byte)(number >>> 8),
            (byte)number
	    };
	}
	
	private int addMod(int modulus, int x, int y)
	{
		int r = (x + y) % modulus;
		return r >= 0 ? r : r + modulus;
	}
}
