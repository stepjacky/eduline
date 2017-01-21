package org.jackysoft.utils;

import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;

public class StreamUtils {
	/**
	 * scale image
	 * 
	 * @param sbi image to scale
	 * @param imageType type of image
	 * @param dWidth width of destination image
	 * @param dHeight height of destination image
	 * @param fWidth x-factor for transformation / scaling
	 * @param fHeight y-factor for transformation / scaling
	 * @return scaled image
	 */
	public static BufferedImage scale(BufferedImage input) {
	    BufferedImage output = null;
	    if(input != null) {
	    
	    	int width  = input.getWidth();
	    	int height = input.getHeight();
	    	
	    	if(width<=1024) return input;
	    	int vh = height*1024/width;
	    	double v = 1024/width;
	    	output = new BufferedImage(1024, vh, BufferedImage.TYPE_INT_RGB);
	        Graphics2D g = output.createGraphics();
	        g.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
	        g.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);
	        g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
	        AffineTransform at = AffineTransform.getScaleInstance(1, 1);
	        g.drawRenderedImage(input, at);
	    }
	    
	    
	    return output;
	}
}
