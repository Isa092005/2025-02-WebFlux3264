package com.parcial.dos.parcialdos.account.dto;

import java.math.BigDecimal;

public class AccountResponseDTO {
    private Long id;
    private String numeroCuenta;
    private String dueno;
    private BigDecimal balance;
    private Boolean activo;

    public AccountResponseDTO() {}

    public AccountResponseDTO(Long id, String numeroCuenta, String dueno, BigDecimal balance, Boolean activo) {
        this.id = id;
        this.numeroCuenta = numeroCuenta;
        this.dueno = dueno;
        this.balance = balance;
        this.activo = activo;
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getNumeroCuenta() { return numeroCuenta; }
    public void setNumeroCuenta(String numeroCuenta) { this.numeroCuenta = numeroCuenta; }

    public String getDueno() { return dueno; }
    public void setDueno(String dueno) { this.dueno = dueno; }

    public BigDecimal getBalance() { return balance; }
    public void setBalance(BigDecimal balance) { this.balance = balance; }

    public Boolean getActivo() { return activo; }
    public void setActivo(Boolean activo) { this.activo = activo; }
}
