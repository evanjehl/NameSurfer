import acm.graphics.*;
import acm.program.*;

public class FlipHorizontal extends GraphicsProgram {
	public void run () {
		GImage flippedImage = flipHorizontal(new GImage("milkmaid.jpg"));
	}
	
	private GImage flipHorizontal(GImage image) {
		int[][] array = image.getPixelArray();
		int height = array.length;
		int width = array[0].length;
		for (int i = 0; i < height; i++) {
			for (int p1 = 0; p1 < width; p1++) {
				
			}
		}
	}
}