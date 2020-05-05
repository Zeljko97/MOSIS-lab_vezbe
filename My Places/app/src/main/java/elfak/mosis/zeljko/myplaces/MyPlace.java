package elfak.mosis.zeljko.myplaces;

import androidx.annotation.NonNull;

public class MyPlace {
    String name;
    String description;
    String longitude;
    String latitude;
    int ID;

    public int getID() {
        return  ID;
    }
    public  void setID(int ID){
        this.ID = ID;
    }

    public String getLongitute() {
        return longitude;
    }
    public String getLatitude() {
        return latitude;
    }

    public void setLongitude(String longitude){
        this.longitude = longitude;
    }
    public void setLatitude(String latitude){
        this.latitude = latitude;
    }
    public MyPlace(String nm, String desc)
    {
        this.name=nm;
        this.description=desc;
    }
    public MyPlace(String nm)
    {
        this(nm,"");
    }
    public String getName() {   return name;    }
    public String getDesc() { return description; }

    public void setName(String nm)
    {
        this.name=nm;
    }
    public void setDescription(String desc)
    {
        this.description=desc;
    }

    @NonNull
    @Override
    public String toString() {
        return this.name;
    }
}
