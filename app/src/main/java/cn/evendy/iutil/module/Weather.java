package cn.evendy.iutil.module;

/**
 * @author: evendy
 * @time: 2015/5/14 15:35
 */
public class Weather {
    private WeatherInfo weatherinfo;

    public WeatherInfo getWeatherinfo() {
        return weatherinfo;
    }

    public void setWeatherinfo(WeatherInfo weatherinfo) {
        this.weatherinfo = weatherinfo;
    }

    @Override
    public String toString() {
        return "Weather{" +
                "weatherinfo=" + weatherinfo +
                '}';
    }
}
