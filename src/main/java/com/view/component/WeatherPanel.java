package com.view.component;

import com.view.model.Model_Card;
import java.awt.Color;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridLayout;
import java.awt.RenderingHints;
import java.io.File;
import java.io.IOException;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.json.JsonArray;
import javax.json.JsonValue;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import weather.forecast.model.WeatherData;

public class WeatherPanel extends javax.swing.JPanel implements  Runnable{

    private String dir = null;
    private JsonArray currData;
    private JsonArray forecastData;

    public Color getColor1() {
        return color1;
    }

    public void setColor1(Color color1) {
        this.color1 = color1;
    }

    public Color getColor2() {
        return color2;
    }

    public void setColor2(Color color2) {
        this.color2 = color2;
    }

    private Color color1;
    private Color color2;

    public WeatherPanel() {
        String path = "src\\main\\java\\com\\view\\icon";
        File file = new File(path);
        String absolutePath = file.getAbsolutePath();
        dir = absolutePath;
        initComponents();
        setOpaque(false);
        color1 = Color.BLACK; 
        color2 = Color.WHITE;
        Thread t = new Thread(this);
        t.start();
    }

    public void run() {

        while (true) {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException ex) {
                ex.printStackTrace();
            }
            lblDongHo.setText(getTime());
        }
    }

    public String getTime() {
        Date now = new Date();
        String pattern = "HH:mm:ss a dd-MM-yyyy ";
        SimpleDateFormat dateFormat = new SimpleDateFormat(pattern);
        return dateFormat.format(now);

    }

    public void getWeatherData() {
        String query = txtCity.getText();
        if (query.isEmpty()) {
            return;
        }
        // gather data from Open Weather Map API
        // get current weather data
        // url = api.openweathermap.org/data/2.5/weather?q={city_name}&APPID=3506dfa8bbebf7709e6fba904a68559a
        WeatherData weatherData = null;
        try {
            weatherData = new WeatherData(query);
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(this, "Lỗi server !");
        }

        currData = weatherData.getCurrData();
        forecastData = weatherData.getForecastData();
        try {
            new lstCityDialogFrame(lblCityName, lblPressure, LblTemperature, lblWeatherDescription, lblWeatherMain, lblWindDirection, lblWindSpeed, dateList, currData, forecastData).setVisible(true);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Không có dữ liệu trả về");
        }

        if (currData != null) {
            for (JsonValue el : currData) {
                System.out.println(currData.get(0).asJsonObject().getJsonObject("main"));
            }
        }
    }

