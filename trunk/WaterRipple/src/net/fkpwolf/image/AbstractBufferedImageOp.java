package net.fkpwolf.image;



import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.PointF;
import android.graphics.Rect;

/**
 * A convenience class which implements those methods of BufferedImageOp which are rarely changed.
 */
public abstract class AbstractBufferedImageOp{// implements Cloneable {

//    public Bitmap createCompatibleDestImage(Bitmap src) {
////        if ( dstCM == null )
////            dstCM = src.getColorModel();
////        return new Bitmap(dstCM, dstCM.createCompatibleWritableRaster(src.getWidth(), src.getHeight()), dstCM.isAlphaPremultiplied(), null);
//    	return BitmapFactory.decodeByteArray(src.getNinePatchChunk(), 0, src.getNinePatchChunk().length);
//    }
    
//    public Rect getBounds2D( Bitmap src ) {
//        return new Rect(0, 0, src.getWidth(), src.getHeight());
//    }
    
//    public PointF getPoint2D( PointF srcPt, PointF dstPt ) {
//        if ( dstPt == null )
//            dstPt = new PointF();
//        dstPt.set( srcPt.x, srcPt.y );
//        return dstPt;
//    }

//    public RenderingHints getRenderingHints() {
//        return null;
//    }

	/**
	 * A convenience method for getting ARGB pixels from an image. This tries to avoid the performance
	 * penalty of BufferedImage.getRGB unmanaging the image.
     * @param image   a BufferedImage object
     * @param x       the left edge of the pixel block
     * @param y       the right edge of the pixel block
     * @param width   the width of the pixel arry
     * @param height  the height of the pixel arry
     * @param pixels  the array to hold the returned pixels. May be null.
     * @return the pixels
     * @see #setRGB
     */
	public int[] getRGB( Bitmap image, int x, int y, int width, int height, int[] pixels ) {
//		int type = image.getType();
//		if ( type == BufferedImage.TYPE_INT_ARGB || type == BufferedImage.TYPE_INT_RGB )
//			return (int [])image.getRaster().getDataElements( x, y, width, height, pixels );
//		return image.getRGB( x, y, width, height, pixels, 0, width );
		pixels = new int[width * height];
		image.getPixels(pixels, 0, width, x, y, width, height);
		return pixels;
    }

	/**
	 * A convenience method for setting ARGB pixels in an image. This tries to avoid the performance
	 * penalty of BufferedImage.setRGB unmanaging the image.
     * @param image   a BufferedImage object
     * @param x       the left edge of the pixel block
     * @param y       the right edge of the pixel block
     * @param width   the width of the pixel arry
     * @param height  the height of the pixel arry
     * @param pixels  the array of pixels to set
     * @see #getRGB
	 */
	public void setRGB( Bitmap image, int x, int y, int width, int height, int[] pixels ) {
//		int type = image.getType();
//		if ( type == BufferedImage.TYPE_INT_ARGB || type == BufferedImage.TYPE_INT_RGB )
//			image.getRaster().setDataElements( x, y, width, height, pixels );
//		else
//			image.setRGB( x, y, width, height, pixels, 0, width );

		image.setPixels(pixels, 0, width, x, y, width, height);
    }

//	public Object clone() {
//		try {
//			return super.clone();
//		}
//		catch ( CloneNotSupportedException e ) {
//			return null;
//		}
//	}
}
