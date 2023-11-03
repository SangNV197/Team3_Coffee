/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package com.view.form_Template;

import DoUong_HoaDon_ThongKe_Model.BieuDoThongKe;
import DoUong_HoaDon_ThongKe_Model.LichSuHoaDon;
import DoUong_HoaDon_ThongKe_Repository.DAO_ThongKe;
import DoUong_HoaDon_ThongKe_Service.LichSuHoaDonService;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.Date;
import java.util.ArrayList;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.CreationHelper;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.DefaultCategoryDataset;

/**
 *
 * @author Sang
 */
public class Form_ThongKe extends javax.swing.JPanel {

    private LichSuHoaDonService lichSuHoaDonService = new LichSuHoaDonService();
    private DefaultTableModel model = new DefaultTableModel();
    private DAO_ThongKe dAO_ThongKe = new DAO_ThongKe();
    ArrayList<LichSuHoaDon> lstLichSuHoaDon = new ArrayList<>();
    ArrayList<BieuDoThongKe> lstBieuDoThongKe = new ArrayList<>();

    public Form_ThongKe() {
        initComponents();
        loadData();
        loadTongDoanhThu();
        loadTongHoaDon();
        loadTongSanPham();
        loadBieuDo();
    }

    public void loadTongDoanhThu() {
        long doanhThu = dAO_ThongKe.getTongDoanhThu();
        lblTongDoanhThu.setText(String.valueOf(doanhThu) + "đ");
    }

    public void loadTongHoaDon() {
        int tongHHoaDon = dAO_ThongKe.getTongHoaDon();
        lblTongHoaDon.setText(String.valueOf(tongHHoaDon));
    }

    public void loadTongSanPham() {
        int tongSanPham = dAO_ThongKe.getTongSanPham();
        lblTongSanPham.setText(String.valueOf(tongSanPham));
    }

    public void loadTongDoanhThuTheoNgay(Date d1, Date d2) {
        long doanhThu1 = dAO_ThongKe.getTongDonhThuTheoNgayChon(d1, d2);
        lblTongDoanhThu.setText(String.valueOf(doanhThu1) + "đ");
    }

    public void loadTongHoaDonTheoNgay(Date d1, Date d2) {
        int tongHHoaDon1 = dAO_ThongKe.getTongHoaDonTheoNgayChon(d1, d2);
        lblTongHoaDon.setText(String.valueOf(tongHHoaDon1));
    }

    public void loadTongSanPhamTheoNgay(Date d1,Date d2) {
        int tongSanPham1 = dAO_ThongKe.getTongSanPhamTheoNgayChon(d1, d2);
        lblTongSanPham.setText(String.valueOf(tongSanPham1));
    }

    public void loadBieuDo() {
        model = (DefaultTableModel) tblBieuDo.getModel();
        model.setRowCount(0);
        lstBieuDoThongKe = dAO_ThongKe.getBieuDo();
        for (BieuDoThongKe bieuDoThongKe : lstBieuDoThongKe) {
            model.addRow(new Object[]{
                bieuDoThongKe.getTenDoUong(),
                bieuDoThongKe.getSoLuong()
            });
        }
    }

    public void loadBieuDoTheoNgay(Date d1, Date d2) {
        model = (DefaultTableModel) tblBieuDo.getModel();
        model.setRowCount(0);
        lstBieuDoThongKe = dAO_ThongKe.getBieuDoTheoNgay(d1, d2);
        for (BieuDoThongKe bieuDoThongKe : lstBieuDoThongKe) {
            model.addRow(new Object[]{
                bieuDoThongKe.getTenDoUong(),
                bieuDoThongKe.getSoLuong()
            });
        }
    }

