/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ducson.baitaplon;

import com.ducson.cauhinh.CauHinh;
import java.util.Date;

/**
 *
 * @author khach
 */
public class TaiKhoanGuiTien {

    private double tien = 0;
    private double lai;
    private Date ngayTao = new Date();
    private Date ngayCapNhatLai = ngayTao;

    /**
     * @return the tien
     */
    public double getTien() {
        return tien;
    }

    public TaiKhoanGuiTien() {
        this.setLai(0.2);
    }

    /**
     * @param tien the tien to set
     */
    public void setTienGui(double tienGui, int a) {
        if (a == 0) {
            this.tien = tienGui;
        }
        if (a == 2) {
            this.tien -= tienGui;
        }
        if (a == 1) {
            this.tien += tienGui;
        }
    }

    /**
     * @return the lai
     */
    public double getLai() {
        return lai;
    }
    /**
     * tính lãi xuất theo ngày với kỳ hạn 0.2%/nam
     * ngayHan số ngày tính lãi.
     */
    public double tinhLaiXuat(int ngayHan) {
        return this.getTien() / 100 * this.getLai()/365 * ngayHan;
    }

    /**
     * cộng tiền lãi vào tài khoản khi đủ ngày han là ngày lãi
     */
    public double congLaiXuat(int han) {
        if ((new Date().getTime() - this.getNgayCapNhatLai().getTime()) / (24 * 3600 * 1000) >= han) {
            int h = (int) (((new Date().getTime() - this.getNgayTao().getTime()) / (24 * 3600 * 1000)) / han);
            for (int i = 1; i <= h; i++) {
                this.setTienGui(this.tinhLaiXuat(365), 1);
            }
            this.setNgayCapNhatLai(new Date());
            return this.getTien();
        } else {
            this.setNgayCapNhatLai(new Date());
            return this.getTien();
        }

    }

    /**
     * @param lai the lai to set
     */
    public void setLai(double lai) {
        this.lai = lai;
    }

    /**
     * @return the ngayTao
     */
    public Date getNgayTao() {
        return ngayTao;
    }

    /**
     * @param ngayTao the ngayTao to set
     */
    public void setNgayTao(Date ngayTao) {
        this.ngayTao = ngayTao;
    }

    /**
     * @return the ngayCapNhatLai
     */
    public Date getNgayCapNhatLai() {
        return ngayCapNhatLai;
    }

    /**
     * @param ngayCapNhatLai the ngayCapNhatLai to set
     */
    public void setNgayCapNhatLai(Date ngayCapNhatLai) {
        this.ngayCapNhatLai = ngayCapNhatLai;
    }

}
