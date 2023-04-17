/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ducson.baitaplon;

import java.io.IOException;
import java.util.Calendar;
import java.util.Date;

/**
 *
 * @author khach
 */
public class TaiKhoanKyHanMotNam extends TaiKhoanKyHan {
    public TaiKhoanKyHanMotNam(int dem, String idAccout) throws IOException{
        super.setId(dem + 1);
        super.setIdAccount(idAccout);
        this.LuuTaiKhoan();
    }
 
    @Override
    public void setLai(double lai) {
        super.setLai(7.9);
        super.setKyHan("1Nam");
    }
    @Override
    public Date tinhNgayDaoHan() {
        Calendar c1 = Calendar.getInstance();
         c1.setTime(this.getNgayTao());
         c1.add(Calendar.DATE, (365));
        return c1.getTime();
    }
    
}
