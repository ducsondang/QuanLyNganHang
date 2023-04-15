/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ducson.baitaplon;

import com.ducson.cauhinh.CauHinh;
import java.text.ParseException;
import java.util.Date;
import java.util.Random;

/**
 *
 * @author khach
 */
public class TaiKhoanKyHan extends TaiKhoanGuiTien {

    private static String id;
    private Date ngayTao = new Date();
    private String kyHan;

    {
        setId(getId() + 1);
    }

    public TaiKhoanKyHan() {
        System.out.println("Ky han cua tai khoan\n1: 1 Tuan: 2%/Nam\n2: 1 Thang: 5.5%/Nam\n3: 6 Thang: 7.5%/Nam\n4: 12 Thang: 7.9%/Nam\n");
        String str = CauHinh.sc.next();
        int number = 0;
        try {
            number = Integer.parseInt(str);
        } catch (Exception ex) {
            // code xử lý ngoại lệ
        }
        if (number == 1) {
            this.setLai(2.0);
            this.setKyHan("1 Tuan");
        }
        if (number == 2) {
            this.setLai(5.5);
            this.setKyHan("1 Thang");

        }
        if (number == 3) {
            this.setLai(7.5);
            this.setKyHan("6 Thang");

        }
        if (number == 4) {
            this.setLai(7.9);
            this.setKyHan("12Thang");

        }else{
            System.out.println("Nhap sai vui long nhap lai");
        }
    }

    public void hienThi() {
        System.out.printf("ID: %s\nKy Han: %s\nLai Xuat: %s\nSo Tien: %sVND\n", this.getId(), this.getKyHan(), this.getLai(),this.getTien());
    }

    /**
     * @return the kyHan
     */
    public String getKyHan() {
        return kyHan;
    }

    /**
     * @param kyHan the kyHan to set
     */
    public void setKyHan(String kyHan) {
        this.kyHan = kyHan;
    }

    /**
     * @return the id
     */
    public String getId() {
        return id;
    }

    /**
     * @param aId the id to set
     */
    public void setId(String aId) {
        this.id = aId;
    }

    /**
     * @param aId the id to set
     */
    /**
     * @return the ngayTao
     */
    /**
     * @return the soThuTu
     */
}
