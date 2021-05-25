package lab6;

import java.util.ArrayList;
import java.util.List;

public class Weather
{
    public int visibility;
    public Main main;
    public ArrayList<weatherDescription> weather;
    public String name;

    public class Main
    {
    	public double temp;
    	public double temp_min;
    	public double temp_max;
    	public double pressure;

    }

    public class weatherDescription
    {
    	public String id;
    	public String main;
    	public String description;
    	public String icon;
    }
    
    public int getvisibility() { return visibility; }
    public Main getmain() { return main; }
    public List<weatherDescription> getweather() { return weather; }
    public String getname() { return name; }

    public void setvisibility(int visibility) { this.visibility = visibility; }
    public void setmain(Main main) { this.main = main; }
    public void setweather(ArrayList<weatherDescription> weather) { this.weather = weather; }
    public void setname(String name) { this.name = name; }

    public String toString() {
        return String.format("visibility:%d,name:%s,temp:%f,temp_min:%f,temp_max:%f,pressure:%f,id:%s,main:%s,description:%s,icon:%s", visibility,name,main.temp,main.temp_min,main.temp_max,main.pressure,weather.get(0).id,weather.get(0).main,weather.get(0).description,weather.get(0).icon);
    }
}

