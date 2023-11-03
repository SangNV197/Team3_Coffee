SELECT*FROM dbo.ChiTietDoUong
ORDER BY IdLoaiDoUong;

SELECT*FROM dbo.HoaDon;

SELECT*FROM dbo.GiamGia

SELECT*FROM dbo.HoaDon;

INSERT INTO dbo.HoaDon(IdBan,IdKH,IdNV,Ma,NgayThanhToan,TinhTrangThanhToan,TrangThaiPhaChe,MaGiamGia)VALUES(NULL,NULL,NULL,NULL,NULL,0,0,NULL)


INSERT INTO dbo.HoaDonChiTiet(IdHoaDon,IdChiTietDoUong,SoLuong)VALUES( NULL,NULL,NULL)

SELECT*FROM dbo.HoaDonChiTiet;

EXEC sp_help 'HoaDonChiTiet'


INSERT INTO dbo.Ban(IdBan)VALUES(N'Bàn 1')

SELECT*FROM dbo.Ban;

INSERT INTO dbo.KhachHang(Ma,Ten,TenDem,Ho,NgaySinh,Sdt,DiaChi,NgayTao)VALUES(?,?,?,?,?,?,?,?)


INSERT INTO dbo.NhanVien(Ma,Ten,TenDem,Ho,GioiTinh,NgaySinh,DiaChi,Sdt,TaiKhoan,MatKhau,IdCB,TrangThai,HinhAnh,NgayTao)
VALUES( NULL,DEFAULT,DEFAULT,DEFAULT, DEFAULT,DEFAULT,DEFAULT,DEFAULT,NULL,DEFAULT,NULL,DEFAULT,NULL,DEFAULT)

INSERT INTO dbo.GiamGia(MaGiamGia,PhamTram,MoTa,NgayTao)VALUES(?,?,?,?)

INSERT INTO dbo.CapBac(Ma,Ten,LuongPartime)VALUES(?,?,?)


Alter Table dbo.HoaDon
Add NumOrder Int Identity(1, 1)

Alter Table dbo.HoaDon
Add Ma AS ('HD'+CONVERT([varchar](10),[NUMORDER])) PERSISTED NOT NULL

SELECT*FROM dbo.HoaDon;

INSERT INTO dbo.Ban
(
    IdBan,
    NgayTao
)
VALUES
(   N'Bàn 6',    -- IdBan - nvarchar(20)
    DEFAULT -- NgayTao - varchar(20)
    )

SELECT*FROM dbo.Ban

EXEC sp_help 'HoaDon'

SELECT*FROM dbo.NhanVien;

DELETE FROM ban

INSERT INTO dbo.Ban
(IdBan,Ten)
VALUES(5,N'Bàn 5')

SELECT*FROM dbo.Ban;

DELETE FROM dbo.HoaDon

SELECT*FROM dbo.HoaDon;

SELECT GETDATE()

SELECT  MONTH(GETDATE());

SELECT FORMAT(GETDATE(),'yyyy-MM-dd')


SELECT FORMAT(GETDATE(),'HH:mm')

SELECT*FROM dbo.HoaDon;

SELECT*FROM dbo.HoaDonChiTiet;

select  HoaDon.Ma, NhanVien.Ten, convert(varchar, hoadon.NgayTao, 105) as 'NgayTao',convert(varchar, hoadon.NgayTao, 105) as 'NgayTao', 
            (sum(SoLuong)) as 'SoLuongSP', sum(ChiTietDoUong.GiaBan * HoaDonChiTiet.SoLuong) as 'TongTien', GiamGia.PhamTram, HoaDon.Stt  from HoaDonChiTiet
            join ChiTietDoUong on HoaDonChiTiet.IdChiTietDoUong = ChiTietDoUong.Id
            join HoaDon on HoaDonChiTiet.IdHoaDon = HoaDon.Id
            join NhanVien on HoaDon.IdNV = NhanVien.Id
            join GiamGia on GiamGia.MaGiamGia = HoaDon.MaGiamGia
            group by HoaDon.Ma, NhanVien.Ten, HoaDon.NgayTao, HoaDon.ThoiGian, GiamGia.PhamTram, HoaDon.Stt


SELECT*FROM dbo.NhanVien;

SELECT*FROM dbo.GiamGia

INSERT INTO dbo.GiamGia
(MaGiamGia,TenKhuyenMai,LoaiKhuyenMai,GiaTri,NgayKetThuc,TrangThai)
VALUES('mh3753',N'Quốc Tế Thiếu nhi','%',5,'2023-2-05',N'DangHoatDong')


