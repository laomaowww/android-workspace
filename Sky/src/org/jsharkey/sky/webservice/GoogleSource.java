package org.jsharkey.sky.webservice;

import java.io.IOException;
import java.io.Reader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.jsharkey.sky.webservice.Forecast.ParseException;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlPullParserFactory;

import android.util.Log;
import android.util.TimeFormatException;

/**
 * 
 * @author fan
 *
 */
public class GoogleSource implements ForecastSource {

	private String WEBSERVICE_URL = "http://www.google.com/ig/api?weather=shanghai";

	private static XmlPullParserFactory sFactory = null;

	private static String TAG = "GoogleSource";
	
	@Override
	public List<Forecast> getForecasts(double lat, double lon, int days)
			throws ParseException {
		return null;
	}

	public List<Forecast> getForecasts(String cityName) throws ParseException {
        //String url = String.format(WEBSERVICE_URL, cityName);
		String url = WEBSERVICE_URL;
        Reader reader = WebserviceHelper.queryApi(url);
        
        // Parse incoming forecast data
        List<Forecast> forecasts = parseResponse(reader);
        return forecasts;
	}

	  private static List<Forecast> parseResponse(Reader response) throws ParseException {
	        // Keep a temporary mapping between time series tags and forecasts
		  	List<Forecast> list = new ArrayList<Forecast>();
	        String detailsUrl = null;

	        try {
	            if (sFactory == null) {
	                sFactory = XmlPullParserFactory.newInstance();
	            }
	            XmlPullParser xpp = sFactory.newPullParser();

	            String thisTag = null;
	            String thisData = null;

	            Forecast forecast  = null;

	            xpp.setInput(response);
	            int eventType = xpp.getEventType();
	            while (eventType != XmlPullParser.END_DOCUMENT) {
	                if (eventType == XmlPullParser.START_TAG) {
	                    thisTag = xpp.getName();
	                    
	                    thisData = xpp.getAttributeValue(null, "data");
	                    
	                    if ("day_of_week".equals(thisTag)){
	                    	//if (forecast != null)
	                    	//forecast.validStart = NoaaSource.parseDate(thisData);
	                    	forecast.validStart = getValidStart(thisData);
	                    }else if ("low".equals(thisTag)){
	                    	if (forecast != null)
	                    		forecast.tempLow = Integer.parseInt(thisData);
	                    }else if("high".equals(thisTag)){
	                    	if (forecast != null)
	                    		forecast.tempHigh = Integer.parseInt(thisData);
	                    }else if("condition".equals(thisTag)){
	                    	if (forecast != null)
	                    		forecast.conditions = thisData;
	                    }else if("icon".equals(thisTag)) {
	                    	if (forecast != null)
	                    		forecast.url = thisData;
	                    } else if("forecast_conditions".equals(thisTag)){
	                    	forecast = new Forecast();
	                    	list.add(forecast);
	                    }
	                } else if (eventType == XmlPullParser.END_TAG) {
	                    thisTag = null;

	                } 
	                
	                eventType = xpp.next();
	            }
	        } catch (IOException e) {
	            throw new ParseException("Problem parsing XML forecast", e);
	        } catch (XmlPullParserException e) {
	            throw new ParseException("Problem parsing XML forecast", e);
	        } catch (TimeFormatException e) {
	            throw new ParseException("Problem parsing XML forecast", e);
	        }
	        
	        Log.d(TAG , list.toString());
	        return list;
	    }

	private static long getValidStart(String thisData) {
		if("Mon".equals(thisData)){
			return 2;
		}else if ("Tue".equals(thisData)){
			return 3;
		}else if ("Wed".equals(thisData)){
			return 4;
		}else if ("Thu".equals(thisData)){
			return 5;
		}else if ("Fri".equals(thisData)){
			return 6;
		}else if ("Sat".equals(thisData)){
			return 7;
		}else if ("Sun".equals(thisData)){
			return 1;
		}else
			return -1;
	}
	
	public static String getValidStart(long thisData) {
		if(thisData == 2 )
			return "星期一";
		else if(thisData == 3 )
			return "星期二";
		else if(thisData == 4 )
			return "星期三";
		else if(thisData == 5 )
			return "星期四";
		else if(thisData == 6 )
			return "星期五";
		else if(thisData == 7 )
			return "星期六";
		else if(thisData == 1 )
			return "星期天";
		else
			return "星期无";
	}

}
