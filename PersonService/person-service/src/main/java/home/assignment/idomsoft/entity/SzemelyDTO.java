/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package home.assignment.idomsoft.entity;

import java.io.Serializable;
import java.util.List;

import javax.validation.constraints.Size;

import home.assignment.idomsoft.validation.BirthDate;
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

    public String getSzulDat() {
        return szulDat;
    }

    public void setSzulDat(String szulDat) {
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

    public List<OkmanyDTO> getOkmLista() {
        return okmLista;
    }

    public void setOkmLista(List<OkmanyDTO> okmLista) {
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
    
    @BirthDate
    private String szulDat;

    @Gender
    private String neme;
    
    @Size(min=3)
    @Nationality
    private String allampKod;
    
    private String allampDekod;
    
    private List<OkmanyDTO> okmLista;
    
}
