package com.parcial.dos.parcialdos.account.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.parcial.dos.parcialdos.account.dto.AccountRequestDTO;
import com.parcial.dos.parcialdos.account.dto.AccountResponseDTO;
import com.parcial.dos.parcialdos.account.dto.AccountOwnerBalanceDTO;
import com.parcial.dos.parcialdos.account.entity.Account;
import com.parcial.dos.parcialdos.account.repository.AccountRepository;

import java.util.List;
import java.util.stream.Collectors;
import java.math.BigDecimal;
import java.util.Optional;

@Service
@Transactional
public class AccountServiceImpl implements IAccountService {

    private final AccountRepository repo;

    public AccountServiceImpl(AccountRepository repo) {
        this.repo = repo;
    }

    @Override
    public AccountResponseDTO create(AccountRequestDTO request) {
        Account acc = new Account(request.getNumeroCuenta(), request.getDueno(), request.getBalance(), request.getActivo());
        Account saved = repo.save(acc);
        return toResponse(saved);
    }

    @Override
    public List<AccountResponseDTO> getAll() {
        return repo.findAll().stream().map(this::toResponse).collect(Collectors.toList());
    }

    @Override
    public AccountResponseDTO getById(Long id) {
        return repo.findById(id).map(this::toResponse).orElse(null);
    }

    @Override
    public String update(Long id, AccountRequestDTO request) {
        Optional<Account> opt = repo.findById(id);
        if (opt.isEmpty()) return "Cuenta no encontrada";
        Account acc = opt.get();
        if (request.getNumeroCuenta() != null) acc.setAccountNumber(request.getNumeroCuenta());
        if (request.getDueno() != null) acc.setOwnerName(request.getDueno());
        if (request.getBalance() != null) acc.setBalance(request.getBalance());
        if (request.getActivo() != null) acc.setActive(request.getActivo());
        repo.save(acc);
        return "Actualizado";
    }

    @Override
    public void delete(Long id) {
        repo.deleteById(id);
    }

    @Override
    public AccountOwnerBalanceDTO findByNumeroCuenta(String numeroCuenta) {
        Optional<Account> opt = repo.findByAccountNumber(numeroCuenta);
        if (opt.isEmpty()) return null;
        Account a = opt.get();
        return new AccountOwnerBalanceDTO(a.getOwnerName(), a.getBalance());
    }

    private AccountResponseDTO toResponse(Account a) {
        return new AccountResponseDTO(a.getId(), a.getAccountNumber(), a.getOwnerName(), a.getBalance(), a.getActive());
    }
}