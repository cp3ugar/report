package indi.xk.report.pojo;

import java.util.UUID;

import javax.validation.constraints.Digits;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;


/**
 * 组织机构实体类.
 * @author WW
 * @date：2017年8月8日上午10:45:58
 */
@SuppressWarnings("serial")
public class Org extends BaseEntity{
	/**
	 * 组织机构ID.
	 */
	private String orgId;
	/**
	 * 组织机构名称.
	 */
	@NotNull(message = "组织机构名称不能为空")
	@Size(max=64, min=1, message = "组织机构名称最大长度为64位")
	private String orgName;
	/**
	 * 组织机构描述.
	 */
	@Size(max=256, min=0, message = "组织机构描述最大长度为256位")
	private String orgDesc;
	/**
	 * 组织机构类型.
	 */
	@Size(max=32, min=0, message = "组织机构类型最大长度为32位")
	private String orgType;
	/**
	 * 组织机构顺序.
	 */
	@Digits(integer = 11, fraction = 0, message = "组织机构顺序最大长度为11位")
	private int showOrder;
	/**
	 * 父ID.
	 */
	private String pid;
	/**
	 * 行政区划ID.
	 */
	//@Size(max=36, min=36, message = "行政区划ID长度为36位")
	private String zid;
	/**
	 * 行政区划名称.
	 */
	@Size(max=64, min=0, message = "行政区划名称最大长度为64位")
	private String zname;
	
	@Size(max=36, min=0, message = "组织机构号最大长度为36位")
	private String orgCode;
	
	
	public String getOrgId() {
		return orgId;
	}
	public void setOrgId(String orgId) {
		this.orgId = orgId;
	}
	public String getOrgName() {
		return orgName;
	}
	public void setOrgName(String orgName) {
		this.orgName = orgName;
	}
	public String getOrgDesc() {
		return orgDesc;
	}
	public void setOrgDesc(String orgDesc) {
		this.orgDesc = orgDesc;
	}
	
	public String getOrgType() {
		return orgType;
	}
	public void setOrgType(String orgType) {
		this.orgType = orgType;
	}
	public int getShowOrder() {
		return showOrder;
	}
	public void setShowOrder(int showOrder) {
		this.showOrder = showOrder;
	}
	public String getPid() {
		return pid;
	}
	public void setPid(String pid) {
		this.pid = pid;
	}
	public String getZid() {
		return zid;
	}
	public void setZid(String zid) {
		this.zid = zid;
	}
	public String getZname() {
		return zname;
	}
	public void setZname(String zname) {
		this.zname = zname;
	}
	
	public Org(){
		this.orgId = UUID.randomUUID().toString();
	}
	public String getOrgCode() {
		return orgCode;
	}
	public void setOrgCode(String orgCode) {
		this.orgCode = orgCode;
	}
	
}
	