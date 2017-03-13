package com.apress.spring.domain;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import org.hibernate.annotations.Type;
import org.hibernate.annotations.TypeDef;
import org.hibernate.annotations.TypeDefs;

import com.apress.spring.util.StringJsonUserType;


/*
 * SQL script:
 * testdb=# create table testjson(id serial, data jsonb, remain_balance decimal(19,6));
 * */

@Entity
// 指定表名
// @Table(name="test_json2")
@TypeDefs({ @TypeDef(name = "StringJsonObject", typeClass = StringJsonUserType.class) })
public class Testjson {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@Type(type = "StringJsonObject")
	@Column(columnDefinition = "json")
	private String data;

	private BigDecimal remainBalance;

	public Testjson() {
	}

	public Testjson(String data) {
		this.data = data;
		this.remainBalance = new BigDecimal(0); // BigDecimal.ZERO
	}

	public Testjson(String data, BigDecimal remainBalance) {
		this.data = data;
		this.remainBalance = remainBalance;
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
		StringBuilder builder = new StringBuilder();
		builder.append("Testjson [id=");
		builder.append(id);
		builder.append(", data=");
		builder.append(data);
		builder.append(", remainBalance=");
		builder.append(remainBalance);
		builder.append("]");
		return builder.toString();
	}

	public BigDecimal getRemainBalance() {
		return remainBalance;
	}

	public void setRemainBalance(BigDecimal remainBalance) {
		this.remainBalance = remainBalance;
	}

}
