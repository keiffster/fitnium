package com.magneticreason.fitnium.flash;

/**
 * http://www.adobe.com/support/flash/publishexport/scriptingwithflash/scriptingwithflash_03.html
 */

import com.magneticreason.fitnium.FitniumFixture;
import com.magneticreason.fitnium.api.FitniumVariableAPI;
import com.magneticreason.fitnium.utils.FitniumUtils;

import com.thoughtworks.selenium.FlashSelenium;
import com.thoughtworks.selenium.SeleniumException;

public class FitniumFlashFixture extends FitniumFixture {

	protected FlashSelenium flashApp;

	public FitniumFlashFixture () {
		super ();
	}
	
	public void initialiseFlashApplication (String appName) {
		if ( null == this.getSelenium() ) {
			throw new SeleniumException ( "Selenium must be initialised first" );
		}
		this.flashApp = new FlashSelenium ( this.getSelenium(), appName );
	}
	
	public boolean isFlashApplicationInitialised() {
		return (this.flashApp!=null);
	}
	
	public void waitForFlashApplicationToLoad () {
		this.waitForFlashApplicationToLoadForSeconds("30");
	}
	
	public void waitForFlashApplicationToLoadForSeconds ( String seconds) {
		long millis = Long.parseLong(seconds)*1000L;
		long timeNow = System.currentTimeMillis();
		
		while ( 100 != flashApp.PercentLoaded() ) {
			if ( System.currentTimeMillis() > (timeNow+millis) )
				throw new SeleniumException ("Timed out waiting for flash app to load");
			try {
				Thread.sleep(500);
			} catch (InterruptedException e) {
				throw new SeleniumException ("Interrupted waiting for flash app to load");
			}
		}
	}

	/**
	 * Standard Methods
	 */
	
	public boolean isFlashApplicationLoaded () {
		return (flashApp.PercentLoaded()==100);
	}
	
	public String flashCall ( String function ) {
		return flashApp.call(function);
	}
	
	public void storeResultOfFlashCallIn(String function, String name) {
    	FitniumVariableAPI.setFitniumVariableCalledWithValue ( this, name, flashCall(function));
	}
	
	public String flashCallWithData ( String function, String data ) {
		return flashApp.call(function, FitniumUtils.stringToArray(data));
	}
	
	public void storeResultOfFlashCallWithDataIn(String function, String data, String name) {
    	FitniumVariableAPI.setFitniumVariableCalledWithValue ( this, name, flashCallWithData(function, data));
	}
	
	public String getFlashVariable ( String name ) {
		return flashApp.GetVariable(name);
	}
	
	public void setFlashVariable(String name, String value) {
		flashApp.SetVariable(name, value);
	}
	
	public void loadFlashMovieFromUrlIntoLayer (String url, int layerNumber ) {
		flashApp.LoadMovie(layerNumber, url);
	}
	
	public int percentageFlashMovieLoaded () {
		return flashApp.PercentLoaded();
	}
	
	public int numberOfFramesInFlashMovie () {
		return flashApp.TotalFrames();
	}
	
	public void playFlashMovie ( ) {
		flashApp.Play();
	}
	
	public boolean isFlashMoviePlaying ()  {
		return flashApp.IsPlaying();
	}
	
	public void stopFlashMovie () {
		flashApp.StopPlay();
	}
	
	public void rewindFlashMovie () {
		flashApp.Rewind();
	}
	
	public void gotoFrameInFlashMovie ( int frame ) {
		flashApp.GotoFrame(frame);
	}

	public void panFlashMovie ( int x, int y, int mode ) {
		flashApp.Pan(x, y, mode);
	}
	
	public void zoomFlashMovie ( int percent ) {
		flashApp.Zoom(percent);	
	}
	
	public void setFlashMovieZoomRctangle ( int left, int top, int right, int bottom ) {
		flashApp.SetZoomRect(left, top, right, bottom);
	}
	
	/**
	 * Tell Methods
	 */
	
	public void playFlashMovieTarget ( String target ) {
		flashApp.TPlay(target);
	}
	
	public void stopFlashMovieTarget ( String target ) {
		flashApp.TStopPlay(target);	
	}

	public String getFlashMovieTargetProperty ( String target, String property ) {
		return flashApp.TGetProperty(target, property);
	}
	
	public int getFlashMovieTargetPropertyAsNumber ( String target, String property ) {
		return flashApp.TGetPropertyAsNumber(target, property);
	}

	public void setFlashMovieTargetPropertyValue ( String target, String property, String value ) {
		flashApp.TSetProperty(target, property, value);
	}

	public String currentFlashMovieTargetLabel ( String target ) {
		return flashApp.TCurrentLabel(target);
	}

	public void callFlashMovieTargetLabel ( String target, String label ) {
		flashApp.TCallLabel(target, label);
	}
	
	public void gotoFlashMovieTargetLabel ( String target, String label ) {
		flashApp.TGotoLabel(target, label);
	}
	
	public int currentFlashMovieTargetFrame ( String target ) {
		return flashApp.TCurrentFrame(target);
	}

	public void callFlashMovieTargetFrame ( String target, int frame ) {
		flashApp.TCallFrame(target, frame );
	}
	
	public void gotoFlashMovieTargetLabel ( String target, int frame ) {
		flashApp.TGotoFrame(target, frame );
	}
}
