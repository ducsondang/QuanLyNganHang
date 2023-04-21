/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ducson.baitaplon;

import com.ducson.cauhinh.CauHinh;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
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

    private List<Account> dsAccount = new ArrayList<>();

    public void taoAccount(Account... h) {
        this.dsAccount.addAll(Arrays.asList(h));
    }

    /**
     * tạo account
     *
     * @author khach
     */
    public void taoAccount(int n) throws ParseException, IOException {
        for (int i = 1; i <= n; i++) {
            System.out.printf("== Nhap tai khoan %d ==\n", i);
            Account h = new Account();
            if (this.dsAccount.stream().filter(u -> u.getAccount().equals(h.getAccount())).findFirst().isEmpty() == false) {
                System.out.println("UserName da co nguoi su dung!\nVui long cung cap UserName khac");
            } else {
                this.dsAccount.add(h);
                h.saveAccount();
                System.out.printf("Tao tai khoan thanh cong\nUser: %s\nPass: %s\n", h.getAccount(), h.getMatKhau());
            }

        }
    }

    /**
     * lưu danh sách account
     *
     * @author khach
     */
    public void luuDSAccount() {
        File f = new File("src/main/resources/account.txt");
        if (f.delete()) {
            this.dsAccount.forEach(h -> {
                try {
                    h.saveAccount();
                } catch (IOException ex) {
                    System.out.println("Loi khong luu duoc tai khoan");
                }
            });
        }
    }

    /**
     * lấy dữ liệu tai khoản kỳ hạn từ file
     *
     * @author khach
     */
    public void nhapTaiKhoanKyHan() {
        this.dsAccount.forEach(h -> {
            try {
                h.nhapTaiKhoanKyHan("src/main/resources/taikhoankyhan.txt");
            } catch (ParseException ex) {
                System.out.println("Loi khong nhap duoc tai khoan");
            } catch (IOException ex) {
                System.out.println("Loi khong nhap duoc tai khoan");
            }
        });
    }

    /**
     * các chức năng của khách hàng khi đăng nhập
     *
     * @author khach
     */
    public void dangNhap(String userName, String pass) throws ParseException, FileNotFoundException, IOException {
        boolean cont = true;
        if (this.dsAccount.stream().filter(t -> t.getAccount().equals(userName)).findFirst().isEmpty() == false) {
            Account a = this.dsAccount.stream().filter(t -> t.getAccount().equals(userName)).findFirst().get();
            if (this.dsAccount.stream().filter(t -> t.getAccount().equals(userName)).findFirst().isEmpty() == false && a.getMatKhau().equals(pass) == true) {
                a.getTaiKhoanGuiTien().congLaiXuat(1);
                luuDSAccount();
                System.out.println("Dang nhap thanh cong");
                System.out.println("====================");
                do {

                    System.out.println("1: Doi mat khau\n2: Them tai khoan co ky han\n3: Rut Tien\n4: Nap tien vao tai khoan\n5: Xem Lai Xuat\n6: Danh Sach tai khoan ky han:\n7: Nap Tien Vao Tai Khoan ky han\n8: Rut tien tai khoan ky han\n9: Thong tin tai khoan\n10: Thoat");
                    int number = 0;
                    String numberInput = CauHinh.sc.next();
                    try {
                        number = Integer.parseInt(numberInput);
                    } catch (Exception e) {
                        System.out.println("Nhap sai vui long nhap lai!");
                    }
                    switch (number) {
                        case 1:
                            System.out.println("Nhap mat khau moi");
                            a.setMatKhau(CauHinh.sc.next());
                            luuDSAccount();
                            System.out.println("Doi mat khau thanh cong\n========================");
                            break;
                        case 3:
                            System.out.println("Nhap So Tien Rut");
                            a.getTaiKhoanGuiTien().setTienGui(CauHinh.sc.nextDouble(), 2);
                            luuDSAccount();
                            System.out.println("Rut Tien Thanh Cong\n========================");
                            break;
                        case 2:
                            if (a.getTaiKhoanGuiTien().getTien() < 100000) {
                                System.out.println("Yeu cau Tai Khoan chinh phai có tren 100.000 VND");
                            }
                            if (a.getTaiKhoanGuiTien().getTien() > 100000) {
                                a.moTaiKhoanKyHan();
                            }
                            break;
                        case 5:
                            System.out.printf("Tien Lai Hang Thang Cua Quy Khach La: %.1f VND\n",
                                    a.getTaiKhoanGuiTien().tinhLaiXuat(30));
                            break;
                        case 4:
                            System.out.println("Nhap so tien nap");
                            double tien = 0;
                            String tienInput = CauHinh.sc.next();
                            try {
                                tien = Double.parseDouble(tienInput);
                                a.getTaiKhoanGuiTien().setTienGui(tien, 1);
                                System.out.println("Nap tien thanh cong");
                                luuDSAccount();
                            } catch (Exception e) {
                                System.out.println("nhap sai vui long nhap lai!");
                            }
                            break;
                        case 6:
                            System.out.println("Xin chao " + a.getHoTen());
                            a.hienThiTaiKhoanKyHan();
                            break;
                        case 7:
                            a.napTienVaoTKKyHan();
                            luuDSAccount();
                            break;
                        case 8:
                            a.rutTienTaiKhoanKyhan();
                            luuDSAccount();
                            break;
                        case 9:
                            a.hienThi();
                            break;
                        default:
                            cont = false;
                    }
                } while (cont);
            } else {
                System.out.println("USERNAME hoac PASS khong chinh xac");
            }
        } else {
            System.out.println("USERNAME hoac PASS khong chinh xac");
        }

    }

    public void guiRutTien(String tk, double tien, int a) {
        this.dsAccount.stream().filter(t -> t.getAccount().equals(tk)).findFirst().get().getTaiKhoanGuiTien().setTienGui(tien, a);
    }

    public void hienThi() {
        this.dsAccount.forEach(h -> h.hienThi());
    }

    public Account timKiemTheoId(String id) {
        return this.dsAccount.stream().filter(h -> h.getId().equals(id)).findFirst().get();
    }

    public Account timKiemTheoTaiKhoan(String tk) {
        return this.dsAccount.stream().filter(h -> h.getAccount().equals(tk)).findFirst().get();
    }

    public List<Account> timKiemTheoTen(String kw) {
        return this.dsAccount.stream().filter(h -> h.getHoTen().contains(kw)).collect(Collectors.toList());
    }

    /**
     * lấy danh sách account từ file.
     *
     * @author khach
     */
    public void nhapAccount(String path) throws FileNotFoundException, ParseException {
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
                String ngayCapNhatLai = sc.next();
                if (sc.hasNext()) {
                    sc.nextLine();
                }
                Account h = new Account(id, name, gioiTinh, taiKhoan, namSinh, que, CCCD, money, pass, ngayTao, ngayCapNhatLai);
                this.dsAccount.add(h);
            }
        }
    }

    /**
     * sắp xếp danh sách account theo tổng số tiền có(tk chính + tk kỳ hạn)
     *
     * @author khach
     */
    public void sapXep() {
        this.dsAccount.sort((h1, h2) -> {
            double tb1 = h1.tinhTongTien();
            double tb2 = h2.tinhTongTien();
            if (tb1 < tb2) {
                return 1;
            } else if (tb1 > tb2) {
                return -1;
            }
            return 0;
        });
        luuDSAccount();
    }
}
