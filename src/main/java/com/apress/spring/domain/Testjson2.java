package com.apress.spring.domain;

import java.math.BigDecimal;
import java.util.List;

import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.apache.commons.lang3.builder.ToStringBuilder;

/*
 * 使用hibernate类型转换
 * SQL script:
 * testdb=# create table testjson2(id serial, data json, answers jsonb, remain_balance decimal(19,6));
 * testdb=# create table testjson3(id serial, data jsonb, remain_balance decimal(19,6));
 * */
@Entity
@Table(name = "testjson2")
// @Table(name="testjson3")
public class Testjson2 {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	private String data;

	// List转换为String,数据库中再转换为JSON.
	@Convert(converter = ListString2JsonConverter.class)
	private List<String> answers;

	private BigDecimal remainBalance;

	public Testjson2() {
	}

	public Testjson2(String data) {
		this.data = data;
		this.remainBalance = new BigDecimal(0); // BigDecimal.ZERO
	}

	public Testjson2(String data, BigDecimal remainBalance) {
		this.data = data;
		this.remainBalance = remainBalance;
	}

	public Testjson2(String data, BigDecimal remainBalance, List<String> answers) {
		this.data = data;
		this.remainBalance = remainBalance;
		this.answers = answers;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getData() {
		return data;
	}

	public void setData(String data) {
		this.data = data;
	}

	@Override
	public String toString() {
		// StringBuilder builder = new StringBuilder();
		// builder.append("Testjson [id=");
		// builder.append(id);
		// builder.append(", data=");
		// builder.append(data);
		// builder.append(", remainBalance=");
		// builder.append(remainBalance);
		// builder.append("]");
		// return builder.toString();

		return ToStringBuilder.reflectionToString(this);
	}

	public BigDecimal getRemainBalance() {
		return remainBalance;
	}

	public void setRemainBalance(BigDecimal remainBalance) {
		this.remainBalance = remainBalance;
	}

	public List<String> getAnswers() {
		return answers;
	}

	public void setAnswers(List<String> answers) {
		this.answers = answers;
	}

}
