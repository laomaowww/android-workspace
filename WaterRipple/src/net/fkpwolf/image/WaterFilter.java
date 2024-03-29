package net.fkpwolf.image;
import android.graphics.Bitmap;
import android.graphics.Point;
import android.graphics.PointF;



/**
 * A filter which produces a water ripple distortion.
 */
public class WaterFilter extends TransformFilter {
	
	private float wavelength = 16;
	private float amplitude = 10;
	private float phase = 0;
	private float centreX = 0.5f;
	private float centreY = 0.5f;
	private float radius = 50;

	private float radius2 = 0;
	private float icentreX;
	private float icentreY;

	public WaterFilter() {
		setEdgeAction( CLAMP );
	}

	/**
	 * Set the wavelength of the ripples.
	 * @param wavelength the wavelength
     * @see #getWavelength
	 */
	public void setWavelength(float wavelength) {
		this.wavelength = wavelength;
	}

	/**
	 * Get the wavelength of the ripples.
	 * @return the wavelength
     * @see #setWavelength
	 */
	public float getWavelength() {
		return wavelength;
	}

	/**
	 * Set the amplitude of the ripples.
	 * @param amplitude the amplitude
     * @see #getAmplitude
	 */
	public void setAmplitude(float amplitude) {
		this.amplitude = amplitude;
	}

	/**
	 * Get the amplitude of the ripples.
	 * @return the amplitude
     * @see #setAmplitude
	 */
	public float getAmplitude() {
		return amplitude;
	}

	/**
	 * Set the phase of the ripples.
	 * @param phase the phase
     * @see #getPhase
	 */
	public void setPhase(float phase) {
		this.phase = phase;
	}

	/**
	 * Get the phase of the ripples.
	 * @return the phase
     * @see #setPhase
	 */
	public float getPhase() {
		return phase;
	}

	/**
	 * Set the centre of the effect in the X direction as a proportion of the image size.
	 * @param centreX the center
     * @see #getCentreX
	 */
	public void setCentreX( float centreX ) {
		this.centreX = centreX;
	}

	/**
	 * Get the centre of the effect in the X direction as a proportion of the image size.
	 * @return the center
     * @see #setCentreX
	 */
	public float getCentreX() {
		return centreX;
	}
	
	/**
	 * Set the centre of the effect in the Y direction as a proportion of the image size.
	 * @param centreY the center
     * @see #getCentreY
	 */
	public void setCentreY( float centreY ) {
		this.centreY = centreY;
	}

	/**
	 * Get the centre of the effect in the Y direction as a proportion of the image size.
	 * @return the center
     * @see #setCentreY
	 */
	public float getCentreY() {
		return centreY;
	}
	
	/**
	 * Set the centre of the effect as a proportion of the image size.
	 * @param centre the center
     * @see #getCentre
	 */
	public void setCentre( PointF centre ) {
		this.centreX = centre.x;
		this.centreY = centre.y;
	}

	/**
	 * Get the centre of the effect as a proportion of the image size.
	 * @return the center
     * @see #setCentre
	 */
	public PointF getCentre() {
		return new PointF( centreX, centreY );
	}
	
	/**
	 * Set the radius of the effect.
	 * @param radius the radius
     * @min-value 0
     * @see #getRadius
	 */
	public void setRadius(float radius) {
		this.radius = radius;
	}

	/**
	 * Get the radius of the effect.
	 * @return the radius
     * @see #setRadius
	 */
	public float getRadius() {
		return radius;
	}

	private boolean inside(int v, int a, int b) {
		return a <= v && v <= b;
	}
	
    public Bitmap filter( Bitmap src, Bitmap dst ) {
		icentreX = src.getWidth() * centreX;
		icentreY = src.getHeight() * centreY;
		if ( radius == 0 )
			radius = Math.min(icentreX, icentreY);
		radius2 = radius*radius;
		return super.filter( src, dst );
	}
	
	protected void transformInverse(int x, int y, float[] out) {
		
		float dx = x-icentreX;
		float dy = y-icentreY;
		float distance2 = dx*dx + dy*dy;
		//out[0] = x; out[1]= y; return;
		
		if (distance2 > radius2) {
			out[0] = x;
			out[1] = y;
		} else {
			float distance = (float)Math.sqrt(distance2);
			//float amount = amplitude * (float)Math.sin(distance / wavelength * ImageMath.TWO_PI - phase);
			float amount = amplitude * (float)Math.sin(distance / 2.83 - phase);
			amount *= (radius-distance)/radius;
			if ( distance != 0 )
				amount *= wavelength/distance;
			out[0] = x + dx*amount; 
			out[1] = y + dy*amount;
		}
		
	}

	public String toString() {
		return "Distort/Water Ripples...";
	}
	
}
