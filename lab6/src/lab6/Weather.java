package lab6;

import java.util.List;

public class Weather
{
    public int visibility;
    //public Main main;
    //public List<weatherDescription> weather;
    public String name;

    public class Main
    {
        private double temp;
        private double temp_min;
        private double temp_max;
        private double pressure;

    }

    public class weatherDescription
    {
    	private String id;
    	private String main;
    	private String description;
    	private String icon;
    }
    
    public int getvisibility() { return visibility; }
    //public Main getmain() { return main; }
    //public List<weatherDescription> getweather() { return weather; }
    public String getname() { return name; }

    public void setvisibility(int visibility) { this.visibility = visibility; }
    //public void setmain(Main main) { this.main = main; }
    //public void setweather(List<weatherDescription> weather) { this.weather = weather; }
    public void setname(String name) { this.name = name; }

    public String toString() {
        return String.format("title:%s,id:%d", visibility,name);
    }
}

