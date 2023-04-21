/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ducson.baitaplon;

import com.ducson.cauhinh.CauHinh;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

/**
 *
 * @author khach
 */
public class Account {

    private static int dem = 1;
    private String soThuTu;
    private String hoTen;
    private String gioiTinh;
    private Date ngaySinh;
    private String queQuan;
    private int CCCD;
    private String account;
    private String matKhau;
    private String id;
    private TaiKhoanGuiTien taiKhoanGuiTien;
    private List<TaiKhoanKyHan> dsKyHan = new ArrayList<>();
    private boolean check = true;

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

    public Account(String id, String ten, String gioiTinh, String taiKhoan, String ngaySinh, String Que, int CCCD, double tien, String passwork, String ngay, String ngayCapNhatLai) throws ParseException {
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
        this.account = taiKhoan;
        this.taiKhoanGuiTien.setNgayTao(CauHinh.f.parse(ngay));
        this.taiKhoanGuiTien.setNgayCapNhatLai(CauHinh.f.parse(ngayCapNhatLai));
    }

    public Account() {
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
            System.out.println("Nhap sai dinh dang ngay sinh");
        }
        this.setNgaySinh(ngaySinh);
        System.out.print("Gioi Tinh: ");
        this.setGioiTinh(CauHinh.sc.nextLine());
        System.out.print("Que Quan: ");
        this.setQueQuan(CauHinh.sc.nextLine());
        System.out.print("Tai khoan: ");
        this.setAccount(CauHinh.sc.next());
        System.out.print("CCCD: ");
        this.setCCCD(0);
        String intCccd = CauHinh.sc.next();
        try {
            this.setCCCD(Integer.parseInt(intCccd));
        } catch (Exception ex) {
            System.out.println("Nhap sai dinh dang CCCD");
        }
        System.out.print("Tien Gui(VND): ");
        taiKhoanGuiTien.setTienGui(0, 0);
        double intTien = CauHinh.sc.nextDouble();
        try {
            taiKhoanGuiTien.setTienGui(intTien, 0);
        } catch (Exception ex) {
            System.out.println("Nhap sai dinh dang tien");
        }
        this.taiKhoanGuiTien.setNgayTao(new Date());
        this.setId((CauHinh.f.format(this.taiKhoanGuiTien.getNgayTao()) + this.soThuTu).replaceAll("/", ""));
        this.taiKhoanGuiTien.setNgayCapNhatLai(new Date());
        this.setMatKhau(Integer.toString((new Random()).nextInt(999999)));
    }

    public void hienThi() {
        System.out.println("=======================");
        System.out.printf("ID: %s\nHo va ten: %s\nNgay Sinh: %s\nTai Khoan: %s\nSo tien tai khoan thanh toan: %.1f VND\nTong tien: %.1f VND\n",
                this.getId(), this.getHoTen(), CauHinh.f.format(this.getNgaySinh()),
                this.getAccount(), this.getTaiKhoanGuiTien().getTien(), this.tinhTongTien());
    }

    public void moTaiKhoanKyHan() throws IOException {
        System.out.println("Ky han cua tai khoan\n1: 1 Tuan: 2%/Nam\n2: 1 Thang: 5.5%/Nam\n3: 6 Thang: 7.5%/Nam\n4: 12 Thang: 7.9%/Nam\n");
        String str = CauHinh.sc.next();
        int number = 0;
        try {
            number = Integer.parseInt(str);
        } catch (Exception ex) {
            // code xử lý ngoại lệ
        }
        if (number == 1) {
            TaiKhoanKyHanMotTuan a = new TaiKhoanKyHanMotTuan(this.dsKyHan.size(), this.getId());
            this.getDsKyHan().add(a);
        }
        if (number == 2) {
            TaiKhoanKyHanMotThang a = new TaiKhoanKyHanMotThang(this.dsKyHan.size(), this.getId());
            this.getDsKyHan().add(a);
        }
        if (number == 3) {
            TaiKhoanKyHanSauThang a = new TaiKhoanKyHanSauThang(this.dsKyHan.size(), this.getId());
            this.getDsKyHan().add(a);
        }
        if (number == 4) {
            TaiKhoanKyHanMotNam a = new TaiKhoanKyHanMotNam(this.dsKyHan.size(), this.getId());
            this.getDsKyHan().add(a);
        } else {
            System.out.println("Nhap sai vui long nhap lai");
        }
    }
    
    public void luuDSTaiKhoanKyHan() {
        File n = new File("src/main/resources/taikhoankyahan.txt");
        if (n.delete()) {
            this.dsKyHan.forEach(h -> {
                try {
                    h.luuTaiKhoan();
                } catch (IOException ex) {
                    System.out.println("Loi khong luu duoc tai khoan");
                }
            });
        }
    }
    /**
     * lấy dữ liệu tài khoản kỳ hạn từ file taikhoankyhan.txt
     */
    public void nhapTaiKhoanKyHan(String path) throws FileNotFoundException, ParseException, IOException {
        if (this.isCheck() == true) {
            File f = new File(path);
            try ( Scanner sc = new Scanner(f)) {
                while (sc.hasNext()) {
                    int ID = sc.nextInt();
                    String idAccount = sc.next();
                    double tien = sc.nextDouble();
                    double lai = sc.nextDouble();
                    int kyHan = sc.nextInt();
                    String ngayTao = sc.next();
                    if (sc.hasNext()) {
                        sc.nextLine();
                    }
                    if (idAccount.equals(this.getId())) { // kiểm tra tài khoản kỳ hạn của account
                        if (lai == 2.0) {
                            TaiKhoanKyHanMotTuan h = new TaiKhoanKyHanMotTuan(ID, idAccount, tien, lai, kyHan, ngayTao);
                            this.dsKyHan.add(h);
                        }
                        if (lai == 5.5) {
                            TaiKhoanKyHanMotThang h = new TaiKhoanKyHanMotThang(ID, idAccount, tien, lai, kyHan, ngayTao);
                            this.dsKyHan.add(h);
                        }
                        if (lai == 7.5) {
                            TaiKhoanKyHanSauThang h = new TaiKhoanKyHanSauThang(ID, idAccount, tien, lai, kyHan, ngayTao);
                            this.dsKyHan.add(h);
                        }
                        if (lai == 7.9) {
                            TaiKhoanKyHanMotNam h = new TaiKhoanKyHanMotNam(ID, idAccount, tien, lai, kyHan, ngayTao);
                            this.dsKyHan.add(h);
                        }
                    }
                    this.setCheck(false);
                }
            }

        }
    }

    public void hienThiTaiKhoanKyHan() {
        if (this.dsKyHan.isEmpty()) {
            System.out.println("ban chua co tai khoan ky han");
        } else {
            this.getDsKyHan().forEach(a -> a.hienThi());
        }
    }

    /**
     *
     *
     */
    public void saveAccount() throws IOException {
        File f = new File("src/main/resources/account.txt");
        FileWriter w = new FileWriter(f, true);
        try ( PrintWriter t = new PrintWriter(w)) {
            t.println(this.getId());
            t.println(this.getHoTen());
            t.println(this.getGioiTinh());
            t.println(this.getAccount());
            t.println(CauHinh.f.format(this.getNgaySinh()));
            t.println(this.getQueQuan());
            t.println(this.getCCCD());
            t.println(this.getTaiKhoanGuiTien().getTien());
            t.println(this.getMatKhau());
            t.println(CauHinh.f.format(this.taiKhoanGuiTien.getNgayTao()));
            t.println(CauHinh.f.format(this.taiKhoanGuiTien.getNgayCapNhatLai()));
        }
    }

    /**
     * chức năng chuyển tiền từ tài khoản chính vào tài khoản kì hạn vào ngày
     * đáo hạn
     *
     */
    public void napTienVaoTKKyHan() {
        System.out.println("Nhap ID Tai Khoan ky han");
        String strId = CauHinh.sc.next();
        int number = 0;
        try {
            number = Integer.parseInt(strId);
        } catch (Exception ex) {
            System.out.println("Nhap sai vui long nhap lai!");
        }
        if (number > this.dsKyHan.size()) {
            System.out.print("Khong co tai khoan ky han \nVui long nhap lai\n");
        } else {
            // kiểm tra ngày đầu tiên tạo tài khoản
            if ((new Date().getTime() - this.getTaiKhoanKyHan(number).getNgayTao().getTime()) / (24 * 1000 * 3600) == 0) {
                System.out.println("Nhap so tien");
                String strMoney = CauHinh.sc.next();
                double money = 0;
                try {
                    money = Integer.parseInt(strMoney);
                } catch (Exception ex) {
                    System.out.println("Nhap sai vui long nhap lai!");
                }
                if (this.getTaiKhoanGuiTien().getTien() - money < 50000) {
                    System.out.println("Tai khoan chinh phai co tu 50000VND tro len sau khi chuyen vao tai khoan!\nvui long nhap lai!");
                } else {
                    this.taiKhoanGuiTien.setTienGui(money, 2);
                    this.getTaiKhoanKyHan(number).setTienGui(money, 1);
                    System.out.printf("Nap tien vao tai khoan ID: %s thanh cong\n", this.getTaiKhoanKyHan(number).getId());
                }
            // kiểm tra có phải là ngày đáo hạn hay không
            }if ((new Date().getTime() - this.getTaiKhoanKyHan(number).tinhNgayDaoHan().getTime()) / (24 * 1000 * 3600) == 0) {
                System.out.println("Nhap so tien");
                String strMoney = CauHinh.sc.next();
                double money = 0;
                try {
                    money = Integer.parseInt(strMoney);
                } catch (Exception ex) {
                    System.out.println("Nhap sai vui long nhap lai!");
                }
                if (this.getTaiKhoanGuiTien().getTien() - money < 50000) {
                    System.out.println("Tai khoan chinh phai co tu 50000VND tro len sau khi chuyen vao tai khoan!\nvui long nhap lai!");
                } else {
                    this.taiKhoanGuiTien.setTienGui(money, 2);
                    this.getTaiKhoanKyHan(number).setTienGui(money, 1);
                    System.out.printf("Nap tien vao tai khoan ID: %s thanh cong\n", this.getTaiKhoanKyHan(number).getId());
                }
            }if((new Date().getTime() - this.getTaiKhoanKyHan(number).tinhNgayDaoHan().getTime()) 
                    / (24 * 1000 * 3600) != 0 
                    && (new Date().getTime() - this.getTaiKhoanKyHan(number).getNgayTao().getTime()) 
                    / (24 * 1000 * 3600) != 0) {
                System.out.println("Tai Khoan nay chua toi ngay dao han");

            }
        }
        this.luuDSTaiKhoanKyHan();

    }

    public void rutTienTaiKhoanKyhan() {
        System.out.println("Nhap ID Tai Khoan ky han");
        String strId = CauHinh.sc.next();
        int number = 0;
        try {
            number = Integer.parseInt(strId);
        } catch (Exception ex) {
            System.out.println("Nhap sai vui long nhap lai!");
        }
        if (number > this.dsKyHan.size()) {
            System.out.print("Khong co tai khoan ky han \nVui long nhap lai\n");
        } else {
            System.out.println("Nhap so tien");
            String strMoney = CauHinh.sc.next();
            double money = 0;
            try {
                money = Integer.parseInt(strMoney);
            } catch (Exception ex) {
                System.out.println("Nhap sai vui long nhap lai!");
            }
            if ((new Date().getTime() - this.getTaiKhoanKyHan(number).tinhNgayDaoHan().getTime()) / (24 * 1000 * 3600) == 0) {
                if (this.getTaiKhoanKyHan(number).getTien() < money) {
                    System.out.println("Nhap qua so tien hien co!\nvui long nhap lai!");
                } else {
                    this.taiKhoanGuiTien.setTienGui(this.getTaiKhoanKyHan(number).tinhLaiXuat(money) + money, 1);
                    this.getTaiKhoanKyHan(number).setTienGui(this.getTaiKhoanKyHan(number).tinhLaiXuat(money) + money, 2);
                    System.out.printf("Rut tien vao tai khoan thanh cong\n");
                }
            } else {
                System.out.println("Tai Khoan nay chua toi ngay dao han! bạn có chắc muốn rút với số tiền lãi 0.2%?\n1: CO\n2: KHONG");
                int a = 0;
                String numberChon = CauHinh.sc.next();
                try {
                    a = Integer.parseInt(numberChon);
                } catch (Exception e) {
                    System.out.println("Nhap sai vui long nhap lai");
                }
                if (a == 1) {
                    this.getTaiKhoanKyHan(number).setTienGui(this.getTaiKhoanKyHan(number).getTien() + this.getTaiKhoanKyHan(number).tinhLaiXuat((int) ((new Date().getTime() - this.getTaiKhoanKyHan(number).tinhNgayDaoHan().getTime()) / (24 * 1000 * 3600))), 2);
                    this.taiKhoanGuiTien.setTienGui(this.getTaiKhoanKyHan(number).getTien() + this.getTaiKhoanKyHan(number).tinhLaiXuat((int) ((new Date().getTime() - this.getTaiKhoanKyHan(number).tinhNgayDaoHan().getTime()) / (24 * 1000 * 3600))), 1);
                }
            }
        }
        luuDSTaiKhoanKyHan();
    }

    public double tinhTongTien() {
        double a = 0;
        for (int i = 0; i < this.getDsKyHan().size(); i++) {
            a += this.getDsKyHan().get(i).getTien();
        }
        return this.getTaiKhoanGuiTien().getTien() + a;
    }

    /**
     * trả về tài khoản kỳ hạn
     *
     * @param id của tài khoản kỳ hạn
     */
    public TaiKhoanKyHan getTaiKhoanKyHan(int id) {
        return this.dsKyHan.stream().filter(h -> h.getId() == id).findFirst().get();
    }

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
    public String getAccount() {
        return account;
    }

    /**
     * @param taiKhoan the taiKhoan to set
     */
    public void setAccount(String taiKhoan) {
        this.account = taiKhoan;
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
     * @return the dsKyHan
     */
    public List<TaiKhoanKyHan> getDsKyHan() {
        return dsKyHan;
    }

    /**
     * @param dsKyHan the dsKyHan to set
     */
    public void setDsKyHan(List<TaiKhoanKyHan> dsKyHan) {
        this.dsKyHan = dsKyHan;
    }

    /**
     * @return the check
     */
    public boolean isCheck() {
        return check;
    }

    /**
     * @param check the check to set
     */
    public void setCheck(boolean check) {
        this.check = check;
    }
}
