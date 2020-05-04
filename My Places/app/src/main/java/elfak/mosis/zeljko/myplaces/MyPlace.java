package elfak.mosis.zeljko.myplaces;

import androidx.annotation.NonNull;

public class MyPlace {
    String name;
    String description;
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
