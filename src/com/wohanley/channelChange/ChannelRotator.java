package com.wohanley.channelChange;

import java.util.HashMap;
import java.util.Map;
import java.nio.ByteBuffer;

public abstract class ChannelRotator
{
	public abstract Integer rotateChannels(Integer pixel, ChannelRotationDirection direction);
	
	protected Map<ChannelRotationDirection, Integer> rotationSteps = new HashMap<ChannelRotationDirection, Integer>()
	{{
		put(ChannelRotationDirection.RedToGreenToBlue, -1);
		put(ChannelRotationDirection.RedToBlueToGreen, 1);
	}};
	
	protected byte[] toBytes(int number)
	{
		return new byte[]
		{
            (byte)(number >>> 24),
            (byte)(number >>> 16),
            (byte)(number >>> 8),
            (byte)number
	    };
	}
	
	protected int addMod(int modulus, int x, int y)
	{
		int r = (x + y) % modulus;
		return r >= 0 ? r : r + modulus;
	}
}
