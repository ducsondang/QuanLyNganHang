/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ducson.baitaplon;

import java.util.Date;

/**
 *
 * @author khach
 */
public class TaiKhoanGuiTien {

    private double tien = 0;
    private double lai;
    private Date ngayTao = new Date();

    /**
     * @return the tien
     */
    public double getTien() {
        return tien;
    }
    public TaiKhoanGuiTien(){
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

    public double tinhLaiXuat() {
        return this.getTien() / 100 * this.getLai();
    }
    /**
     * ???
     */
    public double congLaiXuat(int han) {
        if ((new Date().getTime() - this.getNgayTao().getTime()) / (24 * 3600 * 1000) >= han) {
            int h = (int) (((new Date().getTime() - this.getNgayTao().getTime()) / (24 * 3600 * 1000)) / han);
            double money = 0;
            for (int i = 1; i <= h; i++) {
                money = this.getTien() + this.tinhLaiXuat();
            }
            return money;
        } else {
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


}