select  HoaDon.Ma, NhanVien.Ten, convert(varchar, hoadon.NgayTao, 105) as 'NgayTao',convert(varchar, hoadon.ThoiGian, 105) as 'ThoiGian',
                         (sum(SoLuong)) as 'SoLuongSP', sum(ChiTietDoUong.GiaBan * HoaDonChiTiet.SoLuong) as 'TongTien',CASE WHEN dbo.HoaDon.MaGiamGia IS NULL THEN 0 ELSE dbo.GiamGia.GiaTri END AS giatri,
						 HoaDon.TinhTrangThanhToan, sum(ChiTietDoUong.GiaBan * HoaDonChiTiet.SoLuong - 0*(ChiTietDoUong.GiaBan * HoaDonChiTiet.SoLuong)*(CASE WHEN dbo.HoaDon.MaGiamGia IS NULL THEN 0 ELSE dbo.GiamGia.GiaTri END )/100) as 'ThucNhan' from HoaDonChiTiet
                         LEFT JOIN ChiTietDoUong on HoaDonChiTiet.IdChiTietDoUong = ChiTietDoUong.Id
                         LEFT JOIN HoaDon on HoaDonChiTiet.IdHoaDon = HoaDon.Id
                         LEFT JOIN NhanVien on HoaDon.IdNV = NhanVien.Id
                         LEFT JOIN GiamGia on GiamGia.MaGiamGia = HoaDon.MaGiamGia
						 WHERE TinhTrangThanhToan = 1
                         group by HoaDon.Ma,dbo.HoaDon.MaGiamGia, NhanVien.Ten, HoaDon.NgayTao, HoaDon.ThoiGian, GiamGia.GiaTri, HoaDon.Stt,dbo.HoaDon.TinhTrangThanhToan,dbo.HoaDon.SoTienNhanVao
						 

SELECT *FROM dbo.HoaDon
SELECT *FROM dbo.NhanVien 


UPDATE dbo.HoaDon
SET IdNV = 'B9994504-EE0C-47E3-96EB-1EFD01397241'

UPDATE dbo.HoaDon
SET MaGiamGia = 'mh3751';

SELECT*FROM dbo.ChiTietDoUong

SELECT*FROM dbo.LoaiDoUong
alter table dbo.NhanVien add constraint pinky unique(TaiKhoan);

SELECT*FROM nhanvien

select * from NhanVien where TaiKhoan ='abc' and MatKhau = '123456'

SELECT*FROM dbo.CapBac;

UPDATE dbo.HoaDon
SET IdNV='B9994504-EE0C-47E3-96EB-1EFD01397241';

SELECT*FROM dbo.GiamGia;

Alter TABLE gia 
Add NumOrder Int Identity(1, 1)

Alter Table dbo.HoaDon
Add Ma AS ('HD'+CONVERT([varchar](10),[NUMORDER])) PERSISTED NOT NULL


SELECT CASE
	   WHEN dbo.HoaDon.MaGiamGia IS NULL THEN 0 
	   ELSE dbo.GiamGia.GiaTri
	   END AS giatri
	   FROM dbo.HoaDon
LEFT   JOIN dbo.GiamGia
ON dbo.HoaDon.MaGiamGia = dbo.GiamGia.MaGiamGia


CASE WHEN dbo.HoaDon.MaGiamGia IS NULL THEN 0 ELSE dbo.GiamGia.GiaTri END AS giatri


SELECT *FROM dbo.HoaDon ORDER BY NumOrder DESC;


SELECT MAX(IdBan) FROM ban

SELECT*FROM dbo.HoaDon

SELECT*FROM dbo.Ban

SELECT *FROM HOADON

SELECT TOP 1*FROM dbo.HoaDonChiTiet ORDER BY NUMORDER DESC ;

SELECT *FROM dbo.HoaDonChiTiet

ALTER TABLE dbo.HoaDonChiTiet
ADD NumOrder INT IDENTITY(1, 1)


DELETE FROM dbo.HoaDonChiTiet

SELECT*FROM hoadon

SELECT*FROM nhanvien

SELECT *FROM dbo.ChamCong


INSERT INTO ChamCong(IdNV,Ngay,GioVao,GioRa)
VALUES ('B9994504-EE0C-47E3-96EB-1EFD01397241','1999-02-08','00:28','00:29')


ALTER TABLE dbo.NhanVien
ADD NumOrder INT IDENTITY(1, 1)


ALTER TABLE dbo.NhanVien
Add MaNV AS ('NV'+CONVERT([varchar](10),[NUMORDER])) PERSISTED NOT NULL

SELECT*FROM dbo.GiaoCa

SELECT*FROM dbo.NhanVien

SELECT*FROM dbo.ChiTietDoUong;


Alter TABLE dbo.ChiTietDoUong
Add NumOrder Int Identity(1, 1)


SELECT*FROM dbo.ChiTietDoUong

UPDATE dbo.ChiTietDoUong 
SET Status=1;

SELECT * FROM [dbo].[ChiTietDoUong] WHERE Status=1 ORDER BY IdLoaiDoUong;


SELECT ngaytao FROM hoadon where  convert(varchar, hoadon.NgayTao, 105) between '01-07-2023' and '03-08-2023'

SELECT convert(varchar, hoadon.NgayTao, 105) AS abc  FROM dbo.HoaDon


select sum(HoaDonChiTiet.SoLuong) from HoaDonChiTiet 
               join HoaDon on HoaDon.Id = HoaDonChiTiet.IdHoaDon
               where convert(varchar, hoadon.ngaytao, 105) = (select convert(varchar,getDate(), 105))

SELECT*FROM dbo.CapBac
SELECT*FROM giaoca


