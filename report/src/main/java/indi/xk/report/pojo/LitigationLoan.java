package indi.xk.report.pojo;

/**
 * Created by zxy on 2019/12/24.
 */
public class LitigationLoan {
    private String jgmc;//机构名称
    private String laqs;//立案起数
    private String ssdkqs;//涉诉贷款起数
    private String sabj;//涉案本金
    private String salx;//涉案利息
    private String pjqs;//判决起数


    public String getJgmc() {
        return jgmc;
    }

    public void setJgmc(String jgmc) {
        this.jgmc = jgmc;
    }

    public String getLaqs() {
        return laqs;
    }

    public void setLaqs(String laqs) {
        this.laqs = laqs;
    }

    public String getSsdkqs() {
        return ssdkqs;
    }

    public void setSsdkqs(String ssdkqs) {
        this.ssdkqs = ssdkqs;
    }

    public String getSabj() {
        return sabj;
    }

    public void setSabj(String sabj) {
        this.sabj = sabj;
    }

    public String getSalx() {
        return salx;
    }

    public void setSalx(String salx) {
        this.salx = salx;
    }

    public String getPjqs() {
        return pjqs;
    }

    public void setPjqs(String pjqs) {
        this.pjqs = pjqs;
    }

    @Override
    public String toString() {
        return "LitigationLoan{" +
                "jgmc='" + jgmc + '\'' +
                ", laqs='" + laqs + '\'' +
                ", ssdkqs='" + ssdkqs + '\'' +
                ", sabj='" + sabj + '\'' +
                ", salx='" + salx + '\'' +
                ", pjqs='" + pjqs + '\'' +
                '}';
    }
}
