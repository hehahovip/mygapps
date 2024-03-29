/**
 * 
 */
package com.hehaho.metalpriceinfofetcher.bean;

import java.text.NumberFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.logging.Logger;

/**
 * @author Kevin.Wang
 *
 */
public class MetalPriceBean {
	
	private static Logger Log = Logger.getLogger(MetalPriceBean.class.toString());
	
	private static NumberFormat Formatter = NumberFormat.getInstance(Locale.US);
	
	private static SimpleDateFormat DateFormatter = new SimpleDateFormat();

	private double closedPrice;
	
	private double totalAsset;
	
	private double valueOnOunces;
	
	private double valueOnTonners;
	
	private String date;
	
	private double diffOnOunces;
	
	private double diffOnTonners;
	
	static {
		Formatter.setMaximumFractionDigits(3);
	}

	
	public void setClosedPrice(String closedPrice){
		if(checkNull(closedPrice)){
			closedPrice = closedPrice.replace(",", "");
			try{
				this.closedPrice = Double.parseDouble(closedPrice);
			} catch (Exception e) {
				Log.severe("Parsing closedPrice error! closedPrice = " + closedPrice);
			}
		}
	}
	
	public String getClosedPriceString(){
		return Formatter.format(closedPrice);
	}
	
	public void setTotalNetAsset(String totalNetAsset){
		if(checkNull(totalNetAsset)){
			String temp = totalNetAsset.replace("$","");
			temp = temp.replace(",", "");
			try{
				this.totalAsset = Double.parseDouble(temp);
			} catch (Exception e) {
				Log.severe("Parsing totalAsset error! totalAsset = " + temp);
			}
		}
	}
	
	public String getTotalNetAssetString(){
		return Formatter.format(totalAsset);
	}
	
	public void setValueOnOunces(String valueOnOunces){
		if(checkNull(valueOnOunces)){
			valueOnOunces = valueOnOunces.replace(",", "");
			try{
				this.valueOnOunces = Double.parseDouble(valueOnOunces);
			} catch (Exception e) {
				Log.severe("Parsing valueOnOunces error! valueOnOunces = " + valueOnOunces);
			}
		}
	}
	
	public String getValueOnOuncesString(){
		return Formatter.format(valueOnOunces);
	}
	
	public void setvalueOnTonners(String valueOnTonners){
		if(checkNull(valueOnTonners)){
			valueOnTonners = valueOnTonners.replace(",", "");
			try{
				this.valueOnTonners = Double.parseDouble(valueOnTonners);
			} catch (Exception e) {
				Log.severe("Parsing valueOnTonners error! valueOnTonners = " + valueOnTonners);
			}
		}
	}
	
	public String getValueOnTonnersString(){
		return Formatter.format(valueOnTonners);
	}
	
	public void setDateString(String date){
		if(checkNull(date)){
			String[] array = date.trim().split(" ");
			
			try {
				DateFormatter.applyPattern("M/d/yyyy");
				Date temp = DateFormatter.parse(array[array.length -1]);
				DateFormatter.applyPattern("yyyy-MM-dd");
				this.setDate(DateFormatter.format(temp));
			} catch (ParseException e) {
				Log.severe("Parsing date error! date = " + date);
			}
		}
	}
	
	/**
	 * @return the closedPrice
	 */
	public double getClosedPrice() {
		return closedPrice;
	}

	/**
	 * @param closedPrice the closedPrice to set
	 */
	public void setClosedPrice(double closedPrice) {
		this.closedPrice = closedPrice;
	}

	/**
	 * @return the totalAsset
	 */
	public double getTotalAsset() {
		return totalAsset;
	}

	/**
	 * @param totalAsset the totalAsset to set
	 */
	public void setTotalAsset(double totalAsset) {
		this.totalAsset = totalAsset;
	}

	/**
	 * @return the valueOnOunces
	 */
	public double getValueOnOunces() {
		return valueOnOunces;
	}

	/**
	 * @param valueOnOunces the valueOnOunces to set
	 */
	public void setValueOnOunces(double valueOnOunces) {
		this.valueOnOunces = valueOnOunces;
	}

	/**
	 * @return the valueOnTonners
	 */
	public double getValueOnTonners() {
		return valueOnTonners;
	}

	/**
	 * @param valueOnTonners the valueOnTonners to set
	 */
	public void setValueOnTonners(double valueOnTonners) {
		this.valueOnTonners = valueOnTonners;
	}

	/**
	 * @return the date
	 */
	public String getDate() {
		return date;
	}

	/**
	 * @param date the date to set
	 */
	public void setDate(String date) {
		this.date = date;
	}
	
	/**
	 * @return the diffOnOunces
	 */
	public double getDiffOnOunces() {
		return diffOnOunces;
	}
	
	public String getDiffOnOuncesString(){
		return Formatter.format(diffOnOunces);
	}

	/**
	 * @param diffOnOunces the diffOnOunces to set
	 */
	public void setDiffOnOunces(double diffOnOunces) {
		this.diffOnOunces = diffOnOunces;
	}

	/**
	 * @return the diffOnTonners
	 */
	public double getDiffOnTonners() {
		return diffOnTonners;
	}
	
	public String getDiffOnTonnersString(){
		return Formatter.format(diffOnTonners);
	}

	/**
	 * @param diffOnTonners the diffOnTonners to set
	 */
	public void setDiffOnTonners(double diffOnTonners) {
		this.diffOnTonners = diffOnTonners;
	}

	public boolean checkNull(String value){
		return value!= null && !"".equals(value.trim());
	}
	
	
}
