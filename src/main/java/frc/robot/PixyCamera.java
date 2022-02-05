package frc.robot;

import java.util.ArrayList;

import io.github.pseudoresonance.pixy2api.Pixy2;
import io.github.pseudoresonance.pixy2api.Pixy2CCC;
import io.github.pseudoresonance.pixy2api.Pixy2CCC.Block;
import io.github.pseudoresonance.pixy2api.links.SPILink;

public class PixyCamera {
	private static Pixy2 pixy;

	private static Boolean LampsEnabled = true;
	public static int blockCount = 0;

	public static void initialize() {
		pixy = Pixy2.createInstance(new SPILink()); // Creates a new Pixy2 camera using SPILink
		pixy.init(); // Initializes the camera and prepares to send/receive data
		pixy.setLamp((byte) 1, (byte) 1); // Turns the LEDs on
		pixy.setLED(255, 255, 255); // Sets the RGB LED to full white
	}

	public static Block getBiggestBlock(int sigMap) {
		// Gets the number of "blocks", identified targets, that match signature 1 on
		// the Pixy2,
		// does not wait for new data if none is available,
		// and limits the number of returned blocks to 25, for a slight increase in
		// efficiency
		blockCount = pixy.getCCC().getBlocks(false, sigMap, 25);
		System.out.println("Found " + blockCount + " blocks!"); // Reports number of blocks found
		if (blockCount <= 0) {
			return null; // If blocks were not found, stop processing
		}
		ArrayList<Block> blocks = pixy.getCCC().getBlockCache(); // Gets a list of all blocks found by the Pixy2
		Block largestBlock = null;
		for (Block block : blocks) { // Loops through all blocks and finds the widest one
			if (largestBlock == null) {
				largestBlock = block;
			} else if (block.getWidth() > largestBlock.getWidth()) {
				largestBlock = block;
			}
		}
		return largestBlock;
	}

	public static void toggleLights() {
		LampsEnabled = !LampsEnabled;
		if (LampsEnabled) {
			pixy.setLamp((byte) 1, (byte) 1); // Turns the LEDs on
			pixy.setLED(255, 255, 255); // Sets the RGB LED to full white
		} else {
			pixy.setLamp((byte) 0, (byte) 0); // Turns the LEDs on
		}
	}

	public static void lightsOn() {
		LampsEnabled = true;
		pixy.setLamp((byte) 1, (byte) 1); // Turns the LEDs on
		pixy.setLED(255, 255, 255); // Sets the RGB LED to full white
	}
	
	public static void lightsOff() {
		LampsEnabled = false;
		pixy.setLamp((byte) 0, (byte) 0);
	}
}