    public void bieuDo() {
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();
        lstBieuDoThongKe = dAO_ThongKe.getBieuDo();
        for (BieuDoThongKe bieuDoThongKe : lstBieuDoThongKe) {
            dataset.addValue(bieuDoThongKe.getSoLuong(), "", bieuDoThongKe.getTenDoUong());
        }
        JFreeChart barChart = ChartFactory.createBarChart(
                "BIỂU ĐỒ SẢN PHẨM",
                "Tên sản phẩm", "Số lượng",
                dataset, PlotOrientation.VERTICAL, false, false, false);

        ChartPanel chartPanel = new ChartPanel(barChart);
        chartPanel.setPreferredSize(new java.awt.Dimension(560, 367));
        JFrame frame = new JFrame();
        frame.add(chartPanel);
        frame.setTitle("Biểu đồ");
        frame.setSize(700, 500);
        frame.setLocationRelativeTo(null);
        frame.setResizable(false);
        frame.setVisible(true);
    }
    
    public void bieuDoTheoNgay(Date d1, Date d2) {
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();
        lstBieuDoThongKe = dAO_ThongKe.getBieuDoTheoNgay(d1, d2);
        for (BieuDoThongKe bieuDoThongKe : lstBieuDoThongKe) {
            dataset.addValue(bieuDoThongKe.getSoLuong(), "", bieuDoThongKe.getTenDoUong());
        }
        JFreeChart barChart = ChartFactory.createBarChart(
                "BIỂU ĐỒ SẢN PHẨM",
                "Tên sản phẩm", "Số lượng",
                dataset, PlotOrientation.VERTICAL, false, false, false);

        ChartPanel chartPanel = new ChartPanel(barChart);
        chartPanel.setPreferredSize(new java.awt.Dimension(560, 367));
        JFrame frame = new JFrame();
        frame.add(chartPanel);
        frame.setTitle("Biểu đồ");
        frame.setSize(700, 500);
        frame.setLocationRelativeTo(null);
        frame.setResizable(false);
        frame.setVisible(true);
    }

