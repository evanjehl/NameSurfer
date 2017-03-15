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
			for (int p1 = 0; p1 < width / 2; p1++) {
				int p2 = width - p1 - 1;
				int temp = array[i][p1];
				array[i][p1] = array[i][p2];
				array[i][p2] = temp;
			}
		}
		return new GImage(array);
	}
}