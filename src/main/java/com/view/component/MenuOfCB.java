package com.view.component;

import SingletonClass.NV_singleton;
import com.view.event.EventMenuSelected;
import com.view.model.Model_Menu;
import java.awt.Color;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.RenderingHints;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.io.File;
import java.sql.Date;
import java.text.SimpleDateFormat;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import model.NhanVien;

public class MenuOfCB extends javax.swing.JPanel {

    private EventMenuSelected event;
    private String dir;
    private String maNV;
    private String tenNV;
    private String capBac;
    private String ngay;

    public void addEventMenuSelected(EventMenuSelected event) {
        this.event = event;
        listMenu1.addEventMenuSelected(event);
    }

    public MenuOfCB() {

        String path = "src\\main\\java\\com\\view\\icon\\logoTeamResizedNoBac.png";
        File file = new File(path);
        String absolutePath = file.getAbsolutePath();
        dir = absolutePath;
        initComponents();
        setOpaque(false);
        listMenu1.setOpaque(false);
        init();
    }

    private void init() {

        listMenu1.addItem(new Model_Menu("2", "Thông báo", Model_Menu.MenuType.MENU));
        listMenu1.addItem(new Model_Menu("3", "Bán hàng", Model_Menu.MenuType.MENU));
        listMenu1.addItem(new Model_Menu("4", "QL Giao ca", Model_Menu.MenuType.MENU));
        listMenu1.addItem(new Model_Menu("5", "QL Menu", Model_Menu.MenuType.MENU));
        listMenu1.addItem(new Model_Menu("12", "Quản lý khuyến mại", Model_Menu.MenuType.MENU));
        listMenu1.addItem(new Model_Menu("13", "QL Hóa Đơn", Model_Menu.MenuType.MENU));
        listMenu1.addItem(new Model_Menu("1", "Thống kê", Model_Menu.MenuType.MENU));
        listMenu1.addItem(new Model_Menu("8", "Quản lý nhân viên", Model_Menu.MenuType.MENU));
        listMenu1.addItem(new Model_Menu("8", "Tạo tài khoản", Model_Menu.MenuType.MENU));
        listMenu1.addItem(new Model_Menu("9", "Chấm công", Model_Menu.MenuType.MENU));
        listMenu1.addItem(new Model_Menu("12", "Tính lương", Model_Menu.MenuType.MENU));
        listMenu1.addItem(new Model_Menu("9", "Quản lý tin tức", Model_Menu.MenuType.MENU));
        listMenu1.addItem(new Model_Menu("9", "Quản lý nhập kho", Model_Menu.MenuType.MENU));
        listMenu1.addItem(new Model_Menu("9", "Quản lý Loại Đồ Uống", Model_Menu.MenuType.MENU));
        listMenu1.addItem(new Model_Menu("9", "Quản lý Cấp Bậc", Model_Menu.MenuType.MENU));
        listMenu1.addItem(new Model_Menu("9", "Quản lý Bàn", Model_Menu.MenuType.MENU));
        listMenu1.addItem(new Model_Menu("11", "Đăng xuất", Model_Menu.MenuType.MENU));
        listMenu1.addItem(new Model_Menu("", "\n ", Model_Menu.MenuType.TITLE));
        listMenu1.addItem(new Model_Menu("", "\n ", Model_Menu.MenuType.TITLE));
        listMenu1.addItem(new Model_Menu("", "\n ", Model_Menu.MenuType.TITLE));
        listMenu1.addItem(new Model_Menu("", "\n ", Model_Menu.MenuType.TITLE));
        listMenu1.addItem(new Model_Menu("", "\n ", Model_Menu.MenuType.TITLE));
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
        java.util.Date date = new java.util.Date();

        try {
            maNV = NV_singleton.getInstance().nv.getMa();
            capBac = NV_singleton.getInstance().nv.getCapBac().getTenCB();
            tenNV = NV_singleton.getInstance().nv.getTen();
            ngay = formatter.format(date);
        } catch (Exception e) {
            e.printStackTrace();
        }

        listMenu1.addItem(new Model_Menu("", "#Mã NV:  " + maNV, Model_Menu.MenuType.TITLE));
        listMenu1.addItem(new Model_Menu("", "#Cấp bậc:  " + capBac, Model_Menu.MenuType.TITLE));
        listMenu1.addItem(new Model_Menu("", "#Tên NV:  " + tenNV, Model_Menu.MenuType.TITLE));
        listMenu1.addItem(new Model_Menu("", "#Ngày:  " + ngay, Model_Menu.MenuType.TITLE));
//        listMenu1.addItem(new Model_Menu("11", "QL somethingelse", Model_Menu.MenuType.MENU));
//        listMenu1.addItem(new Model_Menu("12", "QL somethingelse", Model_Menu.MenuType.MENU));
//        listMenu1.addItem(new Model_Menu("13", "QL somethingelse", Model_Menu.MenuType.MENU));
//        listMenu1.addItem(new Model_Menu("14", "QL somethingelse", Model_Menu.MenuType.MENU));
//        listMenu1.addItem(new Model_Menu("15", "Tạo tài khoản", Model_Menu.MenuType.MENU));
//        listMenu1.addItem(new Model_Menu("16", "Đăng xuất", Model_Menu.MenuType.MENU));
//        listMenu1.addItem(new Model_Menu("17", "Đăng xuất ", Model_Menu.MenuType.EMPTY));
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        panelMoving = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        listMenu1 = new com.view.swing.ListMenu<>();

        panelMoving.setOpaque(false);

        jLabel1.setFont(new java.awt.Font("sansserif", 1, 17)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        ImageIcon imgIcon  = new javax.swing.ImageIcon(dir);
        Image image = imgIcon.getImage(); // transform it
        Image newimg = image.getScaledInstance(150, 46, java.awt.Image.SCALE_SMOOTH); // scale it the smooth way
        ImageIcon imageIcon = new ImageIcon(newimg);
        jLabel1.setIcon(imageIcon);

        javax.swing.GroupLayout panelMovingLayout = new javax.swing.GroupLayout(panelMoving);
        panelMoving.setLayout(panelMovingLayout);
        panelMovingLayout.setHorizontalGroup(
            panelMovingLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelMovingLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, 191, Short.MAX_VALUE)
                .addContainerGap())
        );
        panelMovingLayout.setVerticalGroup(
            panelMovingLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelMovingLayout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addComponent(jLabel1)
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(listMenu1, javax.swing.GroupLayout.DEFAULT_SIZE, 215, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(panelMoving, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(panelMoving, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(15, 15, 15)
                .addComponent(listMenu1, javax.swing.GroupLayout.DEFAULT_SIZE, 464, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    @Override
    protected void paintChildren(Graphics grphcs) {
        Graphics2D g2 = (Graphics2D) grphcs;
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        GradientPaint g = new GradientPaint(0, 0, Color.decode("#1CB5E0"), 0, getHeight(), Color.decode("#000046"));
        g2.setPaint(g);
        g2.fillRoundRect(0, 0, getWidth(), getHeight(), 15, 15);
        g2.fillRect(getWidth() - 20, 0, getWidth(), getHeight());
        super.paintChildren(grphcs);
    }

    private int x;
    private int y;

    public void initMoving(JFrame fram) {
        panelMoving.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent me) {
                x = me.getX();
                y = me.getY();
            }

        });
        panelMoving.addMouseMotionListener(new MouseMotionAdapter() {
            @Override
            public void mouseDragged(MouseEvent me) {
                fram.setLocation(me.getXOnScreen() - x, me.getYOnScreen() - y);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel1;
    private com.view.swing.ListMenu<String> listMenu1;
    private javax.swing.JPanel panelMoving;
    // End of variables declaration//GEN-END:variables
}
