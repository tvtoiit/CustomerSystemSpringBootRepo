package com.example.CustomerSystemSpringboot.entitys;

import java.math.BigDecimal;
import java.sql.Timestamp;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;

@Entity
@Table(name = "MSTUSER")
public class MstUser {
	@Id
    @Column(name = "PSN_CD")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int psnCd;

    @Column(name = "USERID", length = 8)
    private String userId;

    @Column(name = "PASSWORD", length = 8)
    private String password;

    @Column(name = "USERNAME", length = 40)
    private String username;

    @Column(name = "DELETE_YMD")
    @Temporal(TemporalType.TIMESTAMP)
    private Timestamp deleteYmd;

    @Column(name = "INSERT_YMD", updatable = false, insertable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Timestamp insertYmd;

    @Column(name = "INSERT_PSN_CD", columnDefinition = "numeric(5,0)")
    private BigDecimal insertPsnCd;

    @Column(name = "UPDATE_YMD", updatable = false, insertable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Timestamp updateYmd;

    @Column(name = "UPDATE_PSN_CD", columnDefinition = "numeric(5,0)")
    private BigDecimal updatePsnCd;

    public MstUser() {
    	super();
    }
    
	public MstUser(int psnCd, String userId, String password, String username, Timestamp deleteYmd, Timestamp insertYmd,
			BigDecimal insertPsnCd, Timestamp updateYmd, BigDecimal updatePsnCd) {
		super();
		this.psnCd = psnCd;
		this.userId = userId;
		this.password = password;
		this.username = username;
		this.deleteYmd = deleteYmd;
		this.insertYmd = insertYmd;
		this.insertPsnCd = insertPsnCd;
		this.updateYmd = updateYmd;
		this.updatePsnCd = updatePsnCd;
	}

	public int getPsnCd() {
		return psnCd;
	}

	public void setPsnCd(int psnCd) {
		this.psnCd = psnCd;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public Timestamp getDeleteYmd() {
		return deleteYmd;
	}

	public void setDeleteYmd(Timestamp deleteYmd) {
		this.deleteYmd = deleteYmd;
	}

	public Timestamp getInsertYmd() {
		return insertYmd;
	}

	public void setInsertYmd(Timestamp insertYmd) {
		this.insertYmd = insertYmd;
	}

	public BigDecimal getInsertPsnCd() {
		return insertPsnCd;
	}

	public void setInsertPsnCd(BigDecimal insertPsnCd) {
		this.insertPsnCd = insertPsnCd;
	}

	public Timestamp getUpdateYmd() {
		return updateYmd;
	}

	public void setUpdateYmd(Timestamp updateYmd) {
		this.updateYmd = updateYmd;
	}

	public BigDecimal getUpdatePsnCd() {
		return updatePsnCd;
	}

	public void setUpdatePsnCd(BigDecimal updatePsnCd) {
		this.updatePsnCd = updatePsnCd;
	}
}
