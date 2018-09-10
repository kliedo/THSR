import org.json.JSONArray;
import org.json.JSONObject;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GUI
{
    public void JFrame()
    {
        JFrame demo = new JFrame("高鐵票價計算系統");
        demo.setSize(750, 650);
        demo.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        demo.setLayout(null);
    
        JLabel Title = new JLabel("高鐵票價計算系統");
        Title.setSize(600,50);
        Title.setLocation(185, 20);
        Title.setFont(new Font("標楷體",Font.BOLD,40));
        demo.add(Title);
        
        //文字:起站
        JLabel OriginStation = new JLabel("起站: ");
        OriginStation.setSize(150,100);
        OriginStation.setLocation(100, 60);
        OriginStation.setFont(new Font("標楷體",Font.BOLD,22));
        demo.add(OriginStation);
    
        //文字:訖站
        JLabel DestinationStation = new JLabel("訖站: ");
        DestinationStation.setSize(150,100);
        DestinationStation.setLocation(100, 160);
        DestinationStation.setFont(new Font("標楷體",Font.BOLD,22));
        demo.add(DestinationStation);
        
        //設定下拉選單內的文字
        String[] StationList = {"南港","台北","板橋","桃園","新竹","苗栗","台中","彰化","雲林","嘉義","台南","左營"};
        
        //起站的下拉選單
        JComboBox OriginStationName = new JComboBox(StationList);
        OriginStationName.setSize(100,30);
        OriginStationName.setLocation(200, 100);
        demo.add(OriginStationName);
        
        //訖站的下拉選單
        JComboBox DestinationStationName = new JComboBox(StationList);
        DestinationStationName.setSize(100,30);
        DestinationStationName.setLocation(200, 200);
        //訖站預設為左營
        DestinationStationName.setSelectedIndex(11);
        demo.add(DestinationStationName);
        
        //結果1 -> 用來顯示北上南下
        JLabel result = new JLabel();
        result.setSize(700,50);
        result.setLocation(185, 300);
        result.setFont(new Font("標楷體",Font.BOLD,35));
        demo.add(result);
    
        //結果2 -> 用來顯示普通座
        JLabel result1 = new JLabel();
        result1.setSize(700,50);
        result1.setLocation(185, 350);
        result1.setFont(new Font("標楷體",Font.BOLD,35));
        demo.add(result1);
    
        //結果3 -> 用來顯示商務座
        JLabel result2 = new JLabel();
        result2.setSize(700,50);
        result2.setLocation(185, 400);
        result2.setFont(new Font("標楷體",Font.BOLD,35));
        demo.add(result2);
    
        //結果4 -> 用來顯示自由座
        JLabel result3 = new JLabel();
        result3.setSize(700,50);
        result3.setLocation(185, 450);
        result3.setFont(new Font("標楷體",Font.BOLD,35));
        demo.add(result3);
        
        //按鈕
        JButton button3 = new JButton("計算");
        button3.setSize(100, 50);
        button3.setLocation(350, 140);
        button3.setFont(new Font("標楷體",Font.BOLD,20));
        
        //為按鈕加入事件，按鈕被按時會執行的動作
        ActionListener action = new ActionListener()
        {
            public void actionPerformed(ActionEvent e)
            {
                //取得起站的下拉選單被選種的項目
                String GetOriginStationName = (String)OriginStationName.getSelectedItem();
                //取得訖站的下拉選單被選種的項目
                String GetDestinationStationName = (String)DestinationStationName.getSelectedItem();
                //先過濾起訖站是否相同
                if(GetOriginStationName.equalsIgnoreCase(GetDestinationStationName))
                {
                    JOptionPane.showMessageDialog(null, "起站不能與終站相同!", "錯誤訊息", JOptionPane.ERROR_MESSAGE);
                }
                else
                {
                    Price price = new Price();
                    try
                    {
                        JSONObject myResponse = price.GetPrice(GetOriginStationName,GetDestinationStationName);
                        JSONArray fares = myResponse.getJSONArray("Fares");
                        //普通座
                        JSONObject normal = (JSONObject) fares.get(0);
                        //商務座
                        JSONObject business = (JSONObject) fares.get(1);
                        //自由座
                        JSONObject free = (JSONObject) fares.get(2);
                        if(myResponse.getInt("Direction") == 0)
                        {
                            result.setText("方向: 南下");
                        }
                        else
                        {
                            result.setText("方向: 北上");
                        }
                        result1.setText("普通座 價位:" + normal.get("Price"));
                        result2.setText("商務座 價位:" + business.get("Price"));
                        result3.setText("自由座 價位:" + free.get("Price"));
                    }
                    catch(Exception e1)
                    {
                        JOptionPane.showMessageDialog(null, "錯誤訊息: " + e1.getMessage(), "錯誤訊息", JOptionPane.ERROR_MESSAGE);
                    }
                }
            }
        };
        button3.addActionListener(action);
        demo.add(button3);
        
        demo.setVisible(true);
    }
    
}
