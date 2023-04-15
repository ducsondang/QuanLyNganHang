/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ducson.baitaplon;

import com.ducson.cauhinh.CauHinh;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;

/**
 *
 * @author khach
 */
public class TaiKhoan {

    private static int dem = 1;
    private String soThuTu;
    private String hoTen;
    private String gioiTinh;
    Date ngaySinh;
    private String queQuan;
    private int CCCD;
    private String taiKhoan;
    private String matKhau;
    private String id;
    private TaiKhoanGuiTien taiKhoanGuiTien;
    private List<TaiKhoanKyHan> dsKyHan = new ArrayList<>();

    {
        if (getDem() >= 100 && getDem() < 999) {
            this.setSoThuTu("0" + this.getDem());
        }
        if (getDem() > 10 && getDem() < 100) {
            this.setSoThuTu("00" + this.getDem());
        } else {
            this.setSoThuTu("000" + this.getDem());
        }
        setDem(getDem() + 1);
    }

    public TaiKhoan(String id, String ten, String gioiTinh, String taiKhoan, String ngaySinh, String Que, int CCCD, double tien, String passwork, String ngay) throws ParseException {
        this.taiKhoanGuiTien = new TaiKhoanGuiTien();
        taiKhoanGuiTien.setTienGui(tien, 0);
        taiKhoanGuiTien.setLai(0.2);
        this.hoTen = ten;
        this.gioiTinh = gioiTinh;
        this.ngaySinh = CauHinh.f.parse(ngaySinh);
        this.CCCD = CCCD;
        this.queQuan = Que;
        this.id = id;
        this.matKhau = passwork;
        this.taiKhoan = taiKhoan;
        this.taiKhoanGuiTien.setNgayTao(CauHinh.f.parse(ngay));
    }

    public TaiKhoan() {
        this.taiKhoanGuiTien = new TaiKhoanGuiTien();
        taiKhoanGuiTien.setLai(0.2);
        CauHinh.sc.nextLine();
        System.out.print("Ho ten: ");
        this.setHoTen(CauHinh.sc.nextLine());
        System.out.print("Ngay sinh (dd/MM/yyyy): ");
        Date ngaySinh = new Date("1/1/1");
        String ngay = CauHinh.sc.nextLine();
        try {
            ngaySinh = CauHinh.f.parse(ngay);
        } catch (Exception ex) {
            // code xử lý ngoại lệ
        }
        this.setNgaySinh(ngaySinh);
        System.out.println("gioi Tinh: ");
        this.setGioiTinh(CauHinh.sc.nextLine());
        System.out.println("Que Quan: ");
        this.setQueQuan(CauHinh.sc.nextLine());
        System.out.print("tai khoan: ");
        this.setTaiKhoan(CauHinh.sc.next());
        System.out.print("CCCD: ");
        this.setCCCD(CauHinh.sc.nextInt());
        System.out.print("Tien Gui(VND): ");
        taiKhoanGuiTien.setTienGui(CauHinh.sc.nextInt(), 0);
        this.taiKhoanGuiTien.setNgayTao(new Date());
        this.setId((CauHinh.f.format(this.taiKhoanGuiTien.getNgayTao()) + this.soThuTu).replaceAll("/", ""));
        this.setMatKhau(Integer.toString((new Random()).nextInt(999999)));
    }

    public void hienThi() {
        System.out.println("=======================");
        System.out.printf("ID: %s\nHo va ten: %s\nNgay Sinh: %s\nTai Khoan: %s\nSo tien: %s\n", this.getId(), this.getHoTen(), CauHinh.f.format(this.getNgaySinh()), this.getTaiKhoan(), this.getTaiKhoanGuiTien().getTien());
    }

    public void moTaiKhoanKyHan() {
        TaiKhoanKyHan a = new TaiKhoanKyHan();
        this.dsKyHan.add(a);
    }

    public void hienThiTaiKhoanKyHan() {
        this.dsKyHan.forEach(a -> a.hienThi());
    }

