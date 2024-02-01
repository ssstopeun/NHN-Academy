package com.nhnacademy.jdbc.bank.service.impl;

import com.nhnacademy.jdbc.bank.domain.Account;
import com.nhnacademy.jdbc.bank.exception.AccountAreadyExistException;
import com.nhnacademy.jdbc.bank.exception.AccountNotFoundException;
import com.nhnacademy.jdbc.bank.exception.BalanceNotEnoughException;
import com.nhnacademy.jdbc.bank.repository.AccountRepository;
import com.nhnacademy.jdbc.bank.service.BankService;

import java.sql.Connection;
import java.util.Optional;

public class BankServiceImpl implements BankService {

    private final AccountRepository accountRepository;

    public BankServiceImpl(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    @Override
    public Account getAccount(Connection connection, long accountNumber){
        //todo#11 계좌-조회
        Optional<Account> optionalAccount = accountRepository.findByAccountNumber(connection,accountNumber);

        if(optionalAccount.isEmpty()){
            throw new AccountNotFoundException(accountNumber);
        }
        return optionalAccount.get();
    }

    @Override
    public void createAccount(Connection connection, Account account){
        //todo#12 계좌-등록
        if(isExistAccount(connection,account.getAccountNumber())){
            throw new AccountAreadyExistException(account.getAccountNumber());
        }
        int result = accountRepository.save(connection,account);
        if(result<1){
            throw new RuntimeException();
        }
    }

    @Override
    public boolean depositAccount(Connection connection, long accountNumber, long amount){
        //todo#13 예금, 계좌가 존재하는지 체크 -> 예금실행 -> 성공 true, 실패 false;
        if(!isExistAccount(connection,accountNumber)){
            throw new AccountNotFoundException(accountNumber);
        }
        int result = accountRepository.deposit(connection,accountNumber,amount);
        return result > 0;
    }

    @Override
    public boolean withdrawAccount(Connection connection, long accountNumber, long amount){
        //todo#14 출금, 계좌가 존재하는지 체크 ->  출금가능여부 체크 -> 출금실행, 성공 true, 실폐 false 반환
        if(!isExistAccount(connection,accountNumber)){
            throw new AccountNotFoundException(accountNumber);
        }
        Optional<Account> optionalAccount = accountRepository.findByAccountNumber(connection,accountNumber);
        Account account = optionalAccount.get();
        if(account.getBalance()<=amount){
            throw new BalanceNotEnoughException(accountNumber);
        }
        int result = accountRepository.withdraw(connection,accountNumber,amount);
        return result >0;
    }

    @Override
    public void transferAmount(Connection connection, long accountNumberFrom, long accountNumberTo, long amount){
        //todo#15 계좌 이체 accountNumberFrom -> accountNumberTo 으로 amount만큼 이체

        //계좌 체크
        if(!isExistAccount(connection,accountNumberFrom)){
            throw new AccountNotFoundException(accountNumberFrom);
        }
        if(!isExistAccount(connection,accountNumberTo)){
            throw new AccountNotFoundException(accountNumberTo);
        }

        //From에 amount가 있는지 확인
        Account account = accountRepository.findByAccountNumber(connection,accountNumberFrom).get();
        Account account2 = accountRepository.findByAccountNumber(connection,accountNumberTo).get();
        if(account.getBalance()<=amount){
            throw new BalanceNotEnoughException(accountNumberFrom);
        }

        int depositResult = accountRepository.deposit(connection,accountNumberFrom,amount);
        int withdrawResult = accountRepository.withdraw(connection,accountNumberTo,amount);

        if(depositResult<1){
            throw new RuntimeException("deposit error");
        }
        if(withdrawResult<1){
            throw new RuntimeException("withdraw error");
        }
    }

    @Override
    public boolean isExistAccount(Connection connection, long accountNumber){
        //todo#16 Account가 존재하면 true , 존재하지 않다면 false
        return accountRepository.countByAccountNumber(connection,accountNumber) > 0 ;
    }

    @Override
    public void dropAccount(Connection connection, long accountNumber) {
        //todo#17 account 삭제
        if(!isExistAccount(connection,accountNumber)){
            throw new AccountNotFoundException(accountNumber);
        }
        int result = accountRepository.deleteByAccountNumber(connection,accountNumber);
        if(result < 1){
            throw new RuntimeException();
        }
    }

}