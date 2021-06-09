package top.luhancc.hmily.server1.service;



public interface AccountInfoTcc {

    public void prepare(String accountNo, double amount);

    public void commit(String accountNo, double amount);

    public void rollback(String accountNo, double amount);
}
