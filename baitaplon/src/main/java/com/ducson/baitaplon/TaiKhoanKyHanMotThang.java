/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ducson.baitaplon;

import com.ducson.cauhinh.CauHinh;
import java.io.IOException;
import java.text.ParseException;
import java.util.Calendar;
import java.util.Date;

/**
 *
 * @author khach
 */
public class TaiKhoanKyHanMotThang extends TaiKhoanKyHan {

    public TaiKhoanKyHanMotThang(int dem, String idAccout) throws IOException {
        super.setId(dem + 1);
        super.setIdAccount(idAccout);
        this.luuTaiKhoan();
    }

    public TaiKhoanKyHanMotThang(int ID, String idAccount, double tien, double lai, int kyHan, String ngayTao) throws ParseException, IOException {
        super.setId(ID);
        super.setIdAccount(idAccount);
        super.setTienGui(tien, 0);
        super.setLai(lai);
        super.setKyHan(kyHan);
        super.setNgayTao(CauHinh.f.parse(ngayTao));
    }

    @Override
    public void setLai(double lai) {
        super.setLai(5.5);
        super.setKyHan(30);
    }

    @Override
    public Date tinhNgayDaoHan() {
        Calendar c1 = Calendar.getInstance();
        c1.setTime(this.getNgayTao());
        c1.add(Calendar.DATE, (30));
        return c1.getTime();
    }

}
