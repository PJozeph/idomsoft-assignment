/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package home.assignment.idomsoft.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;

import javax.validation.constraints.Max;
import javax.validation.constraints.Size;

import org.springframework.format.annotation.DateTimeFormat;

import home.assignment.idomsoft.validation.Gender;
import home.assignment.idomsoft.validation.Name;
import home.assignment.idomsoft.validation.Nationality;

public class SzemelyDTO implements Serializable{

    public String getVisNev() {
        return visNev;
    }

    public void setVisNev(String visNev) {
        this.visNev = visNev;
    }

    public String getSzulNev() {
        return szulNev;
    }

    public void setSzulNev(String szulNev) {
        this.szulNev = szulNev;
    }

    public String getaNev() {
        return aNev;
    }

    public void setaNev(String aNev) {
        this.aNev = aNev;
    }

    public Date getSzulDat() {
        return szulDat;
    }

    public void setSzulDat(Date szulDat) {
        this.szulDat = szulDat;
    }

    public String getNeme() {
        return neme;
    }

    public void setNeme(String neme) {
        this.neme = neme;
    }

    public String getAllampKod() {
        return allampKod;
    }

    public void setAllampKod(String allampKod) {
        this.allampKod = allampKod;
    }

    public String getAllampDekod() {
        return allampDekod;
    }

    public void setAllampDekod(String allampDekod) {
        this.allampDekod = allampDekod;
    }

    public ArrayList<OkmanyDTO> getOkmLista() {
        return okmLista;
    }

    public void setOkmLista(ArrayList<OkmanyDTO> okmLista) {
        this.okmLista = okmLista;
    }
    private static final long serialVersionUID = 4L;

    @Size(min=1, max=80)
    @Name
    private String visNev;
   
    @Size(min=1, max=80)
    @Name
    private String szulNev;
    
    @Size(min=1, max=80)
    @Name
    private String aNev;
    
    @DateTimeFormat(pattern = "yyyy.MM.dd")
    private Date szulDat;

    @Gender
    private String neme;
    
    @Size(min=3)
    @Nationality
    private String allampKod;
    
    private String allampDekod;
    
    private ArrayList<OkmanyDTO> okmLista;
    
}
