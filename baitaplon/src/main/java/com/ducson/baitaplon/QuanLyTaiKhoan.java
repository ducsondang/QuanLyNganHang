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
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;

/**
 *
 * @author khach
 */
public class QuanLyTaiKhoan {

    private List<TaiKhoan> dsAccount = new ArrayList<>();

    public void taoTaiKhoan(TaiKhoan... h) {
        this.dsAccount.addAll(Arrays.asList(h));
    }

    public void taoTaiKhoan(int n) throws ParseException, IOException {
        for (int i = 1; i <= n; i++) {
            System.out.printf("== Nhap tai khoan %d ==\n", i);
            TaiKhoan h = new TaiKhoan();
            if (this.dsAccount.stream().filter(u -> u.getTaiKhoan().equals(h.getTaiKhoan())).findFirst().isEmpty() == false) {
                System.out.println("UserName da co nguoi su dung!\nVui long cung cap UserName khac");
            } else {
                this.dsAccount.add(h);
                h.LuuTaiKhoan();
            }

        }
    }

    public void luuDSTaiKhoan() {
        File f = new File("src/main/resources/taikhoan.txt");
        if (f.delete()) {
            this.dsAccount.forEach(h -> {
                try {
                    h.LuuTaiKhoan();
                } catch (IOException ex) {
                    Logger.getLogger(QuanLyTaiKhoan.class.getName()).log(Level.SEVERE, null, ex);
                }
            });
        }
    }

    public void dangNhap(String userName, String pass) throws ParseException, FileNotFoundException, IOException {
        if (this.dsAccount.stream().filter(t -> t.getTaiKhoan().equals(userName)).findFirst().isEmpty() == false) {
            TaiKhoan a = this.dsAccount.stream().filter(t -> t.getTaiKhoan().equals(userName)).findFirst().get();
            if (this.dsAccount.stream().filter(t -> t.getTaiKhoan().equals(userName)).findFirst().isEmpty() == false && a.getMatKhau().equals(pass) == true) {
                a.getTaiKhoanGuiTien().congLaiXuat(365);
                System.out.println("Dang nhap thanh cong");
                System.out.println("====================");
                System.out.println("1: Doi mat khau\n2: Them tai khoan co ky han\n3: Rut Tien\n4: Xem Lai Xuat\n5: Danh Sach tai khoan ky han:\n6: Nap Tien Vao Tai Khoan ky han");
                int numble = CauHinh.sc.nextInt();
                a.nhapTaiKhoanKyHan("src/main/resources/taikhoankyhan.txt");
                switch (numble) {
                    case 1:
                        System.out.println("Nhap mat khau moi");
                        a.setMatKhau(CauHinh.sc.next());
                        luuDSTaiKhoan();
                        System.out.println("Doi mat khau thanh cong");
                        break;
                    case 3:
                        System.out.println("Nhap So Tien Rut");
                        a.getTaiKhoanGuiTien().setTienGui(CauHinh.sc.nextDouble(), 2);
                        luuDSTaiKhoan();
                        System.out.println("Rut Tien Thanh Cong");
                        break;
                    case 2:
                        if (a.getTaiKhoanGuiTien().getTien() < 100000) {
                            System.out.println("Yeu cau Tai Khoan chinh phai có tren 100.000 VND");
                        }
                        if (a.getTaiKhoanGuiTien().getTien() > 100000) {
                            a.moTaiKhoanKyHan();
                        }
                        break;
                    case 4:
                        System.out.printf("Tien Lai Hang Thang Cua Quy Khach La: %.1f VND\n", a.getTaiKhoanGuiTien().tinhLaiXuat());
                        break;
                    case 5:
                        a.hienThiTaiKhoanKyHan();
                        break;
                    case 6:
                        a.napTienVaoTKKyHan();
                        break;
                    default:
                        throw new AssertionError();
                }
            } else {
                System.out.println("USERNAME hoac PASS khong chinh xac");
            }
        } else {
            System.out.println("USERNAME hoac PASS khong chinh xac");
        }

    }

    public void guiRutTien(String tk, double tien, int a) {
        this.dsAccount.stream().filter(t -> t.getTaiKhoan().equals(tk)).findFirst().get().getTaiKhoanGuiTien().setTienGui(tien, a);
    }

    public void hienThi() {
        this.dsAccount.forEach(h -> h.hienThi());
    }

    public TaiKhoan timKiemTheoId(String id) {
        return this.dsAccount.stream().filter(h -> h.getId().equals(id)).findFirst().get();
    }

    public TaiKhoan timKiemTheoTaiKhoan(String tk) {
        return this.dsAccount.stream().filter(h -> h.getTaiKhoan().equals(tk)).findFirst().get();
    }
    
    public List<TaiKhoan> timKiemTheoTen(String kw) {
        return this.dsAccount.stream().filter(h -> h.getHoTen().contains(kw)).collect(Collectors.toList());
    }

    public void nhapTaiKhoan(String path) throws FileNotFoundException, ParseException {
        File f = new File(path);
        try ( Scanner sc = new Scanner(f)) {
            while (sc.hasNext()) {
                String id = sc.nextLine();
                String name = sc.nextLine();
                String gioiTinh = sc.nextLine();
                String taiKhoan = sc.nextLine();
                String namSinh = sc.nextLine();
                String que = sc.nextLine();
                int CCCD = sc.nextInt();
                double money = sc.nextDouble();
                String pass = sc.next();
                String ngayTao = sc.next();
                if (sc.hasNext()) {
                    sc.nextLine();
                }
                TaiKhoan h = new TaiKhoan(id, name, gioiTinh, taiKhoan, namSinh, que, CCCD, money, pass, ngayTao);
                this.dsAccount.add(h);
            }
        }
    }

    public void sapXep() {
        this.dsAccount.sort((h1, h2) -> {
            double tb1 = h1.getTaiKhoanGuiTien().getTien();
            double tb2 = h2.getTaiKhoanGuiTien().getTien();

            if (tb1 < tb2) {
                return 1;
            } else if (tb1 > tb2) {
                return -1;
            }
            return 0;
        });
        luuDSTaiKhoan();
    }

}