    public void loadData() {
        try {
            model = (DefaultTableModel) tblLichSuHoaDon.getModel();
            model.setRowCount(0);
            lstLichSuHoaDon = lichSuHoaDonService.getAll();
            for (LichSuHoaDon lichSuHoaDon : lstLichSuHoaDon) {
                model.addRow(new Object[]{
                    lichSuHoaDon.getMaHoaDon(),
                    lichSuHoaDon.getTenNhanVien(),
                    lichSuHoaDon.getTimeTao(),
                    lichSuHoaDon.getTimeThanhToan(),
                    lichSuHoaDon.getSoLuong(),
                    lichSuHoaDon.getTongTienHoaDon() + " đ",
                    lichSuHoaDon.getChietKhau() + " %",
                    lichSuHoaDon.tienThucNhan() + " đ",
                    lichSuHoaDon.hienThiTrangThai(),});
                System.out.println(lichSuHoaDon.getMaHoaDon());
            }
            System.out.println();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // Load dữ liệu theo thời gian truyền vào
    public void loadDataTheoTime(Date d1, Date d2) {
        try {
            model = (DefaultTableModel) tblLichSuHoaDon.getModel();
            model.setRowCount(0);
            lstLichSuHoaDon = lichSuHoaDonService.getByTime(d1, d2);
            for (LichSuHoaDon lichSuHoaDon : lstLichSuHoaDon) {
                model.addRow(new Object[]{
                    lichSuHoaDon.getMaHoaDon(),
                    lichSuHoaDon.getTenNhanVien(),
                    lichSuHoaDon.getTimeTao(),
                    lichSuHoaDon.getTimeThanhToan(),
                    lichSuHoaDon.getSoLuong(),
                    lichSuHoaDon.getTongTienHoaDon() + " đ",
                    lichSuHoaDon.getChietKhau() + " %",
                    lichSuHoaDon.tienThucNhan() + " đ",
                    lichSuHoaDon.hienThiTrangThai()
                });
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void xuatFileExcel() throws FileNotFoundException, IOException {
        System.out.println(lstLichSuHoaDon);
        XSSFWorkbook workbook = new XSSFWorkbook();
        XSSFSheet sheet = workbook.createSheet("Danh sách hóa đơn");

        //format date 
        CellStyle cellStyle = workbook.createCellStyle();
        CreationHelper createHelper = workbook.getCreationHelper();
        cellStyle.setDataFormat(
                createHelper.createDataFormat().getFormat("m/d/yy"));

        int rowCount = 0;
        //header
        Object[] header = {"Mã hóa đơn", "Tên nhân viên", "Thời gian tạo", "Thời gian thanh toán", "Tổng số lượng", "Tiền hóa đơn", "Khuyến mại", "Tiền nhận về", "Trạng thái"};
        Row headerRow = sheet.createRow(0);

        Cell headerCell0 = headerRow.createCell(0);
        headerCell0.setCellValue((String) header[0]);

        Cell headerCell1 = headerRow.createCell(1);
        headerCell1.setCellValue((String) header[1]);

        Cell headerCell2 = headerRow.createCell(2);
        headerCell2.setCellValue((String) header[2]);

        Cell headerCell3 = headerRow.createCell(3);
        headerCell3.setCellValue((String) header[3]);

        Cell headerCell4 = headerRow.createCell(4);
        headerCell4.setCellValue((String) header[4]);

        Cell headerCell5 = headerRow.createCell(5);
        headerCell5.setCellValue((String) header[5]);

        Cell headerCell6 = headerRow.createCell(6);
        headerCell6.setCellValue((String) header[6]);

        Cell headerCell7 = headerRow.createCell(7);
        headerCell7.setCellValue((String) header[7]);

        Cell headerCell8 = headerRow.createCell(8);
        headerCell8.setCellValue((String) header[8]);

        //
        for (LichSuHoaDon lichSuHoaDon : lstLichSuHoaDon) {
            //create a row
            Row row = sheet.createRow(++rowCount);
            int columnCount = -1;
            // write a row
            Object[] obj = {lichSuHoaDon.getMaHoaDon(), lichSuHoaDon.getTenNhanVien(), lichSuHoaDon.getTimeTao(), lichSuHoaDon.getTimeThanhToan(), lichSuHoaDon.getSoLuong(),
                lichSuHoaDon.getTongTienHoaDon(), lichSuHoaDon.getChietKhau(), lichSuHoaDon.tienThucNhan(), lichSuHoaDon.getTrangThai()};
            for (int colNum = 0; colNum < obj.length; colNum++) {
                System.out.println(rowCount);
                Cell cell = row.createCell(++columnCount);
                if (obj[colNum] instanceof String) {
                    cell.setCellValue((String) obj[colNum]);
                } else if (obj[colNum] instanceof Integer) {
                    cell.setCellValue((Integer) obj[colNum]);
                } else if (obj[colNum] instanceof Integer) {
                    cell.setCellValue((Integer) obj[colNum]);
                } else if (obj[colNum] instanceof Double) {
                    cell.setCellValue((Double) obj[colNum]);
                } else if (obj[colNum] instanceof Date) {
                    cell.setCellValue((Date) obj[colNum]);
                    cell.setCellStyle(cellStyle);
                }
            }
        }

        try (FileOutputStream outputStream = new FileOutputStream("DSHoaDon.xlsx")) {
            workbook.write(outputStream);
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jTextField2 = new javax.swing.JTextField();
        jScrollPane3 = new javax.swing.JScrollPane();
        jTextPane1 = new javax.swing.JTextPane();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jPanel1 = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        lblTongSanPham = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        lblTongDoanhThu = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        lblTongHoaDon = new javax.swing.JLabel();
        jPanel4 = new javax.swing.JPanel();
        dateNgayBatDau = new com.toedter.calendar.JDateChooser();
        dateNgayKetThuc = new com.toedter.calendar.JDateChooser();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        btnBieuDo = new javax.swing.JButton();
        tbnXuatFileExcel = new javax.swing.JButton();
        btnTimKiemTheoNgay = new javax.swing.JButton();
        btnLoad = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblLichSuHoaDon = new javax.swing.JTable();
        jScrollPane4 = new javax.swing.JScrollPane();
        tblBieuDo = new javax.swing.JTable();

        jLabel1.setText("jLabel1");

        jTextField2.setText("jTextField2");

        jScrollPane3.setViewportView(jTextPane1);

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane2.setViewportView(jTable1);

        setBackground(new java.awt.Color(255, 255, 255));
        setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 153, 255)));

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jLabel4.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(0, 0, 0));
        jLabel4.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel4.setText("Tổng sản phẩm");

        lblTongSanPham.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        lblTongSanPham.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblTongSanPham.setText("......");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, 214, Short.MAX_VALUE)
            .addComponent(lblTongSanPham, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addComponent(jLabel4)
                .addGap(18, 18, 18)
                .addComponent(lblTongSanPham, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));
        jPanel2.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jLabel2.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(0, 0, 0));
        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel2.setText("Tổng doanh thu");

        lblTongDoanhThu.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        lblTongDoanhThu.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblTongDoanhThu.setText("......");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, 209, Short.MAX_VALUE)
            .addComponent(lblTongDoanhThu, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addComponent(jLabel2)
                .addGap(18, 18, 18)
                .addComponent(lblTongDoanhThu, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(35, Short.MAX_VALUE))
        );

