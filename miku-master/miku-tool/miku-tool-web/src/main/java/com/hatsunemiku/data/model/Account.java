package com.hatsunemiku.data.model;

import java.io.Serializable;
import java.util.Date;

public class Account implements Serializable {
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column account.id_
     *
     * @mbggenerated
     */
    private Integer id;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column account.account_
     *
     * @mbggenerated
     */
    private String account;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column account.password_
     *
     * @mbggenerated
     */
    private String password;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column account.phone_
     *
     * @mbggenerated
     */
    private String phone;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column account.create_time_
     *
     * @mbggenerated
     */
    private Date createTime;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column account.usable_
     *
     * @mbggenerated
     */
    private Integer usable;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column account.email_
     *
     * @mbggenerated
     */
    private String email;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table account
     *
     * @mbggenerated
     */
    private static final long serialVersionUID = 1L;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column account.id_
     *
     * @return the value of account.id_
     *
     * @mbggenerated
     */
    public Integer getId() {
        return id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column account.id_
     *
     * @param id the value for account.id_
     *
     * @mbggenerated
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column account.account_
     *
     * @return the value of account.account_
     *
     * @mbggenerated
     */
    public String getAccount() {
        return account;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column account.account_
     *
     * @param account the value for account.account_
     *
     * @mbggenerated
     */
    public void setAccount(String account) {
        this.account = account == null ? null : account.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column account.password_
     *
     * @return the value of account.password_
     *
     * @mbggenerated
     */
    public String getPassword() {
        return password;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column account.password_
     *
     * @param password the value for account.password_
     *
     * @mbggenerated
     */
    public void setPassword(String password) {
        this.password = password == null ? null : password.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column account.phone_
     *
     * @return the value of account.phone_
     *
     * @mbggenerated
     */
    public String getPhone() {
        return phone;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column account.phone_
     *
     * @param phone the value for account.phone_
     *
     * @mbggenerated
     */
    public void setPhone(String phone) {
        this.phone = phone == null ? null : phone.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column account.create_time_
     *
     * @return the value of account.create_time_
     *
     * @mbggenerated
     */
    public Date getCreateTime() {
        return createTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column account.create_time_
     *
     * @param createTime the value for account.create_time_
     *
     * @mbggenerated
     */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column account.usable_
     *
     * @return the value of account.usable_
     *
     * @mbggenerated
     */
    public Integer getUsable() {
        return usable;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column account.usable_
     *
     * @param usable the value for account.usable_
     *
     * @mbggenerated
     */
    public void setUsable(Integer usable) {
        this.usable = usable;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column account.email_
     *
     * @return the value of account.email_
     *
     * @mbggenerated
     */
    public String getEmail() {
        return email;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column account.email_
     *
     * @param email the value for account.email_
     *
     * @mbggenerated
     */
    public void setEmail(String email) {
        this.email = email == null ? null : email.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table account
     *
     * @mbggenerated
     */
    @Override
    public boolean equals(Object that) {
        if (this == that) {
            return true;
        }
        if (that == null) {
            return false;
        }
        if (getClass() != that.getClass()) {
            return false;
        }
        Account other = (Account) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
            && (this.getAccount() == null ? other.getAccount() == null : this.getAccount().equals(other.getAccount()))
            && (this.getPassword() == null ? other.getPassword() == null : this.getPassword().equals(other.getPassword()))
            && (this.getPhone() == null ? other.getPhone() == null : this.getPhone().equals(other.getPhone()))
            && (this.getCreateTime() == null ? other.getCreateTime() == null : this.getCreateTime().equals(other.getCreateTime()))
            && (this.getUsable() == null ? other.getUsable() == null : this.getUsable().equals(other.getUsable()))
            && (this.getEmail() == null ? other.getEmail() == null : this.getEmail().equals(other.getEmail()));
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table account
     *
     * @mbggenerated
     */
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
        result = prime * result + ((getAccount() == null) ? 0 : getAccount().hashCode());
        result = prime * result + ((getPassword() == null) ? 0 : getPassword().hashCode());
        result = prime * result + ((getPhone() == null) ? 0 : getPhone().hashCode());
        result = prime * result + ((getCreateTime() == null) ? 0 : getCreateTime().hashCode());
        result = prime * result + ((getUsable() == null) ? 0 : getUsable().hashCode());
        result = prime * result + ((getEmail() == null) ? 0 : getEmail().hashCode());
        return result;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table account
     *
     * @mbggenerated
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", account=").append(account);
        sb.append(", password=").append(password);
        sb.append(", phone=").append(phone);
        sb.append(", createTime=").append(createTime);
        sb.append(", usable=").append(usable);
        sb.append(", email=").append(email);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}