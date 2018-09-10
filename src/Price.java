import org.json.JSONObject;

import javax.swing.*;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class Price
{
    public JSONObject GetPrice(String Origin, String Destination) throws Exception
    {
        //取得起站的車站ID
        String OriginStationID = Main.StationID.get(Origin);
        //取得訖站的車站ID
        String DestinationStationID = Main.StationID.get(Destination);
        String url = "http://ptx.transportdata.tw/MOTC/v2/Rail/THSR/ODFare/" + OriginStationID + "/to/" + DestinationStationID + "?$top=30&$format=JSON";
        URL obj = new URL(url);
        //與上面的網址做訪問的動作
        HttpURLConnection con = (HttpURLConnection) obj.openConnection();
        //設定Http 的模式
        con.setRequestMethod("GET");
        con.setRequestProperty("User-Agent", "Mozilla/5.0");
        //取得網頁回傳的狀態，如果不是200代表出現錯誤
        int responseCode = con.getResponseCode();
        if(responseCode != 200)
        {
            JOptionPane.showMessageDialog(null, "查詢時發生錯誤", "錯誤訊息", JOptionPane.ERROR_MESSAGE);
        }
        //        System.out.println("\nSending 'GET' request to URL : " + url);
//        System.out.println("Response Code : " + responseCode);
        //讀入網頁回傳的JSON內容
        BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
        String inputLine;
        StringBuffer response = new StringBuffer();
        while ((inputLine = in.readLine()) != null) {
            response.append(inputLine);
        }
        in.close();
        //去掉網頁回傳的JSON頭尾的[]，不然會無法解析JSON
        String a = response.substring(1,response.length()-1);
//            System.out.println(response);
        JSONObject myResponse = new JSONObject(a);
        
//        //取得起點車站
//        System.out.println("起點站名- "+myResponse.getJSONObject("OriginStationName").get("Zh_tw"));
//        System.out.println("OriginStationID- "+myResponse.getString("OriginStationID"));
//        //取得終點車站
//        System.out.println("終點站名- "+myResponse.getJSONObject("DestinationStationName").get("Zh_tw"));
//        System.out.println("DestinationStationID- "+myResponse.getString("DestinationStationID"));
//
//        //取得 北上(1)或南下(0)
//        System.out.println("Direction- "+myResponse.getInt("Direction"));
//
//
//        JSONArray fares = myResponse.getJSONArray("Fares");
//        //取得 一般座陣列
//        JSONObject normal = (JSONObject) fares.get(0);
//        System.out.println("Price- "+normal.get("Price"));
//        System.out.println("TicketType- "+normal.get("TicketType"));
//        //取得 商務座陣列
//        JSONObject business = (JSONObject) fares.get(1);
//        System.out.println("Price- "+business.get("Price"));
//        System.out.println("TicketType- "+business.get("TicketType"));
//        //取得 自由座陣列
//        JSONObject free = (JSONObject) fares.get(2);
//        System.out.println("Price- "+free.get("Price"));
//        System.out.println("TicketType- "+free.get("TicketType"));
    
        return myResponse;
    }
}
