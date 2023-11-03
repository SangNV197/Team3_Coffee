/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package com.view.component;

import java.text.DecimalFormat;
import java.util.ArrayList;
import javax.json.JsonArray;
import javax.json.JsonObject;
import javax.json.JsonValue;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

/**
 *
 * @author 84374
 */
public class lstCityDialogFrame extends javax.swing.JFrame {

    /**
     * Creates new form lstCityDialogFrame
     */
    private JPanel localPanel;
    private JsonArray localCurWheather;
    private JsonObject localForeCastObj;
    private JsonArray localForeCast;
    private ArrayList<String> list_selection;
    private JLabel local_lblCity;
    private JLabel local_lblPressure;
    private JLabel local_LblTemperature;
    private JLabel local_lblWeatherDescription;
    private JLabel local_lblWeatherMain;
    private JLabel local_lblWindDirection;
    private JLabel local_lblWindSpeed;
    private JList local_dateList;

    public lstCityDialogFrame(JLabel lblCity, JLabel lblPressure, JLabel LblTemperature, JLabel lblWeatherDescription, JLabel lblWeatherMain, JLabel lblWindDirection, JLabel lblWindSpeed, JList dateList, JsonArray curWheather, JsonArray foreCast) {
        initComponents();
        this.pack();
        this.setLocationRelativeTo(null);
        localCurWheather = curWheather;
        localForeCast = foreCast;
        local_lblCity = lblCity;
        local_lblPressure = lblPressure;
        local_LblTemperature = LblTemperature;
        local_lblWeatherDescription = lblWeatherDescription;
        local_lblWeatherMain = lblWeatherMain;
        local_lblWindDirection = lblWindDirection;
        local_lblWindSpeed = lblWindSpeed;
        local_dateList = dateList;
        loadToJdialog();
        dateList.addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent lse) {
                int count = dateList.getSelectedIndex();
                DecimalFormat d = new DecimalFormat(".#");
//                local_lblCity.setText(localForeCastObj.getString("name"));
                local_lblPressure.setText((d.format(localForeCastObj.getJsonArray("list").getJsonObject(getIdxDate()).getJsonObject("main").getJsonNumber("pressure").doubleValue())));
                local_LblTemperature.setText(d.format(localForeCastObj.getJsonArray("list").getJsonObject(getIdxDate()).getJsonObject("main").getJsonNumber("temp").doubleValue() - 273));
                local_lblWeatherDescription.setText(localForeCastObj.getJsonArray("list").getJsonObject(getIdxDate()).getJsonArray("weather").getJsonObject(0).getString("description"));
                local_lblWeatherMain.setText(localForeCastObj.getJsonArray("list").getJsonObject(getIdxDate()).getJsonArray("weather").getJsonObject(0).getString("main"));
                final String DEGREE = "\u00b0";
                if (localForeCastObj.getJsonArray("list").getJsonObject(getIdxDate()).getJsonObject("wind").getJsonNumber("deg") != null) {
                    local_lblWindDirection.setText(d.format(localForeCastObj.getJsonArray("list").getJsonObject(getIdxDate()).getJsonObject("wind").getJsonNumber("deg").doubleValue()) + DEGREE);
                } else {
                    local_lblWindDirection.setText("");
                }
                local_lblWindSpeed.setText(d.format(localForeCastObj.getJsonArray("list").getJsonObject(getIdxDate()).getJsonObject("wind").getJsonNumber("speed").doubleValue()) + " m/s");
            }
        });
    }

    public int getIdxDate() {
        for (int i = 0; i < localForeCastObj.asJsonObject().getJsonArray("list").size(); i++) {
            if (localForeCastObj.getJsonArray("list").getJsonObject(i).getString("dt_txt").equals(local_dateList.getSelectedValue())) {
                return i;
            }
        }
        return 0;
    }

    public void loadToJdialog() {
        ArrayList<String> li = new ArrayList<>();
        if (localCurWheather != null) {
            for (JsonValue el : localCurWheather) {
                String listval = el.asJsonObject().getString("name");
                listval += " (" + el.asJsonObject().getJsonObject("coord").getInt("lon");
                listval += ", " + el.asJsonObject().getJsonObject("coord").getInt("lat") + ")";
                li.add(listval);
            }
        }
        String[] strs = new String[li.size()];
        li.toArray(strs);
        jDialogCityList.setModel(new javax.swing.AbstractListModel<String>() {
            String[] strings = strs;

            public int getSize() {
                return strings.length;
            }

            public String getElementAt(int i) {
                return strings[i];
            }
        });
    }

    public void setParentFrame() {
        int count = jDialogCityList.getSelectedIndex();
        localForeCastObj = localForeCast.get(count).asJsonObject();
        System.out.println("test forecast");
        System.out.println(localForeCastObj.getJsonArray("list"));
        DecimalFormat d = new DecimalFormat(".#");
        local_lblCity.setText(localCurWheather.get(count).asJsonObject().getString("name"));
        local_lblPressure.setText((d.format(localCurWheather.get(count).asJsonObject().getJsonObject("main").getJsonNumber("pressure").doubleValue())));
        local_LblTemperature.setText(d.format(localCurWheather.get(count).asJsonObject().getJsonObject("main").getJsonNumber("temp").doubleValue() - 273));
        local_lblWeatherDescription.setText(localCurWheather.get(count).asJsonObject().getJsonArray("weather").getJsonObject(0).getString("description"));
        local_lblWeatherMain.setText(localCurWheather.get(count).asJsonObject().getJsonArray("weather").getJsonObject(0).getString("main"));
        final String DEGREE = "\u00b0";
        if (localCurWheather.get(count).asJsonObject().getJsonObject("wind").getJsonNumber("deg") != null) {
            local_lblWindDirection.setText(d.format(localCurWheather.get(count).asJsonObject().getJsonObject("wind").getJsonNumber("deg").doubleValue()) + DEGREE);
        } else {
            local_lblWindDirection.setText("");
        }
        local_lblWindSpeed.setText(d.format(localCurWheather.get(count).asJsonObject().getJsonObject("wind").getJsonNumber("speed").doubleValue()) + " m/s");
    }

    public void loadToJdialogParent() {
        int count = jDialogCityList.getSelectedIndex();
        ArrayList<String> li = new ArrayList<>();
        if (localForeCast.get(count).asJsonObject().getJsonArray("list") != null) {
            for (JsonValue el : localForeCast.get(count).asJsonObject().getJsonArray("list")) {
                li.add(el.asJsonObject().getString("dt_txt"));
            }
        }
        String[] strs = new String[li.size()];
        li.toArray(strs);

        local_dateList.setModel(new javax.swing.AbstractListModel<String>() {
            String[] strings = strs;

            public int getSize() {
                return strings.length;
            }

            public String getElementAt(int i) {
                return strings[i];
            }
        });
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        selectBtn = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        jDialogCityList = new javax.swing.JList<>();
        jLabel1 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        selectBtn.setFont(new java.awt.Font("Rockwell", 0, 14)); // NOI18N
        selectBtn.setText("Select");
        selectBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                selectBtnActionPerformed(evt);
            }
        });

        jDialogCityList.setBackground(new java.awt.Color(250, 250, 250));
        jDialogCityList.setFont(new java.awt.Font("Rockwell", 0, 12)); // NOI18N
        jDialogCityList.setModel(new javax.swing.AbstractListModel<String>() {
            String[] strings = { "Item 1", "Item 2", "Item 3", "Item 4", "Item 5" };
            public int getSize() { return strings.length; }
            public String getElementAt(int i) { return strings[i]; }
        });
        jDialogCityList.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jDialogCityListMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(jDialogCityList);

        jLabel1.setFont(new java.awt.Font("Rockwell", 0, 24)); // NOI18N
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("Select a City");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(103, 103, 103)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jScrollPane1)
                            .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, 183, Short.MAX_VALUE)))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(138, 138, 138)
                        .addComponent(selectBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 118, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(114, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(35, 35, 35)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 118, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(selectBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(38, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void selectBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_selectBtnActionPerformed
        // TODO add your handling code here:
        setParentFrame();
        loadToJdialogParent();
        this.dispose();
    }//GEN-LAST:event_selectBtnActionPerformed

    private void jDialogCityListMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jDialogCityListMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_jDialogCityListMouseClicked

    /**
     * @param args the command line arguments
     */

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JList<String> jDialogCityList;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JButton selectBtn;
    // End of variables declaration//GEN-END:variables
}
