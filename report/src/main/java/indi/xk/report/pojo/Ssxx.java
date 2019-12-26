package indi.xk.report.pojo;

import java.math.BigDecimal;

public class Ssxx {
	private String id;
	private String jkid;// 借据号 jjh
	private Integer syts;//剩余天数
	private String dfmc;//对方名称 即借款人
	private String khh;
	private String jkrzjh;//借款人证件号码
	private String jkrlxfs;
	private String jkrxzz;
	private String fqrq;//发起日期
	private String sslx;
	private BigDecimal bde;//标的额
	private String pjsah;
	private String pjr;
	private String zxsxdqr;
	private String slfg;
	private String sljd;
	private String sszt;
	private String ajcbzrr;
	private String jd;//阶段
	private Integer ssbs;
	private String sfja;//是否结案
	private String jarq;
	private String jafs;
	private String jasm;
	private String fzr;
	private String fzrgh;
	private String orgCodeSs;//诉讼管理机构code
	private Integer sqbq;//是否诉前保全
	private Integer ssbq;//是否诉讼保全
	private BigDecimal ssszye;//发起诉讼时的余额
	private BigDecimal dqzye;//当前总余额
	
	private Integer fileType;
	private BigDecimal dkye;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getJkid() {
		return jkid;
	}

	public void setJkid(String jkid) {
		this.jkid = jkid;
	}

	public Integer getSyts() {
		return syts;
	}

	public void setSyts(Integer syts) {
		this.syts = syts;
	}

	public String getDfmc() {
		return dfmc;
	}

	public void setDfmc(String dfmc) {
		this.dfmc = dfmc;
	}

	public String getKhh() {
		return khh;
	}

	public void setKhh(String khh) {
		this.khh = khh;
	}

	public String getJkrzjh() {
		return jkrzjh;
	}

	public void setJkrzjh(String jkrzjh) {
		this.jkrzjh = jkrzjh;
	}

	public String getJkrlxfs() {
		return jkrlxfs;
	}

	public void setJkrlxfs(String jkrlxfs) {
		this.jkrlxfs = jkrlxfs;
	}

	public String getJkrxzz() {
		return jkrxzz;
	}

	public void setJkrxzz(String jkrxzz) {
		this.jkrxzz = jkrxzz;
	}

	public String getFqrq() {
		return fqrq;
	}

	public void setFqrq(String fqrq) {
		this.fqrq = fqrq;
	}

	public String getSslx() {
		return sslx;
	}

	public void setSslx(String sslx) {
		this.sslx = sslx;
	}


	public BigDecimal getBde() {
		return bde;
	}

	public void setBde(BigDecimal bde) {
		this.bde = bde;
	}

	public String getPjsah() {
		return pjsah;
	}

	public void setPjsah(String pjsah) {
		this.pjsah = pjsah;
	}

	public String getPjr() {
		return pjr;
	}

	public void setPjr(String pjr) {
		this.pjr = pjr;
	}

	public String getZxsxdqr() {
		return zxsxdqr;
	}

	public void setZxsxdqr(String zxsxdqr) {
		this.zxsxdqr = zxsxdqr;
	}

	public String getSlfg() {
		return slfg;
	}

	public void setSlfg(String slfg) {
		this.slfg = slfg;
	}

	public String getSljd() {
		return sljd;
	}

	public void setSljd(String sljd) {
		this.sljd = sljd;
	}

	public String getSszt() {
		return sszt;
	}

	public void setSszt(String sszt) {
		this.sszt = sszt;
	}

	public String getAjcbzrr() {
		return ajcbzrr;
	}

	public void setAjcbzrr(String ajcbzrr) {
		this.ajcbzrr = ajcbzrr;
	}

	public String getJd() {
		return jd;
	}

	public void setJd(String jd) {
		this.jd = jd;
	}

	public Integer getSsbs() {
		return ssbs;
	}

	public void setSsbs(Integer ssbs) {
		this.ssbs = ssbs;
	}

	public String getSfja() {
		return sfja;
	}

	public void setSfja(String sfja) {
		this.sfja = sfja;
	}

	public String getJarq() {
		return jarq;
	}

	public void setJarq(String jarq) {
		this.jarq = jarq;
	}

	public String getJafs() {
		return jafs;
	}

	public void setJafs(String jafs) {
		this.jafs = jafs;
	}

	public String getJasm() {
		return jasm;
	}

	public void setJasm(String jasm) {
		this.jasm = jasm;
	}

	public String getFzr() {
		return fzr;
	}

	public void setFzr(String fzr) {
		this.fzr = fzr;
	}


	public String getFzrgh() {
		return fzrgh;
	}

	public void setFzrgh(String fzrgh) {
		this.fzrgh = fzrgh;
	}


	public BigDecimal getDqzye() {
		return dqzye;
	}

	public void setDqzye(BigDecimal dqzye) {
		this.dqzye = dqzye;
	}

	public Integer getSqbq() {
		return sqbq;
	}

	public void setSqbq(Integer sqbq) {
		this.sqbq = sqbq;
	}

	public Integer getSsbq() {
		return ssbq;
	}

	public void setSsbq(Integer ssbq) {
		this.ssbq = ssbq;
	}

	public String getOrgCodeSs() {
		return orgCodeSs;
	}

	public void setOrgCodeSs(String orgCodeSs) {
		this.orgCodeSs = orgCodeSs;
	}

	public BigDecimal getSsszye() {
		return ssszye;
	}

	public void setSsszye(BigDecimal ssszye) {
		this.ssszye = ssszye;
	}

	public Integer getFileType() {
		return fileType;
	}

	public void setFileType(Integer fileType) {
		this.fileType = fileType;
	}

	public BigDecimal getDkye() {
		return dkye;
	}

	public void setDkye(BigDecimal dkye) {
		this.dkye = dkye;
	}



	
	
}