//        public void loadToJdialog() {
//        ArrayList<String> li = new ArrayList<>();
//        if (localJsonArray != null) {
//            for (JsonValue el : localJsonArray) {
//                String listval = el.asJsonObject().getString("name");
//                listval += " (" + el.asJsonObject().getJsonObject("coord").getInt("lon");
//                listval += ", " + el.asJsonObject().getJsonObject("coord").getInt("lat") + ")";
//                li.add(listval);
//            }
//        }
//        String[] strs = new String[li.size()];
//        li.toArray(strs);
//        jDialogCityList.setModel(new javax.swing.AbstractListModel<String>() {
//            String[] strings = strs;
//            public int getSize() {
//                return strings.length;
//            }
//            public String getElementAt(int i) {
//                return strings[i];
//            }
//        });
//    }
    public JLabel getLblTemperature() {
        return LblTemperature;
    }

    public void setLblTemperature(JLabel LblTemperature) {
        this.LblTemperature = LblTemperature;
    }

    public JLabel getLblCityName() {
        return lblCityName;
    }

    public void setLblCityName(JLabel lblCityName) {
        this.lblCityName = lblCityName;
    }

    public JLabel getLblPressure() {
        return lblPressure;
    }

    public void setLblPressure(JLabel lblPressure) {
        this.lblPressure = lblPressure;
    }

    public JLabel getLblWeatherDescription() {
        return lblWeatherDescription;
    }

    public void setLblWeatherDescription(JLabel lblWeatherDescription) {
        this.lblWeatherDescription = lblWeatherDescription;
    }

    public JLabel getLblWeatherMain() {
        return lblWeatherMain;
    }

    public void setLblWeatherMain(JLabel lblWeatherMain) {
        this.lblWeatherMain = lblWeatherMain;
    }

    public JLabel getLblWindDirection() {
        return lblWindDirection;
    }

    public void setLblWindDirection(JLabel lblWindDirection) {
        this.lblWindDirection = lblWindDirection;
    }

    public JLabel getLblWindSpeed() {
        return lblWindSpeed;
    }

    public void setLblWindSpeed(JLabel lblWindSpeed) {
        this.lblWindSpeed = lblWindSpeed;
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        lbIcon = new javax.swing.JLabel();
        txtCity = new javax.swing.JTextField();
        searchBtn = new javax.swing.JButton();
        lblCityName = new javax.swing.JLabel();
        LblTemperature = new javax.swing.JLabel();
        lblWeatherMain = new javax.swing.JLabel();
        lblWeatherDescription = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        lblWindSpeed = new javax.swing.JLabel();
        lblWindDirection = new javax.swing.JLabel();
        lblPressure = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        dateList = new javax.swing.JList<>();
        lblDongHo = new javax.swing.JLabel();

        setForeground(new java.awt.Color(255, 255, 255));
        setPreferredSize(new java.awt.Dimension(600, 446));

        lbIcon.setIcon(new javax.swing.ImageIcon(dir+"stock.png"));

        txtCity.setBackground(new java.awt.Color(240, 240, 240));
        txtCity.setFont(new java.awt.Font("Rockwell", 0, 14)); // NOI18N
        txtCity.setToolTipText("");
        txtCity.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtCityActionPerformed(evt);
            }
        });

        searchBtn.setBackground(new java.awt.Color(102, 0, 102));
        searchBtn.setFont(new java.awt.Font("Rockwell", 1, 12)); // NOI18N
        searchBtn.setForeground(new java.awt.Color(255, 255, 255));
        searchBtn.setText("Search");
        searchBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                searchBtnActionPerformed(evt);
            }
        });

        lblCityName.setFont(new java.awt.Font("Rockwell", 0, 24)); // NOI18N
        lblCityName.setForeground(new java.awt.Color(255, 255, 255));
        lblCityName.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblCityName.setText("----");

        LblTemperature.setFont(new java.awt.Font("Rockwell", 0, 60)); // NOI18N
        LblTemperature.setForeground(new java.awt.Color(255, 255, 255));
        LblTemperature.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
        LblTemperature.setText("------");

        lblWeatherMain.setFont(new java.awt.Font("Rockwell", 0, 18)); // NOI18N
        lblWeatherMain.setForeground(new java.awt.Color(255, 255, 255));
        lblWeatherMain.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblWeatherMain.setText("-----");

        lblWeatherDescription.setFont(new java.awt.Font("Rockwell", 0, 12)); // NOI18N
        lblWeatherDescription.setForeground(new java.awt.Color(255, 255, 255));
        lblWeatherDescription.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblWeatherDescription.setText("------");

        jLabel4.setFont(new java.awt.Font("Rockwell", 0, 18)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(255, 255, 255));
        jLabel4.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel4.setText("Wind");

        lblWindSpeed.setFont(new java.awt.Font("Rockwell", 0, 12)); // NOI18N
        lblWindSpeed.setForeground(new java.awt.Color(255, 255, 255));
        lblWindSpeed.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblWindSpeed.setText("------");

        lblWindDirection.setFont(new java.awt.Font("Rockwell", 0, 12)); // NOI18N
        lblWindDirection.setForeground(new java.awt.Color(255, 255, 255));
        lblWindDirection.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblWindDirection.setText("----");

        lblPressure.setFont(new java.awt.Font("Rockwell", 0, 12)); // NOI18N
        lblPressure.setForeground(new java.awt.Color(255, 255, 255));
        lblPressure.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblPressure.setText("-----");

        jLabel3.setFont(new java.awt.Font("Rockwell", 0, 18)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel3.setText("Pressure");

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("DỰ BÁO THỜI TIẾT ");

        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setText("Thành phố");

        jLabel6.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(255, 255, 255));
        jLabel6.setText("Chọn thời gian");

        jLabel7.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(255, 255, 255));
        jLabel7.setText("Thành phố");

        dateList.setModel(new javax.swing.AbstractListModel<String>() {
            String[] strings = { "Item 1", "Item 2", "Item 3", "Item 4", "Item 5" };
            public int getSize() { return strings.length; }
            public String getElementAt(int i) { return strings[i]; }
        });
        dateList.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                dateListMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(dateList);

        lblDongHo.setBackground(new java.awt.Color(255, 255, 255));
        lblDongHo.setFont(new java.awt.Font("Segoe UI", 1, 36)); // NOI18N
        lblDongHo.setForeground(new java.awt.Color(255, 255, 255));
        lblDongHo.setText("00:00:00 AM");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap(64, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel1)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel2)
                                .addGap(27, 27, 27)
                                .addComponent(txtCity, javax.swing.GroupLayout.PREFERRED_SIZE, 242, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(18, 18, 18)
                        .addComponent(lbIcon)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(searchBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 97, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(88, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(0, 23, Short.MAX_VALUE)
                                .addComponent(lblDongHo, javax.swing.GroupLayout.PREFERRED_SIZE, 473, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                            .addComponent(jLabel7)
                                            .addGap(78, 78, 78))
                                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                .addComponent(lblCityName, javax.swing.GroupLayout.PREFERRED_SIZE, 247, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                                    .addComponent(lblWeatherDescription, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                    .addComponent(lblWeatherMain, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 237, Short.MAX_VALUE)))
                                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                                                .addGap(62, 62, 62)
                                                .addComponent(LblTemperature))))
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                            .addComponent(lblWindSpeed, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                            .addComponent(jLabel4, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                            .addComponent(lblWindDirection, javax.swing.GroupLayout.PREFERRED_SIZE, 99, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addGap(29, 29, 29)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 109, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(lblPressure, javax.swing.GroupLayout.PREFERRED_SIZE, 101, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addGap(47, 47, 47)))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel6)
                                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 178, javax.swing.GroupLayout.PREFERRED_SIZE))))
                        .addContainerGap(40, Short.MAX_VALUE))))
            .addGroup(layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(jLabel5)
                .addGap(451, 451, 451))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(60, 60, 60)
                        .addComponent(lbIcon))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(12, 12, 12)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(txtCity, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(searchBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel2)))
                            .addComponent(jLabel5))))
                .addGap(40, 40, 40)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7)
                    .addComponent(jLabel6))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 293, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(lblCityName, javax.swing.GroupLayout.PREFERRED_SIZE, 58, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(LblTemperature, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(7, 7, 7)
                        .addComponent(lblWeatherMain, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(lblWeatherDescription)
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(lblPressure))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel4)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(lblWindSpeed)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lblWindDirection)))
                .addGap(49, 49, 49)
                .addComponent(lblDongHo)
                .addContainerGap(73, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void txtCityActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtCityActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtCityActionPerformed

    private void searchBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_searchBtnActionPerformed
        // TODO add your handling code here:
        getWeatherData();
    }//GEN-LAST:event_searchBtnActionPerformed

    private void dateListMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_dateListMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_dateListMouseClicked

    @Override
    protected void paintComponent(Graphics grphcs) {
        Graphics2D g2 = (Graphics2D) grphcs;
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        GradientPaint g = new GradientPaint(0, 0, color1, 0, getHeight(), color2);
        g2.setPaint(g);
        g2.fillRoundRect(0, 0, getWidth(), getHeight(), 15, 15);
        g2.setColor(new Color(255, 255, 255, 50));
        g2.fillOval(getWidth() - (getHeight() / 2), 10, getHeight(), getHeight());
        g2.fillOval(getWidth() - (getHeight() / 2) - 20, getHeight() / 2 + 20, getHeight(), getHeight());
        super.paintComponent(grphcs);
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel LblTemperature;
    private javax.swing.JList<String> dateList;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JLabel lbIcon;
    private javax.swing.JLabel lblCityName;
    private javax.swing.JLabel lblDongHo;
    private javax.swing.JLabel lblPressure;
    private javax.swing.JLabel lblWeatherDescription;
    private javax.swing.JLabel lblWeatherMain;
    private javax.swing.JLabel lblWindDirection;
    private javax.swing.JLabel lblWindSpeed;
    private javax.swing.JButton searchBtn;
    private javax.swing.JTextField txtCity;
    // End of variables declaration//GEN-END:variables
}
