package com.crawler.Model;
/**
 * 
 * @author Administrator 当前天气包装类
 *
 */
public class CurrentWeather {
    private String city;
    private String windPower;
    private String humidity;
    private String sunrise;
    private String sunset;
    private String temperature;
    private String upTime;
    /**
     * 获取温度
     * @return
     */
    public String getTemperature() {
        return temperature;
    }
    /**
     * 设置温度
     * @param temperature
     */
    public void setTemperature(String temperature) {
        this.temperature = temperature;
    }

    /**
     * 得到当前城市
     * 
     * @return String 城市
     */
    public String getCity() {
        return city;
    }

    /**
     * 设置当前城市
     * 
     * @param city
     *            城市名称
     */
    public void setCity(String city) {
        this.city = city;
    }
    /**
     * 得到风力
     * 
     * @return
     */
    public String getWindPower() {
        return windPower;
    }

    /**
     * 设置风力
     * 
     * @param windPower
     *            风力
     */
    public void setWindPower(String windPower) {
        this.windPower = windPower;
    }

    /**
     * 得到湿度
     * 
     * @return
     */
    public String getHumidity() {
        return humidity;
    }

    /**
     * 设置湿度
     * 
     * @param humidity
     */
    public void setHumidity(String humidity) {
        this.humidity = humidity;
    }

    /**
     * 得到日出时间
     * 
     * @return
     */
    public String getSunrise() {
        return sunrise;
    }

    /**
     * 设置日出时间
     * 
     * @param sunrise
     */
    public void setSunrise(String sunrise) {
        this.sunrise = sunrise;
    }

    /**
     * 获取日落时间
     * 
     * @return
     */
    public String getSunset() {
        return sunset;
    }

    /**
     * 设置日落时间
     * 
     * @param sunset
     */
    public void setSunset(String sunset) {
        this.sunset = sunset;
    }
  
    @Override
    public String toString() {
        return "CurrentWeather [city=" + city + ", windPower=" + windPower + ", humidity=" + humidity + ", sunrise="
                + sunrise + ", sunset=" + sunset + ", temperature=" + temperature + ", upTime=" + upTime + "]";
    }
    public CurrentWeather(){}
    /**
     * 获取本次更新的时间
     * @return
     */
	public String getUpTime() {
		return upTime;
	}
	/**
	 * 设置本次更新的时间
	 * @param upTime
	 */
	public void setUpTime(String upTime) {
		this.upTime = upTime;
	}

}