        jPanel3.setBackground(new java.awt.Color(255, 255, 255));
        jPanel3.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jLabel3.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(0, 0, 0));
        jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel3.setText("Tổng hóa đơn");

        lblTongHoaDon.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        lblTongHoaDon.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblTongHoaDon.setText("......");

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, 215, Short.MAX_VALUE)
            .addComponent(lblTongHoaDon, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addComponent(jLabel3)
                .addGap(18, 18, 18)
                .addComponent(lblTongHoaDon, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel4.setBackground(new java.awt.Color(255, 255, 255));
        jPanel4.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        dateNgayBatDau.setDateFormatString("dd-MM-yyyy");

        dateNgayKetThuc.setDateFormatString("dd-MM-yyyy");

        jLabel5.setText("Từ ngày:");

        jLabel6.setText("Đến ngày:");

        btnBieuDo.setText("Biểu đồ");
        btnBieuDo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBieuDoActionPerformed(evt);
            }
        });

        tbnXuatFileExcel.setText("Xuất file");
        tbnXuatFileExcel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tbnXuatFileExcelActionPerformed(evt);
            }
        });

        btnTimKiemTheoNgay.setText("Tìm kiếm");
        btnTimKiemTheoNgay.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnTimKiemTheoNgayActionPerformed(evt);
            }
        });

        btnLoad.setText("Load Data");
        btnLoad.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLoadActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel6)
                    .addComponent(jLabel5))
                .addGap(36, 36, 36)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(dateNgayKetThuc, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(dateNgayBatDau, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(btnTimKiemTheoNgay)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnLoad)
                .addGap(18, 18, 18)
                .addComponent(tbnXuatFileExcel)
                .addGap(18, 18, 18)
                .addComponent(btnBieuDo)
                .addGap(30, 30, 30))
        );

        jPanel4Layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {btnBieuDo, btnLoad, tbnXuatFileExcel});

        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGap(36, 36, 36)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(btnLoad)
                            .addComponent(tbnXuatFileExcel)
                            .addComponent(btnBieuDo)
                            .addComponent(btnTimKiemTheoNgay)))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGap(23, 23, 23)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel5)
                            .addComponent(dateNgayBatDau, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(12, 12, 12)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel6)
                            .addComponent(dateNgayKetThuc, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(26, Short.MAX_VALUE))
        );

        tblLichSuHoaDon.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null}
            },
            new String [] {
                "Mã hóa đơn", "Tên nhân viên", "TG tạo", "TG thanh toán", "Tổng số lượng", "Tiền hóa đơn", "Khuyến mại", "Thực nhận", "Trạng thái thanh toán"
            }
        ));
        tblLichSuHoaDon.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        tblLichSuHoaDon.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblLichSuHoaDonMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tblLichSuHoaDon);

        tblBieuDo.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null},
                {null, null},
                {null, null},
                {null, null}
            },
            new String [] {
                "Tên sản phẩm", "Số lượng"
            }
        ));
        jScrollPane4.setViewportView(tblBieuDo);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(28, 28, 28)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(20, 20, 20)
                        .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(154, 154, 154)
                        .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 139, Short.MAX_VALUE)
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(24, 24, 24))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 712, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 209, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(25, 25, 25))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(33, 33, 33)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(18, 18, 18)
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 354, Short.MAX_VALUE)
                    .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents

    private void tblLichSuHoaDonMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblLichSuHoaDonMouseClicked

    }//GEN-LAST:event_tblLichSuHoaDonMouseClicked

    private void tbnXuatFileExcelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tbnXuatFileExcelActionPerformed
        try {
            xuatFileExcel();
            JOptionPane.showMessageDialog(this, "Xuất file thành công !");
        } catch (Exception e) {
            JOptionPane.showConfirmDialog(this, "Không thể xuất file excel !");
            e.printStackTrace();
        }
    }//GEN-LAST:event_tbnXuatFileExcelActionPerformed

    private void btnTimKiemTheoNgayActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTimKiemTheoNgayActionPerformed
