package service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
//import java.sql.*;
import model.GiaoCa;
import repository.DAO_GiaoCa;

/**
 *
 * @author LENOVO T560
 */
public class GiaoCaService {

    private DAO_GiaoCa giaoCaRepository;

    public GiaoCaService() {
        giaoCaRepository = new DAO_GiaoCa();
    }

    public ArrayList<GiaoCa> selectALL() {
        return giaoCaRepository.selectALL();
    }

    public ArrayList<GiaoCa> selectAllByDate(String tuNgay, String denNgay) {
        return giaoCaRepository.selectAllByDate(tuNgay, denNgay);
    }

    public void delete(String id) {
        giaoCaRepository.delete(id);
    }

    public void update(String maGiaoCa, GiaoCa giaoCa) {
        giaoCaRepository.update(maGiaoCa, giaoCa);
    }

    public void save(GiaoCa giaoCa) {
        giaoCaRepository.save(giaoCa);
    }

    public BigDecimal getTongDoanhThu(Date ngayTao, String caLamViec) {
        return giaoCaRepository.getTongDoanhThu(ngayTao, caLamViec);
    }

}
