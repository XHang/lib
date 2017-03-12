package com.crawler.Model;
/**
 * һ�������
 * @author Mr-hang
 */
public class Weather {
	private String date;
	private String temperatureRange;
	private String city;
	private String dayWeather;
	private String nightWeather;
	private String dayDirectionAndWindpower;
	private String nightDirectionAndWindpower;
	/**
	 * �õ���ǰ����������
	 * @return
	 */
	public String getDate() {
		return date;
	}
	/**
	 * ���õ�ǰ����������
	 * @param date
	 */
	public void setDate(String date) {
		this.date = date;
	}
	public String getDayWeather() {
		return dayWeather;
	}
	public void setDayWeather(String dayWeather) {
		this.dayWeather = dayWeather;
	}
	public String getDayDirectionAndWindpower() {
		return dayDirectionAndWindpower;
	}
	public void setDayDirectionAndWindpower(String dayDirectionAndWindpower) {
		this.dayDirectionAndWindpower = dayDirectionAndWindpower;
	}
	public String getNightDirectionAndWindpower() {
		return nightDirectionAndWindpower;
	}
	public void setNightDirectionAndWindpower(String nightDirectionAndWindpower) {
		this.nightDirectionAndWindpower = nightDirectionAndWindpower;
	}
	public String getNightWeather() {
		return nightWeather;
	}
	public void setNightWeather(String nightWeather) {
		this.nightWeather = nightWeather;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getTemperatureRange() {
		return temperatureRange;
	}
	public void setTemperatureRange(String temperatureRange) {
		this.temperatureRange = temperatureRange;
	}
    @Override
    public String toString() {
        return "Weather [date=" + date + ", temperatureRange=" + temperatureRange + ", city=" + city + ", dayWeather="
                + dayWeather + ", nightWeather=" + nightWeather + ", dayDirectionAndWindpower="
                + dayDirectionAndWindpower + ", nightDirectionAndWindpower=" + nightDirectionAndWindpower + "]";
    }
	
}
