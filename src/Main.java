import java.util.HashMap;

public class Main
{
    public static HashMap<String, String> StationID = new HashMap<>();
    public static void main(String[] args)
    {
        //設定每個車站對應的車站ID
        StationID.put("南港","0990");
        StationID.put("台北","1000");
        StationID.put("板橋","1010");
        StationID.put("桃園","1020");
        StationID.put("新竹","1030");
        StationID.put("苗栗","1035");
        StationID.put("台中","1040");
        StationID.put("彰化","1043");
        StationID.put("雲林","1047");
        StationID.put("嘉義","1050");
        StationID.put("台南","1060");
        StationID.put("左營","1070");
        //呼叫介面開啟
        GUI gui = new GUI();
        gui.JFrame();
    }
}