    public void LuuTaiKhoan() throws IOException {
        File f = new File("src/main/resources/taikhoan.txt");
        FileWriter w = new FileWriter(f, true);
        try ( PrintWriter t = new PrintWriter(w)) {
            t.println(this.getId());
            t.println(this.getHoTen());
            t.println(this.getGioiTinh());
            t.println(this.getTaiKhoan());
            t.println(CauHinh.f.format(this.getNgaySinh()));
            t.println(this.getQueQuan());
            t.println(this.getCCCD());
            t.println(this.getTaiKhoanGuiTien().getTien());
            t.println(this.getMatKhau());
            t.println(CauHinh.f.format(this.taiKhoanGuiTien.getNgayTao()));
        }
    }

//    public double tinhLaiXuat() {
//        return this.tienGui / 100 * this.laiXuat / 12;
//    }
//
//    public void congLaiXuat() {
//        if (new Date().getTime() - this.ngayTao.getTime() == 30) {
//            this.tien = this.tinhLaiXuat() + this.tienGui;
//        }
//    }
    
    public void napTienVaoTKKyHan() {
        System.out.println("Nhap ID Tai Khoan co ky han");
        String strId = CauHinh.sc.next();
        int number = 0;
        try {
            number = Integer.parseInt(strId);
        } catch (Exception ex) {
            // code xử lý ngoại lệ
        }
        System.out.println("Nhap so tien");
        String strMoney = CauHinh.sc.next();
        double money = 0;
        try {
            money = Integer.parseInt(strMoney);
        } catch (Exception ex) {
            // code xử lý ngoại lệ
        }
        if(this.getTaiKhoanGuiTien().getTien()- money < 50000){
            System.out.println("Tai khoan chinh phai co tu 50000VND tro len sau khi chuyen vao tai khoan!\nvui long nhap lai!");
        }else{
            this.taiKhoanGuiTien.setTienGui(money, 2);
            this.dsKyHan.stream().filter(t -> t.getId().equals(number)).findFirst().empty();
        }
    }

    /**
     * @param tienGui the tienGui to set
     * @param a
     */
    /**
     * @return the dem
     */
    public static int getDem() {
        return dem;
    }

    /**
     * @param aDem the dem to set
     */
    public static void setDem(int aDem) {
        dem = aDem;
    }

    /**
     * @return the soThuTu
     */
    public String getSoThuTu() {
        return soThuTu;
    }

    /**
     * @param soThuTu the soThuTu to set
     */
    public void setSoThuTu(String soThuTu) {
        this.soThuTu = soThuTu;
    }

    /**
     * @return the hoTen
     */
    public String getHoTen() {
        return hoTen;
    }

    /**
     * @param hoTen the hoTen to set
     */
    public void setHoTen(String hoTen) {
        this.hoTen = hoTen;
    }

    /**
     * @return the gioiTinh
     */
    public String getGioiTinh() {
        return gioiTinh;
    }

    /**
     * @param gioiTinh the gioiTinh to set
     */
    public void setGioiTinh(String gioiTinh) {
        this.gioiTinh = gioiTinh;
    }

    /**
     * @return the ngaySinh
     */
    public Date getNgaySinh() {
        return ngaySinh;
    }

    /**
     * @param ngaySinh the ngaySinh to set
     */
    public void setNgaySinh(Date ngaySinh) {
        this.ngaySinh = ngaySinh;
    }

    /**
     * @return the queQuan
     */
    public String getQueQuan() {
        return queQuan;
    }

    /**
     * @param queQuan the queQuan to set
     */
    public void setQueQuan(String queQuan) {
        this.queQuan = queQuan;
    }

    /**
     * @return the CCCD
     */
    public int getCCCD() {
        return CCCD;
    }

    /**
     * @param CCCD the CCCD to set
     */
    public void setCCCD(int CCCD) {
        this.CCCD = CCCD;
    }

    /**
     * @return the tienGui
     */
    /**
     * @return the taiKhoan
     */
    public String getTaiKhoan() {
        return taiKhoan;
    }

    /**
     * @param taiKhoan the taiKhoan to set
     */
    public void setTaiKhoan(String taiKhoan) {
        this.taiKhoan = taiKhoan;
    }

    /**
     * @return the matKhau
     */
    public String getMatKhau() {
        return matKhau;
    }

    /**
     * @param matKhau the matKhau to set
     */
    public void setMatKhau(String matKhau) {
        this.matKhau = matKhau;
    }

    /**
     * @return the id
     */
    public String getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * @return the taiKhoanGuiTien
     */
    public TaiKhoanGuiTien getTaiKhoanGuiTien() {
        return taiKhoanGuiTien;
    }

    /**
     * @param taiKhoanGuiTien the taiKhoanGuiTien to set
     */
    public void setTaiKhoanGuiTien(TaiKhoanGuiTien taiKhoanGuiTien) {
        this.taiKhoanGuiTien = taiKhoanGuiTien;
    }

}
