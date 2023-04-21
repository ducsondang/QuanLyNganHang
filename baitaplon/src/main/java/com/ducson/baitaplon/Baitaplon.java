/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Project/Maven2/JavaApp/src/main/java/${packagePath}/${mainClassName}.java to edit this template
 */
package com.ducson.baitaplon;

import com.ducson.cauhinh.CauHinh;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.ParseException;

/**
 *
 * @author khach
 */
public class Baitaplon {

    public static void main(String[] args) throws ParseException, FileNotFoundException, IOException {
        QuanLyTaiKhoan dstk = new QuanLyTaiKhoan();
        dstk.nhapAccount("src/main/resources/account.txt");
        dstk.nhapTaiKhoanKyHan();
        boolean cont = true;
        while (cont) {
            System.out.println("-----MENU-----\n1: Tao tai khoan\n2: Dang Nhap\n3: Tinh Tien Lai\n4: Gui rut tien\n5: Tra cuu khach hang theo ho ten\n6: Tra cuu khach hang theo ma so khach hang\n7: Danh sach tai khoan\n8: Sap xep danh sach\n9: Thoat\n");
            String chon = CauHinh.sc.nextLine();
            switch (chon) {
                case "1":
                    System.out.print("so luong tai khoan muon tao: ");
                    int numble = 0;
                    String numbleSLTaiKhoan = CauHinh.sc.next();
                    try {
                        numble = Integer.parseInt(numbleSLTaiKhoan);
                        dstk.taoAccount(numble);
                    } catch (Exception e) {
                        System.out.println("nhap sai vui long nhap lai");
                    }
                    break;
                case "2":
                    System.out.println("Nhap USERNAME:\nNhap PASS:");
                    String user = CauHinh.sc.next();
                    String Pass = CauHinh.sc.next();
                    dstk.dangNhap(user, Pass);
                    break;
                case "3":
                    System.out.println("Nhap Tai Khoan");
                    String tk = CauHinh.sc.next();
                    System.out.printf("Lai Xuat Hang Thang Cua Tai Khoan %s\n%.1f VND\n", dstk.timKiemTheoTaiKhoan(tk).getAccount(), dstk.timKiemTheoTaiKhoan(tk).getTaiKhoanGuiTien().tinhLaiXuat(30));
                    break;
                case "4":
                    System.out.println("Nhap Tai Khoan");
                    String Tk = CauHinh.sc.next();
                    System.out.println("1: Rut Tien\n2: Gui tien");
                    int rutGui = 0;
                    String rutGuiInput = CauHinh.sc.next();
                    try {
                        rutGui = Integer.parseInt(rutGuiInput);
                    } catch (Exception e) {
                        System.out.println("nhap sai vui long nhap lai");
                    }
                    if (rutGui == 1) {
                        System.out.println("nhap so tien: ");
                        double tien = 0;
                        String tienInput = CauHinh.sc.next();
                        try {
                            tien = Double.parseDouble(tienInput);
                        } catch (Exception e) {
                            System.out.println("Nhap sai vui long nhap lai!");
                        }
                        dstk.guiRutTien(Tk, tien, 2);
                        dstk.luuDSAccount();
                    }
                    if (rutGui == 2) {
                        System.out.println("nhap so tien: ");
                        double tien = CauHinh.sc.nextDouble();
                        dstk.guiRutTien(Tk, tien, 1);
                        dstk.luuDSAccount();
                    } else {
                        System.out.println("chon sai vui long chon lai!");
                    }
                    break;
                case "5":
                    System.out.print("Nhap ten can tim: ");
                    String nameSearch = CauHinh.sc.nextLine();
                    dstk.timKiemTheoTen(nameSearch).forEach(h -> h.hienThi());
                    break;
                case "6":
                    System.out.println("nhap ma so khach hang: ");
                    String id = CauHinh.sc.nextLine();
                    dstk.timKiemTheoId(id).hienThi();
                    break;
                case "7":
                    dstk.hienThi();
                    break;
                case "8":
                    dstk.sapXep();
                    break;
                default:
//                    cont = false;
                    break;
            }
        }
    }
}
