package com.cn.kevin.rt.java.util.function.bean;

/**
 * 身份证
 * @author wj
 * @date 2018-01-18
 */
public class IdentityCard {
    /**身份证号码*/
    private String IDNumber;
    /**身份证名称*/
    private String IDName;

    public IdentityCard(String IDNumber, String IDName) {
        this.IDNumber = IDNumber;
        this.IDName = IDName;
    }

    public String getIDNumber() {

        return IDNumber;
    }

    public void setIDNumber(String IDNumber) {
        this.IDNumber = IDNumber;
    }

    public String getIDName() {
        return IDName;
    }

    public void setIDName(String IDName) {
        this.IDName = IDName;
    }

    /**
     * 身份证号码相同即为同一个身份证
     * @param o
     * @return
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        IdentityCard that = (IdentityCard) o;

        return IDNumber.equals(that.IDNumber);
    }

    @Override
    public int hashCode() {
        return IDNumber.hashCode();
    }

    @Override
    public String toString() {
        return "IdentityCard{" +
                "IDNumber='" + IDNumber + '\'' +
                ", IDName='" + IDName + '\'' +
                '}';
    }
}
