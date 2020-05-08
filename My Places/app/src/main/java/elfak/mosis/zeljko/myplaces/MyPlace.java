package elfak.mosis.zeljko.myplaces;

import androidx.annotation.NonNull;

import com.google.firebase.database.Exclude;
import com.google.firebase.database.IgnoreExtraProperties;

@IgnoreExtraProperties
public class MyPlace {
    public String name;
    public String description;
    public String longitude;
    public String latitude;
    @Exclude
    public String key;
    public MyPlace() {}


    public String getLongitude() {
        return longitude;
    }
    public String getLatitude() {
        return latitude;
    }
    /*
    public void setLongitude(String longitude){
      this.longitude = longitude;
    }
    public void setLatitude(String latitude){
        this.latitude = latitude;
    }*/
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

    /*public void setName(String nm)
    {
        this.name=nm;
    }
    public void setDescription(String desc)
    {
        this.description=desc;
    }*/

    @NonNull
    @Override
    public String toString() {
        return this.name;
    }
}
