package service;

import java.sql.Connection;

public interface SupplyService {
    public void addSupply(Connection con);
    public void getSupplyByDescription(Connection con);
    public void updateSupplyByDescription(Connection con);
    public void deleteSupplyByDescription(Connection con);
    public void getSuppliesByProject(Connection con);
}
