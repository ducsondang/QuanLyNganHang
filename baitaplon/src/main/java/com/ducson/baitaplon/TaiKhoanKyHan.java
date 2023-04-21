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
import java.util.Date;

/**
 *
 * @author khach
 */
public abstract class TaiKhoanKyHan extends TaiKhoanGuiTien {

    private int id;
    private Date ngayTao = new Date();
    private int kyHan;
    private String idAccount;

    /**
     *
     *
     * @return
     */
    public abstract Date tinhNgayDaoHan();

    public void luuTaiKhoan() throws IOException {
        File f = new File("src/main/resources/taikhoankyhan.txt");
        FileWriter w = new FileWriter(f, true);
        try ( PrintWriter t = new PrintWriter(w)) {
            t.println(this.getId());
            t.println(this.getIdAccount());
            t.println(this.getTien());
            t.println(this.getLai());
            t.println(this.getKyHan());
            t.println(CauHinh.f.format(this.getNgayTao()));
        }
    }

    public void hienThi() {
        System.out.printf("================\nID: %s\nID Account: %s\nKy Han: %s Ngay\nLai Xuat: %s/Nam\nSo Tien: %.1fVND\nNgay Dao Han : %s\nTien lai sau khi het ky han: %.1fVND\n", 
                this.getId(), this.getIdAccount(), this.getKyHan(), this.getLai(),
                this.getTien(), CauHinh.f.format(this.tinhNgayDaoHan()), this.tinhLaiXuat());
    }
    
    public double tinhLaiXuat() {
        return this.getTien() / 100 * this.getLai()/365 * this.kyHan;
    }
    
    public double tinhLaiXuat(double money) {
        return money / 100 * this.getLai()/365 * this.kyHan;
    }
    /**
     * @return the kyHan
     */
    public int getKyHan() {
        return kyHan;
    }

    /**
     * @param kyHan the kyHan to set
     */
    public void setKyHan(int kyHan) {
        this.kyHan = kyHan;
    }

    /**
     * @return the id
     */
    public int getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * @return the idAccount
     */
    public String getIdAccount() {
        return idAccount;
    }

    /**
     * @param idAccount the idAccount to set
     */
    public void setIdAccount(String idAccount) {
        this.idAccount = idAccount;
    }

}