//        String d1 = ((JTextField) dateNgayBatDau.getDateEditor().getUiComponent()).getText();
//        String d2 = ((JTextField) dateNgayKetThuc.getDateEditor().getUiComponent()).getText();
        
        Date d1 = new Date(dateNgayBatDau.getDate().getTime());
        Date d2 = new Date(dateNgayKetThuc.getDate().getTime());
        
        loadDataTheoTime(d1, d2);
        loadTongDoanhThuTheoNgay(d1, d2);
        loadTongHoaDonTheoNgay(d1, d2);
        loadTongSanPhamTheoNgay(d1, d2);
        loadBieuDoTheoNgay(d1, d2);
//        bieuDoTheoNgay(d1, d2);
    }//GEN-LAST:event_btnTimKiemTheoNgayActionPerformed

    private void btnLoadActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLoadActionPerformed
        // TODO add your handling code here:
        loadData();
        loadTongDoanhThu();
        loadTongHoaDon();
        loadTongSanPham();
        loadBieuDo();
    }//GEN-LAST:event_btnLoadActionPerformed

    private void btnBieuDoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBieuDoActionPerformed
        // TODO add your handling code here:
//        java.awt.EventQueue.invokeLater(new Runnable() {
//            public void run() {
//                new BieuDo(tblBieuDo).setVisible(true);
//            }
//        });
        bieuDo();

    }//GEN-LAST:event_btnBieuDoActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnBieuDo;
    private javax.swing.JButton btnLoad;
    private javax.swing.JButton btnTimKiemTheoNgay;
    private com.toedter.calendar.JDateChooser dateNgayBatDau;
    private com.toedter.calendar.JDateChooser dateNgayKetThuc;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JTable jTable1;
    private javax.swing.JTextField jTextField2;
    private javax.swing.JTextPane jTextPane1;
    private javax.swing.JLabel lblTongDoanhThu;
    private javax.swing.JLabel lblTongHoaDon;
    private javax.swing.JLabel lblTongSanPham;
    private javax.swing.JTable tblBieuDo;
    private javax.swing.JTable tblLichSuHoaDon;
    private javax.swing.JButton tbnXuatFileExcel;
    // End of variables declaration//GEN-END:variables

}
